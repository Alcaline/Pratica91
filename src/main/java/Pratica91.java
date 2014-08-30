
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná
 * DAINF - Departamento Acadêmico de Informática
 * IF62C - Fundamentos de Programação 2
 * 
 * Template de projeto de programa Java usando Maven.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class Pratica91 {
    public static void main(String[] args) {
        
        Runtime rt = Runtime.getRuntime();
        String os = System.getProperty("os.name");
        Process proc = null;
        String cmd = "";
        String dir = "texto.txt";
        int nRead;
        byte[] saida = new byte[1024];
        BufferedReader bfr;
        String linha;
        
        System.out.println(
                "Sistema Operacional: " + os +
                "\nNumero de Processadores: " + rt.availableProcessors() +
                "\nMemória Total: " + rt.totalMemory()/1024 + "Mb" +
                "\nMemória Livre: " + rt.freeMemory()/1024 + "Mb" +
                "\nMemória Disponível " + rt.maxMemory()/1024 + "Mb"
        );
        
        if (os.toLowerCase().contains("linux"))
            cmd = "gedit " + dir;
        else if (os.toLowerCase().contains("windows"))
            cmd = "notepad " + dir;
           
        try {
            proc = rt.exec(cmd);
            proc.waitFor();
        } catch (IOException ex) {
            Logger.getLogger(Pratica91.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pratica91.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while((nRead = proc.getInputStream().read()) != -1){
                    System.out.print(new String(saida, 0, nRead));
            }
        } catch (IOException ex) {
                Logger.getLogger(Pratica91.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            bfr = new BufferedReader(new FileReader(dir));
            
            while((linha = bfr.readLine()) != null)
                System.out.println(linha);
            bfr.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("O caminho não existe.");
            Logger.getLogger(Pratica91.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pratica91.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
