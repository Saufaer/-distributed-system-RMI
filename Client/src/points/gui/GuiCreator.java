package points.gui;


import points.interfaces.Player;
import points.interfaces.PointsInterface;
import points.interfaces.ServerCallbacksOverrider;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;




import java.rmi.RemoteException;


@SuppressWarnings("serial")
public class GuiCreator extends JFrame {

	private JPanel contentPane;
	private PointsInterface server;
	private static int playerID = -1;
	
	
	private BoardCreator gameArea;


	/**
	 * Create the frame.
     * @param gameServer
     * @param name
     * @param figure
     * @param colorS
	 */
	public GuiCreator(PointsInterface gameServer, String name, int figure, String colorS) {
		setTitle(name);
		this.server = gameServer;
		
		try {
			server.addCallbackListener(new ServerCallbacksOverrider() {
				
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
		} catch (RemoteException e) { e.printStackTrace(); }	
		
		initGUI(server);
	}
	
	private void initGUI(PointsInterface board) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 600, 600);
		contentPane =  new JPanel();

		setContentPane(contentPane);
		
		JPanel gameBoard = new JPanel();
		gameBoard.setLayout(new BorderLayout(0, 0));
		contentPane.add(gameBoard, "2, 2, 4, 4, fill, fill");
		
		gameArea = new BoardCreator(board);
		gameArea.setVisible(false);
		gameBoard.add(gameArea, BorderLayout.CENTER);	
	}

}
