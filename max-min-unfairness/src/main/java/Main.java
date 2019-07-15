class Main {

    interface IntComparator {
        boolean compare(int a, int b);
    }

    public static void main(String[] args) {

        int[] array = new int[] { 10,9,8,7,6,5,4,3,2,1 };
        //int[] array = new int[] { 1,4,7,2 };
        //int[] array = new int[] { 10 };
        new Main().maxMin(2, array);

    }

    int maxMin(int k, int[] arr) {

        if (k > arr.length) {
            System.out.println("k cannot be larger than the array");
            return 0;
        }

        printArray(arr);

        //selectionSort(arr);
        //insertionSort(arr);
        //quickSort(arr);
        //arr = mergeSort(arr);

        insertionSort(arr,  (a, b) -> a < b);
        printArray(arr);

        insertionSort(arr, (a, b) -> a > b);
        printArray(arr);

        int minDelta = Integer.MAX_VALUE;

        for (int i = 0; i + k - 1 < arr.length; i++) {

            int nextDelta = arr[i + k - 1] - arr[i];

            if (nextDelta < minDelta) {
                minDelta = nextDelta;
            }

        }

        System.out.println(minDelta);

        return minDelta;
    }

    public void selectionSort(int[] array) {
        int i, j;
        int aLength = array.length;

        for (i = 0; i < aLength - 1; i++) {

            int jMin = i;

            for (j = i + 1; j < aLength; j++) {
                if (array[j] < array[jMin]) {
                    jMin = j;
                }
            }

            if (jMin != i) {
                swap(array, i, jMin);
            }
        }
    }

    public void insertionSort(int[] array, IntComparator comp) {

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

    public void quickSort(int[] array) {
        quickSortR(array, 0, array.length - 1);
    }

    private void quickSortR(int[] array, int lIdx, int rIdx) {

        if (lIdx < rIdx) {

            int nextMinPos = lIdx;
            int pivot = array[rIdx];

            for (int iter = nextMinPos; iter < rIdx; iter ++) {

                if (array[iter] < pivot) {
                    swap(array, nextMinPos, iter);
                    nextMinPos ++;
                }

            }

            swap(array, nextMinPos, rIdx);

            quickSortR(array, lIdx, nextMinPos - 1);
            quickSortR(array, nextMinPos + 1, rIdx);

        }

    }

    public int[] mergeSort(int[] array) {
        return mergeSortR(array, 0, array.length - 1);
    }

    public int[] mergeSortR(int[] array, int left, int right) {

        if (left == right) {
            return new int[] { array[left] };
        }

        int diff = right - left;
        int half = diff / 2;

        int[] mergedLeft = mergeSortR(array, left, left + half);

        int[] mergedRigth = mergeSortR(array, left + half + 1, right);

        int[] mergedAll = new int[mergedLeft.length + mergedRigth.length];

        int mLeftIter = 0;
        int mRightIter = 0;
        int mAllIter = 0;

        boolean keepLooping = true;

        while (keepLooping) {

            boolean keepLoopingLeft = false;
            boolean keepLoopingRigth = false;

            if (mLeftIter < mergedLeft.length) {
                keepLoopingLeft = true;
            }

            if (mRightIter < mergedRigth.length) {
                keepLoopingRigth = true;
            }

            if (keepLoopingLeft && keepLoopingRigth) {

                int leftValue = mergedLeft[mLeftIter];
                int rightValue = mergedRigth[mRightIter];

                if (leftValue < rightValue) {
                    mergedAll[mAllIter] = leftValue;
                    mLeftIter++;
                }
                else {
                    mergedAll[mAllIter] = rightValue;
                    mRightIter++;
                }

            }
            else if (keepLoopingLeft) {
                mergedAll[mAllIter] = mergedLeft[mLeftIter++];
            }
            else if (keepLoopingRigth){
                mergedAll[mAllIter] = mergedRigth[mRightIter++];
            }

            mAllIter ++;
            keepLooping = keepLoopingLeft || keepLoopingRigth;
        }


        return mergedAll;
    }


    private void swap(int[] arr, int left, int right) {

        if (left != right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

    }

    private void printArray(int[] arr) {

        for (int num : arr) {
            System.out.print(num);
            System.out.print("_");
        }

        System.out.println();
    }

}
