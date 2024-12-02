package com.jb.aoc.yr_2024.day_2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D2_Red_Nosed_Reports {


    // todo: optimize these functions


    public static boolean isIncreasing(List<Integer> list){
        for(int i = 1; i < list.size(); i++){
            if(list.get(i) < list.get(i-1) ){
                return false;
            }
        }
        return true;
    }

    public static boolean isDecreasing(List<Integer> list){
        for(int i = 1; i < list.size(); i++){
            if(list.get(i) > list.get(i-1) ){
                return false;
            }
        }
        return true;
    }

    public static boolean adjacencyTest(List<Integer> list){
        for(int i = 1; i < list.size(); i++){
            int n1 = list.get(i);
            int n2 = list.get(i-1);
            int diff = Math.abs(n1 - n2);
            if(diff < 1 || diff > 3){
                return false;
            }
        }

        return true;
    }

    public static int safeReports(ArrayList<ArrayList<Integer>> reports){

        //each innerlist should be
        // 1. either increasing or decreasing
        // 2. adjacent elements differ by atleast 1 and atmost 3
        // if above two conditions match -> report is safe

        int safeReports = 0;

        for(ArrayList<Integer> report : reports){
            if((isIncreasing(report) || isDecreasing(report)) && adjacencyTest(report)){
                safeReports++;
            }
        }

        return safeReports;
    }


    public static int safeReports2(ArrayList<ArrayList<Integer>> reports){
        int safeReports = 0;

        for(ArrayList<Integer> report : reports){
            if((isIncreasing(report) || isDecreasing(report)) && adjacencyTest(report)){
                safeReports++;
            }else{
                for(int i = 0; i < report.size(); i++){
                    List<Integer> leftHalf = report.subList(0,i);
                    List<Integer> rightHalf = report.subList(i+1,report.size());
                    List<Integer> newList = new ArrayList<>();
                    newList.addAll(leftHalf);
                    newList.addAll(rightHalf);

                    var test = (isIncreasing(newList) || isDecreasing(newList)) && adjacencyTest(newList);

                    if(test){
                        safeReports++;
                        break;
                    }
                }
            }
        }

        return safeReports;
    }

    //0 1 2 3 4
    //1 3 2 4 5
    //8 6 4 4 1


    public static ArrayList<ArrayList<Integer>> readInput(String filename){
        File file = new File(filename);
        try{
            Scanner sc = new Scanner(file);
            ArrayList<ArrayList<Integer>> reports = new ArrayList<>();

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] split = line.split(" ");
                ArrayList<Integer> report = new ArrayList<>();
                for (String s : split) {
                    report.add(Integer.parseInt(s));
                }

                reports.add(report);

            }
            return reports;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {

        int ans = safeReports(readInput("src/main/resources/inputs/day_2/input.txt"));
        int ans2 = safeReports2(readInput("src/main/resources/inputs/day_2/input.txt"));
        System.out.println(ans);
        System.out.println(ans2);
    }
}
