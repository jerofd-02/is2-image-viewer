package software.ulpgc.imageviewer.application;

import software.ulpgc.imageviewer.io.ImageDeserializer;
import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ImageDisplay;
import software.ulpgc.imageviewer.view.ViewPort;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private final ImageDeserializer deserializer;
    private Image image;
    private int offset;

    public SwingImageDisplay(ImageDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    @Override
    public void show(Image image, int offset) {
        this.image = image;
        this.offset = offset;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawImage(g);
    }

    public void drawImage(Graphics g) {
        java.awt.Image image = get(this.image);
        ViewPort viewport = new ViewPort(0, 0, getWidth(), getHeight()).fit(image.getWidth(null), image.getHeight(null));
        g.drawImage(
                image,
                viewport.x() + offset,
                viewport.y(),
                viewport.width(),
                viewport.height(),
                null
        );

        g.drawImage(
                get(this.image.previous()),
                viewport.x() + offset - getWidth(),
                viewport.y(),
                viewport.width(),
                viewport.height(),
                null
        );

        g.drawImage(
                get(this.image.next()),
                viewport.x() + offset + getWidth(),
                viewport.y(),
                viewport.width(),
                viewport.height(),
                null
        );
    }


    private final Map<Integer, java.awt.Image> images = new HashMap<>();
    private java.awt.Image get(Image image) {
        return images.computeIfAbsent(hashOf(image.content()), h -> desearilize(image.content()));
    }

    private static int hashOf(byte[] image) {
        return Arrays.hashCode(image);
    }

    private java.awt.Image desearilize(byte[] order) {
        return deserializer.desearilize(order);
    }
}
