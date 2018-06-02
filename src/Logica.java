


import java.lang.Math;
import java.util.*;
import java.util.Collection;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author USUARIO
 */
public class Logica {
    
   ArrayList <Nodo> lista = new ArrayList();
    private int manzanasTotal;
    private Caballo blanco;
    private Caballo negro;
    private boolean turno;
    private ArrayList <Manzana> manzanas; 
    
    
    private int blancoX;
    private int blancoY;
    private int dim = 6;
    private int prof = 6;
    private int [][] mundo = new int[dim][dim];
    
    private boolean turnoJuego;
    
    public Logica(int a, int b, int c) 
    {    
        
        dim = a;
        manzanasTotal = b;
        prof = c;
        
        mundo = new int[dim][dim];
        blanco = new Caballo(0,0,0);
        negro = new Caballo(0,0,0);
        manzanas = new ArrayList();

        
        for(int i=0; i<dim; i++)
        {
            for(int j=0; j<dim; j++)
            {
                mundo[i][j] = 0;
            }
        }        
        
        inicioAleatorio();
        //leerArchivo();
        estadoInicial();
        turno = true;
        turnoJuego = true;
        
        /*
        for(int i=0;i<dim;i++)
        {
            for(int j=0; j<dim; j++)
                System.out.print(" " + mundo[i][j] + " ");
            System.out.println(" ");
        }*/
    }    


    public void inicioAleatorio()
    {    
        int aleatorio = (int) (Math.random() * dim) ;
        int aleatorio2 = (int) (Math.random() * dim) ;
        
        mundo[aleatorio][aleatorio2] = 2;   
        
        while(mundo[aleatorio][aleatorio2] != 0)
        {
            aleatorio = (int) (Math.random() * dim) ;     
            aleatorio2 = (int) (Math.random() * dim) ;            
        }
        
        mundo[aleatorio][aleatorio2] = 3;        

        
        for(int i=0; i<manzanasTotal; i++)
        {
            while(mundo[aleatorio][aleatorio2] != 0)
            {
                aleatorio = (int) (Math.random() * dim) ;     
                aleatorio2 = (int) (Math.random() * dim) ;            
            }
            mundo[aleatorio][aleatorio2] = 1; 
        }
    }    
    
    //Metodo que lee desde un archivo de texto los datos del mundo y los agrega a una matriz 
    public void leerArchivo() {

        int numeroEntero, sumaInt = 0;
        double numeroDouble, sumaDouble = 0;

        File f = new File("Prueba0.txt");

        try  (Scanner entrada = new Scanner(f)) {

            for(int i=0; i<dim ; i++)
            {
                for(int j=0; j<dim; j++)
                {
                    if(entrada.hasNextInt())                
                        mundo[i][j] = entrada.nextInt();          
                }
            }
            entrada.close();

        }  catch (FileNotFoundException e) {
                System.out.println(e.toString());
            } catch (Exception e) {
                System.out.println(e.toString());
            }
    }
    
    public void estadoInicial(){
    for(int i=0; i<dim ; i++)
    {
        for(int j=0; j<dim; j++)
        {
            if(mundo[i][j] == 2)
            {
                blanco.setPosicionX(i);
                blanco.setposicionY(j);
            }
            if(mundo[i][j] == 3)
            {
                negro.setPosicionX(i);
                negro.setposicionY(j);
            }            
            if(mundo[i][j] == 1)
            {
                Manzana m = new Manzana(i,j);                
                manzanas.add(m);
                manzanasTotal++;
            }               
  
        }
    }
}
    

    
    
    public boolean finJuego(Caballo blanco, Caballo negro)
    {        
      if((blanco.getManzanas()+ negro.getManzanas()) == manzanasTotal)
          return true;
      else
          return false;
    }

    public int utilidad(Caballo blanco, Caballo negro)
    {        
        if(finJuego(blanco,negro))
        {
            if(blanco.getManzanas() > negro.getManzanas())
            {
                return 1;
            }
            else if(blanco.getManzanas() < negro.getManzanas())
            {
                return -1;
            }
            else
                return 0;
        }
        else
        {
            return 0;
        }
    }


    public ArrayList <Manzana> reconteo(int x, int y, ArrayList <Manzana> manzanas)
    {        
        if(comeManzana(x,y,manzanas) == 1)
        {
            for(int i=0; i< manzanas.size(); i++)
            {
               if((manzanas.get(i).getPosicionX() == x) && (manzanas.get(i).getPosicionY() == y))
                   manzanas.remove(i);
            }
            return manzanas;
        }
        else
            return manzanas;
    }        


