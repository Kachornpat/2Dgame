package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;


public class TileManager {
	GamePanel gamePanel;
	Tile[] tiles;
	int mapTileNumber[][];

	public TileManager(GamePanel gamePanel)
	{
		this.gamePanel = gamePanel;
		tiles = new Tile[10];
		mapTileNumber = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
		getTileImage();
		loadMap();
	}
	
	public void getTileImage()
	{
		try {
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick.png"));
			
			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void loadMap()
	{
		try
		{
			InputStream inputStream = getClass().getResourceAsStream("/maps/map.txt");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
			int columns = 0;
			int rows = 0;
			while (columns < gamePanel.maxScreenCol && rows < gamePanel.maxScreenRow)
			{
				String line = bufferedReader.readLine();
				String numbers_str[] = 	line.split(" ");
				
				while (columns < gamePanel.maxScreenCol) {
					int number = Integer.parseInt(numbers_str[columns]);
					mapTileNumber[columns][rows] = number;
					columns++;
				} // while
				if (columns == gamePanel.maxScreenCol) {
					columns = 0;
					rows++;
				}// if
				
			} // while
			bufferedReader.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D screen)
	{
		int columns = 0, rows = 0;
		int x = 0, y = 0;
		
		while (columns < gamePanel.maxScreenCol && rows < gamePanel.maxScreenRow)
		{
			int tileNumber = mapTileNumber[columns][rows];
			
			screen.drawImage(tiles[tileNumber].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
			columns++;
			x += gamePanel.tileSize;
			
			if (columns == gamePanel.maxScreenCol)
			{
				columns = 0;
				x = 0;
				rows++;
				y += gamePanel.tileSize;
			}
		
		}
		
	}

}
