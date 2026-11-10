public class Empleado {
    String nombre;
    String cargo;
    double sueldoBase;
    double bonificaciones;
    double deducciones;

    public Empleado(String nombre, String cargo, double sueldoBase, double bonificaciones, double deducciones) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.sueldoBase = sueldoBase;
        this.bonificaciones = bonificaciones;
        this.deducciones = deducciones;
    }

    public double calcularSueldoNeto() {
        return sueldoBase + bonificaciones - deducciones;
    }
}