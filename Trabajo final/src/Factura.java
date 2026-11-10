public class Factura {
    int idFactura;
    String cliente;
    String descripcion;
    double subtotal;
    double porcentajeDescuento;
    double valorTotal;

    public Factura(int idFactura, String cliente, String descripcion, double subtotal, double porcentajeDescuento, double valorTotal) {
        this.idFactura = idFactura;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.subtotal = subtotal;
        this.porcentajeDescuento = porcentajeDescuento;
        this.valorTotal = valorTotal;
    }
}