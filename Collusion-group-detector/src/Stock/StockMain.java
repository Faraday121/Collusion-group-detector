package Stock;

public class StockMain {
    public static void main(String[] args) {
        System.out.println("_________________ Program start __________________");
        //在这里设置所有需要提前设置的参数
        //数组长度区,数组长度不够会报错
//        int data01Length = 49;
//        int data01Column = 4;
        int data04Length = 50;//结果表最好给双倍的容量

        //k值和kt值区
        int k = 7;
        int kt = 5;

        //提示当前的k和kt值。
        System.out.println("The k value is :"+k);
        System.out.println("The kt value is :"+kt);
//        System.out.println("The given length of data02 is:"+data01Length+" column:"+data01Column);
        System.out.println("The given volume of data04 is "+data04Length);
        System.out.println("Please confirm the volume is enough");

        /**
         data01存储程序内部的原表SBV
         data02存储来自数据库的原表SBV
         data03存储输入k值后的SBB结果表
         data04存储输入kt值后的S结果表

         dataSeller存储找出的所有去重之后的Seller名单，并排序，此表依赖于data02的长度，是中间表
         cargo也是中间表，用来运输
         */

        int[][] data01 = datasets.getDataSet01();
        int rowDatLength = data01.length;

 //       int[][] data02 = new int[data01Length][data01Column];//这个存储从数据库转来的原始数据
        //提取collusion groups

        Functions.getPureResult(data01,k,kt,data04Length,rowDatLength);


    }
}
