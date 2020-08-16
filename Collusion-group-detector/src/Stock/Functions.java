package Stock;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Functions {

    //创建一个新的ArrayList存储结果集返回的内容
    @NotNull
    @Contract(pure = true)
    public static ArrayList generateData() {
        ArrayList data = new ArrayList(100);
        return data;
    }

    //打印数组方法区
    public static void printArray(int[] array,int length){
        for (int i = 0; i <length ; i++) {
            System.out.print(array[i]);
            System.out.print(" ");
        }
        System.out.println("\t");
        System.out.println("_____________________");
    }
    public static void printArray(int[][] array,int rowLength,int columnLength){
        for (int i = 0; i <rowLength ; i++) {
            for (int j = 0; j <columnLength ; j++) {
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println("\t");
        }
        System.out.println("______________________");

    }
    public static void printArrayForGephi(int[][] array,int rowLength,int columnLength){
        for (int i = 0; i <rowLength ; i++) {
            for (int j = 0; j <columnLength ; j++) {
                System.out.print(array[i][j]);
                System.out.print(",");
            }
            System.out.println("\t");
        }
        System.out.println("______________________");
    }
    public static void printArrayForIDEA(int[][] array,int rowLength,int columnLength){
        int count = 0;
        for (int i = 0; i <rowLength ; i++) {
            System.out.print("{");
            for (int j = 0; j <columnLength ; j++) {
                System.out.print(array[i][j]);
                if(count <2){
                    System.out.print(",");
                    count++;
                }

            }
            System.out.print("},");
            System.out.println("\t");
            count=0;
        }
        System.out.println("______________________");
    }
    //排序方法
    public static int[] sort(@NotNull int[] array){
        int temp;
        for(int i = 0;i<array.length;i++){
            for (int j = i+1; j <array.length; j++) {
                if(array[i]>array[j]){
                    temp = array[i];
                    array[i]=array[j];
                    array[j]=temp;
                }
            }
        }
        return array;
    }

    //去重方法
    @NotNull
    @Contract(pure = true)
    public static int[] deleteDuplicate(@NotNull int[] array) {
        int[] duplicateFlag = new int[array.length];
        int singNum = 0;
        //为重复的数打上标记
        for (int i = 0; i < duplicateFlag.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] == array[j]) {
                    duplicateFlag[i] = 1;
                    break;
                }
            }
        }

        for (int i = 0; i < duplicateFlag.length; i++) {
            if (duplicateFlag[i] == 0) {
                singNum++;
            }
        }
        int[] newArray = new int[singNum];

        singNum = 0;
        for (int i = 0; i < duplicateFlag.length; i++) {
            if (duplicateFlag[i] == 0) {
                newArray[singNum] = array[i];
                singNum++;
            }
        }
//        for (int i = 0; i <newArray.length ; i++) {
//            System.out.println(newArray[i]);
//        }

        return newArray;
    }

    //阶乘方法
    public static int factorial(int number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }

    //组合数方法
    public static int Combination(int n, int k) {
        if (k == 0 || k == n)
            return 1;
        else
            return Combination(n - 1, k) + Combination(n - 1, k - 1);
    }

    //去除零值
    @NotNull
    @Contract(pure = true)
    public static int[] zeroKiller(@NotNull int[] array) {
        //得到旧数组零的个数
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                count++;
            }
        }
        //定义新数组,由于这里需要得知新数组的长度，因此必须求出旧数组中零的个数
        int[] newarray = new int[array.length - count];
        //遍历原来的旧数组
        for (int i = 0, j = 0; i < array.length; i++) {
            //将旧数组中不等于0的元素赋给新数组
            if (array[i] != 0) {
                newarray[j] = array[i];
                j++;
            }
        }
