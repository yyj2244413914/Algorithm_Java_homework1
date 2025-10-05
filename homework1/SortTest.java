package homework1;
public class SortTest {
    // 使用指定的排序算法完成一次排序所需要的时间，单位是纳秒
    public static double time(SortAlgorithm alg, Double[] numbers){
        double start = System.nanoTime();
        alg.sort(numbers);
        double end = System.nanoTime();
        return end - start;
    }
    // 为了避免一次测试数据所造成的不公平，对一个实验完成T次测试，获得T次测试之后的平均时间
    public static double test(SortAlgorithm alg, Double[] numbers, int T)
    {
        double totalTime = 0;
        for(int i = 0; i < T; i++)
            totalTime += time(alg, numbers);
        return totalTime/T;
    }
    // 执行样例，仅供参考。
    // 由于测试数据的规模大小，算法性能，机器性能等因素，请同学们耐心等待每次程序的运行。
    public static void main(String[] args) {
        int[] dataLength = new int[9];
        dataLength[0] = 256;
        for(int i = 1; i < dataLength.length; i++)
            dataLength[i] = dataLength[i-1] * 2;
        double[] elapsedTime = new double[dataLength.length];
        SortAlgorithm []alg = new SortAlgorithm[7];
        alg[0] = new Insertion();
        alg[1] = new Selection();
        alg[2] = new Bubble();
        alg[3] = new Quick();
        alg[4] = new Shell1();
        alg[5] = new Shell2();
        alg[6] = new Shell3();
        for(int i = 0; i < alg.length; i++){
            System.out.println(alg[i].toString() + ":");
            for(int j = 0; j < dataLength.length; j++){
                elapsedTime[j] = test(alg[i], GenerateData.getRandomData(dataLength[j]), 5);
                System.out.println(  " "  + elapsedTime[j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
