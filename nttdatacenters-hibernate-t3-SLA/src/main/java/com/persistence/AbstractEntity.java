package com.persistence;

// IMPORTS //
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractEntity - AbstractClass - Entidad abstracta que contiene 
 * atributos comunes entre las entidades "Cliente" y "Contrato"
 * 
 * @author Santiago
 * 
 * @see com.persistence.Client
 * @see com.persistence.Contract
 */

@MappedSuperclass
public abstract class AbstractEntity implements Serializable{

	// IMPLEMENTS SERIALIZABLE //
	private static final long serialVersionUID = 1L;
	
	// MAPEO DE ATRIBUTOS //
	
	/**
	 * 
	 * Id de la entidad
	 * 
	 */
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")	
	protected int id;

    // Getters and Setters //    
    /**
     * 
	 * getId - Devuelve la ID a la entidad
	 * @return int - id
	 * 
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * setId - Asigna la ID a la entidad	
	 *  
	 */
	public void setId(int id) {
		this.id = id;
	}    
    
}
