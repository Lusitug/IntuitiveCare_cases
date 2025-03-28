package main.java.intuitive.care.downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DownloadPDF {

    public DownloadPDF(){}
    
    private static String downloadDirectory = System.getProperty("user.dir") + File.separator + "case_01" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "intuitive" + File.separator + "care" + File.separator + "pdfs";
    
    public static void downloadAnexos(String urlPDF, String filename){
        ifNoExists(downloadDirectory);
        
        try {
            URL url = new URL(urlPDF);
            HttpURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            try(InputStream in = con.getInputStream();
                FileOutputStream out = new FileOutputStream(downloadDirectory +"\\"+ filename)){
                    
                byte[] buffer = new byte[4096];
                int bytesRead;

                while( (bytesRead = in.read(buffer)) != -1){
                    out.write(buffer,0, bytesRead);
                }

                System.out.println("Download concluido: " + filename);
            }
            
        } catch (Exception e) {
            System.out.println("ERRO: "+e.getMessage());
        }
    }
    
    private static void ifNoExists(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
   
    public static String getDownloadDirectory() {
        return downloadDirectory;
    }
}
