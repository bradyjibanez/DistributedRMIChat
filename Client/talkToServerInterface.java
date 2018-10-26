
/**
*@author Brady Ibanez brady.ibanez@uoit.net
*@version 1.0
*/
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
*talkToServerInterface deals with the coordination of server speak to client particular methods. Th$
*/
public interface talkToServerInterface extends Remote{

        /**
        *This is the interface call defined by the Client side invocation script talkToServer.
        */
        public String getUsername() throws RemoteException;

        /**
        *This is the interface call defined by the Client side interface talkToServer.
        *@param s - this is the String representation that the Server is to send to a given user.
        */
        public void tell(String s) throws RemoteException;
}



