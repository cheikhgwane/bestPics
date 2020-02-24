package beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Image {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private String titre;
	
	@Column
	private String description;
	
	@Column
	private String keywords;
	
	@Column
	private int largeur;
	
	@Column
	private int hauteur;
	
	@Column
	private Date dateCreation;
	
	@Column
	private Date dateMaj;

	@Column
	private String fileImage;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Album album;
	
	public Image() {
		
	}
	
	public Image(int id, String titre, String description, String keywords, int largeur, int hauteur, Date dateCreation,
			Date dateMaj, String fileImage) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.keywords = keywords;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.dateCreation = dateCreation;
		this.dateMaj = dateMaj;
		this.fileImage = fileImage;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getKeywords() {
		return keywords;
	}


	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	public int getLargeur() {
		return largeur;
	}


	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}


	public int getHauteur() {
		return hauteur;
	}


	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	public Date getDateMaj() {
		return dateMaj;
	}


	public void setDateMaj(Date dateMaj) {
		this.dateMaj = dateMaj;
	}


	public String getFileImage() {
		return fileImage;
	}


	public void setFileImage(String fileImage) {
		this.fileImage = fileImage;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
}
