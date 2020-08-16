package UselessThings;

import Stock.Functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StockTest {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = DButil.getConnection();
            //获取预编译的数据库操作对象
            String sql1 = "select Seller,Buyer,Count,Volume from data01";
            ps = conn.prepareStatement(sql1);
            //ps.setString(1,"data01");
            rs = ps.executeQuery();
            //存储结果
            ArrayList data1 = new ArrayList(100);
            while (rs.next()) {
                int a = rs.getInt("Seller");
                int b = rs.getInt("Buyer");
                int c = rs.getInt("Count");
                int d = rs.getInt("Volume");
                data1.add(new transaction(a, b, c, d));
            }


//            //遍历ArrayList data1,先隐藏了，这个暂时不用输出，别删了，优化要用
//            Iterator it1 = data1.iterator();
//            while (it1.hasNext()) {
//                Object elt = it1.next();
//                System.out.println(elt);
//            }

            System.out.println("_________________ Program start __________________");
            //在这里设置所有需要提前设置的参数
            //数组长度区,数组长度不够会报错
            int data02Length = 49;
            int data02Column = 4;
            int data04Length = 50;

            //k值和kt值区
            int k = 7;
            int kt = 5;

            //提示当前的k和kt值。
            System.out.println("The k value is :"+k);
            System.out.println("The kt value is :"+kt);
            System.out.println("The given length of data02 is:"+data02Length+" column:"+data02Column);
            System.out.println("The given volume of data04 is "+data04Length);
            System.out.println("Please confirm the volume is enough");

            /**
             data02存储原表
             data03存储输入k值后的S-B结果表
             data04存储输入kt值后的S结果表

             dataSeller存储找出的所有去重之后的Seller名单，并排序，此表依赖于data02的长度，是中间表
             cargo也是中间表，用来运输
             */

            //数组备份区
            conn = DButil.getConnection();
            String sql2 = "select Seller,Buyer,Count,Volume from data01";
            ps = conn.prepareStatement(sql2);
            rs = ps.executeQuery();

            int[][] data02 = new int[data02Length][data02Column];//这个存储从数据库转来的原始数据

            int x = 0;
            while (rs.next()) {
                int a = rs.getInt("Seller");
                data02[x][0] = a;
                int b = rs.getInt("Buyer");
                data02[x][1] = b;
                int c = rs.getInt("Count");
                data02[x][2] = c;
                int d = rs.getInt("Volume");
                data02[x][3] = d;
                x++;
            }

            //反正结果是一定的，不输出原始数据了
//            System.out.println("This is row data from data02");
//            Functions.printArray(data02,data02.length,4);

            //提取collusion groups

            int[] dataSeller = new int[data02.length];//存储所有的seller信息

            for (int i = 0; i < data02.length; i++) {//提取所有的Seller到dataSeller中
                dataSeller[i] = data02[i][0];
            }
            //对所有的seller进行排序，去重操作
            dataSeller = Functions.sort(dataSeller);
            dataSeller = Functions.deleteDuplicate(dataSeller);
            //
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
                for (int j = 0; j <data02Length ; j++) {//注意这里
                    if ((dataSeller[i]==data02[j][0])&&(cargoIndex<k)){
                        cargo[cargoIndex]=data02[j][1];
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
                            if((data03[SellerIndex1][l]==data03[SellerIndex2][m])&&(correctNumber<=kt)){//》=
                                correctNumber ++;
                                condition1=true;
                            }
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

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DButil.close(conn,ps,rs);
        }




    }
}
