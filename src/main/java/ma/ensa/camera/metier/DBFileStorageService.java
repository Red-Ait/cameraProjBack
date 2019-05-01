package ma.ensa.camera.metier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ma.ensa.camera.dao.DBFileDao;
import ma.ensa.camera.entities.DBFile;

import org.springframework.util.StringUtils;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileDao dbFileRepository;

    public DBFile storeFile(MultipartFile file) throws Exception {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId).get();
    }
    public List<String> getPhotos() {
    	List<String> paths = new  ArrayList<String>();
    	System.out.println("oo" + this.dbFileRepository.findAll().size());
    	this.dbFileRepository.findAll().forEach(d -> {
    		
    		paths.add(d.getId());
    	});
    	return paths;
    }
}
