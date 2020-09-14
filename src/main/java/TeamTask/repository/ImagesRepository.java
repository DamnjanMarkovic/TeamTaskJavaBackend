package TeamTask.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import TeamTask.models.Images;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Images img where img.imagename =(:imagename)")
    void removeImage(String imagename);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Task tas where tas.taskid =(:taskid)")
    void deleteByIdTask(UUID taskid);


    @Query(value = "SELECT images.id_image FROM images WHERE images.imagename = ?",
            nativeQuery = true)
    List<Integer> getSpecificPhoto(String role);


    @Query(value = "SELECT * FROM images WHERE images.id_image = ?",
            nativeQuery = true)
    Images getSpecificPhotos(Integer id_image);

    @Query(value = "select count(*) from images WHERE imagename = ?",
            nativeQuery = true)
    Integer checkImagetexistance(String imagename);

    @Query(value = "SELECT id_image FROM images WHERE imagename = ?",
            nativeQuery = true)
    Integer returnImageIDBasedOnImageName(String imagename);

}
