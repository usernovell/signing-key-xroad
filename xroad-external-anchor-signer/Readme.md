# X-Road External Configuration Signer

## Comandos

### Crear certificado digital y llave privada para firma digital con el algoritmo RSA-SHA512
```
openssl
> req -x509 -sha512 -nodes -days 365 -newkey rsa:2048 -keyout certs/AND_private.key -out certs/AND_certificate.crt
```

### Firmar digitalmente el archivo de parámetros compartidos (shared-params.xml)
```
openssl dgst -sha512 -sign certs/AND_private.key -out data/signature.sha512 data/signedData
```

### Codificar la firma digital usando el algoritmo Base64
```
openssl base64 -in data/signature.sha512 -out data/signature.base64
```

### Pasos para ajustar la información requerida para el anclaje
* En el archivo **configuration_anchor_???.xml**, reemplazar en la línea 07 el certificado de verificación de la firma digital.
```
05    <source>
06       <downloadURL>https://farruza.s3.amazonaws.com/externalconfCOL</downloadURL>
07       <verificationCert>MIIELzCCAxegAwIBAgIUEcvtEL+Yglg1TTtVMg7Ny/YBGOUwDQYJKoZIhvcNAQENBQAwgaYxCzAJBgNVBAYTAkNPMQ8wDQYDVQQIDAZCT0dPVEExDzANBgNVBAcMBkJPR09UQTE5MDcGA1UECgwwQ09SUE9SQUNJT04gQUdFTkNJQSBOQUNJT05BTCBERSBHT0JJRVJOTyBESUdJVEFMMQswCQYDVQQLDAJJVDEMMAoGA1UEAwwDQU5EMR8wHQYJKoZIhvcNAQkBFhB4cm9hZEBhbmQuZ292LmNvMB4XDTIyMDYxODExMzYyOFoXDTIzMDYxODExMzYyOFowgaYxCzAJBgNVBAYTAkNPMQ8wDQYDVQQIDAZCT0dPVEExDzANBgNVBAcMBkJPR09UQTE5MDcGA1UECgwwQ09SUE9SQUNJT04gQUdFTkNJQSBOQUNJT05BTCBERSBHT0JJRVJOTyBESUdJVEFMMQswCQYDVQQLDAJJVDEMMAoGA1UEAwwDQU5EMR8wHQYJKoZIhvcNAQkBFhB4cm9hZEBhbmQuZ292LmNvMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwY0+p17Lc0dWBAQFzAZ+zWK7PvBa/9UWtrdV4/sxYdJYwNuZbnnBgRYRykjUDofLNw+/uv1OpgTQeiwyojRyOyFCFxI/Ko2EZn65sxMwuCO/RWv8lKD7/lR+vwk0hkfp2onGLybPCXVZJ8kTGltjlp/OuMRw+F7uNFIemXPDMVOOLDPu0b/wxo5HMjNoIOsceZ6rjZUzSG9HIeJrxJmIAgSgHMgLbdX1FFjom4stY/VYiZ1G9yXTrKioCI4VMLI+P48ipE/7IDwV7ufrPY2RrhU8XTt14AtmZlAbioWZvrI7CfXVDLsEvGPjgJPgGl3KLOjZVu0k57/bcBZahtb9UwIDAQABo1MwUTAdBgNVHQ4EFgQUjmEtESijXs+dcUCwWx2AruWW/s0wHwYDVR0jBBgwFoAUjmEtESijXs+dcUCwWx2AruWW/s0wDwYDVR0TAQH/BAUwAwEB/zANBgkqhkiG9w0BAQ0FAAOCAQEABxPMpc8XfLJIuREBYYVTWRIV4qU1oV8k2zIELqRnveoiQ4WF4ngoo2/DDprMcH6WlfpSdnZg3HXuTobsdJKd8EdBeGpd9aztzmX94bsgJgaxMpfoAEoCky+tiKmPHMLRWZbBg/BDdnUXO7+YdJ8eH9G5bxLevu2eI+nvHJAhe7koRqyq8DU/Zjt1W7esQKUKegdNRysKvCplrf0gzbTG2/NNogjab0nfYX749oriWZMjBh0NjM8wS+7Hi4foo7hcPCQqyyK1GB8oNpc74RQ1XqUR4Y0jTUOk0gPolKD4/jAXxa6zaAAM5rZaitXXryWvrQQ9fE6XhuQJOF4SZNUPWQ==</verificationCert>
08    </source>
```

