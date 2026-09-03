package ValeMais;

public class Produto {

    private String categoria;
    private String codigo;
    private String descricao;
    private double preco;

    public Produto(String codigo, String descricao, double preco, String categoria) {
        setCategoria(categoria);
        setCodigo(codigo);
        setDescricao(descricao);
        setPreco(preco);

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
