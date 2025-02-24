public class Movie {
    private String title;
    private String cast;
    private String director;
    private String overview;
    private int runTime;
    private double userRating;

    public Movie(String t, String c, String d, String o, int r, double u){
        title = t;
        cast = c;
        director = d;
        overview = o;
        runTime = r;
        userRating = u;
    }

    public String getTitle(){
        return title;
    }

    public String getCast(){
        return cast;
    }

    public String getDirector(){
        return director;
    }

    public String getOverview(){
        return overview;
    }

    public int getRunTime(){
        return runTime;
    }

    public double getUserRating(){
        return userRating;
    }
}
