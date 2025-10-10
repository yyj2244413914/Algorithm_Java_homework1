package homework1;

public class CompareQwithT {

    // 随机从数组中选一个索引
    public static int selectRandomFromData(Double[] data) {
        int idx = (int) (Math.random() * data.length);
        return idx;
    }

    /**
     * 生成一个长度为 N 的均匀分布随机数组，
     * 其中 repeatedRange 比例的元素是相同的（即重复值）。
     */
    public static Double[] getRandomRepeatedData(int N, double repeatedRange) {
        Double[] data = GenerateData.getRandomData(N);  // 生成均匀分布数据
        Double repeatedNum = data[selectRandomFromData(data)]; // 随机选一个元素作为重复值

        // 将前 N * repeatedRange 个元素替换为重复值
        for (int i = 0; i < N * repeatedRange; i++) {
            data[i] = repeatedNum;
        }

        // 打乱数组，避免重复值都集中在前面
        GenerateData.shuffle(data, 0, data.length);
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
                    testDataGroups[groupIdx] = getRandomRepeatedData(n, repeatedRange);
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
