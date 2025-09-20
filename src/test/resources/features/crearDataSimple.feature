
Feature: Creamos data

  @CrearDataSimple
  Scenario Outline: Creamos nueva data
    Given generamos  token
    And  usamos el formato de body  "bodyDataSimpleEstructura.json"
    And remplazo los campos de JSON con los siguintes valores:
      | dni      | nroDeTarjeta   |  CU |
      | <dni>    | <nroDeTarjeta> | <CU>|
    And creamos la data en el endpoint "https://f0k1ec2sp8.execute-api.us-east-1.amazonaws.com/test/streams/v1/interbank-da-hbo-stream/ingesta"
    Examples:
      | dni      | nroDeTarjeta        |CU            |
      | 77044540 |    4547751253262109 |00000062037590|
      | 77044540 |    4547751253262109 |00000062037590|