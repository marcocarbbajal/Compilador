/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * interfaz.java
 *
 * Created on 17/08/2011, 10:00:34 PM
 */
package Lexico_Sintactico_Semantico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
 
/**
 *
 * @author Marco Carbajal
 */
public class interfaz extends javax.swing.JFrame {
 Color colorfondodefault;
    Highlighter hilit;
    Highlighter.HighlightPainter painter;
 
    private File fichero;
    private String nombre;
    private String nombre_archivo;
    private boolean bandera;
    List<identificador> tokenslist;
    static public String resultado;
    static public int linea;
    static public int columna;
    	public static JTextArea fuente;
	public static JTextArea lines;
        private JTextField editor;
    public static JTextArea status;
    public static JScrollPane jsp;
    public String buscar;
    public int end;
    public int  linenum,columnnum;
    Object[][] matriz;
   // JTextArea fuente;
    //JTextArea lineas;
    /** Creates new form interfaz */
    public  interfaz() {
initComponents();     
//**************************Estirar Tabla**************************

    // fuente=LineNumbering.jta;
//********************LINEAS*****************************************
// fuente=LineNumbering.jta;
       fuente = new JTextArea();
       //txtbuscar.setEnabled(false);
       
       lines = new JTextArea("1");
       fuente.setEnabled(false);
       fuente.setBackground(new java.awt.Color(153, 153, 153));
     lineacolumna.setBorder(null);
       lineacolumna.setBackground(new java.awt.Color(153, 153, 153));
//     
//       status.setBackground(Color.yellow);
        resultadoText.setEnabled(false);
        
        jsp = new JScrollPane();
       jsp.setBackground(new java.awt.Color(153, 153, 153));
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        fuente.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //editor = new JTextArea();

        fuente.addCaretListener(new CaretListener() {
            // Each time the caret is moved, it will trigger the listener and its method caretUpdate.
            // It will then pass the event to the update method including the source of the event (which is our textarea control)
            public void caretUpdate(CaretEvent e) {
                JTextArea editArea = (JTextArea)e.getSource();

                // Lets start with some default values for the line and column.
                 linenum = 1;
                 columnnum = 1;

                // We create a try catch to catch any exceptions. We will simply ignore such an error for our demonstration.
                try {
                    // First we find the position of the caret. This is the number of where the caret is in relation to the start of the JTextArea
                    // in the upper left corner. We use this position to find offset values (eg what line we are on for the given position as well as
                    // what position that line starts on.
                    int caretpos = editArea.getCaretPosition();
                    linenum = editArea.getLineOfOffset(caretpos);

                    // We subtract the offset of where our line starts from the overall caret position.
                    // So lets say that we are on line 5 and that line starts at caret position 100, if our caret position is currently 106
                    // we know that we must be on column 6 of line 5.
                    columnnum = caretpos - editArea.getLineStartOffset(linenum);

                    // We have to add one here because line numbers start at 0 for getLineOfOffset and we want it to start at 1 for display.
                    linenum += 1;
                }
                catch(Exception ex) { }

                // Once we know the position of the line and the column, pass it to a helper function for updating the status bar.
                updateStatus(linenum, columnnum);
            }
        });

        // Add the fields to the layout, the editor in the middle and the status at the bottom.
     //   fuente.add(editor, BorderLayout.CENTER);

        status = new JTextArea();
        //fuente.add(txtbuscar, BorderLayout.SOUTH);

        // Give the status update value
        updateStatus(1,1);
        
        
       ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		lines.setBackground(Color.LIGHT_GRAY);
		lines.setEditable(false);
                
		fuente.getDocument().addDocumentListener(new DocumentListener(){
			public String getText(){
				int caretPosition = fuente.getDocument().getLength();
				Element root = fuente.getDocument().getDefaultRootElement();
				String text = "1" + System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
					text += i + System.getProperty("line.separator");
				}
				return text;
			}
			@Override
			public void changedUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
			@Override
			public void insertUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
			@Override
			public void removeUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
		});
 
		jsp.getViewport().add(fuente);
		jsp.setRowHeaderView(lines);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                
                javax.swing.GroupLayout Layoutcuerpo = new javax.swing.GroupLayout(cuerpo);
        cuerpo.setLayout(Layoutcuerpo);
        Layoutcuerpo.setHorizontalGroup(
            Layoutcuerpo.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
        );
        Layoutcuerpo.setVerticalGroup(
            Layoutcuerpo.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
        );
            fuente.setBackground(new java.awt.Color(102, 102, 102));
            jsp.setBackground(new java.awt.Color(102, 102, 102));
             
             // fuente.setColumns(20);
        fuente.setForeground(Color.white);
        fuente.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        fuente.setRows(5);
            status.setBackground(new java.awt.Color(102, 102, 102));
       // fuente.setColumns(20);
        status.setForeground(Color.white);
        status.setFont(new java.awt.Font("Calibri", 3, 16)); // NOI18N
        
