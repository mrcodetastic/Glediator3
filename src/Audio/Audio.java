// 
// Decompiled by Procyon v0.5.36
// 

package Audio;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioFormat;
import java.io.ByteArrayOutputStream;

public class Audio
{
    private static final int BUFFER_LENGTH = 2048;
    private static final int AUDIO_LEVEL_BUFFER_LENGTH = 100;
    ByteArrayOutputStream byteArrayOutputStream;
    AudioFormat audioFormat;
    TargetDataLine targetDataLine;
    AudioInputStream audioInputStream;
    SourceDataLine sourceDataLine;
    Mixer.Info[] mixerInfo;
    Mixer.Info[] supported_mixerInfo;
    Mixer mixer;
    boolean mixer_opened;
    byte[] tempBuffer;
    SoundSpectrum sound_spectrum;
    private int[] audio_level_buffer;
    
    public Audio() {
        this.mixer_opened = false;
        this.tempBuffer = new byte[2048];
        this.sound_spectrum = new SoundSpectrum();
        this.audio_level_buffer = new int[100];
        for (int i = 0; i < 100; ++i) {
            this.audio_level_buffer[i] = 0;
        }
    }
    
    public Mixer.Info[] GetMixerInfo() {
        int count = 0;
        try {
            final Line.Info targetDLInfo = new Line.Info(TargetDataLine.class);
            this.mixerInfo = AudioSystem.getMixerInfo();
            this.supported_mixerInfo = AudioSystem.getMixerInfo();
            for (int cnt = 0; cnt < this.mixerInfo.length; ++cnt) {
                final Mixer currentMixer = AudioSystem.getMixer(this.mixerInfo[cnt]);
                if (currentMixer.isLineSupported(targetDLInfo)) {
                    this.supported_mixerInfo[count] = this.mixerInfo[cnt];
                    ++count;
                }
            }
            this.mixerInfo = new Mixer.Info[count];
            for (int i = 0; i < this.mixerInfo.length; ++i) {
                this.mixerInfo[i] = this.supported_mixerInfo[i];
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return this.mixerInfo;
    }
    
    public void SetAudioFormat() {
        this.audioFormat = this.getAudioFormat();
    }
    
    public void Set_and_Start_Mixer(final int dev) {
        final DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, this.audioFormat);
        try {
            this.mixer = AudioSystem.getMixer(this.mixerInfo[dev]);
            (this.targetDataLine = (TargetDataLine)this.mixer.getLine(dataLineInfo)).open(this.audioFormat);
            this.targetDataLine.start();
            this.mixer_opened = true;
        }
        catch (Exception e) {
            System.out.println(e);
            this.mixer_opened = false;
        }
    }
    
    public void Stop_Mixer() {
        this.mixer.close();
        this.mixer_opened = false;
    }
    
    public int[] processAudio(final AudioPropertiers audio_properties) {
        final int[] stereo_level = new int[2];
        if (this.mixer_opened) {
            try {
                final int cnt = this.targetDataLine.read(this.tempBuffer, 0, this.tempBuffer.length);
                audio_properties.fft = this.do_fft_and_spectrum(this.tempBuffer, audio_properties.fft_binns, stereo_level);
                stereo_level[0] *= (int)(audio_properties.gain / 10.0f);
                stereo_level[1] *= (int)(audio_properties.gain / 10.0f);
                if (stereo_level[0] > 100) {
                    stereo_level[0] = 100;
                }
                if (stereo_level[1] > 100) {
                    stereo_level[1] = 100;
                }
                audio_properties.stereo_audio_level = (stereo_level[0] + stereo_level[1]) / 2;
                int average_audio_level = 0;
                for (int i = 0; i < 99; ++i) {
                    this.audio_level_buffer[i] = this.audio_level_buffer[i + 1];
                    average_audio_level += this.audio_level_buffer[i];
                }
                this.audio_level_buffer[99] = audio_properties.stereo_audio_level;
                average_audio_level += this.audio_level_buffer[99];
                average_audio_level /= 100;
                audio_properties.average_audio_level = average_audio_level;
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        return stereo_level;
    }
    
    private AudioFormat getAudioFormat() {
        final float sampleRate = 44100.0f;
        final int sampleSizeInBits = 16;
        final int channels = 2;
        final boolean signed = true;
        final boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }
    
    float[] do_fft_and_spectrum(final byte[] buffer, final int number_of_binns, final int[] stereo_level) {
        return this.sound_spectrum.do_fft(buffer, number_of_binns, stereo_level);
    }
}
