// 
// Decompiled by Procyon v0.5.36
// 

package Audio;

public class SoundSpectrum
{
    private float[] left;
    private float[] right;
    private int sampleSize;
    private byte[] audioDataBuffer;
    private FFT fft;
    private float[] old_FFT;
    private int desiredBands;
    
    public SoundSpectrum() {
        this.sampleSize = 512;
        this.audioDataBuffer = null;
        this.fft = new FFT(512);
        this.old_FFT = new float[512];
        this.desiredBands = 12;
    }
    
    public float[] do_fft(final byte[] buffer, final int number_of_binns, final int[] sound_level) {
        this.audioDataBuffer = buffer;
        this.desiredBands = number_of_binns;
        this.handleAudioBytes();
        sound_level[0] = this.calculateRMSLevel(this.left);
        sound_level[1] = this.calculateRMSLevel(this.right);
        return this.computeFFT(this.mergeStereo(this.left, this.right));
    }
    
    private void handleAudioBytes() {
        this.left = new float[this.sampleSize];
        this.right = new float[this.sampleSize];
        for (int c = 0, a = 0; a < this.sampleSize; ++a, c += 4) {
            this.left[a] = ((this.audioDataBuffer[c + 1] << 8) + this.audioDataBuffer[c]) / 32767.0f;
            this.right[a] = ((this.audioDataBuffer[c + 3] << 8) + this.audioDataBuffer[c + 2]) / 32767.0f;
        }
    }
    
    private float[] mergeStereo(final float[] pLeft, final float[] pRight) {
        for (int a = 0; a < pLeft.length; ++a) {
            pLeft[a] = (pLeft[a] + pRight[a]) / 2.0f;
        }
        return pLeft;
    }
    
    private float[] computeFFT(final float[] pSample) {
        final int saMultiplier = 256 / this.desiredBands;
        final float c = 0.0f;
        final float[] wFFT = this.fft.calculate(pSample);
        final float wSadfrr = 0.03f;
        final float[] spectrum = new float[this.desiredBands];
        int a = 0;
        for (int bd = 0; bd < this.desiredBands; ++bd) {
            float wFs = 0.0f;
            for (int b = 0; b < saMultiplier; ++b) {
                wFs += wFFT[a + b];
            }
            wFs *= (float)Math.log(bd + 2);
            if (wFs > 1.0f) {
                wFs = 1.0f;
            }
            if (wFs >= this.old_FFT[a]) {
                this.old_FFT[a] = wFs;
            }
            else {
                final float[] old_FFT = this.old_FFT;
                final int n = a;
                old_FFT[n] -= wSadfrr;
                if (this.old_FFT[a] < 0.0f) {
                    this.old_FFT[a] = 0.0f;
                }
                wFs = this.old_FFT[a];
            }
            spectrum[bd] = wFs;
            a += saMultiplier;
        }
        return spectrum;
    }
    
    private int calculateRMSLevel(final float[] audioData) {
        float lSum = 0.0f;
        for (int i = 0; i < audioData.length; ++i) {
            lSum += audioData[i];
        }
        final double dAvg = lSum / audioData.length;
        double sumMeanSquare = 0.0;
        for (int j = 0; j < audioData.length; ++j) {
            sumMeanSquare += Math.pow(audioData[j] - dAvg, 2.0);
        }
        final double averageMeanSquare = sumMeanSquare / audioData.length;
        return (int)(200.0 * (Math.pow(averageMeanSquare, 0.5) + 0.0));
    }
}
