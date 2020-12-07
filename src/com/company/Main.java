package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("D:\\Теорія прийняття рішень\\matrixLab_3.txt")));
        File f = new File("D:\\Теорія прийняття рішень\\matrixLab_3.txt");     //Creation of File Descriptor for input file
        StringBuffer sb = new StringBuffer();
        String input = new String();


        while (sc.hasNextLine()) {
            input = sc.nextLine();
            sb.append(input);
        }
        String source = sb.toString();
        String str[] = source.split(" ");

        int c = 3;
        int r = 5;
        int[] peop = new int[r];
        String[][] cand = new String[r][c];


        int k = 1;
        for (int i = 0; i < r; i++) {
            peop[i] = Integer.parseInt(str[k]);
            k = k + 4;
        }

        k = 2;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cand[i][j] = str[k];
                if (k == 4 || k == 8 || k == 12 || k == 16)
                    k = k + 2;
                else
                    k = k + 1;
            }
        }
        System.out.println();

        System.out.println("Число виборців | Переваги");
        for (int i = 0; i < r; i++) {
            System.out.print("\t\t" + peop[i] + "\t\t ");
            for (int j = 0; j < c; j++) {
                if (j == 2)
                    System.out.print(cand[i][j]);
                else
                    System.out.print(cand[i][j] + " -> ");
            }
            System.out.println();
        }

        int A=0, B=0, C=0;
        System.out.println();
        System.out.println("Метод Кондорсе");
        System.out.println("A проти C: ");
        if (getBest(cand, peop, "A", "C")=="A")
            A++;
        else C++;
        System.out.println("A проти B: ");
        if (getBest(cand, peop, "A", "B")=="A")
            A++;
        else B++;
        System.out.println("B проти C: ");
        if (getBest(cand, peop, "B", "C")=="B")
            B++;
        else C++;

        getResult(A,B,C);



        System.out.println();
        System.out.println("Метод Борда ");
        A = 0;
        System.out.print("A = ");
        for (int i=1; i<6; i++) {
            A=A+(getInt(i, "A", cand)*peop[i-1]);
            if (i<5)
                System.out.print(getInt(i, "A", cand)+"*"+peop[i-1]+" + ");
            else
                System.out.print(getInt(i, "A", cand) +"*"+peop[i-1]+" = "+ A);
        }
        System.out.println();
        B = 0;
        System.out.print("B = ");
        for (int i=1; i<6; i++) {
            B=B+(getInt(i, "B", cand)*peop[i-1]);
            if (i<5)
                System.out.print(getInt(i, "B", cand)+"*"+peop[i-1]+" + ");
            else
                System.out.print(getInt(i, "B", cand) +"*"+peop[i-1]+" = "+ B);
        }
        System.out.println();
        C = 0;
        System.out.print("C = ");
        for (int i=1; i<6; i++) {
            C=C+(getInt(i, "C", cand)*peop[i-1]);
            if (i<5)
                System.out.print(getInt(i, "C", cand)+"*"+peop[i-1]+" + ");
            else
                System.out.print(getInt(i, "C", cand)+"*"+peop[i-1]+" = "+C);
        }
        System.out.println();
        getResult(A,B,C);


    }



    public static int getInt(int row, String letter, String[][] arr){
        int result =0;
        for (int i=0; i<3; i++){
            if (letter.equals(arr[row-1][i])){

                if (i==0)
                    result = 3;
                if (i==1)
                    result = 2;
                if (i==2)
                    result = 1;
            }
        }
        return result;
    }

    static String getBest(String[][] arr, int[] peop, String letter1, String letter2){
        int a=0, b=0;
        for (int i = 1; i < 6; i++) {
            if (getInt(i, letter1, arr) > getInt(i, letter2, arr)){
                a=a+peop[i-1];
                System.out.println("Округ " + i + ": "+letter1+">"+letter2+" - "+letter1+"="+a);
            }
            else {
                b=b+peop[i-1];
                System.out.println("Округ " + i + ": "+letter2+">"+letter1+" - "+letter2+"="+b);
            }
        }
        if (a>b){
            System.out.println(a+">"+b+", отже "+letter1 +">"+ letter2);
            return letter1;
        }
        else {
            System.out.println(b + ">" + a + ", отже " + letter2 + ">" + letter1);
            return letter2;
        }
    }

    static void getResult(int A, int B, int C){
        System.out.println("\nРезультат:");
        if (C>A)
        {
            if (A>B) {
                if (C > B)
                    System.out.println("C > A > B ");
                else
                    System.out.println("C > B > A ");
            }
        }
        else if (B>C) {
            if (A>C)
                System.out.println("B > A > C ");
            else
                System.out.println("B > C > A ");
        }
        else {
            if (A>B)
                System.out.println("C > A > B ");
            else
                System.out.println("C > B > A ");
        }
    }

}