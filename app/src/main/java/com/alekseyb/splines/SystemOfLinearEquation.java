package com.alekseyb.splines;

/**
 * Created by Ольга on 05.02.2018.
 */

public class SystemOfLinearEquation {
    // int n;
    // double a [][];
    // double b [];
    double x [];

    // Конструктор
    // Решение системы линейных уравнений матричным методом
    SystemOfLinearEquation (double [][] a, double [] b) {
        int n = a.length;
        x = new double [n];
        // Матричный метод
        double det = getDet (a);         // детерминант
        double [][] a1 = new double [n][n]; // обратная матрица

        if (Math.abs(det) < 1E-4) { return; } // матрица вырождена!!!
        if (n == 1) {
            x[0] = b[0]/a[0][0]; return;
        }
        for (int i = 0; i < n; i++)     // Вычисляем обратную матрицу и транспонируем её
            for (int j = 0; j < n; j++)
                a1 [j][i] = Math.pow(-1, i+j) * getDet (getMinorMatrix (a,i,j));
        for (int i = 0; i < n; i++)     // Перемножаем обратную матрицу и вектор B
            for (int j = 0; j < n; j++)
                x[i] += a1[i][j] * b[j] / det;
    }

    // Вычеркивание из матрицы matrix [][] строки номер im и столбца номер jm
    private double [][] getMinorMatrix (double [][] matrix, int im, int jm) {
        double [][] minor = new double[matrix.length-1][matrix.length-1];
        int l = 0;
        for (int i = 0; i < minor.length; i++, l++) {
            if (i == im) l++;
            int m = 0;
            for (int j = 0; j < minor.length; j++, m++) {
                if (j == jm) m++;
                minor [i][j] = matrix [l][m];
            }
        }
        return minor;
    }

    // Вычисление определителя матрицы рекурсивным методом разложения по строке
    private double getDet (double [][] matrix) {
        double det = 0;
        int n = matrix.length;
        if (n == 1) return (matrix[0][0]);
        if (n == 2) return (matrix[0][0]*matrix[1][1]-matrix[0][1]*matrix[1][0]);
        for (int i = 0; i < n; i++)
            det += Math.pow(-1, i) * matrix[i][0] * getDet (getMinorMatrix (matrix, i, 0));
        return (det);
    }

}
