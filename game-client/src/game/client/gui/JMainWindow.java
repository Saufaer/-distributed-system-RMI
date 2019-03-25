package game.client.gui;

import game.client.gui.elements.JBoardArea;

import game.enigne.Player;
import game.server.GameServer;
import game.server.impl.ServerCallbacksAdapter;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;




import java.rmi.RemoteException;


@SuppressWarnings("serial")
public class JMainWindow extends JFrame {

	private JPanel contentPane;
	private GameServer server;
	private static int playerID = -1;
	//private static String playerName;
	
	private JBoardArea gameArea;


	/**
	 * Create the frame.
     * @param gameServer
     * @param name
     * @param figure
     * @param colorS
	 */
	public JMainWindow(GameServer gameServer, String name, int figure, String colorS) {
		setTitle("Goridors 1.0: " + name);
		this.server = gameServer;
		
		try {
			server.addCallbackListener(new ServerCallbacksAdapter() {
				private static final long serialVersionUID = -956862938989184585L;
				@Override
				public void boardChanege() throws RemoteException {
					gameArea.repaint();
				}
				@Override
				public void nextPlayer(Player next) throws RemoteException {

					if (next.getID() == playerID) gameArea.on();
					else gameArea.off();
				}
				@Override
				public void updateScore(int ID, int score) throws RemoteException {

				}
				@Override
				public void startGame() throws RemoteException {

					gameArea.setVisible(true);

				}

				@Override
				public void gameOver(int idWinner) throws RemoteException {
                                    
					gameArea.endGame();
                                          

				}
			});
			playerID = server.addPlayer(name, figure, colorS);
			if (playerID == -1) {
				JOptionPane.showMessageDialog(this, "Game already started!", "Game started", JOptionPane.CLOSED_OPTION);
				System.exit(-1);
			}
        	//playerName = name;
		} catch (RemoteException e) { e.printStackTrace(); }	
		
		initGUI(server);
	}
	
	private void initGUI(GameServer board) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 700, 500);
		contentPane =  new JPanel();

		setContentPane(contentPane);
		
		JPanel gameBoard = new JPanel();
		gameBoard.setLayout(new BorderLayout(0, 0));
		contentPane.add(gameBoard, "2, 2, 4, 4, fill, fill");
		
		gameArea = new JBoardArea(board);
		gameArea.setVisible(false);
		gameBoard.add(gameArea, BorderLayout.CENTER);	
	}

}
