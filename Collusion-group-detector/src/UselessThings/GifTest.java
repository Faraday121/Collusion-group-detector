package UselessThings;

import Stock.Functions;
import Stock.datasets;

public class GifTest {

    public static void main(String[] args) {
        int[][] data01 = {
                {1074, 1054, 15100},
                {1042, 1035, 14300},
                {1074, 1035, 13400},
                {1042, 1017, 12400},
                {1042, 1037, 11800},
                {1057, 1054, 10800},
                {1057, 1037, 9900},
                {1049, 1054, 9600},
                {1037, 1054, 9200},
                {1074, 1037, 9200},
                {1049, 1042, 9000},
                {1042, 1029, 8900},
                {1057, 1017, 8800},
                {1074, 1029, 8500},
                {1037, 1029, 8200},
                {1049, 1035, 8200},
                {1037, 1017, 7800},
                {1074, 1042, 7500},
                {1042, 1054, 7300},
                {1057, 1035, 7000},
                {1037, 1074, 6800},
                {1074, 1017, 6700},
                {1049, 1037, 5200},
                {1057, 1042, 4700},
                {1049, 1017, 4600},
                {1037, 1042, 4400},
                {1029, 1054, 3900},
                {1049, 1074, 3600},
                {1029, 1037, 3600},
                {1057, 1074, 3200},
                {1057, 1029, 3100},
                {1029, 1042, 2600},
                {1029, 1035, 2100},
                {1049, 1029, 2100},
                {1029, 1074, 1600},
                {1074, 1057, 1500},
                {1035, 1054, 600},
                {1042, 1057, 600},
                {1037, 1057, 200},
                {1049, 1057, 200},
                {1037, 1049, 200},
                {1035, 1029, 200},
                {1035, 1042, 100},
                {1054, 1029, 100},
                {1035, 1017, 100},
        };

        int data04Length = 50;
        int k = 7;
        int kt = 5;


        data01 = datasets.getDataSet01();

        int rowDatLength = data01.length;

        //int[][] data02 = new int[data01Length][data01Column];//这个存储从数据库转来的原始数据
        //提取collusion groups

        int[] dataSeller = new int[data01.length];//存储所有的seller信息

        for (int i = 0; i < data01.length; i++) {//提取所有的Seller到dataSeller中
            dataSeller[i] = data01[i][0];
        }
        //对所有的seller进行排序，去重操作
        dataSeller = Functions.sort(dataSeller);
        dataSeller = Functions.deleteDuplicate(dataSeller);

        System.out.println("***"+"The number of available sellers:"+dataSeller.length+"***");

        System.out.println("This is the Sellers from data02");
        Functions.printArray(dataSeller,dataSeller.length);

        int[][] data03 = new int[dataSeller.length][k+1];
        int dataSellerIndex = 0;

        for (int i = 0; i <dataSeller.length ; i++) {//输出Seller
            data03[i][0] = dataSeller[dataSellerIndex];
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
                if ((dataSeller[i]==data01[j][0])&&(cargoIndex<k)){
                    cargo[cargoIndex]=data01[j][1];
                    cargoIndex++;
                }
            }
            for (int j = 0; j <cargo.length ; j++) {//优化
                data03[i][j+1]=cargo[j];
            }
            //把cargo清零
            for (int j = 0; j <cargo.length ; j++) {
                cargo[j]=0;
            }
            cargoIndex = 0;
        }

        //这里需要把数组中的所有0值排除，因此遍历data02数组，把所有的0值赋值为1,2,3,4,5……这样就不可能重复了
        //需要注意这里的赋值要与主程序中的区开，如果有1000多个0，那就要报错，不能继续了
        int NumberOfZeroValue = 0;
        int FillerNumber = 1;
        for (int x = 0; x <data03.length ; x++) {
            for (int z = 0; z <8 ; z++) {
                if(NumberOfZeroValue>999){
                    System.out.println("The zero-suppression function is broken");
                }else if(data03[x][z]==0) {
                    data03[x][z] = FillerNumber;
                    FillerNumber++;
                }
            }
        }

        System.out.println("This is the data03:");
        Functions.printArray(data03,data03.length,k+1);

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
                        if((data03[SellerIndex1][l]==data03[SellerIndex2][m])&&(correctNumber<=kt)){
                            correctNumber ++;
                            continue;
                        }
                    }
                    if(correctNumber>=kt){
                        condition1=true;
                    }
                }
                for (int q = 1; q <=k ; q++) {//比较区，同级loop2，输出condition2
                    if (data03[SellerIndex1][0]==data03[SellerIndex2][q]){//两边都要比较
                        condition2=true;
                    }
                }
                for (int p = 0; p <k ; p++) {
                    if (data03[SellerIndex2][0]==data03[SellerIndex1][p]){//两边都要比较
                        condition3=true;
                    }
                }
                if(condition1&&condition2&&condition3){
                    condition=true;
                }
                if (condition){
                    //在这里把正确的结果输出
                    //这里的输出应该是两个成对一起输出的，不能只输出一个，反正后面有去重
                    data04[data04Index]=data03[SellerIndex1][0];
                    data04Index++;
                    data04[data04Index]=data03[SellerIndex2][0];
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

        System.out.println("This is data04,The final result:");
        Functions.printArray(data04,data04.length);


    }
}
