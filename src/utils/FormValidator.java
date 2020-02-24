package utils;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import beans.Album;
import beans.Image;
import beans.Utilisateur;

public class FormValidator {

	private HashMap<String, String> errors = new HashMap<String, String>();
	private HttpServletRequest request;
	private String message;

	public FormValidator(HttpServletRequest req) {
		this.request = req;
	}

	public boolean validateForm(String... fields) {
		validateFields(fields);
		if (errors.isEmpty()) {
			return true;
		}
		return false;
	}

	public String getMessage() {
		return message;
	}

	public HashMap<String, String> getErrors() {
		return this.errors;
	}

	private String getValue(String param) {
		return request.getParameter(param) != null ? request.getParameter(param) : null;
	}

	public Utilisateur getUser() {
		Utilisateur u = new Utilisateur();
		u.setNom(getValue("nom"));
		u.setPrenom(getValue("prenom"));
		u.setLogin(getValue("login"));
		u.setPassword(getValue("password"));
		if(getValue("isAdmin")!=null) {
		  u.setAdmin(true);
		}
		return u;
	}
	
	public Album getAlbum() {
		Album a = new Album();
		a.setNomAlbum(getValue("albumName"));
		if(getValue("isPrivate")!=null) {
			a.setPrivate(true);
		}else {
			a.setPrivate(false);
		}
     	return a;
	}
	
	public Image getImage() {
		Image i = new Image();
		i.setDescription(getValue("description"));
		i.setTitre(getValue("title"));
		i.setKeywords(getValue("keywords"));
		return i;
	}
	private void validateFields(String... fields) {
		for (String f : fields) {
			if (getValue(f) == null || getValue(f).length() == 0) {
				errors.put(f, "Required field");
			}
		}
	}

}
