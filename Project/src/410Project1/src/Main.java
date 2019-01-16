import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    private static String Delimeter = ",";
    private static String[] temp_array;

    public static void main(String[] args) throws IOException {
        DataInputStream data = new DataInputStream(new FileInputStream(
                "/Users/ud2131te/Documents/410Project1/src/File.dat"));

//        while (data.available() > 0) {
//            String line = data.readLine();
//            temp_array = line.split(Delimeter);
//            BreakArray(temp_array);
//        }

        data.close();
    }

//    public  static void BreakArray(String[] a){
//        String[] Titles = {"Address: ","Street: ","City: ","Zip: "};
//
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(Titles[i] + a[i]);
//        }

    }

}