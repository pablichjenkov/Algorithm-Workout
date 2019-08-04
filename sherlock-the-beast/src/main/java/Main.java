import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {

        decentNumber(11);

    }

    static void decentNumber(int n) {

        int div5_Times = n / 3;

        int rest5 = n % 3;

        if (rest5 != 0) {

            if (div5_Times == 0) {
                System.out.print("-1");
                return;
            }

            int div3_Times = 0;

            int rest3 = -1;

            for (int i = 1; i <= div5_Times; i++) {

                int digitsFor3 = i * 3 + rest5;

                div3_Times = digitsFor3 / 5;

                rest3 = digitsFor3 % 5;

                if (rest3 == 0) {

                    print5_Groups(div5_Times - i);

                    print3_Groups(div3_Times);

                    break;
                }

            }

            if (rest3 != 0) { System.out.print("-1"); }

        } else {
            print5_Groups(div5_Times);
        }

    }

    static void print5_Groups(int groups) {

        for (int i = 0; i < groups; i++) {

            System.out.print("5");
            System.out.print("5");
            System.out.print("5");

        }

    }

    static void print3_Groups(int groups) {

        for (int i = 0; i < groups; i++) {

            System.out.print("3");
            System.out.print("3");
            System.out.print("3");
            System.out.print("3");
            System.out.print("3");

        }

    }

}
