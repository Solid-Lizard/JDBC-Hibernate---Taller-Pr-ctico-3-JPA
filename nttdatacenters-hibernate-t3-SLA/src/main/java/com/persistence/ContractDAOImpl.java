package com.persistence;

import java.util.Date;
// IMPORTS //
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.services.SessionManager;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * 
 * ContractDAOImpl - Clase - Implementación DAO de la interfaz CommonDAOImpl para la entidad "Contrato"
 * clase hija de CommonDAOImpl
 * 
 * @see com.persistence.Contract
 * @see com.persistence.ContractDAOI
 * @see com.persistence.CommonDAOImpl
 * 
 * @author Santiago
 * 
 */
public class ContractDAOImpl extends CommonDAOImpl<Contract> implements ContractDAOI{
	
	private static final String CONTRATOS_BUSCADOS_OK = "Contratos buscados correctamente, devueltos: {}";

	private static final String CLIENT = "client";
	
	// LOG //
	private static final Logger LOG = LoggerFactory.getLogger(ContractDAOImpl.class);
	
	// Constructor //
	public ContractDAOImpl(SessionManager sm) {
		super(sm);		
	}	
	
	// CRUD //
	@Override
	public boolean create(Contract contract) {
		LOG.debug("Insertando contrato: {}", contract);
		
		if (super.create(contract)) {
			LOG.debug("Contrato insertado correctamente");
			return true;
			
		} else {
			LOG.debug("Error al insertar el contrato");
			return false;
		}
	}

	@Override
	public List<Contract> read(int id) {
		LOG.debug("Buscando contrato: {}", id);
		
		final List<Contract> c = super.read(id);
		
		LOG.debug("Contrato buscado correctamente");
		
		return c;
	}	

	@Override
	public List<Contract> read(LocalDate validity) {
		LOG.debug("Buscando contratos por fecha de validez: {}", validity);
		
		// Parseamos de "LocalDate" a "Date" //
		final ZoneId defaultZoneId = ZoneId.systemDefault();		
		final Date date = Date.from(validity.atStartOfDay(defaultZoneId).toInstant());
		
		// Construcción de consulta //
		final CriteriaBuilder cb = sm.getSession().getCriteriaBuilder();
		final CriteriaQuery<Contract> cq = cb.createQuery(Contract.class);
		final Root<Contract> rootContract = cq.from(Contract.class);
		
		// Consulta con condiciones de filtrado con Where //
		cq.select(rootContract).where(cb.equal(rootContract.<Date> get("validity"), date));
				
		// Volcamos los resultados en una lista //
		final List<Contract> c = sm.getSession().createQuery(cq).list();		
		
		LOG.debug(CONTRATOS_BUSCADOS_OK, c);
		
		return c;
	}

	@Override
	public List<Contract> read(Double monthPrice) {
		LOG.debug("Buscando contratos según precio mensual: {}", monthPrice);		
		
		// Construcción de consulta //
		final CriteriaBuilder cb = sm.getSession().getCriteriaBuilder();
		final CriteriaQuery<Contract> cq = cb.createQuery(Contract.class);
		final Root<Contract> rootContract = cq.from(Contract.class);
		
		// Consulta con condiciones de filtrado con Where //
		cq.select(rootContract).where( cb.equal(rootContract.<String> get("monthPrice"), monthPrice));

		// Volcamos los resultados en una lista //
		final List<Contract> c = sm.getSession().createQuery(cq).list();
		
		LOG.debug("Contratos buscados correctamente, devueltos {}", c);
			
		return c;
	}
	
	@Override
	public List<Contract> readByExpiration(LocalDate expiration) {
		LOG.debug("Buscando contratos por fecha de expiración: {}", expiration);
		
		// Parseamos de "LocalDate" a "Date" //
		final ZoneId defaultZoneId = ZoneId.systemDefault();		
		final Date date = Date.from(expiration.atStartOfDay(defaultZoneId).toInstant());
		
		// Construcción de consulta //
		final CriteriaBuilder cb = sm.getSession().getCriteriaBuilder();
		final CriteriaQuery<Contract> cq = cb.createQuery(Contract.class);
		final Root<Contract> rootContract = cq.from(Contract.class);
		
		// Consulta con condiciones de filtrado con Where //
		cq.select(rootContract).where(cb.equal(rootContract.<Date> get("expiration"), date));
				
		// Ejecutamos y devolvemos los resultados en una lista //
		final List<Contract> c = sm.getSession().createQuery(cq).list();
				
		
		LOG.debug(CONTRATOS_BUSCADOS_OK, c);
		
		return c;
	}

