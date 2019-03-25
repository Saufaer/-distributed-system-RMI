package game.client.gui;


import game.server.GameServer;

import java.awt.Color;
import javax.swing.JDialog;

import javax.swing.JTextField;


import java.util.Random;

@SuppressWarnings("serial")
public class JClientDialog extends JDialog {
	private final JTextField txtRmi;
	private final JTextField txtName;

	@SuppressWarnings("rawtypes")

	public JClientDialog() {

				txtRmi = new JTextField();
				txtRmi.setText(GameServer.RMI_SERVER + GameServer.RMI_NAME);
				txtName = new JTextField();
				txtName.setText("Player" + (new Random()).nextInt(1000));
	}

	public String getName() {
		return txtName.getText();
	}
	public String getRmi() {
		return txtRmi.getText();
	}
	public Color getPlayerColor() {
            int[] mas = {0, 50,70,90, 110,130, 150,170, 200,220,240};
            Color color=new Color(mas[(new Random()).nextInt(10)], mas[(new Random()).nextInt(10)], mas[(new Random()).nextInt(10)]);
		return color; 
	}
	public Integer getPlayerFigure() {
        //{"CROSS", "CIRCLE", "TRIANGLE", "SQUARE"};
		return (new Random()).nextInt(4)-1;
                    
	}
	
}
