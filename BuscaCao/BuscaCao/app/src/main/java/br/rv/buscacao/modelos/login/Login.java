package br.rv.buscacao.modelos.login;


public class Login {

    private String id ;
    private String email;
    private String password;
    private String token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
