package kr.co.danal.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

import static kr.co.danal.batch.component.DateTimeComponent.DATE_TIME_FORMATTER;

@Getter
@Setter
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    // 번호
    private Long rowNum;

    // 개방서비스명
    private String opnSvcNm;

    // 개방서비스아이디
    private String opnSvcId;

    // 개방자치단체코드
    private String opnSfTeamCode;

    // 관리번호
    private String mgtNo;

    // 인허가일자
    private String apvPermYmd;

    // 인허가취소일자
    private String apvCancelYmd;

    // 영업상태구분코드
    private String trdStateGbn;

    // 영업상태명
    private String trdStateNm;

    // 상세영업상태코드
    private String dtlStateGbn;

    // 상세영업상태명
    private String dtlStateNm;

    // 폐업일자
    private String dcbYmd;

    // 휴업시작일자
    private String clgStdt;

    // 휴업종료일자
    private String clgEnddt;

    // 재개업일자
    private String ropnYmd;

    // 소재지전화
    private String siteTel;

    // 소재지면적
    private String siteArea;

    // 소재지우편번호
    private String sitePostNo;

    // 소재지전체주소
    private String siteWhlAddr;

    // 도로명전체주소
    private String rdnWhlAddr;

    // 도로명우편번호
    private String rdnPostNo;

    // 사업장명
    private String bplcNm;

    // 최종수정시점 - 지자체 정보 업데이트 시점
    private String lastModTs;

    // 데이터갱신구분 - U(Update)/I(Insert)
    private String updateGbn;

    // 데이터갱신일자 - API에 변경점 적용시점
    private String updateDt;

    // 업태구분명
    private String uptaeNm;

    // 좌표정보(x)
    private String posX;

    // 좌표정보(y)
    private String posY;

    // 이하 별도 그룹별 상세항목
    // @Comment("위생업태명")
    private String sntUptaeNm;

    // @Comment("남성종사자수")
    private Integer manEipCnt;

    // @Comment("여성종사자수")
    private Integer wmEipCnt;

    // @Comment("영업장주변구분명")
    private String trdpJubnSeNm;

    // @Comment("등급구분명")
    private String lvSeNm;

    // @Comment("급수시설구분명")
    private String wtrSplyFacilSeNm;

    // @Comment("총직원수")
    private Integer totEpNum;

    // @Comment("본사직원수")
    private Integer hoffEpCnt;

    // @Comment("공장사무직직원수")
    private Integer fctyOwkEpCnt;

    // @Comment("공장판매직직원수")
    private Integer fctySilJobEpCnt;

    // @Comment("공장생산직직원수")
    private Integer fctyPdtJobEpCnt;

    // @Comment("건물소유구분명")
    private String bdngOwnSeNm;

    // @Comment("보증액")
    private Integer isreAm;

    // @Comment("월세액")
    private Integer monAm;

    // @Comment("다중이용업소여부 - Y/N")
    private String multUsnUpsoYn;

    // @Comment("시설총규모")
    private String facilTotScp;

    // @Comment("전통업소지정번호")
    private String jtUpsoAsgnNo;

    // @Comment("전통업소주된음식")
    private String jtUpsoMainEdf;

    // @Comment("홈페이지")
    private String homepage;

    public RestaurantDto(String opnSvcId, String opnSfTeamCode, String mgtNo) {
        this.opnSvcId = opnSvcId;
        this.opnSfTeamCode = opnSfTeamCode;
        this.mgtNo = mgtNo;
    }

    /**
     * 데이터가 업데이트 된 일자를 문자열에서 데이터 타입을 변경하는 기능
     *
     * @return LocalDateTime 업데이트된 데이터 문자열을 LocalDateTime 객체로 변환
     */
    public LocalDateTime getUpdateDtAsLocalDateTime() {
        return LocalDateTime.parse(updateDt, DATE_TIME_FORMATTER);
    }

    /**
     * 시간 관련 데이터 중 API 스펙상 크기와 실제 데이터 크기가 달라 보정하는 기능
     */
    public void removeDateTimeDashColon() {
        apvPermYmd = apvPermYmd != null ? apvPermYmd.replaceAll("-", "") : null;
        apvCancelYmd = apvCancelYmd != null ? apvCancelYmd.replaceAll("-", "") : null;
        dcbYmd = dcbYmd != null ? dcbYmd.replaceAll("-", "") : null;
        clgStdt = clgStdt != null ? clgStdt.replaceAll("-", "") : null;
        clgEnddt = clgEnddt != null ? clgEnddt.replaceAll("-", "") : null;
        ropnYmd = ropnYmd != null ? ropnYmd.replaceAll("-", "") : null;
        lastModTs = lastModTs != null ? lastModTs.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") : null;
    }

    /**
     * 데이터개방서비스코드 목록중 일반음식점 확인 기능
     * 업종별영상내코드 유효값 확인 기능
     *
     * @return boolean true / false
     */
    public boolean isValidCode() {
        // 업종 코드 검사 07_24_04_P - 일반음식점 //opnSvcId opnSvcNm
        if (!(this.opnSvcId.equals("07_24_04_P") && this.opnSvcNm.equals("일반음식점"))) {
            log.warn("업종 코드가 유효하지 않습니다. {} - {}", this.opnSvcNm, this.opnSvcId);
            return false;
        }
        // 영업코드(화면상)	영업상태명(화면상)	상세영업코드	상세영업상태명
        if (this.trdStateGbn.equals("01") && this.trdStateNm.equals("영업/정상")) {
            // 01,  영업/정상, 01 (영업),
            // 01,  영업/정상, BBBB-임시코드 (),
            if (this.dtlStateGbn.equals("01") && this.dtlStateNm.equals("영업")) {
                return true;
            } else if (this.dtlStateGbn.equals("BBBB") && this.dtlStateNm.isEmpty()) {
                return true;
            }
            log.warn("영업 정상 상세 코드가 유효하지 않습니다. {} - {}", this.dtlStateGbn, this.dtlStateNm);
            return false;
        } else if (this.trdStateGbn.equals("03") && this.trdStateNm.equals("폐업")) {
            if (this.dtlStateGbn.equals("02") && this.dtlStateNm.equals("폐업")) {
                return true;
            }
            log.warn("영업 폐업 상세 코드가 유효하지 않습니다. {} - {}", this.dtlStateGbn, this.dtlStateGbn);
            return false;
        }
        log.warn("영업 코드가 유효하지 않습니다. {} - {}", this.trdStateGbn, this.trdStateNm);
        return false;
    }

    /**
     * Restaurant 탐색을 위한 Key 생성
     *
     * @return String key
     */
    public String makeKey() {
        return this.opnSvcId + "_" + opnSfTeamCode + "_" + mgtNo;
    }
}

