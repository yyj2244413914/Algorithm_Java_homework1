package homework1;
public class Bubble extends SortAlgorithm {
    public void sort(Comparable[] objs){
        int N = objs.length;
        for(int i = 0; i < N; i++){
            for(int j = N-1; j > i; j--){
                if(less(objs[j], objs[j-1]))
                    exchange(objs, j, j-1);
            }
        }
    }
    public static void main(String[] args){
        Comparable[] objs = GenerateData. getRandomData(20);
        Bubble bubble= new Bubble();
        bubble.show(objs);
        bubble.sort(objs);
        bubble.show(objs);
        bubble.isSorted(objs);
        if(bubble.isSorted(objs))
            System.out.println("Bubble sort is sorted");
        else
            System.out.println("Bubble sort is not sorted");
    }
}

