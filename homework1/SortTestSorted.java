package homework1;

public class SortTestSorted {
public static void main(String[] args) {
        int T = 5;  // 每组数据规模对应5组不同测试数据
        int[] dataLength = new int[9];
        dataLength[0] = 256;  // 2^8
        for (int i = 1; i < dataLength.length; i++) {
            dataLength[i] = dataLength[i-1] * 2;  // 直到2^16=65536
        }

        SortAlgorithm[] algorithms = new SortAlgorithm[7];
        algorithms[0] = new Insertion();
        algorithms[1] = new Selection();
        algorithms[2] = new Bubble();
        algorithms[3] = new Quick();
        algorithms[4] = new Shell1();
        algorithms[5] = new Shell2();
        algorithms[6] = new Shell3();

        System.out.println("数据规模为2^8-2^16，每组规模对应5组固定测试数据：");
        // 遍历每个数据规模
        for (int lenIdx = 0; lenIdx < dataLength.length; lenIdx++) {
            int n = dataLength[lenIdx];
            System.out.println("----- 数据规模: " + n + " -----");

            // 预生成当前规模的5组测试数据（所有算法共用这5组）
            Double[][] testDataGroups = new Double[T][n];
            for (int groupIdx = 0; groupIdx < T; groupIdx++) {
                testDataGroups[groupIdx] = GenerateData.getSortedData(n);
            }

            // 每个算法在这5组数据上测试并输出结果
            for (SortAlgorithm alg : algorithms) {
                double avgTime = SortTest.test(alg, testDataGroups);
                System.out.printf("%-15s 平均耗时: %.2f 纳秒%n", 
                                 alg.toString(), avgTime);
            }
            System.out.println("----------------------------------------");
        }
    }
}
    

