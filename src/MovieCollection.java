import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {

    private ArrayList<Movie> collection;
    private Scanner scanner;

    public MovieCollection(){
        collection = new ArrayList<Movie>();
        scanner = new Scanner(System.in);
        start();
    }

    private void start(){
        readData();

        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

    }

    private void searchTitles(){
        System.out.println("Please enter the title: ");
        String title = scanner.nextLine().toLowerCase();
        ArrayList<Movie> titles = new ArrayList<>();

        for (Movie m : collection){
            if (m.getTitle().toLowerCase().contains(title)){
                titles.add(m);
            }
        }

        int i = 1;
        for (Movie m : titles){ //organize titles or use new arraylist
            System.out.println(i + ". " + m.getTitle());
            i++;
        }
    }

    private String searchCast(){

    }

    private ArrayList<String> insertionSort(ArrayList<Movie> list, int n){
        ArrayList<String> words = new ArrayList<>();
        String current;
        int idx;
        String temp;

        if (n == 1){ //titles
            for (Movie m : list){
                words.add(m.getTitle());
            }
        }else { //cast
            for (Movie m : list){
                words.add(m.getCast());
            }
        }

        for (int i = 1; i < words.size(); i++){
            current = words.get(i);
            idx = i;

            while (idx > 0 && current.compareTo(words.get(idx - 1)) < 0){
                temp = words.get(idx);
                words.set(idx, words.get(idx - 1));
                words.set(idx - 1, temp);
                idx--;
            }
        }
        return words;

    }


    private String movieInfo(Movie m){
        String str = "Title: " + m.getTitle() +
                "\nCast: " + m.getCast() +
                "\nDirector: " + m.getDirector() +
                "\nOverview: " + m.getOverview() +
                "\nRuntime: " + m.getRunTime() +
                "\nUser Rating: " + m.getUserRating();
        return str;
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
