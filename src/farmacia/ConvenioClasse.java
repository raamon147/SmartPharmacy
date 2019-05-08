/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

/**
 *
 * @author rssobreira
 */
public class ConvenioClasse {
  private String nome;
  private int desconto;

    public ConvenioClasse(int desconto) {
        this.desconto = desconto;
    }

    public ConvenioClasse() {
    }
    

  
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public int getDesconto() {
        return desconto;
    }

    public String getNome() {
        return nome;
    }
}
