package wwwordz.server;

import java.util.List;

import wwwordz.client.ManagerServiceInterface;
import wwwordz.server.game.Round;
import wwwordz.shared.Puzzle;
import wwwordz.shared.Rank;
import wwwordz.shared.WWWordzException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ManagerServiceImpl extends RemoteServiceServlet implements ManagerServiceInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static {
		Round.setJoinStageDuration(5000);
		Round.setPlayStageDuration(5000);
		Round.setReportStageDuration(5000);
		Round.setRankingStageDuration(5000);
	}
	Manager manager = Manager.getInstance();
	
	/* (non-Javadoc)
	 * @see wwwordz.server.ManagerServiceInterface#timeToNextPlay()
	 */
	@Override
	public long timeToNextPlay() {
		return manager.timeToNextPlay();
	}
	/* (non-Javadoc)
	 * @see wwwordz.server.ManagerServiceInterface#register(java.lang.String, java.lang.String)
	 */
	@Override
	public long register(String nick, String password) throws WWWordzException {
		return manager.register(nick, password);
	}
	/* (non-Javadoc)
	 * @see wwwordz.server.ManagerServiceInterface#getPuzzle()
	 */
	@Override
	public Puzzle getPuzzle () throws WWWordzException {
		return manager.getPuzzle();
	}
	/* (non-Javadoc)
	 * @see wwwordz.server.ManagerServiceInterface#setPoints(java.lang.String, int)
	 */
	@Override
	public void setPoints(String nick, int points) throws WWWordzException {
		manager.setPoints(nick, points);
	}
	/* (non-Javadoc)
	 * @see wwwordz.server.ManagerServiceInterface#getRanking()
	 */
	@Override
	public  List<Rank> getRanking() throws WWWordzException {
		return manager.getRanking();
	}
	
}
