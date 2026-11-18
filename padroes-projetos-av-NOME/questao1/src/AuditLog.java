import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Singleton: historico de auditoria unico em todo o programa.
// Os metodos sao synchronized para que registros simultaneos nao se percam.
public class AuditLog {
    //Atributo para persistir a instancia
    private static AuditLog instance;

    private List<String> registros;

    //Construtor privado
    private AuditLog() {
        registros = new ArrayList<String>();
    }

    //Metodo para uso da instancia
    public static synchronized AuditLog getInstance() {
        if (AuditLog.instance == null) {
            AuditLog.instance = new AuditLog();
        }
        return AuditLog.instance;
    }

    // a data/hora e gerada dentro do synchronized, entao a lista
    // fica na ordem em que os registros foram feitos
    public synchronized void registrar(String ambiente, String provedor, String resultado) {
        registros.add(LocalDateTime.now() + " | ambiente=" + ambiente + " | provedor=" + provedor + " | " + resultado);
    }

    public synchronized void imprimirHistorico() {
        System.out.println("===== HISTORICO DE AUDITORIA (" + registros.size() + " registros) =====");
        for (int i = 0; i < registros.size(); i++) {
            System.out.println((i + 1) + ". " + registros.get(i));
        }
    }
}
