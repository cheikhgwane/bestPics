package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Album;
import beans.Image;

public class ImageDao {
	public ImageDao() {

	}

	public int createImage(Image i) throws DaoException {
		Transaction tx = null;
		try (Session session = DatabaseManager.getDatabaseManager().openSession()) {
			if (i != null) {
				tx = session.beginTransaction();
				session.saveOrUpdate(i);
				;
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			tx.rollback();
			throw new DaoException(e.getMessage());
		}
		return 0;
	}
	
	public ArrayList<Image> getImage() throws DaoException {
		try (Session session = DatabaseManager.getDatabaseManager().openSession()) {
			return (ArrayList<Image>) session.createQuery("from Image",Image.class).list();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}
	}
	
	public Image getOne(int imageId) throws DaoException {
		try(Session s = DatabaseManager.getDatabaseManager().openSession()){
			Query q = s.createQuery("From Image i where i.id=:id");
			q.setParameter("id", imageId);
			return (Image)q.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}
	}
	public int deleteImage(Image i) throws DaoException {
		Transaction tx = null;
		try (Session s = DatabaseManager.getDatabaseManager().openSession()) {
			if (i != null) {
				tx = s.beginTransaction();
				s.delete(i);
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			tx.rollback();
			throw new DaoException(e.getMessage());
		}
		return 0;
	}
	
	public Object updateImage(Image i)throws DaoException{
		Transaction tx = null;
		try(Session session = DatabaseManager.getDatabaseManager().openSession()){
		 if(i!=null) {
			 Date d = new Date();
			 i.setDateMaj(d);
			 tx = session.beginTransaction();
			 Query q = session.createQuery("Update Image set description =:desc,dateMaj =:dateMaj,keywords=:keywords,titre=:titre where id=:id");
			 q.setParameter("desc", i.getDescription());
			 q.setParameter("dateMaj",i.getDateMaj());
			 q.setParameter("keywords", i.getKeywords());
			 q.setParameter("titre", i.getTitre());
			 q.setParameter("id", i.getId());
			 q.executeUpdate();
			 tx.commit();
			 return i;
		 }
		}catch(Exception e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}
		return null;
	}
	//todo fix this method
	public List<Image> loadImages(int albumId) throws DaoException {
		try(Session s = DatabaseManager.getDatabaseManager().openSession();) {
			
			Query q = s.createQuery("From Image i");
			q.setParameter("albumId", albumId);
			Album a = (Album)q.getResultList().get(0);
			s.close();
			return a.getImages();
			} catch (Exception e) {
				
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}
	}
}
