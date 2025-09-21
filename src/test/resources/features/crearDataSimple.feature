
Feature: Creamos data

  @CrearDataSimple
  Scenario Outline: Creamos nueva data
    Given generamos  token
    And configuramos las cabezeras :
      | key            |    value         |
      | content-type   | application/json |
    And  usamos el formato de body  "bodyDataSimpleEstructura.json"
    And remplazo los campos de JSON con los siguintes valores:
      | dni      | nroDeTarjeta   |  CU |
      | <dni>    | <nroDeTarjeta> | <CU>|
    And creamos la data en el endpoint "https://f0k1ec2sp8.execute-api.us-east-1.amazonaws.com/test/streams/v1/interbank-da-hbo-stream/ingesta"
    Examples:
      | dni      | nroDeTarjeta        |CU            |
      | 77044540 |    4547751253262109 |00000062037590|


  @CrearDataDevice
  Scenario Outline: Creamos nueva data con cabezeras
    Given generamos  token
    And configuramos las cabezeras :
      | key                          | value                                        |
      | content-type                 | application/json                             |
      | branchid                     | 898                                          |
      | cardidtype                   | 0                                            |
      | consumerid                   | MMP                                          |
      | deviceid                     | 00.00.00.00                                  |
      | funcionalidadid              | ut VCLlabore veniam esse                     |
      | messageid                    | 05babc9424c3de523e64698938b141b4-            |
      | netid                        | BI                                           |
      | ocp-apim-subscription-key    | c2d32889dbcd258363c4e6f7b628c734             |
      | ocp-apim-subscription-secret | fd1992f0f46e874553b561ce0036d747             |
      | parentid                     | P-A0612-b3952f02-77c5-4d8f-b1d0-cf8425fd9564 |
      | serviceid                    | SRM                                          |
      | supervisorid                 | TNKI0000                                     |
      | timestamp                   | 2021-11-25T10:13:38.487-05:00                             |
      | traceid                      | T-A0612-30ad8c1b-c4c3-4eb9-91a5-a5c40d43fa6a |
      | userid                       | TNKI0000                                     |

    And  usamos el formato de body  "bodyDataDevice.json"
    And remplazo los campos de JSON con los siguintes valores:
      | dni      | nroDeTarjeta   |  CU |
      | <dni>    | <nroDeTarjeta> | <CU>|
    And creamos la data en el endpoint "https://apic-gw-smp.apps.ocpsbx.integracion.grupoib.local/ibk-uat/int-smp-uat/issued-device-adm-deb-123"
    Examples:
      | dni      | nroDeTarjeta        |CU            |
      | 77044540 |    4547751253262109 |00000062037590|
