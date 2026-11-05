# Padrões de Projeto em Java – Avaliação Prática

**Universidade Positivo – Disciplina de Padrões de Projeto**

## Objetivo

Aplicar três padrões de projeto em problemas simples, cada um em sua própria questão:

| Questão | Problema | Padrão |
|---|---|---|
| 1 | Monitor de estoque | Observer |
| 2 | Tarifa de estacionamento | Strategy |
| 3 | Pedido de café | Decorator |

## Estrutura do projeto

```
PADR-ES-DE-PROJETOS/
├── pom.xml
├── README.md
└── src/main/java/
    ├── questao1/              (Observer)
    │   ├── ObservadorEstoque.java
    │   ├── Produto.java
    │   ├── Compras.java
    │   ├── Vendas.java
    │   ├── Auditoria.java
    │   └── Main.java
    ├── questao2/              (Strategy)
    │   ├── EstrategiaCobranca.java
    │   ├── CobrancaRotativo.java
    │   ├── CobrancaMensalista.java
    │   ├── CobrancaConveniado.java
    │   ├── Tiquete.java
    │   └── Main.java
    └── questao3/              (Decorator)
        ├── Bebida.java
        ├── Espresso.java
        ├── Cha.java
        ├── Complemento.java
        ├── Leite.java
        ├── Chantilly.java
        ├── Calda.java
        └── Main.java
```

Não é usada nenhuma biblioteca externa. O `pom.xml` serve apenas para organizar o projeto (Java 17 ou superior).

---

## Questão 1 – Monitor de Estoque (Observer)

### O que é o Observer?

O Observer define uma relação "um para muitos": quando um objeto (o **sujeito**) muda de estado, todos os objetos interessados nele (os **observadores**) são avisados automaticamente.

### Por que foi usado aqui?

O produto não precisa saber o que cada área faz com a informação. Ele só guarda uma lista de observadores e avisa todos quando a quantidade muda. Assim dá para adicionar ou remover áreas durante a execução, e criar uma nova área (ex.: Logística) sem mexer na classe `Produto`.

### Como funciona

- `ObservadorEstoque`: interface com o método `atualizar(nomeProduto, quantidadeAnterior, novaQuantidade)`.
- `Produto`: tem nome, quantidade e a lista de observadores. Métodos `adicionarObservador`, `removerObservador` e `setQuantidade`.
  - Se a nova quantidade for igual à atual, **não notifica ninguém**.
  - Se for diferente, atualiza e chama `atualizar(...)` de cada observador.
- `Compras`: alerta quando o estoque fica **abaixo de 5**.
- `Vendas`: alerta quando o produto **estava com 0 e voltou a ter estoque**.
- `Auditoria`: registra **toda** alteração.
- `Main`: faz várias alterações, mostra o caso sem mudança e remove o observador `Compras`.

### Exemplo de saída

```
1) Vendidas 3 unidades (10 -> 7):
  [Auditoria] Registro: Notebook alterado de 10 para 7 unidades.

2) Vendidas mais 4 unidades (7 -> 3):
  [Compras] ALERTA: estoque baixo de Notebook (3 unidades). Fazer novo pedido ao fornecedor.
  [Auditoria] Registro: Notebook alterado de 7 para 3 unidades.

3) Tentativa de alterar para a mesma quantidade (3 -> 3):
[Notebook] Quantidade continua 3. Nenhuma notificacao enviada.

5) Chegou reposicao (0 -> 20):
  [Vendas] Notebook voltou ao estoque com 20 unidades. Ja pode ser vendido novamente!
  [Auditoria] Registro: Notebook alterado de 0 para 20 unidades.

--- Removendo a area de Compras dos observadores ---

6) Vendidas 18 unidades (20 -> 2), Compras nao deve ser avisada:
  [Auditoria] Registro: Notebook alterado de 20 para 2 unidades.
```

---

## Questão 2 – Tarifa de Estacionamento (Strategy)

### O que é o Strategy?

O Strategy separa cada "jeito de fazer" algo (cada algoritmo) em uma classe própria, todas com a mesma interface. O objeto que usa a estratégia só chama o método da interface, sem saber qual regra está por trás, e a estratégia pode ser trocada em tempo de execução.

### Por que foi usado aqui?

Cada perfil de cliente tem uma regra de cobrança diferente. Sem o Strategy, o tíquete teria um `if/else` ou `switch` verificando o perfil. Com o Strategy, o tíquete apenas chama `estrategia.calcular(minutos)`. Para criar um perfil novo basta criar uma nova classe que implemente `EstrategiaCobranca`, sem alterar o código existente.

