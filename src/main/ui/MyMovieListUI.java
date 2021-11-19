package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// MyMovieList Application GUI
public class MyMovieListUI extends JFrame implements ActionListener, ListSelectionListener {
    //Main Panel
    private JPanel mainPanel;
    // MenuBar
    private JMenuBar menuBar;
    private JMenu loadMenu;
    private JMenu saveMenu;
    private JMenuItem loadWatchlist;
    private JMenuItem loadReviews;
    private JMenuItem saveWatchlist;
    private JMenuItem saveReviews;
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

    /* EFFECTS:  creates the frame that will contain the application
                 adds all necessary panels, panes, buttons to the frame
                 watchlist and reviews are set to empty MovieLists
     */
    public MyMovieListUI() {
        super("My Movie List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(720, 480));
        setResizable(false);
        addMenuBar();
        addWatchlistPanel();
        addReviewsPanel();
        addMainPanel();
        setVisible(true);
        watchlist = new MovieList("Watchlist");
        reviews = new MovieList("Reviews");
    }

    /* MODIFIES: this
       EFFECTS:  adds a menu bar with load and save options
     */
    private void addMenuBar() {
        menuBar = new JMenuBar();
        loadMenu = new JMenu("Load");
        menuBar.add(loadMenu);
        loadWatchlist = new JMenuItem("Load Watchlist");
        loadWatchlist.addActionListener(this);
        loadMenu.add(loadWatchlist);
        loadReviews = new JMenuItem("Load Reviews");
        loadReviews.addActionListener(this);
        loadMenu.add(loadReviews);
        saveMenu = new JMenu("Save");
        menuBar.add(saveMenu);
        saveWatchlist = new JMenuItem("Save Watchlist");
        saveWatchlist.addActionListener(this);
        saveMenu.add(saveWatchlist);
        saveReviews = new JMenuItem("Save Reviews");
        saveReviews.addActionListener(this);
        saveMenu.add(saveReviews);
        setJMenuBar(menuBar);
    }

    /* MODIFIES: this
       EFFECTS:  adds a main panel that will hold all the sub panels
     */
    private void addMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(watchlistPanel);
        mainPanel.add(reviewsPanel);
        add(mainPanel);
    }

    /* MODIFIES: this
       EFFECTS:  adds buttons to be used in the watchlist panel
     */
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

    /* MODIFIES: this
       EFFECTS:  adds a split pane to be used in the watchlist panel
     */
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

