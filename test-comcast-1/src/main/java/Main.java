import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    static ArrayList<Pair<Integer, Integer>> fixedPoints = new ArrayList<>();

    public static void main(String[] args) {

        int[] values = new int[]{1,5,8,2,10,11,21}; // 1,2,5,8,10,11,21

        int target = minNthValue(values, 8);

        System.out.println("The target is: " + target);

    }

    static int minNthValue(int[] array, int n) {

        if (n >= array.length) {
            return -1;
        }

        int nextMinIdx = 0;

        for (int i = 0; i < array.length - 1; i++) {

            int curMinIdx = extractMinIdx(array, i, array.length);

            swap(array, nextMinIdx, curMinIdx);

            nextMinIdx ++;

            if (nextMinIdx == n) {

                return array[nextMinIdx - 1];

            }

        }

        return -1;

    }

    static int extractMinIdx(int[] array, int startIdx, int endIdx) {

        int curMinIdx = startIdx;
        int curMin = array[startIdx];

        for (int i = startIdx + 1; i < endIdx && i < array.length; i++) {

            if (array[i] < curMin) {

                curMin = array[i];

                curMinIdx = i;

            }

        }

        return curMinIdx;

    }

    static void swap(int[] array, int left, int right) {

        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;

    }


}
