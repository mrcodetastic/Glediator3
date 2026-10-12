from __future__ import annotations

import queue
import threading
import time

import numpy as np

from glediator.core.frame import to_rgb565
from glediator.mapper import map_frame


class MrCodetasticWebSocketOutput:
    """Send one complete RGB565 frame per WebSocket binary message."""

    FRAME_MARKER = b"\x81\x81"

    def __init__(self, url: str, mapping: str = "horizontal-snake") -> None:
        self.url, self.mapping = url, mapping
        self._queue: queue.Queue[np.ndarray | None] = queue.Queue(maxsize=2)
        self._thread: threading.Thread | None = None
        self._stop = threading.Event()
        self.connected = False
        self.last_error = ""
        self._frame_id = 0

    def start(self) -> None:
        if self._thread and self._thread.is_alive():
            return
        self._stop.clear()
        self._thread = threading.Thread(target=self._worker, name="MrCodetasticWebSocket", daemon=True)
        self._thread.start()

    def stop(self) -> None:
        self._stop.set()
        try:
            self._queue.put_nowait(None)
        except queue.Full:
            pass
        if self._thread:
            self._thread.join(timeout=2)

    def send(self, frame: np.ndarray) -> None:
        copy = np.asarray(frame, dtype=np.uint8).copy()
        try:
            self._queue.put_nowait(copy)
        except queue.Full:
            try:
                self._queue.get_nowait()
            except queue.Empty:
                pass
            try:
                self._queue.put_nowait(copy)
            except queue.Full:
                pass

    def _worker(self) -> None:
        # Keep packet encoding and core tests usable when optional app dependencies
        # have not yet been installed; the runtime dependency is declared in pyproject.
        from websockets.sync.client import connect

        while not self._stop.is_set():
            try:
                with connect(self.url, open_timeout=2, close_timeout=1, max_size=None) as websocket:
                    self.connected = True
                    self.last_error = ""
                    while not self._stop.is_set():
                        try:
                            frame = self._queue.get(timeout=0.2)
                        except queue.Empty:
                            continue
                        if frame is None:
                            break
                        websocket.send(self.encode_frame(frame, self.mapping))
            except Exception as exc:  # Network failures should never stop rendering.
                self.connected = False
                self.last_error = str(exc)
                time.sleep(1.0)
        self.connected = False

    @classmethod
    def encode_frame(cls, frame: np.ndarray, mapping: str = "horizontal-snake") -> bytes:
        """Encode marker 0x81 0x81 followed by row-major RGB565 big-endian pixels."""
        pixels = to_rgb565(map_frame(frame, mapping)).reshape(-1)
        return cls.FRAME_MARKER + pixels.astype(">u2", copy=False).tobytes()
