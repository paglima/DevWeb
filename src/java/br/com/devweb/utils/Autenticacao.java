/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devweb.utils;

import br.com.devweb.model.Usuario;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author pauloarthur
 */
public class Autenticacao {
    
    public static boolean autenticaUsuario(HttpServletRequest request, Usuario usuario){
        String senha       = request.getParameter("senha");
        return usuario.getSenha().equals(senha);
    }
    
}
