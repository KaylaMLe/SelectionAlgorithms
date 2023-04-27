package selectalgos.source;


/**
 * applies merge sort to an array
 */
public class MergeSort extends SortingAlgo{
    /**
     * measures the time needed to find the kth item
     * 
     * @param k index of item to selectt
     * @param repeats number of times to run the algorithm
     * @return total time elapsed after all runs
     */
    @Override
    public long time_test(int k, int repeats) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < repeats; i++) {
            sort(orig);
        }

        return System.currentTimeMillis() - start;
    }

    // recursively splits the array into subarrays of length 1 (sortedness
    // guaranteed) then merges halves in asceending order
    protected Integer[] sort(Integer[] arr) {
        if (arr.length == 1){
            return arr;
        }

        // if the length is odd, make the first half the larger half
        Integer firstHalf = arr.length % 2 == 1
            ? (arr.length / 2) + 1 : arr.length / 2;

        Integer[] arrA = new Integer[firstHalf];
        System.arraycopy(arr, 0, arrA, 0, arrA.length);
        arrA = sort(arrA);

        Integer[] arrB = new Integer[arr.length / 2];
        System.arraycopy(arr, firstHalf, arrB, 0, arrB.length);
        arrB = sort(arrB);

        return merge(arrA, arrB);
    }

    // sorts elements from both input arrays into a single ascending array
    private static Integer[] merge(Integer[] arrA, Integer[] arrB) {
        Integer[] merged = new Integer[arrA.length + arrB.length];
        Integer arrAInd = 0;
        Integer arrBInd = 0;

        for(int i = 0; i < merged.length; i++) {
            if (arrA[arrAInd] < arrB[arrBInd]) {
                merged[i] = arrA[arrAInd];
                arrAInd++;

                if (arrAInd == arrA.length) {
                    System.arraycopy(
                        arrB, arrBInd, merged, i + 1, arrB.length - arrBInd);
                    break;
                }
            } else {
                merged[i] = arrB[arrBInd];
                arrBInd++;

                if (arrBInd == arrB.length) {
                    System.arraycopy(
                        arrA, arrAInd, merged, i + 1, arrA.length - arrAInd);
                    break;
                }
            }
        }

        return merged;
    }
}