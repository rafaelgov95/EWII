package br.buscacao.models.user;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


@Entity(noClassnameStored = true)

public class Address {
    @Id
    private ObjectId id;
    private String number;
    private String street;
    private String neighborhood;
    private String town;
    private String state;
    private String postcode;

    public Address(String number,String street,String neighborhood,String town,String state,String postcode){
        this.number=number;
        this.street=street;
        this.neighborhood=neighborhood;
        this.town=town;
        this.state=state;
        this.postcode=postcode;
    }

    public Address(){
        super();
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
