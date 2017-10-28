package br.buscacao.models.usuario;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.mongodb.morphia.annotations.*;

import javax.validation.constraints.NotNull;

public class Pessoa {

    @Id
    private String id;
    @NotNull
    private String nome;
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
}
