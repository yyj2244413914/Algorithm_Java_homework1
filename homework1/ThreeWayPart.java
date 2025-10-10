package homework1;

public class ThreeWayPart extends SortAlgorithm {
     public void sort(Comparable[] objs) {
        if (objs == null || objs.length == 0) {
            return;
        }
        threeWayPartSort(objs, 0, objs.length - 1);
    }
public void threeWayPartSort(Comparable[] objs, int start, int end) {
    // 递归终止条件：子数组长度为0或1时无需排序
    if (start >= end) return;

    // 选择基准值 pivot：取开头、末尾和中间三个元素的中位数
    int medianIndex = getMedianIndex(objs, start, end);
    // 将 pivot 交换到子数组开头，方便划分
    exchange(objs, start, medianIndex);
    Comparable key = objs[start];  // 保存 pivot 值

    // 初始化三个指针
    int lt = start;      // 左边界：objs[start..lt-1] < pivot
    int gt = end;        // 右边界：objs[gt+1..end] > pivot
    int i = start + 1;   // 当前扫描指针：objs[lt..i-1] == pivot, objs[i..gt] 未处理

    // 遍历子数组，进行三路划分
    while (i <= gt) {
        if (less(objs[i], key)) {
            // objs[i] < pivot：交换到左边区域，并移动 lt 和 i
            exchange(objs, lt++, i++);
        } else if (less(key, objs[i])) {
            // objs[i] > pivot：交换到右边区域，并移动 gt
            // 注意 i 不动，因为交换过来的元素还未处理
            exchange(objs, i, gt--);
        } else {
            // objs[i] == pivot：自然留在中间区域，扫描下一个元素
            i++;
        }
    }

    // 递归处理左边 < pivot 的子数组
    threeWayPartSort(objs, start, lt - 1);
    // 递归处理右边 > pivot 的子数组
    threeWayPartSort(objs, gt + 1, end);
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
    public static void main(String[] args) {
        Comparable[] objs = GenerateData. getRandomData(20);
        ThreeWayPart threeWayPart = new ThreeWayPart();
        threeWayPart.show(objs);
        threeWayPart.sort(objs);
        threeWayPart.show(objs);
        threeWayPart.isSorted(objs);
        if(threeWayPart.isSorted(objs))
            System.out.println("ThreeWayPart sort is sorted");
        else
            System.out.println("ThreeWayPart sort is not sorted");
    }
}

