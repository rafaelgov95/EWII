package br.buscacao.models.usuario;


public class Telefone {
    private int dd;
    private int numero;
    private boolean isprincipal;

    public int getDd() {
        return dd;
    }

    public void setDd(int dd) {
        this.dd = dd;
    }

    public int getNumber() {
        return numero;
    }

    public void setNumber(int numero) {
        this.numero = numero;
    }

    public boolean isIsprincipal() {
        return isprincipal;
    }

    public void setIsprincipal(boolean isprincipal) {
        this.isprincipal = isprincipal;
    }
}
