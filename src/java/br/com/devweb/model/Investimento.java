/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devweb.model;

/**
 *
 * @author pauloarthur
 */
public class Investimento {
    
    private Integer idInvestimento;
    private Double juros;
    private Double valorAplicado;
    private Object data;

    public Investimento(Integer idInvestimento, Double juros, Double valorAplicado) {
        this.idInvestimento = idInvestimento;
        this.juros = juros;
        this.valorAplicado = valorAplicado;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
            
    

    public Integer getIdInvestimento() {
        return idInvestimento;
    }

    public void setIdInvestimento(Integer idInvestimento) {
        this.idInvestimento = idInvestimento;
    }

    public Double getJuros() {
        return juros;
    }

    public void setJuros(Double juros) {
        this.juros = juros;
    }

    public Double getValorAplicado() {
        return valorAplicado;
    }

    public void setValorAplicado(Double valorAplicado) {
        this.valorAplicado = valorAplicado;
    }
    
    
}
