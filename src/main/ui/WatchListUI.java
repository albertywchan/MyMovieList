package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatchListUI extends JPanel implements ActionListener {

    private DefaultListModel model;
    private JList watchlist;
    private JTextArea details;
    private JSplitPane splitpane;
    private JPanel moviesButtonPanel;

    public WatchListUI() {
        model = new DefaultListModel();
        moviesButtonPanel = new JPanel();
        moviesButtonPanel.setLayout(new GridLayout(3, 1));
        moviesButtonPanel.setPreferredSize(new Dimension(170, 240));
        setLayout(new BorderLayout());
        watchlist = new JList(model);
        watchlist.setFont(new Font("Lucida Console", Font.PLAIN, 12));
        watchlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        watchlist.setSelectedIndex(0);
        watchlist.setMinimumSize(new Dimension(200, 100));
        details = new JTextArea("Title: Movie 1\nGenre: Action\nRating: n/a\nComment: n/a");
        details.setFont(new Font("Lucida Console", Font.PLAIN, 12));
        splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, watchlist, details);
        splitpane.setEnabled(false);
        addWatchlistButtons();
        addWatchlistPanel();
    }

    private void addWatchlistButtons() {
        JButton addMovieButton = new JButton("Add a Movie");
        addMovieButton.setActionCommand("add");
        addMovieButton.addActionListener(this);
        JButton reviewMovieButton = new JButton("Write a Review");
        JButton removeMovieButton = new JButton("Remove Movie");
        moviesButtonPanel.add(addMovieButton);
        moviesButtonPanel.add(reviewMovieButton);
        moviesButtonPanel.add(removeMovieButton);
    }

    private void addWatchlistPanel() {
        JLabel watchListTitle = new JLabel("Watchlist");
        watchListTitle.setHorizontalAlignment(JLabel.CENTER);
        watchListTitle.setVerticalTextPosition(JLabel.BOTTOM);
        watchListTitle.setOpaque(true);
        watchListTitle.setForeground(Color.WHITE);
        watchListTitle.setBackground(new Color(0x000080));
        add(watchListTitle, BorderLayout.NORTH);
        add(splitpane, BorderLayout.CENTER);
        add(moviesButtonPanel, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "add") {
            model.addElement("Movie 1");
        }
    }
}
