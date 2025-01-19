package software.ulpgc.imageviewer.application;

import software.ulpgc.imageviewer.control.ImagePresenter;
import software.ulpgc.imageviewer.control.NextCommand;
import software.ulpgc.imageviewer.control.PreviousCommand;
import software.ulpgc.imageviewer.io.FileImageLoader;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.create();
        FileImageLoader loader = new FileImageLoader(new File("images/"));
        ImagePresenter presenter = new ImagePresenter(mainFrame.imageDisplay(), loader.load());
        mainFrame
                .add("next", new NextCommand(presenter))
                .add("previous", new PreviousCommand(presenter))
                .setListeners(presenter.getDraggingListeners(), presenter.getReleaseListeners())
                .setVisible(true);
    }
}
