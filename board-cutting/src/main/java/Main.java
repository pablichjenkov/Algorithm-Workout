import java.math.BigInteger;

public class Main {

    int[] cost_y, cost_x;

    int[] segmentCountPerYLine;

    int[] segmentCountPerXLine;

    public static void main(String[] args) {

        int[] cost_y = new int[]{2, 1, 3, 1, 4};

        int[] cost_x = new int[]{4, 1, 2};

        int minTotalCost = new Main().start(cost_y, cost_x);

        System.out.println("Minimum cost is: " + minTotalCost);

    }

    int start(int[] cost_y, int[] cost_x) {
        this.cost_y = cost_y;
        this.cost_x = cost_x;
        this.segmentCountPerYLine = new int[cost_y.length];
        this.segmentCountPerXLine = new int[cost_x.length];

        initSegmentCount(segmentCountPerYLine);
        initSegmentCount(segmentCountPerXLine);

        CutInfo[] cutSeq = new CutInfo[cost_y.length + cost_x.length];

        int yLen = cost_y.length;

        for (int i=0; i<yLen; i++) {

            cutSeq[i] = new CutInfo(i, cost_y[i], true);

        }

        for (int i=0; i<cost_x.length; i++) {

            cutSeq[yLen + i] = new CutInfo(i, cost_x[i], false);

        }

        insertionSort(cutSeq, (left, right) -> left.cost > right.cost);


        BigInteger totalCost = BigInteger.ZERO;

        for (CutInfo cutInfo : cutSeq) {

            if (cutInfo.verticalY) {
                totalCost = totalCost.add(BigInteger.valueOf(cutRow(cutInfo.boardPosition)));
            } else {
                totalCost = totalCost.add(BigInteger.valueOf(cutCol(cutInfo.boardPosition)));
            }

        }

        return totalCost.mod(BigInteger.valueOf(10*1000L*1000L*1000L)).intValue();

    }

    void initSegmentCount(int[] segmentCountArray) {

        for (int i = 0; i<segmentCountArray.length; i++) {

            segmentCountArray[i] = 1;

        }

    }

    int cutRow(int rowY) {

        int cost = cost_y[rowY] * segmentCountPerYLine[rowY];

        increaseSegmentCountX();

        return cost;
    }


    int cutCol(int colX) {

        int cost = cost_x[colX] * segmentCountPerXLine[colX];

        increaseSegmentCountY();

        return cost;

    }

    void increaseSegmentCountX() {

        for (int i = 0; i<segmentCountPerXLine.length; i++) {
            segmentCountPerXLine[i] ++;
        }

    }

    void increaseSegmentCountY() {

        for (int i = 0; i<segmentCountPerYLine.length; i++) {
            segmentCountPerYLine[i] ++;
        }

    }

    public <T> void insertionSort(T[] array, Comparator<T> comp) {

        int i = 1;

        while (i < array.length) {

            int j = i;

            while (j > 0 && comp.compare(array[j], array[j-1]) ) {

                swap(array, j-1, j);
                j--;
            }

            i++;
        }

    }

    private  <T> void swap(T[] arr, int left, int right) {

        if (left != right) {
            T temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

    }

}
