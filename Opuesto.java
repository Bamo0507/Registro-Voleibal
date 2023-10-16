//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 13/10/2023
//Última modificación: 13/10/2023

public class Opuesto extends Jugador {
    //Atributos únicos de los Opuestos
    private int cantAtaques;
    private int bloqueosEfectivos;
    private int bloqueosFallidos;
    //Constructor del Opuesto tomando en cuenta el constructor de la superclase
    public Opuesto(String tipoJugador, String nombre, String pais, int errores, int aces, int cantServicios,
            int cantAtaques, int bloqueosEfectivos, int bloqueosFallidos) {
        super(tipoJugador, nombre, pais, errores, aces, cantServicios);
        this.cantAtaques = cantAtaques;
        this.bloqueosFallidos = bloqueosFallidos;
        this.bloqueosEfectivos = bloqueosEfectivos;
        this.efectividad = ((cantAtaques+bloqueosEfectivos-bloqueosFallidos-errores)*100/(cantAtaques+bloqueosEfectivos+bloqueosFallidos+errores)) + (aces*100)/cantServicios;
    }
}
