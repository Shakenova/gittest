package BigProject;


import javafx.scene.chart.AxisBuilder;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private Socket socket;
    private int id;
    private String clientType;
    private DBManager db;


    public ClientHandler(Socket socket,int id, DBManager db) {
        this.socket = socket;
        this.id = id;
        this.db=db;
    }

    public void run(){
        try{
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            PackageData data = null;

           while((data = (PackageData) inputStream.readObject()).getClientType()!=null) {
               PackageData response = null;

              if (data.getClientType().equals("ADMIN")) {
                  if(data.getOperationType().equals("LIST CITIES")) {
                      //ArrayList<Cities> cities = db.getAllCities();
                      response = new PackageData("LIST CITIES SEND", "SERVER", null, null,  db.getAllCities(), null, null, null, null, null);
//                      System.out.println(response.getOperationType());
//                      outputStream.writeObject(response);
                    }else if(data.getOperationType().equals("LIST AIRCRAFTS")) {
                      ArrayList<Aircrafts> aircrafts = db.getAllAircrafts();
                      response = new PackageData("LIST AIRCRAFTS SEND", "SERVER", aircrafts, null, null, null, null, null, null, null);
//                      System.out.println(response.getOperationType());
//                      outputStream.writeObject(response);
                    }else if(data.getOperationType().equals("LIST FLIGHTS")){
                      ArrayList<Flights> flights = db.getAllFlights();
                      response = new PackageData("LIST FLIGHTS SEND", "SERVER", null, null, null, null, flights, null, null, null);
//                      System.out.println(response.getOperationType());
//                      outputStream.writeObject(response);
                  }else if(data.getOperationType().equals("ADD CITY")){
                      Cities city=data.getCity();
                      db.addCity(city);
                      response = new PackageData("CITY ADDED","SERVER",null,null,null,city,null,null,null,null);

                  }else if(data.getOperationType().equals("ADD AIRCRAFT")){
                      Aircrafts aircraft=data.getAircraft();
                      db.addAircraft(aircraft);
                      response = new PackageData("AIRCRAFT ADDED","SERVER",null,aircraft,null,null,null,null,null,null);

                  }else if(data.getOperationType().equals("ADD FLIGHT")){
                      Flights flight = data.getFlight();
                      //System.out.println(flight.getEconom_place_price());
                      db.addFlight(flight);
                      response = new PackageData("FLIGHT ADDED","SERVER",null,null,null,null,null,flight,null,null);

                  }else if(data.getOperationType().equals("EDIT CITY")){
                      Cities city = data.getCity();
                      Cities nCity =db.getCity(city.getId());
                      if(city.getName()!=null){
                          nCity.setName(city.getName());
                      }else if(city.getCountry()!=null){
                          nCity.setCountry(city.getCountry());
                      }else if(city.getShort_name()!=null){
                          nCity.setShort_name(city.getShort_name());
                      }
                      db.updateCity(nCity);
                      response = new PackageData("CITY EDITED","SERVER",null,null,null,null,null,null,null,null);

                  }else if(data.getOperationType().equals("EDIT AIRCRAFT")){
                      Aircrafts aircraft = data.getAircraft();
                      Aircrafts nAircraft =db.getAircraft(aircraft.getId());
                      if(aircraft.getName()!=null){
                          nAircraft.setName(aircraft.getName());
                      }else if(aircraft.getModel()!=null){
                          nAircraft.setModel(aircraft.getModel());
                      }else if(aircraft.getBClassCapacity()!=null){
                          nAircraft.setBClassCapacity(aircraft.getBClassCapacity());
                      }else if(aircraft.getEClassCapacity()!=null){
                          nAircraft.setEClassCapacity(aircraft.getEClassCapacity());
                      }
                      db.updateAircraft(nAircraft);
                      response = new PackageData("AIRCRAFT EDITED","SERVER",null,null,null,null,null,null,null,null);

                  }else if(data.getOperationType().equals("EDIT FLIGHT")){
                      Flights flight = data.getFlight();
                      Flights nFlight=db.getFlight(flight.getId());
                      if(flight.getAircraft_id()!=null){
                          nFlight.setAircraft_id(flight.getAircraft_id());
                      }else if(flight.getDeparture_city_id()!=null){
                          nFlight.setDeparture_city_id(flight.getDeparture_city_id());
                      }else if(flight.getArrival_city_id()!=null){
                          nFlight.setArrival_city_id(flight.getArrival_city_id());
                      }else if(flight.getDeparture_time()!=null){
                          nFlight.setDeparture_time(flight.getDeparture_time());
                      }else if(flight.getEconom_place_price()!=null){
                          nFlight.setEconom_place_price(flight.getEconom_place_price());
                      }else if(flight.getBusiness_place_price()!=null){
                          nFlight.setBusiness_place_price(flight.getBusiness_place_price());
                      }
                      db.updateFlight(nFlight);
                      response = new PackageData("FLIGHT EDITED","SERVER",null,null,null,null,null,null,null,null);

                  }else if(data.getOperationType().equals("DELETE CITY")){
                      Cities city= data.getCity();
                      db.deleteCity(city.getId());
                      response = new PackageData("CITY DELETED","SERVER",null,null,null,null,null,null,null,null);
                  }else if(data.getOperationType().equals("DELETE AIRCRAFT")){
                      Aircrafts aircraft= data.getAircraft();
                      db.deleteAircraft(aircraft.getId());
                      response = new PackageData("AICRAFT DELETED","SERVER",null,null,null,null,null,null,null,null);
                  }else if(data.getOperationType().equals("DELETE FLIGHT")){
                      Flights flight= data.getFlight();
                      db.deleteFlight(flight.getId());
                      response = new PackageData("FLIGHT DELETED","SERVER",null,null,null,null,null,null,null,null);
                  }
                  System.out.println(response.getOperationType());
                  outputStream.writeObject(response);
              }else if(data.getClientType().equals("KASSIR")){
                  if (data.getOperationType().equals("LIST FLIGHTS")){
                      ArrayList<Flights> flights = db.getAllFlights();
                      response = new PackageData("LIST FLIGHTS SEND", "SERVER", null, null, null, null, flights, null, null, null);
//                      System.out.println(response.getOperationType());
//                      outputStream.writeObject(response);
                  }else if(data.getOperationType().equals("ADD TICKET")){
                      Tickets ticket=data.getTicket();
                      db.addTicket(ticket);
                      response = new PackageData("TICKET ADDED","SERVER",null,null,null,null,null,null,null,ticket);

                  }else if(data.getOperationType().equals("LIST TICKETS")){
                      ArrayList<Tickets> tickets = db.getAllTickets();
                      response = new PackageData("LIST TICKETS SEND", "SERVER", null, null, null, null, null, null, tickets, null);
//
                  }else if(data.getOperationType().equals("EDIT TICKET")){
                      Tickets ticket= data.getTicket();
                      Tickets nTicket =db.getTicket(ticket.getId());
                      if(ticket.getFlight_id()!=null){
                          nTicket.setFlight_id(ticket.getFlight_id());
                      }else if(ticket.getName()!=null){
                          nTicket.setName(ticket.getName());
                      }else if(ticket.getSurname()!=null){
                          nTicket.setSurname(ticket.getSurname());
                      }else if(ticket.getPassport_number()!=null){
                          nTicket.setPassport_number(ticket.getPassport_number());
                      }else if(ticket.getTicket_type()!=null){
                          nTicket.setTicket_type(ticket.getTicket_type());
                      }
                      db.updateTicket(nTicket);
                      response = new PackageData("TICKET EDITED","SERVER",null,null,null,null,null,null,null,null);

                  }else if(data.getOperationType().equals("DELETE TICKET")){
                      Tickets ticket= data.getTicket();
                      db.deleteTicket(ticket.getId());
                      response = new PackageData("TICKET DELETED","SERVER",null,null,null,null,null,null,null,null);
                  }
                  System.out.println(response.getOperationType());
                  outputStream.writeObject(response);
              }else{
                  System.out.println("Data empty");
              }
               data=null;

           }
        }catch (Exception e){
            // e.printStackTrace();
            System.out.println("Client#"+id+" disconnected\n"+"THE SERVER KEEPS LISTENING");
        }
    }
}
