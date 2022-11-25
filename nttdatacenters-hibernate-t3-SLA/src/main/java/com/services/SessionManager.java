package com.services;

// IMPORTS //
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * SessionManager -Ggestiona el flujo de la sesión Hibernate de la aplicación
 * @author Santiago
 */
/**
 * @author Santiago
 *
 */
public class SessionManager {
	// OBJETOS //
	// Log //
	private static final Logger LOG = LoggerFactory.getLogger(SessionManager.class);
	
	// Sesion //
	private Session session;
	private SessionFactory sessionFactory;	
	
	// Configuracion //
	private Configuration conf;
	
	
	// MÉTODOS //	
	// Constructor //
	
	/**
	 * SessionManager - Constructor de la aplicación
	 * Inicializa la configuración de Hibernate
	 */
	public SessionManager() {
		LOG.info("Iniciando constructor de la aplicación");
		
		conf =  new Configuration();			
	    sessionFactory =conf.configure().buildSessionFactory();  
	    
	    LOG.info("Configuración generada correctamente");
	}
	
	// Control de sesión //	
	/**
	 * openSession - Inicia la sesión y la
	 * transacción con la base de datos
	 * 
	 */
	public void openSession () {
		LOG.info("Iniciando sesión y comenzando transaccion");
		session = sessionFactory.openSession();
		LOG.info("Sesión abierta correctamente");
		
		session.beginTransaction();
		LOG.info("Transacción abierta correctamete");
	}
	
	/**
	 * closeSession - Cierra la sesión con la BDD
	 */
	public void closeSession() {
		LOG.info("Cerrando sesión");
		session.close();	
		LOG.info("Sesion cerrada correctamente");
	}
		
	
	// Control de transacciones //
	/**
	 * checkTransaction - Comprueba si la transacción está abierta,
	 * en caso negativo, abre la transacción
	 */
	public void checkTransaction() {
		if (!this.session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
	}
	
	/**
	 * commitTransaction -Confirma la 
	 * transacción y realiza los cambios en la BDD 
	 */
	public void commitTransaction () {
		LOG.info("Confirmando transacción");
		session.getTransaction().commit();			
		LOG.info("Transacción confirmada correctamente");
	}
	
	// GETTERS Y SETTERS //		
	/**
	 * getSession - Devuelve sesión de la BDD
	 * @return Session - Sesión de la BDD
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * setSession - Establece la sesión de la BDD
	 * @param session - Sesión de la BDD
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * getSessionFactory - Devuelve la interfaz "SessionFactory" de la clase
	 * @return SessionFactory - Interfaz SessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * setSessionFactory - Asigna la interfaz "SessionFactory" de la clase
	 * @param sessionFactory - Interfaz SessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;	
	}

	/**
	 * getConf - Devuelve el objeto Configuration de la clase
	 * @return Configuration - Objeto Configuration
	 */
	public Configuration getConf() {
		return conf;
	}

	/**
	 * setConf - Asigna el objeto Configuration de la clase
	 * @param conf - Objeto Configuration
	 */
	public void setConf(Configuration conf) {
		this.conf = conf;
	}
	
}
