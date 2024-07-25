package kr.co.danal.batch;

import kr.co.danal.batch.dto.RestaurantDto;
import kr.co.danal.batch.entity.Restaurant;
import kr.co.danal.batch.repository.RestaurantRepository;
import kr.co.danal.batch.service.JdbcService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
@ActiveProfiles(value = "test")
class DatabaseTests {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcService jdbcService;


    @BeforeEach
    void setBeforeTest() throws NoSuchJobException {
        log.info("테스트 전 설정 시작");
        cleanDatabaseBeforeTest();
    }


    void cleanDatabaseBeforeTest() {
        log.info("테스트 전 데이터 정리");
        String opnSvcId = "07_24_04_P";
        restaurantRepository.deleteAllByOpnSvcId(opnSvcId);
        restaurantRepository.flush();
    }

    // Data 데이터 Select 가지고 와서 비교하기
    @Test
    @Transactional
    void insertDatabaseWithJdbc() throws Exception {
        String insertSql = "insert into tbl_restaurant (opn_svc_id, opn_sf_team_code, mgt_no) values ('코드', '관리번호', '아이디')";
        jdbcTemplate.execute(insertSql);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findByOpnSvcIdAndOpnSfTeamCodeAndMgtNo("코드", "관리번호", "아이디");
        Restaurant restaurant = optionalRestaurant.orElse(null);
        assertNotNull(restaurant);
        assertEquals(restaurant.getOpnSvcId(), "코드");
        assertEquals(restaurant.getOpnSfTeamCode(), "관리번호");
        assertEquals(restaurant.getMgtNo(), "아이디");
    }

