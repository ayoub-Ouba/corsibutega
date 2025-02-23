package Model;


public class produit {
	    private int  id;
	    private String label;
	    private float  prix;
	    private int id_catg;

	    public produit(int id,String label,float prix,int id_catg) {
	        this.id = id;
	        this.label = label;
	        this.prix = prix;
	        this.id_catg = id_catg;
	    }

	    public int getid() {
	        return id;
	    }
	    public String getlabel() {
	        return label;
	    }
	    public float getprix() {
	        return prix;
	    }
	    
	    public int getid_catg() {
	        return id_catg;
	    }
	    public void information() {
	    	System.out.println("id :"+this.id+" label :"+this.label+" prix :"+this.prix);
	    	
	    }

}
