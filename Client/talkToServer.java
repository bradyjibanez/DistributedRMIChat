/**
*@author Brady Ibanez bradyjibanez@uoit.net
*@Version 1.0
*/
import java.rmi.*;
import java.rmi.server.*;

/**
*The talkToServer Class is interested in allowing for server interaction calls within the server
*environment concerning details available from the server end, and in making String replies to
*the interacting client.
*/
public class talkToServer extends UnicastRemoteObject implements talkToServerInterface{

	/**
	*The String representation of the users selected username.
	*/
	private String username;
	/**
	*An object to allow for request call data transmission to server side interface objects
	*/
	private collectorInterface server;

	/**
	*@constructor Provides the user specific variables per user including their user name
	*and server object
	*/
	public talkToServer(String u, collectorInterface s) throws RemoteException {
		username = u;
		server = s;
	}

	/**
	*Allows for server calls to attain client's username.
	*/
	public String getUsername() throws RemoteException{
		return username;
	}

	/**
	*Allows for server to provide String responses to user calls.
	*/
	public void tell(String s) throws RemoteException{
		System.out.println(s);
	}

}
