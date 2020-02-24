package servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import beans.Image;
import beans.Utilisateur;
import dao.AlbumDao;
import dao.DaoException;
import dao.ImageDao;
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

	public static final String UPLOAD_DIR = "C:\\UploadedImg";
	UtilisateurDao userDao = new UtilisateurDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlbumServlet() {
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

		AlbumDao dao = new AlbumDao();
		
		if(requestedUrl.endsWith("/album") && request.getParameter("albumId")!=null) {
			//show all images of an album
			int albumId = Integer.valueOf(request.getParameter("albumId"));
			
			ImageDao imgDao = new ImageDao();
			
			List<Image> images;
			try {
				ArrayList<Image> userImages = new ArrayList<Image>();
				images = imgDao.getImage();
				for(Image i :images) {
					if(i.getAlbum().getId()==albumId) {
						userImages.add(i);
					}
				}

				request.setAttribute("images", userImages);
				request.setAttribute("albumId",albumId);
				
			} catch (DaoException e) {
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher(ALBUM_IMAGE_VIEW).forward(request, response);
		}else if (requestedUrl.contains("delete")) {

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
					e.printStackTrace();
				}
			}
			response.sendRedirect(request.getContextPath() + "/album/");
		}
		else {
			try {

				ArrayList<Album> albums = dao.getAllAlbum();
				ArrayList<Utilisateur> users = userDao.getUsers();

				request.setAttribute("albums", albums);
				request.setAttribute("utilisateurs", users);

			} catch (DaoException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher(ALBUM_VIEW).forward(request, response);
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
				File f = new File(UPLOAD_DIR);
				if (!f.exists()) {
					f.mkdir();
				}

				DataInputStream inputStream = new DataInputStream(imgUtils.uploadImage("albumCover"));
				byte[] buffer = new byte[(int) imgUtils.getFileSize()];
				inputStream.read(buffer);

				String imgName = imgUtils.getImageName();

				File targetFile = new File(UPLOAD_DIR + "\\" + imgName);
				a.setAlbumUrl(imgName);
			 //	a.setProprietaire((Utilisateur)request.getSession().getAttribute("utilisateur").);

				
				DataOutputStream outStream = new DataOutputStream(new FileOutputStream(targetFile));

				outStream.write(buffer);
				outStream.close();

				dao.createAlbum(a, (Utilisateur) session.getAttribute("utilisateur"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher(ALBUM_VIEW).forward(request, response);

		} else if (requestedUrl.endsWith("/update")) {
			int id = getAlbumId(request);
			if (id != -1) {

			}
		} else if (requestedUrl.endsWith("/share")) {
			String isPrivate = request.getParameter("isPrivate");
			String[] userShared = request.getParameterValues("sharedWith");

			if (isPrivate != null) {// public album

				Album sharedAlbum = validator.getAlbum();
				System.out.println(sharedAlbum.isPrivate());

			} else if (userShared.length != 0) {
				Album sharedAlbum = validator.getAlbum();

				for (String s : userShared) {
					Utilisateur u = null;
					try {
						u = userDao.findUser(Integer.valueOf(s));
						sharedAlbum.addUser(u);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (DaoException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}

				}

				/*
				 * for(Utilisateur u: sharedAlbum.getGrantedUser()) {
				 * System.out.println(u.getLogin()); }
				 */
			}

		}
	}

}
