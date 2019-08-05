

public class Main {

    public static void main(String[] args) {

        int[] list = new int[]{5,8,14};

        System.out.println("MinMax: " + sherlockAndMinimax(list, 4, 9));

    }

    static int sherlockAndMinimax(int[] arr, int p, int q) {

        int range = q - p + 1;

        int arrLen = arr.length;

        int[] minCol = new int[range];

        for (int M = p; M <= q; M++) {

            // Extract minimum per M row
            int minInRow = Math.abs(arr[0] - M);

            int delta;
            int value1;

            for (int i = 1; i < arrLen; i++) {

                value1 = arr[i];

                if (value1 >= M) {
                    delta = value1 - M;
                } else {
                    delta = M - value1;
                }

                if (delta < minInRow) {
                    minInRow = delta;
                }

            }

            minCol[M - p] = minInRow;

        }


        int minRowInCol = minCol[0];
        int minRowInColIdx = 0;
        int value = 0;

        for (int i = 1; i < range; i++) {

            value = minCol[i];

            if (value > minRowInCol) {
                minRowInCol = value;
                minRowInColIdx = i;

            }

        }

        return p + minRowInColIdx;

    }

}
