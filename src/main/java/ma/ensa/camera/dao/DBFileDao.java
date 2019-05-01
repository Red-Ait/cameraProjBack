package ma.ensa.camera.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.ensa.camera.entities.*;
@Repository
public interface DBFileDao extends JpaRepository<DBFile, String> {

}