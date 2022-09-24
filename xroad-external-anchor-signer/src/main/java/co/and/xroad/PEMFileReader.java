package co.and.xroad;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class PEMFileReader {

    public static String getPublicKey(String path) {
        String publicKeyContent;
        try {
            publicKeyContent = new String(FileUtils.readFileToByteArray(new File(path)));
            publicKeyContent = publicKeyContent.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return publicKeyContent;
    }

    public static String getPrivateKey(String path) {
        String privateKeyContent;
        try {
            privateKeyContent = new String(FileUtils.readFileToByteArray(new File(path)));
            privateKeyContent = privateKeyContent.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return privateKeyContent;
    }

    public static String getCertificate(String path) {
        String content;
        try {
            content = new String(FileUtils.readFileToByteArray(new File(path)));
            content = content.replaceAll("\\n", "").replace("-----BEGIN CERTIFICATE-----", "").replace("-----END CERTIFICATE-----", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }

    public static String getContentWithoutHeaderFooter(String path) {
        String content;
        try {
            content = new String(FileUtils.readFileToByteArray(new File(path)));
            content = content.replaceAll("\\n", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }
}
