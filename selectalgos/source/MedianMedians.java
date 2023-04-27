package selectalgos.source;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;


/**
 * applies median of medians split to an array
 */
public class MedianMedians extends SortingAlgo{
    /**
     * compares the element chosen by the medians of medians algorithm to the
     * element in the same place in the known valid sorted version
     * 
     * @param k list index
     * @return if the median of medians algo chooses the correct element
     */
    @Override
    public boolean select(int k) {
        return mm(k, orig) == knownSorted[k];
    }

    // uses quick sort with a pivot determined by median of medians
    //TODO: last element of medians is sometimes null
    //TODO: arr is sometimes len 0?
    private int mm(int k, Integer[] arr) {
        if (arr.length <= 5) {
            Arrays.sort(arr);
            return arr[k];
        }
        
        ArrayList<Integer> medians = new ArrayList<Integer>();

        for (int i = 0; i < arr.length; i += 5) {
            int segLength = (i + 5) < arr.length ? 5 : (arr.length - i);
            // an array is more efficient here because segLength always < 5
            // (array list min size is 10)
            Integer[] segment = new Integer[segLength];
            System.arraycopy(arr, i, segment, 0, segLength);
            medians.add(mm(segment.length / 2, segment));
        }

        Collections.sort(medians);
        int pivot = mm(arr.length / 10,
            medians.toArray(new Integer[medians.size()]));

        ArrayList<Integer> arrLow = new ArrayList<Integer>();
        ArrayList<Integer> arrEq = new ArrayList<Integer>();
        ArrayList<Integer> arrHigh = new ArrayList<Integer>();

        for (int elem : arr) {
            if (elem < pivot) {
                arrLow.add(elem);
            } else if (elem > pivot) {
                arrHigh.add(elem);
            } else {
                arrEq.add(elem);
            }
        }

        //TODO: this is probably more efficient with an arraylist
        if (k < arrLow.size()) {
            return mm(k, arrLow.toArray(new Integer[arrLow.size()]));
        } else if (k > (arrLow.size() + arrEq.size())) {
            return mm(k - arrLow.size() - arrEq.size(),
                arrHigh.toArray(new Integer[arrHigh.size()]));
        } else {
            return pivot;
        }
    }

    // median of medians is not a sorting algorithm
    protected Integer[] sort(Integer[] arr) {
        return orig;
    }
}
