import java.util.Scanner;

public class MainJava {

    // 4 ARREGLOS UNIDIMENSIONALES PRINCIPALES DEL PROYECTO INTEGRADOR SLUSH
    static Local[] directorioLocales = new Local[60];
    static int contadorLocales = 0;

    static Producto[] catalogoProductos = new Producto[40];
    static int contadorProductos = 0;

    static Empleado[] nominaEmpleados = new Empleado[20];
    static int contadorEmpleados = 0;

    static Factura[] registroFacturas = new Factura[60];
    static int contadorFacturas = 0;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // Precarga silenciosa de los 39 locales de SLUSH y datos contables iniciales
        precargarDatosIniciales();

        System.out.println("╔══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         SLUSH MEDELLÍN - MANAGER ADMINISTRADOR & DIRECTORIO PRO          ║");
        System.out.println("║     Plataforma de Gestión, Georreferenciación y Contabilidad (v2026)     ║");
        System.out.println("║        Cobertura: Medellín y Valle de Aburrá (39 Sedes Activas)          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");

        // SEGURIDAD: Validación de acceso para Administrador (3 intentos)
        boolean accesoConcedido = false;
        int intentos = 3;

        while (intentos > 0 && !accesoConcedido) {
            System.out.print("🔒 Ingrese Clave de Administrador (Admin2026): ");
            String pass = teclado.nextLine().trim();

            if (pass.equals("Admin2026")) {
                accesoConcedido = true;
                System.out.println("\n✅ [ACCESO AUTORIZADO] Bienvenido al Panel Gerencial y de Consultas SLUSH.");
            } else {
                intentos--;
                System.out.println("❌ Contraseña incorrecta. Intentos restantes: " + intentos);
            }
        }

        if (!accesoConcedido) {
            System.out.println("⛔ Acceso denegado. Sistema SLUSH bloqueado por seguridad.");
            teclado.close();
            return;
        }

        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                MENÚ PRINCIPAL DE GESTIÓN Y CONSULTAS SLUSH               ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║  [REGISTROS Y ACTUALIZACIONES]                                           ║");
            System.out.println("║   1. Registrar Granizado / Topping / Membresía al Inventario             ║");
            System.out.println("║   2. Registrar Empleado y Sueldo a la Nómina                             ║");
            System.out.println("║   3. Generar Factura de Venta con Descuento                              ║");
            System.out.println("║   4. Registrar Nuevo Local Aliado en el Directorio SLUSH                 ║");
            System.out.println("║   5. Calificar Local, Registrar Reseña y Ver Promedio [Función]          ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║  [REPORTES, FUNCIONES FINANCIERAS Y CONSULTAS AVANZADAS]                 ║");
            System.out.println("║   6. Mostrar Inventario Completo de Granizados y Stock                   ║");
            System.out.println("║   7. Mostrar Nómina de Empleados, Bonos y Sueldos Netos                  ║");
            System.out.println("║   8. Mostrar Facturas y Calcular Total de Ventas [Función]               ║");
            System.out.println("║   9. Calcular Gasto Total Mensual de Nómina [Función]                    ║");
            System.out.println("║  10. Calcular Utilidad Neta del Negocio SLUSH [Función Avanzada]         ║");
            System.out.println("║  11. Mostrar Directorio Completo de Locales Registrados (39 Sedes)       ║");
            System.out.println("║  12. Consulta Avanzada: Filtrar Locales por Zona, App, Teléfono o IG     ║");
            System.out.println("║  13. Salir del Sistema                                                   ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");

            opcion = leerEntero(teclado, "👉 Seleccione una opción (1-13): ");

