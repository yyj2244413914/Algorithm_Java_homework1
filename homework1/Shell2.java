package homework1;

public class Shell2 extends SortAlgorithm {
    public void sort(Comparable[] objs) {
        int N = objs.length;
        if (N <= 1) return; // 处理空数组或只有一个元素的情况
        
        // 初始化间隔h为最大的递减序列对应间隔，且h < N
        int h = 1;
        while ((2 * h + 1) / 2 < N) {
            h = (2 * h + 1);
        }
        
        // 按照间隔h进行排序，逐步减小h至1
        while (h >= 1) {
            // 对每个h间隔的子数组进行插入排序
            for (int i = h; i < N; i++) {
                // 将objs[i]插入到其所在的h间隔子数组的正确位置
                for (int j = i; j >= h && less(objs[j], objs[j - h]); j -= h) {
                    exchange(objs, j, j - h);
                }
            }
            // 间隔减半（从h变为h/2）
            h /= 2;
        }
    }
    public static void main(String[] args) {
        Comparable[] objs = GenerateData. getRandomData(20);
        Shell2 shell2 = new Shell2();
        shell2.show(objs);
        shell2.sort(objs);
        shell2.show(objs);
        shell2.isSorted(objs);
        if(shell2.isSorted(objs))
            System.out.println("Shell2 sort is sorted");
        else
            System.out.println("Shell2 sort is not sorted");
    }
}