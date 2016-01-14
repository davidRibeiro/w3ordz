package wwwordz.server.game;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import wwwordz.server.puzzle.Generator;
import wwwordz.shared.Puzzle;
import wwwordz.shared.Rank;
import wwwordz.shared.WWWordzException;

public class Round {
	static long joinStageDuration, playStageDuration, reportStageDuration,
			rankingStageDuration;
	static Date joinDate, playDate, reportDate, rankingDate;
	static long startRound;
	Date start;
	static Date current;

	Players players;
	HashMap<String, Player> playersHash = new HashMap<String, Player>();
	String timeError = "Now's not the time to use that!";
	String playerError = "Error 404: player not found";
	List<Rank> ranking;

	/**
	 * Constructor for a round It adds the durations of each phase to check when
	 * will each one begin
	 */
	public Round() {
		super();
		players = Players.getInstance();
		start = new Date();
		startRound = start.getTime();
		joinDate = new Date(start.getTime() + joinStageDuration);
		playDate = new Date(joinDate.getTime() + playStageDuration);
		reportDate = new Date(playDate.getTime() + reportStageDuration);
		rankingDate = new Date(reportDate.getTime() + rankingStageDuration);
	}

	/**
	 * Adds each phase's duration and returns this sum
	 * 
	 * @return total round duration
	 */
	public static long getRoundDuration() {
		return joinStageDuration + playStageDuration + reportStageDuration
				+ rankingStageDuration;
	}

	/**
	 * Checks how much time there is left until this playing phase If game
	 * already started, returns time until next playing phase
	 * 
	 * @return time, in milliseconds
	 */
	public long getTimeToNextPlay() {
		current = new Date();
		long time = current.getTime();
		if (current.before(joinDate)) {
			time = joinDate.getTime() - time;
		} else {
			time = getRoundDuration() + time - joinStageDuration;
		}
		return time - 1;
	}

	/**
	 * Registers a player with given nick and password
	 * 
	 * @param nick
	 *            , password
	 * @return time, in milliseconds, to this playing phase
	 * @throws WWWordzException
	 */
	public long register(String nick, String password) throws WWWordzException {
		current = new Date();
		if (current.after(joinDate)) {
			throw new WWWordzException(timeError);
		}
		if (!players.verify(nick, password)) {
			throw new WWWordzException(playerError);
		} else {
			playersHash.put(nick, players.getPlayer(nick));
		}
		return joinDate.getTime() - current.getTime();
	}

	/**
	 * Getter this phase's puzzle
	 * 
	 * @return puzzle
	 * @throws WWWordzException
	 */
	public Puzzle getPuzzle() throws WWWordzException {
		current = new Date();
		if (current.before(joinDate)) {
			throw new WWWordzException(timeError);
		}
		if (current.after(playDate)) {
			throw new WWWordzException(timeError);
		}
		Generator gen = new Generator();
		return gen.generate();
	}

	/**
	 * Gets player with given nick and add the number of points given to
	 * player's points
	 * 
	 * @param nick
	 *            , points
	 * @throws WWWordzException
	 */
	public void setPoints(String nick, int points) throws WWWordzException {
		current = new Date();
		if (current.before(playDate)) {
			throw new WWWordzException(timeError);
		} else if (current.after(reportDate)) {
			throw new WWWordzException(timeError);
		} else
			Players.getInstance().addPoints(nick, points);
	}

	/**
	 * Gets ranking of this round
	 * 
	 * @return collection of ranks
	 * @throws WWWordzException
	 */
	public Collection<Rank> getRanking() throws WWWordzException {
		current = new Date();
		if (current.before(reportDate)) {
			throw new WWWordzException(timeError);
		}
		if (ranking != null) {
			return ranking;
		}
		ranking = new LinkedList<Rank>();
		for (Player player : playersHash.values()) {
			Rank rank = new Rank(player.getNick(), player.getPoints(),
					player.getAccumulated());

			ranking.add(rank);
		}
		Collections.sort(ranking, new Comparator<Rank>() {
			public int compare(Rank x, Rank y) {
				return y.getAccumulated() - x.getAccumulated();
			}
		});
		return ranking;
	}

	/**
	 * Getter for duration of join stage
	 * 
	 * @return joinStageDuration
	 */
	public static long getJoinStageDuration() {
		return joinStageDuration;
	}

	/**
	 * Setter for duration of join stage
	 * 
	 * @param joinStageDuration
	 */
	public static void setJoinStageDuration(long joinStageDuration) {
		Round.joinStageDuration = joinStageDuration;
	}

	/**
	 * Getter for duration of play stage
	 * 
	 * @return playStageDuration
	 */
	public static long getPlayStageDuration() {
		return playStageDuration;
	}

	/**
	 * Setter for duration of play stage
	 * 
	 * @param playStageDuration
	 */
	public static void setPlayStageDuration(long playStageDuration) {
		Round.playStageDuration = playStageDuration;
	}

	/**
	 * Getter for duration of report stage
	 * 
	 * @return reportStageDuration
	 */
	public static long getReportStageDuration() {
		return reportStageDuration;
	}

	/**
	 * Setter for duration of report stage
	 * 
	 * @param reportStageDuration
	 */
	public static void setReportStageDuration(long reportStageDuration) {
		Round.reportStageDuration = reportStageDuration;
	}

	/**
	 * Getter for duration of ranking stage
	 * 
	 * @return rankingStageDuration;
	 */
	public static long getRankingStageDuration() {
		return rankingStageDuration;
	}

	/**
	 * Setter for duration of ranking stage
	 * 
	 * @param rankingStageDuration
	 */
	public static void setRankingStageDuration(long rankingStageDuration) {
		Round.rankingStageDuration = rankingStageDuration;
	}

}
