/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexico_Sintactico_Semantico;

/**
 *

 * @author Marco Carbajal
 */
public class Cup {

    public static void main(String[] args) {
        
        String opciones[] = new String[5];
        opciones[0] = "-destdir";
        opciones[1] = "src\\Lexico_Sintactico_Semantico\\";
        opciones[2] = "-parser";
        opciones[3] = "parser";
        opciones[4] = "src\\Lexico_Sintactico_Semantico\\Parser.cup";

        try
        {
            java_cup.Main.main(opciones);
       
        }
        catch ( Exception e )
        {
            System.out.println(e.getMessage());
        }
  
        
          opciones[0] = "-destdir";
        opciones[1] = "src\\Lexico_Sintactico_Semantico\\";
        opciones[2] = "-parser";
        opciones[3] = "parser_semantico";
        opciones[4] = "src\\Lexico_Sintactico_Semantico\\Semantico.cup";

        try
        {
            java_cup.Main.main(opciones);
       
        }
        catch ( Exception e )
        {
            System.out.println(e.getMessage());
        }
  
        
        opciones[0] = "-destdir";
        opciones[1] = "src\\codigointermedio\\";
        opciones[2] = "-parser";
        opciones[3] = "parser";
        opciones[4] = "src\\codigointermedio\\Parser.cup";

        try
        {
            java_cup.Main.main(opciones);
       
        }
        catch ( Exception e )
        {
            System.out.println(e.getMessage());
        }
  
        
        
        
        
    }
}
