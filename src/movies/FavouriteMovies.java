package movies;

import java.sql.*;
import java.util.*;

public class FavouriteMovies {
	  Scanner sc = new Scanner(System.in);
	  static String DB_URL = "jdbc:mysql://localhost/";
	  static final String USER = "root";
	  static final String PASS = "parshit9897";
	  String dbName = "";
	  String existingTable = "";
	  String query = "";
	  Connection cn;

	  public void createDatabase(){
		  dbName = "moviesAssignment";
	    try{
	      Class.forName("com.mysql.cj.jdbc.Driver");
		  cn = DriverManager.getConnection(DB_URL, USER, PASS);
	      Statement st = cn.createStatement();
	      
	      query = "Create Database If Not Exists " + dbName;
	      st.executeUpdate(query);
	      System.out.println("Database created and connected successfully..");
	
	      DB_URL = DB_URL + dbName;
	    }
	    catch(SQLException e){
	      System.out.println(e.getMessage());
	    }
	    catch(ClassNotFoundException e) {
	      System.out.println(e.getMessage());
	    }
	  }

	  public void createTable(){
	    if(!dbName.isEmpty()){
	      try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        cn = DriverManager.getConnection(DB_URL, USER, PASS);
	        Statement st = cn.createStatement();
	        query = "Create Table Movies"
	               + "(Name Varchar(255), "
	               + "Actor Varchar(255), "
	               + "Actress Varchar(255), "
	                + "DirectorName Varchar(255), "
	               + "ReleaseYear Integer, "
	               + "Primary Key(Name))";
	        st.executeUpdate(query);
	        System.out.println("Movies table created successfully..");
	      }
	      catch(SQLException e){
	        System.out.println(e.getMessage());
	      }
	      catch(ClassNotFoundException e) {
		      System.out.println(e.getMessage());
		    }
	    } else{
	      System.out.println("No database selected/found. Please create database before creating table.");
	    }
	  }

	  public void insertData(){
		if (!dbName.isEmpty()) {
			System.out.print("Enter movie name : ");
			String movieName = sc.nextLine();
			System.out.print("Enter actor name : ");
			String actorName = sc.nextLine();
			System.out.print("Enter actress name : ");
			String actressName = sc.nextLine();
			System.out.print("Enter director name : ");
			String directorName = sc.nextLine();
			System.out.print("Enter release year : ");
			int releaseYear = sc.nextInt();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				cn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement st = cn.createStatement();

				boolean tExists = false;
				String tName = "";
				ResultSet rs = cn.getMetaData().getTables(null, null, "movies", null);
				while (rs.next()) {
					tName = rs.getString("TABLE_NAME");
					if (tName != null && tName.equalsIgnoreCase("movies")) {
						tExists = true;
						break;
					}
				}

				if (tExists) {
					query = "Insert Into Movies Values (\'" + movieName + "\', " + "\'" + actorName
							+ "\', " + "\'" + actressName + "\', " + "\'" + directorName + "\', " + releaseYear + ")";
					st.executeUpdate(query);
					System.out.println("1 row inserted!");
				} else {
					System.out.println("No table found. Please create table before entering data.");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("No database found. Please create database and table before inserting data.");
		}

	}	  

	  public void displayData(){
		if (!dbName.isEmpty()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				cn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement st = cn.createStatement();
				query = "Select * from movies";
				ResultSet rs = st.executeQuery(query);
				System.out.printf("%-20s %-20s %-20s %-20s %-20s %n","NAME", "ACTOR", "ACTRESS", "DIRECTOR NAME", "RELEASE YEAR");
				
				while (rs.next()) {
					
					System.out.printf("%-20s %-20s %-20s %-20s %-20d %n", rs.getString("Name"),
					                                                      rs.getString("Actor"),
					                                                      rs.getString("Actress"),
					                                                      rs.getString("DirectorName"),
					                                                      rs.getInt("ReleaseYear"));
				}
				System.out.println();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("No database selected/found to display data.");
		}
	}
	  
	  public void displayData(String data) {
		if (!dbName.isEmpty()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				cn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement st = cn.createStatement();
				query = "Select " + data + " from movies";
				ResultSet rs = st.executeQuery(query);

				System.out.println();
				System.out.println(data.toUpperCase());
				System.out.println();

				if (data.equalsIgnoreCase("releaseYear")) {
					while (rs.next()) {
						System.out.printf("%d", rs.getInt(data));
						System.out.println();
					}
				} else {
					while (rs.next()) {
						System.out.printf("%s", rs.getString(data));
						System.out.println();
					}
				}
				System.out.println();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("No database selected/found to display data.");
		}
	}
}
