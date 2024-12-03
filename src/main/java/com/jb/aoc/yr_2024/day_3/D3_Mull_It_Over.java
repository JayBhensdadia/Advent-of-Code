package com.jb.aoc.yr_2024.day_3;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D3_Mull_It_Over {

    // these are invalid instructions

    //mul(4*
    // mul(6,9!
    // ?(12,34)
    // mul ( 2 , 4 )

    // input is something like this
    // need to find un-corrupted mul instructions from these

    //xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))


    // valid mul instructions are like ..

    //mul(678,910)
    //mul(17,1)
    //mul(19,19)
    //mul(101,2)
    //mul(1,2)

    //8 to 12 characters

    // so if you find m at index 'x'
    // then check substring(x,x+3) is mul or not
    // if yes then check every instruction in range { substring(x,x+8) to substring(x,x+12) }
    // if any of them is valid then evaluate it and add it in sum

    public static boolean isValidInstruction(String str){
        String regex = "^mul\\(\\d{1,3},\\d{1,3}\\)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

    public static int evaluate(String input) {
        String removedKeyWord = input.substring(3);
        String removedBrackets = removedKeyWord.substring(1, removedKeyWord.length() - 1);
        String[] numbers = removedBrackets.split(",");
        return Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
    }


    public static long sumOfUncorruptedMull(String input) {
        int i = 0;
        long sum = 0;

        while(i < input.length()-3) {
            if(input.substring(i, i + 3).equals("mul")) {
                for(int k = 8; k <= 12; k++){
                    String temp = input.substring(i, i+k);
//                    System.out.println(temp);
                    if(isValidInstruction(temp)) {
                        int tempAns = evaluate(temp);
                        sum += tempAns;
//                        System.out.println(temp + " " + tempAns);
                        i = i + k - 1;
                    }
                }
                i++;
            }
            else{
                i++;
            }
        }
        return sum;
    }

    // now you need to acknowledge preconditions appearing before
    // mul instructions
    //do() enables all following multiplications
    //don't() disables all following multiplications

    public static long sumOfUncorruptedMull2(String input) {
        int i = 0;
        long sum = 0;
        boolean isMullEnabled = true;

        while(i < input.length()-7) {

            if(input.substring(i,i+4).equals("do()")) {
                isMullEnabled = true;
            }

            if(input.substring(i,i+7).equals("don't()")){
                isMullEnabled = false;
            }

            if( isMullEnabled &&  input.substring(i, i + 3).equals("mul")) {
                for(int k = 8; k <= 12; k++){
                    String temp = input.substring(i, i+k);
//                    System.out.println(temp);
                    if(isValidInstruction(temp)) {
                        int tempAns = evaluate(temp);
                        sum += tempAns;
//                        System.out.println(temp + " " + tempAns);
                        i = i + k - 1;
                    }
                }
                i++;
            }
            else{
                i++;
            }
        }
        return sum;
    }



    // cleaner version
    public static void sumOfUncorruptedMull3(String input) {
        String regex = "mul\\((\\d+),(\\d+)\\)|(do\\(\\))|(don't\\(\\))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        boolean isMullEnabled = true;
        long sum1 = 0;
        long sum2 = 0;

        while(matcher.find()) {
            String instruction = matcher.group();
            System.out.println(instruction);

            if(instruction.equals("do()")){ isMullEnabled = true; }
            else if(instruction.equals("don't()")){ isMullEnabled = false; }
            else{
                sum1 += evaluate(instruction);
                sum2 += isMullEnabled ? evaluate(instruction) : 0;
            }
        }
        System.out.println(sum1 + " " + sum2);
    }

    public static String readInput(String filename){
        File file = new File(filename);
        try{
            Scanner scanner = new Scanner(file);
            StringBuilder input = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                input.append(line);
            }
            scanner.close();
            return input.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
//        long ans = sumOfUncorruptedMull(readInput("src/main/resources/inputs/day_3/input.txt"));
//        long ans2 = sumOfUncorruptedMull2(readInput("src/main/resources/inputs/day_3/input.txt"));
//        System.out.println(ans);
//        System.out.println(ans2);

        sumOfUncorruptedMull3(readInput("src/main/resources/inputs/day_3/input.txt"));
    }
}
