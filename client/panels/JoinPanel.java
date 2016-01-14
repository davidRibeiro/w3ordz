package wwwordz.client.panels;



import wwwordz.client.Trabalho3;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class JoinPanel extends Composite {

	private VerticalPanel verticalPanel = new VerticalPanel();
	
	public JoinPanel(final DeckPanel deck) {
		initWidget(verticalPanel);
		final HorizontalPanel instructions = new HorizontalPanel();
		final Label instructionsLabel = new Label("Por favor insira o seu nome e password antes de comecar a jogar.");
		instructions.add(instructionsLabel);
		HorizontalPanel nickPanel = new HorizontalPanel();
		HorizontalPanel passwordPanel = new HorizontalPanel();
		
		Label nickLabel = new Label("NICK");
		final TextBox nickTextBox = new TextBox();
		Label passwordLabel = new Label("PASSWORD");
		final PasswordTextBox passwordTextBox = new PasswordTextBox();
		
		nickPanel.add(nickLabel);
		nickPanel.add(nickTextBox);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordTextBox);
		
		Button loginButton = new Button("LOGIN");
		loginButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Trabalho3.managerService.register(nickTextBox.getText(), passwordTextBox.getText(), new AsyncCallback<Long>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Erro: " + caught.toString());
						
					}

					@Override
					public void onSuccess(Long result) {
						Window.alert("Bem-vindo, " + nickTextBox.getText() +"!\nO jogo ira comecar dentro de "+ String.valueOf(result/1000) + " segundos.");
						Timer timer = new Timer(){
							public void run() {
								PlayPanel playPanel = new PlayPanel(deck, nickTextBox.getText());
								deck.add(playPanel);
								deck.showWidget(1);
							}
						};
						timer.schedule(result.intValue());
					}
				});
			}
		
		});
		verticalPanel.add(instructions);
		verticalPanel.add(nickPanel);
		verticalPanel.add(passwordPanel);
		verticalPanel.add(loginButton);
	}
}
