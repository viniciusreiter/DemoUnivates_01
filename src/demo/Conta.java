
package demo;


public class Conta {
    
    private int conta;
    private String nome;
    private float saldo;

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
    
    public void saque(float valorSaque){
        this.saldo -= valorSaque;
      
    }
    
    public void tranferencia (float valorSaque, Conta contaDestino){
        this.saldo -= valorSaque;
        float saldoDestino = contaDestino.getSaldo() + valorSaque;
        contaDestino.setSaldo(saldoDestino);
    }
    
    public String imprimeConta(){
        return "Conta: " + this.conta + " - " + this.nome + " - Saldo: R$ " + this.saldo;
    }
}
