package view;

import model.ControlDesk;
import view.ControlDeskView;

public class drive {

	public static void main(String[] args) {

		int numLanes = 3;
		int maxPatronsPerParty=5;

		ControlDesk controlDesk = new ControlDesk(numLanes);

		ControlDeskView cdv = new ControlDeskView( controlDesk, maxPatronsPerParty);
		controlDesk.subscribe( cdv );

	}
}
