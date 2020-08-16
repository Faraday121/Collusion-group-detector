package UselessThings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StreamControl {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis= new FileInputStream("C:\\Users\\DELL\\Desktop");
            //改造while
            int readData = 0;
            while((readData = fis.read())!=-1){
                System.out.println(readData);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis!=null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
