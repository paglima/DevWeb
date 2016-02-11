/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devweb.controller;

import br.com.devweb.model.Conta;
import br.com.devweb.model.Extrato;
import br.com.devweb.model.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pauloarthur
 */
@WebServlet(name = "SaqueServlet", urlPatterns = {"/saca"})
public class SaqueServlet extends GenericServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
        Double valorSaque = null;
        try{
            valorSaque = Double.valueOf( request.getParameter("valorSaque") );
        }catch(Exception e){
            request.setAttribute("mensagemErro", "Preencha o campo corretamente");
            request.getSession().setAttribute("usuario",usuario);
            request.getServletContext().getRequestDispatcher("/saque.jsp").forward(request, response);
            return;
        }
        
        try {
             Usuario usuarioBanco = usuarioDao.findByConta(usuario.getConta().getNumero());
             Conta conta = usuarioBanco.getConta();
             conta.setSaldo(conta.getSaldo() - valorSaque);
             
             contaDao.updateConta(conta);
             
             Extrato extrato = new Extrato("Sacou "+ valorSaque +" R$ " , conta.getSaldo(), getTimestamp(), conta);
             extradoDAO.save(extrato);
             
             request.getSession().setAttribute("usuario",usuarioBanco);
             request.setAttribute("mensagemSucesso", "Saque feito com sucesso!");
             
             request.getServletContext().getRequestDispatcher("/saque.jsp").forward(request, response);
         } catch (Exception ex) {
             Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        try {
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DepositoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
