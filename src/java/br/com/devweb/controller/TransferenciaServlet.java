/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devweb.controller;

import br.com.devweb.model.Conta;
import br.com.devweb.model.Extrato;
import br.com.devweb.model.Usuario;
import br.com.devwev.dao.ContaDAO;
import br.com.devwev.dao.UsuarioDAO;
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
@WebServlet(name = "TransferenciaServlet", urlPatterns = {"/transfere"})
public class TransferenciaServlet extends GenericServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
        Double valorTransferencia = null;
        String contaDestino = null;
        
        try{
            valorTransferencia = Double.valueOf( request.getParameter("valorTransferencia") );
            contaDestino = request.getParameter("contaDestino");
        }catch(Exception e){
            request.setAttribute("mensagemErro", "Preencha o(s) campo(s) corretamente");
            request.getSession().setAttribute("usuario",usuario);
            request.getServletContext().getRequestDispatcher("/transferencia.jsp").forward(request, response);
            return;
        }
        
        try {
             Usuario usuarioDest= usuarioDao.findByConta(contaDestino);
             Conta contaDest = usuarioDest.getConta();
             contaDest.setSaldo(contaDest.getSaldo() + valorTransferencia);
             
             Conta contaAtual = usuario.getConta();
             contaAtual.setSaldo(contaAtual.getSaldo() - valorTransferencia);
             
             contaDao.updateConta(contaDest);
             contaDao.updateConta(contaAtual);
             
             Extrato extrato = new Extrato("Transferiu para " + usuarioDest.getNome() + ", " + valorTransferencia + " R$", contaAtual.getSaldo(), getTimestamp(), contaAtual);
             extradoDAO.save(extrato);
             
             request.getSession().setAttribute("usuario",usuario);
             request.setAttribute("mensagemSucesso", "Transferência feita com sucesso para "+usuarioDest.getNome()+"!");
             
             request.getServletContext().getRequestDispatcher("/transferencia.jsp").forward(request, response);
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
        usuarioDao = new UsuarioDAO(getConnection());
        contaDao = new ContaDAO(getConnection());
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
