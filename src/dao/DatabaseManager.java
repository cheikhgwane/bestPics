package dao;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import beans.Administrateur;
import beans.Album;
import beans.Image;
import beans.Utilisateur;

public class DatabaseManager {
	private static SessionFactory sessionFactory;
	
	public DatabaseManager() {
		
	}
	public static SessionFactory getDatabaseManager() {
		if(sessionFactory == null) {
			try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/bestPics?useSSL=false");
                settings.put(Environment.USER, "admin");
                settings.put(Environment.PASS, "passer123");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                
                configuration.addAnnotatedClass(Utilisateur.class);
                configuration.addAnnotatedClass(Administrateur.class);
                configuration.addAnnotatedClass(Album.class);
                configuration.addAnnotatedClass(Image.class);
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
		return sessionFactory;
	}
	

}
