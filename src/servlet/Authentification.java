package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Album;
import beans.Image;
import beans.Utilisateur;
import dao.AlbumDao;
import dao.DaoException;
import dao.UtilisateurDao;
import utils.FormValidator;

/**
 * Servlet implementation class Authentification
 */
@WebServlet({ "/login", "/logout", "/register", "/explorer" })
public class Authentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_VIEW = "/WEB-INF/login.jsp";
	private static final String REGISTER_VIEW = "/WEB-INF/register.jsp";
	private static final String EXPLORER_VIEW = "/WEB-INF/explorer.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authentification() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedUrl = request.getRequestURI();

		if (requestedUrl.endsWith("/login")) {
			getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
		} else if (requestedUrl.endsWith("/register")) {
			getServletContext().getRequestDispatcher(REGISTER_VIEW).forward(request, response);
		} else if (requestedUrl.endsWith("/explorer")) {
			AlbumDao albumDao = new AlbumDao();

			ArrayList<Album> albums;
			ArrayList<Image> displayedImage = new ArrayList<Image>();
			try {
				albums = albumDao.getAllAlbum();
				for (Album a : albums) {
					if (!a.isPrivateAlbum() && a.getImages()!=null) {
						for (Image i : a.getImages()) {
							displayedImage.add(i);
						}
					}
				}
			} catch (DaoException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			System.out.println(displayedImage.size());
			request.setAttribute("images", displayedImage.size()!=0 ? displayedImage : null);
			getServletContext().getRequestDispatcher(EXPLORER_VIEW).forward(request, response);

		} else { // deconnexion
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FormValidator validator = new FormValidator(request);
		String[] fields = { "login", "password" };

		boolean isValid = validator.validateForm(fields);
		System.out.println(isValid);

		if (!isValid) {
			request.setAttribute("error", validator.getErrors());
			getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
			return;
		}

		UtilisateurDao userDao = new UtilisateurDao();
		Utilisateur u = validator.getUser();

		try {
			ArrayList<Utilisateur> users = userDao.getUsers();
			for (Utilisateur _u : users) {
				if (_u.getLogin().equals(u.getLogin()) && _u.getPassword().equals(u.getPassword())) {
					HttpSession session = request.getSession();
					session.setAttribute("utilisateur", _u);
					System.out.println("admin" + _u.isAdmin());
					response.sendRedirect(request.getContextPath() + "/home");
					return;
				}
			}
			getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
		} catch (DaoException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			HashMap<String, String> error = new HashMap<String, String>();
			error.put("login", "user login or password doesn't match");
			request.setAttribute("error", error);
			getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
			return;
		}

	}
}