            switch (opcion) {
                case 1:
                    System.out.println("\n--- [OPCIÓN 1] REGISTRAR NUEVO PRODUCTO / GRANIZADO ---");
                    System.out.print("Nombre del granizado/insumo/servicio: ");
                    String nomP = teclado.nextLine();
                    System.out.print("Categoría (Tradicional / Con Licor / Neon / Topping / Membresía): ");
                    String catP = teclado.nextLine();
                    double preP = leerDecimal(teclado, "Precio unitario en COP ($): ");
                    int stoP = leerEntero(teclado, "Cantidad en stock inicial: ");
                    registrarProducto(nomP, catP, preP, stoP, true);
                    pausarConsola(teclado);
                    break;

                case 2:
                    System.out.println("\n--- [OPCIÓN 2] REGISTRAR EMPLEADO EN NÓMINA SLUSH ---");
                    System.out.print("Nombre completo del empleado: ");
                    String nomE = teclado.nextLine();
                    System.out.print("Cargo (Mixólogo / Coordinador / Logística / Marketing): ");
                    String carE = teclado.nextLine();
                    double sueE = leerDecimal(teclado, "Sueldo base en COP ($): ");
                    double bonE = leerDecimal(teclado, "Bonificaciones adicionales ($): ");
                    double dedE = leerDecimal(teclado, "Deducciones de ley ($): ");
                    registrarEmpleado(nomE, carE, sueE, bonE, dedE, true);
                    pausarConsola(teclado);
                    break;

                case 3:
                    System.out.println("\n--- [OPCIÓN 3] GENERAR FACTURA DE VENTA CON DESCUENTO ---");
                    System.out.print("Nombre del cliente o local aliado: ");
                    String cliF = teclado.nextLine();
                    System.out.print("Descripción de productos o plan adquirido: ");
                    String detF = teclado.nextLine();
                    double subF = leerDecimal(teclado, "Subtotal de la venta en COP ($): ");
                    double descF = leerDecimal(teclado, "Porcentaje de descuento (ej. 10 para 10%): ");
                    generarFacturaConDescuento(cliF, detF, subF, descF, true);
                    pausarConsola(teclado);
                    break;

                case 4:
                    System.out.println("\n--- [OPCIÓN 4] REGISTRAR NUEVO LOCAL ALIADO EN SLUSH ---");
                    System.out.print("Nombre del local de granizados: ");
                    String nomL = teclado.nextLine();
                    System.out.print("Barrio o Municipio (ej. Laureles, Belén, Poblado, Envigado): ");
                    String zonL = teclado.nextLine();
                    System.out.print("Dirección exacta: ");
                    String ubiL = teclado.nextLine();
                    System.out.print("Contacto o Plataforma (Teléfono / Rappi / DidiFood): ");
                    String conL = teclado.nextLine();
                    System.out.print("Usuario de Instagram (sin @): ");
                    String redL = teclado.nextLine();
                    System.out.print("Reseña destacada inicial: ");
                    String resL = teclado.nextLine();
                    double punL = leerDecimal(teclado, "Puntuación inicial (1.0 a 5.0): ");
                    registrarLocal(nomL, zonL, ubiL, conL, redL, resL, punL, true);
                    pausarConsola(teclado);
                    break;

                case 5:
                    System.out.println("\n--- [OPCIÓN 5] CALIFICAR LOCAL Y ACTUALIZAR RESEÑA ---");
                    int idCal = leerEntero(teclado, "Ingrese el ID del local a calificar (101 a " + (100 + contadorLocales) + "): ");
                    System.out.print("Escriba la nueva reseña del cliente: ");
                    String resCal = teclado.nextLine();
                    double punCal = leerDecimal(teclado, "Ingrese la nueva puntuación (1.0 a 5.0): ");
                    calificarLocal(idCal, resCal, punCal);
                    double prom = calcularPromedioPuntuacionLocales();
                    System.out.printf(">>> ⭐ PROMEDIO GENERAL ACTUALIZADO DE LA PLATAFORMA SLUSH: %.2f / 5.00 estrellas%n", prom);
                    pausarConsola(teclado);
                    break;

                case 6:
                    mostrarProductos();
                    pausarConsola(teclado);
                    break;

                case 7:
                    mostrarEmpleados();
                    pausarConsola(teclado);
                    break;

                case 8:
                    mostrarFacturas();
                    double totalV = calcularTotalVentas();
                    System.out.printf("%n>>> 💰 TOTAL ACUMULADO DE INGRESOS POR VENTAS: $%,.2f COP%n", totalV);
                    pausarConsola(teclado);
                    break;

                case 9:
                    mostrarEmpleados();
                    double totalN = calcularNominaTotal();
                    System.out.printf("%n>>> 💼 GASTO TOTAL MENSUAL EN NÓMINA SLUSH: $%,.2f COP%n", totalN);
                    pausarConsola(teclado);
                    break;

                case 10:
                    mostrarResumenFinancieroUtilidad();
                    pausarConsola(teclado);
                    break;

                case 11:
                    mostrarLocales();
                    pausarConsola(teclado);
                    break;

                case 12:
                    consultaAvanzadaLocales(teclado);
                    pausarConsola(teclado);
                    break;

                case 13:
                    System.out.println("\n👋 Cerrando SLUSH Manager Pro. ¡Éxitos en la presentación del proyecto integrador!");
                    break;

                default:
                    System.out.println("⚠️ Opción fuera de rango. Por favor ingrese un número entre 1 y 13.");
            }

        } while (opcion != 13);

        teclado.close();
    }

    // ==========================================================
    // UTILIDADES DE LECTURA SEGURA Y PAUSA (Evita bloqueos de Scanner)
    // ==========================================================

    public static int leerEntero(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = teclado.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Ingrese un número entero válido.");
            }
        }
    }

    public static double leerDecimal(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = teclado.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Ingrese un valor numérico válido (ej. 25000 o 4.8).");
            }
        }
    }

    public static void pausarConsola(Scanner teclado) {
        System.out.print("\n[Presione ENTER para volver al menú principal...]");
        teclado.nextLine();
    }

    public static String recortarTexto(String texto, int maxLargo) {
        if (texto == null) return "";
        if (texto.length() <= maxLargo) return texto;
        return texto.substring(0, maxLargo - 3) + "...";
    }

    // ==========================================================
    // MÉTODOS DE REGISTRO Y ACTUALIZACIÓN
    // ==========================================================

    public static void registrarLocal(String nombre, String zona, String ubicacion, String contacto, String redes, String resena, double puntuacion, boolean mostrarMensaje) {
        if (contadorLocales < directorioLocales.length) {
            int id = contadorLocales + 101;
            directorioLocales[contadorLocales] = new Local(id, nombre, zona, ubicacion, contacto, redes, resena, puntuacion);
            contadorLocales++;
            if (mostrarMensaje) {
                System.out.println("✅ ¡Local #" + id + " [" + nombre + "] registrado exitosamente en el Directorio SLUSH!");
            }
        } else {
            System.out.println("❌ Error: Capacidad máxima del directorio de locales alcanzada.");
        }
    }

    public static void calificarLocal(int idLocal, String nuevaResena, double nuevaPuntuacion) {
        if (nuevaPuntuacion < 1.0 || nuevaPuntuacion > 5.0) {
            System.out.println("⚠️ La puntuación debe estar en la escala de 1.0 a 5.0. Se ajustó automáticamente al límite válido.");
            nuevaPuntuacion = Math.max(1.0, Math.min(5.0, nuevaPuntuacion));
        }
        boolean encontrado = false;
        for (int i = 0; i < contadorLocales; i++) {
            if (directorioLocales[i].idLocal == idLocal) {
                encontrado = true;
                directorioLocales[i].resena = nuevaResena;
                directorioLocales[i].puntuacion = nuevaPuntuacion;
                System.out.println("✅ ¡Local #" + idLocal + " [" + directorioLocales[i].nombre + "] actualizado con " + nuevaPuntuacion + "★!");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("❌ No se encontró ningún local con el ID #" + idLocal + ".");
        }
    }

    public static void registrarProducto(String nombre, String categoria, double precio, int stock, boolean mostrarMensaje) {
        if (contadorProductos < catalogoProductos.length) {
            catalogoProductos[contadorProductos] = new Producto(nombre, categoria, precio, stock);
            contadorProductos++;
            if (mostrarMensaje) {
                System.out.printf("✅ ¡Producto [%s] registrado con éxito! Precio: $%,.0f COP | Stock: %d uds.%n", nombre, precio, stock);
            }
        } else {
            System.out.println("❌ Error: Catálogo de productos lleno.");
        }
    }

    public static void registrarEmpleado(String nombre, String cargo, double sueldoBase, double bonificaciones, double deducciones, boolean mostrarMensaje) {
        if (contadorEmpleados < nominaEmpleados.length) {
            Empleado nuevo = new Empleado(nombre, cargo, sueldoBase, bonificaciones, deducciones);
            nominaEmpleados[contadorEmpleados] = nuevo;
            contadorEmpleados++;
            if (mostrarMensaje) {
                System.out.printf("✅ ¡Empleado [%s] registrado en nómina! Sueldo Neto a pagar: $%,.0f COP%n", nombre, nuevo.calcularSueldoNeto());
            }
        } else {
            System.out.println("❌ Error: Cupo de nómina lleno.");
        }
    }

    public static void generarFacturaConDescuento(String cliente, String descripcion, double subtotal, double porcentajeDescuento, boolean mostrarMensaje) {
        if (contadorFacturas < registroFacturas.length) {
            double descuentoMonto = subtotal * (porcentajeDescuento / 100.0);
            double valorTotal = subtotal - descuentoMonto;
            int id = contadorFacturas + 201;

            registroFacturas[contadorFacturas] = new Factura(id, cliente, descripcion, subtotal, porcentajeDescuento, valorTotal);
            contadorFacturas++;
            if (mostrarMensaje) {
                System.out.printf("✅ ¡Factura #%d generada! Subtotal: $%,.0f | Desc (%.1f%%): -$%,.0f | Total Pagado: $%,.0f COP%n",
                        id, subtotal, porcentajeDescuento, descuentoMonto, valorTotal);
            }
        } else {
            System.out.println("❌ Error: Memoria de facturas llena.");
        }
    }

    // ==========================================================
    // FUNCIONES CON RETORNO DE RESULTADOS (Lógica Financiera y KPI)
    // ==========================================================

    public static double calcularTotalVentas() {
        double acumuladoVentas = 0;
        for (int i = 0; i < contadorFacturas; i++) {
            acumuladoVentas += registroFacturas[i].valorTotal;
        }
        return acumuladoVentas;
    }

    public static double calcularNominaTotal() {
        double acumuladoNomina = 0;
        for (int i = 0; i < contadorEmpleados; i++) {
            acumuladoNomina += nominaEmpleados[i].calcularSueldoNeto();
        }
        return acumuladoNomina;
    }

    public static double calcularUtilidadNeta() {
        double ventas = calcularTotalVentas();
        double nomina = calcularNominaTotal();
        return ventas - nomina;
    }

    public static double calcularPromedioPuntuacionLocales() {
        double suma = 0;
        int calificados = 0;
        for (int i = 0; i < contadorLocales; i++) {
            if (directorioLocales[i].puntuacion > 0) {
                suma += directorioLocales[i].puntuacion;
                calificados++;
            }
        }
        return (calificados > 0) ? (suma / calificados) : 0.0;
    }

    // ==========================================================
    // MÉTODOS DE VISUALIZACIÓN CON TABLAS ALINEADAS Y CONSULTAS
    // ==========================================================

    public static void mostrarProductos() {
        System.out.println("\n╔════╦══════════════════════════════════════════════════════╦═══════════════╦════════════════╦══════════╗");
        System.out.println("║ #  ║ NOMBRE DEL GRANIZADO / INSUMO / SERVICIO             ║ CATEGORÍA     ║ PRECIO (COP)   ║ STOCK    ║");
        System.out.println("╠════╬══════════════════════════════════════════════════════╬═══════════════╬════════════════╬══════════╣");
        for (int i = 0; i < contadorProductos; i++) {
            System.out.printf("║ %-2d ║ %-52s ║ %-13s ║ $%,13.0f ║ %-4d uds ║%n",
                    (i + 1),
                    recortarTexto(catalogoProductos[i].nombre, 52),
                    recortarTexto(catalogoProductos[i].categoria, 13),
                    catalogoProductos[i].precio,
                    catalogoProductos[i].stock);
        }
        System.out.println("╚════╩══════════════════════════════════════════════════════╩═══════════════╩════════════════╩══════════╝");
        System.out.println("Total de referencias en inventario: " + contadorProductos);
    }

    public static void mostrarEmpleados() {
        System.out.println("\n╔════╦══════════════════════════╦══════════════════════════════════════════╦══════════════╦═════════════╦═════════════╦══════════════╗");
        System.out.println("║ #  ║ EMPLEADO                 ║ CARGO EN SLUSH                           ║ SUELDO BASE  ║ BONOS (+)   ║ DEDUC. (-)  ║ SUELDO NETO  ║");
        System.out.println("╠════╬══════════════════════════╬══════════════════════════════════════════╬══════════════╬═════════════╬═════════════╬══════════════╣");
        for (int i = 0; i < contadorEmpleados; i++) {
            double neto = nominaEmpleados[i].calcularSueldoNeto();
            System.out.printf("║ %-2d ║ %-24s ║ %-40s ║ $%,11.0f ║ $%,10.0f ║ $%,10.0f ║ $%,11.0f ║%n",
                    (i + 1),
                    recortarTexto(nominaEmpleados[i].nombre, 24),
                    recortarTexto(nominaEmpleados[i].cargo, 40),
                    nominaEmpleados[i].sueldoBase,
                    nominaEmpleados[i].bonificaciones,
                    nominaEmpleados[i].deducciones,
                    neto);
        }
        System.out.println("╚════╩══════════════════════════╩══════════════════════════════════════════╩══════════════╩═════════════╩═════════════╩══════════════╝");
    }

    public static void mostrarFacturas() {
        System.out.println("\n╔════════╦══════════════════════════════════╦══════════════════════════════════════════════╦══════════════╦════════╦══════════════╗");
        System.out.println("║ ID FAC ║ CLIENTE / LOCAL ALIADO           ║ DESCRIPCIÓN DE VENTA                         ║ SUBTOTAL     ║ DESC % ║ TOTAL PAGADO ║");
        System.out.println("╠════════╬══════════════════════════════════╬══════════════════════════════════════════════╬══════════════╬════════╬══════════════╣");
        for (int i = 0; i < contadorFacturas; i++) {
            System.out.printf("║ #%-5d ║ %-32s ║ %-44s ║ $%,11.0f ║ %5.1f%% ║ $%,11.0f ║%n",
                    registroFacturas[i].idFactura,
                    recortarTexto(registroFacturas[i].cliente, 32),
                    recortarTexto(registroFacturas[i].descripcion, 44),
                    registroFacturas[i].subtotal,
                    registroFacturas[i].porcentajeDescuento,
                    registroFacturas[i].valorTotal);
        }
        System.out.println("╚════════╩══════════════════════════════════╩══════════════════════════════════════════════╩══════════════╩════════╩══════════════╝");
    }

    public static void mostrarResumenFinancieroUtilidad() {
        double ventas = calcularTotalVentas();
        double nomina = calcularNominaTotal();
        double utilidad = calcularUtilidadNeta();
        double margen = (ventas > 0) ? ((utilidad / ventas) * 100.0) : 0.0;

        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║              BALANCE FINANCIERO Y UTILIDAD NETA - SLUSH PRO              ║");
        System.out.println("╠══════════════════════════════════════════════════╦═══════════════════════╣");
        System.out.printf("║ (+) Total Ingresos por Ventas y Membresías       ║ $%,16.2f COP ║%n", ventas);
        System.out.printf("║ (-) Gasto Total Mensual de Nómina Operativa      ║ $%,16.2f COP ║%n", nomina);
        System.out.println("╠══════════════════════════════════════════════════╬═══════════════════════╣");
        System.out.printf("║ (=) UTILIDAD NETA OPERATIVA DEL PERIODO          ║ $%,16.2f COP ║%n", utilidad);
        System.out.printf("║ (%%) Margen de Rentabilidad Neta                  ║ %17.2f %%   ║%n", margen);
        System.out.println("╚══════════════════════════════════════════════════╩═══════════════════════╝");
    }

    public static void mostrarLocales() {
        System.out.println("\n╔═════╦══════════════════════════════════╦═══════════════╦═══════════════════════════╦══════════════════════╦══════════════════════════╦═══════╦════════════════════════════════════════════╗");
        System.out.println("║ ID  ║ LOCAL DE GRANIZADOS              ║ ZONA / BARRIO ║ DIRECCIÓN EXACTA          ║ CONTACTO / APP       ║ INSTAGRAM                ║ NOTA  ║ RESEÑA DESTACADA DE CLIENTES               ║");
        System.out.println("╠═════╬══════════════════════════════════╬═══════════════╬═══════════════════════════╬══════════════════════╬══════════════════════════╬═══════╬════════════════════════════════════════════╣");
        for (int i = 0; i < contadorLocales; i++) {
            Local loc = directorioLocales[i];
            System.out.printf("║ %-3d ║ %-32s ║ %-13s ║ %-25s ║ %-20s ║ @%-23s ║ %4.1f★ ║ %-42s ║%n",
                    loc.idLocal,
                    recortarTexto(loc.nombre, 32),
                    recortarTexto(loc.zona, 13),
                    recortarTexto(loc.ubicacion, 25),
                    recortarTexto(loc.numeroContacto, 20),
                    recortarTexto(loc.redesSociales, 23),
                    loc.puntuacion,
                    recortarTexto(loc.resena, 42));
        }
        System.out.println("╚═════╩══════════════════════════════════╩═══════════════╩═══════════════════════════╩══════════════════════╩══════════════════════════╩═══════╩════════════════════════════════════════════╝");
        System.out.printf(">>> Total de Sedes Registradas: %d locales | Calificación Promedio Global: %.2f / 5.00 ★%n",
                contadorLocales, calcularPromedioPuntuacionLocales());
    }

    public static void consultaAvanzadaLocales(Scanner teclado) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║             MOTOR DE CONSULTA AVANZADA DE LOCALES (SLUSH)                ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Filtrar por Barrio o Municipio (Laureles, Belén, La América, etc.)   ║");
        System.out.println("║  2. Filtrar Locales con Convenio en Apps de Domicilio (Rappi / DidiFood) ║");
        System.out.println("║  3. Filtrar Locales con Calificación Top (Igual o mayor a 4.8 ★)         ║");
        System.out.println("║  4. Buscar Local por Nombre o Usuario de Instagram                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");

        int subOp = leerEntero(teclado, "🔎 Seleccione el tipo de filtro avanzado (1-4): ");
        int encontrados = 0;

        if (subOp == 1) {
            System.out.print("Ingrese el Barrio o Municipio a consultar (ej. Laureles, Belén, Aranjuez, Itagüí): ");
            String criterioZona = teclado.nextLine().trim().toLowerCase();
            System.out.println("\n--- RESULTADOS DE BÚSQUEDA EN LA ZONA: [" + criterioZona.toUpperCase() + "] ---");
            for (int i = 0; i < contadorLocales; i++) {
                Local loc = directorioLocales[i];
                if (loc.zona.toLowerCase().contains(criterioZona) || loc.ubicacion.toLowerCase().contains(criterioZona)) {
                    imprimirFilaLocalFiltrado(loc);
                    encontrados++;
                }
            }
        } else if (subOp == 2) {
            System.out.println("\n--- LOCALES CON CONVENIO EN APPS DE DOMICILIO (RAPPI / DIDIFOOD) ---");
            for (int i = 0; i < contadorLocales; i++) {
                Local loc = directorioLocales[i];
                String c = loc.numeroContacto.toLowerCase();
                if (c.contains("rappi") || c.contains("didi")) {
                    imprimirFilaLocalFiltrado(loc);
                    encontrados++;
                }
            }
        } else if (subOp == 3) {
            System.out.println("\n--- LOCALES TOP RATED (PUNTUACIÓN >= 4.8 ★) ---");
            for (int i = 0; i < contadorLocales; i++) {
                Local loc = directorioLocales[i];
                if (loc.puntuacion >= 4.8) {
                    imprimirFilaLocalFiltrado(loc);
                    encontrados++;
                }
            }
        } else if (subOp == 4) {
            System.out.print("Ingrese palabra clave del nombre o Instagram (ej. Guillotina, Astro, Coctelista): ");
            String clave = teclado.nextLine().trim().toLowerCase();
            System.out.println("\n--- RESULTADOS PARA LA PALABRA CLAVE: [" + clave + "] ---");
            for (int i = 0; i < contadorLocales; i++) {
                Local loc = directorioLocales[i];
                if (loc.nombre.toLowerCase().contains(clave) || loc.redesSociales.toLowerCase().contains(clave)) {
                    imprimirFilaLocalFiltrado(loc);
                    encontrados++;
                }
            }
        } else {
            System.out.println("⚠️ Opción de filtro no válida.");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println(">>> ✅ Total de coincidencias encontradas: " + encontrados + " locales.");
    }

    public static void imprimirFilaLocalFiltrado(Local loc) {
        System.out.printf("• [ID #%d] %-32s | Zona: %-13s | Dir: %-25s | Contacto: %-20s | IG: @%-22s | %.1f★%n",
                loc.idLocal,
                recortarTexto(loc.nombre, 32),
                loc.zona,
                recortarTexto(loc.ubicacion, 25),
                loc.numeroContacto,
                loc.redesSociales,
                loc.puntuacion);
    }

    // ==========================================================
    // PRECARGA COMPLETA Y SIN HUECOS DE LOS 39 LOCALES Y DATOS SLUSH
    // ==========================================================
    public static void precargarDatosIniciales() {
        // 1. Los 39 locales de granizados_ubi.xlsx con todos sus campos completos (0 huecos vacíos)
        registrarLocal("Guillotina Granizados/licorera", "Laureles", "Calle 35 #80a-71", "3196287958", "guillotinagranizados", "Excelente licor y granizados bien cargados", 4.9, false);
        registrarLocal("El coctelista urban", "Laureles", "Circular 2 #66B-4", "3042140341", "elcoctelista", "Coctelería urbana creativa y atención rápida", 4.8, false);
        registrarLocal("Siglo 22XL", "Laureles", "Transversal 39D #39B-96", "3137152426", "siglo22bar", "Gran ambiente nocturno y tamaños XL reales", 4.7, false);
        registrarLocal("Ilicito Granizados", "Laureles", "Calle 35 #80-87 Local 101", "Rappi / 3004128890", "ilicito_granizados", "Toppings de gomitas y perlas explosivas top", 4.8, false);
        registrarLocal("Ice Paradise", "Laureles", "Circular 4 #72-58", "3013784600", "ice.paradise_", "Sabores tropicales muy refrescantes", 4.6, false);
        registrarLocal("Granizados hot ice", "Laureles", "Carrera 73 Circular 4 #18", "3003221395", "granizadoshotice", "Buena mezcla de chamoy y mango biche", 4.5, false);
        registrarLocal("Granizados Monster Ice", "Laureles", "Carrera 69 #51-32", "3054070053", "granizados.monsterice", "Vasos gigantes con excelente presentación", 4.7, false);
        registrarLocal("Granizados Medellin (Belén)", "Belén", "Calle 32A #84-24", "3104942960", "granizadosmedellin", "Tradicionales y cremosos, súper recomendados", 4.9, false);
        registrarLocal("Guillotina Granizados Mall Alpes", "Belén", "Calle 30 #83-50", "3196287958", "guillotinagranizados", "Ubicación cómoda en mall y sabor brutal", 4.8, false);
        registrarLocal("Granizados los dioses", "Belén", "Carrera 70 #30-17", "3054352872", "granizados_los_dioses", "El granizado de Baileys es de otro nivel", 4.7, false);
        registrarLocal("Granizado la Galeria", "Belén", "Calle 24 #70A-08", "DidiFood / 3128904512", "granizadoslagaleriaa", "Llega perfecto a domicilio sin derretirse", 4.6, false);
        registrarLocal("Granizados los consagrados", "Belén", "Diagonal 75B #2A-121", "3108629133", "losconsagrados.oficial", "Muy buena música y precios justos", 4.8, false);
        registrarLocal("Granizados el Mirador", "Belén", "Calle 9 #75A-10", "3147721903", "granizadoselmirador", "Vista agradable y granizados frutales", 4.5, false);
        registrarLocal("Granizados BlueIceRacing", "Belén", "Carrera 65A #32B-19", "3016549821", "blueiceracing_", "Temática racing única y sabores neón", 4.7, false);
        registrarLocal("Juan Chupe", "Alfonso López", "Calle 91 #71C-5", "3045228946", "juanchupegranizadosss", "Parche de barrio con granizados potentes", 4.6, false);
        registrarLocal("Granizado Zero Stress", "La América", "Carrera 86 #45AA-23", "3043738410", "zerostress_granizados", "Ideal para tardear y quitar el calor", 4.5, false);
        registrarLocal("Space Cocktails", "La América", "Carrera 88 #43-79", "3015219720", "space_cocktailsla13", "Decoración espacial y vasos LED increíbles", 4.9, false);
        registrarLocal("Guillotina Granizados (La América)", "La América", "Carrera 86 #42C-2", "3196287958", "guillotinagranizados", "Misma calidad premium de siempre", 4.8, false);
        registrarLocal("Luxury Drinks", "La América", "Carrera 88 #37-55", "3115409281", "luxurydrinks_medellin", "Cocteles granizados con licores importados", 4.7, false);
        registrarLocal("Urban Ice", "La América", "Carrera 85 #45C-84", "3017922369", "urbanice20", "Excelente textura de hielo y buen dulce", 4.6, false);
        registrarLocal("AstroFusion", "Castilla", "Calle 92B #66A-49", "3028813490", "astrofusion.cocktails", "Combinaciones exóticas en el boulevard", 4.8, false);
        registrarLocal("Froozy Cocktails", "Castilla", "Carrera 72 #98-75", "3015778665", "frozzycocktails", "Súper refrescantes y atención de primera", 4.7, false);
        registrarLocal("Emojis Cocktails", "Aranjuez", "Carrera 49A #92-78", "3205607858", "emojiscocktail", "Muy instagrameables y llenos de toppings", 4.8, false);
        registrarLocal("Holly Cocktails", "Aranjuez", "Calle 93 #49A-43", "3218002039", "hollycocktails_manrique", "Gran variedad de licores y cremas", 4.7, false);
        registrarLocal("Happy Club", "Aranjuez", "Carrera 49A #91-09", "3024068592", "happyclubgranizados", "Ambiente fiestero y combos económicos", 4.6, false);
        registrarLocal("Uvalula Granizados", "Aranjuez", "Carrera 49A #92-58", "3146971665", "uvalulacocktails", "El sabor de uva y maracuyá es brutal", 4.7, false);
        registrarLocal("Granizados espaciales", "Manrique", "Carrera 37 #71C-58", "3154098211", "granizados.espaciales", "Porciones generosas en plena 45", 4.6, false);
        registrarLocal("Bajo cero", "Manrique", "Calle 70 #44-113", "3007891234", "bajo.ceroclub", "Hielo ultra fino y buen punto de licor", 4.5, false);
        registrarLocal("Mix cocktails", "Campo Valdés", "Carrera 48A #72A-78", "3117163153", "mixcocktails_campovaldes", "Mezclas personalizadas al gusto del cliente", 4.7, false);
        registrarLocal("Zoul Drinks", "Itagüí", "Carrera 57 #47A-55", "3169023418", "zoul.drinks", "El mejor punto de granizados en Itagüí", 4.8, false);
        registrarLocal("Ultravibe", "Itagüí", "Carrera 52 #73-08", "3046712390", "_ultravibe_", "Gran ambiente juvenil y coctelería neón", 4.7, false);
        registrarLocal("Roller Boom Cocktails", "Buenos Aires", "Calle 36 #36-29", "3226730785", "rollerboomcocktails", "Explosión de sabores ácidos y dulces", 4.8, false);
        registrarLocal("Hoz granizados", "Buenos Aires", "Carrera 28 #49-23", "3136094512", "hoz_granizados_", "Muy buena relación calidad-precio", 4.5, false);
        registrarLocal("Shark Cocktails", "Guayabal", "Calle 25 #65D-16", "3184509123", "sharkcoctails_", "El granizado tiburón azul es imperdible", 4.7, false);
        registrarLocal("Meloso Granisodas", "La Floresta", "Avenida 80 #45F-13", "Rappi / 3058123409", "meloso_granisodas", "Granisodas y micheladas súper refrescantes", 4.8, false);
        registrarLocal("Mansión del granizado", "La Floresta", "Transversal 45C #84-47", "3159601716", "mansiondelgranizado", "Espacio amplio para ir con amigos", 4.9, false);
        registrarLocal("Granizados Medellin (San Germán)", "San Germán", "Calle 63 #77-69", "3104942960", "granizadosmedellin", "Excelente servicio cerca a Ciudadela Univ.", 4.8, false);
        registrarLocal("AstroIce", "Envigado", "Diagonal 32B #33A Sur-05", "3244127779", "astroice7", "Calidad premium en el sur del Valle de Aburrá", 4.9, false);
        registrarLocal("La Ofi", "Sabaneta", "Carrera 44 #67 Sur-56", "3105453564", "laofigranizados", "El sitio ideal para después de la oficina", 4.8, false);

        // 2. Precarga de Productos e Insumos SLUSH (5 productos iniciales)
        registrarProducto("Granizado Blue Ice Eléctrico (Vodka + Curazao)", "Con Licor", 24000, 45, false);
        registrarProducto("Slush Mango Biche Especial con Chamoy y Tajín", "Tradicional", 16000, 60, false);
        registrarProducto("Granizado Crema de Whisky & Baileys Supreme", "Con Licor", 28000, 30, false);
        registrarProducto("Granizado Maracuyá & Lichi con Perlas Explosivas", "Neon", 19000, 50, false);
        registrarProducto("Membresía Destacada Mensual Directorio SLUSH", "Membresía", 250000, 39, false);

        // 3. Precarga de Nómina SLUSH (4 empleados iniciales)
        registrarEmpleado("Carlos Andrés Gómez", "Maestro Granicero / Mixólogo Senior", 3200000, 250000, 128000, false);
        registrarEmpleado("Valentina Montoya", "Coordinadora Directorio SLUSH", 3800000, 300000, 152000, false);
        registrarEmpleado("Santiago Restrepo", "Líder de Logística e Insumos", 2900000, 200000, 116000, false);
        registrarEmpleado("Mariana López", "Especialista Marketing (@SlushMed)", 2700000, 180000, 108000, false);

        // 4. Precarga de Facturas SLUSH (4 facturas iniciales)
        generarFacturaConDescuento("Guillotina Granizados (Laureles)", "Plan Premium SLUSH + Pack Perlas", 850000, 10.0, false);
        generarFacturaConDescuento("Evento Corporativo Provenza", "150x Granizados Neon + 80x Mango Biche", 2400000, 15.0, false);
        generarFacturaConDescuento("El Coctelista Urban", "Membresía Destacada + Kit Vasos LED", 620000, 5.0, false);
        generarFacturaConDescuento("Festival del Granizado Medellín", "Stand Oficial SLUSH Fin de Semana", 18500000, 0.0, false);
    }
}