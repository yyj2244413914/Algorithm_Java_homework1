package homework1;

public class CompareQwithT {
    public static int selectRandomFromData(Double[] data){
        int idx = (int) (Math.random() * data.length);
        return idx;
    }
    // 生成一个长度为N的均匀分布且含有重复元素的数据序列，根据不同等级的数据规模，重复元素的比例不同
    // 数据规模为2^8-2^16时，重复元素的比例为0.2
    // 数据规模为2^10-2^14时，重复元素的比例为0.4
    // 数据规模为2^16-2^20时，重复元素的比例为0.6
    public static Double[] getRandomRepeatedData(int N){
        Double repeatedrange = 0.0; // 先初始化
        if (N >= 256 && N <= 1024) {
            repeatedrange = 0.2;
        } else if (N >= 2048 && N <= 8192) {
            repeatedrange = 0.4;
        } else if (N >= 16384 && N <= 65536) {
            repeatedrange = 0.6;
        }
        Double[] data = GenerateData.getRandomData(N);
        Double repeatednum = data[selectRandomFromData(data)];
        for(int i=0;i<N*repeatedrange;i++)data[i]=repeatednum;
        GenerateData.shuffle(data, 0, data.length);
        return data;
    }
    public static void main(String[] args) {
        int T = 5;  // 每组数据规模对应5组不同测试数据
        int[] dataLength = new int[9];
        dataLength[0] = 256;  // 2^8
        for (int i = 1; i < dataLength.length; i++) {
            dataLength[i] = dataLength[i-1] * 2;  // 直到2^16=65536
        }
        SortAlgorithm[] algorithms = new SortAlgorithm[] {
            new Quick(),
            new ThreeWayPart()
};

        System.out.println("数据规模为2^8-2^16，每组规模对应5组固定测试数据：");
        // 遍历每个数据规模
        for (int lenIdx = 0; lenIdx < dataLength.length; lenIdx++) {
            int n = dataLength[lenIdx];
            System.out.println("----- 数据规模: " + n + " -----");

            // 预生成当前规模的5组测试数据（所有算法共用这5组）
            Double[][] testDataGroups = new Double[T][n];
            for (int groupIdx = 0; groupIdx < T; groupIdx++) {
                testDataGroups[groupIdx] = getRandomRepeatedData(n);
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