package homework1;
import java.util.Scanner;

public class QuickSearch {
    public void search(Comparable[] objs, int start, int end, int k){
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
        // 确定轴值的最终位置 P
        int P = i; // 轴值最后落点
        //  判断第 k 小元素的位置关系
        int pivotRank = P - start + 1; // 轴值在当前子数组中的“第几小”
        if (k == pivotRank) {
            System.out.println(objs[P]);// 输出第 k 小的元素
            return;
        } else if (k < pivotRank) {
            // 左边：第 k 小
            search(objs, start, P - 1, k);
        } else {
            // 右边：要减掉左边 + pivot 的数量
            search(objs, P + 1, end, k - pivotRank);
        }
    }

    
    /**
     * 找到start、end和中间位置三个数中的中间值的索引
     */
    public int getMedianIndex(Comparable[] objs, int start, int end) {
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
    public void exchange(Comparable[] objs, int i, int j) {
        Comparable temp = objs[i];
        objs[i] = objs[j];
        objs[j] = temp;
    }
    public boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
 public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("请输入数组大小：");
        int N = scanner.nextInt();
        
        Comparable[] objs = new Comparable[N];
        System.out.println("请输入数组元素（例如一组整数）：");
        for (int i = 0; i < N; i++) {
            objs[i] = scanner.nextInt(); // 假设输入的是整数
        }

        System.out.print("请输入要查找的第 k 小元素 (1 ≤ k ≤ N)：");
        int k = scanner.nextInt();

        System.out.println("第 " + k + " 小的元素是: ");
        QuickSearch qs = new QuickSearch();
        qs.search(objs, 0, N - 1, k);
    }
}
