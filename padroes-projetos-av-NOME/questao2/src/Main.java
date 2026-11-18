// Classe principal da Questao 2 - Casa de Leiloes Online (RF11)
public class Main {
    public static void main(String[] args) {
        System.out.println("=== CASA DE LEILOES ONLINE ===\n");

        // interessados
        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carla = new Participante("Carla");
        PainelLeiloeiro painel = new PainelLeiloeiro();
        Auditor auditor = new Auditor();

        // cadastro dos lotes (categorias diferentes, cada um com a sua regra de comissao)
        Lote quadro = new Lote("Quadro Paisagem ao Entardecer", "Arte", 10000.00, 15000.00, new ComissaoArte());
        Lote notebook = new Lote("Notebook Gamer", "Eletronicos", 3000.00, 6000.00, new ComissaoEletronicos());

        // acompanhamento dos lotes
        quadro.adicionarObservador(ana);
        quadro.adicionarObservador(bruno);
        quadro.adicionarObservador(carla);
        quadro.adicionarObservador(painel);
        quadro.adicionarObservador(auditor);

        notebook.adicionarObservador(bruno);
        notebook.adicionarObservador(carla);
        notebook.adicionarObservador(painel);
        notebook.adicionarObservador(auditor);

        System.out.println("----- Lances no lote: " + quadro.getNome() + " -----");
        ana.darLance(quadro, 10500.00);   // exatamente 5% acima do inicial -> aceito
        bruno.darLance(quadro, 10800.00); // menos de 5% -> recusado
        bruno.darLance(quadro, 12000.00); // aceito
        carla.darLance(quadro, 13000.00); // aceito

        System.out.println("\n>> Ana deixa de acompanhar o lote '" + quadro.getNome() + "'");
        quadro.removerObservador(ana);

        bruno.darLance(quadro, 16000.00); // aceito, Ana nao recebe mais aviso

        System.out.println("\n----- Lances no lote: " + notebook.getNome() + " -----");
        bruno.darLance(notebook, 3200.00); // aceito
        carla.darLance(notebook, 3300.00); // recusado (minimo 3360)
        carla.darLance(notebook, 3500.00); // aceito
        ana.darLance(notebook, 3700.00);   // aceito (Ana pode dar lance mesmo sem acompanhar o lote)

        // campanha: o quadro passa a usar a regra de Eletronicos (RF07)
        System.out.println("\n----- Campanha -----");
        quadro.setRegraComissao(new ComissaoEletronicos());

        System.out.println("\n----- Encerramento -----");
        quadro.encerrar();
        notebook.encerrar();
        bruno.darLance(quadro, 20000.00); // lote encerrado -> recusado

        // fatura do comprador do quadro com servicos adicionais
        // (embalagem especial contratada duas vezes)
        System.out.println();
        iCompra compra = new CompraLote(quadro.getNome(), quadro.getLanceAtual());
        compra = new SeguroTransporteDecorator(compra);
        compra = new CertificadoAutenticidadeDecorator(compra);
        compra = new EmbalagemEspecialDecorator(compra);
        compra = new EmbalagemEspecialDecorator(compra);
        compra = new EntregaExpressaDecorator(compra);

        Fatura fatura = new Fatura();
        fatura.imprimir(compra);

        // acerto do vendedor do lote vendido
        System.out.println();
        quadro.imprimirAcertoVendedor();

        System.out.println();
        auditor.imprimirRegistros();
    }
}
