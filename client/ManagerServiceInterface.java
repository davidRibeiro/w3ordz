package wwwordz.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import wwwordz.shared.Puzzle;
import wwwordz.shared.Rank;
import wwwordz.shared.WWWordzException;

@RemoteServiceRelativePath("manager")
public interface ManagerServiceInterface extends RemoteService {

	public abstract long timeToNextPlay();

	public abstract long register(String nick, String password)
			throws WWWordzException;

	public abstract Puzzle getPuzzle() throws WWWordzException;

	public abstract void setPoints(String nick, int points)
			throws WWWordzException;

	public abstract List<Rank> getRanking() throws WWWordzException;

}