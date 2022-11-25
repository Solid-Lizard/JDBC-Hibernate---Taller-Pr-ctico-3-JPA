package com.persistence;

//IMPORTS //
import java.time.LocalDate;
import java.util.List;

/**
 * 
 * ContractDAOI - Interfaz - Interfaz DAO para la entidad "Contrato"
 * implementa la entidad CommonDAOI
 * 
 * @author Santiago
 * 
 * @see com.persistence.Contract
 * @see com.persistence.CommonDAOI
 * 
 */

public interface ContractDAOI extends CommonDAOI<Contract> {	
	// Métodos read específicos para la enitdad Contrato //
	
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
	
		
}
