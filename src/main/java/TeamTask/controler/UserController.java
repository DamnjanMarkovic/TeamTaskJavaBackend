package TeamTask.controler;

import TeamTask.models.OnRegistrationCompleteEvent;
import TeamTask.models.User;
import TeamTask.models.VerificationToken;
import TeamTask.models.dto.LoginResponse;
import TeamTask.models.dto.UsersInTeamResponse;
import TeamTask.service.TaskService;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import TeamTask.models.Images;
import TeamTask.models.dto.UserRequest;
import TeamTask.models.dto.UserResponse;
import TeamTask.service.ImagesService;
import TeamTask.service.UserService;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.net.Authenticator;
import java.sql.SQLException;
import java.util.*;
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

	public UserController(UserService userService, TaskService taskService) {
		this.userService = userService;
		this.taskService = taskService;
	}

	@GetMapping(value = "/all")
	public List<UserResponse> getAll() {
		return userService.getAll();
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
//	@DeleteMapping("/deleteUserID/{id_user}")
//	public void deleteUserID (@PathVariable UUID id_user) throws Exception {
//		userService.confirmdeleteUser(id_user);
//	}

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
			User user = userService.saveRegisteredUser(userRequest);
				result = response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@GetMapping("/confirmRegistration")
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {
		Locale locale=request.getLocale();
		VerificationToken verificationToken = userService.getVerificationToken(token);
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
		return null;
	}

	//ovde treba proveriti  da li je pass "faceOrAppleUser" - ako jeste, to je dodavanje korisnika fejsbuk ili apple
	@PostMapping(value = "/signUpUser", consumes = {"multipart/form-data"})
	public String saveUser (@RequestParam("imageFile") @PathVariable MultipartFile imageFile,
							HttpServletRequest request, UserRequest userRequest){
		String result = null;
		String response = null;
		System.out.println("novi user registrovan");
		Images image = new Images();
		image.setImagename(imageFile.getOriginalFilename());

		try {

			Integer id_image = imagesService.saveSpecificImage(imageFile, image);
			userRequest.setId_image(id_image);
			User user = userService.registerNewUserAccount(userRequest);
			String appUrl = request.getContextPath();
			sendConfirmationMail();
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user,
					request.getLocale(), appUrl));
			result = response;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void sendConfirmationMail() throws MessagingException {
//		Authenticator auth = new MailAuthenticator();
//		Session session = Session.getInstance(properties, auth);
//		Message msg = new MimeMessage(session);
//		msg.setSubject(subject);
//		msg.setSentDate(new Date());
//		msg.setFrom(new InternetAddress(from, false));
//		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rcpt, false));
//		msg.setContent(msgContent, "text/html");
//		Transport.send(msg);
	}

	//ovde treba proveriti  da li je pass "faceOrAppleUser" - ako jeste, to je dodavanje korisnika fejsbuk ili apple
	@PostMapping(value = "/addNewUserInTeam", consumes = {"multipart/form-data"})
	public String addNewUserInTeam (@RequestParam("imageFile") @PathVariable MultipartFile imageFile,
									HttpServletRequest request, UserRequest userRequest){
		String result = null;
		String response = null;
		System.out.println("nesto");
		Images image = new Images();
		image.setImagename(imageFile.getOriginalFilename());

		try {
			Integer id_image = imagesService.saveSpecificImage(imageFile, image);
			userRequest.setId_image(id_image);
			User user = userService.addNewUserInTeam(userRequest);
			String appUrl = request.getContextPath();
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user,
					request.getLocale(), appUrl));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}





}
