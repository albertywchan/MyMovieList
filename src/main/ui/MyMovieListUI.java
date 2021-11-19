package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMovieListUI extends JFrame implements ActionListener {

    private JPanel watchlistPanel;
    private JPanel reviewsPanel;
    private JPanel movieListPanel;

    //Buttons
    private JPanel watchlistButtons;

    //JSON
    private JsonReader reader;
    private JsonWriter writer;

    // MenuBar
    private JMenuItem loadItem;
    private JMenuItem saveItem;

    public MyMovieListUI() {
        super("My Movie List");
        watchlistPanel = new WatchlistUI();
        reviewsPanel = new ReviewsUI();
        movieListPanel = new JPanel();
        movieListPanel.setLayout(new GridLayout(2, 1));
        movieListPanel.add(watchlistPanel);
        movieListPanel.add(reviewsPanel);
        setUpFrame();
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        loadItem = new JMenuItem("Load");
        loadItem.addActionListener(this);
        saveItem = new JMenuItem("Save");
        saveItem.addActionListener(this);
        menuBar.add(fileMenu);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        setJMenuBar(menuBar);
    }

    private void setUpFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(720, 480));
        setResizable(false);
        setLayout(new GridLayout(1, 2));
        add(movieListPanel);
        addMenuBar();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItem) {
            //
        } else if (e.getSource() == saveItem) {
            //
        }
    }

    public static void main(String[] args) {
        new MyMovieListUI();
    }


}
