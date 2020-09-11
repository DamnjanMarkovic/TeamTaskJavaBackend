package TeamTask.repository;


import TeamTask.models.Teams;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Teams, UUID> {




    @Modifying
    @Query(value = "INSERT INTO dinning_table(table_number, id_restaurant, capacity) VALUES (?,?,?)",
            nativeQuery = true)
    void insertDinningTables(int i, Integer id_restaurant, int i1);

    @Modifying
    @Query(value = "DELETE FROM available_ingredients WHERE id_restaurant = ?",
            nativeQuery = true)
    void deleteAvailableIngredientsInRestaurant(int id_restaurant);



}
