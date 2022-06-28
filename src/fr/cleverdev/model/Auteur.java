package fr.cleverdev.model;

public class Auteur {

	private Long   id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    
    public Auteur() { 
    }
    
    public Auteur(String nom, String prenom, String telephone, String email) { 
    	this.nom = nom;
    	this.prenom = prenom;
    	this.telephone = telephone;
    	this.email = email;
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return getId() + " : " + getNom() + " " + getPrenom() + " - " + getTelephone() + " / " + getEmail();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if(((Auteur) obj).getId() != this.id) {
			return false;
		}
		
		return true;
	}
	
	
	

}
