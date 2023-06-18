package com.example.algorithm.fishmotiondetector;

/**
 * @author yichuan
 * @version 1.0
 * @description: TODO
 * @date 2023/6/18 18:10
 */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *判断鱼儿的状态（运动还是静止）可以使用计算机视觉中的运动检测算法来实现。下面是一个简单的基于帧差法的运动检测算法，使用Java实现
 *在这个实现中，我们使用帧差法来检测运动。具体来说，我们将连续两帧图像转换为灰度图像，并计算它们的绝对差异。然后，我们通过二值化阈值将差异图像转换为二进制运动掩码，
 * 并计算非零像素的百分比。如果非零像素的百分比超过某个阈值，则认为鱼在运动，否则认为鱼是静止的。
 * 请注意，这个实现是一个简单的示例，可能需要根据具体问题进行修改和优化。例如，您可能需要调整阈值来适应不同的场景，
 * 或者使用更高级的运动检测算法来提高准确性。
 */
public class FishMotionDetector {

    private BufferedImage prevFrame;
    private boolean fishMoving;

    public FishMotionDetector() {
        prevFrame = null;
        fishMoving = false;
    }

    public boolean isFishMoving(BufferedImage currentFrame) {
        // Convert the current frame to grayscale
        BufferedImage grayFrame = toGrayscale(currentFrame);

        // If this is the first frame, skip motion detection
        if (prevFrame == null) {
            prevFrame = grayFrame;
            return false;
        }

        // Compute the absolute difference between the current and previous frames
        BufferedImage diffFrame = diff(prevFrame, grayFrame);

        // Threshold the difference image to create a binary motion mask
        BufferedImage mask = threshold(diffFrame, 30);

        // Compute the percentage of non-zero pixels in the motion mask
        double motionPercent = percentNonZero(mask);

        // Update the fishMoving flag based on the motion percentage
        if (motionPercent > 2.0) {
            fishMoving = true;
        } else {
            fishMoving = false;
        }

        // Update the previous frame
        prevFrame = grayFrame;

        // Return the fishMoving flag
        return fishMoving;
    }

    private BufferedImage toGrayscale(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int gray = (int) (0.299 * ((rgb >> 16) & 0xFF) + 0.587 * ((rgb >> 8) & 0xFF) + 0.114 * (rgb & 0xFF));
                grayImage.setRGB(x, y, new Color(gray, gray, gray).getRGB());
            }
        }
        return grayImage;
    }

    private BufferedImage diff(BufferedImage prev, BufferedImage current) {
        int width = prev.getWidth();
        int height = prev.getHeight();
        BufferedImage diffImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int prevGray = new Color(prev.getRGB(x, y)).getRed();
                int currentGray = new Color(current.getRGB(x, y)).getRed();
                int diff = Math.abs(currentGray - prevGray);
                diffImage.setRGB(x, y, new Color(diff, diff, diff).getRGB());
            }
        }
        return diffImage;
    }

    private BufferedImage threshold(BufferedImage image, int threshold) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int gray = new Color(image.getRGB(x, y)).getRed();
                if (gray > threshold) {
                    binaryImage.setRGB(x, y, Color.WHITE.getRGB());
                } else {
                    binaryImage.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        return binaryImage;
    }

    private double percentNonZero(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int totalPixels = width * height;
        int nonZeroPixels = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int gray = new Color(image.getRGB(x, y)).getRed();
                if (gray > 0) {
                    nonZeroPixels++;
                }
            }
        }
        return (double) nonZeroPixels / totalPixels * 100.0;
    }

    public static void main(String[] args) {
        FishMotionDetector detector = new FishMotionDetector();
        try {
            BufferedImage frame1 = ImageIO.read(new File("frame1.png"));
            BufferedImage frame2 = ImageIO.read(new File("frame2.png"));
            boolean fishMoving1 = detector.isFishMoving(frame1);
            boolean fishMoving2 = detector.isFishMoving(frame2);
            System.out.println("Fish moving in frame 1: " + fishMoving1);
            System.out.println("Fish moving in frame 2: " + fishMoving2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
