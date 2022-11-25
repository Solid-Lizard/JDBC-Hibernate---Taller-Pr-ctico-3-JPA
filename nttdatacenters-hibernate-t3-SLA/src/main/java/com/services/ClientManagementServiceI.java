package com.services;

// IMPORTS //
import java.util.List;
import com.persistence.Client;

/**
 * ClientManagementServiceI - Interfaz - Interfaz del servicio para la entidad "Cliente"
 * 
 * @see com.persistence.Client
 * 
 * @author Santiago
 */
public interface ClientManagementServiceI {
	// Métodos CRUD //
	
	/**
	 * create - Crea un cliente en la BBD
	 * 
	 * @param client 
	 * 
	 * @return true - Si el cliente se ha generado correctamente
	 */
	public boolean create(final Client client);
	
	/**
	 * read - Método sobrecargado - Devuelve un Cliente con la misma ID
	 * 
	 * @param id - Id del cliente
	 * 
	 * @return Client
	 * 
	 */
	public List<Client> read(int id);
	
	/**
	 * read - Método sobrecargado - Devuelve una lista de clientes con el mismo nombre
	 * 
	 * @param name
	 * 
	 * @return List - Lista de clientes
	 * 
	 */
	public List<Client> read(String name);	
	
	/**
	 * read - Método sobrecargado - Devuelve una lista de clientes con el mismo nombre y apellido1
	 * 
	 * @param name
	 * @param surname1
	 * 
	 * @return List - Lista de clientes
	 * 
	 */
	public List<Client> read(String name, String surname1);	
	
	/**
	 * update - Actualiza los datos de un cliente en la BDD
	 * 
	 * @param client
	 * 
	 * @return boolean - True si los datos se han actualizado correctamente
	 * 
	 */
	public boolean update(final Client client);
	
	/**
	 * delete - Elimina el cliente de la BDD
	 * 
	 * @param client
	 * 
	 * @return boolean - True si los datos se han eliminado correctamente
	 * 
	 */
	public boolean delete(final Client client);
	
	// Otros métodos //
	/**
	 * searchAll - Devuelve una lista con todos los clientes de la BDD
	 * 
	 * @return List
	 */	
	public List<Client> searchAll();	
	
}
