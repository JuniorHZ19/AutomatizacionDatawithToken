package pe.gob.interbank.util;


import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class BodyManager {


    //Metodo que creara el formato del body con el path de nuestro formato body que debe estar en JsonBody

    public static StringBuilder crearFormatoBody(String path){

        StringBuilder contenido = new StringBuilder();

        contenido = TxtManager.leerArchivoTxt(path);

        System.out.println("Estructura del body : " + contenido.toString());

        return contenido;
    }

    public static String remplazarParametrosBodyfromDataTable(String body, DataTable dataTable){

        List<Map<String, String>> filas = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> fila : filas) {
            // Aquí puedes acceder a cada valor por su cabecera
            System.out.println("Etiquetas a remplazar: ");

            for (String cabecera : fila.keySet()) {

                String etiqueta = "<" + cabecera + ">";
                String valor = fila.get(cabecera);

                System.out.println("Se remplazara :" + etiqueta + " por : " + valor);

                body = body.replaceAll(etiqueta, valor);
            }
        }

        return body;
    }
}
