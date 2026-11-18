// Produto concreto: fila da Aws (SQS)
public class FilaAws implements iFila {
    public static final double CUSTO_MENSAL = 0.40;

    private static int contador = 0;

    private String id;

    public FilaAws() {
        this.id = "sqs-" + proximoNumero();
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
