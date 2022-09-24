package co.and.xroad;

import java.io.File;
import java.security.Signature;
import java.security.cert.X509Certificate;

import org.apache.commons.io.FileUtils;

public class SignVerifier {

    public static void main(String[] args) {

        // XRoad Example
//        String signatureData = "F1AJYSITwFzPjETVT9qDTlSJQNBH6wcX/y5htE0wXJdLhYxcvRpsQ4TApNKfBR0/IYc98pJhMEEcyxM+pWMtCBcSJjOXT8lmsdUwd6LMqzk/1zMkMibCvlBjrq1BsgDn3epud4P83OsWiG7GOEo/QJBIbPmRd7Qvij3LOqY412Z0V8WCg7r4xVIqUrqXuDrA8WiSIYN0QMXoTQWwdo1VpIG1H/neXuTg86SLaxtoQlu4Z11CRWqGZPF+Axl0xFz4T+EpAlRmk8bzemOM2mn2uh5eHC4HHWLNQiITFolHpPz+TJGB+qwQm30hVfYmV2V4O6wY6kRYkT9ihbLtPnDxdQ==";
//        String certificateData = "MIIELzCCAxegAwIBAgIUEcvtEL+Yglg1TTtVMg7Ny/YBGOUwDQYJKoZIhvcNAQENBQAwgaYxCzAJBgNVBAYTAkNPMQ8wDQYDVQQIDAZCT0dPVEExDzANBgNVBAcMBkJPR09UQTE5MDcGA1UECgwwQ09SUE9SQUNJT04gQUdFTkNJQSBOQUNJT05BTCBERSBHT0JJRVJOTyBESUdJVEFMMQswCQYDVQQLDAJJVDEMMAoGA1UEAwwDQU5EMR8wHQYJKoZIhvcNAQkBFhB4cm9hZEBhbmQuZ292LmNvMB4XDTIyMDYxODExMzYyOFoXDTIzMDYxODExMzYyOFowgaYxCzAJBgNVBAYTAkNPMQ8wDQYDVQQIDAZCT0dPVEExDzANBgNVBAcMBkJPR09UQTE5MDcGA1UECgwwQ09SUE9SQUNJT04gQUdFTkNJQSBOQUNJT05BTCBERSBHT0JJRVJOTyBESUdJVEFMMQswCQYDVQQLDAJJVDEMMAoGA1UEAwwDQU5EMR8wHQYJKoZIhvcNAQkBFhB4cm9hZEBhbmQuZ292LmNvMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwY0+p17Lc0dWBAQFzAZ+zWK7PvBa/9UWtrdV4/sxYdJYwNuZbnnBgRYRykjUDofLNw+/uv1OpgTQeiwyojRyOyFCFxI/Ko2EZn65sxMwuCO/RWv8lKD7/lR+vwk0hkfp2onGLybPCXVZJ8kTGltjlp/OuMRw+F7uNFIemXPDMVOOLDPu0b/wxo5HMjNoIOsceZ6rjZUzSG9HIeJrxJmIAgSgHMgLbdX1FFjom4stY/VYiZ1G9yXTrKioCI4VMLI+P48ipE/7IDwV7ufrPY2RrhU8XTt14AtmZlAbioWZvrI7CfXVDLsEvGPjgJPgGl3KLOjZVu0k57/bcBZahtb9UwIDAQABo1MwUTAdBgNVHQ4EFgQUjmEtESijXs+dcUCwWx2AruWW/s0wHwYDVR0jBBgwFoAUjmEtESijXs+dcUCwWx2AruWW/s0wDwYDVR0TAQH/BAUwAwEB/zANBgkqhkiG9w0BAQ0FAAOCAQEABxPMpc8XfLJIuREBYYVTWRIV4qU1oV8k2zIELqRnveoiQ4WF4ngoo2/DDprMcH6WlfpSdnZg3HXuTobsdJKd8EdBeGpd9aztzmX94bsgJgaxMpfoAEoCky+tiKmPHMLRWZbBg/BDdnUXO7+YdJ8eH9G5bxLevu2eI+nvHJAhe7koRqyq8DU/Zjt1W7esQKUKegdNRysKvCplrf0gzbTG2/NNogjab0nfYX749oriWZMjBh0NjM8wS+7Hi4foo7hcPCQqyyK1GB8oNpc74RQ1XqUR4Y0jTUOk0gPolKD4/jAXxa6zaAAM5rZaitXXryWvrQQ9fE6XhuQJOF4SZNUPWQ==";
//        String signedDataFilePath = "/home/fernando/XROAD/digital_signature/digital-signature/data/signedData";

        // Other Example
        String signatureData = PEMFileReader.getContentWithoutHeaderFooter("/home/centralcol/xroad-external-anchor-signer/data/signature.base64");
        String certificateData = PEMFileReader.getCertificate("/home/centralcol/xroad-external-anchor-signer/certs/AND_certificate.crt");
        String signedDataFilePath = "/home/centralcol/xroad-external-anchor-signer/data/signedData";

        String algId = "SHA512withRSA";

        CryptoUtils crypto = new CryptoUtils();

        try {
            Signature verifier = Signature.getInstance(algId, "BC");

            byte[] certBytes = crypto.decodeBase64(certificateData);
            X509Certificate cert = crypto.readCertificate(certBytes);

            byte[] signature = crypto.decodeBase64(signatureData);

            File file = new File(signedDataFilePath);
            byte[] signedData = FileUtils.readFileToByteArray(file);

            boolean verify = crypto.verifySignature(verifier, cert, signature, signedData);
            if(verify) {
                System.out.println("Signature verified");
            } else {
                System.out.println("Signature failed");
            }

            System.out.println("");
            System.out.println("Certificate:");
            System.out.println(certificateData);
            System.out.println("Signature:");
            System.out.println(signatureData);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}