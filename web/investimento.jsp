<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath()%>/resources/css/componentes.css">
        
        <title>Investimento</title>
        <style>
            .left{float:left; width:33%;}
            .font-size{font-size:20px ;}
        </style>
    </head>
    <body>
        <h1>Investimento</h1>
        
        <c:if test="${not empty mensagemSucesso}">
            <span style="color: green"> ${mensagemSucesso}</span>
        </c:if>
            
        <c:if test="${not empty mensagemErro}">
            <span style="color: red"> ${mensagemErro} !!</span>
        </c:if>
        
        <form action="investe" method="post">
            <table style="width:40%">
                <tr>
                    <td>R$: </td>
                    <td><input type="text" name="valorInvestido"></td>
                    <td>Resgatar: <input type="radio" name="tipo" value="Resgatar"></td>
                    <td>Aplicar: <input type="radio" name="tipo" value="Aplicar"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="OK" ></td>
                    <td><a href="<%=getServletContext().getContextPath()%>/home"><button class="botao" type="button">Voltar</button></a></td>
                </tr>
            </table>
            
        </form>
        <div class="left" style="width:30%">
            <h3>Saldo Investimento</h3>
            <div style="font-size: 70px; color: red;">
                R$ ${sessionScope.iv.valorAplicado}
            </div>
        </div>
        
    </body>
</html>
