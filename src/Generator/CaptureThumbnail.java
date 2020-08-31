// 
// Decompiled by Procyon v0.5.36
// 

package Generator;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;

public class CaptureThumbnail
{
    public BufferedImage createThumbnail(BufferedImage inImage, final int largestDimension) {
        try {
            double scale;
            int sizeDifference;
            int originalImageLargestDim;
            if (inImage.getWidth(null) > inImage.getHeight(null)) {
                scale = largestDimension / (double)inImage.getWidth(null);
                sizeDifference = inImage.getWidth(null) - largestDimension;
                originalImageLargestDim = inImage.getWidth(null);
            }
            else {
                scale = largestDimension / (double)inImage.getHeight(null);
                sizeDifference = inImage.getHeight(null) - largestDimension;
                originalImageLargestDim = inImage.getHeight(null);
            }
            BufferedImage outImage = new BufferedImage(100, 100, 1);
            if (scale < 1.0) {
                final int numSteps = sizeDifference / 100;
                final int stepSize = sizeDifference / numSteps;
                final int stepWeight = stepSize / 2;
                final int heavierStepSize = stepSize + stepWeight;
                final int lighterStepSize = stepSize - stepWeight;
                double scaledW = inImage.getWidth(null);
                double scaledH = inImage.getHeight(null);
                int centerStep;
                if (numSteps % 2 == 1) {
                    centerStep = (int)Math.ceil(numSteps / 2.0);
                }
                else {
                    centerStep = -1;
                }
                Integer intermediateSize = originalImageLargestDim;
                Integer previousIntermediateSize = originalImageLargestDim;
                for (Integer i = 0; i < numSteps; ++i) {
                    int currentStepSize;
                    if (i + 1 != centerStep) {
                        if (i == numSteps - 1) {
                            currentStepSize = previousIntermediateSize - largestDimension;
                        }
                        else if (numSteps - i > numSteps / 2) {
                            currentStepSize = heavierStepSize;
                        }
                        else {
                            currentStepSize = lighterStepSize;
                        }
                    }
                    else {
                        currentStepSize = stepSize;
                    }
                    intermediateSize = previousIntermediateSize - currentStepSize;
                    scale = intermediateSize / (double)previousIntermediateSize;
                    scaledW = (int)scaledW * scale;
                    scaledH = (int)scaledH * scale;
                    outImage = new BufferedImage((int)scaledW, (int)scaledH, 1);
                    final Graphics2D g2d = outImage.createGraphics();
                    g2d.setBackground(Color.WHITE);
                    g2d.clearRect(0, 0, outImage.getWidth(), outImage.getHeight());
                    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    final AffineTransform tx = new AffineTransform();
                    tx.scale(scale, scale);
                    g2d.drawImage(inImage, tx, null);
                    g2d.dispose();
                    inImage = (BufferedImage)new ImageIcon(outImage).getImage();
                    previousIntermediateSize = intermediateSize;
                }
            }
            else {
                outImage = new BufferedImage(inImage.getWidth(null), inImage.getHeight(null), 1);
                final Graphics2D g2d = outImage.createGraphics();
                g2d.setBackground(Color.WHITE);
                g2d.clearRect(0, 0, outImage.getWidth(), outImage.getHeight());
                final AffineTransform tx = new AffineTransform();
                tx.setToIdentity();
                g2d.drawImage(inImage, tx, null);
                g2d.dispose();
            }
            return outImage;
        }
        catch (Exception ex) {
            String errorMsg = "";
            errorMsg = errorMsg + "<br>Exception: " + ex.toString();
            errorMsg = errorMsg + "<br>Cause = " + ex.getCause();
            errorMsg += "<br>Stack Trace = ";
            final StackTraceElement[] stackTrace = ex.getStackTrace();
            for (int traceLine = 0; traceLine < stackTrace.length; ++traceLine) {
                errorMsg = errorMsg + "<br>" + stackTrace[traceLine];
            }
            return null;
        }
    }
}
