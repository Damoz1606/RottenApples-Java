package ImagesResourses;

import java.awt.*;
import java.awt.image.*;

import java.io.*;
import javax.imageio.ImageIO;

import GameManager.GamePanel;

public class TileMap {

    // position
    private double x;
    private double y;

    // bounds
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;

    private double tween;

    // map
    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;

    // tileset
    private BufferedImage tileset;
    private int numTilesAcross;
    private Tile[][] tiles;

    // drawing
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
        numColsToDraw = GamePanel.WIDTH / tileSize + 2;
        tween = 0.07;
    }

    public void loadTiles(String s) {

        try {

            tileset = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
            numTilesAcross = tileset.getWidth() / tileSize;
            tiles = new Tile[2][numTilesAcross];

            BufferedImage subimage;
            for (int j = 0; j < numTilesAcross; j++) {
                subimage = tileset.getSubimage(j * tileSize, 0, tileSize, tileSize);
                tiles[0][j] = new Tile(subimage, Tile.NORMAL);
                
                subimage = tileset.getSubimage(j * tileSize, tileSize, tileSize, tileSize);
                tiles[1][j] = new Tile(subimage, Tile.BLOCKED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String s) {

        try {

            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;

            String delims = "\\s+";
            for (int i = 0; i < numRows; i++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for (int j = 0; j < numCols; j++) {
                    map[i][j] = Integer.parseInt(tokens[j]);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getTileSize() {
        return tileSize;
    }

    public int getx() {
        return (int) x;
    }

    public int gety() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getType(int row, int col) {
        int rc = map[row][col];
        int r = rc / numTilesAcross;
        int c = rc % numTilesAcross;
        return tiles[r][c].getType();
    }

    public void setPosition(double x, double y) {

        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;

        fixBounds();

        colOffset = (int) -this.x / tileSize;
        rowOffset = (int) -this.y / tileSize;

    }

    private void fixBounds() {
        if (x < xmin) {
            x = xmin;
        }
        if (y < ymin) {
            y = ymin;
        }
        if (x > xmax) {
            x = xmax;
        }
        if (y > ymax) {
            y = ymax;
        }
    }

    public void draw(Graphics2D g) {

        for (int i = rowOffset; i < rowOffset + numRowsToDraw; i++) {

            if (i >= numRows) {
                break;
            }

            for (int j = colOffset; j < colOffset + numColsToDraw; j++) {

                if (j >= numCols) {
                    break;
                }

                if (map[i][j] == 0) {
                    continue;
                }

                int rc = map[i][j];
                int r = rc / numTilesAcross;
                int c = rc % numTilesAcross;

                g.drawImage(tiles[r][c].getImg(), (int) x + i * tileSize, (int) y + j * tileSize, null);

            }

        }

    }

}
