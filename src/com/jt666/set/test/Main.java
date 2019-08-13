package com.jt666.set.test;

import com.jt666.set.BSTSet;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        String filePath1="F:\\Projects\\DataStructures\\src\\com\\jt666\\set\\test\\pride-and-prejudice.txt";
        if(FileOperation.readFile(filePath1, words1)) {
            System.out.println("Total words: " + words1.size());

            BSTSet<String> set1 = new BSTSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();


        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
       String filePath2="F:\\Projects\\DataStructures\\src\\com\\jt666\\set\\test\\a-tale-of-two-cities.txt";
        if(FileOperation.readFile(filePath2, words2)){
            System.out.println("Total words: " + words2.size());

            BSTSet<String> set2 = new BSTSet<>();
            for(String word: words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }
    }
}
