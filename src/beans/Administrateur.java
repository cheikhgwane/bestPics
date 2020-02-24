package beans;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Administrateur extends Utilisateur{

	public Administrateur() {
		super();
	}

	public Administrateur(int id, String nom, String prenom, String login, String password, List<Album> albums) {
		super(id, nom, prenom, login, password, albums);
	}
	
}
