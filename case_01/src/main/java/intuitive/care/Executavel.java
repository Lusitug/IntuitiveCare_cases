package main.java.intuitive.care;

import java.util.List;

import main.java.intuitive.care.downloader.DownloadPDF;
import main.java.intuitive.care.scraping.Scraping;
import main.java.intuitive.care.zipping.ZippingPDF;

public class Executavel {
    public static void main(String[] args) {
      
        String pathDownload = DownloadPDF.getDownloadDirectory();
        System.out.println("Path Download"+pathDownload);

        // acessar diretamente as urls
        List<String> urlsPDF = Scraping.dataScraping();
        int iterator = 0;
        for(String url: urlsPDF){
            iterator++;
            System.out.println(url);
            DownloadPDF.downloadAnexos(url, "Anexo "+iterator+".pdf");
        }
        waitActions(5000);
        
        ZippingPDF.zippingAnexos(pathDownload);
        
        waitActions(5000);
        System.out.println("OK! ");
    }

    private static void waitActions(long timer){
        try {
            new Thread().sleep(timer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
