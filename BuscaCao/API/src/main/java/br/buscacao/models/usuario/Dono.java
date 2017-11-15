package br.buscacao.models.usuario;
import br.buscacao.models.cao.Cao;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;


import java.util.ArrayList;
import java.util.List;

//@Entity(noClassnameStored = true)
public class Dono extends Pessoa{
    private String id_login;
    @Embedded
    private List<Cao> caes = new ArrayList<Cao>();

}
