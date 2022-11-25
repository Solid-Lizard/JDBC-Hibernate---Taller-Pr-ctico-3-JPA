package com.persistence;

// IMPORTS //
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cliente - Mapeo de la entidad "Cliente" 
 *  
 * @author Santiago 
 * 
 */

@Entity
@Table (name="Cliente")
public class Client extends AbstractEntity implements Serializable{
	
	//Implements Serializable //
	private static final long serialVersionUID = 1L;	
	
	// Mapeo de los atributos //	
	/**
	 * Dni del cliente
	 */
    @Column(name = "DNI", length = 9, unique = true)     
	private String dni;		      
	
    /**
	 * Nombre del cliente
	 */
	@Column (name="Nombre")
	private String name;
	
	/**
	 * Apellido1 del cliente
	 */
	@Column (name="Apellido1")
	private String firstSurname;
	
	/**
	 * Apellido2 del cliente
	 */
	@Column (name="Apellido2")
	private String secondSurname;
	
	// Relacion con Contrato //
	@OneToMany (cascade = CascadeType.ALL, mappedBy="client")
	private List <Contract> contracts;
	
	// Getters y Setters //		
	/**
	 * getDni - Devuelve el DNI del cliente
	 * @return String - dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * setDni - Asigna el DNI del cliente
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * getFirstSurname - Devuelve el apellido1 del cliente
	 * @return String - apellido1
	 */
	public String getFirstSurname() {
		return firstSurname;
	}

	/**
	 * setFirstSurname - Asigna el apellido1 del cliente del cliente
	 * @param firstSurname
	 */
	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	/**
	 * getSecondSurname - Devuelve el apellido2 del cliente
	 * @return String - apellido2
	 */
	public String getSecondSurname() {
		return secondSurname;
	}

	/**
	 * setSecondSurname - Asigna el apellido2 del cliente del cliente
	 * @param secondSurname
	 */
	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}	
	
	/**
	 * getName - Devuelve el nombre del cliente
	 * @return String - nombre
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName - Asigna el nombre del cliente
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	// toString //	
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", id=" + id + ", name=" + name + ", firstSurname=" + firstSurname
				+ ", secondSurname=" + secondSurname + "]";
	}			
	
}
