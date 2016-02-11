/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devweb.controller;

import br.com.devweb.model.Conta;
import br.com.devweb.model.Extrato;
import br.com.devweb.model.Investimento;
import br.com.devweb.model.Usuario;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
@WebServlet(name = "InvestimentoServlet", urlPatterns = {"/investe"})
public class InvestimentoServlet extends GenericServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
        Double valorInvestimento = null;
        String tipo = null;
        try{
            request.getSession().setAttribute("iv",investimentoDAO.getInvestimento(usuario));
            tipo   = request.getParameter("tipo");
            if(tipo.equals("Aplicar")){
                valorInvestimento = Double.valueOf( request.getParameter("valorInvestido") );
            }
        }catch(Exception e){
            request.setAttribute("mensagemErro", "Preencha o campo corretamente");
            request.getSession().setAttribute("usuario",usuario);
            request.getServletContext().getRequestDispatcher("/investimento.jsp").forward(request, response);
            return;
        }
        
         try {
             Usuario usuarioBanco = usuarioDao.findByConta(usuario.getConta().getNumero());
             Conta conta = usuarioBanco.getConta();
             
             Investimento iv = investimentoDAO.getInvestimento(usuario);
             
             Double valorIv = iv.getValorAplicado() == 0D ? 0 : iv.getValorAplicado();
             String mensagemExtrato = "";
             
             if(tipo.equals("Aplicar")){
                conta.setSaldo(conta.getSaldo() - valorInvestimento);
                valorIv += valorInvestimento;
                iv.setValorAplicado(new BigDecimal(valorIv).setScale(2,RoundingMode.HALF_UP).doubleValue());
                mensagemExtrato = " Aplicação no fundo de "+ valorInvestimento+" R$";
             }
             
             if(tipo.equals("Resgatar")){
                Double valorResgate = iv.getValorAplicado();
                               
                for (int i = 0; i < 30; i++) {
                     valorResgate += (valorResgate*iv.getJuros());
                }
                
                iv.setValorAplicado(0D);
                mensagemExtrato = " Resgate no fundo de "+new BigDecimal(valorResgate ).setScale(2, RoundingMode.HALF_UP).doubleValue()+" R$";
                conta.setSaldo(new BigDecimal( conta.getSaldo() + valorResgate ).setScale(2, RoundingMode.HALF_UP).doubleValue() );
             }
             
             investimentoDAO.updateInvestimento(iv);
             contaDao.updateConta(conta);
             
             Extrato extrato = new Extrato(mensagemExtrato, conta.getSaldo(), getTimestamp(), conta);
             extradoDAO.save(extrato);
             
             request.getSession().setAttribute("usuario",usuarioBanco);
             request.getSession().setAttribute("iv",investimentoDAO.getInvestimento(usuario));
             request.setAttribute("mensagemSucesso", "Investimento feito com sucesso!");
             
             request.getServletContext().getRequestDispatcher("/investimento.jsp").forward(request, response);
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
    
}
