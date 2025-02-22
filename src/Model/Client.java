package Model;

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
}
