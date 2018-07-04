package mineriadatos;

import general.Transformacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.math.MathContext;
import taylor.Rectangulo;

public class Grafica extends javax.swing.JPanel{
    protected Color colorEjes, pincel;
    protected double xMin, xMax, yMin, yMax;
    protected int alto, ancho, ejeX, ejeY;
    protected Transformacion Tx, Ty;
    protected boolean rejilla, ejes;
    private Graphics graph;
    protected String nombreEjeX, nombreEjeY;
    protected Rectangle rectNombreX, rectNombreY;
    
    public Grafica(){
        xMin = 0;    xMax = 10;
        yMin = 0;    yMax = 10;
        ejeX = 50;     ejeY = 50;
        rejilla=true;   ejes = true;
        nombreEjeX = "Eje X";   nombreEjeY = "Eje Y";
        Tx = new Transformacion(xMin, xMax, 50, this.getWidth()-1);
        Ty = new Transformacion(yMin, yMax, 50, this.getHeight()-1);
        colorEjes = Color.orange;
        rectNombreX = new Rectangle();
        rectNombreY = new Rectangle();
        
        setBackground(Color.white);
    }
    public Grafica(double xmin, double xmax, double ymin, double ymax) throws ArithmeticException {
        xMin = xmin;    xMax = xmax;
        yMin = ymin;    yMax = ymax;
        ejeX = 50;     ejeY = 50;
        nombreEjeX = "Eje X";   nombreEjeY = "Eje Y";
        rejilla = true;
        ejes = true;
        colorEjes = Color.orange;
        
        Tx = new Transformacion(xMin, xMax, 50, this.getWidth()-1);
        Ty = new Transformacion(yMin, yMax, 50, this.getHeight()-1);
        rectNombreX = new Rectangle();
        rectNombreY = new Rectangle();
        
        setBackground(Color.white);
    }

    public String getNombreEjeX() {
        return nombreEjeX;
    }

    public void setNombreEjeX(String nombreEjeX) {
        this.nombreEjeX = nombreEjeX;
        this.repaint();
//        try{
//            this.repaint(rectNombreX);
//        }catch(NullPointerException e){
//            
//        }
    }

    public String getNombreEjeY() {
        return nombreEjeY;
    }

    public void setNombreEjeY(String nombreEjeY) {
        this.nombreEjeY = nombreEjeY;
        this.repaint();
    }
    
    public void setPuerto(double xmin, double xmax, double ymin, double ymax) throws ArithmeticException {
        if( xmin==xmax )
            throw new ArithmeticException();
        xMin = xmin;    xMax = xmax;
        yMin = ymin;    yMax = ymax;
    }
    
    public void setPuerto(Rectangulo area) throws ArithmeticException {
        if( area.xmin==area.xmax || area.ymin==area.ymax )
            throw new ArithmeticException();
        xMin = area.xmin;    xMax = area.xmax;
        yMin = area.ymin;    yMax = area.ymax;
    }

    public void setRejilla(boolean rejilla) {
        this.rejilla = rejilla;
    }
        
    public void setEjes(boolean ejes) {
        this.ejes = ejes;
    }
    
    public void setColorEjes(Color color) {
        colorEjes = color;
    }
    
    public void setColorPincel(Color color) {
        pincel = color;
    }
    
    @Override
    public void paint(Graphics g){
//        if( ancho>1 && alto>1 ){
            super.paint(g);
//                System.out.println("super.paint() [class=Plano]");
            graph = g;
            graph.setColor(pincel);
//            System.out.println("graph: [class=Plano]"+graph);
            alto = this.getHeight();
            ancho = this.getWidth();
            Tx.setTransformacion(xMin, xMax, 50, ancho-5);
            Ty.setTransformacion(yMin, yMax, 50, alto-5);

//                System.out.println("Dibujando Rejilla ... ");
            if( rejilla )
                dibujarRejilla(g);
//            System.out.println("Rejilla Dibujada");
            if( ejes )
                dibujarEjes(g);
//            System.out.println("Ejes Dibujados");

            g.setColor(Color.gray);
            g.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 13));
            FontMetrics fm = g.getFontMetrics();
            int anchoNombEje = fm.stringWidth(nombreEjeX);
            int pixNombEje = (ancho-ejeY-anchoNombEje)/2;
            g.drawString(nombreEjeX, pixNombEje+ejeY, alto-5);
            rectNombreX.setBounds(pixNombEje+ejeY, alto-5-13, anchoNombEje, 13);

            Graphics2D g2d = (Graphics2D) g;
            anchoNombEje = fm.stringWidth(nombreEjeY);
            pixNombEje = (alto-ejeX-anchoNombEje)/2;
            g2d.translate(15, alto-(pixNombEje+ejeX));
            g2d.rotate(Math.PI/2*3); // 270°
            g2d.translate(-15, pixNombEje+ejeX - alto);
            g2d.drawString(nombreEjeY, 15, alto-(pixNombEje+ejeX));
