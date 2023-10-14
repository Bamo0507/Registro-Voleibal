//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 13/10/2023
//Última modificación: 13/10/2023

//Librerías a utilizar
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Campeonato {
    private static ManejoCSV manejoCSV = new ManejoCSV();
    private static ArrayList<Jugador> jugadores = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static String seleccion;
    private static boolean systemON = true;
    private static boolean systemON2 = true;
    private static int cuenta = 0;
    public static void main(String[] args){
        //Se cargan los jugadores con los que se cuentan actualmente
        jugadores = manejoCSV.leerCSV("RegistroJugadores.csv");
        //Mensaje de bienvenida
        System.out.println("***********************************************************");
        System.out.println("*  __        _______ _     ____ ___  __  __ _____ _ _ _   *");
        System.out.println("*  \\ \\      / / ____| |   / ___/ _ \\|  \\/  | ____| | | |  *");
        System.out.println("*   \\ \\ /\\ / /|  _| | |  | |  | | | | |\\/| |  _| | | | |  *");
        System.out.println("*    \\ V  V / | |___| |__| |__| |_| | |  | | |___|_|_|_|  *");
        System.out.println("*     \\_/\\_/  |_____|_____\\____\\___/|_|  |_|_____(_|_|_)  *");
        System.out.println("***********************************************************");

        System.out.println("\nBuenos días querido usuario ;)\n" + "¿Qué deseas hacer el día de hoy?\n");
        //Ciclo para el menú principal
        while (systemON){
            System.out.println(menuPrincipal());
            //Opciones a desarrollar en el primer menú
            switch (seleccion = sc.nextLine()){
                //Se agregan y actualizan los jugadores
                case "1":
                    manejoCSV.agregarJugador("RegistroJugadores.csv");
                    jugadores = manejoCSV.leerCSV("RegistroJugadores.csv");
                    break;
                //Acceso a menú de información administrativa
                case "2":
                    systemON2 = true;
                    while(systemON2){
                        System.out.println(menuAdministrativo());
                        switch (seleccion = sc.nextLine()){
                            //Se muestra la información de todos los jugadores
                            case "1":
                                for(Jugador jugador: jugadores){
                                    System.out.println(jugador);
                                }
                                System.out.println("");
                                break;
                            //Se muestran los tres mejores líberos
                            case "2":    
                                //Se ordena en orden descendente los jugadores acorde a su valor para la efectividad                     
                                Comparator<Jugador> compararEfectividad = (j1, j2) -> Float.compare(j2.getEfectividad(), j1.getEfectividad());
                                Collections.sort(jugadores, compararEfectividad);
                            
                                int cuenta = 0;
                                for (Jugador jugador : jugadores) {
                                    //Se verifica que aún no se tengan 3 líberos, y si es de este tipo el jugador
                                    if (cuenta < 3 && jugador instanceof Libero) {
                                        System.out.println("---------------------------------");
                                        System.out.println(jugador);
                                        System.out.println("---------------------------------");
                                        cuenta++;
                                    }
                                }
                                break;
                            
                            //Se cuenta la cantidad de pasadores con más de 80% de efectividad
                            case "3":
                                cuenta = 0;
                                for(Jugador jugador: jugadores){
                                    if(jugador instanceof Pasador && jugador.getEfectividad()>80){
                                        cuenta++;
                                    }
                                }
                                System.out.println("Hay " + cuenta + " pasadores con más del 80% de efectividad.\n");
                                break;
                            
                            //Regresamos al menú principal
                            case "4":
                                systemON2 = false;
                                System.out.println("Regresando al menú principal...");
                                break;
                            //Mensaje a mostrar si no se selecciona algo válido
                            default:
                                System.out.println("Por favor, seleccione una opción válida.");
                                break;
                        }
                    }
                    break;
                
                //Mensaje a mostrar al salir del código
                case "3":
                    System.out.println("Que tenga un buen día :)");
                    systemON = false;
                    break;
                //Mensaje a mostrar en caso se seleccione algo inválido
                default:
                    System.out.println("Por favor, selecciona una opción válida...");
                    break;
            }
        }        
    }
    //Devuelve el menú principal 
    public static String menuPrincipal(){
        return "1. Agregar un jugador\n" + "2. Acceder a la información administrativa\n" + "3. Salir\n" + "Seleccione una opción: "; 
    }
    //Devuelve el menú para la información adminsitrativa
    public static String menuAdministrativo(){
        return "1. Mostrar todos los jugadores inscritos\n" + "2. Conocer el top 3 de líberos\n" + "3. Conocer cuántos pasadores hay con más de 80% de efectividad\n" + "4. Regresar al menú principal";
    }

}
