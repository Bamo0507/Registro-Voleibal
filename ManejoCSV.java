import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class ManejoCSV {
    public ArrayList<Jugador> leerCSV(String archivo) {
        ArrayList<Jugador> jugadores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine(); //Evitamos que trate de leer el encabezado
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6) {
                    String tipo = datos[0].trim();
                    String nombre = datos[1].trim();
                    String pais = datos[2].trim();
                    int errores = Integer.parseInt(datos[3].trim());
                    int aces = Integer.parseInt(datos[4].trim());
                    int cantidadServicios = Integer.parseInt(datos[5].trim());

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
        } catch (IOException e) {
            System.err.println("No se encontró el archivo que proporcionaste :(");
        }
        return jugadores;
    }

    public void agregarJugador(String archivo){
        Scanner sc = new Scanner(System.in);
        int selecciontipo = 0;
        String tipo = "";
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

        StringBuilder lineaCSV = new StringBuilder();
        lineaCSV.append(tipo).append(",").append(nombre).append(",").append(pais).append(",").append(errores).append(",").append(aces).append(",").append(cantServicios).append(",");

        if(tipo.equals("Libero")){
            System.out.println("Ingresar la cantidad de recibos efectivos: ");
            int recibosEfectivos = obtenerEnteroValido(sc);
            System.out.println();
            lineaCSV.append(recibosEfectivos);
        } else if(tipo.equals("Pasador")){
            System.out.println("¿Cuántos pases ha realizado");
            int pases = obtenerEnteroValido(sc);
            System.out.println();
            System.out.println("¿Cuántas fintas realizó?");
            int fintas = obtenerEnteroValido(sc);
            System.out.println();
            lineaCSV.append(" ").append(",").append(pases).append(",").append(fintas);
        } else {
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
        try(BufferedWriter w = new BufferedWriter(new FileWriter(archivo, true))){
            w.write(lineaCSV.toString());
            w.newLine();
        } catch(IOException e) {
            System.out.println("No se ha logrado agregar el jugador.");
            e.printStackTrace();
        }
    }

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
