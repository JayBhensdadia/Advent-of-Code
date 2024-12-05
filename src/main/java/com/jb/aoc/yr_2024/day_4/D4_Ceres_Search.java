package com.jb.aoc.yr_2024.day_4;

import java.io.File;
import java.util.Scanner;

public class D4_Ceres_Search {

    public static int xmasCount(char[][] input){

        int count = 0;

        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[i].length; j++){

                //check verticals
                if(i - 3 >= 0 ){
                    String word2 = "" + input[i][j] + input[i-1][j] + input[i-2][j] + input[i-3][j];
                    if(word2.equals("XMAS")) count++;
                }

                if(i + 3 < input.length){
                    String word1 = "" + input[i][j] + input[i+1][j] + input[i+2][j] + input[i+3][j];
                    if(word1.equals("XMAS")) count++;
                }

                //check horizontals
                if(j-3 >= 0){
                    String word2 = "" + input[i][j] + input[i][j-1] + input[i][j-2] + input[i][j-3];
                    if(word2.equals("XMAS")) count++;
                }

                if(j+3 < input.length){
                    String word1 = "" + input[i][j] + input[i][j+1] + input[i][j+2] + input[i][j+3];
                    if(word1.equals("XMAS")) count++;
                }

                //check diagonals
                if(i+3 < input.length && j+3 < input.length){
                    String word1 = "" + input[i][j] + input[i+1][j+1] + input[i+2][j+2] + input[i+3][j+3];
                    if(word1.equals("XMAS")) count++;
                }

                if(i+3 < input.length && j -3 >= 0){
                    String word2 = "" + input[i][j] + input[i+1][j-1] + input[i+2][j-2] + input[i+3][j-3];
                    if(word2.equals("XMAS")) count++;

                }

                if(i - 3 >= 0 && j + 3 < input.length){
                    String word3 = "" + input[i][j] + input[i-1][j+1] + input[i-2][j+2] + input[i-3][j+3];
                    if(word3.equals("XMAS")) count++;

                }

                if(i - 3 >= 0 && j-3 >= 0 ){
                    String word4 = "" + input[i][j] + input[i-1][j-1] + input[i-2][j-2] + input[i-3][j-3];
                    if(word4.equals("XMAS")) count++;
                }

            }
        }


        return count;
    }


    public static int xmasCount2(char[][] input){
        int count = 0;

        for(int i = 1; i < input.length - 1; i++){
            for(int j = 1; j < input[i].length - 1; j++){

                    String word1 = "" + input[i-1][j-1] + input[i][j] + input[i+1][j+1];
                    String word2 = "" + input[i-1][j+1] + input[i][j] + input[i+1][j-1];
                    if((word1.equals("MAS") || word1.equals("SAM")) && (word2.equals("MAS") || word2.equals("SAM"))) count++;


            }
        }


        return count;
    }

    public static char[][] readInput(String fileName){
        File file = new File(fileName);
        try{

            Scanner sc = new Scanner(file);

            char[][] input = new char[140][];
            int index = 0;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                char[] charLine = line.toCharArray();
                input[index++] = charLine;
            }

            return input;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        int ans = xmasCount(readInput("src/main/resources/inputs/day_4/input.txt"));
        int ans2 = xmasCount2(readInput("src/main/resources/inputs/day_4/input.txt"));
        System.out.println(ans);
        System.out.println(ans2);
    }
}
