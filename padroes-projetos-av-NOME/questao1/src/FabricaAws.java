// Fabrica concreta: cria somente recursos da Aws
public class FabricaAws implements iFabricaNuvem {

    @Override
    public iMaquinaVirtual criarMaquinaVirtual() {
        return new MaquinaVirtualAws();
    }

    @Override
    public iArmazenamento criarArmazenamento(int tamanhoGb) {
        return new ArmazenamentoAws(tamanhoGb);
    }

    @Override
    public iFila criarFila() {
        return new FilaAws();
    }

    @Override
    public String getNomeProvedor() {
        return "Aws";
    }

    @Override
    public double getCustoHoraMaquina() {
        return MaquinaVirtualAws.CUSTO_HORA;
    }

    @Override
    public double getCustoGbArmazenamento() {
        return ArmazenamentoAws.CUSTO_GB;
    }

    @Override
    public double getCustoFila() {
        return FilaAws.CUSTO_MENSAL;
    }
}
