package software.ulpgc.imageviewer.model;

public interface Image {
    String id();

    byte[] bitmap();

    Image next();

    Image previous();
}