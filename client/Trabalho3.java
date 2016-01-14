package wwwordz.client;

import wwwordz.client.panels.JoinPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Trabalho3 implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	@SuppressWarnings("unused")
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	public static final ManagerServiceInterfaceAsync managerService = GWT
			.create(ManagerServiceInterface.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		DeckPanel deck = new DeckPanel();
		JoinPanel joinPanel = new JoinPanel(deck);
		deck.add(joinPanel);
		deck.showWidget(0);
		RootPanel.get().add(deck);
	}
	
}
