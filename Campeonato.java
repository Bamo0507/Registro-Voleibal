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
        jugadores = manejoCSV.leerCSV("RegistroJugadores.csv");
        System.out.println("***********************************************************");
        System.out.println("*  __        _______ _     ____ ___  __  __ _____ _ _ _   *");
        System.out.println("*  \\ \\      / / ____| |   / ___/ _ \\|  \\/  | ____| | | |  *");
        System.out.println("*   \\ \\ /\\ / /|  _| | |  | |  | | | | |\\/| |  _| | | | |  *");
        System.out.println("*    \\ V  V / | |___| |__| |__| |_| | |  | | |___|_|_|_|  *");
        System.out.println("*     \\_/\\_/  |_____|_____\\____\\___/|_|  |_|_____(_|_|_)  *");
        System.out.println("***********************************************************");

        System.out.println("\nBuenos días querido usuario ;)\n" + "¿Qué deseas hacer el día de hoy?\n");
        while (systemON){
            System.out.println(menuPrincipal());
            switch (seleccion = sc.nextLine()){
                case "1":
                    manejoCSV.agregarJugador("RegistroJugadores.csv");
                    jugadores = manejoCSV.leerCSV("RegistroJugadores.csv");
                    break;

                case "2":
                    systemON2 = true;
                    while(systemON2){
                        System.out.println(menuAdministrativo());
                        switch (seleccion = sc.nextLine()){
                            case "1":
                                for(Jugador jugador: jugadores){
                                    System.out.println(jugador);
                                }
                                System.out.println("");
                                break;
                                case "2":                         
                                Comparator<Jugador> compararEfectividad = (j1, j2) -> Float.compare(j2.getEfectividad(), j1.getEfectividad());
                                Collections.sort(jugadores, compararEfectividad);
                            
                                int cuenta = 0;
                                for (Jugador jugador : jugadores) {
                                    if (cuenta < 3 && jugador instanceof Libero) {
                                        System.out.println("---------------------------------");
                                        System.out.println(jugador);
                                        System.out.println("---------------------------------");
                                        cuenta++;
                                    }
                                }
                                break;
                            
                            case "3":
                                cuenta = 0;
                                for(Jugador jugador: jugadores){
                                    if(jugador instanceof Pasador && jugador.getEfectividad()>80){
                                        cuenta++;
                                    }
                                }
                                System.out.println("Hay " + cuenta + " pasadores con más del 80% de efectividad.\n");
                                break;
                            case "4":
                                systemON2 = false;
                                System.out.println("Regresando al menú principal...");
                                break;
                            default:
                                System.out.println("Por favor, seleccione una opción válida.");
                                break;
                        }
                    }
                    break;

                case "3":
                    System.out.println("Que tenga un buen día :)");
                    systemON = false;
                    break;

                default:
                    System.out.println("Por favor, selecciona una opción válida...");
                    break;
            }
        }        
    }
    
    public static String menuPrincipal(){
        return "1. Agregar un jugador\n" + "2. Acceder a la información administrativa\n" + "3. Salir\n" + "Seleccione una opción: "; 
    }

    public static String menuAdministrativo(){
        return "1. Mostrar todos los jugadores inscritos\n" + "2. Conocer el top 3 de líberos\n" + "3. Conocer cuántos pasadores hay con más de 80% de efectividad\n" + "4. Regresar al menú principal";
    }

}
