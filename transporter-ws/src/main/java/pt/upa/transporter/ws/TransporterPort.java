package pt.upa.transporter.ws;

import java.util.List;
import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebService(name = "TransporterPortType", targetNamespace = "http://ws.transporter.upa.pt/")
@XmlSeeAlso({ObjectFactory.class})
public class  TransporterPort implements TransporterPortType {
	
	private static final String[] NORTE = { "Porto", "Braga", "Viana do Castelo", "Vila Real", "Bragança" };
	private static final String[] CENTRO = { "Lisboa", "Leiria", "Castelo Branco", "Coimbra", "Aveiro", "Viseu", "Guarda" };
	private static final String[] SUL = { "Setúbal", "Évora", "Portalegre", "Beja", "Faro" };
	
	private int _numberOfJobs;
	private ListJobsResponse _jobs;
	private int _id;
	private String _companyName;
	Random _random;
	public TransporterPort(){ _numberOfJobs = 0; companyName("NoName"); id(0); clearJobs(); _random = new Random(); }
	
	public void id(int id){ _id = id; }
	public void companyName(String companyName){ _companyName = companyName; }
	public String newJobIdentifier(String origin, String destination){
		return _companyName + origin + destination + new Integer(_numberOfJobs++).toString();
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 *     returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "ping", targetNamespace = "http://ws.transporter.upa.pt/", className = "pt.upa.transporter.ws.Ping")
	@ResponseWrapper(localName = "pingResponse", targetNamespace = "http://ws.transporter.upa.pt/", className = "pt.upa.transporter.ws.PingResponse")
	@Action(input = "http://ws.transporter.upa.pt/TransporterPort/pingRequest", output = "http://ws.transporter.upa.pt/TransporterPort/pingResponse")
	public String ping(
			@WebParam(name = "name", targetNamespace = "")
			String name){
		return new String("PING " + name);
	}

