package main.java.intuitive.care.scraping;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Scraping {
    public Scraping(){}

    public static List<String> dataScraping() {
        // TODO Auto-generated method stub
        System.setProperty("webdriver.edge.driver", "case_01\\src\\main\\resources\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions(); 
        // opção referente ao uso da memoria compartilhada pelo navegador

        // correção de possiveis errros
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        //evitar dete~çao dos sites
        options.addArguments("--disable-blink-features=AutomationController") ;
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));   
        options.setExperimentalOption("useAutomationExtension", "null");

        options.addArguments("window-size=1200,800");

        // evitar ser detectado como bot
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");

        // entrar no site referido
        WebDriver driver = new EdgeDriver(options);
        driver.get("https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos");
        

       // procurar e acessar elementos de interesse na página

       // aceitar cookies
       WebElement acceptCookiesBtn = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div[2]/button[3]"));
       acceptCookiesBtn.click();
       waitActions(5000);

       // lista paara armazenar links dos anexos
       List<String> pdfLinks = new ArrayList<>();
       
       // acessar elementos dos Anexos I e II e extrair atributo href
       WebElement linkAnexoI = driver.findElement(By.xpath("//*[@id=\"cfec435d-6921-461f-b85a-b425bc3cb4a5\"]/div/ol/li[1]/a[1]"));
       String pdfLinkAnexoI = linkAnexoI.getAttribute("href"); // retornar 1
       
       if(pdfLinkAnexoI != null){
           System.out.println("Link encontrado: "+ pdfLinkAnexoI );
           pdfLinks.add(pdfLinkAnexoI);
           waitActions(3000);
        }
        
        
        WebElement linkAnexoII = driver.findElement(By.xpath("//*[@id=\"cfec435d-6921-461f-b85a-b425bc3cb4a5\"]/div/ol/li[2]/a"));
        String pdfLinkAnexoII = linkAnexoII.getAttribute("href"); // retornar 2
        if(pdfLinkAnexoII != null){
            System.out.println("Link encontrado: "+ pdfLinkAnexoII );
            pdfLinks.add(pdfLinkAnexoII);
            waitActions(3000);
        }

        return pdfLinks;
    }


    private static void waitActions(long timer){
        try {
            new Thread().sleep(timer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
