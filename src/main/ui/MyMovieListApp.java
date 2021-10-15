package ui;

import model.*;
import java.util.Scanner;

public class MyMovieListApp {
    private MovieList watchlist;
    private MovieList reviews;
    private Scanner scanner;

    public MyMovieListApp() {
        watchlist = new MovieList();
        reviews = new MovieList();
        scanner = new Scanner(System.in);
    }

    private void displayMainMenu() {
        System.out.println("Welcome. Please select an option: \n"
                + "[1] View your watchlist. \n"
                + "[2] Read your reviews. \n"
                + "[3] Exit.");
    }

    private void displayWatchlistMenu() {
        System.out.println("Please select an option: \n"
                + "[1] Add a movie to your watchlist. \n"
                + "[2] Remove a movie from your watchlist. \n"
                + "[3] Return to the main menu.");
    }

    private void displayReviewsMenu() {
        System.out.println("Please select an option: \n"
                + "[1] Write a new review. \n"
                + "[2] Update an old review. \n"
                + "[3] Return to the main menu.");
    }

    private int processSelection(int firstOption, int lastOption) {
        int input = scanner.nextInt();
        while (input < firstOption || input > lastOption) {
            System.out.println("Invalid input. Please select another option: \n");
            input = scanner.nextInt();
        }
        return input;
    }

    private void viewWatchlist() {
        if (watchlist.isEmpty()) {
            System.out.println("Your watchlist is empty.");
        } else {
            System.out.println("Your watchlist is: " + watchlist.toString());
        }
    }

    private void addToWatchlist() {
        System.out.println("Enter the movie title: ");
        String title = scanner.next();
        System.out.println("Enter the movie genre: ");
        String genre = scanner.next();
        Movie newMovie = new Movie(title, genre);
        watchlist.addMovie(newMovie);
        System.out.println(title + " has been successfully added to your watchlist.");
    }

    private void removeFromWatchlist() {
        if (watchlist.isEmpty()) {
            System.out.println("There is nothing to remove. Your watchlist is empty.");
        } else {
            System.out.println("Enter the title of the movie you would like to remove: ");
            String title = scanner.next();
            while (watchlist.getIndexOfMovie(title) == -1) {
                System.out.println("That movie is not in your watchlist. Please select a different movie: ");
                title = scanner.next();
            }
            watchlist.removeMovie(title);
            System.out.println(title + " has been successfully removed from your watchlist.");
        }
    }

    private void readReviews() {
        if (reviews.isEmpty()) {
            System.out.println("You have not written any reviews yet.");
        } else {
            System.out.println("You have reviewed the following movies: " + reviews.toString());
        }
    }

    private void addReview() {
        System.out.println("Enter the title of the movie you would like to review: ");
        String title = scanner.next();
        while (watchlist.getIndexOfMovie(title) == -1) {
            System.out.println("That movie is not in your watchlist. Please select a different movie: ");
            title = scanner.next();
        }
        Movie newReview = watchlist.getMovie(title);
        newReview.setRating(getNewRating());
        newReview.setComment(getNewComment());
        watchlist.removeMovie(title);
        reviews.addMovie(newReview);
    }

    private void updateReview() {
        System.out.println("Enter the title of the movie you would like to review: : ");
        String title = scanner.next();
        while (reviews.getIndexOfMovie(title) == -1) {
            System.out.println("There is no review for that movie. Please select a different review: ");
            title = scanner.next();
        }
        Movie newReview = watchlist.getMovie(title);
        newReview.setRating(getNewRating());
        newReview.setComment(getNewComment());
        System.out.println("Your review for " + title + " has been successfully updated.");
    }

    private int getNewRating() {
        System.out.println("Please enter a rating on a scale of one to five stars: ");
        int rating = scanner.nextInt();
        while (rating < 1 || rating > 5) {
            System.out.println("Invalid rating. Please enter another rating.");
            rating = scanner.nextInt();
        }
        return rating;
    }

    private String getNewComment() {
        System.out.println("Please enter a comment for your review: ");
        return scanner.next();
    }

    public void runApp() {
        boolean exit = false;
        int mainMenuSelection = 0;
        int watchlistMenuSelection = 0;
        int reviewMenuSelection = 0;
        while (!exit) {
            displayMainMenu();
            mainMenuSelection = processSelection(1,3);
            if (mainMenuSelection == 1) {
                viewWatchlist();
                displayWatchlistMenu();
                watchlistMenuSelection = processSelection(1,3);
                if (watchlistMenuSelection == 1) {
                    addToWatchlist();
                } else if (watchlistMenuSelection == 2) {
                    removeFromWatchlist();
                }
            } else if (mainMenuSelection == 2) {
                readReviews();
                displayReviewsMenu();
                reviewMenuSelection = processSelection(1,3);
                if (reviewMenuSelection == 1) {
                    addReview();
                } else if (reviewMenuSelection == 2) {
                    updateReview();
                }
            } else {
                exit = true;
            }
        }
    }
}
