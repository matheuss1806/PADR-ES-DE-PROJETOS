package questao1;

// Interface que toda area interessada no estoque precisa implementar
public interface ObservadorEstoque {

    void atualizar(String nomeProduto, int quantidadeAnterior, int novaQuantidade);
}
