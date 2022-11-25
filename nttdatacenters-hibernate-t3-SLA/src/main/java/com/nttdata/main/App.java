package com.nttdata.main;

// IMPORTS //
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.persistence.Client;
import com.persistence.Contract;
import com.services.ClientManagementServiceImpl;
import com.services.ContractManagementServiceImpl;
import com.services.SessionManager;
import java.time.LocalDate;
import java.util.Date;


/**
 * App - Clase principal de la aplicación
 * @author Santiago
 */
public class App {
	// PARAMETROS CONSTANTES //
	
	// Al ser parámetros repetidos, sonarLint nos da un aviso para extraer la expresión como
	// una constante y, posteriormente, hacer referencia a la misma
	private static final String ARREDONDO = "Arredondo";
	private static final String LOPEZ = "Lopez";	
	private static final String SANTIAGO = "Santiago";
	
	// OBJETOS //
		
	// LOG //
	private static final Logger LOG = LoggerFactory.getLogger(App.class);		
	
	// MÉTODOS //
	
	/**
	 * Main - Método de entrada a la aplicación
	 * @param args	 
	 */
	public static void main( String[] args ) {		
		
        LOG.debug("INICIO DE TRAZA");         
        
        // INICIO DE SESIÓN HIBERNATE //        
        LOG.debug("Iniciando sesion");
        
        SessionManager sm = new SessionManager();
    	sm.openSession();
    	
    	SessionManager sm1 = new SessionManager();
    	sm1.openSession();
                
    	LOG.debug("Sesión iniciada correctamente");
    	
        // GENERACIÓN DE CLIENTES //    	
    	LOG.debug("----------GENERACIÓN DE CLIENTES----------");
    	
    	// Cliente 1 //
        final Client c1 = new Client();
        c1.setDni("30259182z");
        c1.setName(SANTIAGO);
        c1.setFirstSurname(LOPEZ);
        c1.setSecondSurname(ARREDONDO);        
              
        LOG.debug("Cliente 1 generado correctamente");
        
        // Cliente 2 //
        final Client c2 = new Client();
        c2.setDni("30259182s");
        c2.setName(SANTIAGO);
        c2.setFirstSurname(ARREDONDO);
        c2.setSecondSurname(LOPEZ);  
        
        LOG.debug("Cliente 2 generado correctamente");
             
        // Cliente 3 //
        final Client c3 = new Client();               
        c3.setDni("12345678a");
        c3.setName("Peter");
        c3.setFirstSurname("Parker");
        c3.setSecondSurname("Spiderman"); 
        
        LOG.debug("Cliente 3 generado correctamente");
              
        // Cliente 4 //
        final Client c4 = new Client();               
        c4.setDni("87654321a");
        c4.setName("Bruce");
        c4.setFirstSurname("Wayne");
        c4.setSecondSurname("Batman"); 
        
        LOG.debug("Cliente 4 generado correctamente");
        
        LOG.debug("----------CLIENTES GENERADOS CORRECTAMENTE----------");    
        
        // GENERACIÓN DE CONTRATOS //
        LOG.debug("----------GENERACIÓN DE CONTRATOS----------");
        
        // Contrato 1 //
        
        Date fecha1 = new Date();
        
        final Contract co1 = new Contract();
        co1.setClient(c1);       
		co1.setExpiration(fecha1);		
        co1.setValidity(fecha1);
        co1.setId(0);
        co1.setMonthPrice(100);
        
        LOG.debug("Contrato 1 generado correctamente");
        
        // Contrato 2 //
        final Contract co2 = new Contract();
        co2.setClient(c2);
        co2.setExpiration(fecha1);
        co2.setValidity(fecha1);
        co2.setId(0);
        co2.setMonthPrice(200);  
        
        LOG.debug("Contrato 2 generado correctamente");
        
        // Contrato 3 //
        final Contract co3 = new Contract();
        co3.setClient(c3);
        co3.setExpiration(fecha1);
        co3.setValidity(fecha1);
        co3.setId(0);
        co3.setMonthPrice(300);
        
        LOG.debug("Contrato 3 generado correctamente");
        
        // Contrato 4 //
        final Contract co4 = new Contract();
        co4.setClient(c4);
        co4.setExpiration(fecha1);
        co4.setValidity(fecha1);
        co4.setId(0);
        co4.setMonthPrice(400);  
        
        LOG.debug("Contrato 4 generado correctamente");
        
        // Contrato 5 //
        final Contract co5 = new Contract();
        co5.setClient(c1);
        co5.setExpiration(fecha1);
        co5.setValidity(fecha1);
        co5.setId(0);
        co5.setMonthPrice(500);  
        
        LOG.debug("Contrato 5 generado correctamente");
        
        // Contrato 6 //
        final Contract co6 = new Contract();
        co6.setClient(c1);
        co6.setExpiration(fecha1);
        co6.setValidity(fecha1);
        co6.setId(0);
        co6.setMonthPrice(600);  
        
        LOG.debug("Contrato 6 generado correctamente");
        
        LOG.debug("----------CONTRATOS GENERADOS CORRECTAMENTE----------");    
              
        // CONSUMIR SERVICIOS //
        
        // Inicializamos servicios //
        LOG.debug("----------INICIALIZANDO SERVICIOS----------");
        
        final ClientManagementServiceImpl clientManager = new ClientManagementServiceImpl(sm);        
        final ContractManagementServiceImpl contractManager = new ContractManagementServiceImpl(sm1);
        
        LOG.debug("----------SERVICIOS INICIALIZADOS CORRECTAMENTE----------");
               
        // Consumimos servicios //
        
        // Create //
        LOG.debug("----------INICIANDO CREACIÓN DE CLIENTES----------");
        
        clientManager.create(c1);          
        clientManager.create(c2);   
        clientManager.create(c3);
        clientManager.create(c4);
        
        LOG.debug("----------CLIENTES CREADOS SATISFACTORIAMENTE----------");
        
        LOG.debug("----------INICIANDO CREACIÓN DE CONTRATOS----------");
        
        contractManager.create(co1);
        contractManager.create(co2);
        contractManager.create(co3);
        contractManager.create(co4);
        contractManager.create(co5);
        contractManager.create(co6);
        
        
        LOG.debug("----------CONTRATOS CREADOS SATISFACTORIAMENTE----------");
        
        // Read //                
        LOG.debug("----------CONSUMIENDO SERVICIOS DE LECTURA DE CONTRATOS----------");        

        // Obtener fecha actual //
        int dia = LocalDate.now().getDayOfMonth();
        int mes = LocalDate.now().getMonthValue();
        int anio= LocalDate.now().getYear();        
        LocalDate d = LocalDate.of(anio, mes, dia);                         
        
        String mssg;
        
        // Buscamos contratos //
        
        // Buscamos por fecha de validacion //
        mssg = contractManager.read(d).toString();
        LOG.debug(mssg);
        
        // Buscamos por precio mensual //
        mssg = contractManager.read(100.00).toString();
        LOG.debug(mssg);
        
        // Buscamos por id de contrato //
        mssg = contractManager.read(6).toString();
        LOG.debug(mssg);
        
        // Leemos por cliente //
        mssg = contractManager.readByClientId(1).toString();
        LOG.debug(mssg);
        
        mssg = contractManager.readByClientId(2).toString();
        LOG.debug(mssg);
        
        mssg = contractManager.readByClientId(3).toString();
        LOG.debug(mssg);
        
        // Leemos por fecha de expiracion //        
        mssg = contractManager.readByExpiration(d).toString();
        LOG.debug(mssg);
        
        // Leemos según id del cliente y precio mensual del contrato //
        contractManager.readByClientIdandContractMonthPrice(100, 1);     
                       
        // Buscamos según el nombre del cliente al que pertenecen //
        contractManager.readByClientName("Bruce");        
               
        // Buscamos todos los contratos //
        for (Contract c: contractManager.searchAll()) {
        	mssg = c.toString();
        	
        }
        
        LOG.debug(mssg);
        
        LOG.debug("----------SERVICIOS DE LECTURA DE CONTRATOS CONSUMIDOS SATISFACTORIAMENTE----------");
        
        LOG.debug("----------CONSUMIENDO SERVICIOS DE LECTURA DE CLIENTES----------");
                
        clientManager.read(1);
        clientManager.read(SANTIAGO);
        clientManager.read("Peter", "Parker");      
        clientManager.searchAll();        
        
        LOG.debug("----------SERVICIOS DE LECTURA DE CLIENTES CONSUMIDOS SATISFACTORIAMENTE----------");
                     
                                
        // Cierre de sesión //
        LOG.debug("----------CERRANDO SESIÓN----------");                
        
        sm.closeSession();
        
        LOG.debug("----------SESIÓN CERRADA SATISFACTORIAMENTE----------");        
        LOG.debug("----------FIN DE TRAZA----------");
        
        System.out.println("---------- PORFAVOR, CONSULTAR EL ARCHIVO ./LOGS/logs.log PARA OBSERVAR EL FLUJO DE LA APLICACIÓN----------");
        
        
        
    }
}
