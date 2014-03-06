

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class playing1 extends javax.swing.JPanel implements Runnable {

	/**
	 * Creates new form playing1
	 */
        Image img;
        ImageIcon ic;
	public playing1() {
		initComponents();
		
		ic = new ImageIcon(getClass().getResource("/images/play1.jpg"));
                img = ic.getImage();
		//setBackground(Color.cyan);
		requestFocusInWindow();
		
		snake = new Rectangle[3];
		
		
		int x1 = 0;
		for (int i = 0; i < 3; i++) {
			snake[i] = new Rectangle(x1, 25, 10, 10);
			
			x1 += 10;
			
		}
		bonuslabel.setVisible(false);
		
		fd = new Food();
		rg = new Random();
		 bob = new BonusClass();
                bonusrect = new Rectangle(0,0,20,20);
		
		askfood = true;
		ti = 100;
		
	}

	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        score = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bonuslabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(724, 488));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        score.setFont(new java.awt.Font("MS UI Gothic", 0, 24)); // NOI18N
        score.setForeground(new java.awt.Color(204, 204, 0));
        score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        score.setText("0");

        jLabel1.setFont(new java.awt.Font("Script MT Bold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Level");

        jLabel3.setFont(new java.awt.Font("Segoe Script", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("1");

        bonuslabel.setFont(new java.awt.Font("Poor Richard", 0, 36)); // NOI18N
        bonuslabel.setForeground(new java.awt.Color(102, 102, 102));
        bonuslabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bonuslabel.setText("8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(578, 578, 578)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(score, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bonuslabel)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bonuslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 308, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
	
	public void startGame()
	{
		isPressed = false;
		counter = 0;
		left = up = down = false;
		right = true;
		pause = false;
		askfood = true;
		drawBonus = false;
		count = 0;
		bonuslabel.setVisible(false);
		scoree = 0;
		score.setText("0");
		snake = new Rectangle[3];
		((Mainn)this.getTopLevelAncestor()).playPlayMusic();
                
		int x1 = 0;
		for (int i = 0; i < 3; i++) {
			snake[i] = new Rectangle(x1, 25, 10, 10);
			
			x1 += 10;
			
		}
		ImageIcon ic = new ImageIcon(getClass().getResource("images/bg.png"));
		imgbg = ic.getImage();
		
		
		th = new Thread(this);
		th.start();
	}
	
        private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
               if(isPressed && evt.getKeyCode() != KeyEvent.VK_P) {
		       return;
	       }
	       isPressed = true;
	       switch(evt.getKeyCode())
			{
			    case KeyEvent.VK_UP:
				this.func(0);	
				break;
			    case KeyEvent.VK_DOWN:
				this.func(1);	
				    break;
			    case KeyEvent.VK_RIGHT:
				this.func(2);	
				break;
			    case KeyEvent.VK_LEFT:
				this.func(3);	
				break;
			   
			    case KeyEvent.VK_P:
				    
				  if (pause == true) {
					  pause = false;
				  }
				  else
					  pause = true;
				    break;
			 }
        }//GEN-LAST:event_formKeyPressed
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g1 = g;
		g.drawImage(img, 0, 0, this);
		
		//g.drawImage(imgbg, 0, 0, this);
		if (askfood) {
			fd.drawNewFood(rg, g);
			askfood = false;
		}
		else
			fd.drawOldFood(g);
		
		 if (drawBonus)
                    drawBonus = bob.draw();
		 
		
                try {
                    g.setColor(new Color(132, 132, 123));
                    for (int i = 0; i < snake.length - 1; i++) {
                        g.fillOval(snake[i].x, snake[i].y, snake[i].width, snake[i].height);
                        
                    }
            
                    g.setColor(Color.red);
                    int i = snake.length - 1;
                    g.fillOval(snake[i].x, snake[i].y, snake[i].width, snake[i].height);
                } catch (Exception e) {

                }
		isPressed = false;
	}
	
	public void func(int x)
	{
		
		int tmp;
		switch(x) {
			case 0:
				if (down == false) {
					up = true;
					left = right = false;
				}
				break;
			case 1:
				if (up == false) {
					down = true;
					left = right = false;
				}
				break;
			case 2:
				if (left == false) {
					right = true;
					down = up = false;
				}
				break;
			case 3:
				if (right == false) {
					left = true;
					down = up = false;
				}
				break;
		}	
	}
	
	private void changeposition()
	{
		for (int i = 1; i < snake.length; i++) {
			snake[i-1].x = snake[i].x;
			snake[i-1].y = snake[i].y;
			snake[i-1].width = snake[i].width;
			snake[i-1].height = snake[i].height;
		}
	}
	
	private void growsnake()
	{
		LinkedList<Rectangle> list = new LinkedList<Rectangle>(Arrays.asList(snake));
		list.add(new Rectangle(snake[snake.length-1].x, snake[snake.length-1].y, 10, 10 ));
		snake = list.toArray(new Rectangle[list.size()]);
			
	}
	
	boolean collision()
	{
		int l = snake.length;
		
		for (int i = 0; i < l-2; i++) {
			if (snake[i].intersects(snake[l-1])) {
				return true;
			}
				
		}
		
		if (snake[l-1].x < 0 || snake[l-1].x > 660) {
			
			return true;
		}
			
		if (snake[l-1].y < 0 || snake[l-1].y > 460) {
			
			return true;
		}
			
		
		return false;
	}
	
	
	private void pauseGame()
	{
		ImageIcon ic = new ImageIcon(getClass().getResource("images/play1pause.png"));
		Image img = ic.getImage();
		
		Graphics g2 = this.getGraphics();
		
		while (pause) {
				try {
					Thread.sleep(100);
					g2.drawImage(img, 0, 0, this);
				} catch (InterruptedException ex) {
					Logger.getLogger(playing1.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
	}
	
	public void run() {
		
		 Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		while (true) {
			//System.out.println("himalay");
			this.requestFocusInWindow();	
			
			if (pause)
				pauseGame();
			
			for (int i = 0; i < snake.length; i++) {
				
				if (snake[i].intersects(fd.foodrect)) {
                                        ((Mainn)this.getTopLevelAncestor()).playFoodMusic();
					askfood = true;
					growsnake();
					
					scoree += 10;
					score.setText(Integer.toString(scoree));
					
					count++;
                                        if(count == 5){
                                            ((Mainn)this.getTopLevelAncestor()).playTimerMusic();
                                            bob.setFood();
                                          drawBonus = true;
                                            count = 0;
                                        }
					 counter++;
                                        if (counter == 11
						) {
						
					((Mainn)this.getTopLevelAncestor()).stopTimerMusic();
                                            changeStage();
                                         }
					
					break;

				}
			}
			
			for (int i = 0; drawBonus && i < snake.length; i++) {
                            
                            if (snake[i].intersects(bonusrect)) {
                                ((Mainn)this.getTopLevelAncestor()).stopTimerMusic();
                                ((Mainn)this.getTopLevelAncestor()).playBonusMusic();
                                drawBonus = false;
				bonuslabel.setVisible(false);
                                growsnake();
                                scoree += 50;
                                score.setText(Integer.toString(scoree));
                                break;
                            }
                        }
			
			try {
				if (collision()) {
                                        ((Mainn)this.getTopLevelAncestor()).stopTimerMusic();
                                        ((Mainn)this.getTopLevelAncestor()).stopPlayMusic();
                                        ((Mainn)this.getTopLevelAncestor()).playDiedMusic();
                                        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
                                        s.schedule(new Runnable() {
                                            public void run() {
                                                changeMusic();
                                            }
                                        },2, TimeUnit.SECONDS);
                                        
					((Mainn) this.getTopLevelAncestor()).SetScore(scoree);
					CardLayout card = (CardLayout) this.getParent().getLayout();
					card.show(this.getParent(), "gameOver");
				
					break;
				}
				changeposition();
				
				if (up)
					snake[snake.length-1].y -= 10;
				else if (down)
					snake[snake.length-1].y += 10;
				else if (right)
					snake[snake.length-1].x += 10;
				else 
					snake[snake.length-1].x -= 10;
				
				repaint();
				Thread.sleep(ti);
			} catch (InterruptedException ex) {
				System.out.println(ex);
			}
			
		}
	}
	
        public void changeMusic() {
            ((Mainn)this.getTopLevelAncestor()).playGameMusic();
        }
	 private void changeStage() {
            ((Mainn)this.getTopLevelAncestor()).StartStage2(scoree, 1);
            th.stop();
        }
	 
	 public  class BonusClass   {

            public BonusClass() {
		    it = 8;
	    }
         
            int time = 0;
            int x1, y1;
            int it, x;
            
            public void setFood()
            {
                
		bonuslabel.setText("8");
		bonuslabel.setVisible(true);
	
                time = 0;
                  x1 = rg.nextInt(66)*10;
                  y1 = rg.nextInt(46)*10 + 5;
                   bonusrect.x = x1;
                   bonusrect.y = y1;
            }        
                 
            
          public boolean draw() {
                
                time += ti;
                 g1.fillRect(x1, y1, 20, 20);
                 
		 if (time % 1000 == 0) {
			x = it - time/1000;
			 bonuslabel.setText("" + x);
		 }
                 if( time < 8000)
			 return true;
		 else {
                        stopBonus();
			 bonuslabel.setVisible(false);
			 return false;
		 }
			 
          }
	  
	      
            
    }
         
         public void stopBonus() {
             ((Mainn)this.getTopLevelAncestor()).stopTimerMusic();
         }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bonuslabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel score;
    // End of variables declaration//GEN-END:variables
	private Rectangle snake[];	
	boolean askfood ;
	Random rg;
	Food fd;
	boolean up, down, right, left;
	int scoree = 0;
	public Thread th;
	long ti;
	boolean pause;
	  BonusClass bob;
        Rectangle bonusrect;
	 boolean drawBonus;
	int count = 0;
	 Graphics g1;
	  private int counter;
	  Image imgbg;
	  boolean isPressed;
}
