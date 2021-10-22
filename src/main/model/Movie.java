package model;

import org.json.JSONObject;

// This class represents a movie that has a title, a genre, a rating, and a comment
public class Movie {
    private String title;
    private String genre;
    private int rating;
    private String comment;

    /* REQUIRES: arguments must not be empty
       EFFECTS:  fields are set to the parameters
    */
    public Movie(String title, String genre, int rating, String comment) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.comment = comment;
    }

    /* REQUIRES: arguments must not be empty
       EFFECTS:  overloaded constructor for new movies without a rating/comment; mainly used when adding new movies
                 fields are set to the parameters
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

    // EFFECTS:  returns the movie as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("genre", genre);
        json.put("rating", rating);
        json.put("comment", comment);
        return json;
    }
}
