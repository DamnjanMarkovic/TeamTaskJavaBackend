package TeamTask.controler;

import TeamTask.models.dto.LoginResponse;
import TeamTask.models.dto.UsersInTeamResponse;
import TeamTask.service.TaskService;
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
	private final TaskService taskService;

	@Autowired
	private ImagesService imagesService;

	public UserController(UserService userService, TaskService taskService) {
		this.userService = userService;
		this.taskService = taskService;
	}

	@GetMapping(value = "/all")
	public List<UserResponse> getAll() {
		return userService.getAll();
	}

//	@GetMapping(value = "/tableID/{table_number}/{id_restaurant}")
//	public DinningTable getSpecificDinningTable(
//			@PathVariable("table_number") int table_number,
//
//			@PathVariable("id_restaurant") int id_restaurant) {

	@PutMapping("/updateUserName/{userID}/{newName}")
	public void updateUserName(@PathVariable ("userID") String userID, @PathVariable("newName") String newName) throws EntityNotFoundException {
		userService.updateUserName(userID, newName);
	}


//	@PostMapping(value = "/signUpUser", consumes = {"multipart/form-data"})
//	public String saveUser (@RequestParam("imageFile") @PathVariable MultipartFile imageFile,
//							UserRequest userRequest){
	@PutMapping(value = "/updateUserImage", consumes = {"multipart/form-data"})
	public void updateUserImage(@RequestParam("imageFile") @PathVariable MultipartFile imageFile,
								String imagename) throws Exception {
		Images image = new Images();
		image.setImagename(imageFile.getOriginalFilename());
		imagesService.updateImage(imageFile, image);
		userService.updateUserImage(imagename);
	}

	@GetMapping("/getUsersInTeam/{idTeam}")
	public List<UsersInTeamResponse> getUsersInTeam(@PathVariable UUID idTeam) throws EntityNotFoundException {
		return userService.getUsersInTeam(idTeam);
	}

	@GetMapping("/checkIfUsernameExists/{username}")
	public String checkIfUsernameExists(@PathVariable ("username") String username) throws EntityNotFoundException {

		return userService.checkIfUsernameExists(username);
	}

	@GetMapping("/getLoggedInUser/{id_user}/{jwt}")
	public LoginResponse getLoggedInUser(@PathVariable ("id_user") String id_user, @PathVariable("jwt") String jwt) throws EntityNotFoundException {

		return userService.getLoggedInUser(id_user, jwt);
	}

	@DeleteMapping("/deleteUser/{id_user}")
	public void deleteUser (@PathVariable UUID id_user) throws Exception {

		userService.deleteUser(id_user);
//		userService.confirmdeleteUser(id_user);

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

	//ovde treba proveriti  da li je pass "faceOrAppleUser" - ako jeste, to je dodavanje korisnika fejsbuk ili apple
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

	//ovde treba proveriti  da li je pass "faceOrAppleUser" - ako jeste, to je dodavanje korisnika fejsbuk ili apple
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
