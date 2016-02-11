/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devwev.dao;

import br.com.devweb.model.Conta;
import br.com.devweb.model.ContaCorrente;
import br.com.devweb.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author pauloarthur
 */
public class UsuarioDAO extends GenericDAO{
    
    public UsuarioDAO(Connection connection){
        super.connection = connection;
    }
    
    public Usuario findByConta(String numeroConta) throws Exception{
        PreparedStatement sql = connection.
                                    prepareStatement("select * from usuario us inner join conta ct on us.id_conta = ct.id_conta where ct.id_conta=?");
        sql.setString(1, numeroConta);
        
        ResultSet rs = sql.executeQuery();
        
        Usuario usuario = null;
        Conta conta = null;
        while(rs.next()){
            usuario = new Usuario();
            conta = new ContaCorrente();
            
            usuario.setCpf(rs.getString("id_usuario"));
            usuario.setNome(rs.getString("nm_usuario"));
            usuario.setSenha(rs.getString("pw_senha"));
            
            conta.setNumero(numeroConta);
            conta.setSaldo(rs.getDouble("vl_saldo"));
            usuario.setConta(conta);
        }
        return usuario;
    }
    
}
