/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devweb.model;

import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author pauloarthur
 */
public class Extrato {
    
    private Integer idExtrato;
    private String historico;
    private Double valor;
    private Object data;
    private Conta conta;
    private String dt;

    public Extrato(String historico, Double valor, Object data, Conta conta) {
        this.historico = historico;
        this.valor = valor;
        this.data  = data;
        this.conta = conta;
    }

    public Integer getIdExtrato() {
        return idExtrato;
    }

    public void setIdExtrato(Integer idExtrato) {
        this.idExtrato = idExtrato;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    public String getDt() throws SQLException{
        oracle.sql.TIMESTAMP ts = (oracle.sql.TIMESTAMP)getData();
        dt = ts.stringValue();
        return dt;
    }
    
}
