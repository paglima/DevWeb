/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devweb.enums;

/**
 *
 * @author pauloarthur
 */
public enum TipoContaEnum {
    
    CORRENTE("corrente"),
    POUPANCA("poupanca");
    
    private String tipo;

    TipoContaEnum(String tipo) {
        this.tipo = tipo; 
    }
    
    public String getTipo(){
        return tipo;
    }
}
