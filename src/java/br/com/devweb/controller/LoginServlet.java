package br.com.devweb.controller;

import br.com.devweb.model.Usuario;
import br.com.devweb.utils.Autenticacao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends GenericServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (request.getSession().getAttribute("usuario") != null) {
            //getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
            response.sendRedirect(getServletContext().getContextPath() + "/home");
            return;
        }

        response.sendRedirect(getContextPath() + "/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            String numeroConta = request.getParameter("numeroConta");
            Usuario usuario = usuarioDao.findByConta(numeroConta);

            boolean foiAutenticado = Autenticacao.autenticaUsuario(request, usuario);
            request.setAttribute("foiAutenticado", foiAutenticado);

            if (!foiAutenticado) {
                RequestDispatcher rq = getServletContext().getRequestDispatcher("/login.jsp");
                rq.forward(request, response);
                return;
            }

            request.setAttribute("contaCorrente", usuario.getConta());
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect(getServletContext().getContextPath() + "/home");
        } catch (Exception e) {
            System.out.println("erro query");
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
