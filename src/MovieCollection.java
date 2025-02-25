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
        System.out.print("Please enter the title: ");
        String title = scanner.nextLine().toLowerCase();
        ArrayList<Movie> titles = new ArrayList<>();
        int choice;

        for (Movie m : collection){
            if (m.getTitle().toLowerCase().contains(title)){
                titles.add(m);
            }
        }

        ArrayList<String> organizedTitles = insertionSortTitles(titles);
        if (organizedTitles.size() > 0){
            int i = 1;
            for (String s : organizedTitles){ //organize titles or use new arraylist
                System.out.println(i + ". " + s);
                i++;
            }

            System.out.print("\nWhich movie would you like to learn more about?\nEnter a number: ");
            choice = scanner.nextInt();

            for (Movie m : collection){
                if (organizedTitles.get(choice-1).equals(m.getTitle())){
                    System.out.println("\n" + movieInfo(m) + "\n");
                }
            }

        }else{
            System.out.println("No titles match that search term!");
        }
    }

    private void searchCast(){
        System.out.print("Please enter a name: ");
        String name = scanner.nextLine();
        ArrayList<String> casts = new ArrayList<>();
        ArrayList<Movie> movies = new ArrayList<>();
        String[] actors;
        boolean boo = true;
        int choice;

        for(Movie m : collection){
            actors = m.getCast().split("\\|");

            for (String a : actors){
                if (a.toLowerCase().contains(name.toLowerCase())){

                    for(String s : casts){
                        if ((s.toLowerCase().equals(a.toLowerCase()))){
                            boo = false;
                        }
                    }

                    if (boo){
                        casts.add(a);
                    }
                    boo = true;
                }
            }
        }

        ArrayList<String> organizedCast = insertionSortCast(casts);
        if (organizedCast.size() > 0){
            int i = 1;
            for (String s : organizedCast){
                System.out.println(i + ". " + s);
                i++;
            }

            System.out.print("Which actor/actress's movies do you want to see?\nEnter a number: ");
            choice = scanner.nextInt();

            for (Movie m : collection){
                if (m.getCast().contains(organizedCast.get(choice-1))){
                    movies.add(m);
                }
            }

            i = 1;
            for(String s : insertionSortTitles(movies)){
                System.out.println(i + ". " + s);
                i++;
            }

            System.out.print("\nWhich movie would you like to learn more about?\nEnter a number: ");
            choice = scanner.nextInt();

            for (Movie m : collection){
                if (insertionSortTitles(movies).get(choice-1).equals(m.getTitle())){
                    System.out.println("\n" + movieInfo(m) + "\n");
                }
            }

        }else{
            System.out.println("No names match that search term!");
        }

    }

    private ArrayList<String> insertionSortTitles(ArrayList<Movie> list){
        ArrayList<String> words = new ArrayList<>();
        String current;
        int idx;
        String temp;

        for (Movie m : list){
            words.add(m.getTitle());
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

    private ArrayList<String> insertionSortCast(ArrayList<String> list){
        String current;
        int idx;
        String temp;

        for (int i = 1; i < list.size(); i++){
            current = list.get(i);
            idx = i;

            while (idx > 0 && current.compareTo(list.get(idx - 1)) < 0){
                temp = list.get(idx);
                list.set(idx, list.get(idx - 1));
                list.set(idx - 1, temp);
                idx--;
            }
        }
        return list;
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
