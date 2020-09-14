package TeamTask.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="images")
public class Images implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="images_sequence")
	private Integer id_image;
	@Column(name = "imagelocation")
	private String imageLocation;
	private String imagename;

	public Images(String imageLocation, String imagename) {
		this.imageLocation = imageLocation;
		this.imagename = imagename;
	}

//	public Images(Integer id_image, String imageLocation, String imagename) {
//		this.id_image = id_image;
//		this.imageLocation = imageLocation;
//		this.imagename = imagename;
//	}

	public Images() {
	}

	public Integer getId_image() {
		return id_image;
	}

	public void setId_image(Integer id_image) {
		this.id_image = id_image;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

}
