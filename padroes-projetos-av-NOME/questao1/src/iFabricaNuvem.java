// Fabrica abstrata (Abstract Factory)
// Cada fabrica concreta cria a familia completa de recursos de UM provedor,
// assim nao tem como misturar recursos de provedores diferentes no mesmo ambiente.
public interface iFabricaNuvem {
    iMaquinaVirtual criarMaquinaVirtual();
    iArmazenamento criarArmazenamento(int tamanhoGb);
    iFila criarFila();

    String getNomeProvedor();

    // custos unitarios, usados para estimar o custo antes de criar os recursos
    double getCustoHoraMaquina();
    double getCustoGbArmazenamento();
    double getCustoFila();
}
