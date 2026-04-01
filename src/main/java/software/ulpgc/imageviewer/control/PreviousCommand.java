package software.ulpgc.imageviewer.control;

public class PreviousCommand implements Command {
    private final ImagePresenter imagePresenter;

    public PreviousCommand(ImagePresenter imagePresenter) {
        this.imagePresenter = imagePresenter;
    }

    @Override
    public void execute() {
        imagePresenter.show(imagePresenter.image().previous());
    }
}