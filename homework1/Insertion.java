package homework1;
public class Insertion extends SortAlgorithm {
    public void sort(Comparable[] objs){
        int N = objs.length;
        for(int i = 1; i < N; i++){
            for(int j = i; j > 0 && less(objs[j], objs[j-1]); j--)
                exchange(objs, j, j-1);
        }
    }
        public static void main(String[] args) {
        Comparable[] objs = GenerateData. getRandomData(20);
        Shell3 shell3 = new Shell3();
        shell3.show(objs);
        Insertion insertion = new Insertion();
        insertion.sort(objs);
        insertion.show(objs);
        insertion.isSorted(objs);
        if(insertion.isSorted(objs))
            System.out.println("Insertion sort is sorted");
        else
            System.out.println("Insertion sort is not sorted");
    }
}
