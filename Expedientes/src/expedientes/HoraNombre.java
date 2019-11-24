package expedientes;

public class HoraNombre {
    Long hora;
    String nombre;

    public HoraNombre(Long hora, String nombre) {
        this.hora = hora;
        this.nombre = nombre;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
