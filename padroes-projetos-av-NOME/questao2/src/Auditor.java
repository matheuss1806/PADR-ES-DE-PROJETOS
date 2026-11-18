import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Observer concreto: registra todos os lances aceitos com data e hora
public class Auditor implements iObservadorLote {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private List<String> registros;

    public Auditor() {
        registros = new ArrayList<String>();
    }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        String registro = LocalDateTime.now().format(FORMATO) + " | lote=" + lote.getNome()
                + " | participante=" + lance.getNomeParticipante()
                + " | valor=R$ " + String.format("%.2f", lance.getValor());
        registros.add(registro);
        System.out.println("   [Auditor] lance registrado");
    }

    public void imprimirRegistros() {
        System.out.println("===== REGISTROS DO AUDITOR (" + registros.size() + " lances aceitos) =====");
        for (String registro : registros) {
            System.out.println(registro);
        }
    }
}
