package points.interfaces;

import points.interfaces.Player;

import java.rmi.Remote;

public interface BoardCallbacks extends Remote {

	void boardChanege();
	void gameOver(Player winner);
	void nextPlayer(Player next);
	void updateScore(Player player);
}
