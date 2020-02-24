package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Utilisateur;
import dao.DaoException;
import dao.UtilisateurDao;
import utils.FormValidator;

/**
 * Servlet implementation class Utilisateur
 */
@WebServlet({ "/user/*" })
public class UtilisateurServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String HOME_VIEW = "/WEB-INF/home.jsp";
	private static final String ADD_VIEW = "/WEB-INF/addUser.jsp";
	private static final String MODIFY_VIEW = "/WEB-INF/modifyUser.jsp";
	private static final String USER_LIST_VIEW = "/WEB-INF/userList.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UtilisateurServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestedUrl = request.getRequestURI();
		UtilisateurDao dao = new UtilisateurDao();
		
		if (requestedUrl.endsWith("/add")) {
			getServletContext().getRequestDispatcher(ADD_VIEW).forward(request, response);

		} else if (requestedUrl.endsWith("/modify")) {

			String id = request.getParameter("idUser");
			if (id == null) {
				getServletContext().getRequestDispatcher(HOME_VIEW).forward(request, response);
			}

			try {
				Utilisateur u = dao.findUser(Integer.valueOf(id));
				if (u != null) {
					request.setAttribute("utilisateur", u);
				}
				getServletContext().getRequestDispatcher(MODIFY_VIEW).forward(request, response);

			} catch (DaoException e) {
				e.printStackTrace();
				getServletContext().getRequestDispatcher(MODIFY_VIEW).forward(request, response);
			}

		} else if (requestedUrl.endsWith("/list")) {
			try {
				ArrayList<Utilisateur> users = dao.getUsers();
				request.setAttribute("utilisateurs", users);
				System.out.println(dao.getUsers().size());
			} catch (DaoException d) {
				System.out.println(d.getMessage());
			}
			getServletContext().getRequestDispatcher(USER_LIST_VIEW).forward(request, response);

		} else if (requestedUrl.endsWith("/delete")) {

			String _id = request.getParameter("idUser");
			if (_id == null) {
				getServletContext().getRequestDispatcher(USER_LIST_VIEW).forward(request, response);
			}
			System.out.println(_id);
			int idUser = Integer.valueOf(_id);

			try {
				int res = dao.deleteUser(idUser);
				System.out.println("res"+res);
			} catch (DaoException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			response.sendRedirect("list");

		}
		 getServletContext().getRequestDispatcher(USER_LIST_VIEW).forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestedUrl = request.getRequestURI();

		UtilisateurDao dao = new UtilisateurDao();
		FormValidator validator = new FormValidator(request);

		if (requestedUrl.endsWith("/add")) {
			String fields[] = { "nom", "prenom", "login", "password" };
			boolean result = validator.validateForm(fields);
			if (!result) {
				request.setAttribute("error", validator.getErrors());
				request.setAttribute("status", "Erreur lors de l'ajout utilisateur");
				getServletContext().getRequestDispatcher(ADD_VIEW).forward(request, response);
				return;
			}

			try {

				dao.addUtilisateur(validator.getUser());
				request.setAttribute("status", "Utilisateur ajouté");
				getServletContext().getRequestDispatcher(ADD_VIEW).forward(request, response);

			} catch (DaoException e) {
				System.out.println(e.getMessage());
				request.setAttribute("status", "Erreur lors de l'ajout utilisateur");
				getServletContext().getRequestDispatcher(ADD_VIEW).forward(request, response);
				return;

			}
		}else if(requestedUrl.endsWith("/modify")) {
			String fields[] = { "nom", "prenom"};
			boolean result = validator.validateForm(fields);
			if (!result) {
				request.setAttribute("error", validator.getErrors());
				request.setAttribute("status", "Erreur lors de l'ajout utilisateur");
				getServletContext().getRequestDispatcher(USER_LIST_VIEW).forward(request, response);
				return;
			}

			try {
				Utilisateur u = dao.findUser(Integer.valueOf(request.getParameter("idUser")));
				System.out.println("login"+u.getLogin());
				System.out.println("nom"+u.getNom());
				System.out.println(u.getPrenom());
				Utilisateur old = validator.getUser();
				u.setPrenom(old.getPrenom());
				dao.updateUser(u);
				response.sendRedirect("list");
			} catch (DaoException e) {
				request.setAttribute("status", "Erreur lors de l'ajout utilisateur");
				getServletContext().getRequestDispatcher(USER_LIST_VIEW).forward(request, response);
				return;

			}
		}
		
		//
		
		
	}

}
