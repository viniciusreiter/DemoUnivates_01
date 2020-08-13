package demo;
class Banco {
     public static void main (String[] args){
     int conta1 = 1;
     String nomeConta1 = "Vinicius";
     float saldoConta1 = 200;
     System.out.println("Conta: "+ conta1 + " - " + nomeConta1 + "(RS " + saldoConta1 + ")");
     
     int conta2 = 2;
     String nomeConta2 = "Xunning";
     float saldoConta2 = 500;
     System.out.println("Conta: "+ conta2 + " - " + nomeConta2 + "(RS " + saldoConta2 + ")");
     
     saldoConta1 -= 100;
     saldoConta2 += 100;
     
    System.out.println("Conta: "+ conta1 + " - " + nomeConta1 + "(RS " + saldoConta1 + ")");
    System.out.println("Conta: "+ conta2 + " - " + nomeConta2 + "(RS " + saldoConta2 + ")");
     
     
    
}
 }
