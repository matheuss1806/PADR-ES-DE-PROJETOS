// Classe principal da Questao 1 - Provisionamento Multinuvem
public class Main {

    // Componente que escolhe o provedor. E o unico lugar com if para decidir a fabrica.
    // Para incluir um novo provedor (ex: Gcp) basta criar as classes novas e um else if aqui.
    private static iFabricaNuvem escolherFabrica(String provedor) {
        if (provedor.equals("Aws")) {
            return new FabricaAws();
        } else {
            return new FabricaAzure();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== PROVISIONAMENTO MULTINUVEM ===");

        // quatro solicitacoes: 2 na Aws, 1 no Azure e 1 que estoura o orcamento
        SolicitacaoAmbiente s1 = new SolicitacaoAmbiente("loja-producao", "Aws", 2, 100);
        SolicitacaoAmbiente s2 = new SolicitacaoAmbiente("loja-homologacao", "Aws", 1, 50);
        SolicitacaoAmbiente s3 = new SolicitacaoAmbiente("erp-producao", "Azure", 3, 200);
        SolicitacaoAmbiente s4 = new SolicitacaoAmbiente("bigdata-analytics", "Azure", 8, 2000);

        // cada provisionamento roda em uma thread diferente
        ProvisionamentoThread t1 = new ProvisionamentoThread(new MontadorAmbiente(escolherFabrica(s1.getProvedor())), s1);
        ProvisionamentoThread t2 = new ProvisionamentoThread(new MontadorAmbiente(escolherFabrica(s2.getProvedor())), s2);
        ProvisionamentoThread t3 = new ProvisionamentoThread(new MontadorAmbiente(escolherFabrica(s3.getProvedor())), s3);
        ProvisionamentoThread t4 = new ProvisionamentoThread(new MontadorAmbiente(escolherFabrica(s4.getProvedor())), s4);

        // inicia as quatro ao mesmo tempo
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // espera todas terminarem antes de imprimir o historico
        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println();
        AuditLog.getInstance().imprimirHistorico();
    }
}
