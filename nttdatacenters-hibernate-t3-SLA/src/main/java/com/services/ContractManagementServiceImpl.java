package com.services;

import java.time.LocalDate;

// IMPORTS //
import java.util.List;
import com.persistence.Contract;
import com.persistence.ContractDAOImpl;

/**
 * 
 * ContractManagementServiceImpl - Servicio implementación de la interfaz "ContractManagementServiceImpl"
 * para la entidad "Contrato"
 * 
 * <br>
 * <br>
 * 
 * Utiliza los métodos de ContractDAOImpl para realizar las operaciones CRUD con la entidad "Contract"
 * 
 * @author Santiago
 * 
 * @see com.persistence.Contract
 * @see com.persistence.ContractDAOImpl
 * @see com.services.ClientManagementServiceI
 * 
 */
public class ContractManagementServiceImpl implements ContractManagementServiceI{

	//ContractDAOImpl para utilizar sus métodos ya definidos
	final ContractDAOImpl contractDao;
	
	/**
	 * ContractManagementServiceImpl - Método constructor, asigna el ContractDAOImpl
	 * 
	 *  @param sm - SessionManager - SessionManager que se asigna al constructor de ContractDAOImpl
	 */
	public ContractManagementServiceImpl(SessionManager sm) {
		contractDao = new ContractDAOImpl(sm);
	}
	
	// Override Methods //
	@Override
	public boolean create(Contract contract) {		
		return contractDao.create(contract);
	}

	@Override
	public List<Contract> read(int id) {		
		return contractDao.read(id);
	}

	@Override
	public List<Contract> read(LocalDate validity) {
		return contractDao.read(validity);
	}

	@Override
	public List<Contract> read(Double monthPrice) {
		return contractDao.read(monthPrice);
	}

	@Override
	public List<Contract> readByExpiration(LocalDate expiration) {
		return contractDao.readByExpiration(expiration);
	}

	@Override
	public List<Contract> readByClientId(int id) {
		return contractDao.readByClientId(id);
	}
	
	@Override
	public List<Contract> readByClientName(String name) {
		return contractDao.readByClientName(name);
	}
	
	@Override
	public List<Contract> readByClientIdandContractMonthPrice(double budget, int id) {
		return contractDao.readByClientIdandContractMonthPrice(budget, id);
	}

	@Override
	public boolean update(Contract contract) {		
		return contractDao.update(contract);
	}

	@Override
	public boolean delete(Contract contract) {		
		return contractDao.delete(contract);
	}

	@Override
	public List<Contract> searchAll() {
		return contractDao.searchAll();
	}

}
