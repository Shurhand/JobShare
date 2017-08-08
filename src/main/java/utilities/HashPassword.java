
package utilities;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import utilities.internal.ConsoleReader;

import java.io.IOException;

public class HashPassword {
   public static void main(String[] args) throws IOException {
      Md5PasswordEncoder encoder;
      ConsoleReader reader;
      String line, hash;
      
      try {
         System.out.printf("HashPassword 1.3%n");
         System.out.printf("----------------%n%n");
         
         encoder = new Md5PasswordEncoder();
         reader = new ConsoleReader();
         
         line = reader.readLine();
         while (! line.equals("quit")) {
            hash = encoder.encodePassword(line, null);
            System.out.println(hash);
            line = reader.readLine();
         }
      } catch (Throwable oops) {
         System.out.flush();
         System.err.printf("%n%s%n", oops.getLocalizedMessage());
         //oops.printStackTrace(System.out);
      }
   }
   
}
