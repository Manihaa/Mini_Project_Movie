import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {

    private ArrayList<Movie> collection;
    private Scanner scan;

    public MovieCollection(){
        collection = new ArrayList<Movie>();
        scan = new Scanner(System.in);
        start();
    }

    private void start(){
        readData();
        System.out.println(collection);
    }

    private void readData(){
        try {
            File myFile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String title = splitData[0];
                String cast = splitData[1];
                String director = splitData[2];
                String overview = splitData[3];
                int runTime = Integer.parseInt(splitData[4]);
                double userRating = Double.parseDouble(splitData[5]);
                Movie m = new Movie(title, cast, director, overview, runTime, userRating);
                collection.add(m);
            }
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }

}
