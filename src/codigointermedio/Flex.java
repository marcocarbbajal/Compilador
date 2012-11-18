/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package codigointermedio;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
/**
 *
 * @author Marco Carbajal
 */
public class Flex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      JFlex.Main.generate(new File("src\\codigointermedio\\Lexer.flex"));
    }
}
