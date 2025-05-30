import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class MailSSLBypass {
    public static SSLSocketFactory getTrustingSSLSocketFactory() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() { return null; }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
            }
        };

        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new SecureRandom());
        return sc.getSocketFactory();
    }
}