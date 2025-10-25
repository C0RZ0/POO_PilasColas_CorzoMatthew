package model;

public class Operacion {

    public enum TipoOperacion {
        ATENCION  //Por ahora solo hay este tipo
    }

    private TipoOperacion type;
    private Cliente customer;
    private OrdenServicio order;

    //Constructor
    public Operacion(TipoOperacion type, Cliente customer, OrdenServicio order) {
        this.type = type;
        this.customer = customer;
        this.order = order;
    }
    
    //Getters y setters
    public TipoOperacion getType() {
        return type;
    }

    public void setType(TipoOperacion type) {
        this.type = type;
    }

    public Cliente getCustomer() {
        return customer;
    }

    public void setCustomer(Cliente customer) {
        this.customer = customer;
    }

    public OrdenServicio getOrder() {
        return order;
    }

    public void setOrder(OrdenServicio order) {    
        this.order = order;
    }

    //Metodo abstracto
    @Override
    public String toString() {
        return "Operacion (Tipo: " + type + ", Cliente: " + customer.getName() + " " + customer.getSurnames() + ", Orden: " + order.getId() + "}";
    }
}
