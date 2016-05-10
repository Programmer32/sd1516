package pt.upa.broker.ws;

import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.registry.JAXRException;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import pt.upa.ui.Dialog;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebService(name = "BrokerPortType", targetNamespace = "http://ws.broker.upa.pt/")
@XmlSeeAlso({
    ObjectFactory.class
})
@HandlerChain(file="/handler-chain.xml")
public class BrokerPort implements BrokerPortType {

	public BrokerPort() throws JAXRException {}
	
    /**
     * 
     * @param name
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ping", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.Ping")
    @ResponseWrapper(localName = "pingResponse", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.PingResponse")
    @Action(input = "http://ws.broker.upa.pt/BrokerPort/pingRequest", output = "http://ws.broker.upa.pt/BrokerPort/pingResponse")
    public String ping(
        @WebParam(name = "name", targetNamespace = "")
        String name){
    	Dialog.IO().debug("ping","NAME: " + name);
    	return BrokerManager.getInstance().ping(name);
    }

    /**
     * 
     * @param price
     * @param origin
     * @param destination
     * @return
     *     returns java.lang.String
     * @throws InvalidPriceFault_Exception
     * @throws UnavailableTransportPriceFault_Exception
     * @throws UnknownLocationFault_Exception
     * @throws UnavailableTransportFault_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "requestTransport", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.RequestTransport")
    @ResponseWrapper(localName = "requestTransportResponse", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.RequestTransportResponse")
    @Action(input = "http://ws.broker.upa.pt/BrokerPort/requestTransportRequest", output = "http://ws.broker.upa.pt/BrokerPort/requestTransportResponse", fault = {
        @FaultAction(className = UnknownLocationFault_Exception.class, value = "http://ws.broker.upa.pt/BrokerPort/requestTransport/Fault/UnknownLocationFault"),
        @FaultAction(className = InvalidPriceFault_Exception.class, value = "http://ws.broker.upa.pt/BrokerPort/requestTransport/Fault/InvalidPriceFault"),
        @FaultAction(className = UnavailableTransportFault_Exception.class, value = "http://ws.broker.upa.pt/BrokerPort/requestTransport/Fault/UnavailableTransportFault"),
        @FaultAction(className = UnavailableTransportPriceFault_Exception.class, value = "http://ws.broker.upa.pt/BrokerPort/requestTransport/Fault/UnavailableTransportPriceFault")
    })
    public String requestTransport(
        @WebParam(name = "origin", targetNamespace = "")
        String origin,
        @WebParam(name = "destination", targetNamespace = "")
        String destination,
        @WebParam(name = "price", targetNamespace = "")
        int price)
        throws InvalidPriceFault_Exception, UnavailableTransportFault_Exception, UnavailableTransportPriceFault_Exception, UnknownLocationFault_Exception
    {
    	Dialog.IO().debug("requestTransport", "Transport requested from " + origin + " to " + destination + " for " + price);
    	return BrokerManager.getInstance().requestTransport(origin, destination, price);
    }

    /**
     * 
     * @param id
     * @return
     *     returns pt.upa.broker.ws.TransportView
     * @throws UnknownTransportFault_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "viewTransport", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.ViewTransport")
    @ResponseWrapper(localName = "viewTransportResponse", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.ViewTransportResponse")
    @Action(input = "http://ws.broker.upa.pt/BrokerPort/viewTransportRequest", output = "http://ws.broker.upa.pt/BrokerPort/viewTransportResponse", fault = {
        @FaultAction(className = UnknownTransportFault_Exception.class, value = "http://ws.broker.upa.pt/BrokerPort/viewTransport/Fault/UnknownTransportFault")
    })
    public TransportView viewTransport(
        @WebParam(name = "id", targetNamespace = "")
        String id)
        throws UnknownTransportFault_Exception{
    	Dialog.IO().debug("viewTransport", "Request to view transport with id " + id);
    	return BrokerManager.getInstance().viewTransport(id);
    }

    /**
     * 
     * @return
     *     returns java.util.List<pt.upa.broker.ws.TransportView>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listTransports", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.ListTransports")
    @ResponseWrapper(localName = "listTransportsResponse", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.ListTransportsResponse")
    @Action(input = "http://ws.broker.upa.pt/BrokerPort/listTransportsRequest", output = "http://ws.broker.upa.pt/BrokerPort/listTransportsResponse")
    public List<TransportView> listTransports(){
    	Dialog.IO().debug("listTransports", "Request to list all transports");
    	return BrokerManager.getInstance().listTransports();
    }

    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "clearTransports", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.ClearTransports")
    @ResponseWrapper(localName = "clearTransportsResponse", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.ClearTransportsResponse")
    @Action(input = "http://ws.broker.upa.pt/BrokerPort/clearTransportsRequest", output = "http://ws.broker.upa.pt/BrokerPort/clearTransportsResponse")
    public void clearTransports(){
    	Dialog.IO().debug("clearTransports", "Request to clean transports list");
    	BrokerManager.getInstance().clearTransports();
    }
    
    
    /**
     * 
     * @param companyID
     * @param price
     * @param origin
     * @param companyName
     * @param destination
     * @param id
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "")
    @RequestWrapper(localName = "updateJob", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.UpdateJob")
    @ResponseWrapper(localName = "updateJobResponse", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.UpdateJobResponse")
    public void updateJob(
        @WebParam(name = "origin", targetNamespace = "")
        String origin,
        @WebParam(name = "destination", targetNamespace = "")
        String destination,
        @WebParam(name = "price", targetNamespace = "")
        int price,
        @WebParam(name = "id", targetNamespace = "")
        String id,
        @WebParam(name = "companyID", targetNamespace = "")
        String companyID,
        @WebParam(name = "companyName", targetNamespace = "")
        String companyName){
    	Dialog.IO().debug("updateJob", "Job is being updated by Master Broker");
    	BrokerManager.getInstance().updateJob(origin, destination, price, id, companyID, companyName);
    }

    /**
     * 
     * @param endpoint
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "webserviceName", targetNamespace = "")
    @RequestWrapper(localName = "addSlave", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.AddSlave")
    @ResponseWrapper(localName = "addSlaveResponse", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.AddSlaveResponse")
    public void addSlave(
        @WebParam(name = "endpoint", targetNamespace = "")
        String endpoint){
    	Dialog.IO().debug("addSlave", "Request to add a slave");
    	BrokerManager.getInstance().addSlave(endpoint);
    }
    
    /**
     * 
     * @param endpoint
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "removeSlave", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.RemoveSlave")
    public void removeSlave(
        @WebParam(name = "endpoint", targetNamespace = "")
        String endpoint){
    	Dialog.IO().debug("removeSlave", "Removing slave from list" + endpoint);
    	BrokerManager.getInstance().removeSlave(endpoint);
    	Dialog.IO().debug("removeSlave", "Slave removed from list");
    }

    

    /**
     * 
     * @param id
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "pingSlave", targetNamespace = "http://ws.broker.upa.pt/", className = "pt.upa.broker.ws.PingSlave")
    public void pingSlave(
        @WebParam(name = "id", targetNamespace = "")
        int id){
    	Dialog.IO().debug("pingSlave", "Pinging slave");
    	BrokerManager.getInstance().pingSlave(0);    	
    }
}
