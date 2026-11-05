package questao1;

public class Compras implements ObservadorEstoque {

    private static final int ESTOQUE_MINIMO = 5;

    @Override
    public void atualizar(String nomeProduto, int quantidadeAnterior, int novaQuantidade) {
        if (novaQuantidade < ESTOQUE_MINIMO) {
            System.out.println("  [Compras] ALERTA: estoque baixo de " + nomeProduto
                    + " (" + novaQuantidade + " unidades). Fazer novo pedido ao fornecedor.");
        }
    }
}
