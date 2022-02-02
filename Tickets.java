package BigProject;

import java.io.Serializable;

public class Tickets implements Serializable {
    private Long id;
    private Long flight_id;
    private String name;
    private String surname;
    private String passport_number;
    private String ticket_type;

    public Tickets() {}

    public Tickets(Long id, Long flight_id, String name, String surname, String passport_number, String ticket_type) {
        this.id = id;
        this.flight_id = flight_id;
        this.name = name;
        this.surname = surname;
        this.passport_number = passport_number;
        this.ticket_type = ticket_type;
    }

    public Long getId() {
        return id;
    }

    public void setFlight_id(Long flight_id) {
        this.flight_id = flight_id;
    }

    public Long getFlight_id() {
        return flight_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname){this.surname=surname;}
    public String getSurname(){return surname;}

    public void setPassport_number(String passport_number){this.passport_number=passport_number;}
    public String getPassport_number(){return passport_number;}

    public void setTicket_type(String ticket_type){this.ticket_type=ticket_type;}
    public String getTicket_type(){return ticket_type;}

    @Override
    public String toString(){
        return "id:"+id+
                "; flight_oid"+flight_id+
                "; name:"+name+
                "; surname:"+surname+
                "; passport_number:"+passport_number+
                "; ticket_type:"+ticket_type;
    }
}