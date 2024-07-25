package kr.co.danal.batch.repository;

import kr.co.danal.batch.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(value = "SELECT COUNT(*) FROM Restaurant r")
    Integer countAll();

    Optional<Restaurant> findByOpnSvcIdAndOpnSfTeamCodeAndMgtNo(String opnSvcId, String opnSfTeamCode, String mgtNo);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tbl_restaurant r WHERE r .opn_svc_id = :opnSvcId", nativeQuery = true)
    void deleteAllByOpnSvcId(@Param("opnSvcId") String opnSvcId);
}
