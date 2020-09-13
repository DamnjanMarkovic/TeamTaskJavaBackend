package TeamTask.service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import TeamTask.models.Images;
import TeamTask.repository.ImagesRepository;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ImagesService {

    private final ImagesRepository imagesRepository;

    public ImagesService(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Transactional
    public void saveImage(MultipartFile imageFile, Images images) throws Exception {
        savePhotoImage(imageFile, images);
        save(images);


    }
    @Transactional
    public Integer saveSpecificImage(MultipartFile imageFile, Images image) throws Exception {

        savePhotoImage(imageFile, image);
        return imagesRepository.save(image).getId_image();

    }



    public void savePhotoImage(MultipartFile imageFile, Images image) throws Exception {
        image.setImageLocation("src/main/java/TeamTask/images/" + image.getImagename());
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(image.getImageLocation());
        Files.write(path, bytes);
    }


    public void save(Images images) {

        imagesRepository.save(images);
    }

    @Transactional
    public Optional<Images> getPhoto(Integer id) {
        return imagesRepository.findById(id);
    }



    @Transactional
    public Optional<Images> getPhotoOnName(String imagename) {

        Images imagesArrived = new Images();
        imagesArrived.setImagename(imagename);
        Example<Images> photoDTO = Example.of(imagesArrived);
        return imagesRepository.findOne(photoDTO);

    }

    @Transactional
    public List<Images> getAll(){
        return imagesRepository.findAll();
    }
}