package ValeMais;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int opcao;

        Scanner sc = new Scanner(System.in);

        EstoqueGerenciador gerenciador = new EstoqueGerenciador();

        do{

            System.out.println("\n=== 🛒 SISTEMA VALE MAIS ===");
            System.out.println("1 - Cadastrar Novo Produto");
            System.out.println("2 - Listar Estoque do Banco");
            System.out.println("3 - Remover Estoque do Banco");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(sc.nextLine());


        switch (opcao) {

            case 1 ->{
                System.out.println("Digite o nome da Marca do Produto");
                String nomeMarcaProduto = sc.nextLine();

                System.out.print("Digite o nome do produto: ");
                String nomeProduto = sc.nextLine();


                Produto novoProduto = new Produto(nomeMarcaProduto,nomeProduto);
                int produtoId = gerenciador.salvarProduto(novoProduto);

                if(produtoId != -1){

                    System.out.println("Digite a quantidade do produto: ");
                    int quantidadeProduto = Integer.parseInt(sc.nextLine());

                    System.out.println("Qual o código de carga (número)?");
                    int codigoCarga = Integer.parseInt(sc.nextLine());

                    System.out.print("Qual a data de validade? (Digite no formato AAAA-MM-DD): ");
                    String dataTexto =  sc.nextLine();

                    LocalDate dataValidade = LocalDate.parse(dataTexto);

                    gerenciador.salvarLote(produtoId, quantidadeProduto, dataValidade, codigoCarga);
                }

            }

            case 2 ->{

                gerenciador.buscarTodosDoBanco();

            }
            case 3 ->{

                System.out.println("Digite o ID do produto que deseja remover: ");
                int idProduto = Integer.parseInt(sc.nextLine());


                try {
                    gerenciador.removerProduto(idProduto);
                    System.out.println("Produto removido com sucesso!");
                } catch (SQLException e) {
                    System.out.println("ID não encontrado.");
                    throw new RuntimeException(e);
                }
            }

            case 0 ->{
                System.out.println("Encerrando o sistema...");



            }

            default -> System.out.println("Opção inválida, tente novamente!");


        }


        }while(opcao != 0);






    }

}
