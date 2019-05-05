package hibernate;
// Generated 2019-05-03 22:53:30 by Hibernate Tools 4.3.1



/**
 * NauczycielePrzedmiotyId generated by hbm2java
 */
public class NauczycielePrzedmiotyId  implements java.io.Serializable {


     private int idNauczyciela;
     private int idPrzedmioty;

    public NauczycielePrzedmiotyId() {
    }

    public NauczycielePrzedmiotyId(int idNauczyciela, int idPrzedmioty) {
       this.idNauczyciela = idNauczyciela;
       this.idPrzedmioty = idPrzedmioty;
    }
   
    public int getIdNauczyciela() {
        return this.idNauczyciela;
    }
    
    public void setIdNauczyciela(int idNauczyciela) {
        this.idNauczyciela = idNauczyciela;
    }
    public int getIdPrzedmioty() {
        return this.idPrzedmioty;
    }
    
    public void setIdPrzedmioty(int idPrzedmioty) {
        this.idPrzedmioty = idPrzedmioty;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof NauczycielePrzedmiotyId) ) return false;
		 NauczycielePrzedmiotyId castOther = ( NauczycielePrzedmiotyId ) other; 
         
		 return (this.getIdNauczyciela()==castOther.getIdNauczyciela())
 && (this.getIdPrzedmioty()==castOther.getIdPrzedmioty());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdNauczyciela();
         result = 37 * result + this.getIdPrzedmioty();
         return result;
   }   


}

