package main.java.intuitive.care.zipping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZippingPDF {
    
    public ZippingPDF(){}
    
    private static String zipFilePath = System.getProperty("user.dir") + File.separator + "case_01"  + File.separator + "anexos-atualizacao-do-rol-de-procedimentos.zip";

    public static void zippingAnexos(String downloadDir){
        try {
            FileOutputStream output = new FileOutputStream(zipFilePath);  
            ZipOutputStream zipOutput = new ZipOutputStream(output);
            File file_zip = new File(downloadDir);    

            for (File file : file_zip.listFiles()){
                FileInputStream inputs = new FileInputStream(file);
                zipOutput.putNextEntry(new ZipEntry(file.getName()));
                
                byte [] bytes = new byte[1024];
                int length;

                while( (length = inputs.read(bytes)) >= 0){
                    zipOutput.write(bytes,0 , length);
                }
                inputs.close();
            }
            zipOutput.close();
            output.close();
            System.out.println("Anexos zipados.");

        }catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }

}
