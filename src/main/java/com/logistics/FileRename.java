package com.logistics;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileRename {

	private static final String FILE_PATH = "C:\\Users\\Administrator\\git\\logistics\\src\\main\\java\\com\\logistics";
	
	private static List<File> serviceFileList = new ArrayList<>();
	
	public static void printAllService(File[] files) throws IOException {
		for(File f : files) {
			if(f.isDirectory()) {
				printAllService(f.listFiles());
			}else {
				if(f.getName().endsWith("Service.java")) {
					serviceFileList.add(f);
				}
				if(f.getName().endsWith("ServiceImpl.java")) {
					String name = f.getName().replace("Impl.java", "");
					List<String> newLines = new ArrayList<>();
					for (String line : Files.readAllLines(Paths.get(f.getPath()), StandardCharsets.UTF_8)) {
					    if (line.contains("Impl implements " + name)) {
					    	System.out.println(name);
					       newLines.add(line.replace("Impl implements " + name, ""));
					       //public FileUploadServiceImpl(
					    } else if(line.contains(name + "Impl(")){ 
						   newLines.add(line.replace(name + "Impl(", name + "("));
					    } else if(line.contains("@Override")){ 
							   newLines.add(line.replace("@Override", ""));
					    }  else if(line.contains("//@Override")){ 
							   newLines.add(line.replace("//@Override", ""));
					    } else {
					       newLines.add(line);
					    }
					}
					Files.write(Paths.get(f.getPath().replace("Impl", "")), newLines, StandardCharsets.UTF_8);
					f.delete();
				}
			}
		}

//		for(int i=serviceFileList.size()-1;i>=0;i--) {
//			final File serviceFile = serviceFileList.get(i);
//    		serviceFileList.remove(serviceFile);
//    		System.out.println(serviceFile.delete());
//		}
	}
	public static void main(String[] args) throws IOException {
		File rootPath = new File(FILE_PATH);
		printAllService(rootPath.listFiles());
		
		String line  ="@Override";
		System.out.println(line.contains("@Override") );
				
	}
}
