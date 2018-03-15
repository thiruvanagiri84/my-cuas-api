package com.thomsonreuters.cpl.cuasapi.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Utility {
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://Santosh//UploadedFiles//";

	public void saveFileToFolder(MultipartFile file) throws IOException {
    	byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
    }
    
    public void saveFilesToFolder(List<MultipartFile> files) throws IOException {
    	
    	for(MultipartFile file:files) {
    		byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
    	}
    }
    
    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
    
    public static String getExtension(String fileName) {
        char ch;
        int len;
        if(fileName==null || 
                (len = fileName.length())==0 || 
                (ch = fileName.charAt(len-1))=='/' || ch=='\\' || //in the case of a directory
                 ch=='.' ) //in the case of . or ..
            return "";
        int dotInd = fileName.lastIndexOf('.'),
            sepInd = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
        if( dotInd<=sepInd )
            return "";
        else
            return fileName.substring(dotInd+1).toLowerCase();
    }
    
    /*@Test
    public void testGetExtension() {
        assertEquals("", getExtension("C"));
        assertEquals("ext", getExtension("C.ext"));
        assertEquals("ext", getExtension("A/B/C.ext"));
        assertEquals("", getExtension("A/B/C.ext/"));
        assertEquals("", getExtension("A/B/C.ext/.."));
        assertEquals("bin", getExtension("A/B/C.bin"));
        assertEquals("hidden", getExtension(".hidden"));
        assertEquals("dsstore", getExtension("/user/home/.dsstore"));
        assertEquals("", getExtension(".strange."));
        assertEquals("3", getExtension("1.2.3"));
        assertEquals("exe", getExtension("C:\\Program Files (x86)\\java\\bin\\javaw.exe"));
    }*/
	
}
