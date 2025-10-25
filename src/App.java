/*App hecha por Matthew Corzo - 2220241107
Con amor, esfuerzo y trasnocho
*/

import ui.*;

import service.GestorAtencion;

public class App {
    public static void main(String[] args) {
        
        // Crear el gestor de atención
        GestorAtencion gestor = new GestorAtencion();

        // Iniciar el menú principal
        Menu menu = new Menu(gestor);
        menu.iniciar();
    }
}
