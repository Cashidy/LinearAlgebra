public class MatMul {

    public static final double[][] TRANS_MAT_A = {{   0, .18, .78, .28, .01,   0,   0,   0},
                                                  {   1,   0,   0,   0,   0,   0,   0,   0},
                                                  {   0,   1,   0,   0,   0,   0,   0,   0},
                                                  {   0,   0,   1,   0,   0,   0,   0,   0},
                                                  {   0,   0,   0,   1,   0,   0,   0,   0},
                                                  {   0,   0,   0,   0,   1,   0,   0,   0},
                                                  {   0,   0,   0,   0,   0,   1,   0,   0},
                                                  {   0,   0,   0,   0,   0,   0,   1,   0}};
    
    public static final double[][] TRANS_MAT_B = {{   0, .06, .59, .55, .05,   0,   0,   0},
                                                  {   1,   0,   0,   0,   0,   0,   0,   0},
                                                  {   0,   1,   0,   0,   0,   0,   0,   0},
                                                  {   0,   0,   1,   0,   0,   0,   0,   0},
                                                  {   0,   0,   0,   1,   0,   0,   0,   0},
                                                  {   0,   0,   0,   0,   1,   0,   0,   0},
                                                  {   0,   0,   0,   0,   0,   1,   0,   0},
                                                  {   0,   0,   0,   0,   0,   0,   1,   0}};


    public static double[] multiply (double[][] matrix, double[] inVector) {
        double[] outVector = new double[inVector.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                outVector[i] += matrix[i][j] * inVector[j];
            }
        }
        return outVector;
    }

    public static void print(double[] vector) {
        System.out.print("[" + vector[0]);
        for (int i = 1; i < vector.length; i++) {
            System.out.print(", " + vector[i]);
        }
        System.out.println("]");
    }

    public static void main(String args[]) {

        double[] population = {100, 100, 100, 100, 100, 100, 100, 100};
        int years = 200;

        for (int i = 0; i < years / 10; i++) {
            population = multiply(TRANS_MAT_A, population);
            print(population);
        }

        System.out.println();
        System.out.println("Expected populations after " + years + " years:");
        print(population);
        double totalPop = 0;
        for (int i = 0; i < population.length; i++) {
            totalPop += population[i];
        }
        System.out.println("Total expected population = " + totalPop);
    }
}