	@Override
	public List<Contract> readByClientId(int id) {
		LOG.debug("Buscando contratos por id del cliente: {}", id);
		
		// Construcción de consulta //
		final CriteriaBuilder cb = sm.getSession().getCriteriaBuilder();
		final CriteriaQuery<Contract> cq = cb.createQuery(Contract.class);
		final Root<Contract> rootContract = cq.from(Contract.class);
		
		// Consulta con condiciones de filtrado con Where //
		cq.select(rootContract).where(cb.equal(rootContract.<Double> get(CLIENT), id));
				
		// Ejecutamos la consulta y volcamos los resultados //
		final List<Contract> c = sm.getSession().createQuery(cq).list();

		LOG.debug(CONTRATOS_BUSCADOS_OK, c);
		
		return c;
	}
	
	@Override
	public List<Contract> readByClientIdandContractMonthPrice (double monthPrice, int id) {
		// Construimos la consulta //
		final CriteriaBuilder cb = sm.getSession().getCriteriaBuilder();
		final CriteriaQuery<Contract> cq = cb.createQuery(Contract.class);
		final Root<Contract> rootContract = cq.from(Contract.class);		

		// Añadimos condiciones de filtrado (Where) //	
		final Predicate p1 = cb.equal(rootContract.<Double> get("monthPrice"), monthPrice);
		final Predicate p2 = cb.equal(rootContract.<Integer> get(CLIENT), id);
		
		final Predicate[] predicates = { p1, p2 };

		// Consulta //
		cq.select(rootContract).where(predicates);

		// Ordenamos de forma descendente según la id //
		cq.orderBy(cb.desc(rootContract.get(CLIENT)));
		
		// Ejecutamos y devolvemos los resultados //
		return sm.getSession().createQuery(cq).getResultList();	
	}

	@Override
	public List<Contract> readByClientName(String name) {
		
		LOG.debug("Buscando contrato por nombre de cliente: {}", name);
		
		// Construcción de consulta //
		final CriteriaBuilder cb = sm.getSession().getCriteriaBuilder();
		final CriteriaQuery<Contract> cq = cb.createQuery(Contract.class);
		final Root <Contract> rootContract = cq.from(Contract.class);
		
		// Unimos ambas tablas (Join) //
		final Join<Contract,Client> pJoinT = rootContract.join(CLIENT);

		// Condiciones de filtrados con where //
		final Predicate p1 = cb.like(pJoinT.<String> get("name"), name);

		// Consulta //
		cq.select(rootContract).where(p1);
		
		// Volcamos resultados en una lista //
		final List<Contract> c = sm.getSession().createQuery(cq).list();
		
		LOG.debug(CONTRATOS_BUSCADOS_OK, c);
		
		return c;

	}		


	@Override
	public boolean update(Contract contract) {
		LOG.debug("Actualizando contrato: {}", contract.getId());
		
		if (super.update(contract)) {
			LOG.debug("Contrato actualizado satisfactoriamente");
			
			return true;
		} else {
			LOG.error("Error al actualizar al contrato");
			
			return false;
		}
	}



	@Override
	public boolean delete(Contract contract) {
		LOG.debug("Eliminando contrato: {}", contract.getId());
		
		if (super.delete(contract)) {
			LOG.debug("Contrato eliminado satisfactoriamente");
			
			return true;
		} else {
			LOG.error("Error al eliminar el contrato");
			
			return false;
		}
	}

	@Override
	public List<Contract> searchAll() {
		LOG.debug("Obteniendo lista de contratos");
		
		final List<Contract> contracts = super.searchAll();
		
		LOG.debug("Lista de contratos obtenida satisfactoriamente: {}", contracts);
		
		return contracts;
	}	
	
}
