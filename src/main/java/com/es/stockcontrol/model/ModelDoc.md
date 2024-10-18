### Ayuda para implementar Repository

# Documentación

En esta base de datos tenemos 3 tablas:
1. Tabla Proveedores
- id: Long -> AutoGenerado. PrimaryKey
- nombre: String -> único, longitud 50, not null
- direccion: String -> not null
- productos: List<Producto> -> Relacion de @OneToMany

2. Tabla Productos
- id: String -> PrimaryKey -> Lo generamos nosotros
- categoria: String -> not null, longigud 10
- nombre: String -> longitud 50, not null
- descripcion: String
- precio_sin_iva: float -> not null
- precio_con_iva: float -> not null
- fecha_alta: Date -> not null
- proveedor: Proveedor -> Relación de @ManyToOne
- stock: int
3. Tabla Usuarios
- nombre_usuario: String -> PrimaryKey
- password: String -> not null, longitud 20



## Representación de Entidades del Dominio

La capa Model define las estructuras de datos que representan las entidades principales de la aplicación. Estas entidades suelen estar mapeadas directamente a tablas de la base de datos o a documentos en bases de datos NoSQL. Ejemplos típicos son clases como User, Product, Order, que corresponden a usuarios, productos o pedidos.
Definen los atributos y las relaciones de las entidades. 
Especifican restricciones y reglas de validación (como tipos de datos, restricciones de unicidad, valores por defecto).
