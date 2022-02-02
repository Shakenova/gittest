package BigProject;

import java.io.Serializable;

public class Cities implements Serializable {
    private Long id;
    private String name;
    private String country;
    private String short_name;

    public Cities(){}
    public Cities(Long id, String name, String country, String short_name){
        this.id=id;
        this.name=name;
        this.country=country;
        this.short_name=short_name;
    }

    public Long getId(){return id;}

    public void setName(String name){this.name=name;}
    public String getName(){return name;}

    public void setCountry(String country){this.country=country;}
    public String getCountry(){return country;}

    public void setShort_name(String short_name){this.short_name=short_name;}
    public String getShort_name(){return short_name;}

    @Override
    public String toString(){
        return "id:"+id+"; name:"+name+"; country:"+country+"; short name:"+short_name;
    }
}

