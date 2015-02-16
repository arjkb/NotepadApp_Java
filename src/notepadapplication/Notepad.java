/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepadapplication;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Arjun Krishna Babu
 */


/*
    Notepad application with the following functionalities:
    * Bold/Italic/Underline
    * Undo/Redo
*/

public class Notepad extends javax.swing.JFrame {

    /**
     * Creates new form Notepad
     */
    
    UndoAction undoAction;
    RedoAction redoAction;
    UndoManager undo;
    Document doc;
    
    public Notepad() {
        initComponents();
        
        undo = new UndoManager();
        undoAction = new UndoAction();
        redoAction = new RedoAction();        
        
        doc = jTextPane1.getDocument();
        
        doc.addUndoableEditListener( new MyUndoableEditListener() );
        
        //Required for working with keyboard-shortcut (apparently)
        jTextPane1.getActionMap().put("Undo", undoAction);
        jTextPane1.getActionMap().put("Redo", redoAction);

        
        jMenu2.add( new StyledEditorKit.CutAction() );
        jMenu2.add( new StyledEditorKit.CopyAction() );
        jMenu2.add( new StyledEditorKit.PasteAction() );
        
        jMenu3.add( new StyledEditorKit.BoldAction() );
        jMenu3.add( new StyledEditorKit.ItalicAction() );
        jMenu3.add( new StyledEditorKit.UnderlineAction() );
        
        jMenu4.add( undoAction );
        jMenu4.add( redoAction );
        
        jTextPane1.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
        jTextPane1.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
    }
    
    class MyUndoableEditListener implements UndoableEditListener   {

        @Override
        public void undoableEditHappened(UndoableEditEvent E) {
            //Stores the undoable edit into undo
            undo.addEdit( E.getEdit() );
//            undoAction.updateUndoState();
            
            System.out.println("\n Undoable!");
        }
    }
    
    class UndoAction extends AbstractAction  {

        UndoAction()    {
            super("Undo");
        }
        
        public void actionPerformed(ActionEvent ae) {
            try {
                if( undo.canUndo() )    {
                    undo.undo();
                }
            } catch( CannotUndoException CUE )    {
                System.out.println("\n ERROR: Cannot Undo! " + CUE);
                CUE.printStackTrace();
            }
        }
    }
    
    
    class RedoAction extends AbstractAction  {
        
        RedoAction()    {
            super("Redo");
        }
        public void actionPerformed(ActionEvent ae) {
            try {
                if( undo.canRedo() )    {
                    undo.redo();
                }
            } catch( CannotRedoException CRE )    {
                System.out.println("\n ERROR: Cannot Redo! " + CRE);
                CRE.printStackTrace();
            }
        }
    }
    
    public Action getActionByName(String name)    {
       
        HashMap action = new HashMap<Object, Action>();
        Action a;
        
        Action actionsArray[] = jTextPane1.getActions();
                
        for(int i = 0; i < actionsArray.length; i++)    {
            a = actionsArray[i];
            action.put(a.getValue(Action.NAME), a);
        } 
        
        System.out.println(action.get(name));
        return (Action) action.get(name);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT msodify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jTextPane1);

        jScrollPane2.setViewportView(jTextPane2);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Format");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Revision");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        System.out.println("\n This should be good!!");

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Notepad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables
}
