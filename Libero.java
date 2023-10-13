public class Libero extends Jugador{
    private int recibosEfectivos;
    private float efectividad;

    public Libero(String tipoJugador, String nombre, String pais, int errores, int aces, int cantServicios,
            int recibosEfectivos) {
        super(tipoJugador, nombre, pais, errores, aces, cantServicios);
        this.recibosEfectivos = recibosEfectivos;
        
        this.efectividad = ((recibosEfectivos - errores)*100/(recibosEfectivos + errores)) +((aces * 100)/cantServicios); 
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
