package model;

public abstract class Persona {

    private String name, surnames, documentId;

    //Constructor
    public Persona(String name, String surnames, String documentId) {
        this.name = name;
        this.surnames = surnames;
        this.documentId = documentId;
    }

    //Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    // Método abstracto
    public abstract String description();

    @Override
    public String toString() {
        return name + " - Documento: " + documentId;
    }
}
