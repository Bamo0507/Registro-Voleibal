//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 13/10/2023
//Última modificación: 13/10/2023

public class Libero extends Jugador{
    //Atributos únicos de un líbero
    private int recibosEfectivos;
    //Constructor de un líbero tomando en cuenta la superclase
    public Libero(String tipoJugador, String nombre, String pais, int errores, int aces, int cantServicios,
            int recibosEfectivos) {
        super(tipoJugador, nombre, pais, errores, aces, cantServicios);
        this.recibosEfectivos = recibosEfectivos;
        //Método específico para calcular su efectividad
        this.efectividad = ((recibosEfectivos - errores)*100/(recibosEfectivos + errores)) +((aces * 100)/cantServicios); 
    }    
    
}
