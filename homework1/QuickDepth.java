package homework1;

public class QuickDepth extends SortAlgorithm {
    private int depth=0;// 全局递归深度计数器
   public void sort(Comparable[] objs) {
    if (objs == null || objs.length == 0) return;
    quickSort(objs, 0, objs.length - 1); // 初始深度为0
}

public void quickSort(Comparable[] objs, int start, int end) {
        depth++; // 进入递归，深度+1
        System.out.println("enter depth: " + depth);

        if (start >= end) {
            depth--; // 返回递归，深度-1
            System.out.println("exit depth: " + depth);
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

    // 递归调用时深度+1
    quickSort(objs, start, i - 1);
    quickSort(objs, i + 1, end);
    depth--; // 返回递归，深度-1
    System.out.println("exit depth: " + depth);
}
    /**
     * 找到start、end和中间位置三个数中的中间值的索引
     */
    private int getMedianIndex(Comparable[] objs, int start, int end) {
        int mid = start + (end - start) / 2;
        
        // 比较三个位置的元素，返回中间值的索引
        if (less(objs[start], objs[mid])) {
            if (less(objs[mid], objs[end])) {
                return mid; // start < mid < end，返回mid
            } else if (less(objs[start], objs[end])) {
                return end; // start < end <= mid，返回end
            } else {
                return start; // end <= start < mid，返回start
            }
        } else {
            if (less(objs[end], objs[mid])) {
                return mid; // end < mid <= start，返回mid
            } else if (less(objs[end], objs[start])) {
                return end; // mid <= end < start，返回end
            } else {
                return start; // mid <= start <= end，返回start
            }
        }
    }
        public static void main(String[] args){
        Comparable[] objs = GenerateData. getRandomData(20);
        QuickDepth quicknew= new QuickDepth();
        quicknew.show(objs);
        quicknew.sort(objs);
        quicknew.show(objs);
        quicknew.isSorted(objs);
        if(quicknew.isSorted(objs))
            System.out.println("QuickDepth sort is sorted");
        else
            System.out.println("QuickDepth sort is not sorted");
    }
}