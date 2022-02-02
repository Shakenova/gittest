package BigProject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Kassir {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String clientType = "KASSIR";
        try {
            Socket socket = new Socket("localhost", 5555);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            PackageData pd = null;

            while (true) {
                System.out.println("PRESS [1] TO LIST FlIGHTS");
                System.out.println("PRESS [2] TO ADD TICKET");
                System.out.println("PRESS [3] TO LIST TICKETS");
                System.out.println("PRESS [4] TO EDIT TICKET");
                System.out.println("PRESS [5] TO DELETE TICKET");
                System.out.println("PRESS [0] TO EXIT");

                String choice = in.next();

                //Кассир может создавать/изменять/удалять данные таблицы билетов.
//            endless();
               // System.out.println("Waiting request from kassir");
                if (choice.equals("1")) {
                    pd = new PackageData("LIST FLIGHTS", clientType, null, null, null, null, null, null, null, null);
                    outputStream.writeObject(pd);
                   // System.out.println("Waiting request from server");
                    pd = (BigProject.PackageData) inputStream.readObject();
                    if(pd.getFlights().size()>0) {
                        ArrayList<Flights> flights = pd.getFlights();
                        for (int i = 0; i < flights.size(); i++) {
                            System.out.println(flights.get(i).toString());
                        }
                    }else{
                        System.out.println("LIST is empty");
                    }
            }else if(choice.equals("2")){
                    System.out.println("Enter flight_id:");
                    Long flight_id=in.nextLong();
                    System.out.println("Enter name:");
                    String name = in.next();
                    System.out.println("Enter surname:");
                    String surname=in.next();
                    System.out.println("Enter passport_number:");
                     String passport_number=in.next();
                    System.out.println("Enter ticket_type:");
                    String ticket_type=in.next();
                    Tickets ticket = new Tickets(null,flight_id,name,surname,passport_number,ticket_type);
                    pd = new PackageData("ADD TICKET",clientType,null,null,null,null,null,null,null,ticket);
                    outputStream.writeObject(pd);
                    pd=(PackageData) inputStream.readObject();
                    System.out.println(pd.getOperationType());
                }else if(choice.equals("3")){
                    pd = new PackageData("LIST TICKETS", clientType, null, null, null, null, null, null, null, null);
                    outputStream.writeObject(pd);
                    //System.out.println("Waiting request from server");
                    pd = (PackageData) inputStream.readObject();
                    if(pd.getTickets().size()>0) {
                        ArrayList<Tickets> tickets = pd.getTickets();
                        for (int i = 0; i < tickets.size(); i++) {
                            System.out.println(tickets.get(i).toString());
                        }
                    }else{
                        System.out.println("LIST is empty");
                    }
                }else if(choice.equals("4")) {
                    System.out.println("Enter tiket id:");
                    Long id = in.nextLong();
                    System.out.println("Enter [1] for edit flight_id");
                    System.out.println("Enter [2] for edit name");
                    System.out.println("Enter [3] for edit surname");
                    System.out.println("Enter [4] for edit passport_number");
                    System.out.println("Enter [5] for edit ticket_type");
                    String edit = in.next();
                    Tickets ticket = null;
                    if (edit.equals("1")) {
                        System.out.println("Enter new flight_id:");
                        Long flight_id = in.nextLong();
                        ticket = new Tickets(id, flight_id, null, null, null, null);
                    } else if (edit.equals("2")) {
                        System.out.println("Enter new name:");
                        String name = in.next();
                        ticket = new Tickets(id, null, name, null, null, null);
                    } else if (edit.equals("3")) {
                        System.out.println("Enter new surname:");
                        String surname = in.next();
                        ticket = new Tickets(id, null, null, surname, null, null);
                    } else if (edit.equals("4")) {
                        System.out.println("Enter new passport_number");
                        String passport_number = in.next();
                        ticket = new Tickets(id, null, null, null, passport_number, null);
                    } else if (edit.equals("5")) {
                        System.out.println("Enter new ticket_type:");
                        String ticket_type = in.next();
                        ticket = new Tickets(id, null, null, null, null, ticket_type);
                    }
                    pd = new PackageData("EDIT TICKET", clientType, null, null, null, null, null, null, null, ticket);
                    outputStream.writeObject(pd);
                    pd = (PackageData) inputStream.readObject();
                    System.out.println(pd.getOperationType());
                }else if(choice.equals("5")) {
                    System.out.println("Enter id for delete:");
                    Long id=in.nextLong();
                    Tickets ticket=new Tickets(id,null,null,null,null,null);
                    pd = new PackageData("DELETE TICKET",clientType,null,null,null,null,null,null,null,ticket);
                    outputStream.writeObject(pd);
                    pd=(PackageData) inputStream.readObject();
                }else if(choice.equals("0")){
                    System.exit(0);
                }else{
                    System.out.println("Wrong command");
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
