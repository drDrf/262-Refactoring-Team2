package model.lane;

import model.*;
import view.EndGamePrompt;
import view.EndGameReport;

import java.util.Iterator;
import java.util.Vector;

public class FinishedState extends LaneState {
    public FinishedState(Lane lane) {
        super(lane);
    }

    @Override
    public void handleState() {
        Lane lane = super.getLane();
        Party party = lane.getParty();
        EndGamePrompt egp = new EndGamePrompt( ((Bowler) party.getMembers().get(0)).getNickName() + "'s model.Party" );
        int result = egp.getResult();
        egp.distroy();
        egp = null;

        System.out.println("result was: " + result);

        if (result == 1) {					// yes, want to play again
            lane.resetScores();
            lane.resetBowlerIterator();

        } else if (result == 2) {// no, dont want to play another game
            Vector printVector;
            EndGameReport egr = new EndGameReport( ((Bowler)party.getMembers().get(0)).getNickName() + "'s model.Party", party);
            printVector = egr.getResult();
            lane.setPartyAssigned(false);
            Iterator scoreIt = party.getMembers().iterator();
            party = null;
            lane.setPartyAssigned(false);

            lane.publish(lane.lanePublish());

            int myIndex = 0;
            while (scoreIt.hasNext()){
                Bowler thisBowler = (Bowler)scoreIt.next();
                int[][] finalScores = lane.getFinalScores();
                ScoreReport sr = new ScoreReport( thisBowler, finalScores[myIndex++], lane.getGameNumber() );
                sr.sendEmail(thisBowler.getEmail());
                Iterator printIt = printVector.iterator();
                while (printIt.hasNext()){
                    if (thisBowler.getNick() == (String)printIt.next()){
                        System.out.println("Printing " + thisBowler.getNick());
                        sr.sendPrintout();
                    }
                }

            }
        }
    }
}
