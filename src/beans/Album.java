package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String nomAlbum;

	@Column
	private String albumUrl;

	@Column
	private boolean isPrivate;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Utilisateur proprietaire;

	@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
	private List<Image> images;

	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Utilisateur> grantedUser;

	public Album() {
		this.grantedUser = new ArrayList<Utilisateur>();
	}

	public Album(int id, String nomAlbum, Utilisateur proprietaire, List<Image> images) {
		super();
		this.id = id;
		this.nomAlbum = nomAlbum;
		this.proprietaire = proprietaire;
		this.images = images;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomAlbum() {
		return nomAlbum;
	}

	public void setNomAlbum(String nomAlbum) {
		this.nomAlbum = nomAlbum;
	}

	public Utilisateur getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Utilisateur proprietaire) {
		this.proprietaire = proprietaire;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(Image image) {
		this.images.add(image);
	}

	public List<Utilisateur> getGrantedUser() {
		return this.grantedUser;
	}

	public void addUser(Utilisateur u) {
		this.grantedUser.add(u);
	}

	public String getAlbumUrl() {
		return albumUrl;
	}

	public void setAlbumUrl(String albumUrl) {
		this.albumUrl = albumUrl;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void setGrantedUser(ArrayList<Utilisateur> grantedUser) {
		this.grantedUser = grantedUser;
	}

}
