
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Interfaz extends JFrame implements MouseListener{
    
    private Logica logica;
    private ArrayList <JButton> listaCuadros;
    private ImageIcon imagenBlanco;
    private ImageIcon imagenNegro;
    private ImageIcon imagenManzana;
    private GridLayout layout;

    private int movimientoX;
    private int movimientoY;
    private boolean turnoNegro;
    
    private int tamano;
    private int manzanas;
    private int profundidad;
        
    private Nodo respuesta;
    private Caballo caballoBlanco;
    private Caballo caballoNegro;
   
   
   // private ImageIcon imagenMario;
    


    public Interfaz() {
        
        initComponents();
        jPanel2.setBackground(new Color(0,0,0,0));
        listaCuadros = new ArrayList <JButton> ();      
        turnoNegro = false;     
        manzanaBox.setSelectedItem(0);
        tamanoBox.setSelectedItem(0);
        profundidadText.setEnabled(true);
        profundidadText.setText("8");
        setVisible(true);               
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Mario = new javax.swing.JLabel();
        Smart = new javax.swing.JLabel();
        tiempoL = new javax.swing.JLabel();
        tipoL = new javax.swing.JLabel();
        nodosL = new javax.swing.JLabel();
        profundidadL = new javax.swing.JLabel();
        costoL = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        manzanasBlanco = new javax.swing.JLabel();
        manzanasNegro = new javax.swing.JLabel();
        manzanaBox = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        botonJugar = new javax.swing.JButton();
        tamanoBox = new javax.swing.JComboBox<>();
        reiniciar = new javax.swing.JButton();
        profundidadText = new javax.swing.JTextField();
        empezar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new Color(0,0,0,0));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 700));
        jPanel1.setLayout(null);

        Mario.setFont(new java.awt.Font("Cambria", 2, 20)); // NOI18N
        Mario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/alfa.png"))); // NOI18N
        jPanel1.add(Mario);
        Mario.setBounds(750, 420, 340, 100);

        Smart.setFont(new java.awt.Font("Cambria", 2, 20)); // NOI18N
        Smart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/zero.png"))); // NOI18N
        jPanel1.add(Smart);
        Smart.setBounds(890, 510, 370, 140);

        tiempoL.setFont(new java.awt.Font("Cambria", 2, 20)); // NOI18N
        tiempoL.setText("Caballo Negro");
        jPanel1.add(tiempoL);
        tiempoL.setBounds(760, 330, 210, 30);

        tipoL.setFont(new java.awt.Font("Cambria", 2, 20)); // NOI18N
        tipoL.setText("Tamaño:");
        jPanel1.add(tipoL);
        tipoL.setBounds(760, 40, 110, 30);

        nodosL.setFont(new java.awt.Font("Cambria", 2, 20)); // NOI18N
        nodosL.setText("Manzanas:");
        jPanel1.add(nodosL);
        nodosL.setBounds(760, 80, 130, 30);

        profundidadL.setFont(new java.awt.Font("Cambria", 2, 20)); // NOI18N
        profundidadL.setText("Profundidad:");
        jPanel1.add(profundidadL);
        profundidadL.setBounds(760, 120, 110, 30);

        costoL.setFont(new java.awt.Font("Cambria", 2, 20)); // NOI18N
        costoL.setText("Caballo Blanco");
        jPanel1.add(costoL);
        costoL.setBounds(760, 290, 230, 30);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 700));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 700, 700);
        jPanel2.getAccessibleContext().setAccessibleDescription("");

        manzanasBlanco.setBackground(new java.awt.Color(204, 255, 255));
        manzanasBlanco.setFont(new java.awt.Font("Cambria", 2, 20)); // NOI18N
        jPanel1.add(manzanasBlanco);
        manzanasBlanco.setBounds(990, 290, 50, 30);

        manzanasNegro.setBackground(new java.awt.Color(204, 255, 255));
        manzanasNegro.setFont(new java.awt.Font("Cambria", 2, 20)); // NOI18N
        jPanel1.add(manzanasNegro);
        manzanasNegro.setBounds(990, 330, 50, 30);

        manzanaBox.setFont(new java.awt.Font("Cambria", 2, 18)); // NOI18N
        manzanaBox.setForeground(new java.awt.Color(102, 102, 255));
        manzanaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "3", "5", "7", " " }));
        manzanaBox.setFocusable(false);
        manzanaBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                manzanaBoxItemStateChanged(evt);
            }
        });
        manzanaBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manzanaBoxActionPerformed(evt);
            }
        });
        jPanel1.add(manzanaBox);
        manzanaBox.setBounds(880, 80, 70, 28);

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField2);
        jTextField2.setBounds(980, 290, 70, 30);

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField3);
        jTextField3.setBounds(980, 330, 70, 30);

        botonJugar.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        botonJugar.setForeground(new java.awt.Color(102, 102, 255));
        botonJugar.setText("Jugar");
        botonJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonJugarMouseClicked(evt);
            }
        });
        botonJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonJugarActionPerformed(evt);
            }
        });
        jPanel1.add(botonJugar);
        botonJugar.setBounds(830, 220, 90, 30);

        tamanoBox.setFont(new java.awt.Font("Cambria", 2, 18)); // NOI18N
        tamanoBox.setForeground(new java.awt.Color(102, 102, 255));
        tamanoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3x3", "4x4", "5x5", "6x6" }));
        tamanoBox.setFocusable(false);
        tamanoBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tamanoBoxItemStateChanged(evt);
            }
        });
        tamanoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tamanoBoxActionPerformed(evt);
            }
        });
        jPanel1.add(tamanoBox);
        tamanoBox.setBounds(880, 40, 70, 28);

        reiniciar.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        reiniciar.setForeground(new java.awt.Color(110, 110, 255));
        reiniciar.setText("Reiniciar");
        reiniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reiniciarMouseClicked(evt);
            }
        });
        reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarActionPerformed(evt);
            }
        });
        jPanel1.add(reiniciar);
        reiniciar.setBounds(980, 220, 90, 30);

        profundidadText.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        jPanel1.add(profundidadText);
        profundidadText.setBounds(880, 120, 70, 30);

        empezar.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        empezar.setForeground(new java.awt.Color(153, 102, 255));
        empezar.setText("Empezar");
        empezar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empezarMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                empezarMouseReleased(evt);
            }
        });
        jPanel1.add(empezar);
        empezar.setBounds(1030, 80, 100, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/manzana2.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(1060, 330, 30, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/manzana2.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(1060, 290, 40, 30);

        fondo.setBackground(new java.awt.Color(204, 51, 255));
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/993199-chess.jpg"))); // NOI18N
        fondo.setText("jLabel2");
        fondo.setPreferredSize(new java.awt.Dimension(1200, 700));
        jPanel1.add(fondo);
        fondo.setBounds(0, 0, 1200, 700);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void manzanaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manzanaBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_manzanaBoxActionPerformed

    private void manzanaBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_manzanaBoxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_manzanaBoxItemStateChanged

    private void botonJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonJugarMouseClicked
        // TODO add your handling code here:
        
        

        botonJugar.setEnabled(false); 
        
        respuesta = logica.minimax();
        listaCuadros.get((caballoBlanco.getPosicionX()*tamano) + caballoBlanco.getPosicionY()).setIcon(null);
        caballoBlanco = respuesta.getBlanco();
        caballoNegro = respuesta.getNegro();
        listaCuadros.get((caballoBlanco.getPosicionX()*tamano) + caballoBlanco.getPosicionY()).setIcon(imagenBlanco);
        manzanasBlanco.setText(Integer.toString(respuesta.getBlanco().getManzanas()));
        manzanasNegro.setText(Integer.toString(respuesta.getNegro().getManzanas()));
        turnoNegro = true;
        
        if(caballoBlanco.getManzanas()+caballoNegro.getManzanas() == manzanas)
        {
             JOptionPane.showMessageDialog(null, "Ha ganado");
        }        
        
    }//GEN-LAST:event_botonJugarMouseClicked

    private void tamanoBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tamanoBoxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tamanoBoxItemStateChanged

    private void tamanoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tamanoBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tamanoBoxActionPerformed

    private void reiniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reiniciarMouseClicked
        
        this.dispose();
        Interfaz interfaz = new Interfaz();
                
    }//GEN-LAST:event_reiniciarMouseClicked

    private void empezarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empezarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_empezarMouseClicked

