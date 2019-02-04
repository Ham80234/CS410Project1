import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.Selector;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;


public class Main {

    private static String Delimeter = ",";// sets delimeter to break the the string when reading from file
    private static List<String> TestAddresses=  new ArrayList<String>();// test addresses list
    private static List<String> Addresses = new ArrayList<String>();//list of  all addresses
    private static List<String> Streets = new ArrayList<String>();//list of all streets
    private static List<String> WinonaStreets = new ArrayList<String>();// seperate list for streets in winona
    private static List<String> RochesterStreets = new ArrayList<String>();// seperate list for streets in rochester


    private static List<String> Vert = new ArrayList<String>();// list of alll vertical streets-read from Layout
    private static List<String> Horizontal = new ArrayList<String>();//list of all Horizontal streets


    public static void main(String[] args) throws IOException{
        File address = new File("File.dat"); // File Location
        String pathA = address.getAbsolutePath(); // Grabs the path


        File Layout = new File("Layout.dat");
        String pathL = Layout.getAbsolutePath();


     ReadData(pathA, Addresses);
     ReadData(pathL, Streets);
     SplitAddress();
     print(FindRoute( CreateTeastData(5, 55910)));

    }

    private static void ReadData(String Path, List<String> Array) throws IOException{
        //method that reads file into array list
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
        //method to create sublist into vertical and Horizontal streets from the Layout file
        // method takes in zipcode to sort
        String RochesterV = "Vertical Streets: Rochester, 55910";
        String WinonaH = "Horizontal Streets: Winona, 55987";
        String RochesterH = "Horizontal Streets: Rochester, 55910";
        //indexing created for sublist
        int VStart = 0;
        int VEnd = 0;
        int HStart = 0;
        int HEnd = 0;
        if(Zip.equals("55987")){
            VStart = 2;
            for (int i = 0; i < Streets.size(); i++) {
                if(Streets.get(i).equals(WinonaH)){
                    VEnd = i-1;// sets end of Vertical streets ,ie right before "Horizontal Streets: Winona, 55987" in the file
                    HStart = i +1;// sets start of Horizontal streets ,ie right after "Horizontal Streets: Winona, 55987" in the file
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
                if(!Streets.get(i).isEmpty()){
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
            String[] parts = breaker.split(Delimeter);// breaks the string
// for winon-based on zip

            if(parts[3].equals("55987")){
                WinonaStreets.add(Addresses.get(i));
            }
            if(parts[3].equals("55910")){
               RochesterStreets.add(Addresses.get(i));
            }
        }

    }

    private static List<String> FindRoute(List<String> list) {
        //method to found route
        // the route used is through weaving the horzontal and vertical addresses together

        List<String> Route = new ArrayList<String>();// created a list of the route
        List<String> Weaved = new ArrayList<String>();//Weaved list created

        List<String> SmartVert =  new ArrayList<String>(); // Used for our Algorithum
        List<String> SmartHorizontal =  new ArrayList<String>();


        for (int i = 0; i < Vert.size(); i++) {
            for (int j = 0; j < list.size(); j++) {

                if(Vert.contains(FindStreet(list.get(j)))){
                    if(!SmartVert.contains((Vert.get(i)))){
                        SmartVert.add(Vert.get(i));
                    }
                }

            }
        }
        for (int i = 0; i < Horizontal.size(); i++) {
            for (int j = 0; j < list.size(); j++) {

                if (Horizontal.contains(FindStreet(list.get(j)))) {
                    if(!SmartHorizontal.contains((Horizontal.get(i)))){
                        SmartHorizontal.add(Horizontal.get(i));
                    }
                }

            }
        }

        List<String> Temp;



        Temp = (Vert.size() > Horizontal.size()) ? Vert : Horizontal; // temp list set to longest List
// method to weave the list together.It checks if the weaved list value is equal to the whole list value
        for (int i = 0; i < Temp.size(); i++) {
            if(i <= SmartVert.size()-1){

                Weaved.add(SmartVert.get(i));
            }
            if(i <= SmartHorizontal.size()-1){
                Weaved.add(SmartHorizontal.get(i));
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
        //methode to create random test data so we can check our work
            List<String> TestData = new ArrayList<String>();
            int Nums = 0    ;
            String City = null;

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
                int Hov = rand.nextInt(100)%2;
                Nums = rand.nextInt(899) + 100;
                int Selector = Hov == 1 ? rand.nextInt(Vert.size()) : rand.nextInt(Horizontal.size());
                String Street =  Hov == 1 ? Vert.get(Selector) : Horizontal.get(Selector);
                String Address = Nums + "," + Street + "," + City + "," + Zip;
                TestData.add(Address);
            }
            return TestData;
        }


}
