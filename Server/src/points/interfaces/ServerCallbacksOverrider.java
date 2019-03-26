package points.interfaces;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class ServerCallbacksOverrider extends UnicastRemoteObject implements
		InterfaceCallbacks {

	

	protected ServerCallbacksOverrider() throws RemoteException {
		super();
	}

        @Override
	public abstract void boardChanege() throws RemoteException;
        @Override
	public abstract void nextPlayer(Player next) throws RemoteException;
        @Override
	public abstract void startGame() throws RemoteException;
}
