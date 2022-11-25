package com.services;

// IMPORTS // 
import java.util.List;
import com.persistence.Client;
import com.persistence.ClientDAOImpl;

/**
 * 
 * ClientManagementServiceImpl - Servicio implementación de la interfaz "ClientManagementServiceI"
 * para la entidad "Cliente"
 * 
 * <br>
 * <br>
 * 
 * Utiliza los métodos de ClientDAOImpl para realizar las operaciones CRUD con la entidad "Client" 
 * 
 * @author Santiago
 * 
 * @see com.persistence.Client
 * @see com.persistence.ClientDAOImpl
 * @see com.services.ClientManagementServiceI
 * 
 */
public class ClientManagementServiceImpl implements ClientManagementServiceI {
	
	// ClienteDAOImpl para utilizar sus métodos ya definidos
	private final ClientDAOImpl clientDAO;
	
	/**
	 * ClientManagementServiceImpl - Método constructor, asigna el ClientDAOImpl.
	 * 
	 * @param sm - SessionManager - SessionManager que se asigna al constructor de
	 * ClientDAOImpl
	 */
	public ClientManagementServiceImpl(SessionManager sm) {
		clientDAO = new ClientDAOImpl(sm);
	}
	
	
	// Override Methods //
	@Override
	public boolean create(Client client) {	
		return clientDAO.create(client);
	}

	@Override
	public List<Client> read(int id) {
		return clientDAO.read(id);
	}

	@Override
	public List<Client> read(String name) {
		return clientDAO.read(name);
	}

	@Override
	public List<Client> read(String name, String surname1) {
		return clientDAO.read(name, surname1);
	}	

	@Override
	public boolean update(Client client) {
		return clientDAO.update(client);
	}

	@Override
	public boolean delete(Client client) {
		return clientDAO.delete(client);
	}

	@Override
	public List<Client> searchAll() {
		return clientDAO.searchAll();			
	}

}