//        System.out.println("旧数组:");
//        for (int a : array) {
//            System.out.print(a + " ");
//        }
//        System.out.println();
//        System.out.println("新数组:");
//        for (int a : newarray) {
//            System.out.print(a + " ");
//
//        }
        return newarray;
    }


    //核心方法区
    //输入原数据，打印出完整的结果
    public static int[] getResult(int[] @NotNull [] dataSBV, int k, int kt, int data04Length, int rowDatLength){

        //提取collusion groups

        int[] dataSeller = new int[dataSBV.length];//存储所有的seller信息

        for (int i = 0; i < dataSBV.length; i++) {//提取所有的Seller到dataSeller中
            dataSeller[i] = dataSBV[i][0];
        }
        //对所有的seller进行排序，去重操作
        dataSeller = Functions.sort(dataSeller);
        dataSeller = Functions.deleteDuplicate(dataSeller);
        //
        System.out.println("***"+"The number of available sellers:"+dataSeller.length+"***");

        System.out.println("This is the Sellers from data02");
        Functions.printArray(dataSeller,dataSeller.length);

        int[][] dataSBB = new int[dataSeller.length][k+1];
        int dataSellerIndex = 0;

        for (int i = 0; i <dataSeller.length ; i++) {//输出Seller
            dataSBB[i][0] = dataSeller[dataSellerIndex];
            dataSellerIndex++;
            //// 这里写错了i不能乱用，两个数组长度不一样的。定位出了问题
        }

        //先把结果输出为一个个的cargo
        int[] cargo = new int[k];
        int cargoIndex = 0;
        //需要注意在data02表中的循环次数和data03表的循环次数是不一致的
        //需要考虑有些②循环会有超出4个合适的选项，所以要加入kIndex值
        //for循环的框架没问题
        for (int i = 0; i <dataSeller.length ; i++) {
            for (int j = 0; j <rowDatLength ; j++) {//注意这里
                if ((dataSeller[i]==dataSBV[j][0])&&(cargoIndex<k)){
                    cargo[cargoIndex]=dataSBV[j][1];
                    cargoIndex++;
                }
            }
            for (int j = 0; j <cargo.length ; j++) {//优化
                dataSBB[i][j+1]=cargo[j];
            }
            //把cargo清零
            for (int j = 0; j <cargo.length ; j++) {
                cargo[j]=0;
            }
            cargoIndex = 0;
        }

        //这里需要把数组中的所有0值排除，因此遍历dataSBB数组，把所有的0值赋值为1,2,3,4,5……这样就不可能重复了
        //需要注意这里的赋值要与主程序中的区开，如果有1000多个0，那就要报错，不能继续了
        int NumberOfZeroValue = 0;
        int FillerNumber = 1;
        for (int x = 0; x <dataSBB.length ; x++) {
            for (int z = 0; z <k+1 ; z++) {
                if(NumberOfZeroValue>999){
                    System.out.println("The zero-suppression function is broken");
                    break;
                }else if(dataSBB[x][z]==0) {
                    dataSBB[x][z] = FillerNumber;
                    FillerNumber++;
                    NumberOfZeroValue++;
                }
            }
        }

        System.out.println("This is the dataSBB:");
        Functions.printArray(dataSBB,dataSBB.length,k+1);

        //开始进行发现了
        int[] data04 = new int[data04Length*2];
        int data04Index = 0;



        //有两个条件

        //进行判断时会先抓取两个SellerIndex,两个SellerIndex相互独立，且独立于i和j
        //SellerIndex只会增加，不会减少和归零。
        int SellerIndex1 = 0;
        int SellerIndex2 = 1;

        int loopNUmber=dataSeller.length-1;//Seller数量-1
        int finish = 0;//外圈已经跑了多少圈,下次跑要减掉这个圈数

        //通过这两个循环来控制最外层Seller的选择，每次循环都会抓取一对Seller并进行三个判断
        for (int i = 0; i <loopNUmber ; i++) {//Seller数量-1
            for (int j = 1; j <=loopNUmber ; j++) {// 这里的循环数量会逐渐减少的,
                //System.out.println(SellerIndex1+" "+SellerIndex2);//这里就有了正确的index值了，可以开始比较了
                //得到两个index后就要开始进行两次判断，因此有两个同级loop
                //每一个loop的循环次数都是不同的，
                //在loop1里面还有一个双loop循环结构，第一层循环k次，第二层循环k次，总计k*k次
                boolean condition1 = false;
                boolean condition2 = false;
                boolean condition3 = false;
                boolean condition =  false;
                int correctNumber = 0;//用来表示目前的Seller对中有几个合格的Buyer组

                for (int l = 1; l <=k ; l++) {//比较区，同级loop1，输出condition1,对两组Buyer进行比较
                    for (int m = 1; m <=k ; m++) {
                        if((dataSBB[SellerIndex1][l]==dataSBB[SellerIndex2][m])&&(correctNumber<=kt)){
                            correctNumber ++;
                            continue;
                        }
                    }
                    if(correctNumber>=kt){
                        condition1=true;
                    }
                }
                for (int q = 1; q <=k ; q++) {//比较区，同级loop2，输出condition2
                    if (dataSBB[SellerIndex1][0]==dataSBB[SellerIndex2][q]){//两边都要比较
                        condition2=true;
                    }
                }
                for (int p = 0; p <k ; p++) {
                    if (dataSBB[SellerIndex2][0]==dataSBB[SellerIndex1][p]){//两边都要比较
                        condition3=true;
                    }
                }
                if(condition1&&condition2&&condition3){
                    condition=true;
                }
                if (condition){
                    //在这里把正确的结果输出
                    //这里的输出应该是两个成对一起输出的，不能只输出一个，反正后面有去重
                    data04[data04Index]=dataSBB[SellerIndex1][0];
                    data04Index++;
                    data04[data04Index]=dataSBB[SellerIndex2][0];
                    data04Index++;
                }
//                    //输出完毕后重置condition
//                    condition1=false;
//                    condition2=false;
//                    condition=false;


                //完成判断之后的操作
                SellerIndex2++;
            }
            SellerIndex1++;
            loopNUmber--;
            finish++;
            SellerIndex2=1+finish;
        }

        System.out.println("This is data04,");
        Functions.printArray(data04,data04.length);

        //对最后的data04进行去重，去零值的操作
        data04 = Functions.sort(data04);
        data04 = Functions.deleteDuplicate(data04);
        data04 = Functions.zeroKiller(data04);

        System.out.println("This is dataS,The final result:");
        Functions.printArray(data04,data04.length);

        return data04;
    }

    //输入原数据，只打印最后的结果

    public static int[] getPureResult(int[][] dataSBV,int k,int kt,int data04Length,int rowDatLength){
        int[] dataSeller = new int[dataSBV.length];//存储所有的seller信息
        for (int i = 0; i < dataSBV.length; i++) {//提取所有的Seller到dataSeller中
            dataSeller[i] = dataSBV[i][0];
        }
        //对所有的seller进行排序，去重操作
        dataSeller = Functions.sort(dataSeller);
        dataSeller = Functions.deleteDuplicate(dataSeller);

        int[][] dataSBB = new int[dataSeller.length][k+1];
        int dataSellerIndex = 0;

        for (int i = 0; i <dataSeller.length ; i++) {//输出Seller
            dataSBB[i][0] = dataSeller[dataSellerIndex];
            dataSellerIndex++;
        }

        //先把结果输出为一个个的cargo
        int[] cargo = new int[k];
        int cargoIndex = 0;
        for (int i = 0; i <dataSeller.length ; i++) {
            for (int j = 0; j <rowDatLength ; j++) {//注意这里
                if ((dataSeller[i]==dataSBV[j][0])&&(cargoIndex<k)){
                    cargo[cargoIndex]=dataSBV[j][1];
                    cargoIndex++;
                }
            }
            for (int j = 0; j <cargo.length ; j++) {//优化
                dataSBB[i][j+1]=cargo[j];
            }
            //把cargo清零
            for (int j = 0; j <cargo.length ; j++) {
                cargo[j]=0;
            }
            cargoIndex = 0;
        }
        int NumberOfZeroValue = 0;
        int FillerNumber = 1;
        for (int x = 0; x <dataSBB.length ; x++) {
            for (int z = 0; z <k+1 ; z++) {
                if(NumberOfZeroValue>999){
                    System.out.println("The zero-suppression function is broken");
                    break;
                }else if(dataSBB[x][z]==0) {
                    dataSBB[x][z] = FillerNumber;
                    FillerNumber++;
                    NumberOfZeroValue++;
                }
            }
        }

        //开始进行发现了
        int[] data04 = new int[data04Length*2];
        int data04Index = 0;
        //有两个条件
        //进行判断时会先抓取两个SellerIndex,两个SellerIndex相互独立，且独立于i和j
        //SellerIndex只会增加，不会减少和归零。
        int SellerIndex1 = 0;
        int SellerIndex2 = 1;

        int loopNUmber=dataSeller.length-1;//Seller数量-1
        int finish = 0;//外圈已经跑了多少圈,下次跑要减掉这个圈数

        //通过这两个循环来控制最外层Seller的选择，每次循环都会抓取一对Seller并进行三个判断
        for (int i = 0; i <loopNUmber ; i++) {//Seller数量-1
            for (int j = 1; j <=loopNUmber ; j++) {// 这里的循环数量会逐渐减少的,
                //System.out.println(SellerIndex1+" "+SellerIndex2);//这里就有了正确的index值了，可以开始比较了
                //得到两个index后就要开始进行两次判断，因此有两个同级loop
                //每一个loop的循环次数都是不同的，
                //在loop1里面还有一个双loop循环结构，第一层循环k次，第二层循环k次，总计k*k次
                boolean condition1 = false;
                boolean condition2 = false;
                boolean condition3 = false;
                boolean condition =  false;
                int correctNumber = 0;//用来表示目前的Seller对中有几个合格的Buyer组

                for (int l = 1; l <=k ; l++) {//比较区，同级loop1，输出condition1,对两组Buyer进行比较
                    for (int m = 1; m <=k ; m++) {
                        if((dataSBB[SellerIndex1][l]==dataSBB[SellerIndex2][m])&&(correctNumber<=kt)){
                            correctNumber ++;
                            continue;
                        }
                    }
                    if(correctNumber>=kt){
                        condition1=true;
                    }
                }
                for (int q = 1; q <=k ; q++) {//比较区，同级loop2，输出condition2
                    if (dataSBB[SellerIndex1][0]==dataSBB[SellerIndex2][q]){//两边都要比较
                        condition2=true;
                    }
                }
                for (int p = 0; p <k ; p++) {
                    if (dataSBB[SellerIndex2][0]==dataSBB[SellerIndex1][p]){//两边都要比较
                        condition3=true;
                    }
                }
                if(condition1&&condition2&&condition3){
                    condition=true;
                }
                if (condition){
                    //在这里把正确的结果输出
                    //这里的输出应该是两个成对一起输出的，不能只输出一个，反正后面有去重
                    data04[data04Index]=dataSBB[SellerIndex1][0];
                    data04Index++;
                    data04[data04Index]=dataSBB[SellerIndex2][0];
                    data04Index++;
                }
                SellerIndex2++;
            }
            SellerIndex1++;
            loopNUmber--;
            finish++;
            SellerIndex2=1+finish;
        }

        //对最后的data04进行去重，去零值的操作
        data04 = Functions.sort(data04);
        data04 = Functions.deleteDuplicate(data04);
        data04 = Functions.zeroKiller(data04);
        System.out.println("The k is :"+k+" "+"The kt is："+kt+" ");
        Functions.printArray(data04,data04.length);
        return data04;
    }





    //存一些使用到的小框架
    private static void nullmethod1(){
        int SellerIndex1 = 0;
        int SellerIndex2 = 1;

        int loopNUmber=7;//Seller数量-1
        int finishedcount = 0;//外圈已经跑了多少圈

        for (int i = 0; i <7 ; i++) {//Seller数量-1
            for (int j = 1; j <=loopNUmber ; j++) {// 这里的循环数量会逐渐减少的
                System.out.println(SellerIndex1+" "+SellerIndex2);
                SellerIndex2++;
            }
            SellerIndex1++;
            loopNUmber--;
            finishedcount++;
            SellerIndex2=1+finishedcount;
        }
    }
}