	/**
	 * 
	 * @param price
	 * @param origin
	 * @param destination
	 * @return
	 *     returns pt.upa.transporter.ws.JobView
	 * @throws BadLocationFault_Exception
	 * @throws BadPriceFault_Exception
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "requestJob", targetNamespace = "http://ws.transporter.upa.pt/", className = "pt.upa.transporter.ws.RequestJob")
	@ResponseWrapper(localName = "requestJobResponse", targetNamespace = "http://ws.transporter.upa.pt/", className = "pt.upa.transporter.ws.RequestJobResponse")
	@Action(input = "http://ws.transporter.upa.pt/TransporterPort/requestJobRequest", output = "http://ws.transporter.upa.pt/TransporterPort/requestJobResponse", fault = {
			@FaultAction(className = BadLocationFault_Exception.class, value = "http://ws.transporter.upa.pt/TransporterPort/requestJob/Fault/BadLocationFault"),
			@FaultAction(className = BadPriceFault_Exception.class, value = "http://ws.transporter.upa.pt/TransporterPort/requestJob/Fault/BadPriceFault")
	})
	public JobView requestJob(
			@WebParam(name = "origin", targetNamespace = "")
			String origin,
			@WebParam(name = "destination", targetNamespace = "")
			String destination,
			@WebParam(name = "price", targetNamespace = "")
			int price)
					throws BadLocationFault_Exception, BadPriceFault_Exception
	{
		System.out.println("Job requested");
		boolean originNotFound = true; 
		boolean destinationNotFound = true; 
		if(_id % 2 == 0){
			//Even transporters
			for(String s : TransporterPort.NORTE){
				if(s.equals(origin)) originNotFound = false;
				if(s.equals(destination)) destinationNotFound = false;
			}
			for(String s : TransporterPort.CENTRO){
				if(s.equals(origin)) originNotFound = false;
				if(s.equals(destination)) destinationNotFound = false;
			}
		}else{
			//Odd transporters
			for(String s : TransporterPort.CENTRO){
				if(s.equals(origin)) originNotFound = false;
				if(s.equals(destination)) destinationNotFound = false;
			}
			for(String s : TransporterPort.SUL){
				if(s.equals(origin)) originNotFound = false;
				if(s.equals(destination)) destinationNotFound = false;
			}
		}
		//if(originNotFound) throw new BadLocationFault_Exception(origin, new BadLocationFault());
		//if(destinationNotFound) throw new BadLocationFault_Exception(destination, new BadLocationFault());
		//it's stupid if the price is an integer, and the offer is 1, I can't return a valid price lower than that
		if(price > 100 || price < 2 || destinationNotFound | originNotFound) return null;
		
		JobView j = new JobView();
		_jobs.getReturn().add(j);
		j.setJobOrigin(origin);
		j.setJobDestination(destination);
		j.setCompanyName(_companyName);
		j.setJobIdentifier(newJobIdentifier(origin, destination));
		
		if(price <= 10) j.setJobPrice(price - _random.nextInt(price - 1) + 1);
		else 
		if(price % 2 == 0){
			if(_id % 2 == 0) j.setJobPrice(price - _random.nextInt(price - 1) + 1);
			else j.setJobPrice(price + _random.nextInt(price - 1) + 1);
		}else{
			if(_id % 2 == 0) j.setJobPrice(price + _random.nextInt(price - 1) + 1);
			else j.setJobPrice(price - _random.nextInt(price - 1) + 1);
		}
		
		return j;
	}

	/**
	 * 
	 * @param id
	 * @param accept
	 * @return
	 *     returns pt.upa.transporter.ws.JobView
	 * @throws BadJobFault_Exception
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "decideJob", targetNamespace = "http://ws.transporter.upa.pt/", className = "pt.upa.transporter.ws.DecideJob")
	@ResponseWrapper(localName = "decideJobResponse", targetNamespace = "http://ws.transporter.upa.pt/", className = "pt.upa.transporter.ws.DecideJobResponse")
	@Action(input = "http://ws.transporter.upa.pt/TransporterPort/decideJobRequest", output = "http://ws.transporter.upa.pt/TransporterPort/decideJobResponse", fault = {
			@FaultAction(className = BadJobFault_Exception.class, value = "http://ws.transporter.upa.pt/TransporterPort/decideJob/Fault/BadJobFault")
	})
	public JobView decideJob(
			@WebParam(name = "id", targetNamespace = "")
			String id,
			@WebParam(name = "accept", targetNamespace = "")
			boolean accept)
					throws BadJobFault_Exception
	{
		for(JobView job : _jobs.getReturn())
			if(job.getJobIdentifier().equals(id)){
				job.setJobState((accept) ? JobStateView.ACCEPTED : JobStateView.REJECTED);
				return job;
			}
		return null;
	}

	/**
	 * 
	 * @param id
	 * @return
	 *     returns pt.upa.transporter.ws.JobView
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "jobStatus", targetNamespace = "http://ws.transporter.upa.pt/", className = "pt.upa.transporter.ws.JobStatus")
	@ResponseWrapper(localName = "jobStatusResponse", targetNamespace = "http://ws.transporter.upa.pt/", className = "pt.upa.transporter.ws.JobStatusResponse")
	@Action(input = "http://ws.transporter.upa.pt/TransporterPort/jobStatusRequest", output = "http://ws.transporter.upa.pt/TransporterPort/jobStatusResponse")
	public JobView jobStatus(
			@WebParam(name = "id", targetNamespace = "")
			String id){
		for(JobView job : _jobs.getReturn())
			if(job.getJobIdentifier().equals(id)){
				JobView status = new ObjectFactory().createJobView();
				status.setCompanyName(job.getCompanyName());
				status.setJobDestination(job.getJobDestination());
				status.setJobIdentifier(job.getJobIdentifier());
				status.setJobOrigin(job.getJobOrigin());
				status.setJobPrice(job.getJobPrice());
				status.setJobState(job.getJobState());
				return status;
			}
		return null;
	}

	/**
	 * 
	 * @return
	 *     returns java.util.List<pt.upa.transporter.ws.JobView>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "listJobs", targetNamespace = "http://ws.transporter.upa.pt/", className = "pt.upa.transporter.ws.ListJobs")
	@ResponseWrapper(localName = "listJobsResponse", targetNamespace = "http://ws.transporter.upa.pt/", className = "pt.upa.transporter.ws.ListJobsResponse")
	@Action(input = "http://ws.transporter.upa.pt/TransporterPort/listJobsRequest", output = "http://ws.transporter.upa.pt/TransporterPort/listJobsResponse")
	public List<JobView> listJobs(){
		ListJobsResponse response = new ObjectFactory().createListJobsResponse();
		for(JobView job : _jobs.getReturn()){
			JobView status = new ObjectFactory().createJobView();
			status.setCompanyName(job.getCompanyName());
			status.setJobDestination(job.getJobDestination());
			status.setJobIdentifier(job.getJobIdentifier());
			status.setJobOrigin(job.getJobOrigin());
			status.setJobPrice(job.getJobPrice());
			status.setJobState(job.getJobState());
			response.getReturn().add(status);
		}
		return response.getReturn();
	}

	/**
	 * 
	 */
	@WebMethod
	@RequestWrapper(localName = "clearJobs", targetNamespace = "http://ws.transporter.upa.pt/", className = "pt.upa.transporter.ws.ClearJobs")
	@ResponseWrapper(localName = "clearJobsResponse", targetNamespace = "http://ws.transporter.upa.pt/", className = "pt.upa.transporter.ws.ClearJobsResponse")
	@Action(input = "http://ws.transporter.upa.pt/TransporterPort/clearJobsRequest", output = "http://ws.transporter.upa.pt/TransporterPort/clearJobsResponse")
	public void clearJobs(){
		_jobs = new ObjectFactory().createListJobsResponse();
	}

}
