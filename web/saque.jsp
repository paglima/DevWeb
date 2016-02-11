<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath()%>/resources/css/componentes.css">
        
        <title>Saque</title>
        <style>
            .left{float:left; width:33%;}
            .font-size{font-size:20px ;}
        </style>
    </head>
    <body>
        <h1>Saque</h1>
        
        <c:if test="${not empty mensagemSucesso}">
            <span style="color: green"> ${mensagemSucesso}</span>
        </c:if>
            
        <c:if test="${not empty mensagemErro}">
            <span style="color: red"> ${mensagemErro} !!</span>
        </c:if>
        
        <form action="saca" method="post">
            <table style="width:40%">
                <tr>
                    <td>R$: </td>
                    <td><input type="text" name="valorSaque"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="OK" ></td>
                    <td><a href="<%=getServletContext().getContextPath()%>/home"><button class="botao" type="button">Voltar</button></a></td>
                </tr>
            </table>
            
        </form>
        
        
    </body>
</html>
