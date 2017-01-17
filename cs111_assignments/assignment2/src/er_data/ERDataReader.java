package er_data;

import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class ERDataReader {
	public static int[][][] readData(String dataFile) throws FileNotFoundException, NoSuchElementException, IllegalStateException  {	
		int weeks = 4;
		int days = 7;
		int hours = 24;
		Scanner con = new Scanner(new FileReader(dataFile));
		int[][][] anArray = new int[weeks][days][hours];
		while(con.hasNext()){
			for(int x = 0; x < 4; x++){
				for(int y = 0; y < 7; y++){
					for(int z = 0; z < 24; z++){
						anArray[x][y][z] = con.nextInt();
						//System.out.println(anArray[x][y][z]);
					}
				}

			}
		}
		con.close();
		return anArray;
	}
}