package software.ulpgc.imageviewer.io;

public interface ImageDeserializer {
    java.awt.Image desearilize(byte[] bytes);
}
