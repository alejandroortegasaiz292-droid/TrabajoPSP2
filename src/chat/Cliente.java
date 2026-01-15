package chat;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

/**
 * cliente del chat.
 * de momento solo se conecta al servidor y se desconecta.
 * luego enviará comandos y leerá respuestas.
 */
public class Cliente {

    // punto de entrada del programa Cliente
    public static void main(String[] args) {

        // cargo la configuración desde el fichero chat.properties
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("chat.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("[CLIENTE] No se pudo leer chat.properties: " + e.getMessage());
            return; // si no hay config me salgo
        }

        // leo ip y puerto del servidor del fichero con los valores por defecto
        String ipServidor = props.getProperty("servidor.ip", "127.0.0.1");
        int puertoServidor = Integer.parseInt(props.getProperty("servidor.puerto", "12345"));

        System.out.println("[CLIENTE] Intentando conectar con "+ ipServidor+ ":" + puertoServidor + "..");

        // el try crea el socket del cliente y lo cerrará al final
        try (Socket socket = new Socket(ipServidor, puertoServidor)) {
            System.out.println("[CLIENTE] Conectado correctamente al servidor.");

            // aquí luego enviaré comandos al servidor
            // y tendré respuestas, pero de momento no
        } catch (IOException e) {
            System.err.println("[CLIENTE] No se pudo conectar con el servidor: " + e.getMessage());
        }
    }
}

