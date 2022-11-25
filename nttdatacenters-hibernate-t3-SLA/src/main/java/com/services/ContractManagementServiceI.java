package com.services;

// IMPORTS //
import java.util.List;

import com.persistence.Contract;
import java.time.LocalDate;

/**
 * 
 * ContractManagementServiceI - Interfaz - Interfaz del servicio para la entidad "Contrato"
 * 
 * @see com.persistence.Contract
 * 
 * @author Santiago
 * 
 */
public interface ContractManagementServiceI {	
	// CRUD //
	/**
	 * create - Crea un contrato en la BBD
	 * 
	 * @param contract - Contrato generado
	 * 
	 * @return true - Si el contrato se ha generado correctamente
	 */
	public boolean create(final Contract contract);	
	
	/**
	 * read - Método sobrecargado - Devuelve un contrato con la misma ID
	 * 
	 * @param id - Id del contrato
	 * 
	 * @return Contract - contrato
	 * 
	 */
	public List<Contract> read(int id);				
	
	/**
	 * 
	 * read - Método sobrecargado - Busca y devuelve una lista de contratos con una fecha
	 * de validación.
	 * 
	 * @param validity - Fecha de validación
	 * 
	 * @return List - Lista de contratos
	 *  
	 */
	public List<Contract> read(LocalDate validity);
	
	/**
	 * 
	 * read - Método sobrecargado - Busca y devuelve una lista de contratos 
	 * con un precio mensual designado
	 * 
	 * @param monthPrice - Precio mensual
	 * 
	 * @return List - Lista de contratos
	 *  
	 */
	public List<Contract> read (Double monthPrice);
	
	/**
	 * readByExpiration - Busca y devuelve una lista de contratos
	 * según su fecha de expiración
	 * 
	 * @param expiration - Fecha de expiración
	 * 
	 * @return List - Lista de contratos
	 * 
	 */
	public List<Contract> readByExpiration(LocalDate expiration);
	
	/**
	 * 
	 * reaadByClientId - Devuelve una lista de contratos según la Id
	 * del cliente al que pertenecen
	 * 
	 * @param id - Id del cliente
	 * 
	 * @return List - Lista de contratos
	 * 
	 */
	public List<Contract> readByClientId(int id);
	
	/**
	 * 
	 * readByClientName - Devuelve una lista de contratos según el nombre del
	 * cliente al que pertenecen
	 * 
	 * @param name - Nombre del cliente
	 * 
	 * @return List - lista de contratos
	 * 
	 */
	public List<Contract> readByClientName(String name);
	
	
	/**
	 * 
	 * searchByClientIdandContractBudget - Devuelve una lista de contratos según su
	 * precio mensual y la id del cliente al que pertenecen
	 * 
	 * @param monthPrice - Precio mensual del contrato
	 * 
	 * @param id - Id del cliente
	 * 
	 */
	public List<Contract> readByClientIdandContractMonthPrice(double monthPrice, int id);	
	
	/**
	 * update - Actualiza los datos de un contrato en la BDD
	 * 
	 * @param contract
	 * 
	 * @return boolean - True si los datos se han actualizado correctamente
	 * 
	 */
	public boolean update(final Contract contract);
	
	/**
	 * delete - Elimina els contrato de la BDD
	 * 
	 * @param contract
	 * 
	 * @return boolean - True si los datos se han eliminado correctamente
	 * 
	 */
	public boolean delete(final Contract contract);	

	
	// Otros métodos //
	
	/**
	 * searchAll - Devuelve una lista con todos los contratos de una tabla en la BDD
	 * 
	 * @return List
	 */
	public List<Contract> searchAll();		
	
	
}
