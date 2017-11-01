package br.rv.buscacao.modelos.cao;

import java.util.List;


public class Cao {

    private String id ;
    private String nome;
    private List<String> apelidos;
    private List<String> imagens ;
    private String data;

    public Cao(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public List<String> getApelidos() {
        return apelidos;
    }

    public void setApelidos(String  apelido) {
        this.apelidos.add(apelido);
    }

    public List<String>  getImagens() {
        return imagens;
    }

    public void setImagens( String imagen) {
        this.imagens.add(imagen) ;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