    public Nodo minimax(){
 
        Stack <Nodo> pila = new Stack <Nodo>(); //Pila de nodos
        ArrayList <Nodo> pilaHojas = new ArrayList <Nodo> (); 
        Nodo nodo = new Nodo(blanco,negro,null,0,0,manzanas);  //Nodo raiz (posicion de Mario)    
        pila.push(nodo);     

        
            while((pila.size() > 0) )     
            {        
                Nodo nodoActual = pila.pop(); //Elimina de la cola al primer nodo

                //Atributos del nodo actual
                int xBlanco = nodoActual.getBlanco().getPosicionX();
                int yBlanco = nodoActual.getBlanco().getPosicionY();
                int mBlanco = nodoActual.getBlanco().getManzanas();
                int xNegro = nodoActual.getNegro().getPosicionX();
                int yNegro = nodoActual.getNegro().getPosicionY();  
                int mNegro = nodoActual.getNegro().getManzanas();                
                Nodo padre = nodoActual.getReferencia();
                int profundidad = nodoActual.getProfundidad();
                int utilidad = nodoActual.getUtilidad();
                ArrayList manzanasActual = nodoActual.getManzanas();

                if(((nodoActual.getProfundidad() % 2) == 0) && nodoActual.getProfundidad() < prof)
                {
                    ArrayList <Integer> lista = expandir(nodoActual,padre,true);
                    if(lista.size() == 0)
                    {
                        pilaHojas.add(nodoActual);
                    }
                        
                    
                   for (int i = 0; i < lista.size(); i++) 
                   {               
                       if(lista.get(i) == 1) 
                       {
                           Nodo nodoAux;
                           Caballo blancoAux = new Caballo(xBlanco-2,yBlanco-1,comeManzana(xBlanco-2,yBlanco-1,manzanasActual)+mBlanco);
                           Caballo negroAux = nodoActual.getNegro();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xBlanco-2,yBlanco-1,manzanasActual));       
                      //     System.out.println("uno");
                      //     System.out.println("("+ (xBlanco-2) + "," +(yBlanco-1) + ")" + " prof " + (profundidad+1) + "uti " + utilidad);
                            if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);
                        }
                       else if(lista.get(i) == 2) 
                        {
                           Nodo nodoAux;
                           Caballo blancoAux = new Caballo(xBlanco-2,yBlanco+1,comeManzana(xBlanco-2,yBlanco+1,manzanasActual)+mBlanco);
                           Caballo negroAux = nodoActual.getNegro();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xBlanco-2,yBlanco+1,manzanasActual));        
                     //      System.out.println("2");
                     //      System.out.println("("+ (xBlanco-2) + "," +(yBlanco+1) + ")"+ " prof " + (profundidad+1)+ "uti " + utilidad);
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);                       

                        }    
                       else if(lista.get(i) == 3)
                        {
                           Nodo nodoAux;
                           Caballo blancoAux = new Caballo(xBlanco-1,yBlanco-2,comeManzana(xBlanco-1,yBlanco-2,manzanasActual)+mBlanco);
                           Caballo negroAux = nodoActual.getNegro();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xBlanco-1,yBlanco-2,manzanasActual));   
                      //    System.out.println("3");
                     //     System.out.println("("+ (xBlanco-1) + "," +(yBlanco-2) + ")"+ " prof " + (profundidad+1) + "uti " + utilidad);
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);                    

                        }
                       else if(lista.get(i) == 4) 
                        {
                           Nodo nodoAux;
                           Caballo blancoAux = new Caballo(xBlanco-1,yBlanco+2,comeManzana(xBlanco-1,yBlanco+2,manzanasActual)+mBlanco);
                           Caballo negroAux = nodoActual.getNegro();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xBlanco-1,yBlanco+2,manzanasActual));       
                      //     System.out.println("4");
                      //     System.out.println("("+ (xBlanco-1) + "," +(yBlanco+2) + ")"+ " prof " + (profundidad+1)+ "uti " + utilidad);
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);

                        }
                       else if(lista.get(i) == 5) 
                        {
                           Nodo nodoAux;
                           Caballo blancoAux = new Caballo(xBlanco+1,yBlanco-2,comeManzana(xBlanco+1,yBlanco-2,manzanasActual)+mBlanco);
                           Caballo negroAux = nodoActual.getNegro();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xBlanco+1,yBlanco-2,manzanasActual));        
                     //      System.out.println("5");
                     //      System.out.println("("+ (xBlanco+1) + "," +(yBlanco-2) + ")"+ " prof " + (profundidad+1)+ "uti " + utilidad);
                     //       System.out.println("UTILIDAD11 " + (utilidad(blancoAux,negroAux) == 0));
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);
                        } 
                       else if(lista.get(i) == 6) 
                        {
                           Nodo nodoAux;
                           Caballo blancoAux = new Caballo(xBlanco+1,yBlanco+2,comeManzana(xBlanco+1,yBlanco+2,manzanasActual)+mBlanco);
                           Caballo negroAux = nodoActual.getNegro();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xBlanco+1,yBlanco+2,manzanasActual));        
                    //       System.out.println("6");
                    //       System.out.println("("+ (xBlanco+1) + "," +(yBlanco+2) + ")"+ " prof " + (profundidad+1)+ "uti " + utilidad);
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);
                        } 
                       else if(lista.get(i) == 7) 
                        {
                           Nodo nodoAux;
                           Caballo blancoAux = new Caballo(xBlanco+2,yBlanco-1,comeManzana(xBlanco+2,yBlanco-1,manzanasActual)+mBlanco);
                           Caballo negroAux = nodoActual.getNegro();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xBlanco+2,yBlanco-1,manzanasActual));        
                     //      System.out.println("7");
                    //       System.out.println("("+ (xBlanco+2) + "," +(yBlanco-1) + ")"+ " prof " + (profundidad+1)+ "uti " + utilidad);
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);
                        } 
                       else if(lista.get(i) == 8) 
                        {
                           Nodo nodoAux;
                           Caballo blancoAux = new Caballo(xBlanco+2,yBlanco+1,comeManzana(xBlanco+2,yBlanco+1,manzanasActual)+mBlanco);
                           Caballo negroAux = nodoActual.getNegro();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xBlanco+2,yBlanco+1,manzanasActual));       
                    //       System.out.println("8");
                   //        System.out.println("("+ (xBlanco+2) + "," +(yBlanco+1) + ")"+ " prof " + (profundidad+1)+ "uti " + utilidad);
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);
                        }                    
                   }                    
                } //Fin if gigante
                else if(nodoActual.getProfundidad() < prof)
                {
                    ArrayList <Integer> lista = expandir(nodoActual,padre,false);                

                   for (int i = 0; i < lista.size(); i++) 
                   {               
                       if(lista.get(i) == 1) 
                       {
                           Nodo nodoAux;
                           Caballo negroAux = new Caballo(xNegro-2,yNegro-1,comeManzana(xNegro-2,yNegro-1,manzanasActual)+mNegro);
                           Caballo blancoAux = nodoActual.getBlanco();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xNegro-2,yNegro-1,manzanasActual));       
                     //      System.out.println("("+ (xNegro-2) + "," +(yNegro-1) + ")"+ " prof " + (profundidad+1));
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);

                        }
                       else if(lista.get(i) == 2) 
                        {
                           Nodo nodoAux;
                           Caballo negroAux = new Caballo(xNegro-2,yNegro+1,comeManzana(xNegro-2,yNegro+1,manzanasActual)+mNegro);
                           Caballo blancoAux = nodoActual.getBlanco();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xNegro-2,yNegro+1,manzanasActual));        
                 //          System.out.println("("+ (xNegro-2) + "," +(yNegro+1) + ")"+ " prof " + (profundidad+1));
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);                       

                        }    
                       else if(lista.get(i) == 3)
                        {
                           Nodo nodoAux;
                           Caballo negroAux = new Caballo(xNegro-1,yNegro-2,comeManzana(xNegro-1,yNegro-2,manzanasActual)+mNegro);
                           Caballo blancoAux = nodoActual.getBlanco();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xNegro-1,yNegro-2,manzanasActual));      
                  //         System.out.println("("+ (xNegro-1) + "," +(yNegro-2) + ")"+ " prof " + (profundidad+1));
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);                    

                        }
                       else if(lista.get(i) == 4) 
                        {
                           Nodo nodoAux;
                           Caballo negroAux = new Caballo(xNegro-1,yNegro+2,comeManzana(xNegro-1,yNegro+2,manzanasActual)+mNegro);
                           Caballo blancoAux = nodoActual.getBlanco();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xNegro-1,yNegro+2,manzanasActual));       
                  //         System.out.println("("+ (xNegro-1) + "," +(yNegro+2) + ")"+ " prof " + (profundidad+1));
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);

                        }
                       else if(lista.get(i) == 5) 
                        {
                           Nodo nodoAux;
                           Caballo negroAux = new Caballo(xNegro+1,yNegro-2,comeManzana(xNegro+1,yNegro-2,manzanasActual)+mNegro);
                           Caballo blancoAux = nodoActual.getBlanco();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xNegro+1,yNegro-2,manzanasActual));        
                //           System.out.println("("+ (xNegro+1) + "," +(yNegro-2) + ")"+ " prof " + (profundidad+1));
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);
                        } 
                       else if(lista.get(i) == 6) 
                        {
                           Nodo nodoAux;
                           Caballo negroAux = new Caballo(xNegro+1,yNegro+2,comeManzana(xNegro+1,yNegro+2,manzanasActual)+mNegro);
                           Caballo blancoAux = nodoActual.getBlanco();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xNegro+1,yNegro+2,manzanasActual));        
                //           System.out.println("("+ (xNegro+1) + "," +(yNegro+2) + ")"+ " prof " + (profundidad+1));
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);
                        } 
                       else if(lista.get(i) == 7) 
                        {
                           Nodo nodoAux;
                           Caballo negroAux = new Caballo(xNegro+2,yNegro-1,comeManzana(xNegro+2,yNegro-1,manzanasActual)+mNegro);
                           Caballo blancoAux = nodoActual.getBlanco();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xNegro+2,yNegro-1,manzanasActual));        
              //            System.out.println("("+ (xNegro+2) + "," +(yNegro-1) + ")"+ " prof " + (profundidad+1));
                          if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);
                        } 
                       else if(lista.get(i) == 8) 
                        {
                           Nodo nodoAux;
                           Caballo negroAux = new Caballo(xNegro+2,yNegro+1,comeManzana(xNegro+2,yNegro+1,manzanasActual)+mNegro);
                           Caballo blancoAux = nodoActual.getBlanco();
                           nodoAux = new Nodo(blancoAux,negroAux,nodoActual,profundidad + 1,utilidad(blancoAux,negroAux),reconteo(xNegro+2,yNegro+1,manzanasActual));       
               //            System.out.println("("+ (xNegro+2) + "," +(yNegro+1) + ")"+ " prof " + (profundidad+1));
                           if((utilidad(blancoAux,negroAux) == 0) && (profundidad+1) != prof)
                             pila.push(nodoAux);
                           else
                              pilaHojas.add(nodoAux);
                        }                    
                   }                    
                } //Fin else gigante 
            } //FIN WHILE
           

            /* 
            if(n1.getBlanco().getManzanas()>n2.getBlanco().getManzanas())
                respuesta = n1;
            else if (n1.getBlanco().getManzanas()<n2.getBlanco().getManzanas())
                respuesta = n2;*/
            Collections.sort(pilaHojas);
            
      //    System.out.println("CANTIDAD HOJAS " + pilaHojas.size());
           Nodo respuesta = utilidadutilidad(pilaHojas); 
           
       //   System.out.println("LISTA PROF 1 " + lista.size());
      //  System.out.println(lista.get(0).getBlanco().getPosicionX() + " " + lista.get(0).getBlanco().getPosicionY());
       // System.out.println(lista.get(1).getBlanco().getPosicionX() + " " + lista.get(1).getBlanco().getPosicionY());
       // System.out.println(lista.get(2).getBlanco().getPosicionX() + " " + lista.get(2).getBlanco().getPosicionY());    
       //    System.out.println(lista.get(3).getBlanco().getPosicionX() + " " + lista.get(3).getBlanco().getPosicionY());             
           
           
           Nodo harta =  harta();
           actualizar(harta);
           

           lista.clear();
         //  blanco = harta.getBlanco();
         //  negro = harta.getNegro();
        //   manzanas = harta.getManzanas();
         //  actualizaMatriz(harta.getBlanco().getPosicionX(),harta.getNegro().getPosicionY());   
           System.out.println("Decision " + harta.getBlanco().getPosicionX() +harta.getBlanco().getPosicionY() );
            //System.out.println("Decision " + respuesta.getBlanco().getPosicionX() +respuesta.getBlanco().getPosicionY() );
            return harta;
     
    }
 
    public void actualizar(Nodo n)
    {
        
        n.getManzanas().clear();
        if(mundo[n.getBlanco().getPosicionX()][n.getBlanco().getPosicionY()] == 1)
        {
           for(int i=0; i<manzanas.size(); i++) 
           {
              if(( manzanas.get(i).getPosicionX() == n.getBlanco().getPosicionX()) &&( manzanas.get(i).getPosicionY() == n.getBlanco().getPosicionY()) ) 
                  manzanas.remove(i);
                  break;
           }
        }
        
        
         for(int i=0; i<dim; i++)
        {
            for(int j=0; j<dim; j++)
            {
                mundo[i][j] = 0;
            }
        }
       // System.out.println("TAMANO LISTA MANZANAS " + n.getManzanas().size());
        
        /*
        for(int i=0; i< n.getManzanas().size(); i++)
        {
            System.out.println(n.getManzanas().get(i).getPosicionX() + " y " + n.getManzanas().get(i).getPosicionY());
        }*/
        for(int i=0; i<manzanas.size(); i++)
        {
            mundo[manzanas.get(i).getPosicionX()][manzanas.get(i).getPosicionY()] = 1;
        }
        mundo[n.getBlanco().getPosicionX()][n.getBlanco().getPosicionY()] = 2;
        mundo[n.getNegro().getPosicionX()][n.getNegro().getPosicionY()] = 3;
        blanco = n.getBlanco();
        negro = n.getNegro();
       // manzanas = n.getManzanas();
        

    }
    
    public int comeManzana(int x, int y,ArrayList <Manzana> manzanasActual)
    {
        int respuesta = 0;
        for(int i=0; i<manzanasActual.size(); i++)
        {
            if( (manzanasActual.get(i).getPosicionX() == x) && (manzanasActual.get(i).getPosicionY() == y))                            
               respuesta = 1;        
        }
        
        return respuesta;
    }
    
   public int comeManzana2(int x, int y,ArrayList <Manzana> manzanasActual)
    {
        if(mundo[x][y] == 1)
            return 1;
        else 
            return 0;
    }  
 
    
   
    public Nodo hermanos(ArrayList <Nodo> lista,int  tipo)
    {
        
       // System.out.println("LISTA HERMANOS " + lista.size());
        /*
        for(int i=0; i<lista.size(); i++)
        {
            System.out.println(lista.get(i).getNegro().getPosicionX() + " " + lista.get(i).getNegro().getPosicionY() );
        }*/
       // System.out.println("FIN LISTA HERMANOS ");
        ArrayList <Nodo> positivo = new ArrayList <Nodo> ();
        ArrayList <Nodo> cero = new ArrayList <Nodo> ();
        ArrayList <Nodo> negativo = new ArrayList <Nodo> ();
        
        for(int i=0; i<lista.size();i++)
        {
            if(lista.get(i).getUtilidad() == 1)
                positivo.add(lista.get(i));
            else if(lista.get(i).getUtilidad() == 0)
                cero.add(lista.get(i));
            else
                negativo.add(lista.get(i));
        }
        if(tipo == 0)
        {
            if(!(positivo.isEmpty()))
            {
                return positivo.get(0);
            }
            else if(!(cero.isEmpty()))
            {
                return cero.get(0);
            }
            else
                return negativo.get(0);            
        }
        else
        {
            if(!(negativo.isEmpty()))
            {
                return negativo.get(0);
            }
            else if(!(cero.isEmpty()))
            {
                return cero.get(0);
            }
            else
                return positivo.get(0);                
        }   
        

    }
    
    public Nodo utilidadutilidad(ArrayList <Nodo> cola)
    {       
        ArrayList <Nodo> listaHermanos = new ArrayList();
       //System.out.println("LISTA QUE ENTRA ");
        for(int i=0; i< cola.size(); i++)
        {
           // System.out.println(cola.get(i).getNegro().getPosicionX() + " "+ cola.get(i).getNegro().getPosicionY() + " " + cola.get(i).getReferencia().getBlanco().getPosicionX() +  " " + cola.get(i).getReferencia().getBlanco().getPosicionY() );
        }
             
        
        while(cola.size() >= 2)
        {
           // System.out.println("TAMANO " + cola.size());
            
            
            Nodo nodo1 = cola.remove(0);
            Nodo nodo2 = cola.get(0);
            Nodo papa = nodo1.getReferencia();
/*
            System.out.print("Nodo ");
            if((nodo1.getProfundidad() % 2 == 0)&&(nodo1.getReferencia() != null))
                System.out.print(nodo1.getNegro().getPosicionX() + " " + nodo1.getNegro().getPosicionY()+ " " + "pro " + nodo1.getProfundidad()+ " padre " + nodo1.getReferencia().getBlanco().getPosicionX() + " " + nodo1.getReferencia().getBlanco().getPosicionY());
            else if (nodo1.getReferencia() != null)
                System.out.print(nodo1.getBlanco().getPosicionX() + " " + nodo1.getBlanco().getPosicionY()+ " " + "pro " + nodo1.getProfundidad()+ " padre " + nodo1.getReferencia().getNegro().getPosicionX()+ " " + nodo1.getReferencia().getNegro().getPosicionY());
        
            System.out.print(" uti " + nodo1.getUtilidad());
            
            System.out.println(" ");
            
            System.out.print("Nodo2 ");
            if((nodo2.getProfundidad() % 2 == 0) &&(nodo2.getReferencia() != null))
                System.out.print(nodo2.getNegro().getPosicionX() + " " +nodo2.getNegro().getPosicionY() + " " + "pro " + nodo2.getProfundidad() + "padre " + nodo2.getReferencia().getBlanco().getPosicionX() + " " + nodo2.getReferencia().getBlanco().getPosicionY());
            else if (nodo2.getReferencia() != null)
                System.out.print(nodo2.getBlanco().getPosicionX() + " " + nodo2.getBlanco().getPosicionY()+ " " + "pro " + nodo2.getProfundidad() + "padre " + nodo2.getReferencia().getNegro().getPosicionX()+ " " + nodo2.getReferencia().getNegro().getPosicionY());
        
            System.out.print(" uti " + nodo2.getUtilidad());
            
            System.out.println(" ");
            
       //     System.out.println(nodo1.getReferencia().equals(nodo2.getReferencia()));
            
         */   
            int tipo = 1;
            if(nodo1.getReferencia().getProfundidad() % 2 == 0)
            {
            
                 tipo = 0;
            }
               
          
             listaHermanos.add(nodo1);
             
           //  System.out.println("Inicio hermanitos");
            ArrayList<Integer> posiciones = new ArrayList<Integer>();   
            int contador = 0;
            while( (contador < cola.size())&&(cola.get(contador).getProfundidad() == (listaHermanos.get(0).getProfundidad())))
            {
                if(listaHermanos.get(0).getReferencia().equals(cola.get(contador).getReferencia()))
                {
                    posiciones.add(contador);
                }
                contador++;
            }
         //   System.out.println("pos: " +posiciones.size());
            //Cambia lista hermanos
            for(int j=0; j< posiciones.size(); j++)
            {
                listaHermanos.add(cola.get(posiciones.get(j)));
            }

        //    System.out.println("ANTES DE REMOVE " + cola.size());
            int num = 0;
            for(int j=posiciones.size()-1; j>=0; j--)
            {
                num = posiciones.get(j);
             //   System.out.println("ENTROOOOOOOOOOOOOOOO");
                cola.remove(num);
               
            }
           // System.out.println("DESPUES DE REMOVE " + cola.size());
            
            posiciones.clear();
          //   System.out.println("TAMANO LISTA HERMANOS " + listaHermanos.size());
          //   System.out.println("fin Hermanitos");
          /*  
            while(nodo2.getProfundidad() == (papa.getProfundidad()+1))
            {   
                System.out.println("EQUALS " + nodo1.getReferencia().getBlanco().getPosicionX() + " " + nodo1.getReferencia().getBlanco().getPosicionY()
                + nodo2.getReferencia().getBlanco().getPosicionX() + " "+ nodo2.getReferencia().getBlanco().getPosicionY() + nodo1.getReferencia().equals(nodo2.getReferencia()));
                if(papa.equals(nodo2.getReferencia()))
                {
                   listaHermanos.add(nodo2);    
                }               
                    
                if(cola.size() >= 2)
                {
                    nodo1 = cola.remove(0);
                    nodo2 = cola.get(0);
                }
                else
                {                    
                    break;
                }
                    
                
            }//Fin ciclo hermanos*/

        //    System.out.println("TAMANO LISTA HERMANOS " + listaHermanos.size());
            
            if(listaHermanos.get(0).getProfundidad() == 1)
            {
                for(int i=0; i<listaHermanos.size(); i++)
                {
                    lista.add(listaHermanos.get(i));
                }
            }
            
            if(listaHermanos.size()>1)
            {
                nodo1 = hermanos(listaHermanos,tipo);
            }
                            
            
            listaHermanos.clear();
        //    System.out.println("RESUL HERMAN " + nodo1.getNegro().getPosicionX() + " " + nodo1.getNegro().getPosicionY());
          //  System.out.println("TAMANO LISRA FINAL " + lista.size());
            if(nodo1.getProfundidad() != 1)
            {
                nodo1.getReferencia().setUtilidad(nodo1.getUtilidad());
                cola.add(nodo1.getReferencia());
                Collections.sort(cola);

            }
         
            
            
            
            
          //  System.out.println("LISTA PROF 1 TAMANO " + lista.size());

           //  System.out.println("OTRO " + nodo1.getBlanco().getPosicionX() + nodo1.getBlanco().getPosicionY());
         //   System.out.println("OTRO2 " + nodo2.getBlanco().getPosicionX() + nodo2.getBlanco().getPosicionY());

        }
        

        return null;     
    } 


    
    
      
    
