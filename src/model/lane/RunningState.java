package model.lane;

import model.Bowler;
import model.Pinsetter;
import model.ScoreHistoryFile;

import java.util.Date;
import java.util.Iterator;

public class RunningState extends LaneState {
    public RunningState(Lane lane) {
        super(lane);
    }

    @Override
    public void handleState() {
        Lane lane = super.getLane();
        Iterator bowlerIterator = lane.getBowlerIterator();
        Pinsetter setter = lane.getPinsetter();

        if (bowlerIterator.hasNext()) {
            lane.setCurrentThrower((Bowler)bowlerIterator.next());
            lane.setCanThrowAgain(true);
            lane.setTenthFrameStrike(false);
            lane.setBall(0);

            while (lane.getCanThrowAgain()) {
                setter.ballThrown();		// simulate the thrower's ball hiting
                lane.setBall(lane.getBall() + 1);
            }

            if (lane.getFrameNumber() == 9){
                int[][] finalScores = lane.getFinalScores();
                int bowlIndex = lane.getBowlIndex();
                int[][] cumulScores = lane.getCumulScores();

                finalScores[bowlIndex][lane.getGameNumber()] = cumulScores[bowlIndex][9];

                try{
                    Date date = new Date();
                    String dateString = "" + date.getHours() + ":" + date.getMinutes() + " " + date.getMonth() + "/" + date.getDay() + "/" + (date.getYear() + 1900);
                    ScoreHistoryFile.addScore(lane.getCurrentThrower().getNick(), dateString, new Integer(cumulScores[bowlIndex][9]).toString());
                } catch (Exception e) {System.err.println("Exception in addScore. "+ e );}
            }


            setter.reset();
            lane.setBowlIndex(lane.getBowlIndex() + 1);

        } else {
            lane.setFrameNumber(lane.getFrameNumber() + 1);
            lane.resetBowlerIterator();
            lane.setBowlIndex(0);
            if (lane.getFrameNumber() > 9) {
                lane.setGameFinished(true);
                lane.setGameNumber(lane.getGameNumber() + 1);
            }
        }
    }
}
