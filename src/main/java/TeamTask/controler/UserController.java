package TeamTask.controler;

import TeamTask.models.User;
import TeamTask.models.dto.UsersInTeamResponse;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import TeamTask.models.Images;
import TeamTask.models.dto.UserRequest;
import TeamTask.models.dto.UserResponse;
import TeamTask.service.ImagesService;
import TeamTask.service.UserService;


import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping(value = "/rest/users")

public class UserController {

	@Autowired
	private final UserService userService;

	@Autowired
	private ImagesService imagesService;

	public UserController( UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/all")
	public List<UserResponse> getAll() {
		return userService.getAll();
	}


	@GetMapping("/getUsersInTeam/{idTeam}")
	public List<UsersInTeamResponse> getUsersInTeam(@PathVariable UUID idTeam) throws EntityNotFoundException {
		return userService.getUsersInTeam(idTeam);
	}

//	@GetMapping("/getUserOnEmail/{useremail}")
//	public List<UserResponse> getUserOnEmail(@PathVariable String useremail) throws EntityNotFoundException {
//
//		return userService.getUserOnEmail(useremail);
//	}


	@GetMapping("/{id}")
	public List<UserResponse> getUserOnID(@PathVariable UUID id) throws EntityNotFoundException {

		return userService.getUser(id);
	}

	@DeleteMapping("/deleteUser/{id_user}")
	public void deleteUser (@PathVariable Integer id_user) throws Exception {

		userService.deleteUser(id_user);

	}


	@RequestMapping(value = "/getUserImageAsZipInFolder/{id}", produces="application/zip")
		public ResponseEntity<StreamingResponseBody> getUserImageAsZipInFolder(@PathVariable Integer id) {
			return ResponseEntity
				.ok()
				.header("Content-Disposition", "attachment; filename=\"usersImage.zip\"")
				.body(out -> {
					ZipOutputStream zipOutputStream = new ZipOutputStream(out);
					ArrayList<File> files = new ArrayList<>();
					Optional<Images> photoDTO = imagesService.getPhoto(id);
					files.add(new File(photoDTO.get().getImageLocation()));
					for (File file : files) {
						zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
						FileInputStream fileInputStream = new FileInputStream(file);
						IOUtils.copy(fileInputStream, zipOutputStream);
						fileInputStream.close();
						zipOutputStream.closeEntry();
					}
					zipOutputStream.close();
				});
	}

	@RequestMapping(value = "/getUserImages", produces="application/zip")
	public ResponseEntity<StreamingResponseBody> getUserImages() {
		return ResponseEntity
				.ok()
				.header("Content-Disposition", "attachment; filename=\"userImages.zip\"")
				.body(out -> {
					ZipOutputStream zipOutputStream = new ZipOutputStream(out);
					ArrayList<File> files = new ArrayList<>();
					List<Images> photos = userService.getUsersPhotos();
					for (Images imags: photos						 ) {
						if (imags!=null) {
							files.add(new File(imags.getImageLocation()));
						}
					}
					for (File file : files) {
						zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
						FileInputStream fileInputStream = new FileInputStream(file);
						IOUtils.copy(fileInputStream, zipOutputStream);
						fileInputStream.close();
						zipOutputStream.closeEntry();
					}
					zipOutputStream.close();
				});
	}





	@PostMapping(value = "/load")
	public String loadUser (@Valid @RequestBody UserRequest userRequest){
		String result = null;
		String response = null;
		try {
			response = userService.save(userRequest);
				result = response;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	@PostMapping(value = "/signUpUser", consumes = {"multipart/form-data"})
	public String saveUser (@RequestParam("imageFile") @PathVariable MultipartFile imageFile,
							UserRequest userRequest){
		String result = null;
		String response = null;
		System.out.println("nesto");
		Images image = new Images();
		image.setImagename(imageFile.getOriginalFilename());

		try {

			Integer id_image = imagesService.saveSpecificImage(imageFile, image);
			userRequest.setId_image(id_image);
			response = userService.save(userRequest);
			result = response;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@PostMapping(value = "/addNewUserInTeam", consumes = {"multipart/form-data"})
	public String addNewUserInTeam (@RequestParam("imageFile") @PathVariable MultipartFile imageFile,
							UserRequest userRequest){
		String result = null;
		String response = null;
		System.out.println("nesto");
		Images image = new Images();
		image.setImagename(imageFile.getOriginalFilename());

		try {
			Integer id_image = imagesService.saveSpecificImage(imageFile, image);
			userRequest.setId_image(id_image);
			response = userService.addNewUserInTeam(userRequest);
			result = response;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}





}
