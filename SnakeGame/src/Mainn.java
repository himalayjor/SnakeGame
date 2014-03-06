
import java.applet.AudioClip;
import java.awt.CardLayout;
import java.util.concurrent.*;




/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */



public class Mainn extends javax.swing.JApplet {

	
         public Mainn() {
		 
             
             /**/
             
        }
	@Override
	public void init() {
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
			java.util.logging.Logger.getLogger(Mainn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Mainn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Mainn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Mainn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the applet */
		this .setSize(670, 470);
		
		
		
		try {
			java.awt.EventQueue.invokeAndWait(new Runnable() {
				public void run() {
                                        initComponents();
                                        mClip = getAudioClip(getDocumentBase(), "menu.wav");
                                        pClip = getAudioClip(getDocumentBase(), "play.wav");
                                        fClip = getAudioClip(getDocumentBase(), "food.wav");
                                        bClip = getAudioClip(getDocumentBase(), "food.wav"); // Replace with small lenth song. 1 sec!
                                        dClip = getAudioClip(getDocumentBase(), "died.wav");
                                        tClip = getAudioClip(getDocumentBase(), "timer.wav");
                                        gClip = getAudioClip(getDocumentBase(), "game.wav");
                                        
					mClip.loop();
                                        isPlaying = true;
				}
			});
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	
	public void StartPlay() {
            mClip.stop();
            playing11.startGame();
            
	}
	
	public void SetScore(int sc)
	{
		gameOver1.setScore(sc);
	
	}
	
	public void setHighScore()
	{
		highScore1.startRunning();
	}
	
	public void StartStage2(final int scr, int Stage) {
                CardLayout card = (CardLayout) display.getLayout();
                card.show(display, "StageCompleted");
                stageCompleted1.setStage(Stage);
                ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
                s.schedule(new Runnable() {
                    public void run() {
                        changeCard(scr);
                    }
                },2, TimeUnit.SECONDS);
	}
	
	private void changeCard(int Score) {
            CardLayout card = (CardLayout) display.getLayout();
            card.show(display, "play2");
            playing21.startGame(Score);
       }
        
        
         public void toggleMusic() {
            if (isPlaying)  {
                mClip.stop();
                isPlaying = false;
            }
            else {
                mClip.loop();
                isPlaying = true;
            }
        }
	
        public void playFoodMusic() {
            if (isPlaying) {
                fClip.play();
            }
        }
        public void playMenuMusic() {
            if (isPlaying) {
                mClip.loop();
            }
        }
        public void playBonusMusic() {
            if (isPlaying) {
                bClip.play();
            }
        }
        public void stopBonusMusic() {
            bClip.stop();
            
        }
        public void stopPlayMusic() {
            pClip.stop();
            
        }
	
        public void playDiedMusic() {
            if (isPlaying) {
                dClip.play();
            }
        }
        public void playPlayMusic() {
            if (isPlaying) {
                pClip.loop();
            }
        }
        public void playTimerMusic() {
            if (isPlaying) {
                tClip.loop();
            }
        }
        
        public void playGameMusic() {
            if (isPlaying) {
                gClip.play();
            }
        }
        public void stopGameMusic() {
            gClip.stop();
            
        }
        public void stopTimerMusic() {
            tClip.stop();
        }
        
        
        
        
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        display = new javax.swing.JPanel();
        start1 = new Start();
        help1 = new Help();
        playing11 = new playing1();
        gameOver1 = new GameOver();
        highScore1 = new highScore();
        stageCompleted1 = new StageCompleted();
        playing21 = new Playing2();

        setPreferredSize(new java.awt.Dimension(670, 470));

        display.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        display.setPreferredSize(new java.awt.Dimension(670, 470));
        display.setLayout(new java.awt.CardLayout());
        display.add(start1, "start");
        display.add(help1, "help");
        display.add(playing11, "play");
        display.add(gameOver1, "gameOver");
        display.add(highScore1, "high");
        display.add(stageCompleted1, "StageCompleted");
        display.add(playing21, "play2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 672, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(display, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(display, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel display;
    private GameOver gameOver1;
    private Help help1;
    private highScore highScore1;
    private playing1 playing11;
    private Playing2 playing21;
    private StageCompleted stageCompleted1;
    private Start start1;
    // End of variables declaration//GEN-END:variables
	boolean isPlaying;
	AudioClip mClip, pClip, fClip, bClip, tClip, gClip ,dClip;
        
}
