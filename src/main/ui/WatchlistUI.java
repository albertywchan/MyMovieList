package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatchlistUI extends JPanel implements ActionListener {

    private MovieList watchlist;
    private DefaultListModel model;
    private JList list;
    private JTextArea details;
    private JSplitPane splitPane;
    private JPanel buttons;

    public WatchlistUI() {
        watchlist = new MovieList("Watchlist");
        model = new DefaultListModel();
        buttons = new JPanel();
        buttons.setLayout(new GridLayout(3, 1));
        buttons.setPreferredSize(new Dimension(170, 240));
        list = new JList(model);
        list.setFont(new Font("Lucida Console", Font.PLAIN, 12));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setMinimumSize(new Dimension(200, 100));
        details = new JTextArea("Title: Movie 1\nGenre: Action\nRating: n/a\nComment: n/a");
        details.setFont(new Font("Lucida Console", Font.PLAIN, 12));
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, list, details);
        splitPane.setEnabled(false);
        setLayout(new BorderLayout());
        addWatchlistButtons();
        addWatchlistPanel();
    }

    private void addWatchlistButtons() {
        JButton addMovieButton = new JButton("Add a Movie");
        addMovieButton.setActionCommand("add");
        addMovieButton.addActionListener(this);
        buttons.add(addMovieButton);
        JButton reviewMovieButton = new JButton("Write a Review");
        reviewMovieButton.setActionCommand("review");
        buttons.add(reviewMovieButton);
        JButton removeMovieButton = new JButton("Remove Movie");
        removeMovieButton.setActionCommand("remove");
        removeMovieButton.addActionListener(this);
        buttons.add(removeMovieButton);
    }

    private void addWatchlistPanel() {
        JLabel panelTitle = new JLabel("Watchlist");
        panelTitle.setHorizontalAlignment(JLabel.CENTER);
        panelTitle.setVerticalTextPosition(JLabel.BOTTOM);
        panelTitle.setOpaque(true);
        panelTitle.setForeground(Color.WHITE);
        panelTitle.setBackground(new Color(0x000080));
        add(panelTitle, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(buttons, BorderLayout.EAST);
    }

    public void addToWatchlist() {
        String title = JOptionPane.showInputDialog("Enter the movie title:");
        String genre = JOptionPane.showInputDialog("Enter the movie genre:");
        Movie newMovie = new Movie(title, genre);
        watchlist.addMovie(newMovie);
        model.addElement(title);
    }

    public void addReview() {
        int rating = Integer.parseInt(JOptionPane.showInputDialog("Please enter a rating "
                + "on a scale of one to five stars:"));
        while (rating < 1 || rating > 5) {
            rating = Integer.parseInt(JOptionPane.showInputDialog("Invalid rating. Please enter another rating"));
        }
        String comment = JOptionPane.showInputDialog("Please enter a comment for your review:");
        Movie newReview = watchlist.removeMovie(model.remove(list.getSelectedIndex()).toString());
    }

    public void removeFromWatchlist() {
        if (list.getSelectedIndex() != -1) {
            watchlist.removeMovie(model.remove(list.getSelectedIndex()).toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "add") {
            addToWatchlist();
        } else if (e.getActionCommand() == "review") {
            addReview();
        } else if (e.getActionCommand() == "remove") {
            removeFromWatchlist();
        }
        list.setSelectedIndex(0);
    }
}
