//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 13/10/2023
//Última modificación: 13/10/2023

//Librerís a utilizar
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class ManejoCSV {
    //Método para leer la información del CSV y obtener todos los jugadores que se tienen en un ArrayList de tipo Jugador
    public ArrayList<Jugador> leerCSV(String archivo) {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        //Se intenta leer el archivo
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine(); //Evitamos que trate de leer el encabezado
            String linea;
            //Se leerá línea por línea hasta que esté vacío el documento
            while ((linea = br.readLine()) != null) {
                //Se divide la fila en un Array de Strings
                String[] datos = linea.split(",");
                if (datos.length >= 6) {
                    //Se recopila la información con su devido tipo en variables
                    String tipo = datos[0].trim();
                    String nombre = datos[1].trim();
                    String pais = datos[2].trim();
                    int errores = Integer.parseInt(datos[3].trim());
                    int aces = Integer.parseInt(datos[4].trim());
                    int cantidadServicios = Integer.parseInt(datos[5].trim());
                    //Dependiendo del tipo se genera el objeto correspondiente
                    if (tipo.equals("Libero")) {
                        int recibosEfectivos = Integer.parseInt(datos[6].trim());
                        jugadores.add(new Libero(tipo, nombre, pais, errores, aces, cantidadServicios, recibosEfectivos));
                    } else if (tipo.equals("Pasador")) {
                        int pases = Integer.parseInt(datos[7].trim());
                        int fintas = Integer.parseInt(datos[8].trim());
                        jugadores.add(new Pasador(tipo, nombre, pais, errores, aces, cantidadServicios, pases, fintas));
                    } else if (tipo.equals("Auxiliar") || tipo.equals("Opuesto")) {
                        int ataques = Integer.parseInt(datos[9].trim());
                        int bloqueosEfectivos = Integer.parseInt(datos[10].trim());
                        int bloqueosFallidos = Integer.parseInt(datos[11].trim());
                        if (tipo.equals("Auxiliar")){
                            jugadores.add(new Auxiliar(tipo, nombre, pais, errores, aces, cantidadServicios, ataques, bloqueosEfectivos, bloqueosFallidos));
                        } else {
                            jugadores.add(new Opuesto(tipo, nombre, pais, errores, aces, cantidadServicios, ataques, bloqueosEfectivos, bloqueosFallidos));
                        }
                    }
                }
            }
        } catch (IOException e) { //Mensaje a mostrar si no se logra cargar el CSV
            System.err.println("No se encontró el archivo que proporcionaste :(");
        }
        return jugadores;
    }

    //Método para agregar un nuevo jugador al CSV
    public void agregarJugador(String archivo){
        Scanner sc = new Scanner(System.in);
        int selecciontipo = 0;
        String tipo = "";
        //Ciclo para asegurarse que se tenga un tipo válido
        while(!(selecciontipo >= 1 && selecciontipo <= 4)){
            System.out.println(tipoJugador());
            selecciontipo = obtenerEnteroValido(sc);
            switch(selecciontipo){
                case 1:
                    tipo = "Libero";
                    break;
                case 2:
                    tipo = "Pasador";
                    break;
                case 3:
                    tipo = "Auxiliar";
                    break;
                case 4:
                    tipo = "Opuesto";
                    break;
                default: 
                    System.out.println("por favor, seleccione una opción válida, si no no podrá continuar...");
                    break;
            }
        }
        //Solicitamos todos los atributos comunes necesarios
        System.out.println("Ingrese el nombre del jugador: ");
        String nombre = sc.nextLine();
        System.out.println();
        System.out.println("Ingresar el país del jugador: ");
        String pais = sc.nextLine();
        System.out.println();
        System.out.println("¿Cuántos errores ha cometido el jugador?");
        int errores = obtenerEnteroValido(sc);
        System.out.println();
        System.out.println("Ingrese la cantidad de ases del jugador: ");
        int aces = obtenerEnteroValido(sc);
        System.out.println();
        System.out.println("¿Cuántos servicios ha realizado?");
        int cantServicios = obtenerEnteroValido(sc);
        System.out.println();

        //Vamos construyendo lo que se colocará en la fila poco a poco, tomando en cuenta las delimitaciones por coma
        StringBuilder lineaCSV = new StringBuilder();
        lineaCSV.append(tipo).append(",").append(nombre).append(",").append(pais).append(",").append(errores).append(",").append(aces).append(",").append(cantServicios).append(",");
        //Información a solicitar si e sun líbero
        if(tipo.equals("Libero")){
            System.out.println("Ingresar la cantidad de recibos efectivos: ");
            int recibosEfectivos = obtenerEnteroValido(sc);
            System.out.println();
            lineaCSV.append(recibosEfectivos);
        }  //Información a solicitar si se trata de un pasador
        else if(tipo.equals("Pasador")){
            System.out.println("¿Cuántos pases ha realizado");
            int pases = obtenerEnteroValido(sc);
            System.out.println();
            System.out.println("¿Cuántas fintas realizó?");
            int fintas = obtenerEnteroValido(sc);
            System.out.println();
            lineaCSV.append(" ").append(",").append(pases).append(",").append(fintas);
        } //Información a solicitar si es un auxiliar u opuesto
        else {
            System.out.println("¿Cuántos ataques ha realizado?");
            int ataques = obtenerEnteroValido(sc);
            System.out.println();
            System.out.println("¿Cuántos bloqueos efectivos tuvo?");
            int bloqueosEfectivos = obtenerEnteroValido(sc);
            System.out.println();
            System.out.println("¿Cuántos bloqueos fallidos ha tenido?");
            int bloqueosFallidos = obtenerEnteroValido(sc);
            System.out.println();
            lineaCSV.append(" ").append(",").append(" ").append(",").append(" ").append(",").append(ataques).append(",").append(bloqueosEfectivos).append(",").append(bloqueosFallidos);
        }
        //Tratamos de escribir sobre el archivo
        try(BufferedWriter w = new BufferedWriter(new FileWriter(archivo, true))){
            w.write(lineaCSV.toString());
            w.newLine();
        } catch(IOException e) { //Mensaje a mostrar si no logramos escribir sobre el CSV
            System.out.println("No se ha logrado agregar el jugador.");
            e.printStackTrace();
        }
    }
    //Método para el menú de selección del tipo de jugador
    public String tipoJugador(){
        return "1. Libero\n" + "2. Pasador\n" + "3. Auxiliar\n" + "4. Opuesto";
    }

    //Método para asegurarse que se ingrese un entero en los campos necesarios
    public static int obtenerEnteroValido(Scanner scanner) {
        int numero = 0;
        boolean entradaValida = false;
        System.out.println("------------------------");
        do {
            try {
                System.out.print("Por favor, ingresa un número entero: ");
                String entrada = scanner.nextLine();
                numero = Integer.parseInt(entrada);
                entradaValida = true;
                System.out.println("");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes ingresar un número entero.");
            }
        } while (!entradaValida);

        return numero;
    }

}
