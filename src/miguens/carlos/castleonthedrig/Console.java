package miguens.carlos.castleonthedrig;

public class Console {
	
	public void writeLine(String line)
	{
		System.out.println(line);
	}
	
	public void write(String line)
	{
		System.out.print(line);
	}
	
	public void writeLine(String[] line)
	{
		for (int i = 0; i < line.length; i++)
			System.out.println(line[i]);
	}

	public void write(String[] part)
	{
		for (int i = 0; i < part.length; i++)
			System.out.print(part[i]);
	}

	
	public void writeLine(int[] lines)
	{	
		for (int i = 0; i < lines.length; i++)
			System.out.println("0" + lines[i]);
	}
	
	public void write(int[] part)
	{
		for (int i = 0; i < part.length; i++)
			System.out.print("0" + part[i]);
	}
	
	public void writeLine(int[][] lines)
	{	
		for (int i = 0; i < lines.length; i++)
		{
			System.out.print(lines[i][0]);
			System.out.print(", ");
			System.out.println(lines[i][1]);
		}
	}
	
	public void write(int[][] lines)
	{	
		for (int i = 0; i < lines.length; i++)
		{
			System.out.print("(");
			System.out.print(lines[i][0]);
			System.out.print(", ");
			System.out.print(lines[i][1]);
			System.out.print(") ");
		}
		System.out.println("");
	}
}
