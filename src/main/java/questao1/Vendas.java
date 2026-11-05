package questao1;

public class Vendas implements ObservadorEstoque {

    @Override
    public void atualizar(String nomeProduto, int quantidadeAnterior, int novaQuantidade) {
        // Produto estava zerado e agora voltou a ter estoque
        if (quantidadeAnterior == 0 && novaQuantidade > 0) {
            System.out.println("  [Vendas] " + nomeProduto + " voltou ao estoque com "
                    + novaQuantidade + " unidades. Ja pode ser vendido novamente!");
        }
    }
}
