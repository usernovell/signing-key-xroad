package co.and.xroad;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateFactory;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;

public class CryptoUtils {

    static {
        try {
            Security.addProvider(new BouncyCastleProvider());

            CERT_FACTORY = CertificateFactory.getInstance("X.509");
            KEY_FACTORY = KeyFactory.getInstance("RSA");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Holds the certificate factory instance. */
    public static final CertificateFactory CERT_FACTORY;

    /** Holds the RSA key factory instance. */
    public static final KeyFactory KEY_FACTORY;

    /** Digest provider instance. */
    public static final DigestCalculatorProvider DIGEST_PROVIDER = new BcDigestCalculatorProvider();

    public boolean verifySignature(Signature verifier, X509Certificate verificationCert, byte[] signature, byte[] sd) {
        String cn = verificationCert.getSubjectX500Principal().getName();

        try {
            verifier.initVerify(verificationCert.getPublicKey());
            verifier.update(sd);

            if (verifier.verify(signature)) {
                System.out.println("Verified signedData using certificate " + cn);

                return true;
            } else {
                System.out.println("Failed to verify signedData using certificate " + cn);

                return false;
            }
        } catch (Exception e) {
            System.out.println("Error verifying signedData using certificate " + cn);
            return false;
        }
    }

    /**
     * Reads X509Certificate object from given certificate bytes.
     * @param certBytes the certificate bytes
     * @return the read certificate
     */
    public X509Certificate readCertificate(byte[] certBytes) {
        InputStream inStream = new ByteArrayInputStream(certBytes);

        X509Certificate certificate;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            certificate = (X509Certificate) cf.generateCertificate(inStream);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        }
        return certificate;
    }

    /**
     * Creates a base 64 encoded string from the given input bytes.
     * @param input the value to encode
     * @return base 64 encoded string
     */
    public static String encodeBase64(byte[] input) {
        return DatatypeConverter.printBase64Binary(input);
    }

    /**
     * Decodes a base 64 encoded string into byte array.
     * @param base64Str the base64 encoded string
     * @return decoded byte array
     */
    public static byte[] decodeBase64(String base64Str) {
        return DatatypeConverter.parseBase64Binary(base64Str);
    }

    public static String hashSHA512(byte[] data)
            throws Exception {
        return encodeBase64(calculateDigest("SHA-512", data));
    }

    /**
     * Calculates message digest using the provided algorithm id.
     * @param algorithm the algorithm
     * @param data the data
     * @return message digest
     * @throws OperatorCreationException if digest calculator cannot be created
     * @throws IOException if an I/O error occurred
     */
    public static byte[] calculateDigest(String algorithm, byte[] data)
            throws OperatorCreationException, IOException {
        DigestCalculator dc = createDigestCalculator(algorithm);
        return calculateDigest(dc, data);
    }

    /**
     * Calculates message digest using the provided digest calculator.
     * @param dc the digest calculator
     * @param data the data
     * @return message digest
     * @throws IOException if the digest cannot be calculated
     */
    public static byte[] calculateDigest(DigestCalculator dc, byte[] data)
            throws IOException {
        dc.getOutputStream().write(data);
        dc.getOutputStream().close();
        return dc.getDigest();
    }

    /**
     * Creates a new digest calculator with the specified algorithm name.
     * @param algorithm the algorithm name
     * @return a new digest calculator instance
     * @throws OperatorCreationException if the calculator cannot be created
     */
    public static DigestCalculator createDigestCalculator(String algorithm)
            throws OperatorCreationException {
        AlgorithmIdentifier alg = new DefaultDigestAlgorithmIdentifierFinder().find(algorithm);
        return createDigestCalculator(alg);
    }

    /**
     * Creates a new digest calculator with the specified algorithm identifier.
     * @param algorithm the algorithm identifier
     * @return a new digest calculator instance
     * @throws OperatorCreationException if the calculator cannot be created
     */
    public static DigestCalculator createDigestCalculator(
            AlgorithmIdentifier algorithm) throws OperatorCreationException {
        return DIGEST_PROVIDER.get(algorithm);
    }
}
