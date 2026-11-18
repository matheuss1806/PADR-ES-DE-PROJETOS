// Dados de uma solicitacao de ambiente (RF03)
public class SolicitacaoAmbiente {
    private String nomeAmbiente;
    private String provedor;
    private int quantidadeMaquinas;
    private int armazenamentoGb;

    public SolicitacaoAmbiente(String nomeAmbiente, String provedor, int quantidadeMaquinas, int armazenamentoGb) {
        this.nomeAmbiente = nomeAmbiente;
        this.provedor = provedor;
        this.quantidadeMaquinas = quantidadeMaquinas;
        this.armazenamentoGb = armazenamentoGb;
    }

    public String getNomeAmbiente() {
        return nomeAmbiente;
    }

    public String getProvedor() {
        return provedor;
    }

    public int getQuantidadeMaquinas() {
        return quantidadeMaquinas;
    }

    public int getArmazenamentoGb() {
        return armazenamentoGb;
    }
}
