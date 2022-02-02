package BigProject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String clientType="ADMIN";
        try {
            Socket socket = new Socket("localhost", 5555);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            PackageData pd = new PackageData();
      while (true) {
          //Админ может создавать/изменять/удалять список таблиц городов, самолетов и полетов.
          System.out.println("PRESS [1] TO LIST CITIES");
          System.out.println("PRESS [2] TO LIST AIRCRAFTS");
          System.out.println("PRESS [3] TO LIST FLIGHTS");

          System.out.println("PRESS [4] TO ADD CITI");
          System.out.println("PRESS [5] TO ADD AIRCRAFT");
          System.out.println("PRESS [6] TO ADD FLIGHT");

          System.out.println("PRESS [7] TO EDIT CITY");
          System.out.println("PRESS [8] TO EDIT AIRCRAFT");
          System.out.println("PRESS [9] TO EDIT FLIGHT");

          System.out.println("PRESS [10] TO DELETE CITY");
          System.out.println("PRESS [11] TO DELETE AIRCRAFT");
          System.out.println("PRESS [12] TO DELETE FLIGHT");

          System.out.println("PRESS [0] TO EXIT");

          String choice = in.next();

//            endless();
            if(choice.equals("1")){
                pd= new PackageData("LIST CITIES", clientType, null,null,null,null,null,null,null,null);
                outputStream.writeObject(pd);
                //System.out.println("Waiting request from server");
                pd=(PackageData) inputStream.readObject();
                if(pd.getCities().size()>0) {
                    ArrayList<Cities> cities = pd.getCities();
                    for (int i = 0; i < cities.size(); i++) {
                        System.out.println(cities.get(i).toString());
                    }
                }else {
                    System.out.println("LIST is empty");
                }

            }else if(choice.equals("2")){
                pd= new PackageData("LIST AIRCRAFTS",clientType, null,null,null,null,null,null,null,null);
                outputStream.writeObject(pd);
               // System.out.println("Waiting request from server");
                pd=(PackageData) inputStream.readObject();
                if(pd.getAircrafts().size()>0) {
                    ArrayList<Aircrafts> aircrafts = pd.getAircrafts();
                    for (int i = 0; i < aircrafts.size(); i++) {
                        System.out.println(aircrafts.get(i).toString());
                    }
                }else {
                    System.out.println("LIST is empty");
                }

            }else if(choice.equals("3")){
                pd= new PackageData("LIST FLIGHTS",clientType, null,null,null,null,null,null,null,null);
                outputStream.writeObject(pd);
               // System.out.println("Waiting request from server");
                pd=(PackageData) inputStream.readObject();
                if(pd.getFlights().size()>0) {
                    ArrayList<Flights> flights = pd.getFlights();
                    for (int i = 0; i < flights.size(); i++) {
                        System.out.println(flights.get(i).toString());
                    }
                }else {
                    System.out.println("LIST is empty");
                }
            }else if(choice.equals("4")){
                System.out.println("Enter name:");
                String name=in.next();
                System.out.println("Enter country:");
                String country = in.next();
                System.out.println("Enter short name:");
                String short_name=in.next();
                Cities city = new Cities(null,name,country,short_name);
                pd = new PackageData("ADD CITY",clientType,null,null,null,city,null,null,null,null);
                outputStream.writeObject(pd);
                pd=(PackageData) inputStream.readObject();
                System.out.println(pd.getOperationType());

            }else if(choice.equals("5")){
                System.out.println("Enter name:");
                String name = in.next();
                System.out.println("Enter model:");
                String model=in.next();
                System.out.println("Enter business class capacity:");
                Long business_class_capacity=in.nextLong();
                System.out.println("Enter econom class capacity:");
                Long econom_class_capacity=in.nextLong();
                Aircrafts aircraft = new Aircrafts(null,name,model,business_class_capacity,econom_class_capacity);
                pd = new PackageData("ADD AIRCRAFT", clientType,null,aircraft,null,null,null,null,null,null);
                outputStream.writeObject(pd);
                pd=(PackageData) inputStream.readObject();
                System.out.println(pd.getOperationType());
            }else if(choice.equals("6")){
                System.out.println("Enter aircraft id:");
                Long aircraft_id=in.nextLong();
                System.out.println("Enter departure city id:");
                Long departure_city_id = in.nextLong();
                System.out.println("Enter arrival city id:");
                Long arrival_city_id = in.nextLong();
                System.out.println("Enter departure time:");
                String departure_time = in.next();
                System.out.println("Eneter econom place price:");
                Long econom_place_price=in.nextLong();
                System.out.println("Enter business_place_price:");
                Long business_place_price=in.nextLong();
                Flights flight = new Flights(null,aircraft_id,departure_city_id,arrival_city_id,departure_time,econom_place_price,business_place_price);
                pd = new PackageData("ADD FLIGHT",clientType,null,null,null,null,null,flight,null,null);
                outputStream.writeObject(pd);
                pd=(PackageData) inputStream.readObject();
                System.out.println(pd.getOperationType());
            }else if(choice.equals("7")){
                System.out.println("Enter city id:");
                Long id = in.nextLong();
                System.out.println("Enter [1] for edit name");
                System.out.println("Enter [2] for edit country");
                System.out.println("Enter [3] for edit short_name");
                String edit=in.next();
                Cities city = new Cities();
                if(edit.equals("1")){
                    System.out.println("Enter new name:");
                    String name = in.next();
                    city = new Cities(id,name,null,null);
                }else if(edit.equals("2")){
                    System.out.println("Enter new country:");
                    String country = in.next();
                    city = new Cities(id,null,country,null);
                }else if(edit.equals("3")){
                    System.out.println("Enter new short name:");
                    String short_name=in.next();
                    city = new Cities(id,null,null,short_name);
                }
                pd=new PackageData("EDIT CITY",clientType,null,null,null,city,null,null,null,null);
                outputStream.writeObject(pd);
                pd=(PackageData) inputStream.readObject();
                System.out.println(pd.getOperationType());
            }else if(choice.equals("8")){
                System.out.println("Enter aircraft id:");
                Long id = in.nextLong();
                System.out.println("Enter [1] for edit name");
                System.out.println("Enter [2] for edit model");
                System.out.println("Enter [3] for edit business class capacity");
                System.out.println("Enter [4] for edit econom class capacity");
                String edit=in.next();
                Aircrafts aircraft = new Aircrafts();
                if(edit.equals("1")){
                    System.out.println("Enter new name:");
                    String name = in.next();
                    aircraft = new Aircrafts(id,name,null,null,null);
                }else if(edit.equals("2")){
                    System.out.println("Enter new model:");
                    String model = in.next();
                    aircraft = new Aircrafts(id,null,model,null,null);
                }else if(edit.equals("3")){
                    System.out.println("Enter new business class capacity:");
                    Long business_class_capacity = in.nextLong();
                    aircraft = new Aircrafts(id,null,null,business_class_capacity,null);
                }else if(edit.equals("4")){
                    System.out.println("Enter new econom class capacity:");
                    Long econom_class_capacity=in.nextLong();
                    aircraft = new Aircrafts(id,null,null,null,econom_class_capacity);
                }
                pd = new PackageData("EDIT AIRCRAFT",clientType,null,aircraft,null,null,null,null,null,null);
                outputStream.writeObject(pd);
                pd=(PackageData) inputStream.readObject();
                System.out.println(pd.getOperationType());
            }else if(choice.equals("9")){
                System.out.println("Enter flight id:");
                Long id=in.nextLong();
                System.out.println("Enter [1] for edit aicraft_oid");
                System.out.println("Enter [2] for edit departure_city_oid");
                System.out.println("Enter [3] for edit arrival_city_oid");
                System.out.println("Enter [4] for edit departure_time");
                System.out.println("Enter [5] for edit econom_place_price");
                System.out.println("Enter [6] for edit business_place_price");
                String edit=in.next();
                Flights flight = new Flights();
                if(edit.equals("1")){
                    System.out.println("Enter new aicraft_oid:");
                    Long aircraft_id=in.nextLong();
                    flight = new Flights(id,aircraft_id,null,null,null,null,null);
                }else if(edit.equals("2")){
                    System.out.println("Enter new departure_city_oid:");
                    Long departure_city_id=in.nextLong();
                    flight = new Flights(id,null,departure_city_id,null,null,null,null);
                }else if(edit.equals("3")){
                    System.out.println("Enter new arrival_city_oid:");
                    Long arrival_city_id = in.nextLong();
                    flight = new Flights(id,null,null,arrival_city_id,null,null,null);
                }else if(edit.equals("4")){
                    System.out.println("Enter new departure_time:");
                    String departure_time = in.next();
                    flight = new Flights(id,null,null,null,departure_time,null,null);
                }else if(edit.equals("5")){
                    System.out.println("Enter econom_place_price");
                    Long econom_place_price=in.nextLong();
                    flight=new Flights(id,null,null,null,null,econom_place_price,null);
                }else if(edit.equals("6")){
                    System.out.println("Enter business_place_price");
                    Long business_place_price=in.nextLong();
                    flight=new Flights(id,null,null,null,null,null, business_place_price);
                }
                pd = new PackageData("EDIT FLIGHT",clientType,null,null,null,null,null,flight,null,null);
                outputStream.writeObject(pd);
                pd=(PackageData) inputStream.readObject();
            }else if(choice.equals("10")){
                System.out.println("Enter id for delete:");
                Long id=in.nextLong();
                Cities city=new Cities(id,null,null,null);
                pd = new PackageData("DELETE CITY",clientType,null,null,null,city,null,null,null,null);
                outputStream.writeObject(pd);
                pd=(PackageData) inputStream.readObject();
            }else if(choice.equals("11")){
                System.out.println("Enter id for delete:");
                Long id=in.nextLong();
                Aircrafts aircraft=new Aircrafts(id,null,null,null,null);
                pd = new PackageData("DELETE AIRCRAFT",clientType,null,aircraft,null,null,null,null,null,null);
                outputStream.writeObject(pd);
                pd=(PackageData) inputStream.readObject();
            }else if(choice.equals("12")){
                System.out.println("Enter id for delete:");
                Long id=in.nextLong();
                Flights flight=new Flights(id,null,null,null,null,null,null);
                pd = new PackageData("DELETE FLIGHT",clientType,null,null,null,null,null,flight,null,null);
                outputStream.writeObject(pd);
                pd=(PackageData) inputStream.readObject();
            }else if (choice.equals("0")){
                System.exit(0);
            }else{
                System.out.println("Wrong Command");
            }
        pd=null;
       }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
