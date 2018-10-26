import java.rmi.*;

public interface collectorInterface extends Remote {

	  public boolean login (talkToServerInterface m) throws RemoteException;

	  public void sendToAll(String s, talkToServerInterface m) throws RemoteException;

	  public void forHelp(String s, talkToServerInterface m) throws RemoteException;

	  public void quit(talkToServerInterface m)throws RemoteException;

	  public void listUsers(String s, talkToServerInterface m) throws RemoteException;

	  public void changeName(String s, talkToServerInterface m) throws RemoteException;
}
