package utils;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class ImageUtils {
	private HttpServletRequest request;
	private Part filePart;
	private InputStream inputStream;
	
	public ImageUtils(HttpServletRequest req) {
		this.request = req;
	}
	
	public InputStream uploadImage(String imageName) throws IOException, ServletException {
		this.filePart = request.getPart(imageName);
		if(filePart!=null) {
			//this.getImgInfo();
			this.inputStream = filePart.getInputStream();
			return this.inputStream;
		}
		return null;
	}
	
	/*public OutputStream readImage(String path) {
		
	}
	*/
	public String getImageName() {
		return this.filePart!=null ?  this.filePart.getSubmittedFileName() : null;
	}
	
	public long getFileSize() {
		return this.filePart != null ?  this.filePart.getSize() : -1;
	}
	
	public void getImgInfo() {
		if(this.filePart != null) {
			System.out.println(filePart.getSubmittedFileName());
			System.out.println(filePart.getContentType());
			System.out.println(filePart.getSize());
		}else {
			System.out.println("File not present");
		}
	}
}
