package miguens.carlos.castleonthedrig;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Calculo {
	int MAXSIZEMAP = 100;
	int MAXBLOC = 999999;
	int MAXDIRECTIONS = 4;
	char[] DIRECTIONS = {'R', 'L', 'U', 'D'};
	char BLOCK = 'X';
	char EMPTY = '.';
	int Y = 1;
	int X = 0;
	
	int mapSize = 0;
	int[] knight = new int[2];
	int[] castle = new int[2];
	char[][] grid = new char[MAXSIZEMAP][MAXSIZEMAP];
	int[][] map = new int[MAXSIZEMAP+2][MAXSIZEMAP+2];
	
	public static void main(String args[]) {
		try {
			new Solution().execute();
		}catch(IOException e){}
	}
	
	private void mover(char direction)
	{
		switch (direction)
		{
			case 'R':
				xy[X]++;
				break;
			case 'L':
				xy[X]--;
				break;
			case 'D':
				xy[Y]++;
				break;
			case 'U':
				xy[Y]--;
				break;
		}
	}
	
	private void imprimirGrid()
	{
    	Console console = new Console();
    	for (int i = 0; i < mapSize; i++)
    	{
    		console.writeLine(new String(grid[i]));
    	}
    	console.writeLine("");
	}

	int[] xy = new int[2];
	
	private void visitMapCrux(int centerX, int centerY, int weight)
	{	
		int[] newCenter = new int[2];
		xy[X] = centerX;
		xy[Y] = centerY;
		newCenter[X] = centerX;
		newCenter[Y] = centerY;
		map[xy[X]][xy[Y]] = weight;
		grid[xy[X]-1][xy[Y]-1] = (char)('0' + weight);		// visit
		
		for (int i = 0; i < MAXDIRECTIONS; i++)
		{
			char centerDirection = DIRECTIONS[i];
			mover(centerDirection);
			while ((map[xy[X]][xy[Y]] == -1) || 
				  ((map[xy[X]][xy[Y]] > weight) && (map[xy[X]][xy[Y]] != MAXBLOC)))		// if empty
			{
				
				
				for (int j = 0; j < MAXDIRECTIONS; j++)
				{
					char visitDirection = DIRECTIONS[i];
					mover(visitDirection);
					while ((map[xy[X]][xy[Y]] == -1) || 
						  ((map[xy[X]][xy[Y]] > weight) && (map[xy[X]][xy[Y]] != MAXBLOC)))		// if empty
					{
						map[xy[X]][xy[Y]] = weight+1;
						grid[xy[X]-1][xy[Y]-1] = (char)('0' + weight+1);		// visit
						mover(visitDirection);
					}
					xy[X] = centerX;
					xy[Y] = centerY;
				}

				
				
			}
			xy[X] = centerX;
			xy[Y] = centerY;
		}
		
		
		imprimirGrid();

		for (int i = 0; i < MAXDIRECTIONS; i++)
		{
			char direction = DIRECTIONS[i];
			xy[X] = centerX;
			xy[Y] = centerY;
			mover(direction);
			while ((map[xy[X]][xy[Y]] == -1) || 
				  ((map[xy[X]][xy[Y]] > weight) && (map[xy[X]][xy[Y]] != MAXBLOC)))		// if empty
			{
				
				visitMapCrux(xy[X], xy[Y], weight+1);
			
			}
		}

	}

	private void initGrid()
	{
		for (int x = 0; x < mapSize; x++)
			for (int y = 0; y < mapSize; y++)
			{
				map[x+1][y+1] = (grid[x][y] == BLOCK)?MAXBLOC:-1;
			}
		
		for(int i = 0; i < mapSize+2; i++)
		{
			map[i][0] = MAXBLOC;
			map[0][i] = MAXBLOC;
			map[i][mapSize+1] = MAXBLOC;
			map[mapSize+1][i] = MAXBLOC;
		}
	}
	
	public void execute() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader( System.in ) );
		String cursor = "";
        
		if (1 == 0)
		{
			while ((mapSize <= 0) || (mapSize > MAXSIZEMAP))
				mapSize = new Integer(br.readLine()).intValue();

	        // iterate map input
	        for (int i = 0; i < mapSize; i++)
	        {
	        	cursor = br.readLine();
	        	grid[i] = cursor.toCharArray();
	        }
	        cursor = br.readLine();
	    	String[] positions = cursor.split(" ");
	        
	    	knight[0] = (int) (new Integer(positions[0]));
	    	knight[1] = (int) (new Integer(positions[1]));
	
	    	castle[0] = (int) (new Integer(positions[2]));
	    	castle[1] = (int) (new Integer(positions[3]));
		} else {
			int ej = 3;
			int i;
			switch(ej)
			{
			case 1:
				i = 0;
				mapSize = 40;
				grid[i] = new String("...X......XX.X...........XX....X.XX.....").toCharArray(); i++;
				grid[i] = new String(".X..............X...XX..X...X........X.X").toCharArray(); i++;
				grid[i] = new String("......X....X....X.........X...........X.").toCharArray(); i++;
				grid[i] = new String(".X.X.X..X........X.....X.X...X.....X..X.").toCharArray(); i++;
				grid[i] = new String("....X.X.X...X..........X..........X.....").toCharArray(); i++;
				grid[i] = new String("..X......X....X....X...X....X.X.X....XX.").toCharArray(); i++;
				grid[i] = new String("...X....X.......X..XXX.......X.X.....X..").toCharArray(); i++;
				grid[i] = new String("...X.X.........X.X....X...X.........X...").toCharArray(); i++;
				grid[i] = new String("XXXX..X......X.XX......X.X......XX.X..XX").toCharArray(); i++;
				grid[i] = new String(".X........X....X.X......X..X....XX....X.").toCharArray(); i++;
				grid[i] = new String("...X.X..X.X.....X...X....X..X....X......").toCharArray(); i++;
			    grid[i] = new String("....XX.X.....X.XX.X...X.X.....X.X.......").toCharArray(); i++;
			    grid[i] = new String(".X.X.X..............X.....XX..X.........").toCharArray(); i++;
			    grid[i] = new String("..X...............X......X........XX...X").toCharArray(); i++;
			    grid[i] = new String(".X......X...X.XXXX.....XX...........X..X").toCharArray(); i++;
			    grid[i] = new String("...X....XX....X...XX.X..X..X..X.....X..X").toCharArray(); i++;
			    grid[i] = new String("...X...X.X.....X.....X.....XXXX.........").toCharArray(); i++;
			    grid[i] = new String("X.....XX..X.............X.XX.X.XXX......").toCharArray(); i++;
			    grid[i] = new String(".....X.X..X..........X.X..X...X.X......X").toCharArray(); i++;
			    grid[i] = new String("...X.....X..X.............X......X..X..X").toCharArray(); i++;
			    grid[i] = new String("........X.....................X....X.X..").toCharArray(); i++;
			    grid[i] = new String("..........X.....XXX...XX.X..............").toCharArray(); i++;
			    grid[i] = new String("........X..X..........X.XXXX..X.........").toCharArray(); i++;
			    grid[i] = new String("..X..X...X.......XX...X.....X...XXX..X..").toCharArray(); i++;
			    grid[i] = new String(".X.......X..............X....X...X....X.").toCharArray(); i++;
			    grid[i] = new String(".................X.....X......X.....X...").toCharArray(); i++;
			    grid[i] = new String(".......X.X.XX..X.XXX.X.....X..........X.").toCharArray(); i++;
			    grid[i] = new String("X..X......X..............X..X.X.......X.").toCharArray(); i++;
			    grid[i] = new String("X........X.....X.....X....XX.......XX...").toCharArray(); i++;
			    grid[i] = new String("X.....X.................X.....X..X...XXX").toCharArray(); i++;
			    grid[i] = new String("XXX..X..X.X.XX..X....X.....XXX..X......X").toCharArray(); i++;
			    grid[i] = new String("..........X.....X.....XX................").toCharArray(); i++;
			    grid[i] = new String("..X.........X..X.........X...X.....X....").toCharArray(); i++;
			    grid[i] = new String(".X.X....X...XX....X...............X.....").toCharArray(); i++;
			    grid[i] = new String(".X....X....XX.XX........X..X............").toCharArray(); i++;
			    grid[i] = new String("X...X.X................XX......X..X.....").toCharArray(); i++;
			    grid[i] = new String("..X.X.......X.X..X.....XX.........X..X..").toCharArray(); i++;
			    grid[i] = new String("........................X..X.XX..X......").toCharArray(); i++;
			    grid[i] = new String("........X..X.X.....X.....X......X.......").toCharArray(); i++;
			    grid[i] = new String(".X..X....X.X......XX....................").toCharArray(); i++;
			 	
				cursor = "34 28 16 8"; // result 9
				cursor = "39 0 39 3"; // result 3
				break;
			case 2:
				i = 0;
				mapSize=3;
				grid[i] = new String(".X.").toCharArray(); i++;
				grid[i] = new String(".X.").toCharArray(); i++;
				grid[i] = new String("...").toCharArray(); i++;
				
				cursor = "0 0 0 2"; // result 3
				break;
			case 3:
				i = 0;
				mapSize = 10;
				grid[i] = new String(".X..XX...X").toCharArray(); i++;
				grid[i] = new String("X.........").toCharArray(); i++;
				grid[i] = new String(".X.......X").toCharArray(); i++;
				grid[i] = new String("..........").toCharArray(); i++;
				grid[i] = new String("........X.").toCharArray(); i++;
				grid[i] = new String(".X...XXX..").toCharArray(); i++;
				grid[i] = new String(".........X").toCharArray(); i++;
				grid[i] = new String(".....X....").toCharArray(); i++;
				grid[i] = new String(".....XX...").toCharArray(); i++;
				grid[i] = new String(".....X...X").toCharArray(); i++;
				cursor = "9 1 9 6"; // result 3
				break;
			}
		}
		
		long past = Calendar.getInstance().getTimeInMillis();
		
    	String[] xyxy = cursor.split(" ");
        
        knight[0] = (int) (new Integer(xyxy[0]))+1;
        knight[1] = (int) (new Integer(xyxy[1]))+1;

    	castle[0] = (int) (new Integer(xyxy[2]))+1;
    	castle[1] = (int) (new Integer(xyxy[3]))+1;

    	// I get stand up and now I will be visit the map
    	initGrid();
    	visitMapCrux(knight[X], knight[Y], 0);	
	
	}
}
