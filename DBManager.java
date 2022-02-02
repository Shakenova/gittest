package BigProject;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    //Наш объект connection, который хранит данные по подключению. Вся движуха идет через него. Что-то на подобие Socket.
    private Connection connection;

    public void connect(){
        try{
            //Подтягиваем драйвер, который мы добавили в самом начале урока
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Создаем подключение.
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bigproject?useUnicode=true&serverTimezone=UTC","root", ""
            );
            System.out.println("CONNECTED");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<Aircrafts> getAllAircrafts(){

        ArrayList<Aircrafts> aircrafts = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM aircrafts");

            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String model = rs.getString("model");
                Long bClassCapacity = rs.getLong("business_class_capacity");
                Long eClassCapacity = rs.getLong("econom_class_capacity");


                aircrafts.add(new Aircrafts(id,name,model,bClassCapacity,eClassCapacity));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return aircrafts;
    }

    public Aircrafts getAircraft(Long id){
        Aircrafts aircraft = new Aircrafts();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM aircrafts where id = ?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                String name  = rs.getString("name");
                String model = rs.getString("model");
                Long business_class_capacity = rs.getLong("business_class_capacity");
                Long econom_class_capacity = rs.getLong("econom_class_capacity");

                aircraft=new Aircrafts(id,name,model,business_class_capacity,econom_class_capacity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return aircraft;
    }
    public void addAircraft(Aircrafts aircraft){
        try{
            PreparedStatement st = connection.prepareStatement("INSERT INTO aircrafts(id, name, model,business_class_capacity,econom_class_capacity) values(NULL,?,?,?,?)");

            st.setString(1,aircraft.getName());
            st.setString(2,aircraft.getModel());
            st.setLong(3,aircraft.getBClassCapacity());
            st.setLong(4,aircraft.getEClassCapacity());

            st.executeUpdate();

            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteAircraft(Long id){
        try{
            PreparedStatement st = connection.prepareStatement("DELETE FROM aircrafts where id = ?");
            st.setLong(1, id);
            st.executeUpdate();

            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateAircraft(Aircrafts aircraft){
        try{
            PreparedStatement st = connection.prepareStatement("UPDATE aircrafts set name = ?, model = ?, business_class_capacity = ?, econom_class_capacity=? where id = ?");

            st.setString(1, aircraft.getName());
            st.setString(2, aircraft.getModel());
            st.setLong(3, aircraft.getBClassCapacity());
            st.setLong(4, aircraft.getEClassCapacity());
            st.setLong(5, aircraft.getId());

            st.executeUpdate();

            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Cities> getAllCities(){

        ArrayList<Cities> cities = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM cities");

            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String country = rs.getString("country");
                String short_name= rs.getString("short_name");


                cities.add(new Cities(id,name,country,short_name));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return cities;
    }
    public Cities getCity(Long id){
        Cities city = new Cities();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM cities where id = ?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                String name = rs.getString("name");
                String country = rs.getString("country");
                String short_name= rs.getString("short_name");


               city=new Cities(id,name,country,short_name);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return city;
    }

    public void addCity(Cities city){
        try{
            PreparedStatement st = connection.prepareStatement("INSERT INTO cities(id, name,country,short_name) values(NULL,?,?,?)");

            st.setString(1,city.getName());
            st.setString(2,city.getCountry());
            st.setString(3,city.getShort_name());


            st.executeUpdate();

            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteCity(Long id){
        try{
            PreparedStatement st = connection.prepareStatement("DELETE FROM cities where id = ?");
            st.setLong(1, id);
            st.executeUpdate();

            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateCity(Cities city) {
        try {
            PreparedStatement st = connection.prepareStatement("UPDATE cities set name = ?, country = ?, short_name = ? where id = ?");

            st.setString(1, city.getName());
            st.setString(2, city.getCountry());
            st.setString(3, city.getShort_name());
            st.setLong(4, city.getId());

            st.executeUpdate();

            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public ArrayList<Flights> getAllFlights(){

            ArrayList<Flights> flights = new ArrayList<>();
            try{
                PreparedStatement st = connection.prepareStatement("SELECT * FROM flights");

                ResultSet rs = st.executeQuery();

                while (rs.next()){
                    Long id = rs.getLong("id");
                    Long aircraft_id = rs.getLong("aircraft_id");
                    Long departure_city_id = rs.getLong("departure_city_id");
                    Long arrival_city_id =rs.getLong("arrival_city_id");
                    String departure_time = rs.getString("departure_time");
                    Long econom_place_price = rs.getLong("econom_place_price");
                    Long business_place_price = rs.getLong("business_place_price");

                    flights.add(new Flights(id,aircraft_id,departure_city_id,arrival_city_id,departure_time,econom_place_price,business_place_price));
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return flights;
        }
    public Flights getFlight(Long id){
        Flights flight = new Flights();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM flights where id = ?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Long aircraft_id = rs.getLong("aircraft_id");
                Long departure_city_id = rs.getLong("departure_city_id");
                Long arrival_city_id = rs.getLong("arrival_city_id");
                String departure_time = rs.getString("departure_time");
                Long econom_place_price=rs.getLong("econom_place_price");
                Long business_place_price = rs.getLong("business_place_price");


                flight=new Flights(id,aircraft_id,departure_city_id,arrival_city_id,departure_time,econom_place_price,business_place_price);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flight;
    }

        public void addFlight(Flights flight){
            try{
                PreparedStatement st = connection.prepareStatement("INSERT INTO flights(id,aircraft_id,departure_city_id,arrival_city_id,departure_time,econom_place_price,business_place_price) values(NULL,?,?,?,?,?,?)");

                st.setLong(1,flight.getAircraft_id());
                st.setLong(2,flight.getDeparture_city_id());
                st.setLong(3,flight.getArrival_city_id());
                st.setString(4,flight.getDeparture_time());
                st.setLong(5,flight.getEconom_place_price());
                st.setLong(6,flight.getBusiness_place_price());


                st.executeUpdate();

                st.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public void deleteFlight(Long id){
            try{
                PreparedStatement st = connection.prepareStatement("DELETE FROM flights where id = ?");
                st.setLong(1, id);
                st.executeUpdate();

                st.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public void updateFlight(Flights flight){
            try{
                PreparedStatement st = connection.prepareStatement("UPDATE flights set aircraft_id= ?, departure_city_id= ?, arrival_city_id= ?,departure_time=?, econom_place_price=?,business_place_price=? where id = ?");

                st.setLong(1, flight.getAircraft_id());
                st.setLong(2, flight.getDeparture_city_id());
                st.setLong(3, flight.getArrival_city_id());
                st.setString(4,flight.getDeparture_time());
                st.setLong(5,flight.getEconom_place_price());
                st.setLong(6,flight.getBusiness_place_price());
                st.setLong(7, flight.getId());

                st.executeUpdate();

                st.close();
            }catch (Exception e){
                e.printStackTrace();
            }
    }

    public ArrayList<Tickets> getAllTickets(){

        ArrayList<Tickets> tickets = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM tickets");

            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Long id = rs.getLong("id");
                Long flight_id  = rs.getLong("flight_id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String passport_number = rs.getString("passport_number");
                String ticket_type = rs.getString("ticket_type");

                tickets.add(new Tickets(id,flight_id,name,surname,passport_number,ticket_type));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return tickets;
    }

    public Tickets getTicket(Long id){
        Tickets ticket = new Tickets();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM tickets where id = ?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Long flight_id  = rs.getLong("flight_id");
                String  name = rs.getString("name");
                String surname = rs.getString("surname");
                String passport_number = rs.getString("passport_number");
                String ticket_type=rs.getString("ticket_type");

                ticket=new Tickets(id,flight_id,name,surname,passport_number,ticket_type);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ticket;
    }

    public void addTicket(Tickets ticket){
        try{
            PreparedStatement st = connection.prepareStatement("INSERT INTO tickets(id,flight_id,name,surname,passport_number,ticket_type) values(NULL,?,?,?,?,?)");

            st.setLong(1,ticket.getFlight_id());
            st.setString(2,ticket.getName());
            st.setString(3,ticket.getSurname());
            st.setString(4,ticket.getPassport_number());
            st.setString(5,ticket.getTicket_type());


            st.executeUpdate();

            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteTicket(Long id){
        try{
            PreparedStatement st = connection.prepareStatement("DELETE FROM tickets where id = ?");
            st.setLong(1, id);
            st.executeUpdate();

            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateTicket(Tickets ticket){
        try{
            PreparedStatement st = connection.prepareStatement("UPDATE tickets set flight_id= ?,name= ?,surname= ?,passport_number=?,ticket_type=? where id = ?");

            st.setLong(1, ticket.getFlight_id());
            st.setString(2, ticket.getName());
            st.setString(3, ticket.getSurname());
            st.setString(4,ticket.getPassport_number());
            st.setString(5,ticket.getTicket_type());
            st.setLong(6, ticket.getId());

            st.executeUpdate();

            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
