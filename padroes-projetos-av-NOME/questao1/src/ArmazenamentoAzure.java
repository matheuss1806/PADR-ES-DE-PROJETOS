// Produto concreto: armazenamento da Azure (Blob Storage)
public class ArmazenamentoAzure implements iArmazenamento {
    public static final double CUSTO_GB = 0.02;

    private static int contador = 0;

    private String id;
    private int tamanhoGb;

    public ArmazenamentoAzure(int tamanhoGb) {
        this.id = "blob://" + proximoNumero();
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
    public double getCustoGb() {
        return CUSTO_GB;
    }

    @Override
    public double getCustoMensal() {
        return tamanhoGb * CUSTO_GB;
    }
}
