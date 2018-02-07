import java.util.*;
import java.lang.Object;
/**
 * Method created as requested in the Assignment.
 * 
 * @author (Abraham Ferrero) 
 * @version (17/01/2018)
 */
public class MovieRunnerAverage {
    public void printAverageRatings(){
        final long startTime = System.currentTimeMillis();
        SecondRatings MoviesAndRatings = new SecondRatings
        ("ratedmoviesfull.csv", "ratings.csv");
        System.out.println("Number of total movies: " + MoviesAndRatings.getMovieSize());
        System.out.println("Number of total of Raters: " + MoviesAndRatings.getRaterSize());
        int minimum = 12;
        ArrayList<Rating> arrayMovies = MoviesAndRatings.getAverageRatings(minimum);
        //Method to sort them as requested:
        Collections.sort(arrayMovies);
        for(Rating r : arrayMovies){
             String item = r.getItem();
             String movieTitle = MoviesAndRatings.getTitle(item);
             //We also used a method to round the rating value:
             System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d+ " " + movieTitle);
        }
        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime) );
        System.out.println("Movies with at least " + minimum + " ratings: " + arrayMovies.size());
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings MoviesAndRatings = new SecondRatings
        ("ratedmoviesfull.csv", "ratings.csv");
        String movieRequest = "Vacation";
        String id = MoviesAndRatings.getID(movieRequest);
        if(id.equals("NO SUCH TITLE.")){
            System.out.println(id);
        }
        else{
        double aveRating = MoviesAndRatings.getAverageByID(id,1);
        System.out.println("The average rating for the movie " + movieRequest + " is " + aveRating);
        }
    }
    
    //revisar esa cosa rara del constructor entre firstratings y seconds
}
