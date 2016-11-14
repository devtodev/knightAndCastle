package miguens.carlos.castleonthedrig;

public class Map {
	int MAXSIZEMAP = 100;
	public int size = 0;
	public char[][] grid = new char[MAXSIZEMAP][MAXSIZEMAP];
	public int[] castle = new int[2];
	
	public Map() {
		grid = new char[MAXSIZEMAP][MAXSIZEMAP];
	}
	
	public Map(int size) {
		grid = new char[size][size];
		this.size = size;
	}
	
	public char getPosition(int[] position)
	{
		if ((position[0] < 0) || (position[0] >= size))
			return 'X';
		
		if ((position[1] < 0) || (position[1] >= size))
			return 'X';
		
		return grid[position[0]][position[1]];
	}

	public void setVisited(int[] position, int nStep)
	{
		if (nStep < 10)
			grid[position[0]][position[1]] = (char) ('0' + nStep);
		else 
			grid[position[0]][position[1]] = (char) ('A' + nStep-10);
		
		/*Console console = new Console();
		
		for(int i = 0; i < size; i++)
		{
			console.writeLine(new String(grid[i]));
		}
		console.writeLine("");*/

	}

	public void setFree(int[] position)
	{
		grid[position[0]][position[1]] = '.';
		
	/*	Console console = new Console();
		
		for(int i = 0; i < size; i++)
		{
			console.writeLine(new String(grid[i]));
		}
		console.writeLine("");*/
	}
	
	
}
