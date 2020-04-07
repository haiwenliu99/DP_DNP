package test;

import pretreatment.hausdorffDistance;
import pretreatment.reservoirSample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


//测试函数
public class test {
    

    public static void main(String[] args) {

        System.out.println("args = " + Arrays.deepToString(args));
        System.out.println("test.main");
        System.out.println("args = " + args);





//        int n = 5;
//        int m = 4;
//        int one = 0;
//        int two = 0;
//        int three = 0;
//        int four = 0;
//        int five = 0;
//        int N[] = new int[n];
//        for (int i = 0; i < n; i++)
//            System.out.print((N[i] = i) + "  ");
//        System.out.println();
//        for (int j = 0; j < 10000000; j++) {
//            int l[] = reservoir.reservoir(N, m);
//            for (int q = 0; q < m; q++) {
//                if (l[q] == 0)
//                    one++;
//                if (l[q] == 1)
//                    two++;
//                if (l[q] == 2)
//                    three++;
//                if (l[q] == 3)
//                    four++;
//                if (l[q] == 4)
//                    five++;
//
//            }
//        }



//        System.out.println("0 " + one + "  1 " + two + "   2 " + three + "   3 " + four + "   4 " + five);
        try {
            distancesTest();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void distancesTest()  {
        ArrayList<Double> series1 = new ArrayList<Double>(); //Two sets of arbitrary data to demonstrate how to use the function
        ArrayList<Double> series2 = new ArrayList<Double>();

        series1.add(1.0);
        series1.add(7.0);
        series1.add(-1.0);
        series1.add(3.0);
        series1.add(9.0);


        series2.add(-1.0);
        series2.add(4.0);
        series2.add(5.0);
        series2.add(3.0);
        series2.add(8.0);

        Date date = new Date();


        System.out.println(new reservoirSample());
        System.out.println("Hausdorff distance between series 1 and series 2 is: " + hausdorffDistance.hausdorffDistance(series1, series2));
    }
}



