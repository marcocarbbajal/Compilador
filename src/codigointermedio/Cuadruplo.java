package codigointermedio;

public class Cuadruplo
{
    private String op = "";
    private String arg1 = "";
    private String arg2 = "";
    private String resultado = "";

    public Cuadruplo(String op, String arg1, String arg2, String resultado)
    {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.resultado = resultado;
    }

    public Cuadruplo(String op, String arg)
    {
        this.op = op;
        this.arg1 = arg;

    }

    /* Gets y Sets */
    public String getOperador()
    {
        return op;
    }

    public String getArg1()
    {
        return arg1;
    }

    public String getArg2()
    {
        return arg2;
    }

    public String getResultado()
    {
        return resultado;
    }

    public void setOperador(String op)
    {
        this.op = op;
    }

    public void setArg1(String arg1)
    {
        this.arg1 = arg1;
    }

    public void setArg2(String arg2)
    {
        this.arg2 = arg2;
    }

    public void setResultado(String resultado)
    {
        this.resultado = resultado;
    }

    @Override
    public String toString()
    {
        return "| " + this.op + " , " + this.arg1 + " , " + this.arg2 + " , " + this.resultado + " |";
    }
}
