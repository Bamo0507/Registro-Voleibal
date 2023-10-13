public abstract class Jugador{
    protected String tipoJugador;
    protected String nombre;
    protected String pais;
    protected int errores;
    protected int aces;
    protected int cantServicios;
    public float efectividad = 0;

    public Jugador(String tipoJugador, String nombre, String pais, int errores, int aces, int cantServicios) {
        this.tipoJugador = tipoJugador;
        this.nombre = nombre;
        this.pais = pais;
        this.errores = errores;
        this.aces = aces;
        this.cantServicios = cantServicios;
    }

    public float getEfectividad(){
        return this.efectividad;
    }
    

}