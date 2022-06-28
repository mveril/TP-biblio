package fr.cleverdev.model;


public class Livre {
	private Long id;
	private Auteur auteur;
	private String titre;
	private Integer nbPages;
	private String categorie;
	
	public Livre(long id, Auteur auteur, String titre, int nbPages, String categorie) {
		this.id = id;
		this.auteur = auteur;
		this.titre = titre;
		this.nbPages = nbPages;
		this.categorie = categorie;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Auteur getAuteur() {
		return auteur;
	}
	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Integer getNbPages() {
		return nbPages;
	}
	public void setNbPages(Integer nbPages) {
		this.nbPages = nbPages;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
		result = prime * result + ((nbPages == null) ? 0 : nbPages.hashCode());
		result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
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


