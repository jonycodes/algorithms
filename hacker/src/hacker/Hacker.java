/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacker;

import static java.lang.StrictMath.abs;

import java.math.BigInteger;
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
        plusMinus();
        //test(factorial(scan()));
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

    //Diagonal Difference (not complete)

    static int[] getInt(int i, String s) { //gets input and concatinate them 
        String value = "";
        int[] array = new int[i];
        int counter = 0;
        for (int j = 0; j <= s.length(); j++) {
            test(j);
            if (j < s.length() && s.charAt(j) != ' ') {
                if (s.charAt(j + 1) == ' ') {
                    array[counter] = Integer.parseInt(String.valueOf(s.charAt(j)));;
                    //test(Integer.parseInt(String.valueOf(s.charAt(j))));
                    counter++;
                } else {
                    array[counter] = Integer.parseInt(String.valueOf(s.charAt(j) + "" + s.charAt(j + 1)));
                    //test(Integer.parseInt(String.valueOf(s.charAt(j) + "" + s.charAt(j + 1))));
                    j++;
                    counter++;
                }
            }

        }

        return array;
    }
    
    static int[] copyArray(int pointer,int[] refarray, int[]array){
        int counter = 0;
        while (counter < pointer) {  //copy two arrays together
            int ref = 0;
            test(line());
            refarray = getInt(pointer, line());
            System.arraycopy(refarray,0,array, ref, pointer*counter+1);
            counter++;
        }
        return array;
    }

    public static int matrixSum() {
        int pointer = scan();
        int counter = 0;

        int sum = 0;
        int sum1 = 0;
        int[] refarray = new int[pointer]; //refarray to be copied
        int[] array = new int[pointer]; //final array
        copyArray(pointer, refarray, array); //copy two arrays
        
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

    //Plus minus

    static void plusMinus(){
        int pointer = scan();
        int counter = 0;
        int positives =0;
        int negatives=0;
        int zeros = 0;
        while(counter < pointer){
            int number = scan();
            if(number>0){
                positives++;}
            if(number==0){
                zeros++;
            }
            else if(number<0){
                negatives++;
            }
            counter++;
        }
        double b = pointer;
         test("positives "+positives/b +" Negatives "+negatives/b+" Zeros "+zeros/b);
    }

//Extra long Factorial

    static BigInteger factorial(int n){
       BigInteger factorial = new BigInteger(String.valueOf(1));
        int x = n-1;
        while(x >= 1){
           factorial=factorial.multiply(new BigInteger(String.valueOf(n)));
            factorial = factorial.multiply((new BigInteger(String.valueOf(x))));
            n = n-2;
            x = n-1;
            test(factorial);
       }
        return factorial;
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

    static void test(long s){System.out.println("Test value is: "+ s);}

    static void test(BigInteger s){System.out.println("test value is: " + s);}
}
