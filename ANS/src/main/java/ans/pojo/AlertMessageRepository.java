package ans.pojo;

import ans.pojo.AlertMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface AlertMessageRepository extends CrudRepository<AlertMessage, Long> {

    @Query("SELECT a FROM AlertMessage a WHERE a.date >= :startDate AND a.date <= :endDate")
    List<AlertMessage> findMsgByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
