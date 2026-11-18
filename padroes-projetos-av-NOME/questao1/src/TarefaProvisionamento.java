// Tarefa executada em uma thread separada: faz um provisionamento
public class TarefaProvisionamento implements Runnable {
    private MontadorAmbiente montador;
    private SolicitacaoAmbiente solicitacao;

    public TarefaProvisionamento(MontadorAmbiente montador, SolicitacaoAmbiente solicitacao) {
        this.montador = montador;
        this.solicitacao = solicitacao;
    }

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] iniciando provisionamento de "
                + solicitacao.getNomeAmbiente() + " (" + solicitacao.getProvedor() + ")");
        montador.provisionar(solicitacao);
    }
}
