package processor;
import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        menu();
        System.out.println("Your choice: ");
        int choice = scanner.nextInt();

        while (true) {
            switch (choice) {
                case 1:
                    addMatrix();
                    break;
                case 2:
                    multiplyMatrixByConstant();
                    break;
                case 3:
                    multiplyMatrix();
                    break;
                case 4:
                    transposeMatrix();
                    break;
                case 5:
                    determinantOfMatrix1();
                    break;
                case 6:
                    inverseMatrix();
                    break;
                case 0:
                    return;
            }
            System.out.println("Your choice: ");
            choice = scanner.nextInt();
        }
    }

    public static void inverseMatrix() {
        System.out.println("Enter matrix size:");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        double[][] matrix = new double[n][m];
        System.out.println("Enter matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        double det = MatrixDetermined(matrix);
        if (det == 0) {
            System.out.println("This matrix doesn't have an inverse.");
        }

        double[][] inversedMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                inversedMatrix[i][j] = Math.pow(-1, i + j) * MatrixDetermined(SubMatrix(matrix, j , i ));
            }
        }
        multiplyMatrixByConstant1(inversedMatrix, 1 / det);
    }

    public static double[][] SubMatrix(double[][] matrix,int a,int b) {
        double[][] submatrix = new double[matrix.length - 1][matrix.length - 1];
        int row = 0, col = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == a) {
                if(a == matrix.length - 1) {
                    return submatrix;
                }
                i++;
            }
            for (int j = 0; j < matrix.length; j++) {
                if (j == b) {
                    if(b == matrix.length - 1 && i == matrix.length - 1) {
                       return submatrix;
                    } else if (b == matrix.length - 1 && i != matrix.length - 1) {
                        continue;
                    }
                    j++;
                }
                submatrix[row][col] = matrix[i][j];
                col++;

            }
            col = 0;
            row++;

        }

        return submatrix;
    }

    public static void printMatrix(double[][] matrix) {
        System.out.println("Matrix:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
                System.out.print(' ');
            }
        }
    }
    public static double MatrixDetermined(double[][] matrix){
        if(matrix.length!=matrix[0].length){
            System.out.println("The operation cannot be performed.");
            return (Integer)null;
        }
        if(matrix.length==1){
            return matrix[0][0];
        }
        double result = 0;
        if(matrix.length>2){
            for(int i=0;i<matrix[0].length;i++){
                result+=Math.pow(-1,i)*matrix[0][i]*MatrixDetermined(SubMatrix(matrix, 0, i));
            }
        }else {
            result = matrix[0][0]*matrix[1][1]-matrix[0][1]*matrix[1][0];
        }
        return result;
    }

    public static void determinantOfMatrix1() {
        System.out.println("Enter matrix size:");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        double[][] matrix = new double[n][m];
        System.out.println("Enter matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        System.out.println("The result is:");
        System.out.println(MatrixDetermined(matrix));

    }

    public static void transposeMatrix() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.println("Your choice: ");
        int choice = scanner.nextInt();
        System.out.println("Enter size of matrix:");

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        double[][] matrix1 = new double[n][m];
        System.out.println("Enter matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix1[i][j] = scanner.nextDouble();
            }
        }
        double[][] res = new double[m][n];
        switch (choice) {
            case 1:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                            res[j][i] = matrix1[i][j];
                    }
                }
                break;
            case 2:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        res[i][j] = matrix1[n - 1 - j][m - 1 - i];
                    }
                }
                break;
            case 3:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                            res[i][j] = matrix1[i][m - 1 - j];
                    }
                }
                break;
            case 4:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        res[i][j] = matrix1[n- 1 - i][ j];
                    }
                }
                break;
        }
        System.out.println("The result is:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(res[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }


    }

    public static void menu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
    }

    public static void multiplyMatrix() {
        System.out.println("Enter size of first matrix:");
        int n1 = scanner.nextInt();
        int m1 = scanner.nextInt();
        double[][] matrix1 = new double[n1][m1];
        System.out.println("Enter first matrix:");
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                matrix1[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("Enter size of second matrix:");
        int n2 = scanner.nextInt();
        int m2 = scanner.nextInt();
        double[][] matrix2 = new double[n2][m2];
        System.out.println("Enter second matrix:");
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < m2; j++) {
                matrix2[i][j] = scanner.nextDouble();
            }
        }
        try {
            if (m1 != n2 ) throw new Exception("ERROR");
            double[][] result = new double[n1][m2];

            for (int i = 0; i < n1; i++) {
                for (int j = 0; j < m2; j++) {
                    for (int k = 0; k < n2; k++) {
                        result[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
            System.out.println("The result is:");
            for (int i = 0; i < n1; i++) {
                for (int j = 0; j < m2; j++) {
                    System.out.print(result[i][j]);
                    System.out.print(' ');
                }
                System.out.println();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void multiplyMatrixByConstant() {
        System.out.println("Enter size of matrix:");
        int n1 = scanner.nextInt();
        int m1 = scanner.nextInt();
        double[][] matrix1 = new double[n1][m1];
        System.out.println("Enter matrix:");
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                matrix1[i][j] = scanner.nextDouble();
            }
        }
        System.out.println("Enter constant:");
        double multiplier = scanner.nextDouble();
        System.out.println("The result is:");
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                System.out.print(multiplier * matrix1[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
    public static void multiplyMatrixByConstant1(double[][] matrix, double n) {
        int n1 = matrix.length;
        int m1 = matrix[0].length;

        double multiplier = n;
        System.out.println("The result is:");
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                System.out.print(multiplier * matrix[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public static void addMatrix() {
        System.out.println("Enter size of first matrix:");
        int n1 = scanner.nextInt();
        int m1 = scanner.nextInt();
        double[][] matrix1 = new double[n1][m1];
        System.out.println("Enter first matrix:");
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                matrix1[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("Enter size of second matrix:");
        int n2 = scanner.nextInt();
        int m2 = scanner.nextInt();
        double[][] matrix2 = new double[n1][m1];
        System.out.println("Enter second matrix:");
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < m2; j++) {
                matrix2[i][j] = scanner.nextDouble();
            }
        }
        try {
            if (n1 != n2 || m1 != m2) throw new Exception("ERROR");
            System.out.println("The result is:");
            for (int i = 0; i < n2; i++) {
                for (int j = 0; j < m2; j++) {
                    System.out.print(matrix1[i][j] + matrix2[i][j]);
                    System.out.print(' ');
                }
                System.out.println();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
