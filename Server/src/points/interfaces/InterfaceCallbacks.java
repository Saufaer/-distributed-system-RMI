package points.interfaces;


import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceCallbacks extends Remote, Serializable {

	public void boardChanege() throws RemoteException;
	public void nextPlayer(Player next) throws RemoteException;
	public void startGame() throws RemoteException;
	public void gameOver(int idWinner) throws RemoteException;

}
