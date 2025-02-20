import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@WebServlet("/FacturacionServlet")
public class FacturacionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        List<Object> productos = new ArrayList<>();
        productos.add(new Producto(1, "Laptop", 1000));
        productos.add(new Producto(2, "Mouse", 20));
        productos.add(new Producto(3, "Teclado", 50));

        List<Object> clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "Juan Perez", "juan@example.com"));
        clientes.add(new Cliente(2, "Maria Lopez", "maria@example.com"));

        List<Object> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "admin", "Administrador"));
        usuarios.add(new Usuario(2, "cajero1", "Cajero"));

        List<Object> facturas = new ArrayList<>();
        facturas.add(new Factura(1, "Juan Perez", 1020));
        facturas.add(new Factura(2, "Maria Lopez", 50));

        List<Object> ventas = new ArrayList<>();
        ventas.add(new Venta(1, "Laptop", 1));
        ventas.add(new Venta(2, "Mouse", 1));

        Gson gson = new Gson();
        String jsonData = gson.toJson(new Datos(productos, clientes, usuarios, facturas, ventas));

        out.print(jsonData);
        out.flush();
    }
    
    class Datos {
        List<Object> productos, clientes, usuarios, facturas, ventas;
        Datos(List<Object> p, List<Object> c, List<Object> u, List<Object> f, List<Object> v) {
            this.productos = p;
            this.clientes = c;
            this.usuarios = u;
            this.facturas = f;
            this.ventas = v;
        }
    }

    class Producto {
        int id; String nombre; int precio;
        Producto(int i, String n, int p) { id = i; nombre = n; precio = p; }
    }

    class Cliente {
        int id; String nombre, email;
        Cliente(int i, String n, String e) { id = i; nombre = n; email = e; }
    }

    class Usuario {
        int id; String nombre, rol;
        Usuario(int i, String n, String r) { id = i; nombre = n; rol = r; }
    }

    class Factura {
        int id; String cliente; int total;
        Factura(int i, String c, int t) { id = i; cliente = c; total = t; }
    }

    class Venta {
        int id; String producto; int cantidad;
        Venta(int i, String p, int c) { id = i; producto = p; cantidad = c; }
    }
}
