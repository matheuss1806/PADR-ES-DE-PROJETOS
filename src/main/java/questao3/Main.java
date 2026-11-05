package questao3;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== QUESTAO 3 - PEDIDO DE CAFE (DECORATOR) =====\n");

        // Pedido 1: Espresso com Leite
        Bebida pedido1 = new Espresso();
        pedido1 = new Leite(pedido1);
        imprimirPedido(1, pedido1);

        // Pedido 2: Cha com Calda e Chantilly
        Bebida pedido2 = new Cha();
        pedido2 = new Calda(pedido2);
        pedido2 = new Chantilly(pedido2);
        imprimirPedido(2, pedido2);

        // Pedido 3: Espresso com Chantilly repetido (duas vezes) e Calda
        Bebida pedido3 = new Espresso();
        pedido3 = new Chantilly(pedido3);
        pedido3 = new Chantilly(pedido3);
        pedido3 = new Calda(pedido3);
        imprimirPedido(3, pedido3);

        // Pedido 4: Cha puro, sem complementos
        Bebida pedido4 = new Cha();
        imprimirPedido(4, pedido4);
    }

    private static void imprimirPedido(int numero, Bebida pedido) {
        System.out.println("Pedido " + numero + ": " + pedido.getDescricao());
        System.out.println("Total: R$ " + String.format("%.2f", pedido.getPreco()));
        System.out.println();
    }
}
