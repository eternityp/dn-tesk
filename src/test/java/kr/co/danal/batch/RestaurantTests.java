package kr.co.danal.batch;

import kr.co.danal.batch.dto.RestaurantDto;
import kr.co.danal.batch.entity.Restaurant;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;


import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class RestaurantTests {

    static RestaurantDto restaurantDto;

    @BeforeAll
    static void setDto() {
        String opnSvcId = "07_24_04_P";
        String opnSvcNm = "일반음식점";
        String trdStateGbn = "01";
        String trdStateNm = "영업/정상";
        String dtlStateGbn = "01";
        String dtlStateNm = "영업";
        String apvPermYmd = "2024-07-24";
        String apvCancelYmd = "2024-07-24";
        String dcbYmd = "2024-07-24";
        String clgStdt = "2024-07-24";
        String clgEnddt = "2024-07-24";
        String ropnYmd = "2024-07-24";
        String lastModTs = "2024-07-24 23:01:02";
        restaurantDto = new RestaurantDto();
        restaurantDto.setOpnSvcId(opnSvcId);
        restaurantDto.setOpnSvcNm(opnSvcNm);
        restaurantDto.setTrdStateGbn(trdStateGbn);
        restaurantDto.setTrdStateNm(trdStateNm);
        restaurantDto.setDtlStateGbn(dtlStateGbn);
        restaurantDto.setDtlStateNm(dtlStateNm);
        restaurantDto.setApvPermYmd(apvPermYmd);
        restaurantDto.setApvCancelYmd(apvCancelYmd);
        restaurantDto.setDcbYmd(dcbYmd);
        restaurantDto.setClgStdt(clgStdt);
        restaurantDto.setClgEnddt(clgEnddt);
        restaurantDto.setRopnYmd(ropnYmd);
        restaurantDto.setLastModTs(lastModTs);

    }

    @Test
    @DisplayName("업종, 영업 코드 유효값 확인 테스트")
    void isValidCode() {
        RestaurantDto successDto = new RestaurantDto();
        BeanUtils.copyProperties(restaurantDto, successDto);
        assertTrue(successDto.isValidCode());
    }

    @Test
    @DisplayName("업종, 영업 코드 유효값 확인 테스트")
    void isInValidCode() {
        RestaurantDto failSvcIdDto = new RestaurantDto();
        BeanUtils.copyProperties(restaurantDto, failSvcIdDto);
        failSvcIdDto.setOpnSvcId("74_24_05_p");
        assertFalse(failSvcIdDto.isValidCode());

        RestaurantDto failSvcNameDto = new RestaurantDto();
        BeanUtils.copyProperties(restaurantDto, failSvcNameDto);
        failSvcNameDto.setOpnSvcNm("일반음식점2");
        assertFalse(failSvcNameDto.isValidCode());

        RestaurantDto failTrdStateGbnDto = new RestaurantDto();
        BeanUtils.copyProperties(restaurantDto, failTrdStateGbnDto);
        failTrdStateGbnDto.setTrdStateGbn("03");
        assertFalse(failTrdStateGbnDto.isValidCode());

        RestaurantDto failTrdStateNmDto = new RestaurantDto();
        BeanUtils.copyProperties(restaurantDto, failTrdStateNmDto);
        failTrdStateNmDto.setTrdStateNm("비정상");
        assertFalse(failTrdStateNmDto.isValidCode());

        RestaurantDto failDtlStateGbnDto = new RestaurantDto();
        BeanUtils.copyProperties(restaurantDto, failDtlStateGbnDto);
        failDtlStateGbnDto.setDtlStateGbn("06");
        assertFalse(failDtlStateGbnDto.isValidCode());

        RestaurantDto failDtlStateNmDto = new RestaurantDto();
        BeanUtils.copyProperties(restaurantDto, failDtlStateNmDto);
        failDtlStateNmDto.setTrdStateGbn("03");
        failDtlStateNmDto.setTrdStateNm("페업");
        failDtlStateNmDto.setDtlStateGbn("02");
        failDtlStateNmDto.setTrdStateNm("BBBB");
        assertFalse(failDtlStateNmDto.isValidCode());
    }


    @Test
    @DisplayName("시간 관련 포맷 변경 확인 테스트")
    void removeDateTimeFormatTest() {

        RestaurantDto dateTestDto = new RestaurantDto();
        BeanUtils.copyProperties(restaurantDto, dateTestDto);
        dateTestDto.removeDateTimeDashColon();

        assertEquals(dateTestDto.getApvPermYmd(), "20240724");
        assertEquals(dateTestDto.getApvCancelYmd(), "20240724");
        assertEquals(dateTestDto.getApvPermYmd(), "20240724");
        assertEquals(dateTestDto.getDcbYmd(), "20240724");
        assertEquals(dateTestDto.getApvPermYmd(), "20240724");
        assertEquals(dateTestDto.getClgStdt(), "20240724");
        assertEquals(dateTestDto.getClgEnddt(), "20240724");
        assertEquals(dateTestDto.getLastModTs(), "20240724230102");
    }


    @Test
    @DisplayName("비교를 위한 키 생성 테스트")
    void makeKeyTest() {
        String opnSvcId = "07_24_04_P";
        String opnSfTeamCode = "3600000";
        String mgtNo = "3600000-101-2014-00060";
        String dKey = opnSvcId + "_" + opnSfTeamCode + "_" + mgtNo;

        Restaurant restaurant = new Restaurant();
        restaurant.setOpnSvcId(opnSvcId);
        restaurant.setOpnSfTeamCode(opnSfTeamCode);
        restaurant.setMgtNo(mgtNo);
        String makeKey = restaurant.makeKey();

        assertEquals(dKey, makeKey);
    }


}
