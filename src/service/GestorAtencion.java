package service;

import model.*;
import java.util.*;

public class GestorAtencion {
    
    private Deque<Cliente> colaClientes = new LinkedList<>();   //Cola (en este caso de doble etremo) para clientes en espera
    private Stack<Operacion> historial = new Stack<>();  //Pila para historial de operaciones
    private List<OrdenServicio> ordenes = new ArrayList<>();  //Lista de todas las órdenes de servicio creadas
    private int contarOrdenes = 1;
    
    //Se recibe al objeto "cliente"
    public void encolarCliente(Cliente cliente) {
        colaClientes.add(cliente);
        System.out.println("\n\nCliente encolado exitosamente:");
        System.out.println("Nombre: " + cliente.getName() + " " + cliente.getSurnames());
        System.out.println("Documento: " + cliente.getDocumentId());
        System.out.println("Prioridad: " + cliente.getPriority());
        System.out.println("Posición en la cola: " + colaClientes.size());
    }

    //Retornar el cliente con todos los datos
    public Cliente verSiguiente(){               
        if (colaClientes.isEmpty()) {
            System.out.println("No hay clientes en la cola");
            return null;
        }
    
        Cliente siguiente = colaClientes.peek();
        
        System.out.println("\n--- SIGUIENTE CLIENTE EN LA COLA ---");
        System.out.println("Cliente: " + siguiente.getName() + " " + siguiente.getSurnames());
        System.out.println("Documento: " + siguiente.getDocumentId());
        System.out.println("Prioridad: " + siguiente.getPriority());
        System.out.println("Clientes en cola (incluido este): " + colaClientes.size());
        return siguiente;   
    }

    
    //Se atiende a un Cliente, se crea "OrdenServicio" y se guarda la Operacion
    public OrdenServicio atenderCliente(String descripcionProblema) {
        //Verificar si hay clientes en la cola
        if (colaClientes.isEmpty()) {
            System.out.println("\n\n\nNo hay clientes en la cola...");
            return null; //En caso de no haber clientes, salir
        }
    
        //Validar la descripción del problema
        if (descripcionProblema == null || descripcionProblema.trim().isEmpty()) {
            System.out.println("\nLa descripción del problema NO puede estar vacía.");
            return null; //En caso de que la descripcion NO sea valida, salir
        }
    
        //Obtener el cliente
        Cliente cliente = colaClientes.poll();
    
        //Generar ID unico para el orden 
        String idOrden = String.format("ORD-%03d", contarOrdenes++);
    
        //Crear la orden de servicio
        String nombreCliente = cliente.getName() + " " + cliente.getSurnames();
        OrdenServicio orden = new OrdenServicio(idOrden, nombreCliente, descripcionProblema.trim(), new Date());
        ordenes.add(orden);
    
        //Registrar en el historial (O sea, guardarlo en la pila)
        Operacion operacion = new Operacion(Operacion.TipoOperacion.ATENCION, cliente, orden);
        historial.push(operacion);

        //Mostrar informacion
        System.out.println("\n\nCliente atendido exitosamente:");
        System.out.println("Cliente: " + cliente.getName() + " " + cliente.getSurnames());
        System.out.println("Documento: " + cliente.getDocumentId());
        System.out.println("Prioridad: " + cliente.getPriority());
        System.out.println("Fecha de creación: " + orden.getDate());
        System.out.println("Orden: " + idOrden);
        System.out.println("Problema: " + orden.getProblemDescription());
        System.out.println("Estado: " + orden.getStatus());
        return orden;
    }
    
    //Tamaño de la cola
    public int getTamanioCola() {
        return colaClientes.size();
    }
    //Listar a los clientes con toda su informacion
    public void listarCola() {
        System.out.println("\n||---- COLA DE CLIENTES ----||");
        
        if (colaClientes.isEmpty()) {
            System.out.println("\n\n\nNo hay clientes en la cola...");
            return;
        }
        
        System.out.println("Total de clientes en la cola: " + colaClientes.size());
        System.out.println("--------------------------------------");
        
        int posicion = 1;
        for (Cliente c : colaClientes) {
            System.out.println(posicion + ". " + c); // toString() del Cliente
            posicion++;
        }        
    }
    
    //Deshacer una opcion
    public boolean deshacerUltimaOperacion() {
        if (historial.isEmpty()) {
            System.out.println("\n\n\nNo hay operaciones para deshacer");         
        }

        // Sacar la ultima operación 
        Operacion ultimaOp = historial.pop();

        // Recuperar el cliente y la orden
        Cliente cliente = ultimaOp.getCustomer();
        OrdenServicio orden = ultimaOp.getOrder();

        // Eliminar la orden de la lista
        ordenes.remove(orden);

        // Devolver cliente al frente de la cola
        ((LinkedList<Cliente>) colaClientes).addFirst(cliente);

        System.out.println("\nOperación deshecha exitosamente:");
        System.out.println("  Cliente devuelto: " + cliente.getName() + " " + cliente.getSurnames());
        System.out.println("  Orden eliminada: #" + orden.getId());
        return true;
    }

    //Ver tope del historial
    public Operacion verTopeHistorial() {
        if (historial.isEmpty()) {
            System.out.println("\n\n\nEl historial está vacío...");
            return null;
        }
    
        Operacion tope = historial.peek();
        System.out.println("\n||--- ULTIMA OPERACION ---||");
        System.out.println(tope.toString());
        return tope;
    }
    
    //Tamaño de la pila
    public int getTamanioHistorial() {
        return historial.size();
    }

    public void listarHistorial() {
        System.out.println("\n\n\n========== HISTORIAL DE OPERACIONES ==========\n");
        
        if (historial.isEmpty()) {
            System.out.println("El historial esta vacio...");
            return;
        }
        
        System.out.println("Total: " + historial.size());
        System.out.println("----------------------------------------------");
        
        int num = 1;
        for (Operacion op : historial) {
            System.out.println("\n#" + num + " - " + op);
            num++;
        }
        System.out.println("==============================================\n");
    }
    
    public void listarTodasLasOrdenes() {
        System.out.println("\n========== ORDENES DE SERVICIO ==========");
    
        if (ordenes.isEmpty()) {
            System.out.println("No hay ordenes registradas");
            return;
        }
    
        System.out.println("Total: " + ordenes.size());
        System.out.println("-----------------------------------------");
    
        for (OrdenServicio orden : ordenes) {
            System.out.println(orden);
        }
    
        System.out.println("=========================================\n");
    }

    //Verificaciones
    
    public boolean colaVacia() {
        return colaClientes.isEmpty();
    }
    
    public boolean historialVacio() {
        return historial.isEmpty();
    }     
}
