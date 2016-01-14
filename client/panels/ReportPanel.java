package wwwordz.client.panels;

import java.util.List;

import wwwordz.client.Trabalho3;
import wwwordz.shared.Puzzle;
import wwwordz.shared.Puzzle.Solution;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ReportPanel extends Composite {

	VerticalPanel verticalPanel = new VerticalPanel();
	public ReportPanel(DeckPanel deck, String nick, int points, Puzzle puzzle) {
		initWidget(verticalPanel);
		List<Solution> solutions = puzzle.getSolutions();
		int row = 5;
		int collumns = solutions.size()/5;
		Grid grid = new Grid(row, collumns);
		for(int i=0; i<row; i++){
			for(int j=0; j<collumns; j++){
				grid.setText(i, j, solutions.get(i*j).toString());
			}
		}
		Trabalho3.managerService.setPoints(nick, points, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
