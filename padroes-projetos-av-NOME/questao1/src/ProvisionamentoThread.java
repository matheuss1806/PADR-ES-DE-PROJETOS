// Thread que executa um provisionamento (heranca da classe Thread do Java)
public class ProvisionamentoThread extends Thread {
    private MontadorAmbiente montador;
    private SolicitacaoAmbiente solicitacao;

    public ProvisionamentoThread(MontadorAmbiente montador, SolicitacaoAmbiente solicitacao) {
        this.montador = montador;
        this.solicitacao = solicitacao;
    }

    @Override
    public void run() {
        System.out.println("[" + getName() + "] iniciando provisionamento de "
                + solicitacao.getNomeAmbiente() + " (" + solicitacao.getProvedor() + ")");
        montador.provisionar(solicitacao);
    }
}
