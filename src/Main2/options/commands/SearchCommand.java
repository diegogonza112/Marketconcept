package options.commands;

import gui.ButtonCommandInterface;
import gui.GUIFactory;
import gui.GUIFactoryInterface;
import user.User;

import javax.swing.*;
import java.io.IOException;

public class SearchCommand implements ButtonCommandInterface {
    JFrame frame;
    User user;

    public SearchCommand(JFrame frame, User user) {
        this.frame = frame;
        this.user = user;
    }

    @Override
    public void apply() throws IOException, ClassNotFoundException {
        frame.dispose();
        GUIFactory guiFactory = new GUIFactory(this.user);
        GUIFactoryInterface guiFrame = guiFactory.getFrame("SEARCH");
        guiFrame.createGUI();
    }
}
