/**
*@author Brady Ibanez brady.ibanez@uoit.net
*@version 1.0
*/
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
*This is an interface implementation for the method calls that reside within the Server file. It deals with client to server communication. 
*/
public interface collectorInterface extends Remote{

	  /**
	  *This is concerned with the crossing of data needed for the Server invokation of the login() method.
	  *@param m - This is used as the object in order to send user details, and is primarily required for username conveyance. 
	  */
	  public boolean login(talkToServerInterface m) throws RemoteException;

	  /**
	  *This is the interface call of the sendToAll() method within the Server.
	  *@param s - This is used to provide the String of what the user is looking to convey.
	  *@param m - This is the user object representing the user details for the requesting user.
	  */
	  public void sendToAll(String s, talkToServerInterface m) throws RemoteException;

	  /**
	  *This is the interface call of forHelp() within the Server.
	  *@param s - This is used to provide the String that the user entered in to request help.
	  *@param m - This is the user object representing the user details for the requesting user.
	  */
	  public void forHelp(String s, talkToServerInterface m) throws RemoteException;

	  /**
	  *This is the interface call to quit() within the Server.
	  *@param m - This is the user object representing the user details for the requesting user.
	  */
	  public void quit(talkToServerInterface m) throws RemoteException;

	  /**
	  *This is the interface call to listUsers() within the Server.
	  *@param s - This is used to provide the String that the user enters calling for active users to be listed.
	  *@param m - This is the user object representing the user details for the requesting user.
	  */
	  public void listUsers(String s, talkToServerInterface m) throws RemoteException;

	  /**
	  *This is the interface call to changeName() within the Server.
	  *@param s - This is the String trigger that allows the server to realize that a changeName call is necessary
	  *@param m - This is the user object representing the user details for the requesting user.
	  */
	  public void changeName(String s, talkToServerInterface m) throws RemoteException;
}
