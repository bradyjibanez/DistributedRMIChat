/**
*@author Brady Ibanez brady.ibanez@uoit.net
*@version 1.0
*/
import java.rmi.*;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;

/**
*This class is utilized in order to invoked each Client instance and allows for user
*continuous input that can be acted upon accordingly by the server. This is done through the
*the simple act of String comparison for each input value the user provides.
*/
public class Client {

    /**
    *Interested in the given invocation of a given client. The one thing that should be noted
    *is that for each changeUser() name call, the client does most of the heavy lifting by
    *triggering a remote method call to remove their user name from the active user list. It
    *is then up to the user to reinitialize the login process and create a new user name. There
    *is input conscientious considerations made server side to account for this action as well.
    */
    public static void main(String[] args) {
      try {
		/**
		*Used to allow for differentiation between intital user login for connection
		*and subsequent name changes.
		*/
		int nameCount = 0;
		/**
		*Used to initialize username values as a String
		*/
		String username = new String();
		System.out.println("----------------WELCOME TO THE RMI GROUP CHAT----------------");
		for(;;){
		      nameCount++;
		      System.setSecurityManager(new RMISecurityManager());
		      collectorInterface server = (collectorInterface)Naming.lookup("rmi://localhost/Chat");

		      Scanner scanner=new Scanner(System.in);
		      System.out.println(" ");
		      System.out.println("Enter a username and press Enter:");
		      /**
	 	      *Allows for recognition of user input from the keyboard.
		      */
		      username = scanner.nextLine();
		      System.out.println(" ");
		      talkToServerInterface toPrint = new talkToServer(username,server);
		      server.login(toPrint);
		      if(nameCount<2){
		      server.sendToAll("just Connected", toPrint);
		      } else {
		      server.sendToAll("new Name", toPrint);
		      }
		      for(;;){
		    	  String whatWasSaid = scanner.nextLine();
			  if (whatWasSaid.equals("./help")) {
				server.forHelp(whatWasSaid, toPrint);
			  } else if (whatWasSaid.equals("./quit")) {
				server.sendToAll("quit", toPrint);
				server.quit(toPrint);
				System.out.println("------------------THANK YOU!!----------------");
				System.exit(0);
			  } else if (whatWasSaid.equals("./listusers")) {
			  	server.listUsers(whatWasSaid, toPrint);
			  } else if (whatWasSaid.equals("./changename")) {
				server.quit(toPrint);
				break;
			  } else {
				server.sendToAll(whatWasSaid, toPrint);
			  }
	    	  }
		}
       }catch (Exception e) {
          System.out.println("Hello Client exception: " + e);
          e.printStackTrace();
       }
    }
}
