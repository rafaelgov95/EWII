package br.buscacao.models.usuario;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

public class Pessoa {

    @Id
    private ObjectId id;
    private String nome;
    @Indexed(options = @IndexOptions(unique = true))
    private String email;
    private String senha;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
