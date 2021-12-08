package options.search;


import options.buy.BuyController;
import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ScrollSearchGUI implements ActionListener {

    SearchPresenterInterface searchPresenter = new SearchPresenter();
    JLabel searchIntro = new JLabel(searchPresenter.inputIndex());
    JFrame frame = new JFrame();
    JButton back = new JButton(searchPresenter.backButton());
    JButton buy = new JButton(searchPresenter.buyButton());
    JTextField searchBar = new JTextField(10);
    JPanel scrollPanel = new JPanel();
    JPanel indexPanel = new JPanel();
    JPanel titlePanel = new JPanel();
    JPanel backPanel = new JPanel();
    JLabel messageLabel = new JLabel();


    User user;
    String tag;
    SearchController searchController = new SearchController(user);
    ArrayList<String> searchList;


    public ScrollSearchGUI(User user, String tag) throws IOException, ClassNotFoundException {
        this.user = user;
        this.tag = tag;

        this.searchList = searchController.getSearchProductStrings(tag);
        messageLabel.setBounds(125, 125, 130, 130);


        searchIntro.setBounds(125, 20, 100, 35);
        searchIntro.setFont(new Font(null, Font.PLAIN, 13));
        titlePanel.add(searchIntro);

        titlePanel.setBounds(1, 1, 100, 35);

        scrollPanel.setBounds(125, 100, 100, 100);
        indexPanel.setBounds(125, 300, 100, 100);

        backPanel.add(back);
        backPanel.setBounds(130, 600, 100, 100);


        DefaultListModel list = new DefaultListModel();
        for (String item : searchList) {
            list.addElement(item);
        }
        JList listOfProductDisplay = new JList(list);
        JScrollPane listScroller = new JScrollPane(listOfProductDisplay);

        // back button
        back.setBounds(130, 600, 50, 35);
        back.addActionListener(this);
        listScroller.setBounds(100, 100, 100, 100);
        scrollPanel.add(listScroller);


        //buy button
        buy.setBounds(160, 300, 50, 35);
        buy.addActionListener(this);


        // search bar + search button
        // search bar + search button

        searchBar.setBounds(100, 350, 100, 35);


        searchBar.setFont(new Font(null, Font.PLAIN, 15));
        searchBar.addActionListener(this);

        indexPanel.add(searchBar);
        indexPanel.add(buy);
        indexPanel.add(back);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(listScroller);
        frame.add(titlePanel);
        frame.add(messageLabel);


        listScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(backPanel);
        frame.add(indexPanel);
        frame.add(scrollPanel);


    }

    /**
     * The action listener that sees what the user is doing and determines the results from this action.
     *
     * @param action the action of the user
     */
    @Override
    public void actionPerformed(ActionEvent action) {
        if (action.getSource() == back) {
            frame.dispose();
            SearchGUI searchGUI = new SearchGUI(user);
        } else if (action.getSource() == buy) {
            String index = searchBar.getText();
            int indexInt = Integer.parseInt(index);

            BuyController buyController = new BuyController();
            boolean allowedBuy = false;
            try {
                ArrayList<String> productIds = searchController.getProductIDStrings(this.tag);
                allowedBuy = buyController.allowBuy(user, productIds, indexInt);
            } catch (IOException | ClassNotFoundException e) {
                // TODO: make exception message more specific
                e.printStackTrace();
            }
            if (allowedBuy) {
                messageLabel.setForeground(Color.green);
                messageLabel.setText(searchPresenter.canBuy());
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText(searchPresenter.cannotBuy());



            }


        }

    }
}