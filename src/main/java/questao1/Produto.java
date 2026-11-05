package questao1;

import java.util.ArrayList;
import java.util.List;

// Produto e o "sujeito" observado: quando o estoque muda, ele avisa os observadores
public class Produto {

    private String nome;
    private int quantidade;
    private List<ObservadorEstoque> observadores = new ArrayList<>();

    public Produto(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public void adicionarObservador(ObservadorEstoque observador) {
        observadores.add(observador);
    }

    public void removerObservador(ObservadorEstoque observador) {
        observadores.remove(observador);
    }

    public void setQuantidade(int novaQuantidade) {
        // Se a quantidade nao mudou, ninguem e notificado
        if (novaQuantidade == quantidade) {
            System.out.println("[" + nome + "] Quantidade continua " + quantidade + ". Nenhuma notificacao enviada.");
            return;
        }

        int quantidadeAnterior = quantidade;
        quantidade = novaQuantidade;
        notificarObservadores(quantidadeAnterior);
    }

    private void notificarObservadores(int quantidadeAnterior) {
        for (ObservadorEstoque observador : observadores) {
            observador.atualizar(nome, quantidadeAnterior, quantidade);
        }
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
