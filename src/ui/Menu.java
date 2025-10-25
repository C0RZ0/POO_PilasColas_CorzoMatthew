package ui;

import service.GestorAtencion;
import model.Cliente;
import java.util.Scanner;

//Menu con las distintas funciones y metodos creados en GestorAtencion
public class Menu {
    
    private GestorAtencion gestor;
    private Scanner scanner;
    
    public Menu(GestorAtencion gestor) {
        this.gestor = gestor;
        this.scanner = new Scanner(System.in);
    }
    
    public void iniciar() {
        System.out.println("\n=== GESTOR DE ATENCION Y ORDENES ===\n");
        
        int opcion = -1;
        
        while (opcion != 0) {
            mostrarMenu();
            
            try {
                System.out.print("\nOpción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); //Limpiar buffer
                
                System.out.println(); //Salto de línea
                
                switch (opcion) {
                    case 1:
                        encolarCliente();
                        break;
                    case 2:
                        gestor.verSiguiente();
                        break;
                    case 3:
                        atenderCliente();
                        break;
                    case 4:
                        verInfoCola();
                        break;
                    case 5:
                        deshacerOperacion();
                        break;
                    case 6:
                        verInfoHistorial();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opción NO valida, Intente de nuevo");
                }
                
            } catch (Exception e) {
                System.out.println("\nError - Ingrese un numero valido.");
                scanner.nextLine(); //Limpiar buffer
                opcion = -1;
            }
        }
        
        //Decir adios ;(
        scanner.close();
        System.out.println("Cerrando sistema....\n");
    }
    
    //Menu de opciones
    private void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Encolar cliente");
        System.out.println("2. Ver siguiente en la cola");
        System.out.println("3. Atender cliente (crear orden)");
        System.out.println("4. Ver tamaño/lista de la cola");
        System.out.println("5. Deshacer ultima operación (pila)");
        System.out.println("6. Ver tope/tamaño/lista del historial");
        System.out.println("0. Salir");
    }
    
    //Encolar nuevo cliente
    private void encolarCliente() {
        System.out.println("--- ENCOLAR CLIENTE ---");
        
        //Se usa un Try-catch para evitar cualquier intento de ingresar un valor donde no es
        try {
            // Pedir nombre
            System.out.print("Nombre: ");
            String name = scanner.nextLine().trim();
            
            if (name.length() < 2) {
                System.out.println("\nError - El nombre DEBE tener al menos 2 caracteres");
                return;
            }
            
            //Pedir apellidos
            System.out.print("Apellidos: ");
            String surnames = scanner.nextLine().trim();
            
            if (surnames.length() < 2) {
                System.out.println("\nError - Los apellidos DEBEN tener al menos 2 caracteres");
                return;
            }
            
            //Pedir documento
            System.out.print("Documento: ");
            String documentId = scanner.nextLine().trim();
            
            if (documentId.isEmpty()) {
                System.out.println("\nError - El documento es obligatorio");
                return;
            }
            
            //Pedir prioridad
            System.out.print("Prioridad (0-10): ");
            int priority = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            if (priority < 0 || priority > 10) {
                System.out.println("\nError - La prioridad debe estar entre 0 y 10.");
                return;
            }
            
            //Crear y encolar cliente
            Cliente cliente = new Cliente(priority, name, surnames, documentId);
            gestor.encolarCliente(cliente);
            
        } catch (Exception e) {
            System.out.println("\nError al crear el cliente....");
            scanner.nextLine(); //Limpiar buffer
        }
    }
    
    //Atender al siguiente cliente
    private void atenderCliente() {
        System.out.println("--- ATENDER CLIENTE ---");
        
        if (gestor.colaVacia()) {
            System.out.println("\nNo hay clientes en la cola");
            return;
        }
        
        // Mostrar el siguiente cliente
        gestor.verSiguiente();
        
        // Pedir descripción del problema
        System.out.print("\nDescripción del problema: ");
        String descripcion = scanner.nextLine().trim();
        
        if (descripcion.isEmpty()) {
            System.out.println("\nError - La descripción es obligatoria.");
            return;
        }
        
        // Atender
        gestor.atenderCliente(descripcion);
    }
    
    //Mostrar informacion de la cola
    private void verInfoCola() {
        System.out.println("--- INFORMACION DE LA COLA ---");
        System.out.println("\nTamaño de la cola: " + gestor.getTamanioCola());
        gestor.listarCola();
    }
    
    //Deshacer la ultima operacion
    private void deshacerOperacion() {
        System.out.println("--- DESHACER OPERACION ---");
        
        if (gestor.historialVacio()) {
            System.out.println("\nNo hay operaciones para deshacer");
            return;
        }
        
        // Mostrar la operación a deshacer
        gestor.verTopeHistorial();
        
        // Pedir confirmación
        System.out.print("\n¿Deshacer esta operación? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase(); //Uso .trim y .tolowercase para no tomar en cuenta espacios y no diferencias mayus. y minus.
        
        //Rectificar la respuesta de si o no
        if (confirmacion.equals("s")) {
            gestor.deshacerUltimaOperacion();
        } else {
            System.out.println("Operación cancelada.");
        }
    }
    
    //Mostrar informacion del historial
    private void verInfoHistorial() {
        System.out.println("--- INFORMACIÓN DEL HISTORIAL ---");
        System.out.println("\nTamaño del historial: " + gestor.getTamanioHistorial());
        
        if (gestor.historialVacio()) {
            System.out.println("\nEl historial esta vacio...");
        } else {
            System.out.println("\nTope del historial: ");
            gestor.verTopeHistorial();
        }
        
        System.out.println("\nLista completa:");
        gestor.listarHistorial();
    }
}
