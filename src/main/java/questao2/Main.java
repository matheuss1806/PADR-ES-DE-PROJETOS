package questao2;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== QUESTAO 2 - TARIFA DE ESTACIONAMENTO (STRATEGY) =====");

        EstrategiaCobranca rotativo = new CobrancaRotativo();
        EstrategiaCobranca mensalista = new CobrancaMensalista();
        EstrategiaCobranca conveniado = new CobrancaConveniado();

        System.out.println("\n--- Mesmo tempo (150 minutos) nos tres perfis ---");
        Tiquete t1 = new Tiquete("ABC-1234", 150, rotativo);
        Tiquete t2 = new Tiquete("DEF-5678", 150, mensalista);
        Tiquete t3 = new Tiquete("GHI-9012", 150, conveniado);
        t1.imprimir();
        t2.imprimir();
        t3.imprimir();

        System.out.println("\n--- Conveniado dentro dos 30 minutos gratis ---");
        Tiquete t4 = new Tiquete("JKL-3456", 25, conveniado);
        t4.imprimir();

        System.out.println("\n--- Trocando o perfil do mesmo tiquete durante a execucao ---");
        Tiquete tiquete = new Tiquete("XYZ-7777", 95, rotativo);
        tiquete.imprimir();

        tiquete.setEstrategia(conveniado);
        tiquete.imprimir();

        tiquete.setEstrategia(mensalista);
        tiquete.imprimir();
    }
}
