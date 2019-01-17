import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {

    private static String Delimeter = ",";
    private static List<String> Addresses = new ArrayList<String>();
    private static List<String> Streets = new ArrayList<String>();
    private static List<String> VStreets = new ArrayList<String>();
    private static List<String> HStreets = new ArrayList<String>();







    public static void main(String[] args) throws IOException{
     ReadData("/Users/ud2131te/Documents/410Project1/src/File.dat", Addresses);
     ReadData("/Users/ud2131te/Documents/410Project1/src/Layout.dat", Streets);
     SetStreets();
    }

    private static void ReadData(String Path, List<String> Array) throws IOException{
        DataInputStream data = new DataInputStream(new FileInputStream(
                Path));
        int Counter = 0;
        while (data.available() > 0) {
            String line = data.readLine();
            Array.add(line);
            Counter++;
        }
        //print(Array);
        data.close();
    }

    private static void SetStreets(String Zip){
        int Start_index, End_index;

    }
    public static List<String> FindRoute(String Start, String End){
        List<String> Route = new ArrayList<String>();
        return Route;
    }

    public static void print(List<String> route){
        for (int i = 0; i < route.size(); i++) {
            System.out.println(route.get(i));
        }
    }
}
