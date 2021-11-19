package ui;

import model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMovieListUI extends JFrame implements ActionListener, ListSelectionListener {

    private JPanel mainPanel;

    // MenuBar
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadItem;
    private JMenuItem saveItem;

    // MovieLists
    private MovieList watchlist;
    private MovieList reviews;

    // Watchlist Panel
    private JPanel watchlistPanel;
    private JSplitPane watchlistSplitPane;
    private JPanel watchlistButtons;
    private JList watchlistJList;
    private DefaultListModel watchlistModel;
    private JTextArea watchlistDetails;
    private JButton addMovieButton;
    private JButton addReviewButton;
    private JButton removeMovieButton;

    // Reviews Panel
    private JPanel reviewsPanel;
    private JSplitPane reviewsSplitPane;
    private JPanel reviewsButtons;
    private JList reviewsJList;
    private DefaultListModel reviewsModel;
    private JTextArea reviewsDetails;
    private JButton updateReviewButton;

    public MyMovieListUI() {
        super("My Movie List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(720, 480));
        setResizable(false);
        setLayout(new GridLayout(1, 2));
        addMenuBar();
        addWatchlistPanel();
        addReviewsPanel();
        addMainPanel();
        setVisible(true);
        watchlist = new MovieList("Watchlist");
        reviews = new MovieList("Reviews");
    }

    private void addMenuBar() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        loadItem = new JMenuItem("Load");
        loadItem.addActionListener(this);
        saveItem = new JMenuItem("Save");
        saveItem.addActionListener(this);
        menuBar.add(fileMenu);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        setJMenuBar(menuBar);
    }

    private void addMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(watchlistPanel);
        mainPanel.add(reviewsPanel);
        add(mainPanel);
    }

    private void addWatchlistButtons() {
        addMovieButton = new JButton("Add a Movie");
        addMovieButton.addActionListener(this);
        watchlistButtons.add(addMovieButton);
        addReviewButton = new JButton("Write a Review");
        addReviewButton.addActionListener(this);
        watchlistButtons.add(addReviewButton);
        removeMovieButton = new JButton("Remove Movie");
        removeMovieButton.addActionListener(this);
        watchlistButtons.add(removeMovieButton);
    }

    private void addWatchlistSplitPane() {
        JLabel panelTitle = new JLabel("Watchlist");
        panelTitle.setHorizontalAlignment(JLabel.CENTER);
        panelTitle.setVerticalTextPosition(JLabel.BOTTOM);
        panelTitle.setOpaque(true);
        panelTitle.setForeground(Color.WHITE);
        panelTitle.setBackground(new Color(0x000080));
        watchlistPanel.add(panelTitle, BorderLayout.NORTH);
        watchlistPanel.add(watchlistSplitPane, BorderLayout.CENTER);
        watchlistPanel.add(watchlistButtons, BorderLayout.EAST);
    }

    private void addWatchlistPanel() {
        watchlistPanel = new JPanel();
        watchlistPanel.setLayout(new BorderLayout());
        watchlistModel = new DefaultListModel();
        watchlistButtons = new JPanel();
        watchlistButtons.setLayout(new GridLayout(3, 1));
        watchlistButtons.setPreferredSize(new Dimension(170, 240));
        watchlistJList = new JList(watchlistModel);
        watchlistJList.setFont(new Font("Lucida Console", Font.PLAIN, 12));
        watchlistJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        watchlistJList.setMinimumSize(new Dimension(200, 100));
        watchlistJList.addListSelectionListener(this);
        watchlistDetails = new JTextArea();
        watchlistDetails.setFont(new Font("Lucida Console", Font.PLAIN, 12));
        watchlistSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, watchlistJList, watchlistDetails);
        watchlistSplitPane.setEnabled(false);
        addWatchlistButtons();
        addWatchlistSplitPane();
    }

    private void addReviewsButtons() {
        updateReviewButton = new JButton("Update Review");
        updateReviewButton.addActionListener(this);
        reviewsButtons.add(updateReviewButton);
    }

    public void addReviewsSplitPane() {
        JLabel panelTitle = new JLabel("Reviews");
        panelTitle.setHorizontalAlignment(JLabel.CENTER);
        panelTitle.setVerticalTextPosition(JLabel.BOTTOM);
        panelTitle.setOpaque(true);
        panelTitle.setForeground(Color.WHITE);
        panelTitle.setBackground(new Color(0x000080));
        reviewsPanel.add(panelTitle, BorderLayout.NORTH);
        reviewsPanel.add(reviewsSplitPane, BorderLayout.CENTER);
        reviewsPanel.add(reviewsButtons, BorderLayout.EAST);
    }

    public void addReviewsPanel() {
        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BorderLayout());
        reviewsModel = new DefaultListModel();
        reviewsButtons = new JPanel();
        reviewsButtons.setLayout(new GridLayout(3, 1));
        reviewsButtons.setPreferredSize(new Dimension(170, 240));
        reviewsJList = new JList(reviewsModel);
        reviewsJList.setFont(new Font("Lucida Console", Font.PLAIN, 12));
        reviewsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reviewsJList.setMinimumSize(new Dimension(200, 100));
        reviewsJList.addListSelectionListener(this);
        reviewsDetails = new JTextArea();
        reviewsDetails.setFont(new Font("Lucida Console", Font.PLAIN, 12));
        reviewsSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, reviewsJList, reviewsDetails);
        reviewsSplitPane.setEnabled(false);
        addReviewsButtons();
        addReviewsSplitPane();
    }

    private void addToWatchlist() {
        String title = JOptionPane.showInputDialog("Enter the movie title:");
        String genre = JOptionPane.showInputDialog("Enter the movie genre:");
        Movie newMovie = new Movie(title, genre);
        watchlist.addMovie(newMovie);
        watchlistModel.addElement(title);
        watchlistJList.setSelectedIndex(watchlistJList.getLastVisibleIndex());
    }

    private void addReview() {
        if (watchlistJList.getSelectedIndex() != -1) {
            String title = watchlistModel.remove(watchlistJList.getSelectedIndex()).toString();
            int rating = Integer.parseInt(JOptionPane.showInputDialog("Please enter a rating "
                    + "on a scale of one to five stars:"));
            while (rating < 1 || rating > 5) {
                rating = Integer.parseInt(JOptionPane.showInputDialog("Invalid rating. Please enter another rating."));
            }
            String comment = JOptionPane.showInputDialog("Please enter a comment for your review:");
            Movie newReview = watchlist.removeMovie(title);
            reviews.addMovie(newReview);
            reviews.updateReview(title, rating, comment);
            reviewsModel.addElement(title);
            reviewsDetails.setText(reviews.getReview(title));
            reviewsJList.setSelectedIndex(reviewsJList.getLastVisibleIndex());
        }
    }

    private void removeFromWatchlist() {
        if (watchlistJList.getSelectedIndex() != -1) {
            String title = watchlistModel.remove(watchlistJList.getSelectedIndex()).toString();
            watchlist.removeMovie(title);
        }
    }

    public void updateReview() {
        String title = reviewsJList.getSelectedValue().toString();
        int rating = Integer.parseInt(JOptionPane.showInputDialog("Please enter a new rating "
                + "on a scale of one to five stars:"));
        while (rating < 1 || rating > 5) {
            rating = Integer.parseInt(JOptionPane.showInputDialog("Invalid rating. Please enter another rating."));
        }
        String comment = JOptionPane.showInputDialog("Please enter a new comment for your review:");
        reviews.updateReview(title, rating, comment);
        reviewsDetails.setText(reviews.getReview(title));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMovieButton) {
            addToWatchlist();
        } else if (e.getSource() == addReviewButton) {
            addReview();
        } else if (e.getSource() == removeMovieButton) {
            removeFromWatchlist();
        } else if (e.getSource() == updateReviewButton) {
            updateReview();
        } else if (e.getSource() == loadItem) {
            //
        } else if (e.getSource() == saveItem) {
            //
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == watchlistJList) {
            watchlistDetails.setText("");
            if (watchlistJList.getSelectedIndex() != -1) {
                watchlistDetails.setText(watchlist.getTitleAndGenre(watchlistJList.getSelectedValue().toString()));
            }
        } else if (e.getSource() == reviewsJList) {
            reviewsDetails.setText("");
            if (reviewsJList.getSelectedIndex() != -1) {
                reviewsDetails.setText(reviews.getReview(reviewsJList.getSelectedValue().toString()));
            }
        }
    }

    public static void main(String[] args) {
        new MyMovieListUI();
    }
}
