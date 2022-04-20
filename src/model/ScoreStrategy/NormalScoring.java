package model.ScoreStrategy;

public class NormalScoring implements Scoring{
    private int[] bowlerFrames;
    private int[] curScore;
    private int frameCount;
    private int bowlThrows;
    private int frameScore;

    public NormalScoring(int[] bowlerFrames, int[] curScore, int frameCount){
        this.bowlerFrames = bowlerFrames;
        this.curScore = curScore;
        this.frameCount = frameCount;
        this.bowlThrows = frameCount*2;
        this.frameScore = 0;
    }

    @Override
    public int calculate() {
        frameScore += curScore[bowlThrows];
        frameScore += curScore[bowlThrows+1];

        if(frameCount != 0) {
            bowlerFrames[frameCount] += bowlerFrames[frameCount-1] + frameScore;
        }else{
            bowlerFrames[frameCount] += frameScore;
        }

        return 0;
    }
}
