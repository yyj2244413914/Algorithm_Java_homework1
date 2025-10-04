package homework1;
public class Selection extends SortAlgorithm {
    public void sort(Comparable[] objs){
        int N = objs.length;
        for(int i = 0; i < N; i++){
            int min_element = i;
            for(int j = i+1; j < N; j++){
                if(less(objs[j], objs[min_element]))
                    min_element = j;
            }
            exchange(objs, i, min_element);
        }
    }
}
