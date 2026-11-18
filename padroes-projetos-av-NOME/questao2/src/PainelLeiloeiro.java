// Observer concreto: painel do leiloeiro, mostra o maior lance e o total de lances
public class PainelLeiloeiro implements iObservadorLote {

    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.println("   [Painel] Lote '" + lote.getNome() + "' | maior lance: R$ "
                + String.format("%.2f", lote.getLanceAtual()) + " (" + lote.getMaiorLance().getNomeParticipante()
                + ") | total de lances: " + lote.getTotalLances());
    }
}
