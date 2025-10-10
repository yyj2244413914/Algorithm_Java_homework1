package homework1;

public class CompareInverse {
    public static int selectRandomFromData(Double[] data) {
        int idx = (int) (Math.random() * data.length);
        return idx;
    }
    /**
     * 生成一个长度为 N 的倒序数组，
     * 其中 repeatedRange 比例的元素是相同的（即重复值）。
     */
    public static Double[] getInversedRepeatedData(int N, double repeatedRange) {
    Double[] data = GenerateData.getInversedData(N);  // 生成倒序数组
    int key = selectRandomFromData(data);
    Double repeatedNum = data[key]; // 随机选一个元素作为重复值

    int repeatCount = (int) (N * repeatedRange); // 要重复的数量
    int start = Math.max(0, Math.min(N - repeatCount, key)); // 确保不会越界

    // 将一段连续的元素替换为重复值
    for (int i = start; i < start + repeatCount; i++) {
        data[i] = repeatedNum;
    }

    return data;
}


    public static void main(String[] args) {
        int T = 5;  // 每组数据规模对应5组测试
        int[] dataLength = new int[9];
        dataLength[0] = 256;  // 2^8
        for (int i = 1; i < dataLength.length; i++) {
            dataLength[i] = dataLength[i - 1] * 2;  // 直到 2^16 = 65536
        }

        // 三种重复比例
        double[] repeatRates = {0.2, 0.4, 0.6};

        // 要比较的算法
        SortAlgorithm[] algorithms = new SortAlgorithm[]{
                new Quick(),
                new ThreeWayPart()
        };

        System.out.println("数据规模为2^8~2^16，每种重复比例对应5组测试数据：");

        // 遍历每种重复比例
        for (double repeatedRange : repeatRates) {
            System.out.println("========= 重复比例: " + repeatedRange + " =========");

            // 遍历每个数据规模
            for (int lenIdx = 0; lenIdx < dataLength.length; lenIdx++) {
                int n = dataLength[lenIdx];
                System.out.println("----- 数据规模: " + n + " -----");

                // 预生成当前规模的5组测试数据
                Double[][] testDataGroups = new Double[T][n];
                for (int groupIdx = 0; groupIdx < T; groupIdx++) {
                    testDataGroups[groupIdx] = getInversedRepeatedData(n, repeatedRange);
                }

                // 每个算法在这5组数据上测试并输出结果
                for (SortAlgorithm alg : algorithms) {
                    double avgTime = SortTest.test(alg, testDataGroups);
                    System.out.printf("%-15s 平均耗时: %.2f 纳秒%n",
                            alg.toString(), avgTime);
                }

                System.out.println("----------------------------------------\n");
            }
        }
    }
}

