/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacker;

import static java.lang.StrictMath.abs;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jpina011
 */
public class Hacker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //test(line());
       int[] array = getInt(scan(), "1 2 3 4 5 6 6 54");
       test(array[3]);
    }

    public static long sum() {

        long sum = 0;
        int counter = 0;
        int pointer = scan();
        while (counter < pointer) {
            sum += scan(true);
            counter++;
        }

        return sum;
    }

    static int scan() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    static long scan(boolean s) {
        Scanner in = new Scanner(System.in);
        return in.nextLong();
    }

    static String line() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    static int[] getInt(int i, String s) {
        String value = "";
        int j = 0;
        int [] array = new int[s.length()];
        int counter = 0;
        for (j = 0; j <= s.length(); j++) {
            if (j < s.length() && s.charAt(j) != ' ' ) {
                if (s.charAt(j + 1) == ' ') {
                    array[counter] = Integer.parseInt(String.valueOf(s.charAt(j)));;
                    test(Integer.parseInt(String.valueOf(s.charAt(j))));
                    counter++;
                }
                else{
                    array[counter]= Integer.parseInt(String.valueOf(s.charAt(j)+""+s.charAt(j+1)));
                    test(Integer.parseInt(String.valueOf(s.charAt(j)+""+s.charAt(j+1))));
                    j++;
                    counter++;
                }
            }
            
        }
        
        return array;
    }

    public static int matrixSum() {
        int pointer = scan();
        int counter = 0;
        
        int sum = 0;
        int sum1 = 0;

        int[] array =  getInt(pointer, line());

        int pos = 0; // position in the matrix
        counter = 1; // number of sums
        int jump = pointer + 1;  //next position jump
        while (counter <= pointer) {  // Loop through the array
            //test(pos);
            sum += array[pos];
            pos += jump;
            counter++;
            //test(sum);
        }

        pos = pointer - 1;    //position in the opposite matrix
        jump = pointer - 1; //next position jump
        counter = 1;       //counter number of opperations
        while (counter <= pointer) {   //loop through the aarray
            //test(pos);
            sum1 += array[pos];
            pos += jump;
            counter++;
            //test(sum1);
        }

        return abs(sum1 - sum); //returns absolute value

    }

    static void test(int s) {
        System.out.println("Test value: " + s);
    }

    static void test(String s) {
        System.out.println("Test value: " + s);
    }

    static void test(ArrayList s) {
        System.out.println("Test value: " + s);
    }
}
