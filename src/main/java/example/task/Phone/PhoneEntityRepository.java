package example.task.Phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneEntityRepository
        extends JpaRepository<PhoneEntity, Integer> {

}
