package br.com.devweb.model;

import br.com.devweb.enums.TipoContaEnum;

public abstract class Conta {
    
    private String numero;
    private Double saldo;
    

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
    public static Conta getContaByTipo(String tipo){
        Conta conta;
        if(tipo.equals(TipoContaEnum.CORRENTE.getTipo())){
            conta = new ContaCorrente();
        }else{
            conta = new ContaPoupanca();
        }
        return conta;
    }
}
