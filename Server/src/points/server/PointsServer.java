package points.server;


import points.worker.*;
import points.interfaces.*;

import java.rmi.registry.LocateRegistry;

import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class PointsServer extends UnicastRemoteObject implements PointsInterface {

	
	private static GameBoard board;
	private static PointsServer obj;
	public static final int DEF_SIZE = 7;
	private static List<InterfaceCallbacks> callbacks = null;
	private List<Player> playersList;
	private static int playerCount = 0;
	private static Scanner sc;
	private static boolean gameOn = false;
	
	@Override
	public void addCallbackListener(InterfaceCallbacks listener) {
		callbacks.add(listener);
	}
	
	public PointsServer(int w, int h) throws RemoteException 
	{ 
		board = new GameBoard(w<1 ? DEF_SIZE : w, h<1 ? DEF_SIZE : h);
		callbacks = new ArrayList<InterfaceCallbacks>();
		playersList = new ArrayList<Player>();
		board.addCallbackListener(new BoardCallbacks() {
			@Override
			public void boardChanege() {
				if (callbacks != null)
					try {
						for(InterfaceCallbacks sc : callbacks) sc.boardChanege();
					} catch (RemoteException e) { e.printStackTrace(); }
			}
			@Override
			public void nextPlayer(Player next) 
			{ 
				System.out.println("--- Сurrent move: " + next.getName());
				
				if (callbacks != null)
					try {
						for(InterfaceCallbacks sc : callbacks) sc.nextPlayer(next);
					} catch (RemoteException e) { e.printStackTrace(); }
			}
			@Override
			public void updateScore(Player player) {
				System.out.println("--- Current score: ---");
				printPlayersList();
			}
			@Override
			public void gameOver(Player winner) {
				Collections.sort(playersList, new PlayerComp());
				if (callbacks != null)
					try {
						for(InterfaceCallbacks sc : callbacks) sc.gameOver(winner.getID());
					} catch (RemoteException e) { e.printStackTrace(); }
				System.out.println("-- Winner: " + winner.getName());
                              
                                
				printPlayersList();
                                JOptionPane.showMessageDialog(null, "The game is over! "+winner.getName()+" is Winner!");
			}
		});
		
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		System.out.println("---  Server is started ---");
		try {

			if (args.length<2) 
                        {
                            obj = new PointsServer(3,2);
                        }
			else 
                        {
                            obj = new PointsServer(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
                        }

                 LocateRegistry.createRegistry(1099).rebind(RMI_NAME, obj);
                 
                 System.out.println("--- Board "+board.getWidht()+" X "+board.getHeight()+" is created ---");

            System.out.println("--- Server is ready ---");

            		System.out.println("-- Waiting for clients");
        			String out = sc.next();
        			if (out.equals("1"))  
        			{ 
        				for(InterfaceCallbacks sc : callbacks) sc.startGame();
        				board.startGame();  gameOn = true;     
                                }
        			else 
                                {
                                    System.out.println("--- Waiting for clients ---");
                                }


            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
	}

	@Override
	public int getWidht() throws RemoteException 
	{ return board.getWidht(); }
	@Override
	public int getHeight() throws RemoteException 
	{ return board.getHeight(); }
	@Override
	public Player getCellPlayer(int i, int j) throws RemoteException 
	{ return board.getCellPlayer(i, j); }
	@Override
	public BoardWall getCellWall(int i, int j, Side side) throws RemoteException 
	{ return board.getCellWall(i, j, side); }
	@Override
	public String getCellInfo(int i, int j) throws RemoteException 
	{ return board.getCellInfo(i, j); }
	@Override
	public String getCellPlayerInfo(int i, int j) throws RemoteException 
	{ return board.getCellPlayerInfo(i, j); }
	@Override
	public int getCurrentPlayerID() throws RemoteException 
	{ return board.getCurrentPlayer().getID(); }
	@Override
	public List<Player> getPlayerslist() throws RemoteException {
		return playersList;
	}
	@Override
	public void cellClick(int i, int j, Side side) throws RemoteException { board.cellClick(i, j, side); }

	private void printPlayersList() {
		for(Player p : playersList) 
			System.out.println(" | " + p.getName() + " - " + p.getScore()+ " |");
	}
	@Override
	public int addPlayer(String name, int figure, String colorS) throws RemoteException {
		if (!gameOn) {
		Color c = new Color(Integer.parseInt(colorS));
		Player p = new Player(name, figure, c);
		board.newPlayer(p);
		playersList.add(p);
		System.out.println("-- " +  name + " is joined --");
		playerCount=playerCount+1;
                if(playerCount==2)
                {
                System.out.println("-- Сurrent number of clients = "+playerCount+" --");
                System.out.println("Session can be started. To do this, press '1'");
                }
                 if(playerCount>2)
                {
                 System.out.println("-- Сurrent number of clients = "+playerCount+". Session can be started (press '1') --");   
                }
                
		return p.getID();	
		} else return -1;
	}

}
