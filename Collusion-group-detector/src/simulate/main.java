package simulate;

public class main {
    public static void main(String[] args) {

        int[][]array = Functions.getRandom2DArrayData(100);
        Functions.bubble_sort(array);

        Stock.Functions.printArray(array,100,3);


    }
}
