package utils;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class ImageUtils {
	private HttpServletRequest request;
	private Part filePart;
	public static final String UPLOAD_DIR = "C:\\UploadedImg";

	public ImageUtils(HttpServletRequest req) {
		this.request = req;
	}

	public void uploadImage(String imageName) throws IOException, ServletException {
		System.out.println("Filename" + imageName);
		this.filePart = request.getPart(imageName);

		if (filePart != null) {
			try {
				//create directory
				File f = new File(UPLOAD_DIR);
				if (!f.exists()) {
					f.mkdir();
				}

				DataInputStream inputStream = new DataInputStream(this.filePart.getInputStream());
				byte[] buffer = new byte[(int) this.filePart.getSize()];
				inputStream.read(buffer);
			//	this.getImgInfo();
				File targetFile = new File(UPLOAD_DIR + "\\" + this.filePart.getSubmittedFileName());

				DataOutputStream outStream = new DataOutputStream(new FileOutputStream(targetFile));

				outStream.write(buffer);
				outStream.close();
				System.out.println("file size : " + this.filePart.getSize());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public byte[] readImage(String imageUrl) throws IOException {
		File f = new File(UPLOAD_DIR + "\\" + imageUrl);
		
		if (f.exists()) {
			DataInputStream input = null;
			try {
				input = new DataInputStream(new FileInputStream(f));
				byte[] buffer = new byte[(int) f.length()];

				input.read(buffer);
				input.close();
				return buffer;

			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}
		return null;
	}

	public String getImageName() {
		return this.filePart != null ? this.filePart.getSubmittedFileName() : null;
	}

	public long getFileSize() {
		return this.filePart != null ? this.filePart.getSize() : -1;
	}

	public long getFileSize(String path) {
		File f = new File(UPLOAD_DIR + "\\" + path);
		if (f.exists()) {
			return f.length();
		}
		return -1;
	}

	public Dimension getFileDemension(String fileName) {
		File targetFile = new File(UPLOAD_DIR + "\\" + fileName);
		if (!targetFile.canRead()) {
			System.out.println("Cannot read file");
		}
		try {
			BufferedImage bimg = ImageIO.read(targetFile);
			return new Dimension(bimg.getWidth(), bimg.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void getImgInfo() {
		if (this.filePart != null) {
			System.out.println(filePart.getSubmittedFileName());
			System.out.println(filePart.getContentType());
			System.out.println(filePart.getSize());
		} else {
			System.out.println("File not present");
		}
	}
}