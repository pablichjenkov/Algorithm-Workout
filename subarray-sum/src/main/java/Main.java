import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("subarray-sum/input.txt"));
            /*
            String line;
            while ((line = br.readLine()) != null) {
                System.out.print(line);
            }
            */

            int testCasesCount = lineToInt(br.readLine());

            while (testCasesCount > 0) {
                testCasesCount --;
                runTestCase(br.readLine(), br.readLine());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int lineToInt(String text) {
        return Integer.parseInt(text.trim());
    }

    public static void runTestCase(String firstLine, String secondLine) {
        String[] splitBySpace = firstLine.split("\\s+");
        int dataLen = lineToInt(splitBySpace[0]);
        int sum = lineToInt(splitBySpace[1]);
        int[] data = new int[dataLen];

        splitBySpace = secondLine.split("\\s+");

        for (int i = 0; i < splitBySpace.length; i++) {
            data[i] = lineToInt(splitBySpace[i]);
        }

        int testResult = findLargestSubArrayToSum(data, sum);
        System.out.println(Integer.toString(testResult));
    }

    public static int findLargestSubArrayToSum(int[] data, int sum) {

        int curSum = 0;
        int largestSub = 0;

        for (int head = 0, tail = 0; head < data.length; head++) {
            curSum += data[head];

            while (curSum > sum) {
                curSum -= data[tail];
                tail++;
            }

            if (curSum == sum) {
                int matchLen = head-tail;
                if (matchLen > largestSub) {
                    largestSub = matchLen;
                }

                curSum -= data[tail];
                tail ++;
            }

        }

        return largestSub + 1;
    }

}
