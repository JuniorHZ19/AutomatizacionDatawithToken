package pe.gob.auto.step;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.http.Header;
import io.restassured.response.Response;
import pe.gob.auto.util.BodyManager;
import pe.gob.auto.util.TxtManager;
import pe.gob.auto.util.configManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class crearDataSimpleStep {

    private String  bodyString;
    private static String token;
    Map<String, String> headers = new HashMap<>();

    @And("usamos el formato de body  {string}")

    public void creamosBodyFormato(String archivo_nombre) {

        StringBuilder contenido= BodyManager.crearFormatoBody("/src/test/resources/JsonBody/"+archivo_nombre);

        bodyString = contenido.toString();
    }

    @And("remplazo los campos de JSON con los siguintes valores:")
    public void remplazoLosCamposDeJSONConLosSiguintesValores(DataTable dataTable) {


        bodyString= BodyManager.remplazarParametrosBodyfromDataTable(bodyString,dataTable);

        System.out.println("Body final :\n" + bodyString);
    }

    @And("creamos la data en el endpoint {string}")
    public void creamosLaDataEnElEndpoint(String endpoint) {

           StringBuilder token= TxtManager.leerArchivoTxt("/src/test/resources/token.txt");

            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .headers(headers)
                    .body(bodyString .replace("\"", "\\\"") )
                .when()
                .post(endpoint) // endpoint para crear recurso
                .then()
                .extract().response();

        assertEquals(200,response.getStatusCode());
        System.out.println("Código de respuesta: " + response.getStatusCode());
        System.out.println("Body de respuesta: " + response.getBody().asString());


    }

    @Given("generamos  token")
    public void generamosToken() {

        // Llamar al endpoint donde generamos el token :
        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", configManager.get("oauth.grant_type"))
                .formParam("client_id", configManager.get("oauth.client_id"))
                .formParam("client_secret", configManager.get("oauth.client_secret"))
                .formParam("scope", configManager.get("oauth.scope"))
                .post(configManager.get("oauth.token_url"));

        // Validar respuesta
        response.then().statusCode(200);

        // Guardar token para otros steps
        token = response.jsonPath().getString("access_token");

        System.out.println("Token generado: " + token);
        //Y guardamos token en txt

        // Guardar el token en  TXT
        TxtManager.crearArchivoTxt("src/test/resources/token.txt",token);

    }

    @And("configuramos las cabezeras :")
    public void configuramosLasCabezeras(DataTable dataTable) {

        List<Map<String, String>> filas = dataTable.asMaps(String.class, String.class);
        System.out.println("Las cabezeras son:" );
        for(Map<String, String> fila : filas ){

            headers.put(fila.get("key"),fila.get("value"));
            System.out.println(fila);
        }

        System.out.println("Las cabezeras completa:"+ headers);
    }
}
