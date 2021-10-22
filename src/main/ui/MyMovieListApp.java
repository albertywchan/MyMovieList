package ui;

import model.*;
import persistence.*;
import java.util.Scanner;

// MyMovieList application
public class MyMovieListApp {
    private static final String WATCHLIST_FILE = "./data/watchlistTest.json";
    private static final String REVIEWS_FILE = "./data/reviewsTest.json";
    private MovieList watchlist;
    private MovieList reviews;
    private Scanner scanner;

    /* EFFECTS:  watchList and reviews are set to empty MovieLists
                 scanner is initiated
     */
    public MyMovieListApp() {
        watchlist = new MovieList("Watchlist");
        reviews = new MovieList("Reviews");
        scanner = new Scanner(System.in);
    }

    // EFFECTS: starts the application and runs the main menu
    public void runMainMenu() {
        boolean exit = false;
        int selection;
        System.out.print("Welcome. ");
        while (!exit) {
            displayMainMenu();
            selection = processSelection(1, 5);
            if (selection == 1) {
                runWatchlist();
            } else if (selection == 2) {
                runReviews();
            } else if (selection == 3) {
                loadMovies();
            } else if (selection == 4) {
                saveMovies();
            } else {
                exit = true;
                System.out.println("Goodbye.");
            }
        }
    }

    // EFFECTS:  runs the watchlist menu
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

    // EFFECTS: runs the reviews menu
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

    // EFFECTS:  displays the main menu options
    private void displayMainMenu() {
        System.out.println("Please select an option:\n"
                + "[1] View your watchlist.\n"
                + "[2] Read your reviews.\n"
                + "[3] Load an existing watchlist and reviews.\n"
                + "[4] Save your current watchlist and reviews.\n"
                + "[5] Exit.");
    }

    // EFFECTS: displays the watchlist menu options
    private void displayWatchlistMenu() {
        System.out.println("Please select an option:\n"
                + "[1] Add a movie to your watchlist.\n"
                + "[2] Remove a movie from your watchlist.\n"
                + "[3] Return to the main menu.");
    }

    // EFFECTS: displays the reviews menu options
    private void displayReviewsMenu() {
        System.out.println("Please select an option:\n"
                + "[1] Write a new review\n"
                + "[2] Read an old review.\n"
                + "[3] Update an old review.\n"
                + "[4] Return to the main menu.");
    }

    /* REQUIRES: firstOption and lastOption must correspond to the specific menu options
       EFFECTS:  returns an integer representing the option the user has selected
     */
    private int processSelection(int firstOption, int lastOption) {
        int input = scanner.nextInt();
        while (input < firstOption || input > lastOption) {
            System.out.println("Invalid input. Please select another option: ");
            input = scanner.nextInt();
        }
        scanner.nextLine(); // nextLine() must be called after nextInt() in order for scanner to work properly
        return input;
    }

    // EFFECTS:  displays movies currently in the watchlist
    private void viewWatchlist() {
        if (watchlist.isEmpty()) {
            System.out.println("Your watchlist is empty.");
        } else {
            System.out.println("Your watchlist is: " + watchlist.toString());
        }
    }

    /* MODIFIES: this
       EFFECTS:  adds a new movie to watchlist given user inputs for the movie title and genre
     */
    private void addToWatchlist() {
        System.out.println("Enter the movie title: ");
        String title = scanner.nextLine();
        System.out.println("Enter the movie genre: ");
        String genre = scanner.nextLine();
        Movie newMovie = new Movie(title, genre);
        watchlist.addMovie(newMovie);
        System.out.println(title + " has been successfully added to your watchlist.\n");
    }

    /* MODIFIES: this
       EFFECTS:  removes a movie from watchlist given user input for the movie title
     */
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

    // EFFECTS:  displays movies that are currently reviewed
    private void viewReviews() {
        if (reviews.isEmpty()) {
            System.out.println("You have not written any reviews yet.");
        } else {
            System.out.println("You have reviewed the following movies: " + reviews.toString());
        }
    }

    // EFFECTS:  displays a movie review given user input for the movie title
    private void readReview() {
        System.out.println("Enter the title of the movie review you would like to see: ");
        String title = scanner.nextLine();
        if (!reviews.hasMovie(title)) {
            System.out.println("There is no review for " + title + ". Please try again.\n");
        } else {
            System.out.println(reviews.getReview(title));
        }
    }

    /* MODIFIES: this
       EFFECTS:  transfers a specific movie from watchlist to reviews given user input for the movie title
                 rating and comment are updated using user inputs
     */
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

    /* MODIFIES: this
       EFFECTS:  updates a specific movie given user input for the movie title
                 rating and comment are updated using user inputs
     */
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

    // EFFECTS:  asks user for a rating on scale of 1 to 5 and returns it
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

    // EFFECTS:  asks user for a comment and returns it
    private String getNewComment() {
        System.out.println("Please enter a comment for your review: ");
        return scanner.nextLine();
    }

    /* MODIFIES: this
       EFFECTS:  creates new MovieList objects from the JSON files and sets fields to them
     */
    public void loadMovies() {
        JsonReader watchlistReader = new JsonReader(WATCHLIST_FILE);
        JsonReader reviewsReader = new JsonReader(REVIEWS_FILE);
        watchlist = watchlistReader.parseMovieList();
        reviews = reviewsReader.parseMovieList();
    }

    // EFFECTS:  saves both watchlist and reviews to individual JSON files
    public void saveMovies() {
        JsonWriter watchlistWriter = new JsonWriter(WATCHLIST_FILE);
        JsonWriter reviewsWriter = new JsonWriter(REVIEWS_FILE);
        watchlistWriter.saveMovieList(watchlist);
        reviewsWriter.saveMovieList(reviews);
    }

}
