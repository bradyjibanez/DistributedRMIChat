import java.rmi.*;
import java.rmi.server.*;

public class talkToServer extends UnicastRemoteObject implements talkToServerInterface{

	private String username;
	private collectorInterface server;

	public talkToServer(String u, collectorInterface s) throws RemoteException {
		username = u;
		server = s;
	}

	public String getUsername() throws RemoteException{
		return username;
	}

	public void tell(String s) throws RemoteException{
		System.out.println(s);
	}

}
