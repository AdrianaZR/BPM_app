package Clases;

public class Creditos {

    private String hipotecario, automotriz;
    private int cuota_hipotecario, cuota_automotriz;

    public Creditos(){
        hipotecario = "1000000";
        cuota_hipotecario=12;
        automotriz = "500000";
        cuota_automotriz=8;
    }

    public String getHipotecario()
    {
        return hipotecario;
    }

    public int getCuotaHipotecaria()
    {
        return cuota_hipotecario;
    }

    public String getAutomotriz(){
        return automotriz;
    }

    public int getCuotaAutomotriz()
    {
        return cuota_automotriz;
    }
}