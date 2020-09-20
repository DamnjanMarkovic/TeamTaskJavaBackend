package TeamTask.controler;

import TeamTask.emailConfirmation.OnRegistrationSuccessEvent;
import TeamTask.models.User;
import TeamTask.models.Token;
import TeamTask.models.dto.LoginResponse;
import TeamTask.models.dto.UsersInTeamResponse;
import TeamTask.service.TaskService;
import TeamTask.service.TokenService;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import TeamTask.models.Images;
import TeamTask.models.dto.UserRequest;
import TeamTask.models.dto.UserResponse;
import TeamTask.service.ImagesService;
import TeamTask.service.UserService;


import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
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
	ApplicationEventPublisher eventPublisher;
	@Autowired
	private ImagesService imagesService;
	@Autowired
	private MessageSource messages;
	@Autowired
	private MailSender mailSender;
	@Autowired
	private TokenService tokenService;




	private Logger logger = Logger.getLogger(getClass().getName());

	public UserController(UserService userService, TaskService taskService) {
		this.userService = userService;
		this.taskService = taskService;
	}

	@GetMapping(value = "/all")
	public List<UserResponse> getAll() {
		return userService.getAll();
	}
	@GetMapping("/error")
	public String indexPage() {
		return messages.getMessage("message.message.expired", null, null);
	}


	@PutMapping("/updateUserName/{userID}/{newName}")
	public void updateUserName(@PathVariable ("userID") String userID, @PathVariable("newName") String newName) throws EntityNotFoundException {
		userService.updateUserName(userID, newName);
	}


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
	public LoginResponse getLoggedInUser(@PathVariable ("id_user") String id_user) throws EntityNotFoundException {

		return userService.getLoggedInUser(id_user);
	}


	@DeleteMapping("/deleteUser/{id_user}")
	public void deleteUser (@PathVariable UUID id_user) throws Exception {

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
			User user = userService.registerUser(userRequest);
				result = response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@GetMapping("/confirmRegistration")
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {
		Locale locale=request.getLocale();
		Token verificationToken = userService.getVerificationToken(token);
		if(verificationToken == null) {
			String message = messages.getMessage("auth.message.invalidToken", null, locale);
			model.addAttribute("message", message);
			return "redirect:access-denied";
		}
		User user = verificationToken.getUser();
		Calendar calendar = Calendar.getInstance();
		if((verificationToken.getExpiryDate().getTime()-calendar.getTime().getTime())<=0) {
			String message = messages.getMessage("auth.message.expired", null, locale);
			model.addAttribute("message", message);
			return "redirect:access-denied";
		}

		user.setActive(true);
		userService.setActiveUser(user);
		tokenService.removeToken(token);
		return null;
	}
		//check if pass is "faceOrAppleUser"
	@PostMapping(value = "/addNewUserInTeam", consumes = {"multipart/form-data"})
	public String addNewUserInTeam (@RequestParam("imageFile") @PathVariable MultipartFile imageFile,
									UserRequest userRequest, BindingResult result, WebRequest request, Model model){
		User registeredUser = new User();
		if (result.hasErrors()) {
			return "registration";
		}
		Images image = new Images();
		image.setImagename(imageFile.getOriginalFilename());
		if(userService.checkIfUsernameExists(userRequest.getUsername())!=null) {
			model.addAttribute("error","There is already an account with this username: " + userRequest.getUsername());
			logger.info("There is already an account with this username: " + userRequest.getUsername());
//			return "registration";
			System.out.println("cool");
		}
		try {
			Integer id_image = imagesService.saveSpecificImage(imageFile, image);
			userRequest.setId_image(id_image);

			if (userRequest.getPassword().equalsIgnoreCase("faceOrAppleUser")) {
				userService.addNewFaceorAppleUserInTeam(userRequest);
			} else {
				registeredUser = userService.addNewUserInTeam(userRequest);
				String appUrl = request.getContextPath();
				OnRegistrationSuccessEvent event = new OnRegistrationSuccessEvent(registeredUser, request.getLocale(), appUrl);
				eventPublisher.publishEvent(event);
				sendConfirmationMail(event);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "registrationSuccess";
	}
	//check if pass is "faceOrAppleUser"
	@PostMapping(value = "/signUpUser", consumes = {"multipart/form-data"})
	public String saveUser (@RequestParam("imageFile") @PathVariable MultipartFile imageFile,
							UserRequest userRequest, BindingResult result, WebRequest request, Model model){
		User registeredUser = new User();
		if (result.hasErrors()) {
			return "registration";
		}
		Images image = new Images();
		image.setImagename(imageFile.getOriginalFilename());
		if(userService.checkIfUsernameExists(userRequest.getUsername())!=null) {
			model.addAttribute("error","There is already an account with this username: " + userRequest.getUsername());
			logger.info("There is already an account with this username: " + userRequest.getUsername());
//			return "registration";
			System.out.println("cool");
		}
		try {
			Integer id_image = imagesService.saveSpecificImage(imageFile, image);
			userRequest.setId_image(id_image);

			if (userRequest.getPassword().equalsIgnoreCase("faceOrAppleUser")) {
				userService.registerFaceorAppleUserUser(userRequest);
			} else {
				registeredUser = userService.registerUser(userRequest);
				String appUrl = request.getContextPath();
				OnRegistrationSuccessEvent event = new OnRegistrationSuccessEvent(registeredUser, request.getLocale(), appUrl);
				eventPublisher.publishEvent(event);
				sendConfirmationMail(event);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "registrationSuccess";
	}


	public void sendConfirmationMail(OnRegistrationSuccessEvent event) {
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		userService.createVerificationToken(user,token);

		String recipient = user.getUserName();
		String subject = "Team Task Calendar App - Registration Confirmation";
		String url
				= event.getAppUrl() + "/confirmRegistration?token=" + token;
		String message = messages.getMessage("message.registrationSuccessConfimationLink", null, event.getLocale());

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipient);
		email.setSubject(subject);
//		email.setText(message + "\nhttp://192.168.0.15:8080/rest/users" + url);
		email.setText(message + "\nhttps://teamtaskcalendar.herokuapp.com/rest/users" + url);
		mailSender.send(email);

	}


}
