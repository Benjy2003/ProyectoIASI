


public class Main 
{
    public static void main(String[] args) throws Exception 
    {
        TableroMP tMP = new TableroMP();
        TableroES tES = new TableroES();

        tMP.maximaPendiente();
        tMP.mostrar();
        tES.escaladaSimple();
        tES.mostrar();
        
    }
}
