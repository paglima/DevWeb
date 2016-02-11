/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devweb.controller;

import br.com.devwev.dao.ContaDAO;
import br.com.devwev.dao.ExtratoDAO;
import br.com.devwev.dao.InvestimentoDAO;
import br.com.devwev.dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@WebServlet(name = "GenericServlet", urlPatterns = {"/GenericServlet"})
public class GenericServlet extends HttpServlet {

    
    static{
        try {
            Class.forName ("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;
    protected UsuarioDAO usuarioDao;
    protected ContaDAO contaDao;
    protected ExtratoDAO extradoDAO;
    protected InvestimentoDAO investimentoDAO;
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        try {
            if(connection == null){
                connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","DEV_WEB_ADMIN", "DEV_WEB_ADMIN");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        usuarioDao = new UsuarioDAO(getConnection());
        contaDao = new ContaDAO(getConnection());
        extradoDAO = new ExtratoDAO(getConnection());
        investimentoDAO = new InvestimentoDAO(getConnection());
    }
    
    public String getContextPath(){
       return getServletContext().getContextPath();
    }
    
    public Connection getConnection(){
        return connection;
    }

    public Object getTimestamp() {
        java.util.Date date = new java.util.Date();
        Object param = new java.sql.Timestamp(date.getTime());
        return param;
    }
}
