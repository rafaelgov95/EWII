package br.buscacao.models.cao;

import br.buscacao.models.usuario.Pessoa;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

@Entity(noClassnameStored = true)
public class Cao {

    @Id
    private ObjectId id;
    @Reference
    private Pessoa dono;
    private String nome;
    private List<String> apelidos;
    private List<String> imagens ;
    private String data;



    public Cao(){
        super();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public Pessoa getDono() {
        return dono;
    }

    public void setDono(Pessoa dono) {
        this.dono = dono;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
