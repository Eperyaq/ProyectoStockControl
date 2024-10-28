package com.es.stockcontrol;

import com.es.stockcontrol.controller.impl.ProductoController;
import com.es.stockcontrol.controller.impl.ProveedorController;
import com.es.stockcontrol.controller.impl.UserController;
import com.es.stockcontrol.dbConnection.DBConnection;
import com.es.stockcontrol.model.entities.Producto;
import com.es.stockcontrol.model.entities.Proveedor;
import com.es.stockcontrol.model.entities.RespuestaHTTP;
import com.es.stockcontrol.model.entities.User;
import com.es.stockcontrol.repository.impl.ProveedorRepository;
import com.es.stockcontrol.service.impl.ProductoService;
import com.es.stockcontrol.service.impl.ProveedorService;
import com.es.stockcontrol.utils.Initializer;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal de la aplicación StockControl.
 * Esta clase se encarga de gestionar el flujo principal de la aplicación y las interacciones con el usuario.
 */
public class AppStockControl {

    // Instancias de servicios y controladores
    static DBConnection dbConnection = new DBConnection();
    static ProveedorRepository proveedorRepository = new ProveedorRepository(dbConnection);
    static ProveedorService proveedorService = new ProveedorService(proveedorRepository);
    static ProductoService productoService = new ProductoService();
    static ProveedorController proveedorController = new ProveedorController(proveedorService, productoService);

    /**
     * Método principal de la aplicación.
     * Este método se encarga de gestionar el flujo principal de la aplicación, realizando el login
     * y las operaciones de CRUD de productos y proveedores.
     *
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        // Declaro aquí variables que voy a usar durante la ejecución del main
        Scanner scan = new Scanner(System.in);
        boolean login = false;  // Variable para comprobar si se hace un login correcto o no
        User user = new User(); // Variable para almacenar al usuario que se ha logado

        /*
         * Inyecta los usuarios, si no existen, en la base de datos.
         *   Usuario : Elia - Contraseña: 123
         *   Usuario : Sara - Contraseña: 12
         *   Usuario : Manu - Contraseña: 1
         */
        Initializer.usersInyection();

        /*
         * Proceso de login:
         * Se pedirá al usuario su nombre y contraseña, y se validará contra los datos existentes.
         * Si el login es exitoso, se almacena al usuario logueado.
         */
        do {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("""
                    ******************************************************
                    ****    Bienvenid@ a StockControl               ******
                    ******************************************************
                    
                    Introduzca su usuario y contrasena para continuar (0 para salir)
                    """);
            System.out.print("user: ");
            String userInput = scan.nextLine();

            if ("0".equalsIgnoreCase(userInput)) {
                System.out.println("Saliendo...");
                System.exit(0);
            } else {
                System.out.print("password: ");
                String passwordInput = scan.nextLine();

                UserController pController = new UserController();

                RespuestaHTTP<User> respuestaHTTP = pController.login(userInput, passwordInput);

                try {
                    if (respuestaHTTP.getCodigo() == 200) {
                        if (respuestaHTTP.getObj() != null) {
                            user = respuestaHTTP.getObj();
                            System.out.println("Bienvenid@ " + user.getNombre_usuario() + "!");
                            login = true;
                        } else {
                            System.err.println("¡INTRODUCE EL OBJETO EN LA RESPUESTA HTTP DESDE EL CONTROLLER!");
                        }
                    } else {
                        System.out.printf("Error en el login\n\t-codigo %d\n\t-%s\n", respuestaHTTP.getCodigo(), respuestaHTTP.getMensaje());
                    }
                } catch (Exception e) {
                    System.out.println("Error controlado");
                }
            }
        } while (!login);


        /*
         * CRUD de gestión de Stock:
         *  1. Alta producto
         *  2. Baja producto
         *  3. Modificar nombre producto
         *  4. Modificar stock producto
         *  5. Get producto por id
         *  6. Get productos con stock
         *  7. Get productos sin stock
         *  8. Get proveedores de un producto
         *  9. Get todos los proveedores
         */

        String opc;

