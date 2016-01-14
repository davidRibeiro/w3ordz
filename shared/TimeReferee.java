package wwwordz.shared;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.DeckPanel;

public class TimeReferee extends Timer {

	@SuppressWarnings("unused")
	private DeckPanel deck;
	@Override
	public void run() {
		
	}
	public TimeReferee(DeckPanel deck){
		this.deck = deck;
	}

}
