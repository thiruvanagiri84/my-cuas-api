package com.thomsonreuters.cpl.cuasapi.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thomsonreuters.cpl.cuasapi.exceptions.InvalidInputException;

@RestController
public class FileUploadController {
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://Santosh//UploadedFiles//";
    
    //Postman Testing
    //POST request http://localhost:8070/fileupload
    //Body: Select form-data, choose file, key name: "file" (this is same as @RequestParam name)
    
    @PostMapping("/fileupload")
    public String uploadFile(@RequestParam("file") MultipartFile fileToUpload) throws IOException {
    	
    	System.out.println(fileToUpload.getOriginalFilename()+" "+fileToUpload.getSize());
    	if(fileToUpload.isEmpty()) { //if size is 0 Kb
    		throw new InvalidInputException("File is empty");
    	}
    	if(fileToUpload.getSize() > 24000) { //24 KB
    		throw new InvalidInputException("File size more than 24000");
    	}
    	
    	//OR To get extension of file - use apache commons IO library, class: FileNameUtils
    	//String ext1 = FilenameUtils.getExtension("/path/to/file/foo.txt");
    	String extension = "";
    	String fileName = fileToUpload.getOriginalFilename();
    	int i = fileName.lastIndexOf('.');
    	if (i > 0) {
    	    extension = fileName.substring(i+1);
    	}
    	if(extension != "xls" || extension != "xlsx") {
    		throw new InvalidInputException("Please choose only excel with xls or xlsx extension!");
    	}
    	
    	//Upload the file to folder logic.
    	saveFileToFolder(fileToUpload);
    	
    	return "Success";
    }
    
    @PostMapping("/filesupload")
    public String uploadFiles(@RequestParam("files") MultipartFile[] filesToUpload) throws IOException {

    	System.out.println("no.of files uploaded: "+filesToUpload.length);
    	
    	// Get file name
        String uploadedFileNames = Arrays.stream(filesToUpload).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
        System.out.println("File names: "+uploadedFileNames);

        if (StringUtils.isEmpty(uploadedFileNames)) {
            throw new InvalidInputException("Please select a file!");
        }
    	
    	saveFilesToFolder(Arrays.asList(filesToUpload));
    	
    	return "Success";
    }
    
    
    @SuppressWarnings("unchecked")
	@PostMapping("/multifileupload")
    public ResponseEntity<?> uploadFileMulti(
            @RequestParam("extraField") String extraField, //Postman Body: form-data, key is "extraField", value can be any text
            @RequestParam("files") MultipartFile[] uploadfiles) {//Postman Body: form-data, key is "files", value is to choose files

        // Get file name
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {
            saveFilesToFolder(Arrays.asList(uploadfiles));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - "+ uploadedFileName, HttpStatus.OK);
        //Response Body: Successfully uploaded - File1, File2

    }
    
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
    
    //Export to excel functionality using Spring REST, using servletrequest and servletresponse
    @GetMapping("/exporttoExcel")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {

        FileInputStream inputStream = new FileInputStream(new File("C://Santosh//pom.xml"));

        response.setHeader("Content-Disposition", "attachment; filename=\"pom2.xml\"");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream, outputStream);

        outputStream.close();
        inputStream.close();
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
