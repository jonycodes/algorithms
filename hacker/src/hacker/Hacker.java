/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacker;

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
        matrixSum();
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

    public static void matrixSum() {
        int pointer = scan();
        int counter = 0;
        ArrayList<Integer> list = new ArrayList();
        int sum = 0;
        int sum1 = 0;
        int pos = 0;
        int jump = pointer + 1;

        while (counter < pointer * pointer) {
            list.add(scan());
            counter++;
        }
        

        while (pos < pointer) {
            sum += list.get(pos);
            pos += jump;
        }
        
        pos = pointer;
        jump = pointer - 1;
        while (pos < counter) {
            sum1 += list.get(pos);
            pos += jump;
        }

        System.out.print(sum1 + " diagonal " + sum);

    }
}
