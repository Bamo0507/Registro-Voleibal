//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 13/10/2023
//Última modificación: 13/10/2023

public class Pasador extends Jugador {
    //Atributos únicos de los Pasadores
    private int cantPases;
    private int fintas;
    //Constructor para los pasadores tomando en cuent ala superclase
    public Pasador(String tipoJugador, String nombre, String pais, int errores, int aces, int cantServicios,
            int cantPases, int fintas) {
        super(tipoJugador, nombre, pais, errores, aces, cantServicios);
        this.cantPases = cantPases;
        this.fintas = fintas;
        this.efectividad = ((cantPases + fintas-errores)*100/(cantPases+fintas+errores)) + ((aces*100)/cantServicios);
    }
}
