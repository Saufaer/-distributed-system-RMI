package points.interfaces;

import points.interfaces.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import points.interfaces.InterfaceCallbacks;

public abstract class ServerCallbacksOverrider extends UnicastRemoteObject implements
		InterfaceCallbacks {

	private static final long serialVersionUID = 1079040465565540056L;

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
