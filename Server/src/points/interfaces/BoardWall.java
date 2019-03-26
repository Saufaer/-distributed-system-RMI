package points.interfaces;

import java.io.Serializable;

public class BoardWall implements Serializable {

	
	private boolean state = false;
	
	public BoardWall() {}
	public BoardWall(boolean state) {
		this.state = state;
	}
	
	public boolean getState(){
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}

}