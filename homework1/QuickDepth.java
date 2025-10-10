package homework1;

public class QuickDepth extends SortAlgorithm {
    private int depth = 0;       // 全局递归深度计数器
    public int max_depth = 0;    // 寻找最大递归深度

    public void sort(Comparable[] objs) {
        if (objs == null || objs.length == 0) return;
        quickSort(objs, 0, objs.length - 1);
    }

    public void quickSort(Comparable[] objs, int start, int end) {
        depth++; // 进入递归，深度+1
        max_depth = Math.max(max_depth, depth);

        if (start >= end) {
            depth--; // 返回递归，深度-1
            return;
        }

        int medianIndex = getMedianIndex(objs, start, end);
        exchange(objs, start, medianIndex);

        Comparable key = objs[start];
        int i = start;
        int j = end;

        while (i < j) {
            while (i < j && less(key, objs[j])) j--;
            if (i < j) exchange(objs, i, j);

            while (i < j && less(objs[i], key)) i++;
            if (i < j) exchange(objs, i, j);
        }

        quickSort(objs, start, i - 1);
        quickSort(objs, i + 1, end);
        depth--; // 返回递归，深度-1
    }

    /** 找到 start、end 和中间位置三个数中的中间值的索引 */
    private int getMedianIndex(Comparable[] objs, int start, int end) {
        int mid = start + (end - start) / 2;
        // 比较三个位置的元素，返回中间值的索引
        if (less(objs[start], objs[mid])) {
            if (less(objs[mid], objs[end])) {
                return mid; // start < mid < end
            } else if (less(objs[start], objs[end])) {
                return end; // start < end <= mid
            } else {
                return start; // end <= start < mid
            }
        } else {
            if (less(objs[end], objs[mid])) {
                return mid; // end < mid <= start
            } else if (less(objs[end], objs[start])) {
                return end; // mid <= end < start
            } else {
                return start; // mid <= start <= end
            }
        }
    }

    /** 计算平均最大递归深度（移出上面的方法并设为 static） */
    public static int average_max_depth(int dataSize) {
        int sum = 0;
        for (int T = 0; T < 5; T++) {
            Double[] testnumbers = GenerateData.getRandomData(dataSize);
            QuickDepth quickdep = new QuickDepth();
            quickdep.sort(testnumbers);
            sum += quickdep.max_depth;
        }
        return sum / 5;
    }

    public static void main(String[] args) {
        int[] dataLength = new int[9];
        dataLength[0] = 256; // 2^8
        for (int i = 1; i < dataLength.length; i++) {
            dataLength[i] = dataLength[i - 1] * 2; // 直到2^16=65536
            int average_depth = QuickDepth.average_max_depth(dataLength[i - 1]);
            System.out.println("数据长度为 " + dataLength[i - 1] + "，平均最大递归深度为 " + average_depth);
        }
        System.out.println("数据长度为 " + dataLength[dataLength.length - 1] +
                "，平均最大递归深度为 " + QuickDepth.average_max_depth(dataLength[dataLength.length - 1]));
    }
}
