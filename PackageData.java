package BigProject;


import MiniProject.Client;

import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    private String operationType;
    private String clientType;
    private ArrayList<Aircrafts> aircrafts;
    private Aircrafts aircraft;
    private ArrayList<Cities> cities;
    private Cities city;
    private ArrayList<Flights> flights;
    private Flights flight;
    private ArrayList<Tickets> tickets;
    private Tickets ticket;

    public PackageData(){}
    public PackageData(String operationType, String clientType,
                       ArrayList<Aircrafts> aircrafts, Aircrafts aircraft,
                       ArrayList<Cities> cities, Cities city, ArrayList<Flights> flights, Flights flight,
                       ArrayList<Tickets> tickets, Tickets ticket){
        this.operationType=operationType;
        this.clientType=clientType;
        this.aircrafts=aircrafts;
        this.aircraft=aircraft;
        this.cities=cities;
        this.city=city;
        this.flights=flights;
        this.flight=flight;
        this.tickets=tickets;
        this.ticket=ticket;
    }

    public void setOperationType(String operationType){this.operationType=operationType;}
    public String getOperationType(){return operationType;}

    public void setClientType(String clientType){this.clientType=clientType;}
    public String getClientType(){return clientType;}


    public void setAircrafts(ArrayList<Aircrafts> aircrafts){this.aircrafts=aircrafts;}
    public ArrayList<Aircrafts> getAircrafts(){return aircrafts;}

    public void setAircraft(Aircrafts aircraft){this.aircraft=aircraft;}
    public Aircrafts getAircraft(){return aircraft;}

    public void addAircraft(Aircrafts aircraft){
        aircrafts.add(aircraft);
    }

    public void setCities(ArrayList<Cities> cities){this.cities=cities;}
    public ArrayList<Cities> getCities(){return cities;}

    public void setCity(Cities city){this.city=city;}
    public Cities getCity(){return city;}

    public void addCity(Cities city){
        cities.add(city);
    }

    public void setFlights(ArrayList<Flights> flights){this.flights=flights;}
    public ArrayList<Flights> getFlights(){return flights;}

    public void setFlight(Flights flight){this.flight=flight;}
    public Flights getFlight(){return flight;}

    public void addFlight(Flights flight){
        flights.add(flight);
    }

    public void setTickets(ArrayList<Tickets> tickets){this.tickets=tickets;}
    public ArrayList<Tickets> getTickets(){return tickets;}

    public void setTicket(Tickets ticket){this.ticket=ticket;}
    public Tickets getTicket(){return ticket;}

    public void addTicket(Tickets ticket){
        tickets.add(ticket);
    }



}
