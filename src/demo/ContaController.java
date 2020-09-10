
package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
        public boolean debito(int id_conta, float valor){
            Conexao.abreConexao();
            PreparedStatement stmt = null;
            
            try {
                stmt = Conexao.con.prepareStatement(" INSERT INTO movimentos VALUES(DEFAULT,?,?,?,?)");
                stmt.setInt(1, objConta.getNome());
                stmt.setFloat(2, valor);
                
                stmt.executeUpdate();
                return true;
                
               }catch (SQLException ex){
                   System.out.println(exMessage());
                   return false;
               }finally{
                Conexao.closeConnection(con,stmt);
                
               }
            
            
        this.saldo += valor;
      
    }
    
        public boolean credito(float valor){
        if(this.saldo + cheque_especial >= valor){
            this.saldo -= valor;
            return true;
        }else{
            return false;
        }
      
    }
}

