package servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
	private static final String IMAGE_VIEW = "/WEB-INF/images.jsp";
	private static final String IMAGE_INFO = "/WEB-INF/imgInfo.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedUrl = request.getRequestURI();
		String imageUrl = null;

		ImageDao dao = new ImageDao();

		if (requestedUrl.endsWith("/image/imgInfo")) {
			int id = Integer.valueOf(request.getParameter("imageId"));
			try {
				ArrayList<Image> images = dao.getImage();

				for (Image i : images) {
					if (i.getId() == id) {
						request.setAttribute("image", i);
						getServletContext().getRequestDispatcher(IMAGE_INFO).forward(request, response);
						break;
					}
				}
			} catch (DaoException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		} else if (requestedUrl.endsWith("/image/getOne")) {
			System.out.println("inside image servlet");

			if ((imageUrl = getImageUrl(request)) != null) {

				File f = new File(AlbumServlet.UPLOAD_DIR + "\\" + imageUrl);

				if (f.exists()) {

					DataInputStream input = new DataInputStream(new FileInputStream(f));

					byte[] buffer = new byte[(int) f.length()];

					input.read(buffer);

					ServletContext cntx = request.getServletContext();
					String mime = cntx.getMimeType(f.getPath());

					response.setContentLength((int) f.length());
					response.setContentType(mime);

					response.getOutputStream().write(buffer);
					input.close();
				}

			}
		} else if (requestedUrl.endsWith("/image/")) {
			// tous les images dans un album
			// si l'utilisateur fait partie du groupe granted ou que l'album est public
			// ou bien que le current user est le proprio de l'album

		} else if (requestedUrl.endsWith("/image/all")) {// tous les images appartenant à des repos public

		} else if (requestedUrl.endsWith("/delete")) {
			ImageDao imgDao = new ImageDao();

			int id = Integer.valueOf(request.getParameter("imageId"));
			ArrayList<Image> images;
			try {
				images = imgDao.getImage();
				for (Image i : images) {
					if (i.getId() == id) {
						imgDao.deleteImage(i);
						break;
					}
				}
			} catch (DaoException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

			response.sendRedirect(request.getContextPath() + "/album");
		}
	}

	public String getImageUrl(HttpServletRequest request) {
		String imageId = request.getParameter("imageUrl");
		return imageId != null ? imageId : null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AlbumDao dao = new AlbumDao();

		String requestedUrl = request.getRequestURI();

		FormValidator validator = new FormValidator(request);

		ImageUtils imgUtils = new ImageUtils(request);

		ImageDao imgDao = new ImageDao();

		if (requestedUrl.endsWith("/add")) {
			try {

				Image i = validator.getImage();

				File f = new File(AlbumServlet.UPLOAD_DIR);
				if (!f.exists()) {
					f.mkdir();
				}

				DataInputStream inputStream = new DataInputStream(imgUtils.uploadImage("imageFile"));
				byte[] buffer = new byte[(int) imgUtils.getFileSize()];
				inputStream.read(buffer);

				String imgName = imgUtils.getImageName();

				File targetFile = new File(AlbumServlet.UPLOAD_DIR + "\\" + imgName);
				String _id = request.getParameter("albumId");

				int albumId = Integer.valueOf(_id);

				System.out.println(" inside image servlet idAlbum : " + albumId);

				ArrayList<Album> albums = dao.getAllAlbum();

				for (Album a : albums) {
					if (a.getId() == albumId) {
						i.setAlbum(a);
						break;
					}
				}

				Date date = new Date();
				i.setDateCreation(date);
				// System.out.println(targetFile.getAbsoluteFile());

				DataOutputStream outStream = new DataOutputStream(new FileOutputStream(targetFile));
				i.setFileImage(imgUtils.getImageName());

				outStream.write(buffer);
				outStream.close();

				imgDao.createImage(i);
				response.sendRedirect(request.getContextPath() + "/album/albumId" + albumId);
			} catch (Exception e) {

				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
