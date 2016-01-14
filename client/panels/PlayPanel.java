package wwwordz.client.panels;

import java.util.List;

import wwwordz.client.Trabalho3;
import wwwordz.shared.Puzzle;
import wwwordz.shared.Puzzle.Solution;
import wwwordz.shared.Table;
import wwwordz.shared.Table.Cell;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PlayPanel extends Composite {

	private VerticalPanel verticalPanel = new VerticalPanel();
	
	public PlayPanel(final DeckPanel deck, final String nick) {
		initWidget(this.verticalPanel);
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		final HorizontalPanel instructions = new HorizontalPanel();
		final Label instructionsLabel = new Label("Vamos la, " + nick + "! Um clique para seleccionar uma letra, dois cliques para terminar a palavra");
		instructions.add(instructionsLabel);
		final TextBox word = new TextBox();
		final Label pointsLabel = new Label("Pontos: ");
		final Label points = new Label ("0");

		final Grid grid = new Grid(4, 4);
		
		Trabalho3.managerService.getPuzzle(new AsyncCallback<Puzzle>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Erro: " + caught.toString());
			}
			@Override
			public void onSuccess(final Puzzle result) {
				Table table = result.getTable();
				char[][] cells = new char[table.NLINES][table.NCOLUMNS];
				for(Cell c: table){
					cells[c.getLine()][c.getColumn()] = c.getLetter();
				}
				
				for(Cell c: table){
					cells[c.getLine()][c.getColumn()] = c.getLetter();
				}
				for(int i=0; i<4; i++){
					for(int j=0; j<4; j++) {
						String content = String.valueOf(cells[i][j]);
						final Button button = new Button(String.valueOf(0));
						button.setPixelSize(50, 50);
						
						button.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								word.setText(word.getText() + button.getText());
							}
						});
						button.addDoubleClickHandler(new DoubleClickHandler() {
							
							@Override
							public void onDoubleClick(DoubleClickEvent event) {
								String wordChecked = word.getText();
								int lengthWord = wordChecked.length();
								word.setText(word.getText().substring(0, lengthWord-1));
								List<Solution> solutions = result.getSolutions();
								if(solutions.contains(wordChecked)){
									solutions.remove(wordChecked);
									Solution solution = new Solution();
									int pointsInteger = Integer.parseInt(pointsLabel.getText()) + solution.getPoints(lengthWord-1);
									points.setText(String.valueOf(pointsInteger));
								}
							}
						});
						grid.setWidget(i, j, button);
					}
				}

				
			Timer timer = new Timer() {
			
				@Override
				public void run() {
					ReportPanel reportPanel = new ReportPanel(deck, nick, Integer.parseInt(points.getText()), result);
					deck.add(reportPanel);
					deck.showWidget(2);
				}
			};
			timer.schedule(5000);
			}
		});
		verticalPanel.add(instructions);
		horizontalPanel.add(pointsLabel);
		horizontalPanel.add(points);
		verticalPanel.add(grid);
		verticalPanel.add(word);
		verticalPanel.add(horizontalPanel);
	}
}