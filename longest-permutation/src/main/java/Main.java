

public class Main {


    public static void main(String[] args) {

        int[] permutationIn = new int[]{2, 3, 4, 3, 2};

        int[] permutationOut = new int[permutationIn.length];

        permutationOut = largestPermutationNoOneInc(2, permutationIn);

        permutationOut = largestPermutationOneInc(2, permutationIn);

        System.out.println("Longest Permutation: " + getPrintedArray(permutationOut));

    }

    static int[] largestPermutationNoOneInc(int k, int[] arr) {

        int maxNextIdx = 0;

        for (int i = 0; i < k && i < arr.length - 1; i++) {

            int maxCurIdx = getMaxIdx(i, arr);

            if (maxCurIdx == maxNextIdx) {

                k++;

            } else {

                swap(arr, maxNextIdx, maxCurIdx);

            }

            maxNextIdx++;

        }

        return arr;

    }

    static int[] largestPermutationOneInc(int k, int[] arr) {

        if (k == 0) {
            return arr;
        }

        int maxNextIdx = 0;

        int from = 0;

        int maxIdx = getMaxIdx(from, arr);

        while (maxIdx == maxNextIdx && from < arr.length - 1) {

            maxNextIdx ++;

            from ++;

            maxIdx = getMaxIdx(from, arr);

        }

        int maxValue = arr[maxIdx];

        swap(arr, maxNextIdx, maxIdx);

        maxNextIdx++;

        k--;


        for (int i = 1; i <= k && i < arr.length; i++) {

            maxIdx = indexOf(maxValue - i, from, arr);

            if (maxIdx == maxNextIdx) {

                k++;

            } else {

                swap(arr, maxNextIdx, maxIdx);

            }

            maxNextIdx++;

            from++;

        }

        return arr;
    }

    static int getMaxIdx(int from, int[] arr) {

        int max = arr[from];
        int maxIdx = from;

        for (int i = from + 1; i < arr.length; i++) {

            if (arr[i] > max) {
                max = arr[i];
                maxIdx = i;
            }

        }

        return maxIdx;
    }

    static int indexOf(int value, int from, int[] arr) {

        for (; from < arr.length; from++) {

            if (arr[from] == value) {

                return from;

            }

        }

        // This shall never get reached if the data complains to the one incremental constraint.
        throw new RuntimeException("Data is not complaining with the one increment rule");

    }

    static private void swap(int[] arr, int left, int right) {

        if (left != right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

    }


    static private String getPrintedArray(int[] arr) {

        StringBuilder text = new StringBuilder();

        for (Integer value : arr) {
            text.append(value.toString()).append("_");
        }

        return text.toString();
    }

}
