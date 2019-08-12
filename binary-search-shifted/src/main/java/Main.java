

public class Main {


    public static void main(String[] args) {

        int[] array = new int[]{11,12,13,14,15,16,17,18,19,1,2,3,4,5,6,7,8,9,10};

        System.out.println("Target position is: " + findTarget(array,  7));

    }

    static int findTarget(int[] array, int target) {

        //return findTargetRec_1(array, target, 0, array.length - 1);

        return findTargetRec_2(array, target, 0, array.length - 1);

    }

    // n*Log(n)
    static int findTargetRec_1(int[] array, final int target, final int start, final int end) {

        if (end <= start) {
            return -1;
        }

        int startValue = array[start];

        int endValue = array[end];

        if (startValue == target) { return start; }

        if (endValue == target) { return end; }

        int middleIdx = ((end - start) / 2) + start;

        int middleValue = array[middleIdx];

        if (endValue > startValue) { // sorted segment

            if (target < startValue || target > endValue) {
                return -1;
            }

            if (target < middleValue) {

                return findTargetRec_1(array, target, start + 1, middleIdx);

            } else if (target > middleValue) {

                return findTargetRec_1(array, target, middleIdx, end - 1);

            } else {

                return middleIdx;
            }

        }
        else { // unsorted segment

            if (target < startValue && target > endValue) {
                return -1;
            }

            // Guess the left half to see if it is sorted
            int leftHalf = findTargetRec_1(array, target, start + 1, middleIdx);

            if (leftHalf != -1) {
                return leftHalf;
            }

            // right half
            return findTargetRec_1(array, target, middleIdx, end - 1);
        }

    }

    // Log(n)
    static int findTargetRec_2(int[] array, final int target, final int start, final int end) {

        if (end <= start) {
            return -1;
        }

        int startValue = array[start];

        int endValue = array[end];

        if (startValue == target) { return start; }

        if (endValue == target) { return end; }

        int middleIdx = ((end - start) / 2) + start;

        int middleValue = array[middleIdx];

        if (middleValue > startValue) { // sorted left half

            if (target > startValue && target < middleValue) {

                return findTargetRec_2(array, target, start + 1, middleIdx);

            } else {

                return findTargetRec_2(array, target, middleIdx, end - 1);

            }

        }
        else { // unsorted left half

            if (target > startValue  || target < middleValue) {

                return findTargetRec_2(array, target, start + 1, middleIdx);

            } else {

                return findTargetRec_2(array, target, middleIdx, end - 1);

            }

        }

    }

}
