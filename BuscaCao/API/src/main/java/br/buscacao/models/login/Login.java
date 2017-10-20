package br.buscacao.models.login;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

@Entity(noClassnameStored = true)
public class Login {

    @Id
    private ObjectId id;
    @Indexed(options = @IndexOptions(unique = true))
    private String email;
    private String password;
    private String token;
    @Indexed(name = "l_ascending", unique = true)
    private long value;

    @Indexed(options = @IndexOptions(unique = true))
    private long unique;

    public Login()
    {
        super();
    }

    public Login(final String email,final  String pwd){
        this.email=email;
        this.password=pwd;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public  void setValue(final long value) {
        this.value = value;
    }

    public  void setUnique(final long value) {
        this.unique = value;
    }
}
