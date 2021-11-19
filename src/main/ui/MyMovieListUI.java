package ui;

import javax.swing.*;
import java.awt.*;

public class MyMovieListUI extends JFrame {

    private JPanel watchlistPanel;
    private JPanel reviewsButtonPanel;
    private JPanel reviewsPanel;
    private JPanel movieListPanel;

    public MyMovieListUI() {
        super("My Movie List");
        watchlistPanel = new WatchlistUI();
        reviewsButtonPanel = new JPanel();
        reviewsButtonPanel.setLayout(new GridLayout(3, 1));
        reviewsButtonPanel.setPreferredSize(new Dimension(170, 240));
        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BorderLayout());
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

    private void addReviewsButtons() {
        JButton updateReviewButton = new JButton("Update Review");
        JButton removeReviewButton = new JButton("Remove Review");
        reviewsButtonPanel.add(updateReviewButton);
        reviewsButtonPanel.add(removeReviewButton);
    }

    private void addReviewsPanel() {
        JLabel reviewsTitle = new JLabel("Reviews");
        reviewsTitle.setHorizontalAlignment(JLabel.CENTER);
        reviewsTitle.setVerticalTextPosition(JLabel.BOTTOM);
        reviewsTitle.setOpaque(true);
        reviewsTitle.setForeground(Color.WHITE);
        reviewsTitle.setBackground(new Color(0x000080));
        String[] movies = {"Movie 1", "Movie 2", "Movie 3", "Movie 4"};
        JList reviews = new JList(movies);
        reviews.setFont(new Font("Lucida Console", Font.PLAIN, 12));
        reviews.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reviews.setMinimumSize(new Dimension(200, 100));
        JTextArea reviewsDetails = new JTextArea("Title: Movie 1\nGenre: Action\nRating: 5/5\nComment: Great movie.");
        reviewsDetails.setFont(new Font("Lucida Console", Font.PLAIN, 12));
        JSplitPane reviewsSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, reviews, reviewsDetails);
        reviewsSplitPane.setEnabled(false);
        reviewsPanel.add(reviewsTitle, BorderLayout.NORTH);
        reviewsPanel.add(reviewsSplitPane, BorderLayout.CENTER);
        reviewsPanel.add(reviewsButtonPanel, BorderLayout.EAST);
    }

    private void setUpFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(720, 480));
        setResizable(false);
        setLayout(new GridLayout(1, 2));
        addReviewsButtons();
        addReviewsPanel();
        add(movieListPanel);
        addMenuBar();
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyMovieListUI();
    }
}
