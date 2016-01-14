package wwwordz.server;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import wwwordz.server.game.Round;
import wwwordz.shared.Puzzle;
import wwwordz.shared.Rank;
import wwwordz.shared.WWWordzException;

public class Manager {
	private static Manager manager = null;
	ScheduledExecutorService schedule = Executors
			.newSingleThreadScheduledExecutor();
	Round round = new Round();

	/**
	 * Constructor for manager
	 */
	Manager() {
		schedule.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				round = new Round();

			}
		}, Round.getRoundDuration(), Round.getRoundDuration(),
				TimeUnit.MILLISECONDS);
	}

	/**
	 * Returns instance of this class
	 * 
	 * @return manager
	 */
	public static Manager getInstance() {
		if (manager == null) {
			manager = new Manager();
		}
		return manager;
	}


	public long timeToNextPlay() {
		return round.getTimeToNextPlay();
	}

	public long register(String nick, String password) throws WWWordzException {
		return round.register(nick, password);
	}

	public Puzzle getPuzzle() throws WWWordzException {
		return round.getPuzzle();
	}

	public void setPoints(String nick, int points) throws WWWordzException {
		round.setPoints(nick, points);
	}

	public List<Rank> getRanking() throws WWWordzException {
		return (List<Rank>) round.getRanking();
	}
}
