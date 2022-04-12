package com.hewlett;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList<Integer> mergeSort(int[] a, int[] b, int indexA, int indexB, ArrayList<Integer> c) {
        if (c.size() == a.length + b.length) {
            return c;
        } else if (a.length == indexA) {
            for (int i = indexB; i < b.length; i++) {
                c.add(b[i]);
            }

            System.out.println(c);
            return c;
        } else if (b.length == indexB) {
            for (int i = indexA; i < a.length; i++) {
                c.add(a[i]);
            }

            System.out.println(c);
            return c;
        } else {
            if (a[indexA] > b[indexB]) {
                c.add(a[indexA]);
                indexA++;
            }
            else if (a[indexA] < b[indexB]) {
                c.add(b[indexB]);
                indexB++;
            }
            else if (a[indexA] == b[indexB]) {
                c.add(a[indexA]);
                indexA++;
                indexB++;
            }
            System.out.println(indexA + " " + indexB + " " + c);

            return mergeSort(a, b, indexA, indexB, c);
        }
    }

    public static void main(String[] args) {
        int[] a = {27, 24, 23, 15, 10, 8};
        int[] b = {84, 81, 35, 25, 14, 11, 8, 5, 2};
        ArrayList<Integer> c = new ArrayList<>();

        mergeSort(a, b, 0, 0, c);
    }
}
