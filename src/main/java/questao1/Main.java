package questao1;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== QUESTAO 1 - MONITOR DE ESTOQUE (OBSERVER) =====");

        Produto notebook = new Produto("Notebook", 10);

        Compras compras = new Compras();
        Vendas vendas = new Vendas();
        Auditoria auditoria = new Auditoria();

        notebook.adicionarObservador(compras);
        notebook.adicionarObservador(vendas);
        notebook.adicionarObservador(auditoria);

        System.out.println("\nProduto criado: " + notebook.getNome() + " com " + notebook.getQuantidade() + " unidades.");

        System.out.println("\n1) Vendidas 3 unidades (10 -> 7):");
        notebook.setQuantidade(7);

        System.out.println("\n2) Vendidas mais 4 unidades (7 -> 3):");
        notebook.setQuantidade(3);

        System.out.println("\n3) Tentativa de alterar para a mesma quantidade (3 -> 3):");
        notebook.setQuantidade(3);

        System.out.println("\n4) Estoque zerado (3 -> 0):");
        notebook.setQuantidade(0);

        System.out.println("\n5) Chegou reposicao (0 -> 20):");
        notebook.setQuantidade(20);

        System.out.println("\n--- Removendo a area de Compras dos observadores ---");
        notebook.removerObservador(compras);

        System.out.println("\n6) Vendidas 18 unidades (20 -> 2), Compras nao deve ser avisada:");
        notebook.setQuantidade(2);

        System.out.println("\n--- Testando outro produto ---");
        Produto mouse = new Produto("Mouse", 0);
        mouse.adicionarObservador(vendas);
        mouse.adicionarObservador(auditoria);

        System.out.println("\n7) Mouse estava zerado e recebeu 8 unidades (0 -> 8):");
        mouse.setQuantidade(8);
    }
}
