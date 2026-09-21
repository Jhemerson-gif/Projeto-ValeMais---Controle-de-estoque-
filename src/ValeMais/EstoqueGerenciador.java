package ValeMais;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstoqueGerenciador {

    private List<Itemestoque> listadeItens;

    public EstoqueGerenciador() {
        this.listadeItens = new ArrayList<>();
    }

    public  void addItems(Itemestoque novoItem){ //adicionando itens no estoque
        listadeItens.add(novoItem);
        System.out.println("Produto" + novoItem + " adicionado com sucesso!");
    }

    public void exibirTodosOsItens(){  // Itens adicionados no estoque
        System.out.println("---- Lista de Estoque Atual ----");

        for (Itemestoque item : listadeItens) {
            System.out.println("Produto "+ item.getNomedoProduto() +
                    " | Quantidade: " + item.getQuantidadeProduto() +
                    " | Validade: " + item.getDataValidade());
        }
    }


    public void verificadorDeValidades(){
        System.out.println("---- Validades do Estoque ----");

        LocalDate diaHoje = LocalDate.now();

        for(Itemestoque item : listadeItens){

            long diasParaVencer = java.time.temporal.ChronoUnit.DAYS.between(diaHoje, item.getDataValidade());

            if(diasParaVencer < 0){
                System.out.println("!! Urgente: "+ item.getNomedoProduto() + " está com a validade vencida há "+ (diasParaVencer * -1) + " dias !!");

            }else if(diasParaVencer <= 7 ){
                System.out.println("!! Atenção: " + item.getNomedoProduto() + " vence em : " + diasParaVencer + " dias !! (Em 1 semana)");

            }else if(diasParaVencer <= 15){
                System.out.println("!! Atenção: " + item.getNomedoProduto() + " vence em : " + diasParaVencer + " dias !! (Em 15 dias)");
            }else if(diasParaVencer <= 30){
                System.out.println("!! Atenção: " + item.getNomedoProduto() + " vence em : " + diasParaVencer + " dias !! (Em 30 dias)");
            }


        }
    }

    // Método que envia o produto direto para o MySQL
    public void salvarNoBanco(Itemestoque item) {
        // O comando SQL com pontos de interrogação (?) onde vão entrar os dados
        String sql = "INSERT INTO item_estoque (nome_produto, quantidade, data_validade, codigo_carga) VALUES (?, ?, ?, ?)";
        // O try-with-resources já fecha a conexão automaticamente no final
        try (java.sql.Connection conexao = ConexaoBanco.obterConexao();
             java.sql.PreparedStatement comando = conexao.prepareStatement(sql)) {
            // Trocamos os '?' pelos dados que estão no objeto Java
            comando.setString(1, item.getNomedoProduto());
            comando.setInt(2, item.getQuantidadeProduto());
            // Para datas, o JDBC precisa converter do LocalDate do Java para o Date do SQL
            comando.setDate(3, java.sql.Date.valueOf(item.getDataValidade()));
            comando.setInt(4, item.getCodigoCarga());
            // Executa o comando lá no MySQL!
            comando.execute();
            System.out.println("✅ Salvo no Banco de Dados: " + item.getNomedoProduto());
        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar o produto no banco.");
            e.printStackTrace();
        }
    }

    public void buscarTodosDoBanco() {
        String sql = "SELECT * FROM item_estoque";
        try (java.sql.Connection conexao = ConexaoBanco.obterConexao();
             java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
             // O executeQuery() devolve a tabela com os resultados
             java.sql.ResultSet tabelaResultado = comando.executeQuery()) {
            System.out.println("\n--- LENDO DADOS DIRETO DO MYSQL ---");

            // O comando .next() pula para a próxima linha da tabela.
            // Enquanto tiver linha, ele repete esse bloco (while):
            while (tabelaResultado.next()) {

                // Pegamos o valor de cada coluna da linha atual
                int id = tabelaResultado.getInt("id");
                String nome = tabelaResultado.getString("nome_produto");
                int qtd = tabelaResultado.getInt("quantidade");
                java.sql.Date validade = tabelaResultado.getDate("data_validade");
                // Mostra na tela
                System.out.println("ID " + id + " | Produto: " + nome + " | Qtd: " + qtd + " | Validade: " + validade);
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar os produtos no banco.");
            e.printStackTrace();
        }
    }
}
