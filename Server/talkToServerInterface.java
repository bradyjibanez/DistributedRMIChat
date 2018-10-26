import java.rmi.Remote;
import java.rmi.RemoteException;

public interface talkToServerInterface extends Remote{

	public String getUsername() throws RemoteException;

	public void tell(String s) throws RemoteException;
}
