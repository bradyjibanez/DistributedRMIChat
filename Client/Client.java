import java.rmi.*;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;

public class Client {

    public static void main(String[] args) {
      try {
		int nameCount = 0;
		String username = new String();
		System.out.println("----------------WELCOME TO THE RMI GROUP CHAT----------------");
		for(;;){
		      nameCount++;
		      System.setSecurityManager(new RMISecurityManager());
		      collectorInterface server = (collectorInterface)Naming.lookup("rmi://localhost/Chat");

		      Scanner scanner=new Scanner(System.in);
		      System.out.println(" ");
		      System.out.println("Enter a username and press Enter:");
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
