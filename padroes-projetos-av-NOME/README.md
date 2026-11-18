# Avaliacao Semestral - Padroes de Projetos

Projeto em Java puro (somente biblioteca padrao, sem Maven/Gradle). Testado com JDK 17 ou superior.

## Questao 1 - Provisionamento Multinuvem

Padroes usados:
- **Abstract Factory**: `iFabricaNuvem` (fabrica abstrata), `FabricaAws` e `FabricaAzure` (fabricas concretas),
  `iMaquinaVirtual`, `iArmazenamento` e `iFila` (produtos abstratos) e os produtos concretos de cada provedor.
  O `MontadorAmbiente` recebe uma unica fabrica e nao conhece as classes concretas (RNF01, RNF02, RNF03, RNF06).
- **Singleton**: `Configuracao` (le o `config.properties` uma unica vez, double-checked locking) e
  `AuditLog` (historico unico, holder + metodos `synchronized`) (RNF04, RNF05).

Executar (a partir da pasta `questao1`):

```
javac -d bin src/*.java
java -cp bin Main
```

O arquivo `src/config.properties` tem a regiao padrao e o orcamento maximo por ambiente.

## Questao 2 - Casa de Leiloes Online

Padroes usados:
- **Observer**: `Lote` (subject), `iObservadorLote` (observer), `Participante`, `PainelLeiloeiro` e `Auditor` (RNF01).
- **Strategy**: `iRegraComissao` e as regras `ComissaoArte`, `ComissaoVeiculos`, `ComissaoImoveis`, `ComissaoEletronicos`.
  O `Lote` e o contexto e a regra pode ser trocada com `setRegraComissao()` (RNF02).
- **Decorator**: `iCompra`, `CompraLote`, `absServicoDecorator` e os servicos
  `SeguroTransporteDecorator`, `CertificadoAutenticidadeDecorator`, `EmbalagemEspecialDecorator`, `EntregaExpressaDecorator`.
  A `Fatura` imprime qualquer compra do mesmo jeito (RNF03, RNF04).

Executar (a partir da pasta `questao2`):

```
javac -d bin src/*.java
java -cp bin Main
```

## Diagramas

Os diagramas de classe estao em PlantUML: `questao1/diagrama/diagrama_questao1.puml` e
`questao2/diagrama/diagrama_questao2.puml`.
