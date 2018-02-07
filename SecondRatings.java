
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    //We add this other private to the constructor as I made this extra addition to the FirstRatings method 
    private HashMap<String, ArrayList<Rating>> loadRaters;
    public SecondRatings() {
        // default constructor
        this("ratedmovies_short.csv", "ratings_short.csv");
    }
    /*Creating the additional constructor where we call methods from FirstRatings to fill the
     * private variables that form the constructor: */
    public SecondRatings(String moviefile, String ratingsfile){
        //In order to create the constructor we change some methods in FirstRatings class to public
        FirstRatings ratings = new FirstRatings();
        myMovies = ratings.loadMovies(moviefile);
        /*for(Movie m : myMovies){
            System.out.println(m);
        }*/
        myRaters = ratings.csvMethod2(ratingsfile);
        /*System.out.println(myRaters.size());
        for(Rater r : myRaters){
            System.out.println(r.getID() + r.getMyRatings());
        }*/
        loadRaters = ratings.loadRaters(ratingsfile);
    }
    //This method returns the number of movies stored in myMovies. Must be public so other classes have access to it.
    public int getMovieSize(){
        return myMovies.size();
    }
    //This method returns the number of raters in myRaters. Must be public so other classes have access to it.
    public int getRaterSize(){
        //Mind that we are calling the HashMap.
        return loadRaters.size();
    }

    /*This method calculates the average ratings of a defined movie_id and returns a number, 
      which is he average by id.*/
    public double getAverageByID(String id, int minimalRaters){
        double count = 0;
        double numRatings = 0;
        double average = 0;
        if(minimalRaters == 0){
            return 0.0;
        }
        for(ArrayList<Rating> rating : loadRaters.values()){
            for(Rating rat : rating){
                //Iterating over the hashmap, and the ratings to get every rating for the movie selected:
                if(rat.getItem().equals(id)){
                    double value = rat.getValue();
                    numRatings++;
                    count = count + value;
                }
            }
        }
            /*Esta es por si en cualquier momento me piden una media de votacion por user, si no borra.
             * if(m.equals(id)){
                ArrayList<Rating> rating = loadRaters.get(m);
                //if statement to set a minimun:
                if(rating.size() >= minimalRaters){
                    //for loop to get every rating of the user and we just calculat the averages, simple.
                    for(int i=0;i<rating.size();i++){
                        Rating rat = rating.get(i);
                        System.out.println(rat.getValue());
                        count = count + rat.getValue();
                        numRatings++;
                    }
                    average = count/numRatings;
                    // test: System.out.println("average: " + average + " because count = " + count + " and numRatings =" + numRatings);
                }
            }*/
        if(numRatings< minimalRaters){
            return -1;
        }
        else{
            average = count/numRatings;
            return average;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        for(Movie m : myMovies){
            String movie_id = m.getID();
            getAverageByID(movie_id,minimalRaters);
            Rating a = new Rating(movie_id, getAverageByID(movie_id,minimalRaters));
            if(a.getValue() > -1){
                averageRatings.add(a);
            }
        }
        return averageRatings;
    }
    
    public String getTitle(String id){
        for(Movie m : myMovies){
            if(id.equals(m.getID())){
                return m.getTitle();
            }
        }
        return "Movie ID not found in the database";
    }
    
    public String getID(String title){
        for(Movie m : myMovies){
            if(title.equals(m.getTitle())){
                return m.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
}
