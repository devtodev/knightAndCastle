package miguens.carlos.castleonthedrig;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Solution {
	static int MAXSIZEMAP = 100;
	
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
			if ((position[0] < 0) || (position[0] >= size) || (position[1] < 0) || (position[1] >= size))
				return 'X';
			
			return grid[position[0]][position[1]];
		}

		public void setVisited(int[] position, int nStep)
		{
			grid[position[0]][position[1]] = 'V';
			
		}

		public void setFree(int[] position)
		{
			grid[position[0]][position[1]] = '.';
		}
		
		
	}


	public class Knight {
		int X = 1;
		int Y = 0;
		int MAXSIZEMAP = 100;
		int MAXALLOWEDSTEPS = 5000;
		
		private int path[][] = new int[MAXALLOWEDSTEPS][2];
		private int shortenPath[][] = new int[MAXALLOWEDSTEPS][2];
		private int nStep_ShortenPath = MAXALLOWEDSTEPS;
		private int nRoadmap_min = MAXALLOWEDSTEPS;
		private int nStep = 0;
		public int[][] roadmap = new int[MAXALLOWEDSTEPS][2];
		public int nRoadmap = 0;
		

		public int[][] stepsToRoadmap(int[][] steps) {
			nRoadmap = 0;
			roadmap[nRoadmap][X] = steps[0][X];
			roadmap[nRoadmap][Y] = steps[0][Y];
			nRoadmap++;
			char direction = 'D', oldDirection = '*';
			int i = 1;
			while ((direction != '*') && (i < nStep))
			{
				direction = '*';
				if (steps[i-1][X] > steps[i][X])
					direction = 'L';
				
				if (steps[i-1][X] < steps[i][X])
					direction = 'R';
				
				if (steps[i-1][Y] > steps[i][Y])
					direction = 'U';
				
				if (steps[i-1][Y] < steps[i][Y])
					direction = 'D';
				
				if (oldDirection == '*')
					oldDirection = direction;
				
				if (oldDirection != direction)
				{
					// create new roadmap point
					roadmap[nRoadmap][0] = steps[i-1][0];
					roadmap[nRoadmap][1] = steps[i-1][1];
					nRoadmap++;
					oldDirection = direction;
				}
				
				i++;
			}
			// create new roadmap point
			roadmap[nRoadmap][0] = steps[i-1][0];
			roadmap[nRoadmap][1] = steps[i-1][1];
			
			return roadmap;
		}
		
		private boolean castleFounded(int[][] newPath)
		{	
			if (nStep <= nStep_ShortenPath)
			{
				stepsToRoadmap(newPath);
				if (nRoadmap < nRoadmap_min)
				{
					nStep_ShortenPath = nStep;
					nRoadmap_min = nRoadmap;
					for (int i = 0; i < nStep; i++)
					{
						shortenPath[i][0] = newPath[i][0];
						shortenPath[i][1] = newPath[i][1];
					}
					return true;
				}
			}
			
			return false;
		}
		
		private int goNextStep(int[] position, Map map)
		{
			// Is it free?
			if (map.getPosition(position) == '.')
			{
				map.setVisited(position, nStep);
				path[nStep][X] = position[X];
				path[nStep][Y] = position[Y];
				nStep++;
				if ((map.castle[0] == position[0]) && (map.castle[1] == position[1]))
				{
					castleFounded(path);
					map.setFree(position);
					path[nStep][X] = -1;
					path[nStep][Y] = -1;
					nStep--;
					return 1;
				} else {
					goToCastle(position, map);
					map.setFree(position);
					path[nStep][X] = -1;
					path[nStep][Y] = -1;
					nStep--;
					return 0;
				}
			}
			
			return -1;
		}
		
		private int[][] goToCastle(int[] position, Map map) {
			position[X]++;// go right
			if (!((position[0] < 0) || (position[1] < 0) || (position[0] >= map.size) || (position[1] >= map.size) || (nStep >= nStep_ShortenPath)))
					goNextStep(position, map);
			position[X]--;// center
			
			position[Y]--;// go up
			if (!((position[0] < 0) || (position[1] < 0) || (position[0] >= map.size) || (position[1] >= map.size) || (nStep >= nStep_ShortenPath)))
				goNextStep(position, map);
			position[Y]++;// center
			
			position[X]--;// go left
			if (!((position[0] < 0) || (position[1] < 0) || (position[0] >= map.size) || (position[1] >= map.size) || (nStep >= nStep_ShortenPath)))
				goNextStep(position, map);
			position[X]++;// center
			
			position[Y]++;// go down
			if (!((position[0] < 0) || (position[1] < 0) || (position[0] >= map.size) || (position[1] >= map.size) || (nStep >= nStep_ShortenPath)))
				goNextStep(position, map);
			position[Y]--;// center
				
			return path;
		}
		
		public int[][] goToCastleFrom(int[] from, Map map)
		{
			nStep = 0;
			path[nStep][X] = from[X];
			path[nStep][Y] = from[Y];
			nStep++;
			goToCastle(from, map);

			return shortenPath;
		}
	}


