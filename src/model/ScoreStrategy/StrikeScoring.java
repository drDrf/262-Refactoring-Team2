package model.ScoreStrategy;

public class StrikeScoring implements Scoring{
    private int[] bowlerFrames;
    private int[] curScore;
    private int frameCount;
    private int bowlThrows;
    private int frameScore;

    public StrikeScoring(int[] bowlerFrames, int[] curScore, int frameCount){
        this.bowlerFrames = bowlerFrames;
        this.curScore = curScore;
        this.frameCount = frameCount;
        this.bowlThrows = frameCount*2;
        this.frameScore = 0;
    }
    @Override
    public int calculate() {
        //this is for changing the index of the strike multiplier because the last frame
        //has different index format if you get a strike

        frameScore += 10;
        int addition = 2;
        for(int i = 1 ; i<5; i++){
            if(curScore[bowlThrows+1] != -1 && addition >0){
                frameScore += curScore[bowlThrows + 1];
                addition --;
            }
        }


//        int frame8Conditional = 4;
//        if(frameCount == 8){ frame8Conditional = 3;}
//
//        if(frameCount != 9){
//            if(curScore[bowlThrows+2] == 10 && curScore[bowlThrows+frame8Conditional] == 10){   //strikes for next 2 throws
//                frameScore += 30;
//            }
//            else if (curScore[bowlThrows+2] == 10 && curScore[bowlThrows+frame8Conditional] != -1){     //strike and some pins
//                frameScore += 20;
//                frameScore += curScore[bowlThrows+frame8Conditional];
//            }
//            else if (curScore[bowlThrows+2] != -1 && curScore[bowlThrows+3] != -1){
//                frameScore += 10;
//                frameScore += curScore[bowlThrows+2];
//                frameScore += curScore[bowlThrows+3];
//            }
//            else if (curScore[bowlThrows+2] != -1){
//                frameScore += 10;
//                frameScore += curScore[bowlThrows+2];
//            }
//            else{
//                frameScore += 10;
//            }
//        }
//        else{
//            if(curScore[bowlThrows+1] != -1 && curScore[bowlThrows+2] != -1){
//                frameScore += 10;
//                frameScore += curScore[bowlThrows+1];
//                frameScore += curScore[bowlThrows+2];
//            }
//            else if (curScore[bowlThrows+1] != -1){
//                frameScore += 10;
//                frameScore += curScore[bowlThrows+1];
//            }
//            else{
//                frameScore += 10;
//            }
//        }


        if(frameCount != 0) {
            bowlerFrames[frameCount] += bowlerFrames[frameCount-1] + frameScore;
        }else{
            bowlerFrames[frameCount] += frameScore;
        }
        return 0;
    }
}
