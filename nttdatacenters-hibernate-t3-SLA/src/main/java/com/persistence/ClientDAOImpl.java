package com.persistence;


// IMPORTS //
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.services.SessionManager;

/**
 * ClientDAOImpl - Implementación DAO para la entidad "Cliente" de la interfaz ClientDAOI
 * 
 * 
 * @see com.persistence CommonDAOImpl
 * @see com.persistence.ClientDAOI
 * 
 * @author Santiago
 */
public class ClientDAOImpl extends CommonDAOImpl<Client> implements ClientDAOI{			

	// OBJETOS Y CONSTANTES //
	private static final String CLIENTE_DEVUELTO_OK = "Cliente/s buscado/s correctamente, cliente/s devuelto/s: {}";
		
	// Logger //
	private static final Logger LOG = LoggerFactory.getLogger(ClientDAOImpl.class);
	
	
	// MÉTODOS //
	
	/**
	 * ClientDAOImpl - Constructor, asigna el SessionManager
	 * @param sm
	 */
	public ClientDAOImpl(SessionManager sm) {
		super(sm);			
	}
	
	// Override Methods //
	
	// CRUD //
	@Override
	public boolean create(Client client) {
		LOG.debug("Insertando cliente: {}", client);
		
		if (super.create(client)) {			
			LOG.debug("Cliente insertado correctamente");
			return true;
			
		} else {
			LOG.error("Error al insertar al cliente");
			return false;
		}
			
	}	
	
	@Override
	public List<Client> read(String name) {
		LOG.debug("Buscando cliente por nombre: {}", name);
		
		// Construcción de consulta //
		final CriteriaBuilder cb = sm.getSession().getCriteriaBuilder();
		final CriteriaQuery<Client> cq = cb.createQuery(Client.class);		
		final Root<Client> rootClient = cq.from(Client.class);
		
		// Consulta con condiciones de filtrado //
		cq.select(rootClient).where(cb.like(rootClient.<String>get("name"), name));
	
		// Volcamos los resultados en una lista //			
		final List<Client> c = sm.getSession().createQuery(cq).list();

		LOG.debug (CLIENTE_DEVUELTO_OK, c);
		
		return c;
	}

	@Override
	public List<Client> read(int id) {
		LOG.debug("Buscando cliente por ID: {}", id);
		
		final List<Client> c = super.read(id); 
		
		LOG.debug (CLIENTE_DEVUELTO_OK, c);
		
		return c;
	}
	
	@Override
	public List<Client> read(String name, String surname1) {	
		LOG.debug("Buscando cliente por Nombre y Apellido1: {} {}", name, surname1);						
		// Construcción de consulta //
		final CriteriaBuilder cb = sm.getSession().getCriteriaBuilder();
		final CriteriaQuery<Client> cq = cb.createQuery(Client.class);
		final Root<Client> rootClient = cq.from(Client.class);
		
		// Condiciones de filtrado (Where) //
		final Predicate p1 = cb.like(rootClient.<String> get("name"), name);
		final Predicate p2 = cb.like(rootClient.<String> get("firstSurname"), surname1);
		
		final Predicate[] predicates = {p1, p2};
		
		// Consulta con condiciones de filtrado //
		cq.select(rootClient).where(predicates);
				
		// Volcamos los resultados en una lista//
		final List<Client> c = sm.getSession().createQuery(cq).list();				
	
		LOG.debug(CLIENTE_DEVUELTO_OK, c);
		
		return c;
	}

	@Override
	public boolean update(Client client) {
		LOG.debug("Actualizando cliente: {}", client.getDni());
		
		if (super.update(client)) {
			LOG.debug("Cliente actualizado correctamente");
			return true;
			
		} else {
			LOG.debug("Error al actualizar al cliente");
			return false;
		}
		
	}

	@Override
	public boolean delete(Client client) {
		LOG.debug("Eliminando cliente: {}", client.getDni());
		
		if (super.delete(client)) {			
			LOG.debug("Cliente eliminado correctamente");	
			return true;
			
		} else {
			LOG.debug("Error al eliminar cliente}");
			return false;
		}
	}

	// Otros métodos //
	@Override
	public List<Client> searchAll() {
		LOG.debug("Obteniendo lista de clientes");
					
		final List<Client> c = super.searchAll();
		
		LOG.debug("Lista de clientes obtenida satisfactoriamente: {}", c);
		
		return c;
	}		

}
