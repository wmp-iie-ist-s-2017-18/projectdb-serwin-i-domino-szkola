package hibernate;
// Generated 2019-05-17 14:24:19 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Przedmioty generated by hbm2java
 */
public class Przedmioty  implements java.io.Serializable {


     private int idPrzedmioty;
     private String nazwaPrzedmiotu;
     private Set uczniowiePrzedmioties = new HashSet(0);
     private Set nauczycielePrzedmioties = new HashSet(0);
     private Set ocenies = new HashSet(0);

    public Przedmioty() {
    }

	
    public Przedmioty(int idPrzedmioty, String nazwaPrzedmiotu) {
        this.idPrzedmioty = idPrzedmioty;
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
    }
    public Przedmioty(int idPrzedmioty, String nazwaPrzedmiotu, Set uczniowiePrzedmioties, Set nauczycielePrzedmioties, Set ocenies) {
       this.idPrzedmioty = idPrzedmioty;
       this.nazwaPrzedmiotu = nazwaPrzedmiotu;
       this.uczniowiePrzedmioties = uczniowiePrzedmioties;
       this.nauczycielePrzedmioties = nauczycielePrzedmioties;
       this.ocenies = ocenies;
    }
   
    public int getIdPrzedmioty() {
        return this.idPrzedmioty;
    }
    
    public void setIdPrzedmioty(int idPrzedmioty) {
        this.idPrzedmioty = idPrzedmioty;
    }
    public String getNazwaPrzedmiotu() {
        return this.nazwaPrzedmiotu;
    }
    
    public void setNazwaPrzedmiotu(String nazwaPrzedmiotu) {
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
    }
    public Set getUczniowiePrzedmioties() {
        return this.uczniowiePrzedmioties;
    }
    
    public void setUczniowiePrzedmioties(Set uczniowiePrzedmioties) {
        this.uczniowiePrzedmioties = uczniowiePrzedmioties;
    }
    public Set getNauczycielePrzedmioties() {
        return this.nauczycielePrzedmioties;
    }
    
    public void setNauczycielePrzedmioties(Set nauczycielePrzedmioties) {
        this.nauczycielePrzedmioties = nauczycielePrzedmioties;
    }
    public Set getOcenies() {
        return this.ocenies;
    }
    
    public void setOcenies(Set ocenies) {
        this.ocenies = ocenies;
    }




}


