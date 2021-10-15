package model;

public class Movie {
    private String title;
    private String genre;
    private String rating;
    private String comment;

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
        this.rating = "n/a";
        this.comment = "n/a";
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getRating() {
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
        rating = newRating + "/5";
    }

    public void setComment(String newComment) {
        comment = newComment;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Genre: " + genre + ", Rating: " + rating;
    }
}