public Nodo harta(){
   // System.out.println("TAMANO LISTA PROF1 " + lista.size());
   /* for(int i=0; i<lista.size(); i++)
    {
        System.out.println(lista.get(i).getBlanco().getPosicionX() + " " + lista.get(i).getBlanco().getPosicionY() + " " + lista.get(i).getUtilidad());
    }
    */
        ArrayList <Nodo> positivo = new ArrayList <Nodo> ();
        ArrayList <Nodo> cero = new ArrayList <Nodo> ();
        ArrayList <Nodo> negativo = new ArrayList <Nodo> ();
        Nodo aux = new Nodo(blanco, negro, null, prof, dim, manzanas);
        
        for(int i=0; i<lista.size();i++)
        {
            if(lista.get(i).getUtilidad() == 1)
                positivo.add(lista.get(i));
            else if(lista.get(i).getUtilidad() == 0)
                cero.add(lista.get(i));
            else
                negativo.add(lista.get(i));
        }
        
        if(!(positivo.isEmpty()))
        {
            aux = positivo.get(0);
            for(int i = 0; i<positivo.size(); i++)
            {
               if(aux.getBlanco().getManzanas() < positivo.get(i).getBlanco().getManzanas())
                   aux = positivo.get(i);
            }
            
        }
        else if(!(cero.isEmpty()))
        {
            aux = cero.get(0);
            for(int i = 0; i<cero.size(); i++)
            {
               if(aux.getBlanco().getManzanas() < cero.get(i).getBlanco().getManzanas())
                   aux = cero.get(i);
            }
            
        }
        else if(!(negativo.isEmpty()))
        {
            aux = negativo.get(0);
            for(int i = 0; i<negativo.size(); i++)
            {
               if(aux.getBlanco().getManzanas() < negativo.get(i).getBlanco().getManzanas())
                   aux = negativo.get(i);
            }
                       
        }
        
        return aux;
              
    
}    
  

    public ArrayList <Integer> expandir(int x, int y, int x2, int y2){        

       
        ArrayList <Integer> respuesta = new ArrayList();
        if((x!=0)&&(x!=1)&&(y!=0)&&!((x-2 == x2) && (y-1 == y2)))
            respuesta.add(1); //Paso1

        if((x!=0)&&(x!=1)&&(y!=(dim-1))&&!((x-2 == x2) && (y+1 == y2)))
            respuesta.add(2); //Paso2

        if((x!=0)&&(y!=0)&&(y!=1)&&!((x-1 == x2) && (y-2 == y2)))
            respuesta.add(3); //Paso3

        if((x!=0)&&(y!=(dim-2))&&(y!=(dim-1))&&!((x-1 == x2) && (y+2 == y2)))
            respuesta.add(4); //Paso4

        if((x!=(dim-1))&&(y!=0)&&(y!=1)&&!((x+1 == x2) && (y-2 == y2)))
            respuesta.add(5); //Paso5    

        if((x!=(dim-1))&&(y!=(dim-2))&&(y!=(dim-1))&&!((x+1 == x2) && (y+2 == y2)))
            respuesta.add(6); //Paso6 

        if((x!=(dim-2))&&(x!=(dim-1))&&(y!=0)&&!((x+2 == x2) && (y-1 == y2)))
            respuesta.add(7); //Paso7   

        if((x!=(dim-2))&&(x!=(dim-1))&&(y!=(dim-1))&&!((x+2 == x2) && (y+1 == y2)))
            respuesta.add(8); //Paso8          

            return respuesta;
    }
        

    //Expandir sin ciclos
    //Metodo que expande un nodo, creando sus hijos
    //Input: estado del nodo a expandir
    //Output: Arreglo de enteros con los hijos del nodo
    public ArrayList <Integer> expandir(Nodo actual, Nodo padre, boolean turno){
        
        int x = 0; 
        int y = 0;
        int x2 = 0;
        int y2 = 0; 
        int manzanas = 0;
        
        if(turno)
        {
            x = actual.getBlanco().getPosicionX();
            y = actual.getBlanco().getPosicionY();
            x2 = actual.getNegro().getPosicionX();
            y2 = actual.getNegro().getPosicionY();
            manzanas = actual.getBlanco().getManzanas();
        }
        else
        {
            x2 = actual.getBlanco().getPosicionX();
            y2 = actual.getBlanco().getPosicionY();
            x = actual.getNegro().getPosicionX();
            y = actual.getNegro().getPosicionY();
            manzanas = actual.getNegro().getManzanas();            
        }

        if(padre != null)
        { 
            ArrayList <Integer> respuesta = new ArrayList();
            if((x!=0)&&(x!=1)&&(y!=0)&& !((x-2 == x2) && (y-1 == y2)) && evitarCiclo(x-2,y-1,manzanas,padre,turno))
                respuesta.add(1); //Paso1

            if((x!=0)&&(x!=1)&&(y!=(dim-1))&&!((x-2 == x2) && (y+1 == y2)) && evitarCiclo(x-2,y+1,manzanas,padre,turno))
                respuesta.add(2); //Paso2

            if((x!=0)&&(y!=0)&&(y!=1)&&!((x-1 == x2) && (y-2 == y2)) && evitarCiclo(x-1,y-2,manzanas,padre,turno))
                respuesta.add(3); //Paso3

            if((x!=0)&&(y!=(dim-2))&&(y!=(dim-1))&&!((x-1 == x2) && (y+2 == y2)) && evitarCiclo(x-1,y+2,manzanas,padre,turno))
                respuesta.add(4); //Paso4

            if((x!=(dim-1))&&(y!=0)&&(y!=1)&& !((x+1 == x2) && (y-2 == y2)) && evitarCiclo(x+1,y-2,manzanas,padre,turno))
                respuesta.add(5); //Paso5    

            if((x!=(dim-1))&&(y!=(dim-2))&&(y!=(dim-1))&& !((x+1 == x2) && (y+2 == y2)) && evitarCiclo(x+2,y+2,manzanas,padre,turno))
                respuesta.add(6); //Paso6 

            if((x!=(dim-2))&&(x!=(dim-1))&&(y!=0)&&!((x+2 == x2) && (y-1 == y2)) && evitarCiclo(x+2,y-1,manzanas,padre,turno))
                respuesta.add(7); //Paso7   

            if((x!=(dim-2))&&(x!=(dim-1))&&(y!=(dim-1))&&!((x+2 == x2) && (y+1 == y2)) && evitarCiclo(x+2,y+1,manzanas,padre,turno))
                respuesta.add(8); //Paso8          

            return respuesta;
        }
        
        else
        {
            return expandir(x,y, x2, y2);
        }
    }
    
    


