// Produto concreto: fila da Azure (Service Bus)
public class FilaAzure implements iFila {
    public static final double CUSTO_MENSAL = 0.50;

    private static int contador = 0;

    private String id;

    public FilaAzure() {
        this.id = "sb-" + proximoNumero();
    }

    private static synchronized int proximoNumero() {
        contador++;
        return contador;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getCustoMensal() {
        return CUSTO_MENSAL;
    }
}
