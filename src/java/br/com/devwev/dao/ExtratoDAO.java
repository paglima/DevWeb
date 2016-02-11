/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devwev.dao;

import br.com.devweb.model.Conta;
import br.com.devweb.model.Extrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pauloarthur
 */
public class ExtratoDAO extends GenericDAO{

    public ExtratoDAO(Connection connection) {
        super.connection = connection;
    }
    
    public void save(Extrato extrato) throws Exception{
        PreparedStatement sql = connection.prepareStatement("insert into extrato values(SEQ_EXTRATO.nextval,?,?,?,?)");
        sql.setString(1,extrato.getHistorico());
        sql.setDouble(2, extrato.getValor());
        sql.setObject(3, extrato.getData());
        sql.setObject(4, extrato.getConta().getNumero());
        sql.executeUpdate();
    }
    
    public List<Extrato> listaExtratos(Conta conta) throws Exception{
        PreparedStatement sql = connection.prepareStatement("select * from extrato where id_conta = ? order by dt_data_acao desc");
        sql.setString(1, conta.getNumero());
        
        ResultSet rs = sql.executeQuery();
        List<Extrato> extratos = new ArrayList<>();
        
        while (rs.next()) {
            Extrato ex = new Extrato(
                                rs.getString("nm_historico"), 
                                rs.getDouble("vl_valor"), 
                                rs.getObject("dt_data_acao"), 
                                null);
            
            extratos.add(ex);
        }
        return extratos;
    }
    
    
    
}
