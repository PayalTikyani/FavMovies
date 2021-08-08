package movies;

import java.util.*;

public class DatabaseOperations {

	public static void main(String[] args) {
		  int option;
		  boolean nextOption = true;
		  Scanner sc = new Scanner(System.in);
		  FavouriteMovies movies = new FavouriteMovies();
		  while(nextOption){
		      System.out.println("1: Creates and connects the database \"moviesAssignment\" if it does not exist.");
		      System.out.println("2: Creates new table \"movies\" if it does not exist.");
		      System.out.println("3: Insert data into \"movies\" table.");
		      System.out.println("4: Query data from \"movies\" table.");
		      System.out.println("5: Query particular column from \"movies\" table.");
		      System.out.println("6: Exit.");
		      option = sc.nextInt();
		      switch(option){
		        case 1 : movies.createDatabase();
		                 break;

		        case 2 : movies.createTable();
		                 break;

		        case 3 : movies.insertData();
		                 break;

		        case 4:  movies.displayData();
		                 break;
		         
		        case 5:  System.out.print("Enter column name to display data : ");
		                 String data = sc.next();
		        	     movies.displayData(data);
		                 break;

		        default: nextOption = false;
		                 
		      }
		  }
		  sc.close();
	}

}
