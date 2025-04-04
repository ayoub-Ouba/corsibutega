package basedonne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Client;

public class ClientBd {
	
	 public  static int ajouterClientBD(String nom,String prenom,String tele,String email) {
	        String query = "INSERT INTO `client`(`nom`, `prenom`, `tele`, `Email`) VALUES (?, ?, ?, ?)";

	        try (Connection conn = BaseDonnees.getConnection(); 
	             PreparedStatement stmt = conn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS)) {            

	            stmt.setString(1, nom);  
	            stmt.setString(2, prenom);              
	            stmt.setString(3, tele);                  
	            stmt.setString(4, email);                                 

	            // Exécuter la requête
	            int rowsInserted = stmt.executeUpdate();
	        
	            if (rowsInserted > 0) {
	                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) { 
	                    if (generatedKeys.next()) {
	                        int idclient = generatedKeys.getInt(1);
	                        return idclient;
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	           
	        }
	        return 0;
	    }

	 public static Client find_client(String nom, String prenom) {
		    String query = "SELECT Id_Client, nom, prenom, tele, Email FROM client WHERE nom = ? AND prenom = ?";
		    
		    try (Connection conn = BaseDonnees.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		        
		        stmt.setString(1, nom);
		        stmt.setString(2, prenom);
		        
		        try (ResultSet res = stmt.executeQuery()) { 
		            if (res.next()) {
		                return new Client(
		                    res.getInt("Id_Client"),
		                    res.getString("nom"),
		                    res.getString("prenom"),
		                    res.getString("tele"),
		                    res.getString("Email")
		                );
		            }
		        }
		        
		    } catch (SQLException e) {
		        System.err.println("Erreur lors de la recherche du client : " + e.getMessage());
		    }
		    
		    return null; 
		}
	 public static List<Client> listClientBD() {
		    List<Client> clients = new ArrayList<>();
		    String query = "SELECT Id_Client, nom, prenom, tele, Email FROM client";
		    try (Connection conn = BaseDonnees.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		        
		        try (ResultSet res = stmt.executeQuery()) {
		            while (res.next()) { 
		                clients.add(new Client(
		                    res.getInt("Id_Client"),
		                    res.getString("nom"),
		                    res.getString("prenom"),
		                    res.getString("tele"),
		                    res.getString("Email")
		                ));
		            }
		        }
		        
		    } catch (SQLException e) {
		        System.err.println("Erreur lors de la récupération des clients : " + e.getMessage());
		    }
		    return clients;
		}
	
	 public static int nombreClient() {
		    int countClient = 0;
		    String query = "SELECT count(*) FROM client";
		    
		    try (Connection conn = BaseDonnees.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		        
		        try (ResultSet res = stmt.executeQuery()) {
		            if (res.next()) {
		                countClient = res.getInt(1);
		                return countClient;
		            }
		        }
		        
		    } catch (SQLException e) {
		        System.err.println("Erreur lors de la récupération du nombre de clients : " + e.getMessage());
		    }
		    
		    return countClient;
		}


}
