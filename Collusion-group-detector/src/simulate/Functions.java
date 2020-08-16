package simulate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Functions {


    //给volume数组排序
    public static void bubble_sort(int[][] arr){
                var len = arr.length;
        for (var i = 0; i < len - 1; i++) {
            for (var j = 0; j < len - 1 - i; j++) {
                if (arr[j][2] < arr[j+1][2]) {        // 相邻元素两两对比
                    var temp = arr[j+1][2];        // 元素交换
                    arr[j+1][2] = arr[j][2];
                    arr[j][2] = temp;
                }
            }
        }
    }
    //最新的随机数组函数，用这个
    public static int[][] getModule1DataSBV(int numberOfTrader,int tradeTimes ){
        int[][] array= new int[tradeTimes][3];

        //确定trader数据
        int[] trader = new int[numberOfTrader];
        for (int j = 0;j<numberOfTrader;j++){
            trader[j]=1000+j;
        }

        int target1,target2;
        //让trader们自由交易，每完成一次交易后产生一条transaction，随机一个volume
        for (int i = 0; i <tradeTimes ; i++) {
            //这里是每一次交易所使用的交易规则

            //规则是随机选两个trader里面的元素，将这两个元素填到array表里
            //然后随机一个volume值
            //生成[Min, Max]
            //Min + (int)(Math.random() * ((Max - Min) + 1))
            //1000+(int)(Math.random()*((9999-1000)+1))
            target1 = (int)(Math.random()*(numberOfTrader));
            target2 = (int)(Math.random()*(numberOfTrader));
            if (target1!=target2){
                array[i][0]=trader[target1];
                array[i][1]=trader[target2];
                array[i][2]=1000+(int)(Math.random()*((9999-1000)+1));
//                System.out.println(target1+" "+target2+"\t");
            }
        }

        //生成结束后还要根据volume排序数组
        //simulate.Functions.bubble_sort(array);

        //打印数组
//        for (int i = 0; i <array.length ; i++) {
//            for (int j = 0; j <3 ; j++) {
//                System.out.print(array[i][j]);
//                System.out.print(" ");
//            }
//            System.out.println("\t");
//        }

        return array;
    }

    //对随机出来的数组进行IO读写操作
    public static void generateCSVFile(int[][] array){

    }

    //BufferedReader实现读取文本数据，并保存为二维数组。
    public static double[][] getFile(String pathName) throws Exception {
        File file = new File(pathName);
        if (!file.exists())
            throw new RuntimeException("Not File!");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        List<double[]> list = new ArrayList();
        while ((str = br.readLine()) != null) {
            int s = 0;
            String[] arr = str.split(",");
            double[] dArr = new double[arr.length];
            for (String ss : arr) {
                if (ss != null) {
                    dArr[s++] = Double.parseDouble(ss);
                }

            }
            list.add(dArr);
        }
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i).length)
                max = list.get(i).length;
        }
        double[][] array = new double[list.size()][max];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                array[i][j] = list.get(i)[j];
            }
        }
        return array;
    }







    //这些都过时了
    public  static ArrayList getRandomArrayListData(){
        ArrayList simulation = new ArrayList();

        //确定seller数据
        int[] seller = new int[100];
        for (int j = 0;j<seller.length;j++){
            seller[j]=1000+j;
        }

        //确定buyer数据
        int[] buyer = new int[100];
        for (int j = 0;j<buyer.length;j++){
            Random r = new Random();
            int label = r.nextInt(100);
            buyer[j]=seller[label];
        }

        //随机count和volume
        int[] count = new int[100];
        for (int j = 0;j<count.length;j++){
            Random r = new Random();
            int label = r.nextInt(100);
            count[j]=label;
        }
        int[] volume = new int[100];
        for (int j = 0;j<seller.length;j++){
            Random r = new Random();
            int label = r.nextInt(100);
            volume[j]=label*100;
        }

        //将数据添加到transaction里面
        for (int i = 0;i<100;i++){
            simulation.add(new transaction(seller[i],buyer[i],count[i],volume[i]));
        }


        Iterator it = simulation.iterator();
        while (it.hasNext()){
            Object obj = it.next();
            System.out.println(obj);
        }
        return simulation;
    }
    public static int[][] getRandom2DArrayData(int Row){
        int[][] array= new int[Row][3];

        //确定seller数据
        int[] seller = new int[Row];
        for (int j = 0;j<seller.length;j++){
            seller[j]=1000+j;
        }

        //确定buyer数据
        int[] buyer = new int[100];
        for (int j = 0;j<buyer.length;j++){
            Random r = new Random();
            int label = r.nextInt(100);
            buyer[j]=seller[label];
        }


        int[] volume = new int[100];
        for (int j = 0;j<seller.length;j++){
            Random r = new Random();
            int label = r.nextInt(100);
            volume[j]=label*100;
        }

        //填充数据，获得一个原始数据表，SBV
        for (int i = 0; i <Row ; i++) {
            array[i][0]=seller[i];
        }
        for (int i = 0; i <Row ; i++) {
            array[i][1]=buyer[i];
        }
        for (int i = 0; i <Row ; i++) {
            array[i][2]=volume[i];
        }

        //打印数组
//        for (int i = 0; i <Row ; i++) {
//            for (int j = 0; j <3 ; j++) {
//                System.out.print(array[i][j]);
//                System.out.print(" ");
//            }
//            System.out.println("\t");
//        }

        return array;
    }


}
