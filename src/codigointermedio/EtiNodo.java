package codigointermedio;

/**
 * @author Jaime Andres
 */
public class EtiNodo
{
    private String id1, id2, id3;
    private String eti1, eti2, eti3;
    
    public EtiNodo(String id1,String id2, String id3, String eti1, String eti2, String eti3)
    {
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
        this.eti1 = eti1;
        this.eti2 = eti2;
        this.eti3 = eti3;
    }

    public EtiNodo()
    {


    }

    public String getId1()
    {
        return id1;
    }

    public String getId2()
    {
        return id2;
    }

    public String getId3()
    {
        return id3;
    }
    
    public String getEtiqueta1()
    {
        return eti1;
    }

    public String getEtiqueta2()
    {
        return eti2;
    }

    public String getEtiqueta3()
    {
        return eti3;
    }
}
