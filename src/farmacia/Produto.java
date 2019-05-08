
package farmacia;


public class Produto {
    private int codigo;
    private String produto, categoria,status;
    private double preco;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Produto(int codigo, String produto, double preco) {
        this.codigo = codigo;
        this.produto = produto;
        this.preco = preco;
    }

    public Produto() {
    }

    
    public int getCodigo() {
        return codigo;
    }




    public String getCategoria() {
        return categoria;
    }

 
    public double getPreco() {
        return preco;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

   
    public String getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    
}
