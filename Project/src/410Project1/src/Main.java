import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.Selector;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;


public class Main {

    private static String Delimeter = ",";
    private static List<String> TestAddresses=  new ArrayList<String>();
    private static List<String> Addresses = new ArrayList<String>();
    protected static List<String> Streets = new ArrayList<String>();
    private static List<String> WinonaStreets = new ArrayList<String>();
    private static List<String> RochesterStreets = new ArrayList<String>();


    private static List<String> Vert = new ArrayList<String>();
    private static List<String> Horizontal = new ArrayList<String>();

    public static void main(String[] args) throws IOException{

     ReadData("/Users/ud2131te/Documents/410Project1/src/Files/File.dat", Addresses);
     ReadData("/Users/ud2131te/Documents/410Project1/src/Files/Layout.dat", Streets);
     OrderStreets("55987");
     SplitAddress();


print(FindRoute( CreateTeastData(30, 55910)));
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

        data.close();
    }

    private static void OrderStreets(String Zip) throws IndexOutOfBoundsException {
        String RochesterV = "Vertical Streets: Rochester, 55910";
        String WinonaH = "Horizontal Streets: Winona, 55987";
        String RochesterH = "Horizontal Streets: Rochester, 55910";
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
        }else if(Zip.equals("55910")){
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
    }

    private static void SplitAddress(){
        for (int i = 0; i < Addresses.size(); i++) {
            String breaker = Addresses.get(i);
            String[] parts = breaker.split(Delimeter);

            if(parts[3].equals("55987")){
                WinonaStreets.add(Addresses.get(i));
            }
            if(parts[3].equals("55910")){
               RochesterStreets.add(Addresses.get(i));
            }
        }

    }

    private static List<String> FindRoute(List<String> list) {
        List<String> Route = new ArrayList<String>();
        List<String> Weaved = new ArrayList<String>();
        ListIterator<String> Vlitr = Vert.listIterator();
        ;
        ListIterator<String> Hlitr = Horizontal.listIterator();



        List<String> Temp;


        Temp = (Vert.size() > Horizontal.size()) ? Vert : Horizontal;

        for (int i = 0; i < Temp.size(); i++) {
            if(i <= Vert.size()-1){
                Weaved.add(Vert.get(i));
            }
            if(i <= Horizontal.size()-1){
                Weaved.add(Horizontal.get(i));
            }
        }

        for (int i = 0; i < Weaved.size(); i++) {
            String CurrentWeave =  Weaved.get(i);
            for (int j = 0; j < list.size(); j++) {
                String Current = FindStreet(list.get(j));
                if(FindStreet(list.get(j)).equals(Weaved.get(i))){
                    Route.add(list.get(j));
                }
            }
        }
        
        return Route;
    }


        private static String FindStreet (String Address){
            String[] add = Address.split(Delimeter);

            return add[1];

        }

        private static void print (List < String > route) {
            for (int i = 0; i < route.size(); i++) {
                System.out.println(route.get(i));
            }
        }


        private static List<String> CreateTeastData(int amount, int Zip){
            List<String> TestData = new ArrayList<String>();
            int Nums = 0, Hov, Selector;
            String Street = null, City = null;

            if(Zip == 55987){
                City = "Winona";
                OrderStreets("55987");
            }
            if(Zip == 55910){
                City = "Rochester";
                OrderStreets("55910");
            }

            for (int i = 0; i < amount; i++) {
                Random rand = new Random();
                Hov = rand.nextInt(2);
                Nums = rand.nextInt(899) + 100;
                Selector = Hov < 1 ? rand.nextInt(Vert.size()-1) : rand.nextInt(Horizontal.size()-1);
                Street =  Hov < 1 ? Vert.get(Selector) : Horizontal.get(Selector);

                String Address = Nums + "," + Street + "," + City + "," + Zip;
                TestData.add(Address);
            }
            return TestData;
        }


}
