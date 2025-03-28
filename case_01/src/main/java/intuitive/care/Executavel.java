package main.java.intuitive.care;

import java.util.List;

import main.java.intuitive.care.downloader.DownloadPDF;
import main.java.intuitive.care.scraping.Scraping;
import main.java.intuitive.care.zipping.ZippingPDF;

public class Executavel {
    public static void main(String[] args) {
        Scraping scraping = new Scraping();
        DownloadPDF downloadPDF = new DownloadPDF();
        ZippingPDF zippingPDF = new ZippingPDF();


        String pathDownload = downloadPDF.getDownloadDirectory();
        System.out.println("Path Download"+pathDownload);

        // acessar diretamente as urls
        List<String> urlsPDF = scraping.dataScraping();
        int iterator = 0;
        for(String url: urlsPDF){
            iterator++;
            System.out.println(url);
            downloadPDF.downloadAnexos(url, "Anexo "+iterator+".pdf");
        }
        waitActions(5000);
        
        zippingPDF.zippingAnexos(pathDownload);
        
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