    @Test
    @Transactional
    void insertDatabaseWithNameParameter() throws Exception {
        String insertSql = "insert into tbl_restaurant (opn_sf_team_code, mgt_no, opn_svc_id) values (:opnSfTeamCode,:mgtNo,:opnSvcId)";
        Restaurant restaurant = new Restaurant();
        restaurant.setOpnSfTeamCode("코드1");
        restaurant.setMgtNo("관리번호1");
        restaurant.setOpnSvcId("아이디1");
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(restaurant);
        KeyHolder jdbcKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSql, parameterSource, jdbcKeyHolder);

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findByOpnSvcIdAndOpnSfTeamCodeAndMgtNo("아이디1", "코드1", "관리번호1");
        Restaurant databaseRestaurant = optionalRestaurant.orElse(null);
        assertNotNull(databaseRestaurant);
        assertEquals(databaseRestaurant.getOpnSfTeamCode(), "코드1");
        assertEquals(databaseRestaurant.getMgtNo(), "관리번호1");
        assertEquals(databaseRestaurant.getOpnSvcId(), "아이디1");
    }

    @Test
    @Transactional
    void batchInsertDatabaseWithNameParameter() {

        String insertSql = "insert into tbl_restaurant (opn_sf_team_code, mgt_no, opn_svc_id) values (:opnSfTeamCode,:mgtNo,:opnSvcId)";
        List<Restaurant> restaurantList = new ArrayList<>();
        Restaurant restaurant = new Restaurant();
        restaurant.setOpnSfTeamCode("코드2");
        restaurant.setMgtNo("관리번호2");
        restaurant.setOpnSvcId("아이디2");
        restaurantList.add(restaurant);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setOpnSfTeamCode("코드3");
        restaurant2.setMgtNo("관리번호3");
        restaurant2.setOpnSvcId("아이디3");
        restaurantList.add(restaurant2);

        int[] sCount = namedParameterJdbcTemplate.batchUpdate(insertSql, SqlParameterSourceUtils.createBatch(restaurantList));

        assertEquals(sCount.length, 2);
    }

    @Test
    @Transactional
    void batchInsertDatabaseWitJdbcTemplate() {

        String insertSql = "insert into tbl_restaurant (opn_sf_team_code, mgt_no, opn_svc_id) values (?,?,?)";
        List<Restaurant> restaurantList = new ArrayList<>();
        Restaurant restaurant = new Restaurant();
        restaurant.setOpnSfTeamCode("코드4");
        restaurant.setMgtNo("관리번호4");
        restaurant.setOpnSvcId("아이디4");
        restaurantList.add(restaurant);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setOpnSfTeamCode("코드5");
        restaurant2.setMgtNo("관리번호5");
        restaurant2.setOpnSvcId("아이디5");
        restaurantList.add(restaurant2);

        int[] sCount = jdbcTemplate.batchUpdate(
                insertSql,
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Restaurant innerItem = restaurantList.get(i);
                        ps.setString(1, innerItem.getOpnSfTeamCode());
                        ps.setString(2, innerItem.getMgtNo());
                        ps.setString(3, innerItem.getOpnSvcId());
                    }

                    public int getBatchSize() {
                        return restaurantList.size();
                    }
                }
        );

        assertEquals(sCount.length, 2);
    }

    @Test
    @Transactional
    void batchUpdateDatabaseWithNameParameter() {

        String insertSql = "insert into tbl_restaurant (opn_sf_team_code, mgt_no, opn_svc_id) values (:opnSfTeamCode,:mgtNo,:opnSvcId)";
        List<Restaurant> restaurantList = new ArrayList<>();
        Restaurant restaurant = new Restaurant();
        restaurant.setOpnSfTeamCode("코드6");
        restaurant.setMgtNo("관리번호6");
        restaurant.setOpnSvcId("아이디6");
        restaurant.setUpdateDt(LocalDateTime.now());
        restaurantList.add(restaurant);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setOpnSfTeamCode("코드7");
        restaurant2.setMgtNo("관리번호7");
        restaurant2.setOpnSvcId("아이디7");
        restaurant2.setUpdateDt(LocalDateTime.now());
        restaurantList.add(restaurant2);

        int[] insertCount = namedParameterJdbcTemplate.batchUpdate(insertSql, SqlParameterSourceUtils.createBatch(restaurantList));
        assertEquals(insertCount.length, 2);
        String updateSql = "update tbl_restaurant set update_dt = :updateDt where opn_sf_team_code = :opnSfTeamCode and mgt_no = :mgtNo and opn_svc_id = :opnSvcId";

        int[] updateCount = namedParameterJdbcTemplate.batchUpdate(updateSql, SqlParameterSourceUtils.createBatch(restaurantList));
        assertEquals(updateCount.length, 2);
    }

    @Test
    @Transactional
    void batchUpdateDatabaseWithJdbcTemplate() {

        String insertSql = "insert into tbl_restaurant (opn_sf_team_code, mgt_no, opn_svc_id) values (?,?,?)";
        List<Restaurant> restaurantList = new ArrayList<>();
        Restaurant restaurant = new Restaurant();
        restaurant.setOpnSfTeamCode("코드8");
        restaurant.setMgtNo("관리번호8");
        restaurant.setOpnSvcId("아이디8");
        restaurant.setUpdateDt(LocalDateTime.now());
        restaurantList.add(restaurant);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setOpnSfTeamCode("코드9");
        restaurant2.setMgtNo("관리번호9");
        restaurant2.setOpnSvcId("아이디9");
        restaurant2.setUpdateDt(LocalDateTime.now());
        restaurantList.add(restaurant2);

        int[] insertCount = jdbcTemplate.batchUpdate(
                insertSql,
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Restaurant innerItem = restaurantList.get(i);
                        ps.setString(1, innerItem.getOpnSfTeamCode());
                        ps.setString(2, innerItem.getMgtNo());
                        ps.setString(3, innerItem.getOpnSvcId());
                    }

                    public int getBatchSize() {
                        return restaurantList.size();
                    }
                }
        );
        assertEquals(insertCount.length, 2);

        String updateSql = "update tbl_restaurant set update_dt = ? where opn_sf_team_code = ? and mgt_no = ? and opn_svc_id = ?";

        int[] updateCount = jdbcTemplate.batchUpdate(
                updateSql,
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Restaurant innerItem = restaurantList.get(i);
                        ps.setObject(1, innerItem.getUpdateDt());
                        ps.setString(2, innerItem.getOpnSfTeamCode());
                        ps.setString(3, innerItem.getMgtNo());
                        ps.setString(4, innerItem.getOpnSvcId());
                    }

                    public int getBatchSize() {
                        return restaurantList.size();
                    }
                }
        );
        assertEquals(updateCount.length, 2);
    }

    @Test
    @Transactional
    @DisplayName("")
    void fullDataColumnJdbcTest() {
        LocalDateTime updateDt = LocalDateTime.now().withNano(0);
        String opnSvcId = "07_24_04_P";
        String opnSfTeamCode = "3600000";
        String mgtNo = "3600000-101-2014-00060";
        Restaurant restaurant = Restaurant.builder()
                .rowNum(2152083L)
                .opnSvcId(opnSvcId)
                .opnSfTeamCode(opnSfTeamCode)
                .mgtNo(mgtNo)
                .apvPermYmd("20140321")
                .apvCancelYmd("20140321")
                .trdStateGbn("01")
                .dtlStateGbn("01")
                .dcbYmd("20140321")
                .clgStdt("20140321")
                .clgEnddt("20140321")
                .ropnYmd("20140321")
                .siteTel("02 369 1582")
                .siteArea("36.36")
                .sitePostNo("151-867")
                .siteWhlAddr("서울특별시 관악구 신림동 412-234")
                .rdnWhlAddr("서울특별시 관악구 문성로38길 70-4, 1층 (신림동)")
                .rdnPostNo("08843")
                .bplcNm("반달마라")
                .lastModTs("20240326131146")
                .updateGbn("U")
                .updateDt(updateDt)
                .uptaeNm("한식")
                .posX("193751.553611291")
                .posY("441458.053550087")
                .sntUptaeNm("한식")
                .manEipCnt(0)
                .wmEipCnt(0)
                .trdpJubnSeNm("0")
                .lvSeNm("0")
                .wtrSplyFacilSeNm("상수도전용")
                .totEpNum(0)
                .hoffEpCnt(0)
                .fctyOwkEpCnt(0)
                .fctySilJobEpCnt(0)
                .fctyPdtJobEpCnt(0)
                .bdngOwnSeNm("0")
                .isreAm(0)
                .monAm(0)
                .multUsnUpsoYn("N")
                .facilTotScp("0")
                .jtUpsoAsgnNo("0")
                .jtUpsoMainEdf("0")
                .homepage("http://localhost:8080")
                .build();
        List<Restaurant> newList = List.of(restaurant);
        jdbcService.batchNewInsertRestaurant(newList);

        List<RestaurantDto> restaurantDtoList = List.of(new RestaurantDto(opnSvcId, opnSfTeamCode, mgtNo));
        List<Restaurant> restaurantList = jdbcService.getRestaurantList(restaurantDtoList);

        Restaurant getRestaurant = restaurantList.get(0);
        boolean isSame = getRestaurant.isSameDataByValue(restaurant);
        assertTrue(isSame);
    }


}
