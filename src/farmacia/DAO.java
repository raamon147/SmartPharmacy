package farmacia;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class DAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public void inserir(int codigo, String produto, double preco, String categoria,String status) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            String inserir = "INSERT INTO produto (codigo, produto, preco, categoria,status) VALUES (?, ?, ?, ?,?)";
            pstmt = conn.prepareStatement(inserir);
            pstmt.setInt(1, codigo);
            pstmt.setString(2, produto);
            pstmt.setDouble(3, preco);
            pstmt.setString(4, categoria);
            pstmt.setString(5, status);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Adicionado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha em Inserir no DB: " + e.getMessage());
        }

    }

    public void alterar(String produto, double preco, String categoria, int codigo) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            String alterar = "UPDATE produto SET produto = ?, preco = ?, categoria = ?  WHERE codigo = ?;";
            pstmt = conn.prepareStatement(alterar);
            pstmt.setString(1, produto);
            pstmt.setDouble(2, preco);
            pstmt.setString(3, categoria);
            pstmt.setInt(4, codigo);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Alterado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha na alteração: " + e.getMessage());
        }
    }

    public void removerProduto(int codigo) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        int resposta = JOptionPane.showConfirmDialog(null,
                "Deseja realmente excluir o produto? ", "Mensagem", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            try {
                String remover = "DELETE FROM produto WHERE codigo = ?;";
                pstmt = conn.prepareStatement(remover);
                pstmt.setInt(1, codigo);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Removido com Sucesso");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Falha em Remover Produto: " + e.getMessage());
            }
        } else {
            System.out.println("Cancelado");
        }

    }

    public ResultSet buscartexto(String produto) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM produto WHERE produto like ?");
            pstmt.setString(1, "%" + produto + "%");
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "codigo inexistente");
            return null;
        }
    }

    public ResultSet valorCarrinho() {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("select format((sum(preco)),2)as soma from carrinho");
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }

    public ResultSet buscarcodigo(int codigo) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM produto WHERE codigo = ?");
            pstmt.setInt(1, codigo);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "codigo inexistente");
            return null;
        }
    }

    public ResultSet buscarvendedor() {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM vendedor");
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }

    public void cadastrarVendedor(int matricula, String nome) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            String inserir = "INSERT INTO vendedor (matricula, Nome) VALUES (?, ?)";
            pstmt = conn.prepareStatement(inserir);
            pstmt.setInt(1, matricula);
            pstmt.setString(2, nome);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vendedor Cadastrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha em Inserir no DB: " + e.getMessage());
        }
    }

    public void limparCarrinho() {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            String remover = "DELETE FROM carrinho;";
            pstmt = conn.prepareStatement(remover);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha em Remover Produto: " + e.getMessage());
        }
    }

    public void limparAplicacao() {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            String remover = "DELETE FROM aplicacao;";
            pstmt = conn.prepareStatement(remover);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha: " + e.getMessage());
        }
    }

    public ResultSet janelaPesquisa() {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("SELECT * from produto");
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "codigo inexistente");
            return null;
        }
    }

    public ResultSet produtoMaisCaro() {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("select produto, preco from produto where codigo in (select codigo from produto where preco = (select max(preco) from produto))");
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {

            return null;
        }
    }

    public ResultSet carrinhoItens() {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("SELECT  * FROM carrinho");
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "codigo inexistente");
            return null;
        }
    }

    public ResultSet buscarPreco(String produto) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("SELECT format((preco),2) as valor FROM produto where produto = ?");
            pstmt.setString(1, produto);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "codigo inexistente");
            return null;
        }
    }

    public ResultSet buscarSenha(int matricula, int senha) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM gerente WHERE matricula = ? AND senha = ?");
            pstmt.setInt(1, matricula);
            pstmt.setInt(2, senha);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "Dados Invalidos");
            return null;
        }
    }

    public void cadastrarGerente(int matricula, int senha) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            String inserir = "INSERT INTO gerente (matricula, senha) VALUES (?, ?)";
            pstmt = conn.prepareStatement(inserir);
            pstmt.setInt(1, matricula);
            pstmt.setInt(2, senha);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Gerente Cadastrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar Gerente " + e.getMessage());
        }

    }

    public void removerGerente(int matricula) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        int resposta = JOptionPane.showConfirmDialog(null,
                "Deseja realmente remover o gerente do sistema? ", "Mensagem", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            try {
                String remover = "DELETE FROM gerente WHERE matricula = ?;";
                pstmt = conn.prepareStatement(remover);
                pstmt.setInt(1, matricula);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Removido com Sucesso");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Falha em Remover Gerente: " + e.getMessage());
            }
        } else {
            System.out.println("Cancelado");
        }

    }

    public void removerVendedor(String nome) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        int resposta = JOptionPane.showConfirmDialog(null,
                "Deseja realmente excluir o vendedor do sistema? ", "Mensagem", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            try {
                String remover = "DELETE FROM vendedor WHERE nome = ?;";
                pstmt = conn.prepareStatement(remover);
                pstmt.setString(1, nome);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Removido com Sucesso");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Falha em Remover Vendedor: " + e.getMessage());
            }
        } else {
            System.out.println("Cancelado");
        }

    }

    public ResultSet filtrarCategoria(String produto, String categoria) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM produto WHERE produto like ? and categoria = ?");
            pstmt.setString(1, "%" + produto + "%");
            pstmt.setString(2, categoria);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "codigo inexistente");
            return null;
        }
    }

    public void inserirCarrinho(int codigo, String produto, double preco) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            String inserir = "INSERT INTO carrinho (codigo, produto, preco) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(inserir);
            pstmt.setInt(1, codigo);
            pstmt.setString(2, produto);
            pstmt.setDouble(3, preco);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Adicionado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha em Inserir no DB: " + e.getMessage());
        }

    }

    public void removerItemCarrinho(int cod) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        int resposta = JOptionPane.showConfirmDialog(null,
                "Deseja realmente excluir o item do carrinho? ", "Mensagem", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            try {
                String remover = "DELETE FROM carrinho WHERE codigo = ?;";
                pstmt = conn.prepareStatement(remover);
                pstmt.setInt(1, cod);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Removido com Sucesso");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Falha em Remover Item: " + e.getMessage());
            }
        } else {
            System.out.println("Cancelado");
        }
    }

    public void cadastrarCliente(String cpf, String nome, String nascimento, String endereco) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            String inserir = "INSERT INTO clientes (cpf, nome, nascimento, endereco) VALUES (?, ?, ?,?)";
            pstmt = conn.prepareStatement(inserir);
            pstmt.setString(1, cpf);
            pstmt.setString(2, nome);
            pstmt.setString(3, nascimento);
            pstmt.setString(4, endereco);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha em Inserir no DB: " + e.getMessage());
        }

    }

    public ResultSet buscarCliente(String cpf) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM clientes WHERE cpf = ?");
            pstmt.setString(1, cpf);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            int resposta = JOptionPane.showConfirmDialog(null,
                    "Cliente não cadastrado, deseja cadastrar? ", "Mensagem", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                CadastrarCliente cadc = new CadastrarCliente();
                cadc.setVisible(true);
            } else {
                System.out.println("Cancelado");
            }
            return null;
        }
    }

    public void cadastrarAplicacao(String cpf, String nome, String nascimento, String endereco, String tipo, String medicamento, String data) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            String inserir = "INSERT INTO aplicacao (cpf, nome, nascimento, endereco,tipo,medicamento,data) VALUES (?, ?, ?,?,?,?,?)";
            pstmt = conn.prepareStatement(inserir);
            pstmt.setString(1, cpf);
            pstmt.setString(2, nome);
            pstmt.setString(3, nascimento);
            pstmt.setString(4, endereco);
            pstmt.setString(5, tipo);
            pstmt.setString(6, medicamento);
            pstmt.setString(7, data);
            pstmt.executeUpdate();
            int resposta = JOptionPane.showConfirmDialog(null,
                    "Aplicação cadastrada, deseja imprimir? ", "Mensagem", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                Imprimir imp = new Imprimir();
                imp.setCpf(cpf);
                imp.setVisible(true);
            } else {
                System.out.println("Cancelado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha em Inserir no DB: " + e.getMessage());
        }

    }

    public ResultSet imprimirAplicacao(String cpf) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM aplicacao WHERE cpf = ?");
            pstmt.setString(1, cpf);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }

    public void cadastrarConvenio(String convenio, int desconto) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            String inserir = "INSERT INTO convenios (convenio,desconto) VALUES (?, ?)";
            pstmt = conn.prepareStatement(inserir);
            pstmt.setString(1, convenio);
            pstmt.setInt(2, desconto);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Convênio cadastrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha em Inserir no DB: " + e.getMessage());
        }

    }

    public ResultSet listarConvenios() {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM convenios");
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }
        public void alterarStatus(int codigo,String status) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            String alterar = "UPDATE produto SET status = ? WHERE codigo = ?;";
            pstmt = conn.prepareStatement(alterar);
            pstmt.setString(1, status);
            pstmt.setInt(2, codigo);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "O status foi alterado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha na alteração: " + e.getMessage());
        }
    }
    public ResultSet getSaldoPontos(String cpf) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();
        try {
            pstmt = conn.prepareStatement("SELECT pontos FROM clientes WHERE cpf = ?");
            pstmt.setString(1, cpf);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }

}
