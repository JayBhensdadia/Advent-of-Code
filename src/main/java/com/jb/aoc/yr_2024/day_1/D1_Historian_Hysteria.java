package com.jb.aoc.yr_2024.day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class D1_Historian_Hysteria {


    public static long totalDistanceBetweenLists(ArrayList<Integer> list1, ArrayList<Integer> list2) {

        Collections.sort(list1);
        Collections.sort(list2);

        long totalDistance = 0;

        for(int i = 0; i < list1.size(); i++) {
            totalDistance += Math.abs(list1.get(i) - list2.get(i));
        }

        return totalDistance;
    }

    public static ArrayList<ArrayList<Integer>> readInput(String filepath){
        File file = new File(filepath);
        try {
            Scanner sc = new Scanner(file);
            ArrayList<ArrayList<Integer>> outerList = new ArrayList<>();
            ArrayList<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
//                System.out.println(line);
                String[] split = line.split("   ");
                list1.add(Integer.parseInt(split[0]));
                list2.add(Integer.parseInt(split[1]));

            }
            outerList.add(list1);
            outerList.add(list2);
            return outerList;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }

    }

    public static long similarityScore(ArrayList<Integer> list1, ArrayList<Integer> list2) {

        long similarityScore = 0;
        for (int i = 0; i < list1.size(); i++) {
            int num = list1.get(i);
            int count = 0;
            for (int j = 0; j < list2.size(); j++) {
                if (num == list2.get(j)) { count++; }
            }

            similarityScore += ((long) num * count);
        }

        return similarityScore;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> outerList = readInput("src/main/resources/inputs/day_1/input.txt");
        long totalDistance = totalDistanceBetweenLists(outerList.get(0), outerList.get(1));
        long similarityScore = similarityScore(outerList.get(0), outerList.get(1));
        System.out.println(totalDistance);
        System.out.println(similarityScore);
    }
}