public static void main(String args[]) {
	try {
		new Solution().execute();
	}catch(IOException e){}
}
	public void execute() throws IOException {
		long past = Calendar.getInstance().getTimeInMillis();
		
		BufferedReader br = new BufferedReader(new InputStreamReader( System.in ) );
		
		Knight knight = new Knight();
		int mapSize = 0;
		Map map = null;
		String cursor;
    	
		if (1 == 0)
		{
			while ((mapSize <= 0) || (mapSize > MAXSIZEMAP))
				mapSize = new Integer(br.readLine()).intValue();
	        
			map = new Map(mapSize);

	        // iterate map input
	        for (int i = 0; i < mapSize; i++)
	        {
	        	cursor = br.readLine();
	        	map.grid[i] = cursor.toCharArray();
	        }
	        cursor = br.readLine();
	    	String[] positions = cursor.split(" ");
	        int[] position = new int[2];
	        
	    	position[0] = (int) (new Integer(positions[0]));
	    	position[1] = (int) (new Integer(positions[1]));
	
	    	map.castle[0] = (int) (new Integer(positions[2]));
	    	map.castle[1] = (int) (new Integer(positions[3]));
		} else {
			map = new Map(40);
			int i = 0;
			map.grid[i] = new String("...X......XX.X...........XX....X.XX.....").toCharArray(); i++;
			map.grid[i] = new String(".X..............X...XX..X...X........X.X").toCharArray(); i++;
			map.grid[i] = new String("......X....X....X.........X...........X.").toCharArray(); i++;
			map.grid[i] = new String(".X.X.X..X........X.....X.X...X.....X..X.").toCharArray(); i++;
			map.grid[i] = new String("....X.X.X...X..........X..........X.....").toCharArray(); i++;
			map.grid[i] = new String("..X......X....X....X...X....X.X.X....XX.").toCharArray(); i++;
			map.grid[i] = new String("...X....X.......X..XXX.......X.X.....X..").toCharArray(); i++;
			map.grid[i] = new String("...X.X.........X.X....X...X.........X...").toCharArray(); i++;
			map.grid[i] = new String("XXXX..X......X.XX......X.X......XX.X..XX").toCharArray(); i++;
			map.grid[i] = new String(".X........X....X.X......X..X....XX....X.").toCharArray(); i++;
			map.grid[i] = new String("...X.X..X.X.....X...X....X..X....X......").toCharArray(); i++;
		   map.grid[i] = new String("....XX.X.....X.XX.X...X.X.....X.X.......").toCharArray(); i++;
		   map.grid[i] = new String(".X.X.X..............X.....XX..X.........").toCharArray(); i++;
		   map.grid[i] = new String("..X...............X......X........XX...X").toCharArray(); i++;
		   map.grid[i] = new String(".X......X...X.XXXX.....XX...........X..X").toCharArray(); i++;
		   map.grid[i] = new String("...X....XX....X...XX.X..X..X..X.....X..X").toCharArray(); i++;
		   map.grid[i] = new String("...X...X.X.....X.....X.....XXXX.........").toCharArray(); i++;
		   map.grid[i] = new String("X.....XX..X.............X.XX.X.XXX......").toCharArray(); i++;
		   map.grid[i] = new String(".....X.X..X..........X.X..X...X.X......X").toCharArray(); i++;
		   map.grid[i] = new String("...X.....X..X.............X......X..X..X").toCharArray(); i++;
		   map.grid[i] = new String("........X.....................X....X.X..").toCharArray(); i++;
		   map.grid[i] = new String("..........X.....XXX...XX.X..............").toCharArray(); i++;
		   map.grid[i] = new String("........X..X..........X.XXXX..X.........").toCharArray(); i++;
		   map.grid[i] = new String("..X..X...X.......XX...X.....X...XXX..X..").toCharArray(); i++;
		   map.grid[i] = new String(".X.......X..............X....X...X....X.").toCharArray(); i++;
		   map.grid[i] = new String(".................X.....X......X.....X...").toCharArray(); i++;
		   map.grid[i] = new String(".......X.X.XX..X.XXX.X.....X..........X.").toCharArray(); i++;
		   map.grid[i] = new String("X..X......X..............X..X.X.......X.").toCharArray(); i++;
		   map.grid[i] = new String("X........X.....X.....X....XX.......XX...").toCharArray(); i++;
		   map.grid[i] = new String("X.....X.................X.....X..X...XXX").toCharArray(); i++;
		   map.grid[i] = new String("XXX..X..X.X.XX..X....X.....XXX..X......X").toCharArray(); i++;
		   map.grid[i] = new String("..........X.....X.....XX................").toCharArray(); i++;
		   map.grid[i] = new String("..X.........X..X.........X...X.....X....").toCharArray(); i++;
		   map.grid[i] = new String(".X.X....X...XX....X...............X.....").toCharArray(); i++;
		   map.grid[i] = new String(".X....X....XX.XX........X..X............").toCharArray(); i++;
		   map.grid[i] = new String("X...X.X................XX......X..X.....").toCharArray(); i++;
		   map.grid[i] = new String("..X.X.......X.X..X.....XX.........X..X..").toCharArray(); i++;
		   map.grid[i] = new String("........................X..X.XX..X......").toCharArray(); i++;
		   map.grid[i] = new String("........X..X.X.....X.....X......X.......").toCharArray(); i++;
		   map.grid[i] = new String(".X..X....X.X......XX....................").toCharArray(); i++;
			
			cursor = "34 28 16 8"; // result 9
		}
		
    	String[] positions = cursor.split(" ");
        int[] position = new int[2];
        
    	position[0] = (int) (new Integer(positions[0]));
    	position[1] = (int) (new Integer(positions[1]));

    	map.castle[0] = (int) (new Integer(positions[2]));
    	map.castle[1] = (int) (new Integer(positions[3]));

		knight.goToCastleFrom(position, map);
		/*
		int[][] lines = knight.roadmap;
		for (i = 0; i < lines.length; i++)
		{
			System.out.print(lines[i][0]);
			System.out.print(", ");
			System.out.println(lines[i][1]);
		}

    	*/
    	System.out.println(new Integer(knight.nRoadmap).toString());
    	long now = Calendar.getInstance().getTimeInMillis();
		System.out.println(now - past);
	}
}