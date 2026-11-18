// Imprime a fatura do comprador (RF09).
// Trata qualquer compra do mesmo jeito (com ou sem servicos), pois so usa a interface iCompra.
public class Fatura {

    public void imprimir(String comprador, iCompra compra) {
        System.out.println("===== FATURA DO COMPRADOR =====");
        System.out.println("Comprador: " + comprador);
        System.out.println(compra.getDescricao());
        System.out.println("Total a pagar: R$ " + String.format("%.2f", compra.getCusto()));
    }
}
