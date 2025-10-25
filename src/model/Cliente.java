package model;

public class Cliente extends Persona {
    
    //Algunos atributos yo los escribi en ingles :)
    private int priority;
    
    //Constructor
    public Cliente(int priority, String name, String surnames, String documentId){
        super(name, surnames, documentId);
        this.priority = priority;
    }
    
    //Getter y Setter 
    public int getPriority() {
        return priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    } 
    
    //Metodos abstractos
    @Override
    public String description() {
        return "Cliente: " + getName() + " - Documento: " + getDocumentId();
    }
    
    @Override
    public String toString(){
        
        String base = "Cliente: " + getName() + " - Docuento: " + getDocumentId() + ")";
        
        if (priority > 0){
            base += " - Prioridad: " + priority;
        }
        return base;
    }
}