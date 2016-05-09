package pt.upa.ca.ws;

import java.security.PublicKey;

public class CAManager {

	private static CAManager manager;
	
	private String _uddiURL;
	private String _wsName;
	private String _wsURL;
	
	public static final String[] entities = { "broker-ws", "broker-ws-cli", "transporter-ws", "transporter-ws-cli" };

	private CAManager(){
		
	}
	
	public CAManager(String uddiURL, String wsName, String wsURL) {
		this();
		_uddiURL = uddiURL;
		_wsName = wsName;
		_wsURL = wsURL;
	}

	public PublicKey requestPublicKey(String entity) {
		final String PATH = "src/main/resources/";
		PublicKey requestedKey = null;
		
		for(String ent : entities) {
			if(ent.equals(entity)){
				try {
					requestedKey = KeyUtils.readPublicKey(PATH + ent + ".public.key");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		
		return requestedKey;
	}
	
	public static CAManager getInstance() {
		if(manager == null){
			manager = new CAManager("localhost", "ca", "localhost/ca");
		}
		return manager;
	}

}
