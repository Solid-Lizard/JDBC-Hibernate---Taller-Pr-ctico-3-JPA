package com.persistence;

// IMPORTS // 
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.services.SessionManager;
import java.lang.reflect.ParameterizedType;

/**
 * Clase Abstracta - Implementación común de la interfaz CommonDAOI
 * 
 * @see com.persistence.CommonDAOI
 * 
 * @author Santiago
 */
public abstract class CommonDAOImpl <T> implements CommonDAOI<T>{	
	// PARAMETROS CONSTANTES //	
	// LOG //
	private static final Logger LOG = LoggerFactory.getLogger(CommonDAOImpl.class);

	// Mensajes //
	private static final String LECTURA_SATISFACTORIA = "Lectura realizada satisfactoriamente";
	private static final String REALIZANDO_LECTURA = "Realizando lectura de entidad";

	/** 
	 * Session manager 
	 * @see {@link com.services.SessionManager}
	 */
	protected final SessionManager sm;
	
	
	// Tipo de la clase	
	private Class<T> entityClass;			
		
	// MÉTODOS //
	/**
	 * CommonDAOImpl - Constructor de la clase, inicializa el objeto de tipo "SessionManager"
	 * 
	 * @param sm - SessionManager
	 * 
	 * @see {@link com.services.SessionManager}
	 */
	@SuppressWarnings("unchecked")
	protected CommonDAOImpl (SessionManager sm) {
		
		LOG.info("Inicializando SessionManager y Tipo de la clase");
		
		setEntityClass((Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		LOG.info("Tipo de la clase inicializado satisfactoriamente");
		
		this.sm = sm;
		LOG.info("SessionManager inicializado satisfactoriamente");
	}
	
	// Métodos heredados //
	@Override
	public boolean create(T entity) {					
		LOG.info("Iniciando creación de entidad");
		sm.checkTransaction();
		
		sm.getSession().save(entity);				
		
		sm.getSession().flush();
				
		sm.commitTransaction();		
		
		LOG.info("Entidad creada satisfactoriamente");
						
		return true;															
	}

	@Override
	public List<T> read(int id) {	
		LOG.info(REALIZANDO_LECTURA);
		
		sm.checkTransaction();				
				
		// Construcción de consulta //
		final CriteriaBuilder cb = sm.getSession().getCriteriaBuilder();
		final CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		final Root<T> rootClient = cq.from(getEntityClass());
					
		// Consulta con condiciones de filtrado con Where //				
		cq.select(rootClient).where(cb.equal(rootClient.get("id"), id));
		
		// Volcamos resultados en una lista //
		final List<T> c =  sm.getSession().createQuery(cq).list();
		
		LOG.info(LECTURA_SATISFACTORIA);
	 										
		return c;					
	}
	

	@Override
	public boolean update(T entity) {	
		LOG.info("Actualizando entidad");
		
		sm.checkTransaction();
		
		sm.getSession().saveOrUpdate(entity);
		sm.commitTransaction();				
				
		LOG.info("Actualización de entidad realizada satisfactoriamente");
		
		return true;						
		
	}

	@Override
	public boolean delete(T entity) {	

		LOG.info("Eliminando entidad");
		
		sm.checkTransaction();		
		
		sm.getSession().delete(entity);
		sm.commitTransaction();		
		
		LOG.info("Entidad eliminada satisfactoriamente");
						
		return true;	
	}					
					
	@Override
	public List<T> searchAll() {
		LOG.info(REALIZANDO_LECTURA);		

		final CriteriaBuilder cb = sm.getSession().getCriteriaBuilder();
		final CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		final Root<T> rootClient = cq.from(getEntityClass());
		cq.select(rootClient);
					
		final List<T> c = sm.getSession().createQuery(cq).getResultList();
		
		LOG.info(LECTURA_SATISFACTORIA);
		
		return c;
	}	

	
	// Getters y Setters //
	/**
	 * getEntityClasss - Devuelve el tipo de la clase
	 * @return Class - Tipo de la clase 
	 * 
	 */
	public Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * setEntityClasss - Asigna el tipo de la clase
	 * @param entityClass - Tipo de la clase 
	 * 
	 */
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}	
	
	
}