//Metodo que busca en la rama si se encuentra el mismo nodo, para evitar ciclos
//Input: estado del nodo que se quiere crear, nodo padre
//Output: un booleano que indica si se debe crear dicho nodo o no
public boolean evitarCiclo(int x, int y, int manzanas, Nodo padre, boolean turno)
{
    if(turno)
    {
        boolean resultado = true;
        while(padre != null)
        {
            if((padre.getBlanco().getPosicionX() == x) && (padre.getBlanco().getPosicionY() == y)&& (padre.getBlanco().getManzanas() == manzanas))
                resultado = (resultado && false);
            padre = padre.getReferencia();
        }
        return resultado;        
    } 
    else
    {
        boolean resultado = true;
        while(padre != null)
        {
            if((padre.getNegro().getPosicionX() == x) && (padre.getNegro().getPosicionY() == y)&& (padre.getNegro().getManzanas() == manzanas))
                resultado = (resultado && false);
            padre = padre.getReferencia();
        }
        return resultado;        
    }    

}

    public void actualizarNegro(int x, int y){
        if(comeManzana(x,y,manzanas) == 1)
        {      
            //System.out.println("COMIOOOOOOOOOOOOOOO");
            negro.setManzanas(negro.getManzanas()+1);         
        }
        negro.setPosicionX(x);
        negro.setposicionY(y);
        actualizar2();
        
        
        
    }
    
    public void actualizar2(){
        for(int i=0; i<dim; i++)
        {
            for(int j=0; j<dim; j++)
            {
                mundo[i][j] = 0;
            }
        }

        for(int i=0; i<manzanas.size(); i++)
        {
            mundo[manzanas.get(i).getPosicionX()][manzanas.get(i).getPosicionY()] = 1;
        }
        mundo[blanco.getPosicionX()][blanco.getPosicionY()] = 2;
        mundo[negro.getPosicionX()][negro.getPosicionY()] = 3;   
        
       manzanas.clear();
       manzanasTotal = 0;
        for(int i=0;i<dim;i++)
        {
            for(int j=0; j<dim; j++)
            {
                if(mundo[i][j] == 1)
                {
                    Manzana m = new Manzana(i,j);                
                    manzanas.add(m);
                    manzanasTotal++;
                }                 
            }

        }         
        /*
        for(int i=0;i<dim;i++)
        {
            for(int j=0; j<dim; j++)
                System.out.print(" " + mundo[i][j] + " ");
            System.out.println(" ");
        } */       
       
    }
public void actualizaMatriz(int x, int y)
{
    mundo[x][y] = 0;
}    


public Caballo getBlanco()
{
    return blanco;
}

public Caballo getNegro()
{
    return negro;
}

public void setBlanco(Caballo b)
{
    this.blanco = b;
}

public void setNegro(Caballo b)
{
    this.negro = b;
}

public ArrayList <Manzana> getManzanas()
{
    return manzanas;
}
public void setManzanas(ArrayList <Manzana> m)
{
    this.manzanas = m;
}

}

