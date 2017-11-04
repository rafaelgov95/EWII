package br.buscacao.models.usuario;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.mongodb.morphia.annotations.*;

import javax.validation.constraints.NotNull;

public class Pessoa {

    @Id
    private String id;
    @Indexed(options = @IndexOptions(unique = true))
    private String user_nome;
    @NotNull
    private String nome;
    @NotNull
    private String sobre_nome;
//    @NotNull
    private String data;
    @Email
    @Indexed(options = @IndexOptions(unique = true))
    private String email;
    @NotNull
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_nome() {
        return user_nome;
    }

    public void setUser_nome(String user_nome) {
        this.user_nome = user_nome;
    }

    public String getSobre_nome() {
        return sobre_nome;
    }

    public void setSobre_nome(String sobre_nome) {
        this.sobre_nome = sobre_nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
