package com.persistence;

import java.util.List;

/**
 * 
 * ClientDAOI - Interfaz - Interfaz DAO para la entidad "Cliente"
 * 
 * @author Santiago 
 * 
 * @see com.persistence.CommonDAOI
 * 
 * 
 */
public interface ClientDAOI extends CommonDAOI<Client>{
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
	
}
