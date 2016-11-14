package miguens.carlos.castleonthedrig;

import java.util.*;


public class CastleOnTheGrid {
	static int MAXSIZEMAP = 100;
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		Knight knight = new Knight();
		int mapSize = 0;
		
		while ((mapSize <= 0) || (mapSize > MAXSIZEMAP))
			mapSize = in.nextInt();
        
		Map map = new Map(mapSize);
		
        String cursor;
        // flush input
        in.nextLine();
        // iterate map input
        for (int i = 0; i < mapSize; i++)
        {
        	cursor = in.nextLine();
        	map.grid[i] = cursor.toCharArray();
        }
        cursor = in.nextLine();
    	String[] positions = cursor.split(" ");
        int[] position = new int[2];
        
    	position[0] = (int) (new Integer(positions[0]));
    	position[1] = (int) (new Integer(positions[1]));

    	map.castle[0] = (int) (new Integer(positions[2]));
    	map.castle[1] = (int) (new Integer(positions[3]));
    	
		knight.goToCastleFrom(position, map);
    	
    	Console console = new Console();
    	console.write(new Integer(knight.nRoadmap).toString());
    	/*
    	 * (0, 0) (1, 0) (2, 0) (3, 0) (4, 0) (4, 1) (3, 1) (3, 2) (-1, -1)
    	 */
	}
}