//        }
    }

    public void setGraph(Graphics graph) {
        this.graph = graph;
    }

    public Graphics getGraph() {
        return graph;
    }

    public Transformacion getTx() {
        return Tx;
    }

    public Transformacion getTy() {
        return Ty;
    }
    
    public void drawPoint(double x, double y, int size){
        graph.fillOval(Tx.transforma(x), yInv(Ty.transforma(y)), size, size);
    }
    
    public void drawLine(double x1, double y1, double x2, double y2){
//        System.out.println("\tGraficando; ("+Tx.transforma(x1)+","+yInv(Ty.transforma(y1))+")("+Tx.transforma(x2)+","+yInv(Ty.transforma(y2))+")");
        graph.drawLine(Tx.transforma(x1), yInv(Ty.transforma(y1)), Tx.transforma(x2), yInv(Ty.transforma(y2)));
    }
    
    public void drawRect(double x, double y, double width, double height){
        graph.drawRect(Tx.transforma(x), yInv(Ty.transforma(y)), Tx.transforma(width), yInv(Ty.transforma(height)) );
    }
    
    public void drawOval(double x, double y, double width, double height){
        graph.drawOval(Tx.transforma(x), yInv(Ty.transforma(y)), Tx.transforma(width), yInv(Ty.transforma(height)) );
    }
    private int yInv(int y){
        return alto - y - 1;
    }
    
    private void dibujarEjes(Graphics g){
        g.setColor(colorEjes);
        
        int i, TxInt, TyEn0, TyInt, TxEn0, numInterv;
        double rango, intervalo, ptoInterv;
        String strnum;
        
        // EJE X
        numInterv = ancho/40;
        rango = xMax-xMin;
//        System.out.println("\nnumIntervalo\t= "+numInterv);
//        System.out.println("\nRango antes\t= "+rango);
        for(i=1; rango<numInterv; i*=10)
            rango *= 10;
        intervalo = Math.ceil(rango/numInterv);
        intervalo /= i;
//        System.out.println("\nRango\t= "+rango);
//        System.out.println("\nintervalo\t= "+intervalo);
        TyEn0 = Ty.transforma(yMin);
        TxEn0 = Tx.transforma(xMin);
        double inicio = xMin>0 ? xMin : 0;
        for( ptoInterv = -intervalo; ptoInterv>=xMin-intervalo/10; ptoInterv-=intervalo){
//        System.out.println("\nptoInterv NEG\t= "+ptoInterv);
            TxInt = Tx.transforma(ptoInterv);
            g.drawLine(TxInt, yInv(TyEn0), TxInt, yInv(TyEn0-10));
            strnum = formatear(ptoInterv, intervalo);
            g.drawString(strnum, TxInt-10, yInv(TyEn0-25));
        }
        for( ptoInterv = inicio+intervalo; ptoInterv<=xMax+intervalo/10; ptoInterv+=intervalo){
            TxInt = Tx.transforma(ptoInterv);
            g.drawLine(TxInt, yInv(TyEn0), TxInt, yInv(TyEn0-10));
            strnum = formatear(ptoInterv, intervalo);
            g.drawString(strnum, TxInt-10, yInv(TyEn0-25));
        }
        
        // EJE Y
        numInterv = alto/40;
        rango = yMax-yMin;
        for(i=1; rango<numInterv; i*=10)
            rango *= 10;
        intervalo = Math.ceil(rango/numInterv);
        intervalo /= i;
        inicio = yMin>0 ? yMin : 0;
        for(ptoInterv = -intervalo ; ptoInterv>=yMin; ptoInterv-=intervalo){
            TyInt = this.getHeight()-Ty.transforma(ptoInterv);
            g.drawLine(TxEn0, TyInt, TxEn0+10, TyInt);
            strnum = formatear(ptoInterv, intervalo);
            g.drawString(strnum, TxEn0-25, TyInt);
        }
        for(ptoInterv = inicio+intervalo ; ptoInterv<=yMax; ptoInterv+=intervalo){
            TyInt = this.getHeight()-Ty.transforma(ptoInterv);
            g.drawLine(TxEn0, TyInt, TxEn0+10, TyInt);
            strnum = formatear(ptoInterv, intervalo);
            g.drawString(strnum, TxEn0-25, TyInt);
        }
        
        //g.drawString(null, i, i);
//        System.out.println(" ----> xmin = "+xMin);
//        System.out.println(" ----> Tx = "+Tx);
//        System.out.println(" ----> Tx(xmin) = "+Tx.transforma(xMin));
        g.drawLine(Tx.transforma(xMin), yInv(Ty.transforma(yMin)), Tx.transforma(xMax), yInv(Ty.transforma(yMin)));   // X
        g.drawLine(Tx.transforma(xMin), yInv(Ty.transforma(yMin)), Tx.transforma(xMin), yInv(Ty.transforma(yMax)));        // Y
    }
    
    private void dibujarRejilla(Graphics g) /*throws ArithmeticException*/ {
        //System.out.println("ClassPlano: Transformacion Plano: "+Tx+", "+Ty);
        g.setColor( new Color(235,235,235) );
        int i, TxInt, TyInt, numInterv;
        double rango, intervalo, ptoInterv;
        
//            System.out.println("Dibujando Eje X");
        // Eje X
        numInterv = ancho/40;
//            System.out.println("NumInterv = "+numInterv);
        rango = xMax-xMin;
//            System.out.println("Rango Antes = "+rango);
        for(i=1; rango<numInterv; i*=10)
            rango *= 10;
//            System.out.println("Rango = "+rango);
        intervalo = Math.ceil(rango/numInterv);
//            System.out.println("Intervalo Antes = "+intervalo);
        intervalo /= i;
//            System.out.println("Intervalo = "+intervalo);
        for( ptoInterv=-intervalo; ptoInterv>=xMin; ptoInterv-=intervalo){
            //System.out.println("PtoInt = "+ptoInterv);
            TxInt = Tx.transforma(ptoInterv);
            g.drawLine(TxInt, yInv(ejeX), TxInt, yInv(Ty.transforma(yMax)) ); // rejilla Y (vertical)
        }
//            System.out.println("FOR 2");
        for( ptoInterv=intervalo; ptoInterv<=xMax; ptoInterv+=intervalo){
            //System.out.println("PtoInt = "+ptoInterv);
            TxInt = Tx.transforma(ptoInterv);
            g.drawLine(TxInt, yInv(ejeX), TxInt, yInv(Ty.transforma(yMax)) ); // rejilla Y (vertical)
        }
     
//            System.out.println("\nDibujando Eje Y");
            
        // EJE Y
        numInterv = alto/40;
//            System.out.println("NumInterv = "+numInterv);
        rango = yMax-yMin;
//            System.out.println("Rango Antes = "+rango);
        for(i=1; rango<numInterv; i*=10)
            rango *= 10;
//            System.out.println("Rango = "+rango);
        //intervalo = (int) (rango/numInterv);
        intervalo = Math.ceil(rango/numInterv);
//            System.out.println("Intervalo Antes = "+intervalo);
        intervalo /= i;
//            System.out.println("Intervalo = "+intervalo);
        
//            System.out.println("Inicio for 1");
//            System.out.println("yMin = "+yMin);
            //System.out.println("Ty(0) = "+Ty.transforma(0));
        for(ptoInterv = -intervalo ; ptoInterv>=yMin; ptoInterv-=intervalo){
            //System.out.println("PtoInt = "+ptoInterv);
            TyInt = this.getHeight()-Ty.transforma(ptoInterv);
            g.drawLine(ejeY, TyInt, Tx.transforma(xMax), TyInt);   // rejilla X
        }
//            System.out.println("Inicio for 2");
//            System.out.println("yMax = "+yMax);
//            System.out.println("Intervalo = "+intervalo);
        for(ptoInterv = intervalo ; ptoInterv<=yMax; ptoInterv+=intervalo){
            //System.out.println("PtoInt = "+ptoInterv);
            TyInt = this.getHeight()-Ty.transforma(ptoInterv);
            g.drawLine(ejeY, TyInt, Tx.transforma(xMax), TyInt);   // rejilla X
        }
//            System.out.println("Fin Metodo Rejilla()");
    }
    
    private String formatear(double num, double intervalo){
        String strnum;
        
        if( intervalo>10 ){
            if( num>Long.MAX_VALUE || num<Long.MIN_VALUE ){
                String strDouble = String.valueOf(num);
//                System.out.println("num="+num+"\tstrDouble="+strDouble);
                BigDecimal bdNum = new BigDecimal(strDouble);
                strnum = ""+bdNum.round(MathContext.UNLIMITED);
            }
            else {
                long auxPto = Math.round(num);
                strnum = String.valueOf(auxPto);
            }
        }
        else{
            strnum = String.format("%.3f", num);
            int k, index = strnum.indexOf('.');
            for(k=strnum.length()-1;  k>=index; k--){
                if( strnum.charAt(k)!='0' )
                    break;
            }
            if( k==index )
                strnum = strnum.substring(0,k);
            else if( k!=strnum.length()-1 )
                strnum = strnum.substring(0,k+1);
        }
        return strnum;
    }
    
    public static void main(String[] args){
        javax.swing.JFrame v = new javax.swing.JFrame();
        v.setBounds(20,20, 400,400);
        v.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Grafica graf = new Grafica(0,10, 0,10);
        graf.setNombreEjeX("Eje X");
        graf.setNombreEjeY("Eje Y");
        v.getContentPane().add(graf, BorderLayout.CENTER);
        v.setVisible(true);
    }
}
