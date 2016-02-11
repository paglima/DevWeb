/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devwev.dao;

import br.com.devweb.model.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author pauloarthur
 */
public class ContaDAO extends GenericDAO{
    
    
    public ContaDAO(Connection connection){
        super.connection = connection;
    }
    
    public Conta findById(String numeroConta) throws Exception{
        PreparedStatement sql = connection.prepareStatement("select * from conta where id_conta = ?");
        sql.setString(1, numeroConta);
        
        ResultSet rs = sql.executeQuery();
        
        Conta conta = null;
        while(rs.next()){
            conta = Conta.getContaByTipo(rs.getString("en_tipo"));
            conta.setNumero(numeroConta);
            conta.setSaldo(rs.getDouble("vl_saldo"));
        }
        return conta;
    }
    
    public void updateConta(Conta conta) throws Exception{
        PreparedStatement sql = connection.prepareStatement("update conta set vl_saldo=? where id_conta=?");
        sql.setDouble(1, conta.getSaldo());
        sql.setString(2, conta.getNumero());
        sql.executeUpdate();
    }
    
    
}
