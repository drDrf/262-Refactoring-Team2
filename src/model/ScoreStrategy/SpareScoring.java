package model.ScoreStrategy;

public class SpareScoring implements Scoring{
    private int[] bowlerFrames;
    private int[] curScore;
    private int frameCount;
    private int bowlThrows;
    private int frameScore;

    public SpareScoring(int[] bowlerFrames, int[] curScore, int frameCount){
        this.bowlerFrames = bowlerFrames;
        this.curScore = curScore;
        this.frameCount = frameCount;
        this.bowlThrows = frameCount*2;
        this.frameScore = 0;
    }

    @Override
    public int calculate() {
        if(true){   //change for test
            if(curScore[bowlThrows+2] != -1){       //if bowled after a spare
                frameScore += 10;
                frameScore += curScore[bowlThrows+2];
            }else{
                frameScore += 10;
            }
        }


        if(frameCount != 0) {
            bowlerFrames[frameCount] += bowlerFrames[frameCount-1] + frameScore;
        }else{
            bowlerFrames[frameCount] += frameScore;
        }

        return 0;
    }
}
