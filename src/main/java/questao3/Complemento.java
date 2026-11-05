package questao3;

// Decorator: o complemento "embrulha" uma bebida e tambem e uma Bebida
public abstract class Complemento implements Bebida {

    protected Bebida bebida;

    public Complemento(Bebida bebida) {
        this.bebida = bebida;
    }
}
