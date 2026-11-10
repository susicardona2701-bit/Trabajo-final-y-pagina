public class Local {
    int idLocal;
    String nombre;
    String zona;
    String ubicacion;
    String numeroContacto;
    String redesSociales;
    String resena;
    double puntuacion;

    public Local(int idLocal, String nombre, String zona, String ubicacion, String numeroContacto, String redesSociales, String resena, double puntuacion) {
        this.idLocal = idLocal;
        this.nombre = nombre;
        this.zona = zona;
        this.ubicacion = ubicacion;
        this.numeroContacto = numeroContacto;
        this.redesSociales = redesSociales;
        this.resena = resena;
        this.puntuacion = puntuacion;
    }
}