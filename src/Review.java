// Assignment #: 6
//         Name: Vincent Le
//    StudentID: 1214852145
//      Lecture: MWF 10:45-11:35am
//  Description: The class Review represent reviews of a movie.

import java.text.DecimalFormat;

public class Review
{
    private int numberOfReviews;
    private double sumOfRatings;
    private double average;

    //Constructor to initialize all member variables
    public Review()
    {
        numberOfReviews = 0;
        sumOfRatings = 0.0;
        average = 0.0;
    }

    //It updates the number of REviews and avarage based on the
    //an additional rating specified by its parameter
    public void updateRating(double rating)
    {
        numberOfReviews++;
        sumOfRatings += rating;
        if (numberOfReviews > 0)
        {
            average = sumOfRatings/numberOfReviews;
        }
        else
            average = 0.0;
    }

    //toString() method returns a string containg its review average
    //and te number of Reviews
    public String toString()
    {
        DecimalFormat fmt = new DecimalFormat("0.00");
        String result = "Reviews:\t" + fmt.format(average) + "("
                + numberOfReviews + ")";
        return result;
    }
}
