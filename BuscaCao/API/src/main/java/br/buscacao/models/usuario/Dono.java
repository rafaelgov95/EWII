package br.buscacao.models.usuario;
import br.buscacao.models.cao.Cao;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;


import java.util.ArrayList;
import java.util.List;


public class Dono extends Pessoa{

    @Embedded
    private List<Address> address = new ArrayList<Address>();

    @Embedded
    private List<Cao> caes = new ArrayList<Cao>();

    @Embedded
    private List<Telefone> telefones = new ArrayList<Telefone>();

}
