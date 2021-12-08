package options.follow_users;

import gui.ButtonCommandInterface;
import gui.GUIFactoryInterface;
import gui.GeneralGUIMakerInterface;
import login.sign_in.ResetCommand;
import options.OptionsGUIMaker;
import options.commands.BuyCommand;
import options.commands.FollowCommand;
import options.commands.OptionsCommand;
import options.commands.SearchCommand;
import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FollowGUIMaker implements ActionListener, GUIFactoryInterface, GeneralGUIMakerInterface {

    public static JTextField searchBar = new JTextField();
    User user;
    static Map<String, ButtonCommandInterface> commandMap = new HashMap<>();
    JFrame frame = new JFrame();
    public JLabel messageLabel = new JLabel();

    public FollowGUIMaker(User user) {
        this.user = user;
    }

    /**
     * The action listener that sees what the user is doing and determines the results from this action.
     * @param action the action of the user
     */
    @Override
    public void actionPerformed(ActionEvent action) {
        String buttonText = action.getActionCommand();
        ButtonCommandInterface button = commandMap.get(buttonText);
        try {
            button.apply();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createGUI() {
        FollowPresenter followPresenter = new FollowPresenter();
        JButton followButtonLabel = new JButton(followPresenter.followButton());

        JButton clear = new JButton(followPresenter.clearButton());
        JButton back = new JButton(followPresenter.backButton());
        JLabel followLabel = new JLabel(followPresenter.followInstructions());

        followLabel.setBounds(125, 25, 250, 35);
        followLabel.setFont(new Font("Serif", Font.PLAIN, 14));

        // search bar + search button
        followButtonLabel.setBounds(50, 50, 200, 35);
        searchBar.setBounds(125, 50, 200, 35);
        followButtonLabel.setBounds(125, 100, 200, 35);
        followButtonLabel.setFont(new Font(null, Font.PLAIN, 15));
        followButtonLabel.addActionListener(this);

        // button to clear the search bar
        clear.setBounds(125, 150, 200, 35);
        clear.setFont(new Font(null, Font.PLAIN, 15));
        clear.addActionListener(this);

        // go back button
        back.setBounds(125, 300, 100, 35);
        back.setFont(new Font(null, Font.PLAIN, 15));
        back.addActionListener(this);
        //Label after clicking follow button

        messageLabel.setBounds(125, 125, 200, 130);
        frame.add(followButtonLabel);
        frame.add(followLabel);
        frame.add(searchBar);
        frame.add(clear);
        frame.add(back);
        frame.add(messageLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        commandMap.put(back.getText(), new OptionsCommand(frame, this.user));
        commandMap.put(clear.getText(), new ResetCommand(this));
        commandMap.put(followButtonLabel.getText(), new FollowCommand(this.user, this));

    }

    @Override
    public void disposeFrame() {
        frame.dispose();
    }

    @Override
    public void resetFields() {
        searchBar.setText("");
    }
}
