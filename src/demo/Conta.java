
package demo;


public class Conta {
    
    private int conta;
    private String nome;
    private float saldo;
    private float cheque;

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    public void debito(float valor){
        this.saldo += valor;
      
    }
    
        public boolean credito(float valor){
        if(this.saldo + cheque >= valor){
            this.saldo -= valor;
            return true;
        }else{
            return false;
        }
      
    }

    public float getCheque() {
        return cheque;
    }

    public void setCheque(float cheque) {
        this.cheque = cheque;
    }
        
    
    public String imprimeConta(){
        return "Conta: " + this.conta + " - " + this.nome + " - Saldo: R$ " + this.saldo;
    }
}
