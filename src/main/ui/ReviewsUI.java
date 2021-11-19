package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviewsUI extends JPanel implements ActionListener {

    private MovieList reviews;
    private DefaultListModel model;
    private JList list;
    private JTextArea details;
    private JSplitPane splitPane;
    private JPanel buttons;

    public ReviewsUI() {
        reviews = new MovieList("Reviews");
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
        addReviewsButtons();
        addReviewsPanel();
    }

    public void addReviewsButtons() {
        JButton updateReviewButton = new JButton("Update Review");
        updateReviewButton.setActionCommand("update");
        updateReviewButton.addActionListener(this);
        buttons.add(updateReviewButton);
    }

    public void addReviewsPanel() {
        JLabel panelTitle = new JLabel("Reviews");
        panelTitle.setHorizontalAlignment(JLabel.CENTER);
        panelTitle.setVerticalTextPosition(JLabel.BOTTOM);
        panelTitle.setOpaque(true);
        panelTitle.setForeground(Color.WHITE);
        panelTitle.setBackground(new Color(0x000080));
        add(panelTitle, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(buttons, BorderLayout.EAST);
    }

    public void updateReview() {
        int rating = Integer.parseInt(JOptionPane.showInputDialog("Please enter a new rating "
                + "on a scale of one to five stars:"));
        while (rating < 1 || rating > 5) {
            rating = Integer.parseInt(JOptionPane.showInputDialog("Invalid rating. Please enter another rating"));
        }
        String comment = JOptionPane.showInputDialog("Please enter a new comment for your review:");
        reviews.updateReview(list.getSelectedValue().toString(), rating, comment);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("update")) {
            updateReview();
        }
    }
}
