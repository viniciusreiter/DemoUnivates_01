
package demo;

import static demo.Conexao.getConnection;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Vinicius
 */
public class ContaController {
    
    public Conta buscar(int id){
        
        try{
            Conexao.abreConexao();
            ResultSet rs = null;
            
            String sql = " SELECT * FROM contas WHERE id = " + id;
            rs = Conexao.stmt.executeQuery(sql);
                    
                Conta objeto = new Conta();
            
                   if(rs.next()){
                       objeto.setId(rs.getInt("id"));
                       objeto.setConta(rs.getString("conta"));
                       objeto.setNome(rs.getString("nome"));
                       objeto.setCheque_especial(rs.getFloat("cheque_especial"));
                       
                   }
                   
                   return objeto;
            
        }catch(SQLException ex){
            return null;
        }
    }
    
    public Conta validarLogin(String conta, String senha){
        
        try{
            Conexao.abreConexao();
            ResultSet rs = null;
            
            String sql = " SELECT * FROM contas WHERE conta = '"+ conta + "' AND senha = md5('"+ senha +"') ";
            rs = Conexao.stmt.executeQuery(sql);
                    
                Conta objeto = new Conta();
            
                   if(rs.next()){
                       objeto.setId(rs.getInt("id"));
                       objeto.setConta(rs.getString("conta"));
                       objeto.setNome(rs.getString("nome"));
                       objeto.setCheque_especial(rs.getFloat("cheque_especial"));
                       
                       return objeto;
                   }
                   
                   return null;
            
        }catch(SQLException ex){
            return null;
        }
    }
    
//    public Conta validarLogin(String conta, String senha){
//        
//        try{
//            Conexao.abreConexao();
//            ResultSet rs = null;
//            
//            String sql = " SELECT * FROM contas WHERE id = " + id;
//            rs = Conexao.stmt.executeQuery(sql);
//                    
//                Conta objeto = new Conta();
//            
//                   if(rs.next()){
//                       objeto.setId(rs.getInt("id"));
//                       objeto.setConta(rs.getString("conta"));
//                       objeto.setNome(rs.getString("nome"));
//                       objeto.setCheque_especial(rs.getFloat("cheque_especial"));
//                       
//                   }
//                   
//                   return objeto;
//            
//        }catch(SQLException ex){
//            return null;
//        }
//    }
        
    public float buscarSaldo(int idConta){
        
        try{
            Conexao.abreConexao();
            ResultSet rs = null;
            
            String sql = " SELECT SUM (CASE tipo_operacao WHEN 'D' THEN valor ELSE -valor END) as saldo " +
                    "FROM movimentos WHERE id_conta = " + idConta;
            rs = Conexao.stmt.executeQuery(sql);
                    

            
                   if(rs.next()){
                       
                       return rs.getFloat("saldo");
                   }
                   
            return 0;
            
        }catch(SQLException ex){
            return 0;
        }
    }
    
    public boolean debito(Conta conta, float valor){
            Conexao.abreConexao();
            Connection con = getConnection();
            PreparedStatement stmt = null;
            
            try {
                stmt = con.prepareStatement(" INSERT INTO movimentos VALUES(DEFAULT, ?, ?, NOW(), 'D') ");
                stmt.setInt(1, conta.getId());
                stmt.setFloat(2, valor);
                
                stmt.executeUpdate();
                
                return true;
                
               }catch (SQLException ex){
                   System.out.println(ex.getMessage());
                   return false;
               }finally{
                Conexao.closeConnection(con,stmt);
                
               }
                 
    }
        
    public boolean credito(Conta conta, float valor){
        //se o saldo for maior ou igual ao valor requerido vai passar batido aki
            float temp = conta.getSaldo() + conta.getCheque_especial();
            if (temp < valor){
               return false;
           }     
            
            Conexao.abreConexao();
            Connection con = getConnection();
            PreparedStatement stmt = null;
            
            try {
                stmt = con.prepareStatement(" INSERT INTO movimentos VALUES(DEFAULT, ?, ?, NOW(), 'C') ");
                stmt.setInt(1, conta.getId());
                stmt.setFloat(2, valor);
                
                stmt.executeUpdate();
                
                return true;
                
               }catch (SQLException ex){
                   System.out.println(ex.getMessage());
                   return false;
               }finally{
                Conexao.closeConnection(con,stmt);
                
               }
                 
    }
    
    public void preencher(JTable jtbTabela) {

        Conexao.abreConexao();
        
        Vector<String> cabecalhos = new Vector<String>();
        Vector dadosTabela = new Vector(); //receber os dados do banco
        
        cabecalhos.add("Código");
        cabecalhos.add("Conta");
        cabecalhos.add("Nome");
        cabecalhos.add("Excluir");
             
        ResultSet result = null;
        
        try {

            String SQL = "";
            SQL = " SELECT id, conta, nome ";
            SQL += " FROM contas ";
            SQL += " ORDER BY nome ";
            
            result = Conexao.stmt.executeQuery(SQL);
            
            Vector<Object> linha;
            while(result.next()) {
                linha = new Vector<Object>();
                
                linha.add(result.getInt(1));
                linha.add(result.getString(2));
                linha.add(result.getString(3));
                linha.add("");
                
                dadosTabela.add(linha);
            }
            
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }

        jtbTabela.setModel(new DefaultTableModel(dadosTabela, cabecalhos) {

            @Override
            public boolean isCellEditable(int row, int column) {
              return false;
            }
            // permite seleção de apenas uma linha da tabela
        });

        // permite seleção de apenas uma linha da tabela
        jtbTabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i <= 2; i++) {
            column = jtbTabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0: //id
                    column.setPreferredWidth(80);
                    break;
                case 1: //conta
                    column.setPreferredWidth(80);
                    break;
                case 2: //nome
                    column.setPreferredWidth(250);
                    break;
                case 3:
                    column.setPreferredWidth(10);
                    break;
            }
        }
        
        jtbTabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) 
            {
                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
                if (row % 2 == 0) {
                    setBackground(Color.LIGHT_GRAY);
                } else {
                    setBackground(Color.GRAY);
                }
                
                return this;
            }
        });
    }
    
//        public boolean credito(float valor){
//        if(this.saldo + cheque_especial >= valor){
//            this.saldo -= valor;
//            return true;
//        }else{
//            return false;
//        }
//      
//    }
}

