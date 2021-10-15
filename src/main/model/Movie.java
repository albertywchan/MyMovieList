package model;

// This class represents a movie that has a title, a genre, a rating, and a comment
public class Movie {
    private String title;
    private String genre;
    private int rating;
    private String comment;

    /* REQUIRES: title and genre are not empty strings
       EFFECTS:  title and genre of the movie are set to the parameters
                 rating is default set to -1 and the comment is default set to be empty
    */
    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
        this.rating = -1;
        this.comment = "";
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setGenre(String newGenre) {
        genre = newGenre;
    }

    public void setRating(int newRating) {
        rating = newRating;
    }

    public void setComment(String newComment) {
        comment = newComment;
    }

    // EFFECTS:  returns a String with the format "Title: title | Genre: genre"
    @Override
    public String toString() {
        return "Title: " + title + " | Genre: " + genre;
    }
}
