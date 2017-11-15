package br.buscacao.models.login;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.*;

import javax.validation.constraints.NotNull;

@Entity(noClassnameStored = true)
public class Login {

    @Id
    private String id ;
    @Email
    @Indexed(options = @IndexOptions(unique = true))
    private String email;
    @NotNull
    @NotEmpty
    private String password;
    private String id_conta;
    private String type;

    public Login()
    {
        super();
    }

    public Login(final String email,final  String pwd){
        this.email=email;
        this.password=pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getId_conta() {
        return id_conta;
    }

    public void setId_conta(String id_conta) {
        this.id_conta = id_conta;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
