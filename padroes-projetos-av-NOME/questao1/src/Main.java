// Classe principal da Questao 1 - Provisionamento Multinuvem
public class Main {

    // Componente que escolhe o provedor. E o unico lugar com if para decidir a fabrica.
    // Para incluir um novo provedor (ex: Gcp) basta criar as classes novas e um else if aqui.
    private static iFabricaNuvem escolherFabrica(String provedor) {
        if (provedor.equals("Aws")) {
            return new FabricaAws();
        } else if (provedor.equals("Azure")) {
            return new FabricaAzure();
        }
        throw new IllegalArgumentException("Provedor desconhecido: " + provedor);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== PROVISIONAMENTO MULTINUVEM ===");

        // quatro solicitacoes: 2 na Aws, 1 no Azure e 1 que estoura o orcamento
        SolicitacaoAmbiente s1 = new SolicitacaoAmbiente("loja-producao", "Aws", 2, 100);
        SolicitacaoAmbiente s2 = new SolicitacaoAmbiente("loja-homologacao", "Aws", 1, 50);
        SolicitacaoAmbiente s3 = new SolicitacaoAmbiente("erp-producao", "Azure", 3, 200);
        SolicitacaoAmbiente s4 = new SolicitacaoAmbiente("bigdata-analytics", "Azure", 8, 2000);

        SolicitacaoAmbiente[] solicitacoes = { s1, s2, s3, s4 };
        Thread[] threads = new Thread[solicitacoes.length];

        // cada provisionamento roda em uma thread diferente, todos ao mesmo tempo
        for (int i = 0; i < solicitacoes.length; i++) {
            iFabricaNuvem fabrica = escolherFabrica(solicitacoes[i].getProvedor());
            MontadorAmbiente montador = new MontadorAmbiente(fabrica);
            threads[i] = new Thread(new TarefaProvisionamento(montador, solicitacoes[i]),
                    "thread-" + solicitacoes[i].getNomeAmbiente());
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        // espera todas as threads terminarem antes de imprimir o historico
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        System.out.println();
        AuditLog.getInstance().imprimirHistorico();
    }
}
