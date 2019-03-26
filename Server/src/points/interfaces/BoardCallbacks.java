package points.interfaces;


import java.rmi.Remote;

public interface BoardCallbacks extends Remote {

	void boardChanege();
	void gameOver(Player winner);
	void nextPlayer(Player next);
	void updateScore(Player player);
}
