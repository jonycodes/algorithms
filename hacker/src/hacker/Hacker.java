/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacker;

import static java.lang.StrictMath.abs;
import java.math.BigInteger;
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

        test("Matrix sum is"+ matrixSum());
        //plusMinus();
        //test(factorial(scan()));
    }


    //Diagonal Difference of one NxN matrix

    public static int matrixSum() {
        int counter = 0;
        int sum = 0;
        int sum1 = 0;
        Scanner in = new Scanner(System.in);
        int pointer = in.nextInt();
        in.nextLine();
        String s = "";
        int[] array = new int[pointer*pointer]; //final array
        while(counter < pointer){
            s += in.nextLine()+" ";
            counter++;
        }

        counter =0; // reset counter
        int counter1 = 0; // start second counter
        while(counter < pointer*pointer) {
            String[] copied = getValue(s, counter1);
            int number = Integer.parseInt(copied[0]);
            counter1 = Integer.parseInt(copied[1]);
            array[counter] = number;
            //test(array[counter]);
            counter++;
        }

        int pos = 0; // position in the matrix
        counter = 0; // Reset counter
        int jump = pointer + 1;  //next diagonal position
        while (counter < pointer) {  // Loop through the array
            sum += array[pos];
            pos += jump;
            counter++;
            //test(sum);
        }

        pos = pointer - 1;  //advanced backwards position in the matrix
        jump = pointer - 1; //next diagonal position
        counter = 0;        //Resets counter
        while (counter < pointer) {   //loop through the aarray
            sum1 += array[pos];
            pos += jump;
            counter++;
            //test(sum1);
        }
        return abs(sum1 - sum); //returns absolute value
    }


    //Returns individual value from a String in an array at pos(0) and returns pointer in the array after the value at pos(1)
    static String[] getValue(String s, int counter){
        String[] array = new String[2];
        String value = "";
        if(s.charAt(counter) == ' '){
            counter++;
        }
       //test("counter is " +counter);
        while(s.charAt(counter) != ' '){
           value += s.charAt(counter);
            counter++;
       }
        //test("Counter on get value "+counter);
        array[0] = value;
        array[1] = String.valueOf(counter);
        return array;
    }


    //Plus minus

    static void plusMinus(){
        Scanner in = new Scanner(System.in);
        float pointer = in.nextInt();
        int counter = 0;
        double positives =0;
        double negatives=0;
        double zeros = 0.000;
        in.nextLine();
        String s = in.nextLine()+" ";
        test(s);
        String[] array;
        int number;
        int counter1 = 0;
        while(counter1 < pointer) {
                array = getValue(s, counter);
                number = Integer.parseInt(array[0]);
                counter = Integer.parseInt(array[1]);
                test("number "+number);
                //test("counter "+counter);
                if (number > 0) {
                    positives++;
                }
                if (number == 0) {
                    zeros++;
                } else if (number < 0) {
                    negatives++;
                }
            counter1++;
         
        }
            float b = pointer;
        System.out.println();
        System.out.format("%.3f%n",positives/b);
        System.out.format("%.3f%n",negatives/b);
        System.out.format("%.3f%n",zeros/b);
    }


//Extra long Factorial returns N!

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

    static double scanDouble() {
        Scanner in = new Scanner(System.in);
        return in.nextDouble();
    }

    static int scan(){
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    static void test(int s) {
        System.out.println("Test value: " + s);
    }

    static void test(String s) {
        System.out.println("Test value: " + s);
    }

    static void test(int[] s) {
        System.out.println("Test value: " + s);
    }

    static void test(long s){System.out.println("Test value is: "+ s);}

    static void test(BigInteger s){System.out.println("test value is: " + s);}
}