* Modificar el archivo **shared-params.xml** para incluir o eliminar según el caso los tres (3) campos requeridos por X-Road versión Colombia.

* Generar Hash del archivo **shared-params.xml** usando el algoritmo SHA512, ejecutar **co.and.xroad.HashGenerator**.

* En el archivo **externalconf**, reemplazar en la línea 17 el Hash del archivo **shared-params.xml**. 
```
15    Hash-algorithm-id: http://www.w3.org/2001/04/xmlenc#sha512
16    
17    eIfYVNrSm9qcfsEpEKGXLsXVIUVDOr5vY7yZtvB6WCCns5YQmaVYpeliRlPGqNXrRsYf++xu77HClvVsuCQhug==
18    --FghwNhz/bYoND1D6qVXpCKwIy0g=
```

* En el archivo **externalconf**, reemplazar en la línea 23 el Hash del certificado de verificación de la firma digital.
```
22    Signature-Algorithm-Id: http://www.w3.org/2001/04/xmldsig-more#rsa-sha512
23    Verification-certificate-hash: TRmS8lggYvDBX2nXGMq19Cz6uP7vOJmBxMDqG2ZNlyBW86d20QGwkWHYYwODpxeWQfh38bs5Gj4Nn+a5bB/aNA==; hash-algorithm-id="http://www.w3.org/2001/04/xmlenc#sha512"
24
```

* Crear archivo textual con la información que se requiere firmar digitalmente, líneas 6-18 del archivo **externalconf**, ejemplo:
```
06    --CKNmMImFCB53HGYluzuyxUYJQAc=
07    Expire-date: 2022-06-30T14:35:01Z
08    Version: 2
09    
10    --CKNmMImFCB53HGYluzuyxUYJQAc=
11    Content-type: application/octet-stream
12    Content-transfer-encoding: base64
13    Content-identifier: SHARED-PARAMETERS; instance='SP-DEV01'
14    Content-location: /V2/20220619142501474983000/shared-params.xml
15    Hash-algorithm-id: http://www.w3.org/2001/04/xmlenc#sha512
16    
17    dIj826JpJEjDSAvvTvF/fEs/ESknd679v/8NmpGzQ9J68et8iRnvtI6xLrXqp/uKlHRZiIedd1Fa5YFjagZUwA==
18    --CKNmMImFCB53HGYluzuyxUYJQAc=
```

* Firmar digitalmente el archivo creado en el paso anterior (data/signedData) usando los comandos de **OpenSSL**.
```
openssl dgst -sha512 -sign certs/AND_private.key -out data/signature.sha512 data/signedData
openssl base64 -in data/signature.sha512 -out data/signature.base64
```

* En el archivo **externalconf**, reemplazar en la línea 25 la firma digital.
```
23    Verification-certificate-hash: TRmS8lggYvDBX2nXGMq19Cz6uP7vOJmBxMDqG2ZNlyBW86d20QGwkWHYYwODpxeWQfh38bs5Gj4Nn+a5bB/aNA==; hash-algorithm-id="http://www.w3.org/2001/04/xmlenc#sha512"
24    
25    su3Ihdmybmr0Kzws/lcMEe9dKyBJGITcCOD1iZicXP1fxP49dL7Kcsi44gGsKXFGu1tCFC/vXot32lXRi39Iy7KTmfnYYmAD5itbEqo5frNz1ohxDScqoWMNp7+sQVu+l53tsfzwuCPZlplwpMm5SXhwElJJHXNDqTT4iJUCIqoijIWOoWy4I04A+Spp4frYRbxxKZl6vt+tdfZZ7MNFi1eZQOR6ZYDXagncmY3wS8b5xFW7nzV9Uh16OIe4dCbBFevTRRSDwDylYJ0J5rV2p74cfD4eQZ8vCdd9lAOGappn9Yxm8b6MBJCxPGTaFvrp36Tz7HgZgEE4Ai+wLh7QLg==
26    --qHD4TH1k2bs5ua8iZUqRQ6TDV3Y=--
```

* Subir archivos **externalconf** y **shared-params.xml** a S3 u otro repositorio que permita exponerlos mediante HTTP/HTTPS.

* Cargar ancla externa.