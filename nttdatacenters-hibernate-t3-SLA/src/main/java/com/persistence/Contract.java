package com.persistence;

// IMPORTS //
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * Contrato - Mapeo de la entidad "Contrato"
 * 
 * @author Santiago
 * 
 */

@Entity
@Table (name="Contrato")
public class Contract  extends AbstractEntity implements Serializable{	
	
	// Implements serializable //
	private static final long serialVersionUID = 2L;	
	
	// Mapeo de atributos //
	
	/**
	 * Cliente al que pertenece el contrato
	 */
	@ManyToOne
	@JoinColumn(name="Cliente")
	private Client client;			
	
	/**
	 * Fecha de validación del contrato
	 */
	@Column (name = "FechaValidez", unique= false)
	@Temporal (TemporalType.DATE)
	private Date validity;
	
	/**
	 * Fecha de expiración del contrato
	 */
	@Column (name="FechaExpiracion", unique = false)
	@Temporal (TemporalType.DATE)
	private Date expiration;
	
	/**
	 * Precio mensual del contrato
	 */
	@Column (name="PrecioMensual")
	private double monthPrice;		
	
	// Getters y Setters //	
	/**
	 * 
	 * getClient - Devuelve el cliente al que pertenece el contrato
	 * 	 
	 * @see com.persistence.Client
	 * 
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * 
	 * setClient - Asigna un cliente al que pertenece el contrato
	 * 
	 * @param client - Cliente
	 * 
	 * @see com.persistence.Client
	 * 
	 */	
	public void setClient(Client client) {
		this.client = client;
	}
	
	/**
	 * 
	 * getValidity - Devuelve la fecha de validación del contrato
	 * 
	 * @return Date - Fecha de validación
	 * 
	 */
	public Date getValidity() {
		return validity;
	}
	
	/**
	 * 
	 * setValidity - Asigna fecha de validación del contrato
	 * 
	 * @param validity - Fecha de validación
	 * 
	 */

	public void setValidity(Date validity) {	
			this.validity = validity;

	}

	/**
	 * 
	 * getExpiration - Devuelve fecha de expiración del contrato
	 * 
	 * @return Date - Fecha de expiración
	 * 
	 */
	public Date getExpiration() {
		return expiration;
	}

	/**
	 * 
	 * setExpiration - Asigna fecha de expiración del contrato
	 * 
	 * @param expiration - Fecha de expiración
	 * 
	 */
	public void setExpiration(Date expiration) {	
		this.expiration = expiration;
	
	}

	/**
	 * 
	 * getMonthPrice - Devuelve precio mensual del contrato
	 * 
	 * @return double - Precio mensual del contrato
	 * 
	 */
	public double getMonthPrice() {
		return monthPrice;
	}

	/**
	 * 
	 * setMonthPrice - Asigna el precio mensual del contrato
	 * 
	 * @param monthPrice - Precio mensual
	 * 
	 */
	public void setMonthPrice(double monthPrice) {
		this.monthPrice = monthPrice;
	}		
	
	// toString //
	@Override
	public String toString() {
		return "Contract [id=" + id + ", vigencia=" + validity + ", caducidad=" + expiration + ", precioMensual="
				+ monthPrice + "]";
	}
	
}
