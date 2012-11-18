package codigointermedio;

import java.util.ArrayList;
import Lexico_Sintactico_Semantico.*; 

public class CodigoIntermedio
{
    public static boolean[] temporalesUsados = new boolean[10];
    public static ArrayList<Cuadruplo> codigoIntermedio = new ArrayList<Cuadruplo>(); // Tabla de Cuadruplos

    public static ArrayList<EtiNodo> listaEtiNodos = new ArrayList<EtiNodo>();
    private static int contadorEtiquetas = 0;
    
    public static String getTemporalNuevo()
    {
        for( int i = 0; i < temporalesUsados.length; i++ )
            if( temporalesUsados[i] == false )
            {
                temporalesUsados[i] = true;
                return "t" + i;
            }
        return "ERROR";
    }

    public static String getEtiquetaNueva()
    {
        contadorEtiquetas++;
        return "eti" + contadorEtiquetas;
    }

    public static EtiNodo getUltimoEtiNodo()
    {
        if ( !listaEtiNodos.isEmpty() )
            return listaEtiNodos.get(listaEtiNodos.size() - 1);
        else
            return null;
    }

    public static void agregarEtiNodo(EtiNodo nodo)
    {
        listaEtiNodos.add(nodo);
    }

    public static void quitarUltimoEtiNodo()
    {
        listaEtiNodos.remove(listaEtiNodos.size() - 1);
    }

    public static int cantidadEtiNodos()
    {
        return listaEtiNodos.size();
    }

    public static void generarCuadruplo(String op, String arg1, String arg2, String resultado)
    {
        Cuadruplo cuadruplo = new Cuadruplo(op, arg1, arg2, resultado);
        codigoIntermedio.add(cuadruplo);
        CodigoIntermedio.reciclarTemporales(arg1);
        CodigoIntermedio.reciclarTemporales(arg2);
    }
    
    public static void reciclarTemporales(String temporal)
    {
        if( temporal.charAt(0) != 't' || temporal.length() != 2)
            return;
        
        int id = Integer.parseInt(""+temporal.charAt(1));
        temporalesUsados[id] = false;
    }

    // Limpiar la tabla de Cuadruplos
    public static void limpiar()
    {
        codigoIntermedio = new ArrayList<Cuadruplo>();
        temporalesUsados = new boolean[10];
        listaEtiNodos = new ArrayList<EtiNodo>();
        contadorEtiquetas = 0;
    }

    public static void imprimirCuadruplos()
    {
        String result = "";
        Cuadruplo cuadruploActual = null;
        String op = "", arg1 = "", arg2 = "", resultado = "";

        result =  "-----------------CUADRUPLOS-----------------\n\n"
                + "  \t| OP\t| ARG1\t| ARG2\t| RESULTADO\t|\n"
                + "--------------------------------------------\n";
        for(int i = 0; i < codigoIntermedio.size(); i++)
        {
            cuadruploActual = codigoIntermedio.get(i);
            op = cuadruploActual.getOperador();
            arg1 = cuadruploActual.getArg1();
            arg2 = cuadruploActual.getArg2();
            resultado = cuadruploActual.getResultado();
            result += String.valueOf(i) + "\t| " + op + "\t| " + arg1 + "\t| " + arg2 + "\t| " + resultado + "\t\t|\n";
        }

        interfaz.resultadoText.append(result);
    }
}