private void turnoNegro(int i){
    
    listaCuadros.get((caballoNegro.getPosicionX()*tamano) + caballoNegro.getPosicionY()).setIcon(null);
    logica.actualizarNegro(movimientoX, movimientoY);
    caballoNegro = logica.getNegro();        
    listaCuadros.get(i).setIcon(imagenNegro);
    manzanasBlanco.setText(Integer.toString(respuesta.getBlanco().getManzanas()));
    manzanasNegro.setText(Integer.toString(caballoNegro.getManzanas()));    
    botonJugar.setEnabled(true);
    
        if(caballoBlanco.getManzanas()+caballoNegro.getManzanas() == manzanas)
        {
            if(caballoBlanco.getManzanas() > caballoNegro.getManzanas())
             JOptionPane.showMessageDialog(null, "Caballo blanco ha ganado");
            else
                JOptionPane.showMessageDialog(null, "Caballo negro ha ganado");
        }    
    
} 

public void pintaCuadros(){

    //Activa las imagenes 
    ImageIcon imagen = new ImageIcon("Imagenes/blanco.png");                
    imagenBlanco = new ImageIcon(imagen.getImage().getScaledInstance(200-(tamano*20), 200-(tamano*20), java.awt.Image.SCALE_DEFAULT)); 
    imagen = new ImageIcon("Imagenes/negro.png");                
    imagenNegro = new ImageIcon(imagen.getImage().getScaledInstance(200-(tamano*20), 200-(tamano*20), java.awt.Image.SCALE_DEFAULT)); 
    imagen = new ImageIcon("Imagenes/manzana.png");                
    imagenManzana = new ImageIcon(imagen.getImage().getScaledInstance(200-(tamano*20), 200-(tamano*20), java.awt.Image.SCALE_DEFAULT));  
    
    //Pinta los cuadros blanco y negro
    boolean color = true;
    if(tamano%2 == 0)
           {
               for(int i=0; i<(tamano*tamano); i++)
               {	
                   JButton cuadro = new JButton();
                   cuadro.addMouseListener(this);
                   if(color)
                       cuadro.setBackground(Color.BLACK);
                   else
                       cuadro.setBackground(Color.WHITE);
                   listaCuadros.add(cuadro);
                   if(!(((i+1)%tamano == 0) && ((i+1)>=tamano)))
                       color = !color;
                   else if (!((i+1)%tamano == 0))
                           color = !color;
               }            
           }
    else
    {            
        for(int i=0; i<(tamano*tamano); i++)
        {	
            JButton cuadro = new JButton();
            cuadro.addMouseListener(this);
            if(color)
                cuadro.setBackground(Color.BLACK);
            else
                cuadro.setBackground(Color.WHITE);
            listaCuadros.add(cuadro);
            color = !color;
        }               
    }
    

        layout = new GridLayout(tamano,tamano,3,3);
        jPanel2.setLayout(layout);     
        
        //Agrega imagenes de caballos y manzanas
        listaCuadros.get( (logica.getBlanco().getPosicionX()*tamano) + logica.getBlanco().getPosicionY() ).setIcon(imagenBlanco);
        listaCuadros.get( (logica.getNegro().getPosicionX()*tamano) + logica.getNegro().getPosicionY() ).setIcon(imagenNegro);
      
       for(int i=0; i<manzanas; i++)
       {
          listaCuadros.get( (logica.getManzanas().get(i).getPosicionX()*tamano) + logica.getManzanas().get(i).getPosicionY()).setIcon(imagenManzana);
       }
              
        //Agrega los jLabel de la lista al panel 2
	for(int i=0 ; i<listaCuadros.size() ; i++) 
	{	
            jPanel2.add(listaCuadros.get(i));
	}    
  
        this.paintAll(this.getGraphics());    
}

    private void empezarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empezarMouseReleased
           
        //Guarda variable no. de manzanas
        int index = manzanaBox.getSelectedIndex();
        if(index == 0)
            manzanas = 1;
        else if(index == 1)
            manzanas = 3;
        else if(index == 2)
            manzanas = 5;
        else if(index == 3)
            manzanas = 7;
        
        //Guarda variable tamaño de la matriz
        index = tamanoBox.getSelectedIndex();
        if(index == 0)
            tamano = 3;
        else if(index == 1)
            tamano = 4;
        else if(index == 2)
            tamano = 5;
        else if(index == 3)
            tamano = 6;    
        
        //Guarda variable profundidad     
        profundidad = Integer.parseInt(profundidadText.getText());
        if (profundidad < 1)
        {    
            profundidad = 1;
            profundidadText.setText("1");
        }
        
        
        logica = new Logica(tamano,manzanas,profundidad);
        
        caballoBlanco = logica.getBlanco();
        caballoNegro = logica.getNegro();       
        
        pintaCuadros();
        empezar.setEnabled(false);
    }//GEN-LAST:event_empezarMouseReleased

    private void botonJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonJugarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonJugarActionPerformed

    private void reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reiniciarActionPerformed
 
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String args[]) {
 

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }


