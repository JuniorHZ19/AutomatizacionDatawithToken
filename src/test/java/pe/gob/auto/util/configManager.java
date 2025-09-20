package pe.gob.auto.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configManager {

    private static Properties properties;

    private configManager() {}

    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream in = configManager.class.getClassLoader().getResourceAsStream("credenciales.properties")) {
                if (in == null) {
                    throw new RuntimeException("No se encontró credenciales.properties");
                }
                properties.load(in);
            } catch (IOException e) {
                throw new RuntimeException("Error al cargar credenciales.properties", e);
            }
        }
        return properties;
    }

    public static String get(String key) {
        return getProperties().getProperty(key);
    }
}
