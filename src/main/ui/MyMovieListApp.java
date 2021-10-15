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
        System.out.println("Welcome. Please select an option:\n"
                + "[1] View your watchlist.\n"
                + "[2] Read your reviews.\n"
                + "[3] Exit.");
    }

    private void displayWatchlistMenu() {
        System.out.println("Please select an option:\n"
                + "[1] Add a movie to your watchlist.\n"
                + "[2] Remove a movie from your watchlist.\n"
                + "[3] Return to the main menu.");
    }

    private void displayReviewsMenu() {
        System.out.println("Please select an option:\n"
                + "[1] Write a new review\n"
                + "[2] Read an old review.\n"
                + "[3] Update an old review.\n"
                + "[4] Return to the main menu.");
    }

    private int processSelection(int firstOption, int lastOption) {
        int input = scanner.nextInt();
        while (input < firstOption || input > lastOption) {
            System.out.println("Invalid input. Please select another option: ");
            input = scanner.nextInt();
        }
        scanner.nextLine(); // nextLine() must be called after nextInt() in order for scanner to work properly
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
        String title = scanner.nextLine();
        System.out.println("Enter the movie genre: ");
        String genre = scanner.nextLine();
        Movie newMovie = new Movie(title, genre);
        watchlist.addMovie(newMovie);
        System.out.println(title + " has been successfully added to your watchlist.\n");
    }

    private void removeFromWatchlist() {
        if (watchlist.isEmpty()) {
            System.out.println("There is nothing to remove.");
        } else {
            System.out.println("Enter the title of the movie you would like to remove: ");
            String title = scanner.nextLine();
            if (!watchlist.hasMovie(title)) {
                System.out.println(title + " is not in your watchlist. Please try again.\n");
            } else {
                watchlist.removeMovie(title);
                System.out.println(title + " has been successfully removed from your watchlist.\n");
            }
        }
    }

    private void viewReviews() {
        if (reviews.isEmpty()) {
            System.out.println("You have not written any reviews yet.");
        } else {
            System.out.println("You have reviewed the following movies: " + reviews.toString());
        }
    }

    private void readReview() {
        System.out.println("Enter the title of the movie review you would like to see: ");
        String title = scanner.nextLine();
        if (!reviews.hasMovie(title)) {
            System.out.println("There is no review for " + title + ". Please try again.\n");
        } else {
            System.out.println(reviews.getReview(title));
        }
    }

    private void addReview() {
        System.out.println("Enter the title of the movie you would like to review: ");
        String title = scanner.nextLine();
        if (!watchlist.hasMovie(title)) {
            System.out.println(title + " is not in your watchlist. Please try again.\n");
        } else {
            Movie newReview = watchlist.removeMovie(title);
            reviews.addMovie(newReview);
            reviews.updateReview(title, getNewRating(), getNewComment());
            System.out.println("Your review for " + title + " has been successfully added.\n");
        }
    }

    private void updateReview() {
        if (reviews.isEmpty()) {
            System.out.println("There are no reviews to update.");
        } else {
            System.out.println("Enter the title of the movie you would like to update the review for: ");
            String title = scanner.nextLine();
            if (!reviews.hasMovie(title)) {
                System.out.println("There is no review for " + title + ". Please try again.\n");
            } else {
                reviews.updateReview(title, getNewRating(), getNewComment());
                System.out.println("Your review for " + title + " has been successfully updated.\n");
            }
        }
    }

    private int getNewRating() {
        System.out.println("Please enter a rating on a scale of one to five stars: ");
        int rating = scanner.nextInt();
        while (rating < 1 || rating > 5) {
            System.out.println("Invalid rating. Please enter another rating.");
            rating = scanner.nextInt();
        }
        scanner.nextLine(); // nextLine() must be called after nextInt() in order for scanner to work properly
        return rating;
    }

    private String getNewComment() {
        System.out.println("Please enter a comment for your review: ");
        return scanner.nextLine();
    }

    public void runApp() {
        boolean exit = false;
        int selection;
        while (!exit) {
            displayMainMenu();
            selection = processSelection(1, 3);
            if (selection == 1) {
                runWatchlist();
            } else if (selection == 2) {
                runReviews();
            } else {
                exit = true;
            }
        }
    }

    public void runWatchlist() {
        boolean exit = false;
        int selection;
        while (!exit) {
            viewWatchlist();
            displayWatchlistMenu();
            selection = processSelection(1, 3);
            if (selection == 1) {
                addToWatchlist();
            } else if (selection == 2) {
                removeFromWatchlist();
            } else {
                exit = true;
            }
        }
    }

    public void runReviews() {
        boolean exit = false;
        int selection;
        while (!exit) {
            viewReviews();
            displayReviewsMenu();
            selection = processSelection(1, 4);
            if (selection == 1) {
                addReview();
            } else if (selection == 2) {
                readReview();
            } else if (selection == 3) {
                updateReview();
            } else {
                exit = true;
            }
        }
    }
}
