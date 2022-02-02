package BigProject;

import java.io.Serializable;

public class Aircrafts implements Serializable {
    private Long id;
    private String name;
    private String model;
    private Long bClassCapacity;
    private Long eClassCapacity;

    public Aircrafts(){}
    public Aircrafts(Long id, String name, String model, Long bClassCapacity, Long eClassCapacity){
        this.id=id;
        this.name=name;
        this.model=model;
        this.eClassCapacity=eClassCapacity;
        this.bClassCapacity=bClassCapacity;
    }

    public Long getId(){return id;}

    public void setName(String name){this.name=name;}
    public String getName(){return name;}

    public void setModel(String model){this.model=model;}
    public String getModel(){return model;}

    public void setBClassCapacity(Long bClassCapacity){this.bClassCapacity=bClassCapacity;}
    public Long getBClassCapacity(){return bClassCapacity;}

    public void setEClassCapacity(Long eClassCapacity){this.eClassCapacity=eClassCapacity;}
    public Long getEClassCapacity(){return eClassCapacity;}

    @Override
    public String toString(){
        return "id:"+id+"; name:"+name+"; model:"+model+"; Business class capacity:"+bClassCapacity+
                "; Econom class capacity:"+eClassCapacity;
    }
}
