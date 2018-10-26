/**
*@author Brady Ibanez brady.ibanez@uoit.net
*@version 1.0
*/
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
*This class is utilized in order to commence server connection potential
*and allow for the detailing of remote method to be executed via the implied
*connectivity of the two Interfaces defined
*/
public class Server extends UnicastRemoteObject implements collectorInterface{

	/**
	*The publically altered list of active users edited per login() and quit() call.
	*/
        private Hashtable userList = new Hashtable();
	public Server() throws RemoteException { }

	/**
	*login() is used to allow users to register their username and connection status.
	*@param user: Provides login the talkToServerInterface object representing the user's name.
	*@return login: Provides a boolean value for user to reference status of the their login request.
	*/
        public boolean login(talkToServerInterface user) throws RemoteException {
                userList.put(user.getUsername(), user); //Needed to provide String of name from talkServerInterface
                user.tell("SERVER: Hi there, please keep in mind you can enter ./help for user instructions at anytime.");
                return true;
        }

	/**
	*forHelp() allows satisfaction of the user's request to see possible application user commands
	*@param whatsNeeded: Provides the simple reference to the users ./help request.
	*@param from: The String value representing the invoking user name.
	*/
        public void forHelp(String whatsNeeded, talkToServerInterface from) throws RemoteException{
		System.out.println("\n [" + from.getUsername() + "] requested help");
		from.tell(" ");
		from.tell(" ");
		from.tell("SERVER: Please select from one of the following options:");
		from.tell("1: ./quit - allows user to quit usage");
		from.tell("2: ./listusers - provides a list of all active users");
		from.tell("3: ./changename - allows you to change your visible user name.");
        }

	/**
	*quit() is used in order to trigger a user's removal of their user information from the Hashtable prior to severing their connection locally.
	*@param from: The String value representing the invoking users name.
	*/
	public void quit(talkToServerInterface from) throws RemoteException{
		System.out.println("\n [" + from.getUsername() + "] just Disconnected");
		userList.remove(from.getUsername(), from);
	}

	/**
	*listUsers triggers a user specific print statement of all registered users within the Hashtable l
	*@param whatsNeeded: Provides the String request issuing the call for help.
	*@param from: The String value representing the invoking users name.
	*/
	public void listUsers(String whatsNeeded, talkToServerInterface from) throws RemoteException{
		Enumeration usernames = userList.keys();
		from.tell(" ");
		from.tell(" ");
		while(usernames.hasMoreElements()){
			String user = (String)usernames.nextElement();//String of all listed users from Hashtable l
			if (user.equals(from.getUsername())){//Does not allow for current user to see themself as online user.
				continue;}
			if(user.equals(null)){
				break;
			}
			from.tell(user);
		}from.tell("------------");
	}

	/**
	*changeName() allows for the beginning of a series of actions, however, only removes the user name from the Hashtable l on its own. 
	*@param whatsNeeded: The String request from the user to quit.
	*@param from: The String value representing the invoking users name.
	*/
	public void changeName(String whatsNeeded, talkToServerInterface from) throws RemoteException{
                userList.remove(from.getUsername(), from);
	}

	/**
        *sendToAll() Invokes a call to allow for input String to be shared across all active users in the Hashtable l.
        *@param whatsNeeded: The String containing the content of the message to be shared among all users. This is scanned in detail depending on the input to allow for the appropriate server response. 
        *@param from: The String value representing the invoking users name.
        */
        public void sendToAll(String whatWasSaid, talkToServerInterface from) throws RemoteException{
                System.out.println("\n [" + from.getUsername() + "] " + whatWasSaid);
                Enumeration usernames = userList.keys();
        	while(usernames.hasMoreElements()){
			String user = (String)usernames.nextElement();//String of all listed users from Hashtable l
			talkToServerInterface name = (talkToServerInterface)userList.get(user);//gives talkToServer instantiation reference for each user
			if (user.equals(from.getUsername())){//Does not allow for current user to see themself come online notification
				continue;
			}
                	try{
				if(whatWasSaid.equals("just Connected")){
					name.tell("\n --------" + from.getUsername() + " just connected--------");
				} else if(whatWasSaid.equals("quit")) {
					name.tell("\n --------" + from.getUsername() + " just left--------");
				} else if(whatWasSaid.equals("new Name")){
					name.tell("\n --------" +from.getUsername() + " is a replacement user--------");
				} else {
					name.tell("\n    " + from.getUsername() + ": " + whatWasSaid);
				}
			}catch(Exception e){e.printStackTrace();}
            	}
         }

/**
*Invokes server intialization to begin distributed interaction
*/
    public static void main(String[] args){
         try {
        	System.setSecurityManager(new RMISecurityManager());
		Server obj=new Server();
                Naming.rebind("rmi://localhost/Chat", obj);
                System.out.println("The RMI GroupChat Server is running.");
	     } catch (Exception e) {System.out.println("Chat Server failed: " + e);}
    	}
}
