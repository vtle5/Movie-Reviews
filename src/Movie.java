// Assignment #: 6
//         Name: Vincent Le
//    StudentID: 1214852145
//      Lecture: MWF 10:45-11:35am
//  Description: The class Movie represents a Movie.

public class Movie
{
    private String movieTitle;
    private int year;
    private int length;
    private Review bookReview;

    //Constructor to initialize all member variables
    public Movie()
    {
        movieTitle = "?";
        length = 0;
        year = 0;
        bookReview = new Review();
    }

    //Accessor methods
    public String getMovieTitle()
    {
        return movieTitle;
    }

    public int getLength()
    {
        return length;
    }

    public int getYear()
    {
        return year;
    }

    public Review getReview()
    {
        return bookReview;
    }

    //Mutator methods
    public void setMovieTitle(String aTitle)
    {
        movieTitle = aTitle;
    }

    public void setLength(int aLength)
    {
        length = aLength;
    }

    public void setYear(int aYear)
    {
        year = aYear;
    }

    public void addRating(double rate)
    {
        bookReview.updateRating(rate);
    }

    //toString() method returns a string containg the information on the movie
    public String toString()
    {
        String result = "\nMovie Title:\t\t" + movieTitle
                + "\nMovie Length:\t\t" + length
                + "\nMovie Year:\t\t" + year
                + "\n" + bookReview.toString() + "\n\n";
        return result;
    }
}
