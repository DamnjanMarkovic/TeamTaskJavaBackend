package TeamTask.models;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "teams")
public class Teams {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private UUID id_team;
        private String name_team;
        private Integer id_image;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_team")
//
//    private Set<Available_ingredients> available_ingredients;
//
//    public Restaurant(Integer id_restaurant) {
//        this.id_restaurant = id_restaurant;
//    }

    public Teams() {
    }

    public Teams(UUID id_team, String name_team, Integer id_image) {
        this.id_team = id_team;
        this.name_team = name_team;
        this.id_image = id_image;
    }

    public UUID getId_team() {
        return id_team;
    }

    public void setId_team(UUID id_team) {
        this.id_team = id_team;
    }

    public String getName_team() {
        return name_team;
    }

    public void setName_team(String name_team) {
        this.name_team = name_team;
    }

    public Integer getId_image() {
        return id_image;
    }

    public void setId_image(Integer id_image) {
        this.id_image = id_image;
    }
}
