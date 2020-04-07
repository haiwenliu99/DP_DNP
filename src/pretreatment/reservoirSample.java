package pretreatment;

import java.util.Random;


//蓄水池抽样
public class reservoirSample {

    public static int[] reservoir(int k[], int m) {
        //b为水池
        int b[] = new int[m];
        if (k.length <= m)
            return new int[0];
        else if (k.length > m) {
            for (int j = 0; j < k.length; j++) {
                if (j < m)
                    b[j] = k[j];     // 将前m个数据存入数组
                else if (j >= m) {
                    //从0-j中随机出一个数
                    int r = new Random().nextInt(j + 1);
                    if (r < m)
                        b[r] = k[j];    //如果随机出的r<水池大小 ，则进行替换
                }
            }
        }
        return b;
    }
}
