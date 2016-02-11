package br.com.devweb.model;

/**
 *
 * @author pauloarthur
 */
public class Usuario {
    
    private String cpf;
    private String nome;
    private String senha;
    
    private Conta conta;
    
    public Usuario(){
        
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
//    public Conta getContaCorrente(){
//        if(contas.isEmpty() || contas == null){
//            return null;
//        }
//        
//        Conta contaCorrente = null;
//        
//        for (Conta conta : contas) {
//            contaCorrente =  conta instanceof ContaCorrente ? conta : null;
//        } 
//        
//        return contaCorrente;
//    }
       
}
