package beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String nom;
	@Column
	private String prenom;
	@Column
	private String login;
	@Column
	private String password;
	
	@Column
	private boolean admin;


	@OneToMany(cascade=CascadeType.REMOVE)
	private List<Album> albums;
	
	public Utilisateur() {
		
	}
	
	
	public Utilisateur(int id, String nom, String prenom, String login, String password, List<Album> albums) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.albums = albums;
	}
		
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}


	public List<Album> getAlbums() {
		return albums;
	}


	public void addAlbums(Album albums) {
		this.albums.add(albums);
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Album> getAlbum() {
		return albums;
	}

	public void addAlbum(Album album) {
		this.albums.add(album);
	}
	
}
