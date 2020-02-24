package dao;

import java.util.ArrayList;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Utilisateur;

public class UtilisateurDao {

	public UtilisateurDao() {

	}

	public int addUtilisateur(Utilisateur su) throws DaoException {
		Transaction tx = null;
		try (Session session = DatabaseManager.getDatabaseManager().openSession()) {
			if (su != null) {
				System.out.println(su.getPrenom());
				tx = session.beginTransaction();
				session.save(su);
		        tx.commit();
			}
		} catch (Exception e) {
			 tx.rollback();
			throw new DaoException(e.getMessage());
		}
		return 0;
	}
	
	public int updateUser(Utilisateur su) throws DaoException {
		Transaction tx = null;
		try (Session session = DatabaseManager.getDatabaseManager().openSession()) {
			if (su != null) {
				System.out.println(su.getId()+"-"+su.getLogin());
				tx = session.beginTransaction();
				if(findUser(su.getId())!=null) {
					System.out.println(su.getLogin());
					Query q = session.createQuery("Update Utilisateur set nom=:nom,prenom=:prenom,login=:login where login=:userlogin");
					q.setParameter("nom", su.getNom());
					q.setParameter("prenom", su.getPrenom());
					System.out.println(su.getPrenom());
					q.setParameter("login", su.getLogin());
					q.setParameter("userlogin", su.getLogin());
					q.executeUpdate();
				}
		        tx.commit();
		        return 0;
			}
		} catch (Exception e) {
			 tx.rollback();
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}
		return 0;
	}

	public Utilisateur findUser(int id) throws DaoException {
		try (Session session = DatabaseManager.getDatabaseManager().openSession()) {
			Query q = session.createQuery("from Utilisateur u where u.id=:id");
			q.setParameter("id", id);
			return (Utilisateur) q.getResultList().get(0);
		} catch (Exception r) {
			throw new DaoException("user not found" + r.getMessage());
		}
	}

	public int deleteUser(int userId) throws DaoException {
		Transaction tx = null;
		try (Session session = DatabaseManager.getDatabaseManager().openSession()) {
			if (userId != 0) {
				tx = session.beginTransaction();
				Utilisateur u = session.find(Utilisateur.class,userId);
				session.remove(u);
				 tx.commit();
				return 0;
			}
			System.out.println("No user given");
			return -1;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	public ArrayList<Utilisateur> getUsers() throws DaoException {
		try (Session session = DatabaseManager.getDatabaseManager().openSession()) {
			return (ArrayList<Utilisateur>) session.createQuery("from Utilisateur", Utilisateur.class).list();

		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}
}
