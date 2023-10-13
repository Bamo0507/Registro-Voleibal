public class Opuesto extends Jugador {
    private int cantAtaques;
    private int bloqueosEfectivos;
    private int bloqueosFallidos;
    private float efectividad;
    public Opuesto(String tipoJugador, String nombre, String pais, int errores, int aces, int cantServicios,
            int cantAtaques, int bloqueosEfectivos, int bloqueosFallidos) {
        super(tipoJugador, nombre, pais, errores, aces, cantServicios);
        this.cantAtaques = cantAtaques;
        this.bloqueosFallidos = bloqueosFallidos;
        this.bloqueosEfectivos = bloqueosEfectivos;
        this.efectividad = ((cantAtaques+bloqueosEfectivos-bloqueosFallidos-errores)*100/(cantAtaques+bloqueosEfectivos+bloqueosFallidos+errores)) + (aces*100)/cantServicios;
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
