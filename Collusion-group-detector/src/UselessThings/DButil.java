package UselessThings;

import java.sql.*;
import  java.util.*;

public class DButil {
        private DButil(){
        }

        /**
         * 因为注册驱动的部分只要执行一次就够了，
         * 所以应该写在静态代码块里面
         *"jdbc:mysql://localhost:3306/bjpowernode","root","f1040929519"
         */
        static {
            try{
                ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
                String driver = bundle.getString("driver");
                Class.forName(driver);
//                  Class.forName("com.mysql.jdbc.Driver");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public static Connection getConnection() throws  SQLException{
            ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
            String url = bundle.getString("url");
            String user = bundle.getString("user");
            String password = bundle.getString("password");
            return DriverManager.getConnection(url,user,password);
//            return DriverManager.getConnection("jdbc:mysql://localhost:3306/stock01","root","f1040929519");
        }

        public static void close(Connection conn,Statement ps,ResultSet rs){
            if (ps != null){
                try {
                    ps.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (rs != null){
                try {
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }





}
