// Produto concreto: armazenamento da Aws (S3)
public class ArmazenamentoAws implements iArmazenamento {
    public static final double CUSTO_GB = 0.023;

    private static int contador = 0;

    private String id;
    private int tamanhoGb;

    public ArmazenamentoAws(int tamanhoGb) {
        this.id = "s3://" + proximoNumero();
        this.tamanhoGb = tamanhoGb;
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
    public int getTamanhoGb() {
        return tamanhoGb;
    }

    @Override
    public double getCustoMensal() {
        return tamanhoGb * CUSTO_GB;
    }
}
