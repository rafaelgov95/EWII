package br.buscacao.models.user;
import br.buscacao.models.cao.Cao;
import com.google.gson.annotations.JsonAdapter;
import org.mongodb.morphia.annotations.Reference;


import java.util.ArrayList;
import java.util.List;


public class DonoAdvanced {

    @Reference
    private List<Address> Address = new ArrayList<Address>();
//
//    private List<CaoController> caes = new ArrayList<CaoController>();
//
    public List<br.buscacao.models.user.Address> getAddress() {
        return Address;
    }

    public void setAddress(List<br.buscacao.models.user.Address> address) {
        Address = address;
    }
//
//    public List<CaoController> getCaes() {
//        return caes;
//    }
//
//    public void setCaes(List<CaoController> caes) {
//        this.caes = caes;
//    }
}
