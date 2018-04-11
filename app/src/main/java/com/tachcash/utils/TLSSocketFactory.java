package com.tachcash.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by John on 07.12.2017.
 */

public class TLSSocketFactory extends SSLSocketFactory {

  private SSLSocketFactory delegate;

  public TLSSocketFactory() throws KeyManagementException, NoSuchAlgorithmException {
    SSLContext context = SSLContext.getInstance("TLS");
    context.init(null, null, null);
    delegate = context.getSocketFactory();
  }

  @Override public String[] getDefaultCipherSuites() {
    return delegate.getDefaultCipherSuites();
  }

  @Override public String[] getSupportedCipherSuites() {
    return delegate.getSupportedCipherSuites();
  }

  @Override public Socket createSocket() throws IOException {
    return enableTLSOnSocket(delegate.createSocket());
  }

  @Override public Socket createSocket(Socket s, String host, int port, boolean autoClose)
      throws IOException {
    return enableTLSOnSocket(delegate.createSocket(s, host, port, autoClose));
  }

  @Override public Socket createSocket(String host, int port)
      throws IOException, UnknownHostException {
    return enableTLSOnSocket(delegate.createSocket(host, port));
  }

  @Override public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
      throws IOException, UnknownHostException {
    return enableTLSOnSocket(delegate.createSocket(host, port, localHost, localPort));
  }

  @Override public Socket createSocket(InetAddress host, int port) throws IOException {
    return enableTLSOnSocket(delegate.createSocket(host, port));
  }

  @Override
  public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort)
      throws IOException {
    return enableTLSOnSocket(delegate.createSocket(address, port, localAddress, localPort));
  }

  private Socket enableTLSOnSocket(Socket socket) {
    if (socket != null && (socket instanceof SSLSocket)) {
      ((SSLSocket) socket).setEnabledProtocols(new String[] { "TLSv1.1", "TLSv1.2" });
    }
    return socket;
  }

  public static class X509TrustManagerCustom implements X509TrustManager {

    @Override public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
        throws CertificateException {
      try {
        x509Certificates[0].checkValidity();
      } catch (Exception e) {
        throw new CertificateException("Certificate not valid or trusted.");
      }
    }

    @Override public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
        throws CertificateException {
      try {
        x509Certificates[0].checkValidity();
      } catch (Exception e) {
        throw new CertificateException("Certificate not valid or trusted.");
      }
    }

    @Override public X509Certificate[] getAcceptedIssuers() {
      return new X509Certificate[0];
    }
  }
}