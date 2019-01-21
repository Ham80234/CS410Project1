import java.awt.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {

    private static String Delimeter = ",";
    private static List<String> Addresses = new ArrayList<String>();
    private static List<String> Streets = new ArrayList<String>();
    private static List<String> OrderedStreets = new ArrayList<String>();

    public static void main(String[] args) throws IOException{
     ReadData("/Users/ud2131te/Documents/410Project1/src/File.dat", Addresses);
     ReadData("/Users/ud2131te/Documents/410Project1/src/Layout.dat", Streets);

        OrderStreets("55987");


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

    private static void OrderStreets(String Zip) throws IndexOutOfBoundsException {
        String WinonaV = "Vertical Streets: Winona, 55987";
        String RochesterV = "Vertical Streets: Rochester, 55910";
        String WinonaH = "Horizontal Streets: Winona, 55987";
        String RochesterH = "Horizontal Streets: Rochester, 55910";

        List<String> Vert = new ArrayList<String>();
        List<String> Horizontal = new ArrayList<String>();

        int VStart = 0;
        int VEnd = 0;

        int HStart = 0;
        int HEnd = 0;

        if(Zip.equals("55987")){
            VStart = 2;
            for (int i = 0; i < Streets.size(); i++) {
                if(Streets.get(i).equals(WinonaH)){
                    VEnd = i-1;
                    HStart = i +1;
                }
            }
            for (int i = 0; i < Streets.size(); i++) {
                if(Streets.get(i).equals(RochesterV)){
                    HEnd = i-1;
                }
            }

        }else
        if(Zip.equals("55910")){

            for (int i = 0; i < Streets.size(); i++) {
                if(Streets.get(i).equals(RochesterV)){
                    VStart = i+2;
                }
                if(Streets.get(i).equals(RochesterH)){
                    HStart = i+1;
                    VEnd = i-1;
                }

                if(!Streets.isEmpty()){
                    HEnd = i;
                }
            }

        }else{
            System.out.println("Not a valid Zip: ERROR");
        }

        Vert = Streets.subList(VStart-1, VEnd+1);
        Horizontal = Streets.subList(HStart, HEnd+1);

        //weaving method
//        for (int i = 0; i < 3; i++) {
//            if(!Vert.get(i).isEmpty()){
//                OrderedStreets.add(Vert.get(i));
//                if(!Horizontal.get(i).isEmpty()){
//                    OrderedStreets.add(Horizontal.get(i));
//                }
//            }
//            if(!Horizontal.get(i).isEmpty() && Vert.get(i).isEmpty()){
//                OrderedStreets.add(Horizontal.get(i));
//
//            }
//            if(Horizontal.get(i).isEmpty() && !Vert.get(i).isEmpty()){
//                OrderedStreets.add(Vert.get(i));
//                OrderedStreets.add(Vert.get(i+1));
//            }
//
//        }



        print(OrderedStreets);


    }

    private static List<String> FindRoute(String Start, String End){
        List<String> Route = new ArrayList<String>();


        return Route;
    }

    private static void print(List<String> route){
        for (int i = 0; i < route.size(); i++) {
            System.out.println(route.get(i));
        }
    }
