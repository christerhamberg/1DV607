package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileStorage {
	
	private int systemId = 0;
	
	private String filePath;
	private String idFilePath;
	private String memberFilePath;
	private String boatFilePath;

	private String MEMBERFILE = "member.csv";
	private String BOATFILE = "boat.csv";
	private String IDFILE = "id.csv";
	
	private static FileStorage ownInstrance = null;

	public static FileStorage getInstance (){
		
		if (ownInstrance==null) ownInstrance = new FileStorage();
		return ownInstrance;
		
	}
	
	public FileStorage (){
		
		filePath = "c:\\skolan\\YachtClub\\";
	
	}
	
	public void setFilePath (String fp){
		
		filePath = fp + File.separatorChar;
	
	}
	
	
	public boolean initFileStorage (){
		
		idFilePath = filePath + IDFILE;	
		memberFilePath = filePath + MEMBERFILE;	
		boatFilePath = filePath + BOATFILE;	
		
		// create PATH if needed
		
	    if (createDirectory (idFilePath) == false) return false;
	    
	    // read systemId or Create a new one if it does not exist
	    
	    // read a valid ID if any exists
		try {
			
			File fdir = new File (idFilePath);

			if (fdir.isFile() == true){

				Scanner sc = new Scanner (fdir);
				
				if (sc.hasNextInt() == true){
					
					systemId = sc.nextInt();
				    sc.close ();

				}
				else{
					
				    sc.close ();

					if (getNextSystemId () == -1) return false;
					else return true;
				}

			}
			else{
				if (getNextSystemId () == -1) return false;
				else return true;
		    }

	        return true;
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// if we get to this point we have failed
		
		return false;
	    
	}
	
	public boolean rebuildCache (MemberFacade rebuildCache){
		
		memberFilePath = filePath + MEMBERFILE;	
		boatFilePath = filePath + BOATFILE;	
		
	    
	    // read a valid ID if any exists
		try {
			
			File fdir = new File (memberFilePath);

			if (fdir.isFile() == true){

				Scanner sc = new Scanner (fdir);
				
				while (sc.hasNext() == true){
					
					String data = sc.nextLine();
					
					if (data.length()>1){
					
						try {
					
							String splitter[] = data.split(";");
							int id = Integer.parseInt (splitter[0]);
							int persNo= Integer.parseInt (splitter[2]);
						
							rebuildCache.initMemeber(id, splitter[1], persNo);
					
						}
						catch (NumberFormatException e){
						}
					}

				}
				
				sc.close();

			}
			
			
			fdir = new File (boatFilePath);

			if (fdir.isFile() == true){

				Scanner sc = new Scanner (fdir);
				
				while (sc.hasNext() == true){
					
					String data = sc.nextLine();
										
					if (data.length()>1){
					
						try {
					
							String splitter[] = data.split(";");
							int id = Integer.parseInt (splitter[0]);
							double length= Double.parseDouble (splitter[2]);

							rebuildCache.initBoat(id, splitter[1], length);
					
						}
						catch (NumberFormatException e){
						}
					}
				}

				sc.close();

			}
			
			

	        return true;
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// if we get to this point we have failed
		
		return false;
	    
	}	
	
	public int getNextSystemId () {
		
		int currentId = systemId;
		systemId++;
		
		if (writeDataToFile(idFilePath,""+systemId,false) == false){
			
			// restore systemId;
			systemId--;
			
			return -1; // error
		
		}
		else return currentId;
		
	}
	
	public int getTopSystemId (){
		return systemId -1;
	}
	
	public int writeNewMemberToFile (String data,boolean append){
		
		if (writeDataToFile (memberFilePath,data,append) == false) return -1;
		else return 0;
		
	}
	
	public int writeNewBoatToFile (String data,boolean append){
		
		if (writeDataToFile (boatFilePath,data,append) == false) return -1;
		else return 0;
		
	}


	
	
    private boolean createDirectory(String dirNameToSetup){
		
        File reportFile = new File(dirNameToSetup);
        File parentFile = reportFile.getParentFile();
     
        if (parentFile != null){
        	if (!parentFile.exists()) {
                
                if (parentFile.mkdirs() == false )return false ; // failed to create directory 
                
             }
        }		
        
        return true;

   }
    
    
	private boolean writeDataToFile (String filePath, String data, boolean append) {
		
		FileWriter fout;
		try {
								
			fout = new FileWriter (new File(filePath),append);
			fout.write(data);
            fout.close();
            
            return true;
            
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// if we get to this point something failed!
		
		return false;
		   
	}

}
