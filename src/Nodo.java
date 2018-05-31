
import java.util.ArrayList;

//Clase que representa un nodo 
//Prioridad: menor costo


public class Nodo implements Comparable <Nodo>
{    
    private Caballo blanco;
    private Caballo negro;
    private Nodo referencia;       
    private int profundidad;
    private int utilidad;
    private ArrayList <Manzana> manzanas;

    
    public Nodo(Caballo blanco, Caballo negro, Nodo referencia, int profundidad, int utilidad, ArrayList <Manzana> manzanas) {
        this.blanco = blanco;
        this.negro = negro;
        this.referencia = referencia;   
        this.profundidad = profundidad;
        this.utilidad = utilidad;
        this.manzanas = (ArrayList <Manzana>) manzanas.clone();
    }
 
    public Caballo getBlanco() {
        return blanco;
    }

    public Caballo getNegro() {
        return negro;
    }
    
    public Nodo getReferencia() {
        return referencia;
    }    

    public int getProfundidad() {
        return profundidad;
    }

    public int getUtilidad() {
        return utilidad;
    } 
    
    public ArrayList <Manzana> getManzanas() {
        return manzanas;
    }
    
    public void setManzanas(ArrayList <Manzana> manzanas) {
        this.manzanas = manzanas;
    }     
    
    
    public void setBlanco(Caballo blanco){
        this.blanco = blanco;
    }
    
    public void setNegro(Caballo negro){
        this.negro = negro;
    }
    
    public void setReferencia(Nodo referencia){
        this.referencia = referencia;
    }
    
    public void setProfundidad(int profundidad){
        this.profundidad = profundidad;
    }
    
    public void setUtilidad(int utilidad){
        this.utilidad = utilidad;
    } 
    
   //El valor mas bajo tendrá la mayor prioridad para Priority Queue
    public int compareTo(Nodo nodo) {
        if(this.getProfundidad() == nodo.getProfundidad())
            return 0;
        else if(this.getProfundidad()> nodo.getProfundidad())
            return -1;
        else 
            return 1;        
    }     


}    
    
