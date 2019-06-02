package hibernate;
// Generated 2019-05-17 14:24:19 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Uwagi generated by hbm2java
 */
public class Uwagi  implements java.io.Serializable {


     private int idUwagi;
     private Nauczyciele nauczyciele;
     private Uczniowie uczniowie;
     private String opis;
     private Date dataWpisania;
     private String typUwagi;

    public Uwagi() {
    }

    public Uwagi(int idUwagi, Nauczyciele nauczyciele, Uczniowie uczniowie, String opis, Date dataWpisania, String typUwagi) {
       this.idUwagi = idUwagi;
       this.nauczyciele = nauczyciele;
       this.uczniowie = uczniowie;
       this.opis = opis;
       this.dataWpisania = dataWpisania;
       this.typUwagi = typUwagi;
    }

    public Uwagi(int idUwagi, int idNaucz, int idUcznia, String opis, Date data, String typ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    public int getIdUwagi() {
        return this.idUwagi;
    }
    
    public void setIdUwagi(int idUwagi) {
        this.idUwagi = idUwagi;
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
    public String getOpis() {
        return this.opis;
    }
    
    public void setOpis(String opis) {
        this.opis = opis;
    }
    public Date getDataWpisania() {
        return this.dataWpisania;
    }
    
    public void setDataWpisania(Date dataWpisania) {
        this.dataWpisania = dataWpisania;
    }
    public String getTypUwagi() {
        return this.typUwagi;
    }
    
    public void setTypUwagi(String typUwagi) {
        this.typUwagi = typUwagi;
    }




}


