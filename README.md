Este proyecyo fue desarrollado en Java, y su objetivo es simular un sistema de atencion al cliente mediante el uso de estructuras de datos:
-Colas (Deque)
-Pilas (Stack)
-Listas (ArrayList)

FUNCIONALIDAD GENERAL:
El programa permite:
-Registrar nuevos clientes en una cola de atencion
-Visualizar el siguiente cliente en la cola
-Atender clientes creando una orden de servicio asociada con descripcion, estado y fecha en la que se creo
-Deshacer la ultima operacion realizada
-Visualizar informacion detallada de la cola y del historial de las operaciones hechas

ESTRUCTURA DEL PROYECTO:

src/
 ├── model/
 │   ├── Persona.java
 │   ├── Cliente.java
 │   ├── OrdenServicio.java
 │   └── Operacion.java
 │
 ├── service/
 │   └── GestorAtencion.java
 │
 ├── ui/
 │   └── Menu.java
 │
 └── App.java

model -> Clases que representan los elementos del sistema
service -> Gestiona la logica principal (Colas, Pilas y Operaciones)
ui -> Procesa la interaccion con el usuario a traves del menu
App.java -> Punto de entrada de TODO el programa

EJECUCION:
1. Compilar el proyecyo desde una IDE o terminal:

    javac App.java
    
2. Ejecutar el programa:

    java App
    
3. Usar el menu para navegar entre las opciones disponibles

EJEMPLO DE USO:

=== GESTOR DE ATENCION Y ORDENES ===

--- MENÚ PRINCIPAL ---
1. Encolar cliente
2. Ver siguiente en la cola
3. Atender cliente (crear orden)
4. Ver tamaño/lista de la cola
5. Deshacer ultima operación (pila)
6. Ver tope/tamaño/lista del historial
0. Salir

Al atender a un cliente YA ingresado (Encolado) como "Andres Cascadas", con Documento de 12331348 y prioridad 2:

Cliente atendido exitosamente:
Cliente: Andres Cascadas
Documento: 12331348
Prioridad: 2
Orden: ORD-001
Fecha de creación: Thu Oct 24 21:15:00 GMT-05:00 2025
Problema: No se enciende el equipo
Estado: ABIERTA




AUTOR:
Matthew Habib Corzo Torres
Proyecto académico – Programación Orientada a Objetos
Universidad de Ibagué
2025
