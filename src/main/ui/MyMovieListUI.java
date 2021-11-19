package ui;

import javax.swing.*;
import java.awt.*;

public class MyMovieListUI extends JFrame {

    private JPanel watchlistPanel;
    private JPanel reviewsPanel;
    private JPanel movieListPanel;

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
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem saveItem = new JMenuItem("Save");
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

    public static void main(String[] args) {
        new MyMovieListUI();
    }
}
