import java.io.File;
import java.util.Arrays;

public class dirlist {
    public static void main(String[] args) {
        File testTxts = new File(".\\testarr");
        String[] testNames = testTxts.list();
        System.out.println(Arrays.toString(testNames));
    } 
}
