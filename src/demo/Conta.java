
package demo;


public class Conta {
    
    private int id;
    private String conta;
    private String nome;
    private float saldo;
    private float cheque_especial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public float getCheque_especial() {
        return cheque_especial;
    }

    public void setCheque_especial(float cheque_especial) {
        this.cheque_especial = cheque_especial;
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
    


        
    
    public String imprimeConta(){
        return "Conta: " + this.conta + " - " + this.nome + " - Saldo: R$ " + this.saldo;
    }
}
