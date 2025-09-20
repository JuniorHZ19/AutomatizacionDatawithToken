package pe.gob.auto.util;

import java.io.*;

public class TxtManager {

    public static StringBuilder leerArchivoTxt(String path){

        StringBuilder contenido =new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/"+path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return  contenido;
    }

    public static void crearArchivoTxt(String path,String contenido){

        try (FileWriter writer = new FileWriter(System.getProperty("user.dir")+"/"+path)) {

            writer.write(contenido);

            System.out.println("Se ah guardado correctamente en el .txt en la ruta :"+path);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
