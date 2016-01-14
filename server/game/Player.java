package wwwordz.server.game;

import java.io.Serializable;

public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nick, password;
	int points, accumulated;

	/**
	 * Empty constructor for player
	 */
	public Player() {
		//super();
		this.nick = "";
		this.password = "";
		this.points = 0;
		this.accumulated = 0;
	}

	/**
	 * Constructor for a player, given its nick and password
	 * 
	 * @param nick
	 *            , password
	 */
	public Player(String nick, String password) {
		super();
		this.nick = nick;
		this.password = password;
		this.points = 0;
		this.accumulated = 0;
	}

	/**
	 * Constructor for a player, given its nick, password, points for the
	 * current round and total points accumulated
	 * 
	 * @param nick
	 *            , password, points, accumulated
	 */
	public Player(String nick, String password, int points, int accumulated) {
		super();
		this.nick = nick;
		this.password = password;
		this.points = points;
		this.accumulated = accumulated;
	}

	/**
	 * Getter for player's nick
	 * 
	 * @return player's nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * Setter for player's nick
	 * 
	 * @param player
	 *            's new nick
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Getter for player's password
	 * 
	 * @return player's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for player's password
	 * 
	 * @param player
	 *            's new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter for player's points in the current round
	 * 
	 * @return player's points in the current round
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Setter for player's points in the current round
	 * 
	 * @param player
	 *            's points in the current round
	 */
	public void setPoints(int points) {
		this.points = points;
		setAccumulated(points);
	}

	/**
	 * Getter for player's total points accumulated
	 * 
	 * @return player's total points accumulated
	 */
	public int getAccumulated() {
		return accumulated;
	}

	/**
	 * Setter for player's total points accumulated
	 * 
	 * @param points
	 *            to be added
	 */
	public void setAccumulated(int points) {
		this.accumulated += points;
	}

}