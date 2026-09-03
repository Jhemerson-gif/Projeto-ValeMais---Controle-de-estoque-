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
}
