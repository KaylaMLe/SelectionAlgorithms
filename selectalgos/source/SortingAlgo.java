package selectalgos.source;

import testutils.source.TestReadWrite;


/**
 * framework for a testable select algo
 */
//TODO: figure out overrides vs abstract classes
abstract public class SortingAlgo {
    /**
     * unsorted array
     */
    protected Integer[] orig;
    /**
     * array sorted by an algorithm known to be valid
     */
    protected Integer[] knownSorted;
    /**
     * array sorted by the algorithm to be tested
     */
    protected Integer[] testSorted;

    /**
     * reads a test and separates the arr returned into the original and sorted
     * version. applies the sorting algo to the original and stores the result.
     * 
     * @param pathToTest filepath to the txt file of the test
     */
    public void load_arr(String pathToTest) {
        Integer[] concat = TestReadWrite.read_test(pathToTest);

        orig = new Integer[concat.length / 2];
        System.arraycopy(concat, 0, orig, 0, orig.length);

        knownSorted = new Integer[orig.length];
        System.arraycopy(concat, knownSorted.length,
            knownSorted, 0, knownSorted.length);

        testSorted = sort(orig);
    }

    /**
     * returns a specific element from the list sorted by the given algo
     * 
     * @param index an integer array position
     * @return the element at index in the test sorted arr
     */
    public int get_from_test(int index){
        return testSorted[index];
    }

    /**
     * returns a specific element from the known sorted list
     * 
     * @param index an integer array position
     * @return
     */
    public int get_from_known(int index) {
        return knownSorted[index];
    }

    /**
     * applies the algorithm to the original arr and returns the item at the kth
     * index
     * 
     * @param k the index of the element to select
     * @return whether or not array output matches the known sorted array at
     * that index
     */
    public boolean select(int k) {
        return testSorted[k] == knownSorted[k];
    }

    /**
     * measures the time needed to find the kth item
     * 
     * @param k index of item to selectt
     * @param repeats number of times to run the algorithm
     * @return total time in milliseconds elapsed after all runs
     */
    public long time_test(int k, int repeats) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < repeats; i++) {
            select(k);
        }

        return System.currentTimeMillis() - start;
    }

    abstract protected Integer[] sort(Integer[] arr);
}
