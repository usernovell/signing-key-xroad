package co.and.xroad;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class HashGenerator {

    public static void main(String[] args) {

      //  String filePath = "/home/centralcol/xroad-external-anchor-signer/data/shared-params.xml";

        try {

           // File file = new File(filePath);
           // byte[] inputData = FileUtils.readFileToByteArray(file);
            //String hash = CryptoUtils.hashSHA512(inputData);

            System.out.println("");
            System.out.println("Hash:");
           // System.out.println(hash);

           String certificate = "MIIELzCCAxegAwIBAgIUEcvtEL+Yglg1TTtVMg7Ny/YBGOUwDQYJKoZIhvcNAQENBQAwgaYxCzAJBgNVBAYTAkNPMQ8wDQYDVQQIDAZCT0dPVEExDzANBgNVBAcMBkJPR09UQTE5MDcGA1UECgwwQ09SUE9SQUNJT04gQUdFTkNJQSBOQUNJT05BTCBERSBHT0JJRVJOTyBESUdJVEFMMQswCQYDVQQLDAJJVDEMMAoGA1UEAwwDQU5EMR8wHQYJKoZIhvcNAQkBFhB4cm9hZEBhbmQuZ292LmNvMB4XDTIyMDYxODExMzYyOFoXDTIzMDYxODExMzYyOFowgaYxCzAJBgNVBAYTAkNPMQ8wDQYDVQQIDAZCT0dPVEExDzANBgNVBAcMBkJPR09UQTE5MDcGA1UECgwwQ09SUE9SQUNJT04gQUdFTkNJQSBOQUNJT05BTCBERSBHT0JJRVJOTyBESUdJVEFMMQswCQYDVQQLDAJJVDEMMAoGA1UEAwwDQU5EMR8wHQYJKoZIhvcNAQkBFhB4cm9hZEBhbmQuZ292LmNvMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwY0+p17Lc0dWBAQFzAZ+zWK7PvBa/9UWtrdV4/sxYdJYwNuZbnnBgRYRykjUDofLNw+/uv1OpgTQeiwyojRyOyFCFxI/Ko2EZn65sxMwuCO/RWv8lKD7/lR+vwk0hkfp2onGLybPCXVZJ8kTGltjlp/OuMRw+F7uNFIemXPDMVOOLDPu0b/wxo5HMjNoIOsceZ6rjZUzSG9HIeJrxJmIAgSgHMgLbdX1FFjom4stY/VYiZ1G9yXTrKioCI4VMLI+P48ipE/7IDwV7ufrPY2RrhU8XTt14AtmZlAbioWZvrI7CfXVDLsEvGPjgJPgGl3KLOjZVu0k57/bcBZahtb9UwIDAQABo1MwUTAdBgNVHQ4EFgQUjmEtESijXs+dcUCwWx2AruWW/s0wHwYDVR0jBBgwFoAUjmEtESijXs+dcUCwWx2AruWW/s0wDwYDVR0TAQH/BAUwAwEB/zANBgkqhkiG9w0BAQ0FAAOCAQEABxPMpc8XfLJIuREBYYVTWRIV4qU1oV8k2zIELqRnveoiQ4WF4ngoo2/DDprMcH6WlfpSdnZg3HXuTobsdJKd8EdBeGpd9aztzmX94bsgJgaxMpfoAEoCky+tiKmPHMLRWZbBg/BDdnUXO7+YdJ8eH9G5bxLevu2eI+nvHJAhe7koRqyq8DU/Zjt1W7esQKUKegdNRysKvCplrf0gzbTG2/NNogjab0nfYX749oriWZMjBh0NjM8wS+7Hi4foo7hcPCQqyyK1GB8oNpc74RQ1XqUR4Y0jTUOk0gPolKD4/jAXxa6zaAAM5rZaitXXryWvrQQ9fE6XhuQJOF4SZNUPWQ==";
            String hashCert = CryptoUtils.hashSHA512(CryptoUtils.decodeBase64(certificate));
//
            System.out.println("");
            System.out.println("HashCert:");
            System.out.println(hashCert);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
