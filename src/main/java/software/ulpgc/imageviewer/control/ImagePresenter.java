package software.ulpgc.imageviewer.control;

import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ImageDisplay;

public class ImagePresenter {
    private final ImageDisplay display;
    private Image image;
    private boolean dragging;
    private int initialOffset;

    public ImagePresenter(ImageDisplay display, Image initialImage) {
        this.display = display;
        show(initialImage, 0);
    }

    private void show(Image image, int offset) {
        this.image = image;
        display.show(image, offset);
    }

    public void next() {
        show(image.next(), 0);
    }

    public void previous() {
        show(image.previous(), 0);
    }

    public ImageDisplay.Dragging getDraggingListeners() {
        return new ImageDisplay.Dragging() {
            @Override
            public void on(int offset) {
                setDragging(offset);
                show(image, offset - initialOffset);
            }
        };
    }

    public ImageDisplay.Release getReleaseListeners() {
        return new ImageDisplay.Release() {
            @Override
            public void on(int offset, int width) {
                unsetDragging();
                if (offset > width / 2)  {
                    previous();
                    return;
                } else if (offset < width / 2) {
                    next();
                    return;
                }
            }
        };
    }

    private void setDragging(int offset) {
        if (dragging) return;
        dragging = true;
        initialOffset = offset;
    }

    private void unsetDragging() {
        dragging = false;
        initialOffset = 0;
    }

}