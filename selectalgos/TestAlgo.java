package selectalgos;

import java.io.File;
import selectalgos.source.*;


/**
 * checks merge select's ability to accurately sort an array
 */
public class TestAlgo {
    /**
     * applies a selecting algorithm to all test files in .\testarr
     * 
     * @param args the algorithm to use in the tests
     */
    public static void main(String args[]) {
        // default algorithm is merge sort
        SortingAlgo sortingAlgo = new MergeSort();
        String desc = "Merge sorting";

        if (args.length > 0) {
            if (args[0].equals("quick")) {
                sortingAlgo = new QuickSelect();
                desc = "Quick selecting";
            } else if (args[0].equals("median")) {
                sortingAlgo = new MedianMedians();
                desc = "Median of medians selecting";
            }
        }

        File testTxts = new File(".\\testarr");
        String[] testNames = testTxts.list();

        Integer kInd = 0;
        Integer[] kList = {9999, 89, 33, 0, 2315, 4156, 2514, 368, 4496, 2344};

        for (String txtName : testNames) {
            System.out.println(String.format("\u0007%s %s", desc, txtName));
            sortingAlgo.load_arr(".\\testarr\\" + txtName);


            if (sortingAlgo.select(kList[kInd])) {
                System.out.println("\tPassed!\n");
            } else {
                System.out.println(String.format("\u0007\u0007\tFound "
                    + "%d at index %d instead of %d\n",
                    sortingAlgo.get_from_test(kList[kInd]), kList[kInd],
                    sortingAlgo.get_from_known(kList[kInd])));
            }

            kInd++;
        }
    }
}