package com.alekseyb.splines;

/**
 * Аппроксимация сплайнами
 * Created by Алексей Бычков on 05.02.2018.
 */

public class Spline3D {
    private double x [], y []; // Табличная функция
    private double a0 [], a1 [], a2 [], a3 []; // Параметры сплайна

    // Конструктор
    Spline3D (double xt [], double yt []) {
        int n = xt.length;          // Количество точек аппроксимации
        x = xt; y = yt;
        a0 = new double [n-1];
        a1 = new double [n-1];
        a2 = new double [n-1];
        a3 = new double [n-1];
        double h [] = new double[n-1], a [][] = new double [n-2][n-2], b [] = new double [n-2];

        for (int i = 0; i < h.length; i++) h[i] = x[i+1] - x[i];
        // Составляем систему уравнений для поиска a2
        for (int i = 0; i < a.length; i++) {
            a[i][i] = 2 * (h[i] + h[i+1]);
            if (i < n-3) a[i][i+1] = h[i+1];
            if (i > 0) a[i][i-1] = h[i];
            b[i] = 6 * ((y[i+2]-y[i+1])/h[i+1] - (y[i+1]-y[i])/h[i]);
        }
        SystemOfLinearEquation s = new SystemOfLinearEquation(a, b);
        // a2
        for (int i = 0; i < a2.length-1; i++) a2 [i] = s.x[i]; a2 [a2.length-1] = 0;
        // a0
        for (int i = 0; i < a0.length; i++) a0 [i] = y[i+1];
        // a1
        a1 [0] = (y[1] - y[0]) / h[0] + h[0] * 2*a2[0] / 6;
        for (int i = 1; i < a1.length; i++) a1 [i] = (y[i+1] - y[i]) / h[i] + h[i] * (2*a2[i] + a2[i-1]) / 6;
        // a3
        a3 [0] = a2 [0] / h [0];
        for (int i = 1; i < a3.length; i++) a3 [i] = (a2 [i] - a2 [i-1]) / h [i];
    }

    // Аппроксимирующая функция 3-го порядка (сплайн) S3 (t) = a0 + a1*t + a2*t^2 + a3*t^3
    double s3 (double t) {
        if (t < x[0]) {
            return (y[0]); // экстраполяция за левым краем
        }
        if (t > x[x.length-1]) {
            return (y[y.length-1]); // экстраполяция за правым краем
        }
        for (int i = 0; i < x.length-1; i++)
            if ((t >= x[i]) & (t <= x[i+1]))
                return (a0[i] + a1[i]*(t-x[i+1]) + a2[i]/2*Math.pow(t-x[i+1],2) + a3[i]/6*Math.pow(t-x[i+1],3));
        return 0; // чтобы компилятор не ругался
    }

}
