package wwwordz.client;

import java.util.List;

import wwwordz.shared.Puzzle;
import wwwordz.shared.Rank;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ManagerServiceInterfaceAsync {

	void getPuzzle(AsyncCallback<Puzzle> callback);

	void getRanking(AsyncCallback<List<Rank>> callback);

	void register(String nick, String password, AsyncCallback<Long> callback);

	void setPoints(String nick, int points, AsyncCallback<Void> callback);

	void timeToNextPlay(AsyncCallback<Long> callback);

}
