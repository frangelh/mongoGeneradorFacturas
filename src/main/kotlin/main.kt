import com.google.gson.Gson
import java.io.File
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

fun generarFecha(): Date {
    var ra = Random()
    var anio = ra.nextInt(2017 - 2016) + 2016
    var dia = Math.floor((Math.random() * 30) + 1);
    var mes = Math.floor((Math.random() * 12) + 1);

    return Date();
}

data class Cliente(val ID: String, val NOMBRE: String)
data class Producto(val ID: String, val DESCRIPCION: String, val PRECIO: Double)
data class Factura(
        var codigoFactura: Long,
        var sucursal: String,
        var codigoCliente: String,
        var fechaFactura: Date,
        var nombreCliente: String,
        var ciudadFactura: String,
        var ciudadDespacho: String,
        var totalFactura: Double,
        var articulos: java.util.ArrayList<Articulo>
)

data class Articulo(var codigoProducto: String,
                    var descripcion: String,
                    var precioVenta: Double,
                    var cantidadFacturada: Double,
                    var importe: Double // importe = cantidad * precio
)

val clientes = arrayOf(
        Cliente(ID = "1001", NOMBRE = "MARIA CASTRO"),
        Cliente(ID = "1002", NOMBRE = "ROBERTO SANTANA"),
        Cliente(ID = "1003", NOMBRE = "MARIO RAMIREZ"),
        Cliente(ID = "1004", NOMBRE = "ALEXANDRA VASQUEZ"),
        Cliente(ID = "1005", NOMBRE = "JOSE RAMON RAMIREZ"),
        Cliente(ID = "1006", NOMBRE = "CARME GUTIERREZ"),
        Cliente(ID = "1007", NOMBRE = "ANGELA RODRIGUEZ"),
        Cliente(ID = "1008", NOMBRE = "MANUEL CEPEDA"),
        Cliente(ID = "1009", NOMBRE = "FRANCISCO PEREZ"),
        Cliente(ID = "1010", NOMBRE = "OLIVER RODRIGUEZ"))

val productos = arrayOf(
        Producto(ID = "11", DESCRIPCION = "PRODUCTO 11", PRECIO = 14.00),
        Producto(ID = "14", DESCRIPCION = "PRODUCTO 14", PRECIO = 10.00),
        Producto(ID = "42", DESCRIPCION = "PRODUCTO 42", PRECIO = 9.80),
        Producto(ID = "72", DESCRIPCION = "PRODUCTO 72", PRECIO = 34.80),
        Producto(ID = "51", DESCRIPCION = "PRODUCTO 51", PRECIO = 42.40),
        Producto(ID = "41", DESCRIPCION = "PRODUCTO 41", PRECIO = 7.70),
        Producto(ID = "65", DESCRIPCION = "PRODUCTO 65", PRECIO = 16.80),
        Producto(ID = "20", DESCRIPCION = "PRODUCTO 20", PRECIO = 64.80),
        Producto(ID = "33", DESCRIPCION = "PRODUCTO 33", PRECIO = 150.00),
        Producto(ID = "60", DESCRIPCION = "PRODUCTO 60", PRECIO = 27.20),
        Producto(ID = "39", DESCRIPCION = "PRODUCTO 39", PRECIO = 14.40),
        Producto(ID = "49", DESCRIPCION = "PRODUCTO 49", PRECIO = 16.00))

var listadoSucursal = arrayOf("SUCURSAL SANTIAGO", "SUCURSAL SANTO DOMINGO")
var listadoCiudad: Array<String> = arrayOf("SANTIAGO", "MOCA", "LA VEGA", "SANTO DOMINGO", "DAJABON", "MONTECRISTI", "HIGUEY", "MAO VALVERDE")


fun main(args: Array<String>) {
    var r = Random()

    var i: Int = 0
    var documentos = ArrayList<Factura>()
    while (i < 500000) {
        var cliente = clientes[r.nextInt(10)]
        // generar articulos a partir de los productos
        var articulos: ArrayList<Articulo> = ArrayList()
        var j: Int = 0
        var total: Double = 0.0
        while (j < 5) {
            var p = productos[r.nextInt(12)] // producto
            var c = r.nextInt(10).toDouble()+1
            var importe: Double = (p.PRECIO * c)
            importe = DecimalFormat("#.00").format(importe).toDouble()
            articulos.add(Articulo(p.ID, p.DESCRIPCION, p.PRECIO, c, importe))
            total += importe
            j++
        }
        var documento = Factura(
                codigoFactura = i.toLong(),
                sucursal = listadoSucursal[r.nextInt(2)],
                codigoCliente = cliente.ID,
                fechaFactura = generarFecha(),
                nombreCliente = cliente.NOMBRE,
                ciudadFactura = listadoCiudad[r.nextInt(8)],
                ciudadDespacho = listadoCiudad[r.nextInt(8)],
                totalFactura = DecimalFormat("#.00").format(total).toDouble(),
                articulos = articulos
        )

        documentos.add(documento)
        i++
    }


    println(Gson().toJson(documentos))

    File("./input.json").printWriter().use { out ->
        documentos.forEach {out.println(Gson().toJson(it))}
    }
    println("Terminado... !")


}