public void habilitar()
{
    botonJugar.setEnabled(true);
}

public void ejecutarHilo(int x, int y, String pasos)
{
    Runnable runnable = () -> {
        ejecucion(x,y,pasos);
    
    };
    Thread hilo = new Thread(runnable);
    hilo.start();
}

public void reiniciar()
{
        jPanel2.setBackground(new Color(0,0,0,0));
        listaCuadros = new ArrayList <JButton> ();      
        turnoNegro = false;     
        manzanaBox.setSelectedItem(0);
        tamanoBox.setSelectedItem(0);
        profundidadText.setEnabled(true);
        profundidadText.setText("10");  
}

public void ejecucion(int x, int y, String pasos)
{/*
    String[] listaPasos = pasos.split("");
    int paso = 0;
    ImageIcon imagen;			
    imagen = new ImageIcon("Imagenes/0.png");		
    ImageIcon imagenEscala = new ImageIcon(imagen.getImage().getScaledInstance(72, 72, java.awt.Image.SCALE_DEFAULT)); 
   
    listaImagenes.set((x*10)+y, imagenEscala);
    
    try{
        for(int i=0; i < listaPasos.length; i++)
        {
            paso = Integer.parseInt(listaPasos[i]);
            if(paso==0)
            {
                listaCuadros.get(((x+1)*10) + y).setIcon(imagenMario);
                listaCuadros.get((x*10) + y).setIcon(listaImagenes.get((x*10) + y));         
                x = x+1;
                Thread.sleep(1000);  
            }
            if(paso==1)
            {
                listaCuadros.get(((x-1)*10) + y).setIcon(imagenMario);
                listaCuadros.get((x*10) + y).setIcon(listaImagenes.get((x*10) + y));
                
                x = x-1;
                Thread.sleep(800);  
            }
            if(paso==2)
            {
                listaCuadros.get((x*10) + y-1).setIcon(imagenMario);
                listaCuadros.get((x*10) + y).setIcon(listaImagenes.get((x*10) + y));
                 
                y = y-1;
                Thread.sleep(800);  
            }
            if(paso==3)
            {
                listaCuadros.get((x*10) + y+1).setIcon(imagenMario);
                listaCuadros.get((x*10) + y).setIcon(listaImagenes.get((x*10) + y));
               
                y=y+1;
                Thread.sleep(800);  
            }            
        } 
    }
            catch(Exception e)
            {
                
            }    
    */
}





    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Mario;
    private javax.swing.JLabel Smart;
    private javax.swing.JButton botonJugar;
    private javax.swing.JLabel costoL;
    private javax.swing.JButton empezar;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JComboBox<String> manzanaBox;
    private javax.swing.JLabel manzanasBlanco;
    private javax.swing.JLabel manzanasNegro;
    private javax.swing.JLabel nodosL;
    private javax.swing.JLabel profundidadL;
    private javax.swing.JTextField profundidadText;
    private javax.swing.JButton reiniciar;
    private javax.swing.JComboBox<String> tamanoBox;
    private javax.swing.JLabel tiempoL;
    private javax.swing.JLabel tipoL;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent click) {
        
        
      		for(int i=0; i<listaCuadros.size(); i++)
		{
			if( click.getSource() == listaCuadros.get(i))
			{                             
				if(turnoNegro)
				{                                    
                                    movimientoX = i/tamano;
                                    movimientoY = i%tamano;
                                    turnoNegro = false;
                                    turnoNegro(i);

				}			
			}
		}

    }

    @Override
    public void mousePressed(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
