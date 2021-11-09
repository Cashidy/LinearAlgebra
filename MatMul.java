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


    public static double[] multiply(double[][] matrix, double[] inVector) {
        double[] outVector = new double[inVector.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                outVector[i] += matrix[i][j] * inVector[j];
            }
        }
        return outVector;
    }
    public static double[][] multiply(double[][] mat1, double[][] mat2) {
        double[][] outMat = new double[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                for (int k = 0; k < mat1[i].length; k++) {
                    outMat[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return outMat;
    }
    public static double[] multiply(double num, double[] inVec) {
        double[] outVec = new double[inVec.length];
        for (int i = 0; i < inVec.length; i++) {
            outVec[i] = num * inVec[i];
        }
        return outVec;
    }
    public static double[][] multiply(double num, double[][] inMat) {
        double[][] outMat = new double[inMat.length][inMat[0].length];
        for (int i = 0; i < inMat.length; i++) {
            for (int j = 0; j < inMat[i].length; j++) {
                outMat[i][j] = num * inMat[i][j];
            }
        }
        return outMat;
    }

    public static double[] add(double[] a, double[] b) {
        if (a.length == b.length) {
            double[] sum = new double[a.length];
            for (int i = 0; i < a.length; i++) {
                sum[i] = a[i] + b[i];
            }
            return sum;
        }
        throw new IllegalArgumentException("tried to add vectors of varying dimension");
    }
    public static double[][] add(double[][] a, double[][] b) {
        if (a.length == b.length && a[0].length == b[0].length) {
            double[][] sum = new double[a.length][a[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    sum[i][j] = a[i][j] + b[i][j];
                }
            }
            return sum;
        }
        throw new IllegalArgumentException("tried to add two matricies of varying size");
    }

    public static double[][] identity(int size) {
        double[][] iden = new double[size][size];
        for (int i = 0; i < size; i++) {
            iden[i][i] = 1;
        }
        return iden;
    }

    public static double[] copy(double[] vector) {
        double[] copy = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            copy[i] = vector[i];
        }
        return copy;
    }
    public static double[][] copy(double[][] matrix) {
        double[][] copy = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                copy[i][j] = matrix[i][j];
            }
        }
        return copy;
    }

    public static double[][] rref(double[][] matrix) {
        double[][] rrefMat = copy(matrix);
        for (int i = 0; i < matrix.length; i++) {
            boolean nonZero = false;
            for (int ii = 0; ii < matrix[i].length; ii++) {
                if (rrefMat[i][ii] != 0) {
                    rrefMat[i] = multiply(Math.pow(rrefMat[i][ii], -1), rrefMat[i]);
                    for (int j = 0; j < matrix.length; j++) {
                        if (j == i) {
                            continue;
                        }
                        double toMul = -1 * rrefMat[j][ii];
                        rrefMat[j] = add(rrefMat[j], multiply(toMul, rrefMat[i]));
                    }
                    nonZero = true;
                    break;
                }
            }
            if (!nonZero) {
                double[] temp = copy(matrix[i]);
                for (int ii = i; ii < matrix.length - 1; ii++) {
                    matrix[ii] = copy(matrix[ii + 1]);
                }
                matrix[matrix.length - 1] = temp;
            }
        }
        for (int i = 1; i < rrefMat.length; i++) {
            for (int j = i; j > 0; j--) {
                int zerosA = 0;
                for (int k = 0; k < rrefMat[0].length; k++) {
                    if (rrefMat[j][k] != 0) {
                        break;
                    }
                    zerosA++;
                }
                int zerosB = 0;
                for (int k = 0; k < rrefMat[0].length; k++) {
                    if (rrefMat[j - 1][k] != 0) {
                        break;
                    }
                    zerosB++;
                }
                if (zerosA < zerosB) {
                    double[] temp = copy(rrefMat[j]);
                    rrefMat[j] = copy(rrefMat[j - 1]);
                    rrefMat[j - 1] = temp;
                }
            }
        }
        return rrefMat;
    }

    public static double[][] invert(double[][] mat) {
        if (mat.length == mat[0].length) {
            double[][] matI = new double[mat.length][mat.length * 2];
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    matI[i][j] = mat[i][j];
                    if (i == j) {
                        matI[i][j + mat.length] = 1;
                    } else {
                        matI[i][j + mat.length] = 0;
                    }
                }
            }
            double[][] rrefMatI = rref(matI);
            //check to see if first half is identity
            double[][] arcMat = new double[mat.length][mat.length];
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    arcMat[i][j] = rrefMatI[i][j + mat.length];
                }
            }
            return arcMat;
        }
        throw new IllegalArgumentException("tried to take the inverse of a non square matrix");
    }

    public static void print(double[] vector) {
        System.out.print("[" + vector[0]);
        for (int i = 1; i < vector.length; i++) {
            System.out.print(", " + vector[i]);
        }
        System.out.println("]");
    }
    public static void print(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            print(matrix[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        project3();

        
        double[][] matA = {{  1,  0,  0},
                           {  0,  1,  0},
                           {  0,  1,  1}};

        double[][] matB = {{  3,  2, -1},
                           { -7,  1,  0},
                           { 12,  0,  1}};

        double[][] matC = {{  4,  2, -1,  1},
                           { -8, -2,  0,  2},
                           { 12,  0,  3,  3},
                           {  5,  1, -1,  4}};

        double[][] matD = {{  2,  9,  0,  7},
                           { -1, 12,  3,  1},
                           {  8,  4, 13, -2}};
                           
        double[][] matAB = multiply(matA, matB);
        double[][] matBA = multiply(matB, matA);

        double[] vecX = {1, 2, 3};
        double[] vecB = multiply(matA, vecX);

        double[][] mat3A = multiply(3, matA);

        double[][] rrefC = rref(matC);

        double[][] rrefD = rref(matD);

        





        /*
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
        */
    }

    public static void project3() {

        double[][] t = {{.879652, .0382096, .0524704, .00078604},
                        {.0212174, .800218, .0040833, .0143059},
                        {.098087, .0272926, .880155, .0526647},
                        {.00104348, .134279, .0632911, .932243}};
        double[][] s = {{.391875, .688637, -69.1855, -.637083},
                        {.129365, -.806542, -61.423, -.00618741},
                        {.789631, -.882094, 129.609, -.35673},
                        {1, 1, 1, 1}};
        double[][] d = {{1, 0, 0, 0},
                        {0, .768831, 0, 0},
                        {0, 0, .815268, 0},
                        {0, 0, 0, .90817}};
        double[][] sInv = {{.432737, .432737, .432737, .432737},
                           {.279495, -.674137, -.171157, .112833},
                           {-.00268715, -.00654206, .00318552, -.000616045},
                           {-.709545, .247941, -.264766, .455045}};

        double[][] tn = identity(4);
        for (int i = 0; i < 1000; i++) {
            tn = multiply(tn, t);
        }
        
        double[] x1 = {1, 0, 0, 0};
        double[] x2 = {.1, .8, .03, .07};
    }
}
