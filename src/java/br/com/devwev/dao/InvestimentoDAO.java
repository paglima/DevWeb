/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devwev.dao;

import br.com.devweb.model.Extrato;
import br.com.devweb.model.Investimento;
import br.com.devweb.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pauloarthur
 */
public class InvestimentoDAO extends GenericDAO{

    public InvestimentoDAO(Connection connection) {
        super.connection = connection;
    }
    
    
    public Investimento getInvestimento(Usuario usuario) throws Exception{
        PreparedStatement sql = connection.prepareStatement("select * from usuario us inner join investimento iv on us.id_investimento = iv.id_investimento where us.id_usuario = ?");
        sql.setString(1, usuario.getCpf());
        
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
            Investimento iv = new Investimento(rs.getInt("id_investimento"),
                                               rs.getDouble("vl_juros"),
                                               rs.getDouble("vl_aplicado"));
            iv.setData(rs.getObject("dt_inicio"));
            
            return iv;
        }
        
        return null;
    }
    
    public void updateInvestimento(Investimento iv) throws SQLException{
        PreparedStatement sql = connection.prepareStatement("update investimento set vl_aplicado = ? where id_investimento = ?");
        sql.setDouble(1, iv.getValorAplicado());
        sql.setInt(2, iv.getIdInvestimento());
        sql.executeUpdate();
    }
    
}
