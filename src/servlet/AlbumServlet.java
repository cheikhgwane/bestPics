package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Album;
import beans.Utilisateur;
import dao.AlbumDao;
import dao.DaoException;
import dao.UtilisateurDao;
import utils.FormValidator;
import utils.ImageUtils;

/**
 * Servlet implementation class Album
 */
@MultipartConfig
@WebServlet("/album/*")
public class AlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ALBUM_VIEW = "/WEB-INF/album.jsp";
	private static final String ALBUM_IMAGE_VIEW = "/WEB-INF/images.jsp";
	private static final String ALBUM_INFO = "/WEB-INF/albumInfo.jsp";

	UtilisateurDao userDao = new UtilisateurDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 * 
	 */
	public AlbumServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedUrl = request.getRequestURI();
		AlbumDao dao = new AlbumDao();
		UtilisateurDao userDao = new UtilisateurDao();
		Utilisateur currentUser = (Utilisateur) request.getSession().getAttribute("utilisateur");

		if (requestedUrl.endsWith("/album") && request.getParameter("albumId") != null) {
			// show all images of an album
			int albumId = getAlbumId(request);

			AlbumDao albumDao = new AlbumDao();

			try {
				ArrayList<Album> userAlbum;
				List<Utilisateur> users = null;
				List<Utilisateur> grantedUser = null;

				userAlbum = albumDao.getAllAlbum();

				Album displayedAlbum = null;
				if (userAlbum.size() != 0) {

					for (Album a : userAlbum) {
						if (a.getId() == albumId) {
							displayedAlbum = a;	
						}
					}
					int albumSize = displayedAlbum.getImages()!=null ? displayedAlbum.getImages().size():0;
					request.setAttribute("album", displayedAlbum);
					request.setAttribute("albumSize",albumSize);
				}

				users = userDao.getUsers();
				grantedUser = displayedAlbum.getGrantedUser();

				for (Utilisateur user : grantedUser) {
					if (users.contains(user)) {
						System.out.println(user.getLogin());
						users.remove(user);
					}
				}
				users.removeIf(u -> (u.getId() == currentUser.getId()));

				request.setAttribute("utilisateurs", users);
				request.setAttribute("grantedUsers", grantedUser);
				
				System.out.println("Taille liste utilisateur autorisé "+grantedUser.size());
				

			} catch (DaoException e) {
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher(ALBUM_IMAGE_VIEW).forward(request, response);
		} else if (requestedUrl.contains("delete")) {

			int id = getAlbumId(request);
			System.out.println(id);
			if (id != -1) {
				try {
					ArrayList<Album> albums = dao.getAllAlbum();
					for (Album a : albums) {
						if (a.getId() == id) {
							dao.deleteAlbum(a);
							System.out.println("album name" + a.getNomAlbum());
							break;
						}
					}

					albums = dao.getAllAlbum();
					ArrayList<Utilisateur> users = userDao.getUsers();

					request.setAttribute("albums", albums);
					request.setAttribute("utilisateurs", users);
				} catch (DaoException e) {
					System.out.println(e.getMessage());
					response.sendRedirect(request.getContextPath() + "/album/");
					e.printStackTrace();
				}
			}
			response.sendRedirect(request.getContextPath() + "/album/");

		} else if (requestedUrl.endsWith("/albumInfo") && request.getParameter("albumId") != null) {
			int albumId = getAlbumId(request);

			List<Utilisateur> users = null;
			List<Utilisateur> grantedUser = null;
			ArrayList<Album> albums;

			try {
				albums = dao.getAllAlbum();
				for (Album a : albums) {
					if (a.getId() == albumId) {
						System.out.println("album found here");
						grantedUser = a.getGrantedUser();
						request.setAttribute("album", a);
						request.setAttribute("albumSize", a.getImages().size());
						System.out.println("albumCover : " + a.getAlbumUrl());
						break;
					}
				}
				users = userDao.getUsers();

				for (Utilisateur user : grantedUser) {
					if (users.contains(user)) {
						users.remove(user);
					}
				}
				request.setAttribute("utilisateurs", users);
				request.setAttribute("grantedUsers", grantedUser);
				getServletContext().getRequestDispatcher(ALBUM_INFO).forward(request, response);
				return;
			} catch (DaoException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/album/");
				return;
			}

		} else

		{
			AlbumDao albumDao = new AlbumDao();
			try {
				ArrayList<Album> albums = albumDao.getAlbums(currentUser.getId());
				request.setAttribute("albums", albums);
				getServletContext().getRequestDispatcher(ALBUM_VIEW).forward(request, response);

			} catch (DaoException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}

	}

	public int getAlbumId(HttpServletRequest request) {
		String albumId = request.getParameter("albumId");
		if (albumId != null) {
			int _id = Integer.valueOf(albumId);
			return _id;
			// return the page with the list of image that belong to that album
		}
		return -1;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AlbumDao dao = new AlbumDao();

		String requestedUrl = request.getRequestURI();

		FormValidator validator = new FormValidator(request);

		ImageUtils imgUtils = new ImageUtils(request);

		if (requestedUrl.endsWith("/add")) {

			Album a = validator.getAlbum();
			HttpSession session = request.getSession();

			try {
				imgUtils.uploadImage("albumCover");
				String imgName = imgUtils.getImageName();
				a.setAlbumUrl(imgName);
				dao.createAlbum(a, (Utilisateur) session.getAttribute("utilisateur"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/album/user/");

		} else if (requestedUrl.endsWith("/modify") && request.getParameter("albumId") != null)
			try {
				{
					int albumId = getAlbumId(request);
					ArrayList<Album> userAlbums = dao
							.getAlbums(((Utilisateur) request.getSession().getAttribute("utilisateur")).getId());

					Album oldAlbum = null;
					Album newAlbum = validator.getAlbum();
					newAlbum.setId(albumId);

					for (Album a : userAlbums) {
						if (a.getId() == albumId) {
							oldAlbum = a;
						}
					}

					if (request.getPart("albumCover") != null
							&& request.getPart("albumCover").getSubmittedFileName().length() != 0) {
						imgUtils.uploadImage("albumCover");
						imgUtils.getImgInfo();

						newAlbum.setAlbumUrl(imgUtils.getImageName());
					} else { // in case the albumCover was not modified by the user
						newAlbum.setAlbumUrl(oldAlbum.getAlbumUrl());
					}

					System.out.println(dao.updateAlbum(newAlbum));
					response.sendRedirect(request.getContextPath() + "/album/user/");
				}
			} catch (DaoException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		else if (requestedUrl.endsWith("/share")) {
			String[] userShared = request.getParameterValues("sharedWith");

			Album sharedAlbum = null;
			int userId = ((Utilisateur)request.getSession().getAttribute("utilisateur")).getId();

			try {
				ArrayList<Album> albums = dao.getAlbums(userId);
				for (Album a : albums) {
					if (a.getId() == getAlbumId(request)) {
						sharedAlbum = a;
					}
				}
			} catch (DaoException e1) {
				System.out.println(e1.getMessage());
				response.sendRedirect(request.getContextPath() + "/album/user/");
				e1.printStackTrace();
			}

			if (userShared.length != 0) {
				System.out.println("shared user length" + userShared.length);
				for (String s : userShared) {
					Utilisateur u = null;
					try {
						u = userDao.findUser(Integer.valueOf(s));
						sharedAlbum.addUser(u);
						System.out.println("Added user "+ u.getLogin());
					} catch (Exception e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
						response.sendRedirect(request.getContextPath() + "/album/user/");
						return;
					}
				}
			}

			sharedAlbum.setId(getAlbumId(request));
			try {
				dao.updateAlbum(sharedAlbum);
				response.sendRedirect(request.getContextPath() + "/album/user/");
				return;
			} catch (DaoException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/album/user/");

		}
	}
}
