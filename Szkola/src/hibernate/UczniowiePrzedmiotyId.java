package hibernate;
// Generated 2019-05-17 14:24:19 by Hibernate Tools 4.3.1



/**
 * UczniowiePrzedmiotyId generated by hbm2java
 */
public class UczniowiePrzedmiotyId  implements java.io.Serializable {


     private int idPrzedmioty;
     private int idUcznia;

    public UczniowiePrzedmiotyId() {
    }

    public UczniowiePrzedmiotyId(int idPrzedmioty, int idUcznia) {
       this.idPrzedmioty = idPrzedmioty;
       this.idUcznia = idUcznia;
    }
   
    public int getIdPrzedmioty() {
        return this.idPrzedmioty;
    }
    
    public void setIdPrzedmioty(int idPrzedmioty) {
        this.idPrzedmioty = idPrzedmioty;
    }
    public int getIdUcznia() {
        return this.idUcznia;
    }
    
    public void setIdUcznia(int idUcznia) {
        this.idUcznia = idUcznia;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UczniowiePrzedmiotyId) ) return false;
		 UczniowiePrzedmiotyId castOther = ( UczniowiePrzedmiotyId ) other; 
         
		 return (this.getIdPrzedmioty()==castOther.getIdPrzedmioty())
 && (this.getIdUcznia()==castOther.getIdUcznia());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdPrzedmioty();
         result = 37 * result + this.getIdUcznia();
         return result;
   }   


}


