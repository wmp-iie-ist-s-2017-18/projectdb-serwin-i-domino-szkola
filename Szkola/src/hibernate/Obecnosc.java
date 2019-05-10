package hibernate;
// Generated 2019-05-10 10:26:17 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Obecnosc generated by hbm2java
 */
public class Obecnosc  implements java.io.Serializable {


     private int idObecnosc;
     private Nauczyciele nauczyciele;
     private Uczniowie uczniowie;
     private boolean typObecnosci;
     private Date dataWpisania;

    public Obecnosc() {
    }

    public Obecnosc(int idObecnosc, Nauczyciele nauczyciele, Uczniowie uczniowie, boolean typObecnosci, Date dataWpisania) {
       this.idObecnosc = idObecnosc;
       this.nauczyciele = nauczyciele;
       this.uczniowie = uczniowie;
       this.typObecnosci = typObecnosci;
       this.dataWpisania = dataWpisania;
    }
   
    public int getIdObecnosc() {
        return this.idObecnosc;
    }
    
    public void setIdObecnosc(int idObecnosc) {
        this.idObecnosc = idObecnosc;
    }
    public Nauczyciele getNauczyciele() {
        return this.nauczyciele;
    }
    
    public void setNauczyciele(Nauczyciele nauczyciele) {
        this.nauczyciele = nauczyciele;
    }
    public Uczniowie getUczniowie() {
        return this.uczniowie;
    }
    
    public void setUczniowie(Uczniowie uczniowie) {
        this.uczniowie = uczniowie;
    }
    public boolean isTypObecnosci() {
        return this.typObecnosci;
    }
    
    public void setTypObecnosci(boolean typObecnosci) {
        this.typObecnosci = typObecnosci;
    }
    public Date getDataWpisania() {
        return this.dataWpisania;
    }
    
    public void setDataWpisania(Date dataWpisania) {
        this.dataWpisania = dataWpisania;
    }




}


