public class Deuda {
    int idDeuda;
    String tercero; // Proveedor o Acreedor
    String concepto;
    double monto;
    boolean pagada; // true si ya se canceló, false si está pendiente

    public Deuda(int idDeuda, String tercero, String concepto, double monto, boolean pagada) {
        this.idDeuda = idDeuda;
        this.tercero = tercero;
        this.concepto = concepto;
        this.monto = monto;
        this.pagada = pagada;
    }
}