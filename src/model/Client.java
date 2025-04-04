package model;

import basedonne.ClientBd;
import java.util.List;

public class Client {
	private int id;
    private String nom;
    private String prenom;
    private String tele;
    private String email;
    
    public  Client (int id,String nom, String prenom,String tele,String email) {
    	this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tele = tele;
        this.email = email;
    }

    public int getid() {
        return id;
    }
    public void setid(int idv) {
       id=idv;
    }

    public String getemail() {
        return email;
    }

    public String getnom() {
        return nom;
    }
    public String getprenom() {
        return prenom;
    }

    public String gettele() {
        return tele;
    }
    // Affichage uniquement du nom et prénom
    @Override
    public String toString() {
        return nom + " " + prenom;
    }
    
    public static List<Client> listeClient() {
        return ClientBd.listClientBD();
    	
    }
    public int ajouterClient() {
    	return ClientBd.ajouterClientBD(this.nom,this.prenom,this.tele,this.email);
    }
	public static int nombreClient() {
	    return ClientBd.nombreClient();
	}

  
}
