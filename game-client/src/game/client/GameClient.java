package game.client;

import game.client.gui.JClientDialog;
import game.client.gui.JMainWindow;
import game.server.GameServer;


import java.awt.Color;
import java.awt.EventQueue;
import java.rmi.Naming;
import java.rmi.Remote;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class GameClient {

	private static void trySetLookAndFeel() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
		        if ("Windows".equals(info.getName())) { UIManager.setLookAndFeel(info.getClassName()); break; }
		} catch (Exception e) {
		    try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) { ex.printStackTrace(); }
		}
	}
	
	private static void startGame(String rmi, String name, int figure, String colorS) {
		GameServer server = null;
		try {
                    String localhost    = "127.0.0.1";
		String RMI_HOSTNAME = "java.rmi.server.hostname";
                System.setProperty(RMI_HOSTNAME, localhost);
               
        	Remote RemoteObject = Naming.lookup(rmi);
                
        	server = (GameServer)RemoteObject;
        	if (server == null) throw new Exception("RemoteObject return null");
        	//else JOptionPane.showMessageDialog(null, "Server connected");
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Client exception: " + e.toString(), 
        			"Connection failed", JOptionPane.ERROR_MESSAGE);
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            System.exit(-1);
        }		
		JMainWindow window = new JMainWindow(server, name, figure, colorS);
		window.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		trySetLookAndFeel();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final JClientDialog dialog = new JClientDialog();

							String rmi = dialog.getRmi();
							String name = dialog.getName();
							int f = dialog.getPlayerFigure() + 1;
							Color c = dialog.getPlayerColor();
							String colorS = Integer.toString(c.getRGB());
				
							startGame(rmi, name, f, colorS);

				} catch (Exception e) { e.printStackTrace(); }
			}
		});

	}

}
