package UselessThings;

import Stock.datasets;

public class StockMainPureVersion {
    public static void main(String[] args) {


        int data04Length = 200;
        int k ;
        int kt ;

        for (int i = 4; i <10 ; i++) {
            k=i;
            for (int j = 1; j <10 ; j++) {
                kt=j;
                int[][] data01 = datasets.getRandomExample01();
                int rowDatLength = data01.length;
                Stock.Functions.getPureResult(data01,k,kt,data04Length,rowDatLength);
            }
        }


    }
}
