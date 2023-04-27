package selectalgos;

import java.io.File;
import selectalgos.source.*;


/**
 * displays timed test results for each algorithm
 */
public class TestAll {
    /**
     * instantiates all algos and times repeated runs for each test file
     * 
     * @param args args[0] is the number of repeats for each test (default
     * value is 10)
     */
    public static void main(String args[]) {
        int repeats = args.length < 1 ? 10 : Integer.parseInt(args[0]);

        MergeSort mergeSort = new MergeSort();
        QuickSelect quickSelect = new QuickSelect();
        MedianMedians medianMedians = new MedianMedians();
    
        File testTxts = new File(".\\testarr");
        String[] testNames = testTxts.list();
    
        Integer kInd = 0;
        Integer[] kList = {61427, 93388, 119297, 142528, 40684,
            136056, 1841, 46005, 150944, 168949, 60150};
    
        for (String txtName : testNames) {
            System.out.println("\u0007Merge sorting " + txtName);
            mergeSort.load_arr(".\\testarr\\" + txtName);
            long time = mergeSort.time_test(kList[kInd], repeats);
            System.out.println(String.format(
                "\u0007\u0007\tTime for %d repeats = %d ms\n", repeats, time));

            System.out.println("\u0007Quick selecting " + txtName);
            quickSelect.load_arr(".\\testarr\\" + txtName);
            time = quickSelect.time_test(kList[kInd], repeats);
            System.out.println(String.format(
                "\u0007\u0007\tTime for %d repeats = %d ms\n", repeats, time));

            System.out.println("\u0007MM selecting " + txtName);
            medianMedians.load_arr(".\\testarr\\" + txtName);
            time = medianMedians.time_test(kList[kInd], repeats);
            System.out.println(String.format(
                "\u0007\u0007\tTime for %d repeats = %d ms\n", repeats, time));
            
            kInd++;
        }
    }
}