        lineacolumna.setForeground(Color.DARK_GRAY);
        lineacolumna.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        
        lines.setBackground(new java.awt.Color(102, 102, 102));
       // lines.setColumns(20);
        lines.setForeground(Color.LIGHT_GRAY);
        lines.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lines.setRows(5);
        fuente.setCaretColor(Color.LIGHT_GRAY);
        
        
        final UndoManager undo = new UndoManager();
    Document doc = fuente.getDocument();
    
    // Listen for undo and redo events
    doc.addUndoableEditListener(new UndoableEditListener() {
        public void undoableEditHappened(UndoableEditEvent evt) {
            undo.addEdit(evt.getEdit());
        }
    });
    
    // Create an undo action and add it to the text component
    fuente.getActionMap().put("Undo",
        new AbstractAction("Undo") {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (undo.canUndo()) {
                        undo.undo();
                    }
                } catch (CannotUndoException e) {
                }
            }
       });
    
    // Bind the undo action to ctl-Z
    fuente.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
    
    // Create a redo action and add it to the text component
    fuente.getActionMap().put("Redo",
        new AbstractAction("Redo") {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (undo.canRedo()) {
                        undo.redo();
                    }
                } catch (CannotRedoException e) {
                }
            }
        });
    
    fuente.getActionMap().put("find",
        new AbstractAction("find") {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (txtbuscar.isEnabled() == false) {
                        txtbuscar.setText("");
                        txtbuscar.requestFocus();
                        txtbuscar.setEnabled(true);
                        
                    }
                } catch (Exception e) {
                }
            }
        });
    
    
        txtbuscar.getActionMap().put("ENTER",
        new AbstractAction("ENTER") {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (txtbuscar.isEnabled() == true) {
                       siguienteTexto();
                    }
                } catch (Exception e) {
                }
            }
        });
    
        
          // Bind the redo action to enter
       // KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true);
    txtbuscar.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"ENTER");

        
          // Bind the redo action to ctl-Y
    fuente.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");

    fuente.getInputMap().put(KeyStroke.getKeyStroke("control F"), "find");
    
       ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	hilit = new DefaultHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(new java.awt.Color(204, 153, 0));
        fuente.setHighlighter(hilit);
        
        colorfondodefault = txtbuscar.getBackground();
     
        txtbuscar.addKeyListener(new KeyListener(){


   @Override
   public void keyPressed(KeyEvent e) {
    if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
     hilit.removeAllHighlights();
              txtbuscar.setText("");
              txtbuscar.setBackground(colorfondodefault);
    }
   }


   @Override
   public void keyReleased(KeyEvent e) {
                try {
                    // TODO Auto-generated method stub
                    buscarTexto();
                } catch (BadLocationException ex) {
                    Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
   }


   @Override
   public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
   }
        });
         
    }
     
    
    private void updateStatus(int linenumber, int columnnumber) {
        status.setText("Linea: " + linenumber + " Columna: " + columnnumber);
                lineacolumna.setText(status.getText());
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TablaFrame = new javax.swing.JFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultadoText = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        boton_abrir = new javax.swing.JButton();
        boton_guardar_como = new javax.swing.JButton();
        boton_guardar = new javax.swing.JButton();
        boton_nuevo = new javax.swing.JButton();
        boton_analizar = new javax.swing.JButton();
        Cuadruplos = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        cuerpo = new javax.swing.JPanel();
        lineacolumna = new javax.swing.JTextField();
        boton_cerrar = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        analizar = new javax.swing.JMenu();
        menu_nuevo = new javax.swing.JMenuItem();
        menu_abrir = new javax.swing.JMenuItem();
        menu_guardar = new javax.swing.JMenuItem();
        menu_guardar_como = new javax.swing.JMenuItem();
        menu_analizar = new javax.swing.JMenuItem();
        menu_Tabla = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        TablaFrame.setTitle("Tabla de Símbolos");

        jScrollPane3.setFocusable(false);
        jScrollPane3.setName(""); // NOI18N

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(51, 51, 51));
        jTable1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setFont(new java.awt.Font("Calibri", 1, 14));
        jTable1.setForeground(new java.awt.Color(204, 204, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", "", "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAlignmentX(2.0F);
        jTable1.setAlignmentY(2.0F);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTable1.setDragEnabled(true);
        jTable1.setEnabled(false);
        jTable1.setFillsViewportHeight(true);
        jTable1.setMinimumSize(new java.awt.Dimension(120, 120));
        jTable1.setPreferredSize(new java.awt.Dimension(1200, 0));
        jTable1.setRequestFocusEnabled(false);
        jScrollPane3.setViewportView(jTable1);

        TablaFrame.getContentPane().add(jScrollPane3, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compilador de Ada");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        resultadoText.setBackground(new java.awt.Color(51, 51, 51));
        resultadoText.setColumns(20);
        resultadoText.setFont(new java.awt.Font("Miriam Fixed", 1, 14));
        resultadoText.setForeground(new java.awt.Color(0, 153, 153));
        resultadoText.setLineWrap(true);
        resultadoText.setRows(3);
        resultadoText.setCaretColor(new java.awt.Color(255, 0, 0));
        resultadoText.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        resultadoText.setDisabledTextColor(new java.awt.Color(0, 204, 255));
        resultadoText.setSelectionColor(new java.awt.Color(0, 0, 255));
        jScrollPane2.setViewportView(resultadoText);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        boton_abrir.setBackground(new java.awt.Color(153, 153, 153));
        boton_abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/abrir2.png"))); // NOI18N
        boton_abrir.setToolTipText("abrir");
        boton_abrir.setBorder(null);
        boton_abrir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton_abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_abrirActionPerformed(evt);
            }
        });

        boton_guardar_como.setBackground(new java.awt.Color(153, 153, 153));
        boton_guardar_como.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardarcomo.png"))); // NOI18N
        boton_guardar_como.setToolTipText("guardar como");
        boton_guardar_como.setBorder(null);
        boton_guardar_como.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton_guardar_como.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_guardar_comoActionPerformed(evt);
            }
        });

        boton_guardar.setBackground(new java.awt.Color(153, 153, 153));
        boton_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        boton_guardar.setToolTipText("guardar");
        boton_guardar.setBorder(null);
        boton_guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_guardarActionPerformed(evt);
            }
        });

        boton_nuevo.setBackground(new java.awt.Color(153, 153, 153));
        boton_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        boton_nuevo.setToolTipText("nuevo");
        boton_nuevo.setBorder(null);
        boton_nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_nuevoActionPerformed(evt);
            }
        });

        boton_analizar.setBackground(new java.awt.Color(153, 153, 153));
        boton_analizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/analizar2.png"))); // NOI18N
        boton_analizar.setToolTipText("analizar");
        boton_analizar.setBorder(null);
        boton_analizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton_analizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_analizarActionPerformed(evt);
            }
        });

        Cuadruplos.setText("Cuadruplos");
        Cuadruplos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CuadruplosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boton_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(boton_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(boton_guardar_como, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(boton_abrir, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(boton_analizar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(Cuadruplos, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(boton_guardar, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(boton_nuevo, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(boton_guardar_como, 0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(boton_abrir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGap(37, 37, 37))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Cuadruplos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(boton_analizar, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator2.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados del analisis"));

        cuerpo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout cuerpoLayout = new javax.swing.GroupLayout(cuerpo);
        cuerpo.setLayout(cuerpoLayout);
        cuerpoLayout.setHorizontalGroup(
            cuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 771, Short.MAX_VALUE)
        );
        cuerpoLayout.setVerticalGroup(
            cuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );

        lineacolumna.setEditable(false);

        boton_cerrar.setBackground(new java.awt.Color(153, 153, 153));
        boton_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar.png"))); // NOI18N
        boton_cerrar.setToolTipText("cerrar");
        boton_cerrar.setBorder(null);
        boton_cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_cerrarActionPerformed(evt);
            }
        });

        txtbuscar.setBackground(new java.awt.Color(153, 153, 153));
        txtbuscar.setFont(new java.awt.Font("Calibri", 3, 16));
        txtbuscar.setForeground(new java.awt.Color(51, 51, 51));
        txtbuscar.setText("Buscar");
        txtbuscar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 51, 51)));
        txtbuscar.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtbuscar.setEnabled(false);
        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lineacolumna)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cuerpo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(boton_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_cerrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lineacolumna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(153, 153, 153));

        analizar.setText("Menu");

        menu_nuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menu_nuevo.setText("Nuevo");
        menu_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_nuevoActionPerformed(evt);
            }
        });
        analizar.add(menu_nuevo);

        menu_abrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        menu_abrir.setText("Abrir");
        menu_abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_abrirActionPerformed(evt);
            }
        });
        analizar.add(menu_abrir);

        menu_guardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menu_guardar.setText("Guardar");
        menu_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_guardarActionPerformed(evt);
            }
        });
        analizar.add(menu_guardar);

        menu_guardar_como.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menu_guardar_como.setText("Guardar como");
        menu_guardar_como.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_guardar_comoActionPerformed(evt);
            }
        });
        analizar.add(menu_guardar_como);

        menu_analizar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK));
        menu_analizar.setText("Analizar");
        menu_analizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_analizarActionPerformed(evt);
            }
        });
        analizar.add(menu_analizar);

        menu_Tabla.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK));
        menu_Tabla.setText("Mostrar Tabla");
        menu_Tabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_TablaActionPerformed(evt);
            }
        });
        analizar.add(menu_Tabla);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem5.setText("Salir");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        analizar.add(jMenuItem5);

        jMenuBar1.add(analizar);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_nuevoActionPerformed
  fuente.setEnabled(true); 
    fuente.setBackground(Color.DARK_GRAY);
    jsp.setBackground(Color.DARK_GRAY);
    
    status.setBackground(Color.DARK_GRAY);
    //lineacolumna.setBackground(Color.DARK_GRAY);
    
    fuente.setText("");
        resultadoText.setText("");

        resetearTabla();

}//GEN-LAST:event_boton_nuevoActionPerformed

    
    private void menu_analizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_analizarActionPerformed
       
       
        resultadoText.setText("");
        crear_archivo_fuente();
        try {
            probarLexerFile();
        } catch (IOException ex) {
            Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         //fichero.delete();
    }//GEN-LAST:event_menu_analizarActionPerformed

    private void menu_abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_abrirActionPerformed
        
       abrir();
       bandera=true;
       fuente.setEnabled(true); 
      fuente.setBackground(Color.DARK_GRAY);
      jsp.setBackground(Color.DARK_GRAY);
     
      status.setBackground(Color.DARK_GRAY);
    //  lineacolumna.setBackground(Color.DARK_GRAY);
     
      resetearTabla();
    }//GEN-LAST:event_menu_abrirActionPerformed

    private void menu_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_guardarActionPerformed
              
        guardar();  
        
    }//GEN-LAST:event_menu_guardarActionPerformed

    private void menu_guardar_comoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_guardar_comoActionPerformed
       
        guardar_como();  
    }//GEN-LAST:event_menu_guardar_comoActionPerformed

    private void boton_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_guardarActionPerformed
      
        
        guardar();
              
    }//GEN-LAST:event_boton_guardarActionPerformed

    private void boton_guardar_comoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_guardar_comoActionPerformed
       
        
        guardar_como();
        

    }//GEN-LAST:event_boton_guardar_comoActionPerformed

    private void boton_abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_abrirActionPerformed
       abrir();
       bandera=true;
       
       fuente.setEnabled(true); 
       fuente.setBackground(Color.DARK_GRAY);
      jsp.setBackground(Color.DARK_GRAY);
      
    //   lineacolumna.setBackground(Color.DARK_GRAY);
       
       resetearTabla();

    }//GEN-LAST:event_boton_abrirActionPerformed

    private void boton_analizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_analizarActionPerformed

         lnombre = new ArrayList();
     ltipo = new ArrayList();
     lparametro = new ArrayList();
     ltipopara = new ArrayList();
     ltiporeturn = new ArrayList();
     lambito = new ArrayList();
     larreglo = new ArrayList();
     ldimension = new ArrayList();
     ltipopar = new ArrayList();
    loffset = new ArrayList<Integer>();
    lprofundidad = new ArrayList<Integer>();
    
   
        matriz = null;
       
        resultado = "";
        resultadoText.setText("");
        crear_archivo_fuente();
        try {
            probarLexerFile();
            
            
            resultado=resultado+"Entrada Exitosa";
        } catch (IOException ex) {
           // Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            resultado=resultado+"Entrada Incorrecta";
        }

    }//GEN-LAST:event_boton_analizarActionPerformed

    private void menu_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_nuevoActionPerformed
     
        fuente.setText("");
        resultadoText.setText("");

        resetearTabla();
        
    }//GEN-LAST:event_menu_nuevoActionPerformed

    private void boton_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_cerrarActionPerformed
        
        
        int result = JOptionPane.showConfirmDialog(null,"Desea guardar el archivo",null,JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                   
        switch(result)  {
                    case JOptionPane.YES_OPTION:
                                                   if(bandera){
                                                        guardar();     
                                                    }else guardar_como();
                                                   bandera=false;
                                                   break;
                    case JOptionPane.NO_OPTION:
                                                   break;
                    case JOptionPane.CANCEL_OPTION:
                                                   break;
                        
                    }
        fuente.setText("");
        resultadoText.setText("");
        fuente.setEnabled(false);
        fuente.setBackground(new java.awt.Color(153, 153, 153));
        jsp.setBackground(new java.awt.Color(153, 153, 153));
       
        status.setBackground(new java.awt.Color(153, 153, 153));
    //    lineacolumna.setBackground(new java.awt.Color(153, 153, 153));
       
        resetearTabla();
        
    }//GEN-LAST:event_boton_cerrarActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       
        System.exit(DISPOSE_ON_CLOSE);
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void menu_TablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_TablaActionPerformed
        // TODO add your handling code here:
        TablaFrame.setSize(400, 470);
        TablaFrame.setVisible(true);
        tablaResultado();
    }//GEN-LAST:event_menu_TablaActionPerformed

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void CuadruplosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CuadruplosActionPerformed
        System.out.println("\n\n********************************************************************\n");
        System.out.println("********************************************************************\n");
        codigointermedio.CodigoIntermedio.imprimirCuadruplos();
        // TODO add your handling code here:
    }//GEN-LAST:event_CuadruplosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new interfaz().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cuadruplos;
    private javax.swing.JFrame TablaFrame;
    private javax.swing.JMenu analizar;
    private javax.swing.JButton boton_abrir;
    private javax.swing.JButton boton_analizar;
    private javax.swing.JButton boton_cerrar;
    private javax.swing.JButton boton_guardar;
    private javax.swing.JButton boton_guardar_como;
    private javax.swing.JButton boton_nuevo;
    private javax.swing.JPanel cuerpo;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField lineacolumna;
    private javax.swing.JMenuItem menu_Tabla;
    private javax.swing.JMenuItem menu_abrir;
    private javax.swing.JMenuItem menu_analizar;
    private javax.swing.JMenuItem menu_guardar;
    private javax.swing.JMenuItem menu_guardar_como;
    private javax.swing.JMenuItem menu_nuevo;
    public static javax.swing.JTextArea resultadoText;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
     
    
    static ArrayList lnombre = new ArrayList();
    static ArrayList ltipo = new ArrayList();
    static ArrayList lparametro = new ArrayList();
    static ArrayList ltipopara = new ArrayList();
    static ArrayList ltiporeturn = new ArrayList();
    static ArrayList lambito = new ArrayList();
    static ArrayList larreglo = new ArrayList();
    static ArrayList ldimension = new ArrayList();
    static ArrayList ltipopar = new ArrayList();
    static ArrayList<Integer> loffset = new ArrayList<Integer>();
    static ArrayList<Integer> lprofundidad = new ArrayList<Integer>();
    
    
    
    
    public void probarLexerFile() throws IOException{
        resultado="";
        tokenslist = new LinkedList<identificador>();
        Reader reader = new BufferedReader(new FileReader("fuente.txt"));
        Lexer lexer = new Lexer (reader);
        try {
        
        Lexer lexer1 = new Lexer (new FileReader("fuente.txt"));
        parser p = new parser( lexer1 );
        p.parse();
        AnalizadorLexico(lexer1);
        if (resultado.equalsIgnoreCase("")){
        resultado = "";
        try {
        
        Lexer lexer2 = new Lexer (new FileReader("fuente.txt"));
        parser_semantico p2 = new parser_semantico( lexer2 );
        p2.parse();
        AnalizadorLexico(lexer2);
        resultado = resultado+("\nEntrada Exitosa\n");
        if(resultado.equals("\nEntrada Exitosa\n")){
        resultado = "";
        try {
        
        Lexer lexer3 = new Lexer (new FileReader("fuente.txt"));
        codigointermedio.parser p3 = new codigointermedio.parser( lexer3 );
        p3.parse();
        AnalizadorLexico(lexer3);
        resultado = resultado+("\nEntrada Exitosa\n");
        }catch (Exception ex) {
             resultado = resultado+("\nEntrada Iconrrecta (Codigo Intermedio)"+" error: "+ex.getMessage()+"\n");
        
            //JOptionPane.showMessageDialog(null, ex);
        } 
        
            
        
        
        }
        
        
        } catch (Exception ex) {
             resultado = resultado+("\nEntrada Iconrrecta (Semantico)\n");
        
            //JOptionPane.showMessageDialog(null, ex);
        }
        
        }
        // resultado = resultado+("\nEntrada Exitosa\n");
        
        } catch (Exception ex) {
             resultado = resultado+("\nEntrada Iconrrecta (Tabla de Simbolos)\n");
        
            //JOptionPane.showMessageDialog(null, ex);
        }
resultadoText.setText(resultado);
    }

    public void AnalizadorLexico(Lexer lexer){
        int contIDs=0;
        while (true){
            try {
                //Token token =lexer.yylex();
                Symbol s = lexer.next_token();
                if (s.sym == sym.EOF) {
                        System.out.println("\n********************Simbolos********************\n");
                    
                    for (int i = 0; i < tokenslist.size(); i++) {
                        System.out.println(tokenslist.get(i).nombre + "=" + tokenslist.get(i).ID);
                    }
                    //resultado=resultado+"Fin del Archivo";
                    resultadoText.setText(resultado);
                    return;
                }
                identificador tokens = new identificador();
                
                switch (s.sym) {
               /*     case sym.DOSPUNTOS:
                        contIDs++;
                        tokens.token = "DOSPUNTOS";
                        tokens.nombre = ":";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",if> ";
                        break;
                    case sym.OUT:
                        contIDs++;
                        tokens.token = "OUT";
                        tokens.nombre = "out";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",else> ";
                        break;
                    case sym.PAR2:
                        contIDs++;
                        tokens.token = "PAR2";
                        tokens.nombre = ")";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",endif> ";
                        break;
                    case sym.WHEN:
                        contIDs++;
                        tokens.token = "WHEN";
                        tokens.nombre = "when";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",while> ";
                        break;
                    case sym.DISTINTO:
                        contIDs++;
                        tokens.token = "DISTINTO";
                        tokens.nombre = "/=";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",endwhile> ";
                        break;
                    case sym.PAR1:
                        contIDs++;
                        tokens.token = "PAR1";
                        tokens.nombre = "(";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<&>";
                        break;
                    case sym.FUNCTION:
                        contIDs++;
                        tokens.token = "FUNCTION";
                        tokens.nombre = "function";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<||>";
                        break;
                    case sym.POR:
                        contIDs++;
                        tokens.token = "POR";
                        tokens.nombre = "*";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<+>";
                        break;
                    case sym.PUT:
                        contIDs++;
                        tokens.token = "PUT";
                        tokens.nombre = "Put";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<->";
                        break;
                    case sym.ENTERO:
                        contIDs++;
                        tokens.token = "ENTERO";
                        tokens.nombre = "Integer";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<*>";
                        break;
                    case sym.ARRAY:
                        contIDs++;
                        tokens.token = "ARRAY";
                        tokens.nombre = "array";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "</>";
                        break;
                    case sym.IGUAL:
                        contIDs++;
                        tokens.token = "IGUAL";
                        tokens.nombre = "=";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<==>";
                        break;*/
                    case sym.ID:
                        {
                            contIDs++;
                            tokens.token = "ID";
                            tokens.nombre = lexer.yytext();
                            tokens.ID = contIDs;
                            tokenslist.add(tokens);
                            //resultado=resultado+ "<ID," + contIDs + "> ";
                            break;
                        }/*
                    case sym.NUMERO:
                        contIDs++;
                        tokens.token = "NUMERO";
                        tokens.nombre = lexer.yytext();
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ","+lexer.lexeme+"> ";
                        break;
                    case sym.FIN:
                        contIDs++;
                        tokens.token = "FIN";
                        tokens.nombre = ";";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ","+lexer.lexeme+"> ";
                        break;
                    case sym.FOR:
                        contIDs++;
                        tokens.token = "FOR";
                        tokens.nombre = "for";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",function > ";
                        break;
                    case sym.AND:
                        contIDs++;
                        tokens.token = "AND";
                        tokens.nombre = "and";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<;> ";
                        break;
                    case sym.MENORIGUAL:
                        contIDs++;
                        tokens.token = "MENORIGUAL";
                        tokens.nombre = "<=";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",lib > ";
                        break;
                    case sym.IS:
                        contIDs++;
                        tokens.token = "IS";
                        tokens.nombre = "is";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<(> ";
                        break;
                    case sym.OR:
                        contIDs++;
                        tokens.token = "OR";
                        tokens.nombre = "or";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",global > ";
                        break;
                    case sym.IN:
                        contIDs++;
                        tokens.token = "IN";
                        tokens.nombre = "in";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",ret > ";
                        break;
                    case sym.INOUT:
                        contIDs++;
                        tokens.token = "INOUT";
                        tokens.nombre = "in out";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",ret > ";
                        break;
                    case sym.GET:
                        contIDs++;
                        tokens.token = "GET";
                        tokens.nombre = "Get";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<:> ";
                        break;
                    case sym.BEGIN:
                        contIDs++;
                        tokens.token = "BEGIN";
                        tokens.nombre = "begin";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "< ]> ";
                        break;
                    case sym.EXIT:
                        contIDs++;
                        tokens.token = "EXIT";
                        tokens.nombre = "exit";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",word > ";
                        break;
                    case sym.LOOP:
                        contIDs++;
                        tokens.token = "LOOP";
                        tokens.nombre = "loop";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",print > ";
                        break;
                    case sym.IF:
                        contIDs++;
                        tokens.token = "IF";
                        tokens.nombre = "if";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",import> ";
                        break;
                    case sym.MAYORIGUAL:
                        contIDs++;
                        tokens.token = "MAYORIGUAL";
                        tokens.nombre = ">=";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "< , > ";
                        break;
                    case sym.OF:
                        contIDs++;
                        tokens.token = "OF";
                        tokens.nombre = "of";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",selectcase> ";
                        break;
                    case sym.RETURN:
                        contIDs++;
                        tokens.token = "RETURN";
                        tokens.nombre = "return";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",endfor> ";
                        break;
                    case sym.TRUE:
                        contIDs++;
                        tokens.token = "TRUE";
                        tokens.nombre = "true";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",print> ";
                        break;
                    case sym.COMILLA:
                        contIDs++;
                        tokens.token = "COMILLA";
                        tokens.nombre = "\"";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",endfunction> ";
                        break;
                    case sym.COMA:
                        contIDs++;
                        tokens.token = "COMA";
                        tokens.nombre = ",";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<!=> ";
                        break;
                    case sym.MENOS:
                        contIDs++;
                        tokens.token = "MENOS";
                        tokens.nombre = "-";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<=>";
                        break;
                    case sym.MENOR:
                        contIDs++;
                        tokens.token = "MENOR";
                        tokens.nombre = "<";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",case> ";
                        break;
                    case sym.MAYOR:
                        contIDs++;
                        tokens.token = "MAYOR";
                        tokens.nombre = ">";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "< [ > ";
                        break;
                    case sym.REAL:
                        contIDs++;
                        tokens.token = "REAL";
                        tokens.nombre = lexer.yytext();
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "<" + token+ ",for> ";
                        break;
                    case sym.ELSE:
                        contIDs++;
                        tokens.token = "ELSE";
                        tokens.nombre = "else";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "< " + token+ ",default> ";
                        break;
                    case sym.PUNTO:
                        contIDs++;
                        tokens.token = "PUNTO";
                        tokens.nombre = ".";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "< " + token+ ",break> ";
                        break;
                    case sym.WHILE:
                        contIDs++;
                        tokens.token = "WHILE";
                        tokens.nombre = "while";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "< ) > ";
                        break;
                    case sym.FLOAT:
                        contIDs++;
                        tokens.token = "FLOAT";
                        tokens.nombre = "Float";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "< " + token+ ",endselectcase> ";
                        break;
                    case sym.THEN:
                        contIDs++;
                        tokens.token = "THEN";
                        tokens.nombre = "then";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "< > > ";
                        break;
                    case sym.END:
                        contIDs++;
                        tokens.token = "END";
                        tokens.nombre = "end";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "< < > ";
                        break;
                    case sym.FALSE:
                        contIDs++;
                        tokens.token = "FALSE";
                        tokens.nombre = "false";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "< <= > ";
                        break;
                    case sym.ENTRE:
                        contIDs++;
                        tokens.token = "ENTRE";
                        tokens.nombre = "/";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "< >= > ";
                        break;
                         case sym.PROCEDURE:
                        contIDs++;
                        tokens.token = "PROCEDURE";
                        tokens.nombre = "procedure";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "< >= > ";
                        break;
                              case sym.MAS:
                        contIDs++;
                        tokens.token = "MAS";
                        tokens.nombre = "+";
                        tokens.ID = contIDs;
                        tokenslist.add(tokens);
                        //resultado=resultado+ "< >= > ";
                        break;*/
                    default:
                //resultado=resultado+ "<"+ s.value + "> ";
                }
            } catch (IOException ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
              
    }

    
   
    public void tablaResultado(){
        CUP$parser$actions s = null ;
        ArrayList <ArrayList>listatemp = new ArrayList();
         matriz = new Object [lnombre.size()][10];
         //JOptionPane.showMessageDialog(null,s.lnombre.size());
      /*   matriz[0][0] = 
            matriz[0][1] = 
            matriz[0][2] = 
            matriz[0][3] = 
            matriz[0][4]=
            matriz[0][5] = 
            matriz[0][6] = 
        */CUP$parser$actions sym = null ;
         String lpara = "";
         int contador = 0;
           for(int i =0; i<=ltipopar.size()-1;i++){
               System.out.println("entro----"+ltipopar.get(i).toString()); 
            }    System.out.println("************************************************"); 
           
         for(int i =0; i<=lnombre.size()-1;i++){
        
            matriz[i][0] = i;
            matriz[i][1] = lnombre.get(i).toString();
            matriz[i][2] = ltipo.get(i);
            matriz[i][3] = ldimension.get(i);
            
            matriz[i][4] = lparametro.get(i);
            matriz[i][6] = ltiporeturn.get(i); 
            matriz[i][7] = lambito.get(i); 
            matriz[i][8] = loffset.get(i); 
            matriz[i][9] = lprofundidad.get(i); 
            
         }
          for(int i =0; i<=ltipopar.size()-1;i++){
                System.out.println("i:  "+i);
                System.out.println(contador);
        if(contador<ltipopar.size()){        
         
            if(ltipopar.get(contador).toString().equals("bloque")){
             System.out.println("bloque----"+contador);
             contador++;}
        }    
        if(contador<ltipopar.size()){        
       
         
         if(ltipopar.get(contador).toString().equals("no tiene") ){
                          System.out.println("no tiene----"+contador); 
            matriz[i][5]=  "no tiene parametros"; contador++;}
                   
                
          if (ltipopar.get(contador).equals("no hay") ){ 
                System.out.println(" ----"+contador); 
            matriz[i][5]=  ""; contador++;} 
          
          
          if (ltipopar.get(contador).equals("") ){ 
                System.out.println(" ----"+contador); 
            matriz[i][5]=  ""; contador++;} 
 
          if(!ltipopar.get(contador).equals("bloque")&& !ltipopar.get(contador).equals("no hay") && !ltipopar.get(contador).equals("") && !ltipopar.get(contador).equals("no tiene") ) {
                    while (!ltipopar.get(contador).equals("bloque")&& !ltipopar.get(contador).equals("") ) {
                    lpara = lpara +" "+ltipopar.get(contador);
                                     System.out.println("while----"+contador); 
        
                    contador++;
                        // JOptionPane.showMessageDialog(null,"entro----"+contador);
                            } 
                    matriz[i][5]= lpara;
                    
                        lpara=""; 
                
                        } 
                }
          }
                           
            
         
   
        jTable1.setModel(new javax.swing.table.DefaultTableModel(matriz,
            new String [] {
               "Indice","Nombre", "tipo", "Dimension de Arreglo" , "Tipo de Parametro" ,"Cantidad de Parametros", "Tipo de Retorno" , "Ambito" ,"Desplazamiento","Profundidad"
        }
        ));
  
       // TabladeSimbolo.imprimirtabla();
    }

    public void resetearTabla(){
        Object[][] matriz = {};
        jTable1.setModel(new javax.swing.table.DefaultTableModel(matriz,
            new String [] {
                "Token", "ID", "Valor"
        }
        ));
    }

    private void crear_archivo_fuente() {
       
       fichero=new File("fuente.txt");
       
       try {
             if (fichero.createNewFile())
                 System.out.println("El fichero se ha creado correctamente");
           } catch (IOException ioe) {
               ioe.printStackTrace();
           }

     
       try {
            
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter escritor = new PrintWriter(bw);
            String texto = fuente.getText();
            escritor.println(texto);
            escritor.close();
        } catch(IOException ex) {
            System.err.println("Error al escribir archivo");
        }
       
   
    }


    private void mostrar_fuente(File archivo) throws IOException {
        
         try {
                
                String eol = System.getProperty("line.separator");
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String linea = br.readLine();
               
                while (linea != null) {
                    fuente.append(linea + eol);
                    linea = br.readLine();
                }
                br.close();
            } catch (IOException ex) {
                System.err.println("Se produjo un error al leer el archivo");
            }
        
    }

    private void guardar_como_fuente(String archivo) throws IOException {
        
              
         try {
            
            FileWriter fw = new FileWriter(archivo+".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter escritor = new PrintWriter(bw);
            String texto = fuente.getText();
            escritor.println(texto);
            escritor.close();
        } catch(IOException ex) {
            System.err.println("Error al escribir archivo");
        }
        
    }

    private void guardar_como() {
        JFileChooser  chooser = new JFileChooser();
        int returnVal = chooser.showSaveDialog(fuente);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           
                String archivo= chooser.getSelectedFile().getPath();
            try {
                guardar_como_fuente(archivo);
            } catch (IOException ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            
     
        }
    }

    private void guardar() {
         
        try {
            
            FileWriter fw = new FileWriter(nombre_archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter escritor = new PrintWriter(bw);
            String texto = fuente.getText();
            escritor.println(texto);
            escritor.close();
        } catch(IOException ex) {
            System.err.println("Error al escribir archivo");
        }
    }

    private void abrir() {
        JFileChooser  chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = chooser.showOpenDialog(fuente);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                fuente.setText("");
                       File archivo= chooser.getSelectedFile();
                nombre_archivo=chooser.getSelectedFile().getPath();
                mostrar_fuente(archivo);
            } catch (IOException ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
     
        }
        
    }
    public void buscarTexto() throws BadLocationException {
     
        hilit.removeAllHighlights();
        buscar = txtbuscar.getText().toUpperCase();
        
        if (buscar.length() > 0) {
         String contenido = fuente.getText().toUpperCase();
            int index = contenido.indexOf(buscar, 0);
            if (index >= 0) {       
                try {
                     end = index + buscar.length();
                    hilit.addHighlight(index, end, painter);
                    fuente.setCaretPosition(end);   
                    //txtbuscar.setBackground(Color.DARK_GRAY);
                   // estado.setText("'" + s + "' fue encontrado. Presione ESC para finalizar la busqueda.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                txtbuscar.setForeground(Color.WHITE);
              //  estado.setText("'" + s + "' no se ha encontrado. Presione ESC para finalizar la busqueda.");
            }
        }else{
         //estado.setText("Digite la palabra a buscar.");
        }
       // if (txtbuscar.getText().equals(""))
         //   txtbuscar.setEnabled(false);
            }
    public void siguienteTexto() throws BadLocationException {
        hilit.removeAllHighlights();
        buscar = txtbuscar.getText().toUpperCase();
        String contenido=fuente.getText();
        int c=1;
         int index = contenido.indexOf(buscar, 0);
          // end = index + buscar.length();
          int length = buscar.length();
        if (buscar.length() > 0) {
        while(contenido.isEmpty()){
            
        contenido = contenido.substring(index = contenido.indexOf(buscar, index+1)).toUpperCase();
        //JOptionPane.showMessageDialog(null,contenido);
           
            if (index >= 0) {       
                try {                    
                      hilit = new DefaultHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(new java.awt.Color(204, 153, 0));
        fuente.setHighlighter(hilit);
                                 hilit.addHighlight(index, index+length, painter);
                    index = contenido.indexOf(buscar, index+1);
                   
                    //fuente.setCaretPosition(end);   
                    } catch (Exception e) {
                    e.printStackTrace();}
                 } else {
                txtbuscar.setForeground(Color.WHITE);}
            }
       
        }
    }

    public static ArrayList verificarVariable(ArrayList lista1, ArrayList lista2){
        ArrayList noRepetidos = new ArrayList();
        int contador=0;
        int indice=0;
        for (int i=0;i<=lista1.size()-1;i++){
             for (int j=0;j<=lista2.size()-1;j++){
                if (lista1.get(i).equals(lista2.get(j))){
                    contador++;  
                }
                
              }
              if (contador==0){
                    noRepetidos.add(lista1.get(i));
                   }
             contador=0;
        }
        return noRepetidos;
    }
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/ADA95_2.jpg"));


        return retValue;
    }

}


