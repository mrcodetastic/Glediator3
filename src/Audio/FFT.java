// 
// Decompiled by Procyon v0.5.36
// 

package Audio;

public class FFT
{
    private float[] xre;
    private float[] xim;
    private float[] mag;
    private float[] fftSin;
    private float[] fftCos;
    private int[] fftBr;
    private int ss;
    private int ss2;
    private int nu;
    private int nu1;
    
    public FFT(final int pSampleSize) {
        this.ss = pSampleSize;
        this.ss2 = this.ss >> 1;
        this.xre = new float[this.ss];
        this.xim = new float[this.ss];
        this.mag = new float[this.ss2];
        this.nu = (int)(Math.log(this.ss) / Math.log(2.0));
        this.nu1 = this.nu - 1;
        this.prepareFFTTables();
    }
    
    private int bitrev(final int j, final int nu) {
        int j2 = j;
        int k = 0;
        for (int i = 1; i <= nu; ++i) {
            final int j3 = j2 >> 1;
            k = (k << 1) + j2 - (j3 << 1);
            j2 = j3;
        }
        return k;
    }
    
    public float[] calculate(final float[] pSample) {
        int n2 = this.ss2;
        int nu1 = this.nu - 1;
        for (int wAps = pSample.length / this.ss, a = 0, b = 0; a < pSample.length; a += wAps, ++b) {
            this.xre[b] = pSample[a];
            this.xim[b] = 0.0f;
        }
        int x = 0;
        for (int l = 1; l <= this.nu; ++l) {
            for (int k = 0; k < this.ss; k += n2) {
                for (int i = 1; i <= n2; ++i) {
                    final float c = this.fftCos[x];
                    final float s = this.fftSin[x];
                    final int kn2 = k + n2;
                    final float tr = this.xre[kn2] * c + this.xim[kn2] * s;
                    final float ti = this.xim[kn2] * c - this.xre[kn2] * s;
                    this.xre[kn2] = this.xre[k] - tr;
                    this.xim[kn2] = this.xim[k] - ti;
                    final float[] xre = this.xre;
                    final int n3 = k;
                    xre[n3] += tr;
                    final float[] xim = this.xim;
                    final int n4 = k;
                    xim[n4] += ti;
                    ++k;
                    ++x;
                }
            }
            --nu1;
            n2 >>= 1;
        }
        for (int k = 0; k < this.ss; ++k) {
            final int r = this.fftBr[k];
            if (r > k) {
                final float tr = this.xre[k];
                final float ti = this.xim[k];
                this.xre[k] = this.xre[r];
                this.xim[k] = this.xim[r];
                this.xre[r] = tr;
                this.xim[r] = ti;
            }
        }
        this.mag[0] = (float)Math.sqrt(this.xre[0] * this.xre[0] + this.xim[0] * this.xim[0]) / this.ss;
        for (int i = 1; i < this.ss2; ++i) {
            this.mag[i] = 2.0f * (float)Math.sqrt(this.xre[i] * this.xre[i] + this.xim[i] * this.xim[i]) / this.ss;
        }
        return this.mag;
    }
    
    private void prepareFFTTables() {
        int n2 = this.ss2;
        int nu1 = this.nu - 1;
        this.fftSin = new float[this.nu * n2];
        this.fftCos = new float[this.nu * n2];
        int k = 0;
        int x = 0;
        for (int l = 1; l <= this.nu; ++l) {
            while (k < this.ss) {
                for (int i = 1; i <= n2; ++i) {
                    final float p = (float)this.bitrev(k >> nu1, this.nu);
                    final float arg = 6.2831855f * p / this.ss;
                    this.fftSin[x] = (float)Math.sin(arg);
                    this.fftCos[x] = (float)Math.cos(arg);
                    ++k;
                    ++x;
                }
                k += n2;
            }
            k = 0;
            --nu1;
            n2 >>= 1;
        }
        this.fftBr = new int[this.ss];
        for (k = 0; k < this.ss; ++k) {
            this.fftBr[k] = this.bitrev(k, this.nu);
        }
    }
}
