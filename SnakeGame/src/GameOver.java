
import java.awt.CardLayout;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class GameOver extends javax.swing.JPanel {

	/**
	 * Creates new form GameOver
	 */
	public GameOver() {
		initComponents();
		enterName.setVisible(false);
		name.setVisible(false);
		submit.setVisible(false);
		result.setVisible(false);
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name = new javax.swing.JTextField();
        score = new javax.swing.JLabel();
        enterName = new javax.swing.JLabel();
        menu = new javax.swing.JLabel();
        highScore = new javax.swing.JLabel();
        submit = new javax.swing.JLabel();
        result = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);
        add(name);
        name.setBounds(333, 362, 130, 30);

        score.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        score.setForeground(new java.awt.Color(0, 0, 102));
        score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        score.setText("0");
        add(score);
        score.setBounds(230, 231, 190, 80);

        enterName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        enterName.setForeground(new java.awt.Color(0, 153, 255));
        enterName.setText("Enter Name");
        add(enterName);
        enterName.setBounds(220, 358, 100, 40);

        menu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        menu.setForeground(new java.awt.Color(255, 0, 0));
        menu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu.setText("MENU");
        menu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuMouseClicked(evt);
            }
        });
        add(menu);
        menu.setBounds(20, 422, 80, 30);

        highScore.setFont(new java.awt.Font("Traditional Arabic", 0, 18)); // NOI18N
        highScore.setForeground(new java.awt.Color(204, 0, 102));
        highScore.setText("High Score");
        highScore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        highScore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                highScoreMouseClicked(evt);
            }
        });
        add(highScore);
        highScore.setBounds(27, 362, 90, 50);

        submit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submit.setForeground(new java.awt.Color(0, 204, 51));
        submit.setText("Submit Score");
        submit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitMouseClicked(evt);
            }
        });
        add(submit);
        submit.setBounds(343, 404, 120, 30);

        result.setFont(new java.awt.Font("Monotype Corsiva", 0, 24)); // NOI18N
        result.setForeground(new java.awt.Color(0, 0, 255));
        result.setText("Congratulations you have got a new High Score");
        add(result);
        result.setBounds(118, 12, 440, 60);

        jPanel1.setPreferredSize(new java.awt.Dimension(670, 470));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameo.jpg"))); // NOI18N
        jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);

        add(jPanel1);
        jPanel1.setBounds(0, 0, 670, 470);
    }// </editor-fold>//GEN-END:initComponents

        private void menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseClicked
                CardLayout card = (CardLayout) this.getParent().getLayout();
		card.show(this.getParent(), "start");
                ((Mainn)this.getTopLevelAncestor()).stopGameMusic();
                ((Mainn)this.getTopLevelAncestor()).playMenuMusic();
                
        }//GEN-LAST:event_menuMouseClicked

        private void highScoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_highScoreMouseClicked
                 CardLayout card = (CardLayout) this.getParent().getLayout();		
		card.show(this.getParent(), "high");
		((Mainn) this.getTopLevelAncestor()).setHighScore();
        }//GEN-LAST:event_highScoreMouseClicked

        private void submitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitMouseClicked
		
		ob.submitName(name.getText());
		result.setVisible(true);
		name.setVisible(false);
		enterName.setVisible(false);
		submit.setVisible(false);
		
		System.out.println(result.getText());
		
        }//GEN-LAST:event_submitMouseClicked
	
	public  void setScore(int scoree)
	{
		ob = new Client();
		
		score.setText("" + scoree);
		name.setText("");
		
		enterName.setVisible(false);
		name.setVisible(false);
		submit.setVisible(false);
		result.setVisible(false);
		
		

		if (ob.setScore(scoree)) {
			

			enterName.setVisible(true);
			name.setVisible(true);
			submit.setVisible(true);

		}
		
	}
	
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel enterName;
    private javax.swing.JLabel highScore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel menu;
    private javax.swing.JTextField name;
    private javax.swing.JLabel result;
    private javax.swing.JLabel score;
    private javax.swing.JLabel submit;
    // End of variables declaration//GEN-END:variables
	Client ob;
	
}