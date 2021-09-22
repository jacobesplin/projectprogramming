package com.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;
public class APISelfSigned{
	
	
    public static void print(Object obj){
        System.out.println(obj);
    }
    
    public String sendDataToServer(String api,String buildJson) {
    	String streamToString="";
		// Create a trust manager that does not validate certificate chains
		File crtFile = new File("selfsigned.cer");
		Certificate certificate = null;
		try {
			certificate = CertificateFactory.getInstance("X.509").generateCertificate(new FileInputStream(crtFile));
		} catch (CertificateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Or if the crt-file is packaged into a jar file:
		// CertificateFactory.getInstance("X.509").generateCertificate(this.class.getClassLoader().getResourceAsStream("server.crt"));


		KeyStore keyStore;
		try {
			keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(null, null);
			keyStore.setCertificateEntry("server", certificate);

			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			trustManagerFactory.init(keyStore);

			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
			HttpsURLConnection connection = (HttpsURLConnection) new URL(api).openConnection();
			connection.setRequestMethod("POST"); // PUT is another valid option
			connection.setDoOutput(true);
			
			byte[] out = buildJson.getBytes(StandardCharsets.UTF_8);
			int length = out.length;

			connection.setFixedLengthStreamingMode(length);
			connection.setRequestProperty("Content-Type", "application/json");
			
			connection.setHostnameVerifier(new HostnameVerifier() {
		        @Override
		        public boolean verify(String hostname, SSLSession sslSession) {
		            return true;
		        }
		    });
			//System.out.println("did get input");
			connection.setSSLSocketFactory(sslContext.getSocketFactory());
			connection.connect();
			try(java.io.OutputStream os = connection.getOutputStream()) {
			    os.write(out);
			}
			InputStream responseStream = connection.getInputStream();
			//System.out.println("did get input");
			InputStreamReader inputStreamReader = new InputStreamReader(responseStream);
			Stream<String> streamOfString= new BufferedReader(inputStreamReader).lines();
	        streamToString = streamOfString.collect(Collectors.joining());
	        System.out.println(streamToString);
	        return streamToString;
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "{\"info\":\"false\"}";
    }
    //These are the functions asked for in the story
    /*--------------------------------------------------------------------------------------------------------*/
   
    public static void main(String[] args) {
    	APISelfSigned api = new APISelfSigned();
    	while(true) {
    		print(api.sendDataToServer("https://jacobsmuzik.ddns.net/ReactSpringBoot/api/v12/data/collection",""));
    		//print(Processes.time());
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    
    
        
}