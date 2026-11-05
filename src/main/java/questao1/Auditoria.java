package questao1;

public class Auditoria implements ObservadorEstoque {

    @Override
    public void atualizar(String nomeProduto, int quantidadeAnterior, int novaQuantidade) {
        System.out.println("  [Auditoria] Registro: " + nomeProduto + " alterado de "
                + quantidadeAnterior + " para " + novaQuantidade + " unidades.");
    }
}
