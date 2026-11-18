import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Observer concreto: registra todos os lances aceitos com data e hora
public class Auditor implements iObservadorLote {
    private List<String> registros;

    public Auditor() {
        registros = new ArrayList<String>();
    }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        registros.add(LocalDateTime.now() + " | lote=" + lote.getNome()
                + " | participante=" + lance.getParticipante()
                + " | valor=R$ " + String.format("%.2f", lance.getValor()));
        System.out.println("   [Auditor] lance registrado");
    }

    public void imprimirRegistros() {
        System.out.println("===== REGISTROS DO AUDITOR (" + registros.size() + " lances aceitos) =====");
        for (String registro : registros) {
            System.out.println(registro);
        }
    }
}
