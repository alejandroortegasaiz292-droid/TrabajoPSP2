package chat;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

// servidor del chat.
// abre un puerto y se queda esperando las conexiones de los clientes.
// cuando un cliente se conecta, se enseña un mensaje por pantalla.

public class Servidor {
    public static void main(String[] args) {

        // cargo la configuración desde el fichero de chat.properties
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("chat.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("[SERVIDOR] No se pudo leer chat.properties: "+ e.getMessage());
            return; // si no hay config, salgo
        }

        // leo el puerto del fichero de propiedades, uso 12345 por defecto si no hay
        int puerto = Integer.parseInt(props.getProperty("servidor.puerto", "12345"));

        System.out.println("[SERVIDOR] Iniciando mi servidor en el puerto "+ puerto + "..");

        // Creo el ServerSocket del servidor y lo inicio.
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("[SERVIDOR] El servidor está presente, esperando clientes..");

            // Bucle infinito: cada vez que un cliente se conecta se escribe un mensaje
            while (true) {
                // accept() se bloquea hasta que un cliente se conecta
                Socket socketCliente = serverSocket.accept();

                System.out.println("[SERVIDOR] Nuevo cliente conectado desde "
                        + socketCliente.getInetAddress() + "-" + socketCliente.getPort());

                // De momento solo cierro el socket del cliente
                socketCliente.close();
            }
        } catch (IOException e) {
            System.err.println("[SERVIDOR] Error de Entrada/Salida en el servidor: " + e.getMessage());
        }
    }
}

