package servlet;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Album;
import beans.Image;
import beans.Utilisateur;
import dao.AlbumDao;
import dao.DaoException;
import dao.ImageDao;
import utils.FormValidator;
import utils.ImageUtils;

/**
 * Servlet implementation class Image
 */
@MultipartConfig
@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMAGE_INFO = "/WEB-INF/imgInfo.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedUrl = request.getRequestURI();
		String imageUrl = null;

		ImageDao dao = new ImageDao();

		if (requestedUrl.endsWith("/image/imgInfo")) {
			int id = Integer.valueOf(request.getParameter("imageId"));
			try {
				Image i = dao.getOne(id);
				request.setAttribute("image", i);
				getServletContext().getRequestDispatcher(IMAGE_INFO).forward(request, response);
			} catch (DaoException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		} else if (requestedUrl.endsWith("/image/getOne")) {
			if ((imageUrl = getImageUrl(request)) != null) {

				ImageUtils utils = new ImageUtils(request);
				ServletContext cntx = request.getServletContext();
				String mime = cntx.getMimeType(imageUrl);

				response.setContentLength((int) utils.getFileSize(imageUrl));
				response.setContentType(mime);

				response.getOutputStream().write(utils.readImage(imageUrl));
			}
		} else if (requestedUrl.endsWith("/delete")) {
			ImageDao imgDao = new ImageDao();
			int idAlbum = 0;
			int id = Integer.valueOf(request.getParameter("imageId"));
			ArrayList<Image> images;
			try {
				images = imgDao.getImage();
				for (Image i : images) {
					if (i.getId() == id) {
						idAlbum = i.getAlbum().getId();
						imgDao.deleteImage(i);
						break;
					}
				}
			} catch (DaoException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

			response.sendRedirect(request.getContextPath() + "/album?albumId=" + idAlbum);
		}
	}

	public String getImageUrl(HttpServletRequest request) {
		String imageId = request.getParameter("imageUrl");
		return imageId != null ? imageId : null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Image id :" + request.getParameter("imageId"));
		AlbumDao dao = new AlbumDao();

		String requestedUrl = request.getRequestURI();

		FormValidator validator = new FormValidator(request);

		ImageUtils imgUtils = new ImageUtils(request);

		ImageDao imgDao = new ImageDao();

		if (requestedUrl.endsWith("/add")) {
			try {
				imgUtils.uploadImage("imageFile");

				Image i = validator.getImage();
				i.setFileImage(imgUtils.getImageName());

				Dimension d = imgUtils.getFileDemension(imgUtils.getImageName());

				i.setHauteur((int) d.getHeight());
				i.setLargeur((int) d.getWidth());

				ArrayList<Album> albums = dao.getAllAlbum();

				int albumId = Integer.valueOf(request.getParameter("albumId"));
				for (Album a : albums) {
					if (a.getId() == albumId) {
						i.setAlbum(a);
						break;
					}
				}

				Date date = new Date();
				i.setDateCreation(date);
				imgDao.createImage(i);

				response.sendRedirect(request.getContextPath() + "/album?albumId=" + albumId);
			} catch (Exception e) {

				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else if (requestedUrl.endsWith("/modify") && request.getParameter("imageId") != null) {
			Image i = validator.getImage();
			i.setId(Integer.valueOf(request.getParameter("imageId")));
			System.out.println("Image id :" + request.getParameter("imageId"));
			try {
				imgDao.updateImage(i);
				Utilisateur u = (Utilisateur) request.getSession().getAttribute("utilisateur");
				response.sendRedirect(request.getContextPath() + "/album/user?userName=" + u.getLogin());
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
