package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Album;
import beans.Image;
import beans.Utilisateur;
import dao.AlbumDao;
import dao.DaoException;

/**
 * Servlet implementation class Home
 */
@WebServlet({"/home/*"})
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String HOME_VIEW = "/WEB-INF/home.jsp";

	/**
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AlbumDao albumDao = new AlbumDao();
		
		ArrayList<Album> albums;
		ArrayList<Image> displayedImage = new ArrayList<Image>();
		
		Utilisateur u =  (Utilisateur)request.getSession().getAttribute("utilisateur");
		try {
			albums = albumDao.getAllAlbum();
			for(Album a:  albums) {
				if(!a.isPrivateAlbum() || verifyAccess(a.getGrantedUser(), u)) {
					for(Image i : a.getImages()) {
						displayedImage.add(i);
					}
				}
			}
		} catch (DaoException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		request.setAttribute("images", displayedImage);
		getServletContext().getRequestDispatcher(HOME_VIEW).forward(request, response);

	}
	
	public boolean verifyAccess(List<Utilisateur> users,Utilisateur u) {
		for(Utilisateur _u: users) {
			if(u.getId()== _u.getId()) return true;
		}
		return false;
	}


}
