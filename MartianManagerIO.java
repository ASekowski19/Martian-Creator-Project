package prob1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import prob2.*;

public class MartianManagerIO {

	/**
	 *  DO NOT ALTER THIS METHOD.
	 */
	public static void writeMartians(String fileName, MartianManager mm) {
		File file = new File(fileName);
        try {
			writeMartiansFile(file, mm);
		}
        catch (FileNotFoundException e) {
			System.out.println("Error writing file");
			e.printStackTrace();
		}
	}
	
	/**
	 *  YOU  WRITE THIS METHOD.
	 *  
	 *  Write the martians in the MartianManager to the file. The format is exactly the same
	 *  as specified in the homework document for reading valid data: G I V or R I V T.
	 */
	private static void writeMartiansFile(File file, MartianManager mm) throws FileNotFoundException {
		   for(int i = 0; i<mm.getNumMartians(); i++) {
	            Martian m = mm.getMartianAt(i);
	            if(m instanceof GreenMartian) {
	                try {
	                FileWriter writer = new FileWriter(file);
	                writer.write("G " + mm.getMartianAt(i).getID() + " " + mm.getMartianAt(i).getVolume());
	                writer.close();
	            }
	                catch(IOException e){
	                    e.printStackTrace();
	                }
	            }
	                else{
	                    if( m instanceof RedMartian) {
	                        try {
	                            FileWriter writer = new FileWriter(file);
	                            writer.write("R " + mm.getMartianAt(i).getID() + " " + mm.getMartianAt(i).getVolume() + " " + ((RedMartian) mm.getMartianAt(i)).getTenacity());
	                            writer.close();
	                        }
	                            catch(IOException e){
	                                e.printStackTrace();
	                            }
	                    }
	            }
	        }
	    }
	
	/**
	 *  DO NOT ALTER THIS METHOD.
	 */
	public static ReadReport readMartians(String fileName) {
		File file = new File(fileName);
		ReadReport report = null;
        try {
			report = readMartiansFile(file);
		}
        catch (FileNotFoundException e) {
			System.out.println("Error reading file");
			e.printStackTrace();
		}
		return report;
	}
	
	/**
	 * YOU WRITE THIS METHOD.
	 * 
	 * Reads a text file that contains Martian data and returns a ReadReport object. Details
	 * are in the homework document. 
	 * 
	 * @param file
	 * @return
	 * @throws RuntimeException
	 * @throws FileNotFoundException
	 */
	private static ReadReport readMartiansFile(File file) throws RuntimeException, FileNotFoundException {
		MartianManager mm = new MartianManager();
		String fileName = file.getName();
		int numSuccessfullyAdded = 0;
		int numAlreadyExist = 0;
		int numIllFormed = 0;
		try {
		Scanner scan = new Scanner(file);
		while(scan.hasNext()) {
			String line = scan.nextLine();
			String[] MData = line.split(" ");
			RedMartian RM;
			GreenMartian GM;
				if (MData[0].equals("R")) {
					if (MData.length == 4) {
						if(isInteger(MData[1]) && isInteger(MData[2]) && isInteger(MData[3])) {
							RM = new RedMartian(Integer.parseInt(MData[1]), Integer.parseInt(MData[2]), Integer.parseInt(MData[3]));
							if(mm.addMartian(RM)){
								numSuccessfullyAdded++;
							}
							else {
								numAlreadyExist++;
							}
						}
						else {
							numIllFormed++;
							}
						}
					else {
						numIllFormed++;
					}
				}
				else if (MData[0].equals("G")){
					if (MData.length == 3) {
						if(isInteger(MData[1]) && isInteger(MData[2])){
							GM = new GreenMartian(Integer.parseInt(MData[1]), Integer.parseInt(MData[2]));
							if(mm.addMartian(GM)){
								numSuccessfullyAdded++;
							}
							else {
								numAlreadyExist++;
							}
						}
						else {
							numIllFormed++;
						}
					}
					else { 
						numIllFormed++;
						}
				}
				else { 
					numIllFormed++;
					}
	}
		int numLinesRead = numSuccessfullyAdded + numAlreadyExist + numIllFormed;
			ReadReport r = new ReadReport(mm, fileName, numLinesRead, numSuccessfullyAdded, numAlreadyExist, numIllFormed);
			scan.close();
			return r;

		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}
public static boolean isInteger(String str) {
    try {
        int x = Integer.parseInt(str);
        return true;
    }
    catch(NumberFormatException nfe) {
        return false;
    }
  }
}

