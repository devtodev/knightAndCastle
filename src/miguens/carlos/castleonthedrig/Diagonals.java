package miguens.carlos.castleonthedrig;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Diagonals {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[][] = new int[n][n];
        for(int a_i=0; a_i < n; a_i++){
            for(int a_j=0; a_j < n; a_j++){
                a[a_i][a_j] = in.nextInt();
            }
        }
        int l = 0;
        int r = 0;
        for (int i = 0; i < n; i++)
        {
            l = l + a[i][i];
            r = r + a[i][n-i-1];
        }
        int t = Math.abs(l - r);
        System.out.println(t);
    }
}
