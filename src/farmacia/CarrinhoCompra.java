/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rssobreira
 */
public class CarrinhoCompra extends javax.swing.JFrame {

    DAO dao = new DAO();
    Autenticacao aut = new Autenticacao();
    Double total = 0.0;
    int porcentagem;
    Double desconto = 0.0;

    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }

    private class TableModelCarrinho extends AbstractTableModel {

        private final String[] colunas = new String[]{"Codigo", "Produto", "Preco"};

        private List<Carrinho> carrinhos;

        TableModelCarrinho(List<Carrinho> carrinhos) {
            this.carrinhos = carrinhos;
        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }

        @Override
        public String getColumnName(int col) {
            return colunas[col];
        }

        @Override
        public int getRowCount() {
            return carrinhos.size();
        }

        @Override
        public Object getValueAt(int lin, int col) {
            if (lin > -1 && lin < carrinhos.size()) {
                Carrinho carrinho = carrinhos.get(lin);
                switch (col) {
                    case 0:
                        return carrinho.getCodigo();
                    case 1:
                        return carrinho.getProduto();
                    case 2:
                        return carrinho.getPreco();
                }
            }
            return null;
        }
    }

    private class TableModelConvenio extends AbstractTableModel {

        private final String[] colunas = new String[]{"Nome Convênio", "Porcentagem de Desconto"};

        private List<ConvenioClasse> convenios;

        TableModelConvenio(List<ConvenioClasse> convenios) {
            this.convenios = convenios;
        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }

        @Override
        public String getColumnName(int col) {
            return colunas[col];
        }

        @Override
        public int getRowCount() {
            return convenios.size();
        }

        @Override
        public Object getValueAt(int lin, int col) {
            if (lin > -1 && lin < convenios.size()) {
                ConvenioClasse convenio = convenios.get(lin);
                switch (col) {
                    case 0:
                        return convenio.getNome();
                    case 1:
                        return convenio.getDesconto();
                }
            }
            return null;
        }
    }

    public void mostrar() {
        ResultSet resultset = dao.carrinhoItens();
        List<Carrinho> carrinhos = new ArrayList<>();
        try {
            while (resultset.next()) {

                String codigo = resultset.getString("codigo");
                String nome = resultset.getString("produto");
                double preco = resultset.getDouble("preco");

                Carrinho carrinho = new Carrinho();
                carrinho.setCodigo(Integer.parseInt(codigo));
                carrinho.setProduto(nome);
                carrinho.setPreco(preco);

                carrinhos.add(carrinho);
            }
        } catch (SQLException e) {
            System.out.println("Falha ao realizar a consulta no BD");
        } finally {
            jTable1.setModel(new CarrinhoCompra.TableModelCarrinho(carrinhos));
        }

    }

    public void listarConvenios() {
        ResultSet resultset = dao.listarConvenios();
        List<ConvenioClasse> convenios = new ArrayList<>();
        try {
            while (resultset.next()) {

                String convenio = resultset.getString("convenio");
                int descontos = resultset.getInt("desconto");

                ConvenioClasse conv = new ConvenioClasse();
                conv.setNome(convenio);
                conv.setDesconto(descontos);

                convenios.add(conv);
            }
        } catch (SQLException e) {
            System.out.println("Falha ao realizar a consulta no BD");
        } finally {
            jTable2.setModel(new CarrinhoCompra.TableModelConvenio(convenios));
        }

    }

    public CarrinhoCompra() {
        initComponents();
        jPanel3.setVisible(false);
        jPanel2.setVisible(false);
        jPanel4.setVisible(false);
        mostrar();
        listarConvenios();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnremover = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnconv = new javax.swing.JButton();
        btngerente = new javax.swing.JButton();
        btnfidelidade = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Carrinho");
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "codigo", "produto", "preco"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable1);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 423, 190));

        btnremover.setText("Remover Item");
        btnremover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremoverActionPerformed(evt);
            }
        });
        getContentPane().add(btnremover, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 230, 170, 36));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Carrinho de Compras");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 49, -1, 46));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor Total:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 36, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 278, 170, 60));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Desconto do Gerente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        jButton3.setText("OK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Digite a Porcentagem");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, -1, 85));

        btnconv.setText("Desconto Convenios");
        btnconv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconvActionPerformed(evt);
            }
        });
        getContentPane().add(btnconv, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 11, 170, 30));

        btngerente.setText("Desconto Gerente");
        btngerente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngerenteActionPerformed(evt);
            }
        });
        getContentPane().add(btngerente, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 98, 170, 31));

        btnfidelidade.setText("Desconto CF");
        btnfidelidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfidelidadeActionPerformed(evt);
            }
        });
        getContentPane().add(btnfidelidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 52, 170, 31));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Convenios Parceiros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome Convênio", "Porcentagem Desconto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jButton6.setText("Aplicar Desconto");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(63, 63, 63))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 11, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente Fidelidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel4.setText("Saldo de Pontos:");

        jLabel5.setText("CPF:");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jButton7.setText("Usar Pontos");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel6.setText("Desconto: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 11, 150, 150));

        setSize(new java.awt.Dimension(640, 434));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        mostrar();
        total = 0.0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            total += Double.parseDouble(jTable1.getValueAt(i, 2).toString());
        }
        total = total - ((total / 100) * porcentagem)-desconto;
        
        jLabel1.setText("R$ " + total);

