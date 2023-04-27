package selectalgos.source;

import java.util.Collections;;


/**
 * applies quick select to an array
 */
public class QuickSelect extends SortingAlgo {
    /**
     * compares the kth element found with quick select to the kth element of
     * the previously sorted list
     * (quick select is not a sorting algorithm)
     * 
     * @param k list index
     * @return if quick select chose the correct element
     */
    @Override
    public boolean select(int k) {
        Integer[] copy = new Integer[orig.length];
        System.arraycopy(orig, 0, copy, 0, orig.length);
        return quick_split(k, copy, 0, orig.length - 1) == knownSorted[k];
    }
    
    // performs quick sort on a portion of the array depending on the calculated
    // partition
    private static int quick_split(
        int k, Integer[] arr, int rangeMin, int rangeMax){
        if (rangeMax > rangeMin) {
            int partionInd = partition(arr, rangeMin, rangeMax);

            if (partionInd < k) {
                quick_split(k, arr, partionInd + 1, rangeMax);
            } else if (partionInd > k) {
                quick_split(k, arr, rangeMin, partionInd - 1);
            }
        }

        return arr[k];
    }

    // sorts elements around the partition (middle element of this section)
    private static int partition(Integer[] arr, int rangeMin, int rangeMax) {
        int pivot = arr[rangeMax];
        int partionInd = rangeMin - 1;

        for (int i = rangeMin; i < rangeMax; i++) {
            if (arr[i] < pivot) {
                partionInd++;

                int tmp = arr[partionInd];
                arr[partionInd] = arr[i];
                arr[i] = tmp;
            }
        }

        int tmp = arr[partionInd + 1];
        arr[partionInd + 1] = arr[rangeMax];
        arr[rangeMax] = tmp;

        return partionInd + 1;
    }

    // since quick select is not a sorting algorithm, the test sorted array will
    // be identical to the original array
    protected Integer[] sort(Integer[] arr) {
        return arr;
    }
}
