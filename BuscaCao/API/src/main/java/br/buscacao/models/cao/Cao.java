package br.buscacao.models.cao;

import br.buscacao.models.gps.Local;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity(noClassnameStored = true)
public class Cao {

    @Id
    @Property("id")
    private String id ;
    @NotNull
    private String dono;
    @NotNull
    private String raca;
    @NotNull
    private String nome;
    @NotNull
    private String resumo;
    @NotNull
    private String apelido;
    @NotNull
    private String sexo;
    @NotNull
    private String imagen;
    @Embedded
    private Local local;
    @NotNull
    private String data_p;
    @NotNull
    private String data_nasc;
    private String data_create;

    public Cao()
    {
        super();
    }
//
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getId() {
//        return id.toString();
//    }
//
//    public void setId(String id) {
//        this.id = new ObjectId(id);
//    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getData_p() {
        return data_p;
    }

    public void setData_p(String data_p) {
        this.data_p = data_p;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getData_create() {
        return data_create;
    }

    public void setData_create(String data_create) {
        this.data_create = data_create;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @PrePersist
    public void prePersist() {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String data = LocalDate.now().format(formatters);
        this.data_create = (data_create == null || data_create.equals("")) ? data: data_create;

    }
}
