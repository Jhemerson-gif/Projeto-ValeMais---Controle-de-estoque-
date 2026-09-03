package ValeMais;

import java.time.LocalDate;

public class Itemestoque {
    private String nomedoProduto;
    private int quantidadeProduto;
    private int entradaProduto;
    private LocalDate dataValidade;
    private LocalDate dataEntrada;
    private int codigoCarga;

    public Itemestoque(String nomedoProduto, int quantidadeProduto, LocalDate dataValidade, int codigoCarga) {
        setNomedoProduto(nomedoProduto);
        setQuantidadeProduto(quantidadeProduto);
        setEntradaProduto(entradaProduto);
        this.dataValidade = dataValidade;
        this.dataEntrada = dataEntrada;
        setCodigoCarga(codigoCarga);

    }

    public boolean darBaixa(int quantidadeVendida) {
        if(quantidadeVendida <= quantidadeProduto){
            this.quantidadeProduto -= quantidadeVendida;
            return true;
        }
        return false;
    }





    public int getEntradaProduto() {
        return entradaProduto;
    }

    public void setEntradaProduto(int entradaProduto) {
        this.entradaProduto = entradaProduto;
    }

    public String getNomedoProduto() {
        return nomedoProduto;
    }

    public void setNomedoProduto(String nomedoProduto) {
        this.nomedoProduto = nomedoProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }


    public int getCodigoCarga() {
        return codigoCarga;
    }

    public void setCodigoCarga(int codigoCarga) {
        this.codigoCarga = codigoCarga;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
}
