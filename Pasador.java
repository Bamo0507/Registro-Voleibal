public class Pasador extends Jugador {
    private int cantPases;
    private int fintas;
    private float efectividad;

    public Pasador(String tipoJugador, String nombre, String pais, int errores, int aces, int cantServicios,
            int cantPases, int fintas) {
        super(tipoJugador, nombre, pais, errores, aces, cantServicios);
        this.cantPases = cantPases;
        this.fintas = fintas;
        this.efectividad = ((cantPases + fintas-errores)*100/(cantPases+fintas+errores)) + ((aces*100)/cantServicios);


    }

    @Override
    public String toString() {
        return "-----" + nombre + " es un " + tipoJugador + " de " + pais + " y tiene una efectividad del " + efectividad + "%-----";
    }
    @Override
    public float getEfectividad() {
        return efectividad;
    }
}
