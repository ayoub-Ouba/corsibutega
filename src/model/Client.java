package model;

import basedonne.ClientBd;
import java.util.List;

public class Client {
	private int id;
    private String nom;
    private String prenom;
    private String tele;
    private String email;
    private ClientBd client_bd ;
    
    public  Client (int id,String nom, String prenom,String tele,String email) {
    	this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tele = tele;
        this.email = email;
        client_bd=new ClientBd();
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
    
    public static List<Client> arrayClient() {
    	ClientBd client_bd = new ClientBd(); // Créer une instance locale
        return client_bd.arrayClientBD();
    	
    }
    public int addClient() {
    	return client_bd.addClient(this.nom,this.prenom,this.tele,this.email);
    }
	public static int countClient() {
		ClientBd client_bd = new ClientBd(); 
	    return client_bd.countClient();
	}
}
