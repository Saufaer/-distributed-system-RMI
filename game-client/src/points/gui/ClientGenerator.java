package points.gui;


import points.server.PointsServer;

import java.awt.Color;
import javax.swing.JDialog;

import javax.swing.JTextField;


import java.util.Random;

@SuppressWarnings("serial")
public class ClientGenerator extends JDialog {
	private final JTextField txtRmi;
	private final JTextField txtName;

	@SuppressWarnings("rawtypes")

	public ClientGenerator() {

				txtRmi = new JTextField();
				txtRmi.setText(PointsServer.RMI_SERVER + PointsServer.RMI_NAME);
				txtName = new JTextField();
				txtName.setText("Client№" + (new Random()).nextInt(1000));
	}

	public String getName() {
		return txtName.getText();
	}
	public String getRmi() {
		return txtRmi.getText();
	}
	public Color getPlayerColor() {      
           Color color=new Color((new Random()).nextInt(255),(new Random()).nextInt(255),(new Random()).nextInt(255));
		return color; 
	}
	public Integer getPlayerFigure() {
        //{"CROSS", "CIRCLE", "TRIANGLE", "SQUARE"};
		return (new Random()).nextInt(4);
                    
	}
	
}
