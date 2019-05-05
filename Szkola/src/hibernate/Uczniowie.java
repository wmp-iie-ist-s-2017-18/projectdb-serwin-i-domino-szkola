package hibernate;
// Generated 2019-05-03 22:53:30 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Uczniowie generated by hbm2java
 */
public class Uczniowie  implements java.io.Serializable {


     private int idUcznia;
     private String imie;
     private String nazwisko;
     private String pesel;
     private float nrLegitymacji;
     private Date dataUrodzenia;
     private String miasto;
     private String ulica;
     private String kodPocztowy;
     private String nrTelefonuDoRodzica;
     private String haslo;
     private Set obecnoscs = new HashSet(0);
     private Set klasaUczniowies = new HashSet(0);
     private Set uwagis = new HashSet(0);
     private Set uczniowiePrzedmioties = new HashSet(0);
     private Set ocenies = new HashSet(0);

    public Uczniowie() {
    }

	
    public Uczniowie(int idUcznia, String imie, String nazwisko, String pesel, float nrLegitymacji, Date dataUrodzenia, String miasto, String ulica, String kodPocztowy, String haslo) {
        this.idUcznia = idUcznia;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.nrLegitymacji = nrLegitymacji;
        this.dataUrodzenia = dataUrodzenia;
        this.miasto = miasto;
        this.ulica = ulica;
        this.kodPocztowy = kodPocztowy;
        this.haslo = haslo;
    }
    public Uczniowie(int idUcznia, String imie, String nazwisko, String pesel, float nrLegitymacji, Date dataUrodzenia, String miasto, String ulica, String kodPocztowy, String nrTelefonuDoRodzica, String haslo, Set obecnoscs, Set klasaUczniowies, Set uwagis, Set uczniowiePrzedmioties, Set ocenies) {
       this.idUcznia = idUcznia;
       this.imie = imie;
       this.nazwisko = nazwisko;
       this.pesel = pesel;
       this.nrLegitymacji = nrLegitymacji;
       this.dataUrodzenia = dataUrodzenia;
       this.miasto = miasto;
       this.ulica = ulica;
       this.kodPocztowy = kodPocztowy;
       this.nrTelefonuDoRodzica = nrTelefonuDoRodzica;
       this.haslo = haslo;
       this.obecnoscs = obecnoscs;
       this.klasaUczniowies = klasaUczniowies;
       this.uwagis = uwagis;
       this.uczniowiePrzedmioties = uczniowiePrzedmioties;
       this.ocenies = ocenies;
    }
   
    public int getIdUcznia() {
        return this.idUcznia;
    }
    
    public void setIdUcznia(int idUcznia) {
        this.idUcznia = idUcznia;
    }
    public String getImie() {
        return this.imie;
    }
    
    public void setImie(String imie) {
        this.imie = imie;
    }
    public String getNazwisko() {
        return this.nazwisko;
    }
    
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public String getPesel() {
        return this.pesel;
    }
    
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
    public float getNrLegitymacji() {
        return this.nrLegitymacji;
    }
    
    public void setNrLegitymacji(float nrLegitymacji) {
        this.nrLegitymacji = nrLegitymacji;
    }
    public Date getDataUrodzenia() {
        return this.dataUrodzenia;
    }
    
    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }
    public String getMiasto() {
        return this.miasto;
    }
    
    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }
    public String getUlica() {
        return this.ulica;
    }
    
    public void setUlica(String ulica) {
        this.ulica = ulica;
    }
    public String getKodPocztowy() {
        return this.kodPocztowy;
    }
    
    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }
    public String getNrTelefonuDoRodzica() {
        return this.nrTelefonuDoRodzica;
    }
    
    public void setNrTelefonuDoRodzica(String nrTelefonuDoRodzica) {
        this.nrTelefonuDoRodzica = nrTelefonuDoRodzica;
    }
    public String getHaslo() {
        return this.haslo;
    }
    
    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
    public Set getObecnoscs() {
        return this.obecnoscs;
    }
    
    public void setObecnoscs(Set obecnoscs) {
        this.obecnoscs = obecnoscs;
    }
    public Set getKlasaUczniowies() {
        return this.klasaUczniowies;
    }
    
    public void setKlasaUczniowies(Set klasaUczniowies) {
        this.klasaUczniowies = klasaUczniowies;
    }
    public Set getUwagis() {
        return this.uwagis;
    }
    
    public void setUwagis(Set uwagis) {
        this.uwagis = uwagis;
    }
    public Set getUczniowiePrzedmioties() {
        return this.uczniowiePrzedmioties;
    }
    
    public void setUczniowiePrzedmioties(Set uczniowiePrzedmioties) {
        this.uczniowiePrzedmioties = uczniowiePrzedmioties;
    }
    public Set getOcenies() {
        return this.ocenies;
    }
    
    public void setOcenies(Set ocenies) {
        this.ocenies = ocenies;
    }




}


