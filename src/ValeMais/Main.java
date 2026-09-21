package ValeMais;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        EstoqueGerenciador gerenciador = new EstoqueGerenciador();


        Itemestoque produto1 = new Itemestoque("Feijão",50, LocalDate.now().plusDays(50),101);
        Itemestoque produto2 = new Itemestoque("Arroz Branco", 100, LocalDate.now().plusDays(20), 102);
        Itemestoque produto3 = new Itemestoque("Leite Integral", 30, LocalDate.now().plusDays(5), 103);
        Itemestoque produto4 = new Itemestoque("Pão de Forma", 10, LocalDate.now().minusDays(2), 104);

        gerenciador.addItems(produto1);
        gerenciador.addItems(produto2);
        gerenciador.addItems(produto3);
        gerenciador.addItems(produto4);


        gerenciador.exibirTodosOsItens();

        gerenciador.verificadorDeValidades();


        produto3.darBaixa(5);

        System.out.println("Nova quantidade de leite: "+ produto3.getQuantidadeProduto());



        EstoqueGerenciador gerenciador2 = new EstoqueGerenciador();

        Itemestoque produtoNovo = new Itemestoque("Biscoito Recheado", 30, LocalDate.now().plusDays(100), 200);

        gerenciador2.salvarNoBanco(produtoNovo);

        gerenciador2.buscarTodosDoBanco();
    }

}
