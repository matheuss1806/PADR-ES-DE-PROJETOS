// Cliente da Abstract Factory: monta o ambiente.
// Recebe de fora UMA fabrica (iFabricaNuvem) e nao conhece nenhuma classe concreta
// de provedor. Nao existe if/switch para escolher o provedor aqui.
public class MontadorAmbiente {
    private iFabricaNuvem fabrica;

    public MontadorAmbiente(iFabricaNuvem fabrica) {
        this.fabrica = fabrica;
    }

    public Ambiente provisionar(SolicitacaoAmbiente solicitacao) {
        Configuracao config = Configuracao.getInstance();
        AuditLog audit = AuditLog.getInstance();
        String provedor = fabrica.getNomeProvedor();

        // RF05: verifica o orcamento ANTES de criar qualquer recurso
        double custoEstimado = estimarCusto(solicitacao);
        if (custoEstimado > config.getOrcamentoMaximo()) {
            String motivo = "custo estimado R$ " + String.format("%.2f", custoEstimado)
                    + " ultrapassa o orcamento de R$ " + String.format("%.2f", config.getOrcamentoMaximo());
            System.out.println("[RECUSADO] Ambiente " + solicitacao.getNomeAmbiente() + " (" + provedor + "): " + motivo);
            audit.registrar(solicitacao.getNomeAmbiente(), provedor, "RECUSADO - " + motivo);
            return null;
        }

        // cria os recursos, todos pela mesma fabrica (mesmo provedor)
        Ambiente ambiente = new Ambiente(solicitacao.getNomeAmbiente(), provedor, config.getRegiaoPadrao());
        for (int i = 0; i < solicitacao.getQuantidadeMaquinas(); i++) {
            ambiente.adicionarMaquina(fabrica.criarMaquinaVirtual());
        }
        ambiente.setArmazenamento(fabrica.criarArmazenamento(solicitacao.getArmazenamentoGb()));
        ambiente.setFila(fabrica.criarFila());

        System.out.println(ambiente.getResumo());
        audit.registrar(solicitacao.getNomeAmbiente(), provedor,
                "ACEITO - custo mensal estimado R$ " + String.format("%.2f", ambiente.getCustoMensal()));
        return ambiente;
    }

    // RF04 calculado com os custos unitarios da fabrica, sem criar recursos
    private double estimarCusto(SolicitacaoAmbiente solicitacao) {
        double custoMaquinas = solicitacao.getQuantidadeMaquinas() * fabrica.getCustoHoraMaquina() * Ambiente.HORAS_MES;
        double custoArmazenamento = solicitacao.getArmazenamentoGb() * fabrica.getCustoGbArmazenamento();
        return custoMaquinas + custoArmazenamento + fabrica.getCustoFila();
    }
}