// TODO add your handling code here:
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnremoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremoverActionPerformed
        try {
            int cod = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            dao.removerItemCarrinho(cod);
            total = 0.0;
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                total += Double.parseDouble(jTable1.getValueAt(i, 2).toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Adicione produtos ao carrinho, para poder remove-los");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnremoverActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged

    }//GEN-LAST:event_formWindowStateChanged

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        jTextField1.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1FocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            porcentagem = Integer.parseInt(jTextField1.getText());
            if (porcentagem <= 50) {
                total -= (total / 100) * porcentagem;
                jLabel1.setText("R$ " + total);
                jPanel2.setVisible(false);
                btngerente.setText("Desconto Incluido");
                btngerente.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Desconto Invalido");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Digite a porcentagem do desconto");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btngerenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngerenteActionPerformed
        if (!btngerente.getText().equals("Desconto Incluido")) {
            jPanel2.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "O desconto so pode ser aplicado uma vez");
            total = total - ((total / 100) * porcentagem);
            jLabel1.setText("R$ " + total);
            
        }
        
       
    }//GEN-LAST:event_btngerenteActionPerformed

    private void btnconvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconvActionPerformed
        jPanel3.setVisible(true);
        this.setSize(920, 430);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnconvActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            porcentagem = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
            total -= (total / 100) * porcentagem;
            jLabel1.setText("R$ " + total);
            jPanel3.setVisible(false);
            this.setSize(650, 430);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione o convenio");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnfidelidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfidelidadeActionPerformed
        jPanel4.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnfidelidadeActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        String cpf = jTextField2.getText();
        ResultSet prod = dao.getSaldoPontos(cpf);
        try {
            while (prod.next()) {
                jLabel4.setText("Saldo de Pontos: " + prod.getString("pontos"));
                desconto = Double.parseDouble(prod.getString("pontos")) * 0.10;
                jLabel6.setText("Desconto: R$ " + desconto);
            }
        } catch (SQLException e) {

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (!jButton7.getText().equalsIgnoreCase("Ja Aplicado")) {
            try {
                if (desconto > total) {
                    JOptionPane.showMessageDialog(null, "O desconto não pode ser maior que o valor final");
                    desconto = 0.0;
                } else {

                    total = total - desconto;
                    jLabel1.setText("R$ " + total);
                    jButton7.setText("Ja Aplicado");
                    btnfidelidade.setEnabled(false);
                    jButton7.setEnabled(false);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Verifique os campos");
            }
        }else{
              JOptionPane.showMessageDialog(null,"Desconto Já Aplicado");
                    total = total - desconto;
                    jLabel1.setText("R$ " + total);
                }

       
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
         * @param args the command line arguments
         */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CarrinhoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarrinhoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarrinhoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarrinhoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CarrinhoCompra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnconv;
    private javax.swing.JButton btnfidelidade;
    private javax.swing.JButton btngerente;
    private javax.swing.JButton btnremover;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
