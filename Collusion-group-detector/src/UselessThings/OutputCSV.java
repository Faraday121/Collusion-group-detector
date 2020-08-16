package UselessThings;

public class OutputCSV {
    public static void main(String[] args) {
        int numberOfTrader = 30;
        int tradeTimes = 200;

        int k = 7;
        int kt = 2;
        int data04Length = 100;

        int[][]dataSBV = simulate.Functions.getModule1DataSBV(numberOfTrader,tradeTimes);
        simulate.Functions.bubble_sort(dataSBV);
        Stock.Functions.printArrayForGephi(dataSBV,tradeTimes,3);
        Stock.Functions.printArrayForIDEA(dataSBV,tradeTimes,3);
        int rowDatLength = dataSBV.length;
        Stock.Functions.getResult(dataSBV,k,kt,data04Length,rowDatLength);

    }
}
