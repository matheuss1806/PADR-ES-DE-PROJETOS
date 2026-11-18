import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Singleton: historico de auditoria unico em todo o programa.
// Tecnica usada: initialization-on-demand holder (a JVM garante uma unica criacao).
// Os metodos sao synchronized para que registros simultaneos nao se percam.
public class AuditLog {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    private List<String> registros;

    private AuditLog() {
        registros = new ArrayList<String>();
    }

    // classe interna que guarda a instancia, so e carregada no primeiro getInstance()
    private static class Holder {
        private static final AuditLog INSTANCE = new AuditLog();
    }

    public static AuditLog getInstance() {
        return Holder.INSTANCE;
    }

    // a data/hora e gerada dentro do synchronized, entao a ordem da lista
    // e exatamente a ordem em que os registros foram feitos
    public synchronized void registrar(String ambiente, String provedor, String resultado) {
        String dataHora = LocalDateTime.now().format(FORMATO);
        registros.add(dataHora + " | ambiente=" + ambiente + " | provedor=" + provedor + " | " + resultado);
    }

    // devolve uma copia que nao pode ser alterada por quem recebe
    public synchronized List<String> getRegistros() {
        return Collections.unmodifiableList(new ArrayList<String>(registros));
    }

    public synchronized void imprimirHistorico() {
        System.out.println("===== HISTORICO DE AUDITORIA (" + registros.size() + " registros) =====");
        for (int i = 0; i < registros.size(); i++) {
            System.out.println((i + 1) + ". " + registros.get(i));
        }
    }
}