### Como funciona

- `EstrategiaCobranca`: interface com `calcular(minutos)` e `getNome()`.
- `CobrancaRotativo`: R$ 8,00 por **hora iniciada** (ex.: 61 min = 2 horas).
- `CobrancaMensalista`: valor fixo de R$ 5,00, independente do tempo.
- `CobrancaConveniado`: 30 primeiros minutos grátis; depois cobra R$ 8,00 por hora iniciada (contada sobre o tempo que passou dos 30 minutos) com **25% de desconto**.
- Valores e desconto estão em **constantes nomeadas** (`VALOR_POR_HORA`, `VALOR_FIXO`, `MINUTOS_GRATIS`, `PERCENTUAL_DESCONTO`).
- `Tiquete`: guarda placa, minutos e a estratégia atual. O método `setEstrategia` permite trocar o perfil durante a execução.
- `Main`: mostra o mesmo tempo nos três perfis e troca o perfil de um mesmo tíquete.

### Exemplo de saída

```
--- Mesmo tempo (150 minutos) nos tres perfis ---
Placa: ABC-1234 | Tempo: 150 min | Perfil: Rotativo | Valor: R$ 24.00
Placa: DEF-5678 | Tempo: 150 min | Perfil: Mensalista | Valor: R$ 5.00
Placa: GHI-9012 | Tempo: 150 min | Perfil: Conveniado | Valor: R$ 12.00

--- Conveniado dentro dos 30 minutos gratis ---
Placa: JKL-3456 | Tempo: 25 min | Perfil: Conveniado | Valor: R$ 0.00

--- Trocando o perfil do mesmo tiquete durante a execucao ---
Placa: XYZ-7777 | Tempo: 95 min | Perfil: Rotativo | Valor: R$ 16.00
Placa: XYZ-7777 | Tempo: 95 min | Perfil: Conveniado | Valor: R$ 12.00
Placa: XYZ-7777 | Tempo: 95 min | Perfil: Mensalista | Valor: R$ 5.00
```

(Dependendo do idioma do computador, o valor pode aparecer com vírgula, ex.: `R$ 24,00`.)

---

## Questão 3 – Pedido de Café (Decorator)

### O que é o Decorator?

O Decorator permite adicionar responsabilidades a um objeto "embrulhando" ele em outro objeto que tem a mesma interface. Cada embrulho acrescenta alguma coisa e repassa o resto para o objeto de dentro.

### Por que foi usado aqui?

Se fosse criada uma classe para cada combinação (`EspressoComLeite`, `EspressoComLeiteEChantilly`...), o número de classes explodiria. Com o Decorator, cada complemento é uma classe só, e as combinações são montadas na hora, inclusive repetindo o mesmo complemento. Um complemento novo é apenas mais uma classe que estende `Complemento`.

### Como funciona

- `Bebida`: interface com `getDescricao()` e `getPreco()`.
- `Espresso` (R$ 6,00) e `Cha` (R$ 4,50): bebidas base.
- `Complemento`: classe abstrata que implementa `Bebida` e guarda a bebida que está sendo "embrulhada".
- `Leite` (R$ 2,00), `Chantilly` (R$ 3,00) e `Calda` (R$ 2,50): cada um junta seu nome à descrição e soma seu preço ao preço da bebida de dentro.
- `Main`: monta quatro pedidos, sendo um deles com **Chantilly repetido**.

### Exemplo de saída

```
Pedido 1: Espresso, Leite
Total: R$ 8.00

Pedido 2: Cha, Calda, Chantilly
Total: R$ 10.00

Pedido 3: Espresso, Chantilly, Chantilly, Calda
Total: R$ 14.50

Pedido 4: Cha
Total: R$ 4.50
```

---

## Como executar

### Opção 1 – VS Code

Abra o projeto no VS Code (com o *Extension Pack for Java*), abra o arquivo `Main.java` da questão desejada e clique em **Run**.

### Opção 2 – Terminal com `javac` (sem Maven)

Na raiz do projeto:

```bash
javac -d out src/main/java/questao1/*.java src/main/java/questao2/*.java src/main/java/questao3/*.java
```

```bash
java -cp out questao1.Main
```

```bash
java -cp out questao2.Main
```

```bash
java -cp out questao3.Main
```

### Opção 3 – Maven

```bash
mvn compile
```

```bash
java -cp target/classes questao1.Main
```

(Troque `questao1` por `questao2` ou `questao3` para as outras questões.)
