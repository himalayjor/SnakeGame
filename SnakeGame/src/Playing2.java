
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
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
 * @author vaibhavatul47
 */
public class Playing2 extends javax.swing.JPanel implements Runnable {

    /**
     * Creates new form Playing2
     */
    Image img;
    ImageIcon ic;
    public Playing2() {
        initComponents();
        requestFocusInWindow();
        ic = new ImageIcon(getClass().getResource("/images/play2.jpg"));
        img = ic.getImage();
        
	bonuslabel.setVisible(false);
	
	
        maze = new Rectangle[9];
        maze[0] = new Rectangle(320, 40, 20, 380);
        maze[1] = new Rectangle(70, 80, 190, 20);
        maze[2] = new Rectangle(240, 100, 20, 230);
        maze[3] = new Rectangle(70, 330, 190, 20);
        maze[4] = new Rectangle(110, 180, 40, 40);
        maze[5] = new Rectangle(400, 80, 190, 20);
        maze[6] = new Rectangle(400, 100, 20, 230);
        maze[7] = new Rectangle(400, 330, 190, 20);
        maze[8] = new Rectangle(480, 180, 40, 40);
        
        //remove(jLabel2);
        
        snake = new Rectangle[3];
        int x1 = 0;
        for (int i = 0; i < 3; i++) {
                snake[i] = new Rectangle(x1, 25, 10, 10);

                x1 += 10;

        }
                
        fd = new Food();
        rg = new Random();
	count = 0;
	drawBonus = false;
	bonusrect = new Rectangle(0,0,20,20);
        askfood = true;
        ti = 80;
    }

    public void startGame(int Prevscore) {
	    isPressed = false;
            left = up = down = false;
            right = true;
            pause = false;
            scoree = Prevscore;
	    drawBonus = false;
	    bonuslabel.setVisible(false);
	    count = 0;
            score.setText(String.valueOf(Prevscore));
            level.setText("2");
            snake = new Rectangle[3];
	    
	     bob = new BonusClass();
	     
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
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
	
	g1 = g;
        g.drawImage(img, 0, 0, this);
	
	
        g.setColor(Color.red);
        if (askfood) {
                fd.drawNewFood(rg, g, maze);
                askfood = false;
        }
        else
                fd.drawOldFood(g);
	
	 if (drawBonus)
                    drawBonus = bob.draw();
        
        g.setColor(Color.yellow);
        for (int i = 0; i < maze.length; i++) {
                g.fillRect(maze[i].x, maze[i].y, maze[i].width, maze[i].height);
        }
        
        try {
            g.setColor(Color.WHITE);
            for (int i = 0; i < snake.length - 1; i++) {
                g.fillOval(snake[i].x, snake[i].y, snake[i].width, snake[i].height);
                
            }
            
            g.setColor(Color.ORANGE);
            int i = snake.length - 1;
            g.fillOval(snake[i].x, snake[i].y, snake[i].width, snake[i].height);
        } catch (Exception e) {
            
        }
	isPressed = false;
    }
    
private void pauseGame()
{
	ImageIcon ic = new ImageIcon(getClass().getResource("images/Pauseplay22.png"));
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
    
    public void func(int x) {
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
    
    private void changeposition() {
        for (int i = 1; i < snake.length; i++) {
                snake[i-1].x = snake[i].x;
                snake[i-1].y = snake[i].y;
                snake[i-1].width = snake[i].width;
                snake[i-1].height = snake[i].height;
        }
    }
	
    private void growsnake() {
		LinkedList<Rectangle> list = new LinkedList<Rectangle>(Arrays.asList(snake));
		list.add(new Rectangle(snake[snake.length-1].x, snake[snake.length-1].y, 10, 10 ));
		
		snake = list.toArray(new Rectangle[list.size()]);
	}
	
    boolean collision() {
        int l = snake.length;
        
        for (int i = 0; i < maze.length; i++) {
            if (snake[l -1].intersects(maze[i])) {
                return true;
            }
        }
        
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
	
		
	public void run() {
        // Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
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
                                scoree += 20;
                                score.setText(Integer.toString(scoree));
				
				count++;
				if(count == 5){
                                    ((Mainn)this.getTopLevelAncestor()).playTimerMusic();

				    bob.setFood();
				  drawBonus = true;
				    count = 0;
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
                                scoree = 0;
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        score = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        level = new javax.swing.JLabel();
        bonuslabel = new javax.swing.JLabel();

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        score.setFont(new java.awt.Font("MV Boli", 0, 24)); // NOI18N
        score.setForeground(new java.awt.Color(51, 51, 255));
        score.setText("0");

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jLabel1.setText("Level:");

        level.setFont(new java.awt.Font("Segoe Script", 1, 18)); // NOI18N
        level.setForeground(new java.awt.Color(204, 0, 0));
        level.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        level.setText("3");

        bonuslabel.setFont(new java.awt.Font("Poor Richard", 0, 36)); // NOI18N
        bonuslabel.setForeground(new java.awt.Color(102, 102, 255));
        bonuslabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bonuslabel.setText("8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(600, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(level, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(score, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bonuslabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(level, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(bonuslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 333, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (isPressed && evt.getKeyCode() != KeyEvent.VK_P) {
		return;
	}
	isPressed = true;
	switch(evt.getKeyCode()) {
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

                  if (pause == true)
                          pause = false;
                  else
                          pause = true;
                    break;
        }
    }//GEN-LAST:event_formKeyPressed

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
                 
		 boolean flag = true;
		while (flag) {
		    x1 = rg.nextInt(66) * 10;
		    y1 = rg.nextInt(46) * 10 + 5;
		    bonusrect.x = x1;
		    bonusrect.y = y1;
		    flag = false;
		    int i = 0;
		    for (; i < maze.length; i++) {
			if (maze[i].intersects(bonusrect)) {
			    flag = true;
			    break;
			}
		    }
		}
	    
            }        
                 
            
          public boolean draw() {
                time += ti;
                 g1.fillRect(x1, y1, 20, 20);
                 
		 if (time % 960 == 0) {
			x = it - time/960;
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
    private javax.swing.JLabel level;
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
    private Rectangle maze[];
     Image imgbg;
     Graphics g1;
     Rectangle bonusrect;
     int count;
      boolean drawBonus;
      BonusClass bob;
      boolean isPressed;
}