    /* MODIFIES: this
       EFFECTS:  consolidates all the previously created components in the watchlist panel
     */
    private void addWatchlistPanel() {
        watchlistPanel = new JPanel();
        watchlistPanel.setLayout(new BorderLayout());
        watchlistModel = new DefaultListModel();
        watchlistButtons = new JPanel();
        watchlistButtons.setLayout(new GridLayout(3, 1,0,0));
        watchlistButtons.setPreferredSize(new Dimension(170, 240));
        watchlistJList = new JList(watchlistModel);
        watchlistJList.setFont(new Font("Lucida Console", Font.PLAIN, 14));
        watchlistJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        watchlistJList.setMinimumSize(new Dimension(200, 100));
        watchlistJList.addListSelectionListener(this);
        watchlistDetails = new JTextArea();
        watchlistDetails.setFont(new Font("Lucida Console", Font.PLAIN, 14));
        watchlistSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, watchlistJList, watchlistDetails);
        watchlistSplitPane.setEnabled(false);
        addWatchlistButtons();
        addWatchlistSplitPane();
    }

    /* MODIFIES: this
       EFFECTS:  adds buttons to be used in the watchlist panel
                 adds a logo of the application in the bottom right
     */
    private void addReviewsButtonsAndLogo() {
        updateReviewButton = new JButton("Update Review");
        updateReviewButton.addActionListener(this);
        reviewsButtons.add(updateReviewButton);
        ImageIcon imageTop = new ImageIcon("./data/logoTop.jpg");
        JLabel logoTop = new JLabel();
        logoTop.setIcon(imageTop);
        logoTop.setHorizontalAlignment(JLabel.CENTER);
        reviewsButtons.add(logoTop);
        ImageIcon imageBottom = new ImageIcon("./data/logoBottom.jpg");
        JLabel logoBottom = new JLabel();
        logoBottom.setIcon(imageBottom);
        logoBottom.setHorizontalAlignment(JLabel.CENTER);
        reviewsButtons.add(logoBottom);
    }

    /* MODIFIES: this
       EFFECTS:  adds a split pane to be used in the reviews panel
     */
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

    /* MODIFIES: this
       EFFECTS:  consolidates all the previously created components in the reviews panel
     */
    public void addReviewsPanel() {
        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BorderLayout());
        reviewsModel = new DefaultListModel();
        reviewsButtons = new JPanel();
        reviewsButtons.setLayout(new GridLayout(3,1,0,0));
        reviewsButtons.setPreferredSize(new Dimension(170, 240));
        reviewsJList = new JList(reviewsModel);
        reviewsJList.setFont(new Font("Lucida Console", Font.PLAIN, 14));
        reviewsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reviewsJList.setMinimumSize(new Dimension(200, 100));
        reviewsJList.addListSelectionListener(this);
        reviewsDetails = new JTextArea();
        reviewsDetails.setFont(new Font("Lucida Console", Font.PLAIN, 14));
        reviewsSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, reviewsJList, reviewsDetails);
        reviewsSplitPane.setEnabled(false);
        addReviewsButtonsAndLogo();
        addReviewsSplitPane();
    }

    /* MODIFIES: this
       EFFECTS:  adds a new movie to watchlist given user inputs for the movie title and genre
     */
    private void addToWatchlist() {
        String title = JOptionPane.showInputDialog("Enter the movie title:");
        String genre = JOptionPane.showInputDialog("Enter the movie genre:");
        Movie newMovie = new Movie(title, genre);
        watchlist.addMovie(newMovie);
        watchlistModel.addElement(title);
        watchlistJList.setSelectedIndex(watchlistJList.getLastVisibleIndex());
    }

    /* MODIFIES: this
       EFFECTS:  transfers a specific movie from watchlist to reviews given user input for the movie title
                 rating and comment are updated using user inputs
     */
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

    /* MODIFIES: this
       EFFECTS:  removes a movie from watchlist given user input for the movie title
     */
    private void removeFromWatchlist() {
        if (watchlistJList.getSelectedIndex() != -1) {
            String title = watchlistModel.remove(watchlistJList.getSelectedIndex()).toString();
            watchlist.removeMovie(title);
        }
    }

    /* MODIFIES: this
       EFFECTS:  updates a specific movie given user input for the movie title
                 rating and comment are updated using user inputs
     */
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

    // EFFECTS:  performs a certain action depending on the button that is pressed
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
        } else if (e.getSource() == loadWatchlist) {
            loadWatchlist();
        } else if (e.getSource() == loadReviews) {
            loadReviews();
        } else if (e.getSource() == saveWatchlist) {
            saveWatchlist();
        } else if (e.getSource() == saveReviews) {
            saveReviews();
        }
    }

    /* MODIFIES: this
       EFFECTS:  updates the details box whenever a new movie is selected
     */
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

    /* MODIFIES: this
       EFFECTS:  populates the watchlist using a JSON file that the user selected
     */
    private void loadWatchlist() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            JsonReader reader = new JsonReader(fileChooser.getSelectedFile().getAbsolutePath());
            watchlist = reader.parseMovieList();
            watchlistModel.removeAllElements();
            for (Movie movie : watchlist.getMovies()) {
                watchlistModel.addElement(movie.getTitle());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to load movies. File was not found.");
        }
    }

    /* MODIFIES: this
       EFFECTS:  populates reviews using a JSON file that the user selected
     */
    private void loadReviews() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            JsonReader reader = new JsonReader(fileChooser.getSelectedFile().getAbsolutePath());
            reviews = reader.parseMovieList();
            reviewsModel.removeAllElements();
            for (Movie movie : reviews.getMovies()) {
                reviewsModel.addElement(movie.getTitle());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to load movies. File was not found.");
        }
    }

    /* MODIFIES: this
       EFFECTS:  saves the watchlist to a JSON file that the user has selected
     */
    private void saveWatchlist() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);
            JsonWriter writer = new JsonWriter(fileChooser.getSelectedFile().getAbsolutePath());
            writer.saveMovieList(watchlist);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to save movies. File was not found.");
        }
    }

    /* MODIFIES: this
       EFFECTS:  saves reviews to a JSON file that the user has selected
     */
    private void saveReviews() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);
            JsonWriter writer = new JsonWriter(fileChooser.getSelectedFile().getAbsolutePath());
            writer.saveMovieList(reviews);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to save movies. File was not found.");
        }
    }
}
