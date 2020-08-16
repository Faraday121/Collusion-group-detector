package Stock;

public class DetectRandom {
    public static void main(String[] args) {

        int data04Length = 100;
        int k = 4;
        int kt = 2;

        System.out.println("The k value is :"+k);
        System.out.println("The kt value is :"+kt);
        System.out.println("The given volume of data04 is "+data04Length);
        System.out.println("Please confirm the volume is enough");
        System.out.println("__________________________________");

        //这里是随机的数据
        int numberOfTrader = 10;
        int tradeTimes = 70;
        int[][]dataSBV = simulate.Functions.getModule1DataSBV(numberOfTrader,tradeTimes);

        Stock.Functions.printArrayForGephi(dataSBV,tradeTimes,3);

        simulate.Functions.bubble_sort(dataSBV);

        System.out.println("HHHHHHHHHH\t");

        int rowDatLength = dataSBV.length;

        Stock.Functions.getResult(dataSBV,k,kt,data04Length,rowDatLength);





//        int[][]data01 = simulate.Functions.getRandom2DArrayData(100);
//        simulate.Functions.bubblesort(data01);
//        int rowDatLength = data01.length;
//        Stock.Functions.getResult(data01,k,kt,data04Length,rowDatLength);
    }
}
