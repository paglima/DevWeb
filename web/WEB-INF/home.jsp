<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <style>
            .left{float:left; width:33%;}
            .font-size{font-size:20px ;}
            .tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #729ea5;border-collapse: collapse;}
            .tftable th {font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;}
            .tftable tr {background-color:#d4e3e5;}
            .tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;}
            .tftable tr:hover {background-color:#ffffff;}
        </style>
        
    </head>
    <body>
        <div class="left" style="width:100%">
            <table style="width:100%">
               <tr>
                   <td><h2>Dev web banking</h2> Bem vindo ${usuario.nome}</td>
                   <td><a style="float:right" href="<%=getServletContext().getContextPath()%>/logout"><button type="button">LogOut</button></a> </td>
                </tr>
            </table>
        </div>
        <div class="left" style="width:25%"> 
            <h3>Menu</h3>
            <table style="width:100%">
                <tr>
                    <td><a  class="font-size" href="<%=getServletContext().getContextPath()%>/deposito.jsp" >Depósito</a></td>
                </tr>
                <tr>
                    <td><a class="font-size" href="<%=getServletContext().getContextPath()%>/transferencia.jsp">Transferência</a></td>
                </tr>
                <tr>
                    <td><a class="font-size" href="<%=getServletContext().getContextPath()%>/investimento.jsp" >Investimento</a></td>
                </tr>
                <tr>
                    <td><a class="font-size" href="<%=getServletContext().getContextPath()%>/saque.jsp" >Saque</a></td>
                </tr>
            </table>
        </div>
        <div class="left" style="width:30%">
            <h3>Saldo</h3>
            <div style="font-size: 70px; color: red;">
                R$ ${sessionScope.usuario.conta.saldo}
            </div>
        </div>
                
        <div class="left" style="width:40%">
            <table class="tftable" border="1">
                <tr>
                    <th colspan="3" style="font-size: 30px;"><center>Extrato</center></th>
                </tr>
                <tr>
                    <th>Histórico</th>
                    <th>Saldo</th>
                    <th>Data</th>
                </tr>
                <c:forEach var="extrato" items="${extratos}">
                    <tr>
                        <td>${extrato.historico}</td>
                        <td>${extrato.valor}</td>
                        <td>${extrato.dt}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        
    </body>
</html>
