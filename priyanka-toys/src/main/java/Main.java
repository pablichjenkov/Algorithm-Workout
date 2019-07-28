


public class Main {

    public static void main(String[] args) {

        int[] weights = new int[] {1, 2, 3, 21, 7, 12, 14, 21};

        System.out.println("Containers needed: " + Integer.toString(new Main().toys(weights)));

    }

    int toys(int[] weights) {

        weights = mergeSort(weights);

        int containerCounter = 0;
        int containerIdx = 0;

        while(containerIdx < weights.length) {

            containerIdx = nextContainer(containerIdx, weights, 4);

            containerCounter ++;

        }

        return containerCounter;

    }

    int nextContainer(int curContainerIdx, int[] weights, int threshold) {

        int containerLimit = weights[curContainerIdx] + threshold;

        // Always include the first item in the container
        curContainerIdx ++;

        while(curContainerIdx < weights.length) {

            if (weights[curContainerIdx] > containerLimit) {
                break;
            }

            curContainerIdx ++;

        }

        return curContainerIdx;

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

}
