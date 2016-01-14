package wwwordz.shared;

import java.io.Serializable;

public class Rank implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nick;
	int points;
	int accumulated;

	/**
	 * Empty constructor for a rank
	 */
	Rank() {
		//super();
	}

	/**
	 * Constructor for a rank with player's name, points of the current round
	 * and total points
	 * 
	 * @param nick
	 *            , points, accumulated
	 */
	public Rank(String nick, int points, int accumulated) {
		//super();
		this.nick = nick;
		this.points = points;
		this.accumulated = accumulated;
	}

	/**
	 * Getter for rank's player's nick
	 * 
	 * @return nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * Setter for rank's player's nick
	 * 
	 * @param nick
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Getter for rank's player's points of current round
	 * 
	 * @return points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Setter for rank's player's points of current round
	 * 
	 * @param points
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Getter for rank's player's total points accumulated
	 * 
	 * @return total points accumulated
	 */
	public int getAccumulated() {
		return accumulated;
	}

	/**
	 * Setter for rank's player's total round accumulated
	 * 
	 * @param total
	 *            points accumulated
	 */
	public void setAccumulated(int accumulated) {
		this.accumulated = accumulated;
	}
}
