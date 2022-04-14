package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Porudzbina implements Serializable {

    @Id
    private UUID uuid = UUID.randomUUID();

    //Artikli (Porudzbina sadrzi vise artikala)
    @ManyToMany
    @JoinTable(name = "poruceni_artikli",
            joinColumns = @JoinColumn(name = "uuid_porudzbine", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "id_artikla", referencedColumnName = "id"))
    private Set<Artikal> artikli = new HashSet<>();

    //Restoran (Porudzbina dolazi iz jednog restorana)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_restorana", referencedColumnName = "id")
    private Restoran restoran;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum_i_vreme = new Date();

    @Column
    private double cena;

    //Kupac
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac kupac;

    @Enumerated(EnumType.STRING)
    private EnumStatus status;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void dodajArtikal(Artikal a){
        artikli.add(a);
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
        this.artikli = artikli;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Date getDatum_i_vreme() {
        return datum_i_vreme;
    }

    public void setDatum_i_vreme(Date datum_i_vreme) {
        this.datum_i_vreme = datum_i_vreme;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Porudzbina: " +
                " \n    uuid=" + uuid +
                ",\n    kupac = " + kupac +
                ",\n    artikli = " + artikli +
                ",\n    restoran = " + restoran +
                ",\n    datum_i_vreme = '" + datum_i_vreme + '\'' +
                ",\n    cena = " + cena +
                ",\n    status = '" + status + '\'';
    }
}
