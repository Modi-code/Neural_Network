import java.util.Random;

public class NetworkTools {
    public static double[] input1D(double[][]  input){
        double[] array= new double[input.length*input[0].length];
        int num = 0;
        for (double[] doubles : input) {
            for (int j = 0; j < input[0].length; j++) {
                array[num] = doubles[j];
                num++;
            }
        }
        return  array;
    }

    public static double generateNumber(double min, double max) {
        Random rand = new Random();
        return rand.nextDouble(min, max);
    }


}