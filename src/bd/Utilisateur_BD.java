package bd;
import Model.Utilisateur;
import java.sql.*;

public class Utilisateur_BD {
	
	public Utilisateur get_information_apartir_email(String email) {
		 String query = "SELECT `Id_user`, `Nom_complet`, `Email`, `password`, `Type` FROM `user` WHERE Email= ?";
	        try (Connection conn = BaseDonnees.getConnection(); 
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, email);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                return new Utilisateur(rs.getInt("Id_user"),rs.getString("Email"), rs.getString("password"),rs.getString("Type"), rs.getString("Nom_Complet"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
		
		
	}


}
