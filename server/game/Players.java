package wwwordz.server.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import wwwordz.shared.WWWordzException;

public class Players implements Serializable {
	private static final long serialVersionUID = 1L;
	static String homePath = "user.dir";
	static String playersSer = "players.ser";
	static File home;
	static File playersFile;
	private static Players playersInstance = null;
	HashMap<String, Player> playerList = new HashMap<String,Player>();
	String errorMessage = "Error 404: player not found";

	/**
	 * Empty constructor for players collection
	 */
	Players() {
		super();
	}

	/**
	 * Getter for directory where data is saved
	 * 
	 * @return home file
	 */
	public static File getHome() {
		return home;
	}

	/**
	 * Setter for directory where data is saved
	 * 
	 * @param new home file
	 */
	public static void setHome(File newHome) {
		home = newHome;
		backUp();
	}

	/**
	 * Creates a backup of file
	 */
	private static void backUp() {
		home = new File(System.getProperty(homePath));
		playersFile = new File(home + "/" + playersSer);
		try {
			FileOutputStream output = new FileOutputStream(playersFile);
			ObjectOutputStream serializer = new ObjectOutputStream(output);
			serializer.writeObject(playersFile);
			serializer.flush();
			serializer.close();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	/**
	 * Getter for players instance
	 * 
	 * @return players instance
	 */
	public static Players getInstance() {
		if (playersInstance == null) {
			playersInstance = restore();
		}
		return playersInstance;
	}

	/**
	 * Tries to read from file, returning instance of this class
	 * 
	 * @return players instance
	 */
	private static Players restore() {
		playersFile = new File(home + "/" + playersSer);
		if (playersFile.canRead()) {
			try {
				FileInputStream input = new FileInputStream(playersFile);
				ObjectInputStream deserializer = new ObjectInputStream(input);
				playersInstance = (Players) deserializer.readObject();
				deserializer.close();
			} catch (IOException | ClassNotFoundException cause) {
				cause.printStackTrace();
			}

		} else
			playersInstance = new Players();
		return playersInstance;
	}

	/**
	 * Verify existence of player with given nick and password, if player
	 * doesn't exist, create new player
	 * 
	 * @param nick
	 *            , password
	 * @return true if player exists and its password is equal to the one given
	 *         (else returns false)
	 */
	boolean verify(String nick, String password) {
		if (playerList.containsKey(nick)) {
			Player player = playerList.get(nick);
			if (player.getPassword().equals(password)) {
				return true;
			} else
				return false;
		}
		Player player = new Player(nick, password);
		playerList.put(nick, player);
		backUp();
		return true;
	}

	/**
	 * Reset given player's current round points
	 * 
	 * @param nick
	 * @throws WWWordzException
	 */
	void resetPoints(String nick) throws WWWordzException {
		if (playerList.containsKey(nick)) {
			Player player = playerList.get(nick);
			player.setPoints(0);
			backUp();
			return;
		}
		throw new WWWordzException(errorMessage);
	}

	/**
	 * Adds given points to player's data
	 * 
	 * @param nick
	 *            , points
	 * @throws WWWordzException
	 */
	void addPoints(String nick, int points) throws WWWordzException {
		if (playerList.containsKey(nick)) {
			Player player = playerList.get(nick);
			player.setPoints(points);
			backUp();
			return;
		}
		throw new WWWordzException(errorMessage);
	}

	/**
	 * Getter for player with given nick
	 * 
	 * @param nick
	 * @return player
	 */
	Player getPlayer(String nick) {
		if (playerList.containsKey(nick)) {
			Player player = playerList.get(nick);
			return player;
		}
		return null;
	}

	/**
	 * Cleans all players and deletes file used
	 */
	void cleanup() {
		playerList.clear();
		while(playersFile.exists()){
			playersFile.delete();
		}
		backUp();
	}
}
