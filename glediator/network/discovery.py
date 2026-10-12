from __future__ import annotations

import socket
import threading
import queue

class DeviceDiscovery:
    """Listen for MrCodeTastic UDP discovery beacons without blocking the UI."""

    PORT = 20586
    WEBSOCKET_PORT = 12345
    BEACON = b"CODETASTIC"

    def __init__(self, port: int = PORT) -> None:
        self.port = int(port)
        self._stop = threading.Event()
        self._thread: threading.Thread | None = None
        self._socket: socket.socket | None = None
        self._events: queue.SimpleQueue[tuple[str, str]] = queue.SimpleQueue()

    @classmethod
    def is_beacon(cls, payload: bytes) -> bool:
        return payload.strip(b"\x00 \t\r\n").upper() == cls.BEACON

    def start(self) -> None:
        if self._thread and self._thread.is_alive():
            return
        self._stop.clear()
        self._thread = threading.Thread(target=self._listen, name="GlediatorDeviceDiscovery", daemon=True)
        self._thread.start()

    def stop(self) -> None:
        self._stop.set()
        if self._socket:
            try:
                self._socket.close()
            except OSError:
                pass
        if self._thread and self._thread is not threading.current_thread():
            self._thread.join(timeout=1.5)
        self._thread = None
        self._socket = None

    def poll_events(self) -> list[tuple[str, str]]:
        events = []
        while True:
            try:
                events.append(self._events.get_nowait())
            except queue.Empty:
                return events

    def _listen(self) -> None:
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self._socket = sock
        try:
            sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
            sock.bind(("0.0.0.0", self.port))
            sock.settimeout(.5)
            self._events.put(("ready", ""))
            while not self._stop.is_set():
                try:
                    payload, (address, _sender_port) = sock.recvfrom(2048)
                except socket.timeout:
                    continue
                except OSError:
                    if not self._stop.is_set():
                        raise
                    break
                if address and self.is_beacon(payload):
                    self._events.put(("device", address))
        except Exception as exc:
            if not self._stop.is_set():
                self._events.put(("error", f"UDP device discovery on port {self.port} failed: {exc}"))
        finally:
            try:
                sock.close()
            except OSError:
                pass
