package dao;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Album;
import beans.Utilisateur;

public class AlbumDao {
	public AlbumDao() {

	}

	public int createAlbum(Album album, Utilisateur user) throws DaoException {
		Transaction tx = null;
		try (Session session = DatabaseManager.getDatabaseManager().openSession()) {
			if (album != null) {
				album.setProprietaire(user);
				tx = session.beginTransaction();
				session.saveOrUpdate(album);
				tx.commit();
				return 0;
			}
			return -1;
		} catch (Exception e) {
			tx.rollback();
			throw new DaoException(e.getMessage());
		}
	}
	
	public int updateAlbum(Album newAlbum) throws DaoException {
		Transaction tx = null;
		try(Session session = DatabaseManager.getDatabaseManager().openSession()){
			tx = session.beginTransaction();
			ArrayList<Album> albums = getAllAlbum();
			for(Album a : albums) {
				if(a.getId() == newAlbum.getId()) {
					a.setAlbumUrl(newAlbum.getAlbumUrl());
					a.setPrivateAlbum(newAlbum.isPrivateAlbum());
					a.setNomAlbum(newAlbum.getNomAlbum());
					//System.out.println("Nom url : " +a.getNomAlbum()+" private album : "+a.isPrivateAlbum()+" albumCoverUrl : "+a.getAlbumUrl());
					Query q = session.createQuery("Update Album set nomAlbum=:nomAlbum, albumUrl=:albumUrl, privateAlbum=:isPrivate where id=:id");
					q.setParameter("nomAlbum",newAlbum.getNomAlbum());
					q.setParameter("albumUrl", newAlbum.getAlbumUrl());
					q.setParameter("isPrivate", newAlbum.isPrivateAlbum());
					q.setParameter("id", newAlbum.getId());
					int res = q.executeUpdate();
					tx.commit();
					return res;
				}
			}
			session.close();
		  return 200;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}
	}

	public ArrayList<Album> getAlbums(int userId) throws DaoException {
		try (Session session = DatabaseManager.getDatabaseManager().openSession()) {
			
			Query q = session.createQuery("from Album a where a.proprietaire.id=:userId ");
			q.setParameter("userId", userId);
			@SuppressWarnings("unchecked")
			ArrayList<Album> r = (ArrayList<Album>) q.getResultList();
			return r;

		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	public ArrayList<Album> getAllAlbum() throws DaoException {
		try (Session session = DatabaseManager.getDatabaseManager().openSession()) {
			return (ArrayList<Album>) session.createQuery("from Album", Album.class).list();

		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}

	}

	public int deleteAlbum(Album album) throws DaoException {
		Transaction tx = null;

		try (Session session = DatabaseManager.getDatabaseManager().openSession()) {
			tx = session.beginTransaction();
			session.delete(album);
			tx.commit();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			throw new DaoException(e.getMessage());
		}
	}

	// public int updateAlbum(Album)

}
