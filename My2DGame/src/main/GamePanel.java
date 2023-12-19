package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale; // real tile size
	
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	KeyHandler keyHandler = new KeyHandler();
	Thread gameThread;
	
	int playerPosX = 100;
	int playerPosY = 100;
	int playerSpeed = 4;
	
	int FramePerSecond = 60;
	
	GamePanel()
	{
		System.out.println("Initialize screen: " + screenWidth + " x " + screenHeight);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}
	
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double  drawInterval = 1000000000/FramePerSecond;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null)
		{
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 1)
			{
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if (timer >= 1000000000) {
				System.out.println(drawCount + " Frame/sec");
				drawCount = 0;
				timer = 0;
			}
				
		}
			
		
	}
	
	public void update()
	{
		if(keyHandler.upPressed) {
			playerPosY -= playerSpeed;
		}
		else if (keyHandler.downPressed) {
			playerPosY += playerSpeed;
		}
		else if (keyHandler.leftPressed) {
			playerPosX -= playerSpeed;
		}
		else if (keyHandler.rightPressed) {
			playerPosX += playerSpeed;
		}
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		g2.fillRect(playerPosX, playerPosY, tileSize, tileSize);
		g2.dispose();
	}
}

