//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 13/10/2023
//Última modificación: 13/10/2023

public abstract class Jugador{
    //Atributos compartidos por todos los tipos de jugador
    protected String tipoJugador;
    protected String nombre;
    protected String pais;
    protected int errores;
    protected int aces;
    protected int cantServicios;
    protected float efectividad = 0;

    //Constructor común para todos los tipos de jugador
    public Jugador(String tipoJugador, String nombre, String pais, int errores, int aces, int cantServicios) {
        this.tipoJugador = tipoJugador;
        this.nombre = nombre;
        this.pais = pais;
        this.errores = errores;
        this.aces = aces;
        this.cantServicios = cantServicios;
    }
    //Método para acceder a la efectividad de los jugadores
    public float getEfectividad(){
        return this.efectividad;
    }
    
    //Devolución de mensaje con información del jugador
    public String toString() {
        return "-----" + nombre + " es un " + tipoJugador + " de " + pais + " y tiene una efectividad del " + efectividad + "%-----";
    }   

}