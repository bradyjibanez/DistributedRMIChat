import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Server extends UnicastRemoteObject implements collectorInterface{

        private Hashtable userList = new Hashtable();
	public Server() throws RemoteException { }

        public boolean login(talkToServerInterface user) throws RemoteException {
                userList.put(user.getUsername(), user); //Needed to provide String of name from talkServerInterface
                user.tell("SERVER: Hi there, please keep in mind you can enter ./help for user instructions at anytime.");
                return true;
        }

        public void forHelp(String whatsNeeded, talkToServerInterface from) throws RemoteException{
		System.out.println("\n [" + from.getUsername() + "] requested help");
		from.tell(" ");
		from.tell(" ");
		from.tell("SERVER: Please select from one of the following options:");
		from.tell("1: ./quit - allows user to quit usage");
		from.tell("2: ./listusers - provides a list of all active users");
		from.tell("3: ./changename - allows you to change your visible user name.");
        }

	public void quit(talkToServerInterface from) throws RemoteException{
		System.out.println("\n [" + from.getUsername() + "] just Disconnected");
		userList.remove(from.getUsername(), from);
	}

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

	public void changeName(String whatsNeeded, talkToServerInterface from) throws RemoteException{
                userList.remove(from.getUsername(), from);
	}

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
