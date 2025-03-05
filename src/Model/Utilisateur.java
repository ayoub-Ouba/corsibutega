package model;

public class Utilisateur {
		private int id;
	    private String email;
	    private String password;
	    private String Type;
	    private String Nom_Complet;

	    public Utilisateur(int id,String email, String password,String Type,String Nom_Complet) {
	    	this.id = id;
	        this.email = email;
	        this.password = password;
	        this.Type = Type;
	        this.Nom_Complet = Nom_Complet;
	    }

	    public int getid() {
	        return id;
	    }

	    public String getemail() {
	        return email;
	    }

	    public String getPassword() {
	        return password;
	    }
	    public String getType() {
	        return Type;
	    }

	    public String getNom_Complet() {
	        return Nom_Complet;
	    }
	


}
