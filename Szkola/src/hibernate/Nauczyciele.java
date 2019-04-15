package hibernate;
// Generated 2019-03-31 21:14:21 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Nauczyciele generated by hbm2java
 */
public class Nauczyciele  implements java.io.Serializable {


     private int idNauczyciela;
     private String imie;
     private String nazwisko;
     private Integer email;
     private String nrTelefonu;
     private String stawka;
     private String login;
     private String haslo;
     private Set klasas = new HashSet(0);
     private Set uwagis = new HashSet(0);
     private Set ocenies = new HashSet(0);

    public Nauczyciele() {
    }

	
    public Nauczyciele(int idNauczyciela, String imie, String nazwisko, String stawka, String login, String haslo) {
        this.idNauczyciela = idNauczyciela;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stawka = stawka;
        this.login = login;
        this.haslo = haslo;
    }
    public Nauczyciele(int idNauczyciela, String imie, String nazwisko, Integer email, String nrTelefonu, String stawka, String login, String haslo, Set klasas, Set uwagis, Set ocenies) {
       this.idNauczyciela = idNauczyciela;
       this.imie = imie;
       this.nazwisko = nazwisko;
       this.email = email;
       this.nrTelefonu = nrTelefonu;
       this.stawka = stawka;
       this.login = login;
       this.haslo = haslo;
       this.klasas = klasas;
       this.uwagis = uwagis;
       this.ocenies = ocenies;
    }
   
    public int getIdNauczyciela() {
        return this.idNauczyciela;
    }
    
    public void setIdNauczyciela(int idNauczyciela) {
        this.idNauczyciela = idNauczyciela;
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
    public Integer getEmail() {
        return this.email;
    }
    
    public void setEmail(Integer email) {
        this.email = email;
    }
    public String getNrTelefonu() {
        return this.nrTelefonu;
    }
    
    public void setNrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }
    public String getStawka() {
        return this.stawka;
    }
    
    public void setStawka(String stawka) {
        this.stawka = stawka;
    }
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    public String getHaslo() {
        return this.haslo;
    }
    
    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
    public Set getKlasas() {
        return this.klasas;
    }
    
    public void setKlasas(Set klasas) {
        this.klasas = klasas;
    }
    public Set getUwagis() {
        return this.uwagis;
    }
    
    public void setUwagis(Set uwagis) {
        this.uwagis = uwagis;
    }
    public Set getOcenies() {
        return this.ocenies;
    }
    
    public void setOcenies(Set ocenies) {
        this.ocenies = ocenies;
    }




}


