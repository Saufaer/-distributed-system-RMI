package points.interfaces;



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PointsInterface extends Remote {
	
	public static String RMI_SERVER = "rmi://localhost/";
	public static String RMI_NAME = "PointsServer";
	
	public int getWidht() throws RemoteException;
	public int getHeight() throws RemoteException;
	
	public Player getCellPlayer(int i, int j) throws RemoteException;
	public BoardWall getCellWall(int i, int j, Side side) throws RemoteException;
	public String getCellInfo(int i, int j) throws RemoteException;
	public String getCellPlayerInfo(int i, int j) throws RemoteException;
	public int getCurrentPlayerID() throws RemoteException;
	public List<Player> getPlayerslist() throws RemoteException;
	public void cellClick(int i, int j,Side side) throws RemoteException;
	public void addCallbackListener(InterfaceCallbacks listener) throws RemoteException;
	public int addPlayer(String name, int figure, String colorS) throws RemoteException;
		
}