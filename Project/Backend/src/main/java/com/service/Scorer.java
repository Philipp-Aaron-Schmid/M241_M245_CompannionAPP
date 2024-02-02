package com.service;

import com.model.Challenge;
import com.model.Play;

public class Scorer {
/**
 * This class scores the submitted sudoku games It builds a string based on the evaluation which can 
 * later be used to display the submitted string in various ways The Scorer loops trough the entire string and chacks 
 * rows collumsn and felds for dupicates.
 * 
 * @param challenge
 * @param play
 */
    public static void calculateScoreAndSet(Challenge challenge, Play play) {
        String playSet = play.getPlaySet().toString();
        String challengeSet = challenge.getChallangeSet().toString();
        int score = 0;
        StringBuilder scoreSetBuilder = new StringBuilder();

        for (int i = 0; i < 81; ++i) {
            if (playSet.charAt(i) == challengeSet.charAt(i)) {
                scoreSetBuilder.append("1");
            } else if (!isDuplicate(playSet, i)) {
                scoreSetBuilder.append("2");
                score++;
            } else {
                scoreSetBuilder.append("3");
                score--;
            }
        }

        play.setPlayScore(score);
        play.setPlaysScoreSet(scoreSetBuilder.toString());
    }

    private static boolean isDuplicate(String playSet, int i) {
        boolean duplicate = isDuplicateRow(playSet, i) || isDuplicateCol(playSet, i) || isDuplicateGrid(playSet, i);
        return duplicate;
    }

    private static boolean isDuplicateRow(String playSet, int current) {
        int begin = (current / 9 * 9);
        int end = begin + 9;
        do {
            if (begin != current && playSet.charAt(begin) == playSet.charAt(current)) {return true;}
            begin++;
        } while (begin < end);
        return false;
    }

    private static boolean isDuplicateCol(String playSet, int current) {
        int col = current % 9;
        for(int row =0; row<73;row += 9){
            if((col+row)!= current && playSet.charAt(col+row) == playSet.charAt(current)){return true;}
            row += 9;
        }

            
        return false;
    }
    private static boolean isDuplicateGrid(String playSet, int current) {
        int col = (current/27)*27;
        int row = ((current%9)/3)*3;
        for (int i = 0;i<27;i += 9){
            for (int j = 0; j<3;j++){
                if (j+row+i+col != current && playSet.charAt(j+row+i+col) == playSet.charAt(current)){return true;}
            }
        }
        return false;
    }

}
