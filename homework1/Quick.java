package homework1;

public class Quick extends SortAlgorithm {
    public void sort(Comparable[] objs) {
        if (objs == null || objs.length == 0) {
            return;
        }
        quickSort(objs, 0, objs.length - 1);
    }
    
    public void quickSort(Comparable[] objs, int start, int end) {
        if (start >= end) {
            return;
        }
        
        // 选择基准值：取开头、末尾和中间三个数的中间值
        int medianIndex = getMedianIndex(objs, start, end);
        // 将基准值交换到起始位置，保持与原有分区逻辑兼容
        exchange(objs, start, medianIndex);
        
        Comparable key = objs[start];
        int i = start;
        int j = end;
        
        while (i < j) {
            // 从右向左找第一个小于等于key的数
            while (i < j && less(key, objs[j])) {
                j--;
            }
            if (i < j) {
                exchange(objs, i, j);
                i++;
            }
            
            // 从左向右找第一个大于等于key的数
            while (i < j && less(objs[i], key)) {
                i++;
            }
            if (i < j) {
                exchange(objs, i, j);
                j--;
            }
        }
        
        objs[i] = key;
        quickSort(objs, start, i - 1);
        quickSort(objs, i + 1, end);
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
        Quick quick= new Quick();
        quick.show(objs);
        quick.sort(objs);
        quick.show(objs);
        quick.isSorted(objs);
        if(quick.isSorted(objs))
            System.out.println("Quick sort is sorted");
        else
            System.out.println("Quick sort is not sorted");
    }
}

