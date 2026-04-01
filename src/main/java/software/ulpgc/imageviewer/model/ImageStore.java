package software.ulpgc.imageviewer.model;

import java.util.stream.Stream;

public interface ImageStore {
    Stream<String> images();
}