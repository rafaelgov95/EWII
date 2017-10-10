package br.buscacao.models.User;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Entity
public class LoginAdvanced extends Login{

    @Reference
    private List<Address> Address = new ArrayList<Address>();

}
