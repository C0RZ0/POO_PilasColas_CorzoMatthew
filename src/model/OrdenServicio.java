package model;

import java.util.Date;

public class OrdenServicio{
    
    public enum EstadoOrden {  //Enum para los estados posibles de una orden
        ABIERTA,
        CERRADA
    }
    
    private String id, customer, problemDescription;
    private Date date;
    private EstadoOrden status;    

    //Constructor
    public OrdenServicio(String id, String customer, String problemDescription, Date date) {
        this.id = id;
        this.customer = customer;
        this.problemDescription = problemDescription;
        this.date = date;
        this.status = EstadoOrden.ABIERTA; //Siempre empieza ABIERTA
    }

    //Getter y Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EstadoOrden getStatus() {
        return status;
    }

    public void setStatus(EstadoOrden status) {
        this.status = status;
    }
    
   //Metodo abstracto
    @Override
    public String toString() {
        return "OrdenServicio (ID=" + id + ", Cliente: " + customer + ", Descripción: " + problemDescription + ", Fecha: " + date + ", Estado: " + status + ")";
    }
    
}
