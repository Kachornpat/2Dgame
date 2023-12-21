package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gamePanel;
	KeyHandler keyHandler;
	
	public Player(GamePanel gamePanel, KeyHandler keyHandler)
	{
		this.gamePanel = gamePanel;
		this.keyHandler = keyHandler;
		setDefaultValue();
		getPlayerImage();
	}
	
	public void setDefaultValue()
	{
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage()
	{
		try
		{
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/back.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/back2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/front.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/front2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed)
		{
			if(keyHandler.upPressed) {
				direction = "up";
				y -= speed;
			}
			else if (keyHandler.downPressed) {
				direction = "down";
				y += speed;
			}
			else if (keyHandler.leftPressed) {
				direction = "left";
				x -= speed;
			}
			else if (keyHandler.rightPressed) {
				direction = "right";
				x += speed;
			}
			
			spriteCounter++;
			if (spriteCounter > 10) {
				if (spriteNumber == 1) {
					spriteNumber = 2;
				}
				else
				{
					spriteNumber = 1;
				}
				spriteCounter = 0;
			}
		} // if key pressed
	} // update()
	public void draw(Graphics2D g2)
	{
		BufferedImage image = null;
		
		switch (direction)
		{
		case "up":
			if (spriteNumber == 1)
			{
				image = up1;
			}
			else
			{
				image = up2;
			}
			break;
		case "down":
			if (spriteNumber == 1)
			{
				image = down1;
			}
			else
			{
				image = down2;
			}
			break;
		case "left":
			if (spriteNumber == 1)
			{
				image = left1;
			}
			else
			{
				image = left2;
			}
			break;
		case "right":
			if (spriteNumber == 1)
			{
				image = right1;
			}
			else
			{
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
	}
}
