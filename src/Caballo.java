/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USUARIO
 */
public class Caballo {
    
    private int posicionX;
    private int posicionY;    
    private int manzanas;
    
    
    Caballo(int x, int y, int m){
        posicionX = x;
        posicionY = y;
        manzanas = m;
    }
    
    public int getPosicionX()
    {
        return posicionX;
    }
    
    public int getPosicionY()
    {
        return posicionY;
    }  
    
    public int getManzanas()
    {
        return manzanas;
    }
    
     public void setPosicionX(int posicionX)
    {
        this.posicionX = posicionX;
    }
     
    public void setposicionY(int posicionY)
    {
        this.posicionY = posicionY;
    }
    
    public void setManzanas(int manzanas)
    {
        this.manzanas = manzanas;
    }
    
}
