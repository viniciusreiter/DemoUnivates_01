
package demo;

public class BancoObjetos {
public static void main(String[] args) {
    
    Conta conta1 = new Conta();
    Conta conta2 = new Conta();
    
        conta1.setConta(1);
        conta1.setNome("Vinicius");
        conta1.setSaldo(200);

        conta2.setConta(2);
        conta2.setNome("Xunning");
        conta2.setSaldo(500);
        

        System.out.println(conta1.imprimeConta()); // imprimir todas as infomaçoes na linha
        
        conta1.saque(50);
        conta1.tranferencia(100, conta2);
        
        System.out.println(conta1.imprimeConta());
        System.out.println(conta2.imprimeConta());
        
        
        
        
        
        
        
        
        
        
        
        System.out.println(conta2.imprimeConta());

        

    }

}

