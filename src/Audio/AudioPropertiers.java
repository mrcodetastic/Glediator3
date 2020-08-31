// 
// Decompiled by Procyon v0.5.36
// 

package Audio;

public class AudioPropertiers
{
    public int left_audio_level;
    public int right_audio_level;
    public int stereo_audio_level;
    public int average_audio_level;
    public int audio_trigger_level;
    public int fft_binns;
    public int gain;
    public float[] fft;
    
    public AudioPropertiers() {
        this.left_audio_level = 0;
        this.right_audio_level = 0;
        this.stereo_audio_level = 0;
        this.average_audio_level = 0;
        this.audio_trigger_level = 0;
        this.fft_binns = 0;
        this.fft = new float[this.fft_binns];
    }
}
