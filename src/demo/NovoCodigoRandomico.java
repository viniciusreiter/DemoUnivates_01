/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.util.Random;

/**
 *
 * @author Vinicius
 */
public class NovoCodigoRandomico {
    
public static void main(String[] args) {
//instância um objeto da classe Random usando o construtor básico
//Exige importação da classe java.util.Random
Random gerador = new Random();
 
//imprime sequência de 10 números inteiros aleatórios entre 0 e 5
    for (int i = 0; i < 10; i++) {
 System.out.println(gerador.nextInt(5) + 1); // o + 1 é para não pegar o 0
}
    }
}
   