        do {
            System.out.println("""
                    ******************************************************
                    ****            APP STOCK CONTROL               ******
                    ******************************************************
                    
                    1. Alta producto
                    2. Baja producto
                    3. Modificar nombre producto
                    4. Modificar stock producto
                    5. Get producto por id
                    6. Get productos con stock
                    7. Get productos sin stock
                    8. Get proveedores de un producto
                    9. Get todos los proveedores
                    0. Salir
                    """);
            System.out.print("Seleccione una opción: ");
            opc = scan.nextLine();

            try {
                switch (opc) {
                    case "1":
                        altaProducto();
                        break;
                    case "2":
                        bajaProducto();
                        break;
                    case "3":
                        modificarNombreProducto();
                        break;
                    case "4":
                        modificarStockProducto();
                        break;
                    case "5":
                        getProductoPorId();
                        break;
                    case "6":
                        getProductosConStock();
                        break;
                    case "7":
                        getProductosSinStock();
                        break;
                    case "8":
                        getProveedoresDeUnProducto();
                        break;
                    case "9":
                        getTodosLosProveedores();
                        break;
                    case "0":
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Error en la elección");
                        break;
                }
            } catch (Exception e) {
                System.out.println("ERROR CONTROLADO");
            }
        } while (!opc.equals("0"));

    }

    /**
     * Realiza la operación de alta de un nuevo producto en la base de datos.
     */
    public static void altaProducto() {
        Scanner scan = new Scanner(System.in);
        ProductoController productoController = new ProductoController();

        System.out.println("1. Alta producto");

        System.out.println("DETALLES PRODUCTO");
        System.out.print("categoria: ");
        String categoriaProducto = scan.nextLine();
        System.out.print("nombre: ");
        String nombreProducto = scan.nextLine();
        System.out.print("precio sin IVA: ");
        String precioSinIva = scan.nextLine();
        System.out.print("descripcion: ");
        String descripcionProducto = scan.nextLine();

        System.out.println("DETALLES PROVEEDOR");
        System.out.print("nombre: ");
        String nombreProveedor = scan.nextLine();
        System.out.print("direccion: ");
        String direccionProveedor = scan.nextLine();

        RespuestaHTTP<Producto> respuesta = productoController.altaProducto(categoriaProducto, nombreProducto, precioSinIva, descripcionProducto, nombreProveedor, direccionProveedor);

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.printf("PRODUCTO INSERTADO CORRECTAMENTE\n%s", respuesta.getObj().toString());
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }


    }

    /**
     * Realiza la operación de dar de baja un producto de la base de datos.
     */
    public static void bajaProducto() {
        Scanner scan = new Scanner(System.in);
        ProductoController productoController = new ProductoController();
        System.out.println("2. Baja producto");

        System.out.print("Introduzca el id del producto: ");
        String idProducto = scan.nextLine();
        RespuestaHTTP<Producto> respuesta = productoController.bajaProducto(idProducto);

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.println("OPERACION EXITOSA");
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }

    /**
     * Modifica el nombre de un producto existente en la base de datos.
     */
    public static void modificarNombreProducto() {
        Scanner scan = new Scanner(System.in);
        ProductoController productoController = new ProductoController();
        System.out.println("3. Modificar nombre producto");
        System.out.print("Introduzca el id del producto: ");
        String idProducto = scan.nextLine();
        System.out.print("Introduzca el nuevo nombre del producto: ");
        String nuevoNombre = scan.nextLine();
        RespuestaHTTP<Producto> respuesta = productoController.modificarNombreProducto(idProducto, nuevoNombre);

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.println("OPERACION EXITOSA");
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }

    /**
     * Modifica el stock actual de un producto existente de la base de datos.
     */
    public static void modificarStockProducto() {
        Scanner scan = new Scanner(System.in);
        ProductoController productoController = new ProductoController();
        System.out.println("4. Modificar stock producto");

        System.out.print("Introduzca el id del producto: ");
        String idProducto = scan.nextLine();
        System.out.print("Introduzca el nuevo stock: ");
        String nuevoStock = scan.nextLine();
        RespuestaHTTP<Producto> respuesta = productoController.modificarStockProducto(idProducto, nuevoStock);

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.println("OPERACION EXITOSA");
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }

    /**
     * Obtiene la información de un producto existente en la base de datos por su identificador.
     */
    public static void getProductoPorId() {
        Scanner scan = new Scanner(System.in);
        ProductoController productoController = new ProductoController();

        System.out.println("5. Get producto por id");

        System.out.print("Introduzca el id del producto: ");
        String idProducto = scan.nextLine();
        RespuestaHTTP<Producto> respuesta = productoController.getProducto(idProducto);

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.println("OPERACION EXITOSA");
            System.out.println(respuesta.getObj().toString());
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }

    }

    /**
     * Obtiene una lista de todos los productos existentes que tengan stock mayor a 0.
     */
    public static void getProductosConStock() {
        ProductoController productoController = new ProductoController();
        System.out.println("6. Get productos con stock");

        RespuestaHTTP<List<Producto>> respuesta = productoController.getProductosConStock();

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.println("OPERACION EXITOSA");
            respuesta.getObj().forEach(producto -> {
                System.out.println(producto.toString());
            });
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }

    }

    /**
     * Obtiene una lista de todos los productos existentes cuyo stock sea 0.
     */
    public static void getProductosSinStock() {
        ProductoController productoController = new ProductoController();
        System.out.println("7. Get productos sin stock");


        RespuestaHTTP<List<Producto>> respuesta = productoController.getProductosSinStock();

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.println("OPERACION EXITOSA");
            respuesta.getObj().forEach(producto -> {
                System.out.println(producto.toString());
            });
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }


    /**
     * Obtiene una lista de proveedores que suministran un producto concreto.
     */
    public static void getProveedoresDeUnProducto() {
        Scanner scan = new Scanner(System.in);

        System.out.println("8. Get proveedores de un producto");

        System.out.print("Introduzca el id del producto: ");
        String idProducto = scan.nextLine();
        RespuestaHTTP<List<Proveedor>> respuesta = proveedorController.getProveedoresProducto(idProducto);

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.println("OPERACION EXITOSA");
            respuesta.getObj().forEach(proveedor -> {
                System.out.println(proveedor.toString());
            });
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }

    }

    /**
     * Obtiene una lista de todos los proveedores registrados en la base de datos.
     */
    public static void getTodosLosProveedores() {

        System.out.println("9. Get todos los proveedores");

        RespuestaHTTP<List<Proveedor>> respuesta = proveedorController.getTodosProveedores();

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.println("OPERACION EXITOSA");

            respuesta.getObj().forEach(proveedor -> {
                System.out.println(proveedor.toString());
            });
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }
}
