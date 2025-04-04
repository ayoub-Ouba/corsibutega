package basedonne;

import java.sql.*;

public class BaseDonnees {
    private static final String URL = "jdbc:mysql://localhost:3306/corsibuttega";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    private static Connection connection = null;

    // Méthode pour obtenir une connexion
    public static Connection getConnection() {
    	    try {
    	        return DriverManager.getConnection(URL, USER, PASSWORD);
    	   
    	    } catch (SQLException e) {
    	        throw new RuntimeException("Erreur de connexion à la base de données", e);
    	    }
    }

    // Méthode pour fermer la connexion
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.out.println("Impossible de fermer la connexion : " + e.getMessage());
            }
        }
    }

   
}
