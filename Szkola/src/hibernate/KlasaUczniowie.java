package hibernate;
// Generated 2019-05-17 14:24:19 by Hibernate Tools 4.3.1



/**
 * KlasaUczniowie generated by hbm2java
 */
public class KlasaUczniowie  implements java.io.Serializable {


     private KlasaUczniowieId id;
     private Klasa klasa;
     private Uczniowie uczniowie;

    public KlasaUczniowie() {
    }

    public KlasaUczniowie(KlasaUczniowieId id, Klasa klasa, Uczniowie uczniowie) {
       this.id = id;
       this.klasa = klasa;
       this.uczniowie = uczniowie;
    }
   
    public KlasaUczniowieId getId() {
        return this.id;
    }
    
    public void setId(KlasaUczniowieId id) {
        this.id = id;
    }
    public Klasa getKlasa() {
        return this.klasa;
    }
    
    public void setKlasa(Klasa klasa) {
        this.klasa = klasa;
    }
    public Uczniowie getUczniowie() {
        return this.uczniowie;
    }
    
    public void setUczniowie(Uczniowie uczniowie) {
        this.uczniowie = uczniowie;
    }




}


