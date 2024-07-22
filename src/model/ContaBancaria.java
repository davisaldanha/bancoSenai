package model;

public class ContaBancaria {

    private String numero;
    private double saldo;
    private String senha;
    private Usuario usuario;

    public ContaBancaria(){}

    public ContaBancaria(String numero, double saldo, String senha){
        this.numero = numero;
        if(saldo > 0 ){
            this.saldo = saldo;
        }else{
            this.saldo = 0.0;
        }
        if(senha.length() == 6){
            this.senha = senha;
        }else{
            this.senha = "123456";
        }
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    //Método para retornar o valor de saldo
    public double getSaldo(){
        return saldo;
    }

    //Método para gravar um valor em saldo
    public void setSaldo(double saldo){
        if(saldo > 0 ){
            this.saldo = saldo;
        }
    }

    //Método para retornar o valor da senha
    public String getSenha(){
        return this.senha;
    }

    //Método para gravar um valor em senha
    public void setSenha(String senha){
        if(senha.length() == 6){
            this.senha = senha;
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //Método para depositar
    public boolean depositar(double valor){
        if(valor > 0){
            saldo += valor;
            return true;
        }
        return false;
    }

    //Método para sacar
    public boolean sacar(double valor){
        if(valor > 0 && saldo >= valor){
            saldo -= valor;
            return true;
        }
        return false;
    }

}
