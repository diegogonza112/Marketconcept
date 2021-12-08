package options.post;

import gui.ButtonCommandInterface;
import gui.GUIFactory;
import gui.GUIFactoryInterface;
import options.OptionsGUIMaker;
import options.commands.CartCommand;
import options.commands.OptionsCommand;
import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SuccessfulPostCreationGUIMaker implements ActionListener, GUIFactoryInterface {
    User user;
    static Map<String, ButtonCommandInterface> commandMap = new HashMap<>();
    JFrame frame = new JFrame();

    public SuccessfulPostCreationGUIMaker(User user) {
        this.user = user;
    }

    public void actionPerformed(ActionEvent action) {
        frame.dispose();
        String buttonText = action.getActionCommand();
        ButtonCommandInterface button = commandMap.get(buttonText);
        try {
            button.apply();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createGUI() throws IOException, ClassNotFoundException {
        EnglishPostPresenter presenter = new EnglishPostPresenter();

        JButton back = new JButton(presenter.backToMenu());
        JLabel message = new JLabel(presenter.congrats());

        back.setBounds(125, 100, 200, 25);
        back.addActionListener(this);

        message.setBounds(125, 50, 300, 25);
        message.setForeground(Color.green);
        message.setFont(new Font(null, Font.PLAIN, 15));

        frame.add(back);
        frame.add(message);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 200);
        frame.setLayout(null);
        frame.setVisible(true);

        commandMap.put(back.getText(), new OptionsCommand(frame, this.user));
    }
}
