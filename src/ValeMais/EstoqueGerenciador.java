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
            System.out.println("Marca: "+ item.getNomeMarcaProduto() +
                    "Produto: "+ item.getNomedoProduto() +
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
    public int salvarProduto(Produto produto) {

        String sql = "INSERT INTO produtos (marca, nome_produto) VALUES (?,?)";

        try (java.sql.Connection conexao = ConexaoBanco.obterConexao();
             java.sql.PreparedStatement comando = conexao.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            comando.setString(1, produto.getMarca());
            comando.setString(2, produto.getNomeProduto());
            comando.executeUpdate();

            java.sql.ResultSet chaveGerada = comando.getGeneratedKeys();
            if (chaveGerada.next()) {
                int idGerado = chaveGerada.getInt(1);
                produto.setId(idGerado);
                System.out.println("✅ Produto cadastrado! ID: " + idGerado);
                return idGerado;
            }


        }catch (java.sql.SQLIntegrityConstraintViolationException e){
                System.out.println("⚠️ Esse produto já existe no catálogo!");

        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar o produto no banco.");
            e.printStackTrace();
        }

        return -1;
    }

    public void salvarLote(int produtoId, int quantidade, java.time.LocalDate dataValidade, int codigoCarga) {
        String sql = "INSERT INTO lotes_estoque (produto_id, quantidade, data_validade, codigo_carga) VALUES (?,?,?,?)";

        try (java.sql.Connection conexao = ConexaoBanco.obterConexao();
             java.sql.PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, produtoId);
            comando.setInt(2, quantidade);
            comando.setDate(3, java.sql.Date.valueOf(dataValidade));
            comando.setInt(4, codigoCarga);
            comando.executeUpdate();


            System.out.println("✅ Lote salvo no estoque com sucesso!");

        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar lote.");
            e.printStackTrace();

        }
    }



    public void buscarTodosDoBanco(){
            String sql = "SELECT p.id, p.marca, p.nome_produto, " +
                    "l.quantidade, l.data_validade, l.codigo_carga " +
                    "FROM produtos p " +
                    "JOIN lotes_estoque l ON p.id = l.produto_id " +
                    "ORDER BY l.data_validade";
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
                String marca = tabelaResultado.getString("marca");
                String nome = tabelaResultado.getString("nome_produto");
                int qtd = tabelaResultado.getInt("quantidade");
                java.sql.Date validade = tabelaResultado.getDate("data_validade");
                int carga = tabelaResultado.getInt("codigo_carga");
                // Mostra na tela
                System.out.printf("%-5d %-15s %-20s %-8d %-15s %-10d%n",
                        id, marca, nome, qtd, validade, carga);
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar os produtos no banco.");
            e.printStackTrace();
        }

    }
}
