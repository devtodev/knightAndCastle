package miguens.carlos.castleonthedrig;

public class Knight {
	int X = 1;
	int Y = 0;
	int MAXSIZEMAP = 100;
	int MAXALLOWEDSTEPS = 2000;
	int MAXSTEPSPERTURN = 4;
	
	private int path[][] = new int[MAXALLOWEDSTEPS][2];
	private int shortenPath[][] = new int[MAXALLOWEDSTEPS][2];
	private int nStep_ShortenPath = MAXALLOWEDSTEPS;
	private int nRoadmap_min = MAXALLOWEDSTEPS;
	private int nStep = 0;
	public int[][] roadmap = new int[MAXALLOWEDSTEPS][2];
	public int nRoadmap = 0;
	

	public int[][] stepsToRoadmap(int[][] steps) {
		for (int i = 0; i < roadmap.length; i++)
		{
			roadmap[i][0] = -1;
			roadmap[i][1] = -1;
		}
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
		stepsToRoadmap(newPath);
		Console console = new Console();
		if (nRoadmap < nRoadmap_min)
		{
			nStep_ShortenPath = nStep;
			nRoadmap_min = nRoadmap;
			for (int i = 0; i < nStep; i++)
			{
				shortenPath[i][0] = newPath[i][0];
				shortenPath[i][1] = newPath[i][1];
			}
			for (int i = nStep; i < shortenPath.length; i++)
			{
				shortenPath[i] = new int[]{-1,-1};
			}
			return true;
		}
		
		return false;
	}
	
	private int goNextStep(int[] position, Map map)
	{
		// Is a valid position?
		if ((position[0] < 0) || (position[1] < 0) || 
			(position[0] >= map.size) || (position[1] >= map.size) || (nStep >= nStep_ShortenPath))
			return -1;
		
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
		if (position[0] == 3)
			position = position;
		position[X]++;// go right
		goNextStep(position, map);
		position[X]--;// center
		
		position[Y]--;// go up
		goNextStep(position, map);
		position[Y]++;// center
		
		position[X]--;// go left
		goNextStep(position, map);
		position[X]++;// center
		
		position[Y]++;// go down
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
