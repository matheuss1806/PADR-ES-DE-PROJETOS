// Fabrica concreta: cria somente recursos da Azure
public class FabricaAzure implements iFabricaNuvem {

    @Override
    public iMaquinaVirtual criarMaquinaVirtual() {
        return new MaquinaVirtualAzure();
    }

    @Override
    public iArmazenamento criarArmazenamento(int tamanhoGb) {
        return new ArmazenamentoAzure(tamanhoGb);
    }

    @Override
    public iFila criarFila() {
        return new FilaAzure();
    }

    @Override
    public String getNomeProvedor() {
        return "Azure";
    }

    @Override
    public double getCustoHoraMaquina() {
        return MaquinaVirtualAzure.CUSTO_HORA;
    }

    @Override
    public double getCustoGbArmazenamento() {
        return ArmazenamentoAzure.CUSTO_GB;
    }

    @Override
    public double getCustoFila() {
        return FilaAzure.CUSTO_MENSAL;
    }
}
