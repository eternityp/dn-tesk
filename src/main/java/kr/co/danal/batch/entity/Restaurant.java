package kr.co.danal.batch.entity;

import jakarta.persistence.*;
import kr.co.danal.batch.dto.RestaurantDto;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tbl_restaurant",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_restaurant_key",
                        columnNames = {"opnSvcId", "opnSfTeamCode", "mgtNo"}
                )
        })
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    // 공통항목
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("번호")
    @Column(name = "rowNum")
    private Long rowNum;

    @Comment("개방자치단체코드")
    @Column(name = "opnSfTeamCode", columnDefinition = "CHAR(7)")
    private String opnSfTeamCode;

    @Comment("관리번호")
    @Column(name = "mgtNo", columnDefinition = "VARCHAR(40)")
    private String mgtNo;

    @Comment("개방서비스아이디")
    @Column(name = "opnSvcId", columnDefinition = "CHAR(10)")
    private String opnSvcId;

    @Comment("데이터갱신구분 - U(Update)/I(Insert)")
    @Column(name = "updateGbn", columnDefinition = "CHAR(1)")
    private String updateGbn;

    @Comment("데이터갱신일자 - API에 변경점 적용시점")
    @Column(name = "updateDt", columnDefinition = "DATETIME")
    private LocalDateTime updateDt;

//    @Comment("개방서비스명")
//    @Column(name = "opnSvcNm", columnDefinition = "VARCHAR(200)")
//    private String opnSvcNm;

    @Comment("사업장명")
    @Column(name = "bplcNm", columnDefinition = "VARCHAR(200)")
    private String bplcNm;

    @Comment("소재지우편번호")
    @Column(name = "sitePostNo", columnDefinition = "VARCHAR(7)")
    private String sitePostNo;

    @Comment("소재지전체주소")
    @Column(name = "siteWhlAddr", columnDefinition = "VARCHAR(500)")
    private String siteWhlAddr;

    @Comment("도로명우편번호")
    @Column(name = "rdnPostNo", columnDefinition = "VARCHAR(7)")
    private String rdnPostNo;

    @Comment("도로명전체주소")
    @Column(name = "rdnWhlAddr", columnDefinition = "VARCHAR(200)")
    private String rdnWhlAddr;

    @Comment("소재지면적")
    @Column(name = "siteArea", columnDefinition = "VARCHAR(18)")
    private String siteArea;

    @Comment("인허가일자")
    @Column(name = "apvPermYmd", columnDefinition = "VARCHAR(8)")
    private String apvPermYmd;

    @Comment("인허가취소일자")
    @Column(name = "apvCancelYmd", columnDefinition = "VARCHAR(8)")
    private String apvCancelYmd;

    @Comment("폐업일자")
    @Column(name = "dcbYmd", columnDefinition = "VARCHAR(8)")
    private String dcbYmd;

    @Comment("휴업시작일자")
    @Column(name = "clgStdt", columnDefinition = "VARCHAR(8)")
    private String clgStdt;

    @Comment("휴업종료일자")
    @Column(name = "clgEnddt", columnDefinition = "VARCHAR(8)")
    private String clgEnddt;

    @Comment("재개업일자")
    @Column(name = "ropnYmd", columnDefinition = "VARCHAR(8)")
    private String ropnYmd;

    @Comment("영업상태구분코드")
    @Column(name = "trdStateGbn", columnDefinition = "VARCHAR(2)")
    private String trdStateGbn;

//    @Comment("영업상태명")
//    @Column(name = "trdStateNm", columnDefinition = "VARCHAR(100)")
//    private String trdStateNm;

    @Comment("상세영업상태코드")
    @Column(name = "dtlStateGbn", columnDefinition = "VARCHAR(4)")
    private String dtlStateGbn;

//    @Comment("상세영업상태명")
//    @Column(name = "dtlStateNm", columnDefinition = "VARCHAR(200)")
//    private String dtlStateNm;

    @Comment("좌표정보(x)")
    @Column(name = "pos_x", columnDefinition = "CHAR(20)")
    private String posX;

    @Comment("좌표정보(y)")
    @Column(name = "pos_y", columnDefinition = "CHAR(20)")
    private String posY;

    @Comment("최종수정시점")
    @Column(name = "lastModTs", columnDefinition = "VARCHAR(14)")
    private String lastModTs;

    @Comment("업태구분명")
    @Column(name = "uptaeNm", columnDefinition = "VARCHAR(100)")
    private String uptaeNm;

    @Comment("소재지전화")
    @Column(name = "siteTel", columnDefinition = "VARCHAR(100)")
    private String siteTel;

    // 이하 별도 그룹별 상세항목
    @Comment("위생업태명")
    @Column(name = "sntUptaeNm", columnDefinition = "VARCHAR(200)")
    private String sntUptaeNm;

    @Comment("남성종사자수")
    @Column(name = "manEipCnt", columnDefinition = "INT(22)")
    private Integer manEipCnt;

    @Comment("여성종사자수")
    @Column(name = "wmEipCnt", columnDefinition = "INT(22)")
    private Integer wmEipCnt;

    @Comment("영업장주변구분명")
    @Column(name = "trdpJubnSeNm", columnDefinition = "VARCHAR(100)")
    private String trdpJubnSeNm;

    @Comment("등급구분명")
    @Column(name = "lvSeNm", columnDefinition = "VARCHAR(200)")
    private String lvSeNm;

    @Comment("급수시설구분명")
    @Column(name = "wtrSplyFacilSeNm", columnDefinition = "VARCHAR(200)")
    private String wtrSplyFacilSeNm;

    @Comment("총직원수")
    @Column(name = "totEpNum", columnDefinition = "INT(22)")
    private Integer totEpNum;

    @Comment("본사직원수")
    @Column(name = "hoffEpCnt", columnDefinition = "INT(22)")
    private Integer hoffEpCnt;

    @Comment("공장사무직직원수")
    @Column(name = "fctyOwkEpCnt", columnDefinition = "INT(22)")
    private Integer fctyOwkEpCnt;

    @Comment("공장판매직직원수")
    @Column(name = "fctySilJobEpCnt", columnDefinition = "INT(22)")
    private Integer fctySilJobEpCnt;

    @Comment("공장생산직직원수")
    @Column(name = "fctyPdtJobEpCnt", columnDefinition = "INT(22)")
    private Integer fctyPdtJobEpCnt;

    @Comment("건물소유구분명")
    @Column(name = "bdngOwnSeNm", columnDefinition = "VARCHAR(200)")
    private String bdngOwnSeNm;

    @Comment("보증액")
    @Column(name = "isreAm", columnDefinition = "INT(22)")
    private Integer isreAm;

    @Comment("월세액")
    @Column(name = "monAm", columnDefinition = "INT(22)")
    private Integer monAm;

    @Comment("다중이용업소여부 - Y/N")
    @Column(name = "multUsnUpsoYn", columnDefinition = "VARCHAR(1)")
    private String multUsnUpsoYn;

    @Comment("시설총규모")
    @Column(name = "facilTotScp", columnDefinition = "VARCHAR(4000)")
    private String facilTotScp;

    @Comment("전통업소지정번호")
    @Column(name = "jtUpsoAsgnNo", columnDefinition = "VARCHAR(20)")
    private String jtUpsoAsgnNo;

    @Comment("전통업소주된음식")
    @Column(name = "jtUpsoMainEdf", columnDefinition = "VARCHAR(20)")
    private String jtUpsoMainEdf;

    @Comment("홈페이지")
    @Column(name = "homepage", columnDefinition = "VARCHAR(500)")
    private String homepage;

    @Builder
    public Restaurant(Long rowNum, String opnSfTeamCode, String mgtNo, String opnSvcId, String updateGbn, LocalDateTime updateDt, String bplcNm, String sitePostNo, String siteWhlAddr, String rdnPostNo, String rdnWhlAddr, String siteArea, String apvPermYmd, String apvCancelYmd, String dcbYmd, String clgStdt, String clgEnddt, String ropnYmd, String trdStateGbn, String dtlStateGbn, String posX, String posY, String lastModTs, String uptaeNm, String siteTel, String sntUptaeNm, Integer manEipCnt, Integer wmEipCnt, String trdpJubnSeNm, String lvSeNm, String wtrSplyFacilSeNm, Integer totEpNum, Integer hoffEpCnt, Integer fctyOwkEpCnt, Integer fctySilJobEpCnt, Integer fctyPdtJobEpCnt, String bdngOwnSeNm, Integer isreAm, Integer monAm, String multUsnUpsoYn, String facilTotScp, String jtUpsoAsgnNo, String jtUpsoMainEdf, String homepage) {
        this.rowNum = rowNum;
        this.opnSfTeamCode = opnSfTeamCode;
        this.mgtNo = mgtNo;
        this.opnSvcId = opnSvcId;
        this.updateGbn = updateGbn;
        this.updateDt = updateDt;
        this.bplcNm = bplcNm;
        this.sitePostNo = sitePostNo;
        this.siteWhlAddr = siteWhlAddr;
        this.rdnPostNo = rdnPostNo;
        this.rdnWhlAddr = rdnWhlAddr;
        this.siteArea = siteArea;
        this.apvPermYmd = apvPermYmd;
        this.apvCancelYmd = apvCancelYmd;
        this.dcbYmd = dcbYmd;
        this.clgStdt = clgStdt;
        this.clgEnddt = clgEnddt;
        this.ropnYmd = ropnYmd;
        this.trdStateGbn = trdStateGbn;
        this.dtlStateGbn = dtlStateGbn;
        this.posX = posX;
        this.posY = posY;
        this.lastModTs = lastModTs;
        this.uptaeNm = uptaeNm;
        this.siteTel = siteTel;
        this.sntUptaeNm = sntUptaeNm;
        this.manEipCnt = manEipCnt;
        this.wmEipCnt = wmEipCnt;
        this.trdpJubnSeNm = trdpJubnSeNm;
        this.lvSeNm = lvSeNm;
        this.wtrSplyFacilSeNm = wtrSplyFacilSeNm;
        this.totEpNum = totEpNum;
        this.hoffEpCnt = hoffEpCnt;
        this.fctyOwkEpCnt = fctyOwkEpCnt;
        this.fctySilJobEpCnt = fctySilJobEpCnt;
        this.fctyPdtJobEpCnt = fctyPdtJobEpCnt;
        this.bdngOwnSeNm = bdngOwnSeNm;
        this.isreAm = isreAm;
        this.monAm = monAm;
        this.multUsnUpsoYn = multUsnUpsoYn;
        this.facilTotScp = facilTotScp;
        this.jtUpsoAsgnNo = jtUpsoAsgnNo;
        this.jtUpsoMainEdf = jtUpsoMainEdf;
        this.homepage = homepage;
    }

    @Builder
    public Restaurant(RestaurantDto restaurantDto) {
        this.rowNum = restaurantDto.getRowNum();
        this.opnSfTeamCode = restaurantDto.getOpnSfTeamCode();
        this.mgtNo = restaurantDto.getMgtNo();
        this.opnSvcId = restaurantDto.getOpnSvcId();
        this.updateGbn = restaurantDto.getUpdateGbn();
        this.updateDt = restaurantDto.getUpdateDtAsLocalDateTime();
        this.bplcNm = restaurantDto.getBplcNm();
        this.sitePostNo = restaurantDto.getSitePostNo();
        this.siteWhlAddr = restaurantDto.getSiteWhlAddr();
        this.rdnPostNo = restaurantDto.getRdnPostNo();
        this.rdnWhlAddr = restaurantDto.getRdnWhlAddr();
        this.siteArea = restaurantDto.getSiteArea();
        this.apvPermYmd = restaurantDto.getApvPermYmd();
        this.apvCancelYmd = restaurantDto.getApvCancelYmd();
        this.dcbYmd = restaurantDto.getDcbYmd();
        this.clgStdt = restaurantDto.getClgStdt();
        this.clgEnddt = restaurantDto.getClgEnddt();
        this.ropnYmd = restaurantDto.getRopnYmd();
        this.trdStateGbn = restaurantDto.getTrdStateGbn();
        this.dtlStateGbn = restaurantDto.getDtlStateGbn();
        this.posX = restaurantDto.getPosX();
        this.posY = restaurantDto.getPosY();
        this.lastModTs = restaurantDto.getLastModTs();
        this.uptaeNm = restaurantDto.getUptaeNm();
        this.siteTel = restaurantDto.getSiteTel();
        this.sntUptaeNm = restaurantDto.getSntUptaeNm();
        this.manEipCnt = restaurantDto.getManEipCnt();
        this.wmEipCnt = restaurantDto.getWmEipCnt();
        this.trdpJubnSeNm = restaurantDto.getTrdpJubnSeNm();
        this.lvSeNm = restaurantDto.getLvSeNm();
        this.wtrSplyFacilSeNm = restaurantDto.getWtrSplyFacilSeNm();
        this.totEpNum = restaurantDto.getTotEpNum();
        this.hoffEpCnt = restaurantDto.getHoffEpCnt();
        this.fctyOwkEpCnt = restaurantDto.getFctyOwkEpCnt();
        this.fctySilJobEpCnt = restaurantDto.getFctySilJobEpCnt();
        this.fctyPdtJobEpCnt = restaurantDto.getFctyPdtJobEpCnt();
        this.bdngOwnSeNm = restaurantDto.getBdngOwnSeNm();
        this.isreAm = restaurantDto.getIsreAm();
        this.monAm = restaurantDto.getMonAm();
        this.multUsnUpsoYn = restaurantDto.getMultUsnUpsoYn();
        this.facilTotScp = restaurantDto.getFacilTotScp();
        this.jtUpsoAsgnNo = restaurantDto.getJtUpsoAsgnNo();
        this.jtUpsoMainEdf = restaurantDto.getJtUpsoMainEdf();
        this.homepage = restaurantDto.getHomepage();
    }

    /**
     * 변경점이 있을경우 데이터로 새로 교체
     *
     * @param restaurantDto CSV에서 읽은 새 Restaurant 데이터
     */
    public void putNewData(RestaurantDto restaurantDto) {
        this.rowNum = restaurantDto.getRowNum();
        this.opnSfTeamCode = restaurantDto.getOpnSfTeamCode();
        this.mgtNo = restaurantDto.getMgtNo();
        this.opnSvcId = restaurantDto.getOpnSvcId();
        this.updateGbn = restaurantDto.getUpdateGbn();
        this.updateDt = restaurantDto.getUpdateDtAsLocalDateTime();
        this.bplcNm = restaurantDto.getBplcNm();
        this.sitePostNo = restaurantDto.getSitePostNo();
        this.siteWhlAddr = restaurantDto.getSiteWhlAddr();
        this.rdnPostNo = restaurantDto.getRdnPostNo();
        this.rdnWhlAddr = restaurantDto.getRdnWhlAddr();
        this.siteArea = restaurantDto.getSiteArea();
        this.apvPermYmd = restaurantDto.getApvPermYmd();
        this.apvCancelYmd = restaurantDto.getApvCancelYmd();
        this.dcbYmd = restaurantDto.getDcbYmd();
        this.clgStdt = restaurantDto.getClgStdt();
        this.clgEnddt = restaurantDto.getClgEnddt();
        this.ropnYmd = restaurantDto.getRopnYmd();
        this.trdStateGbn = restaurantDto.getTrdStateGbn();
        this.dtlStateGbn = restaurantDto.getDtlStateGbn();
        this.posX = restaurantDto.getPosX();
        this.posY = restaurantDto.getPosY();
        this.lastModTs = restaurantDto.getLastModTs();
        this.uptaeNm = restaurantDto.getUptaeNm();
        this.siteTel = restaurantDto.getSiteTel();
        this.sntUptaeNm = restaurantDto.getSntUptaeNm();
        this.manEipCnt = restaurantDto.getManEipCnt();
        this.wmEipCnt = restaurantDto.getWmEipCnt();
        this.trdpJubnSeNm = restaurantDto.getTrdpJubnSeNm();
        this.lvSeNm = restaurantDto.getLvSeNm();
        this.wtrSplyFacilSeNm = restaurantDto.getWtrSplyFacilSeNm();
        this.totEpNum = restaurantDto.getTotEpNum();
        this.hoffEpCnt = restaurantDto.getHoffEpCnt();
        this.fctyOwkEpCnt = restaurantDto.getFctyOwkEpCnt();
        this.fctySilJobEpCnt = restaurantDto.getFctySilJobEpCnt();
        this.fctyPdtJobEpCnt = restaurantDto.getFctyPdtJobEpCnt();
        this.bdngOwnSeNm = restaurantDto.getBdngOwnSeNm();
        this.isreAm = restaurantDto.getIsreAm();
        this.monAm = restaurantDto.getMonAm();
        this.multUsnUpsoYn = restaurantDto.getMultUsnUpsoYn();
        this.facilTotScp = restaurantDto.getFacilTotScp();
        this.jtUpsoAsgnNo = restaurantDto.getJtUpsoAsgnNo();
        this.jtUpsoMainEdf = restaurantDto.getJtUpsoMainEdf();
        this.homepage = restaurantDto.getHomepage();
    }

    /**
     * Dto와 Entity가 같은 Restaurant 인지 Unique키 로 비교
     *
     * @param restaurantDto CSV에서 읽은 Restaurant 데이터
     * @return boolean true / false
     */
    public boolean isSameRestaurant(RestaurantDto restaurantDto) {
        return opnSfTeamCode.equals(restaurantDto.getOpnSfTeamCode())
                && (mgtNo.equals(restaurantDto.getMgtNo())
                && (opnSvcId.equals(restaurantDto.getOpnSvcId())));
    }

    /**
     * 새로 읽은 데이터와 등록된 데이터가 동일한지 updateDt로 비교
     *
     * @param restaurantDto CSV에서 읽은 Restaurant 데이터
     * @return boolean true / false
     */
    public boolean isNeedUpdateRestaurantData(RestaurantDto restaurantDto) {
        if (updateDt.equals(restaurantDto.getUpdateDtAsLocalDateTime()) && updateGbn.equals(restaurantDto.getUpdateGbn())) {
            // getUpdateGbn U, I 상관없이 putUpdate 예정
            return false;
        }
        return true;
    }

    /**
     * 새로 읽은 데이터와 등록되어 있는 데이터가 동일한지 값으로 비교
     *
     * @param restaurantDto CSV에서 읽은 Restaurant 데이터
     * @return boolean true / false
     */
    public boolean isSameDataByValueWithDto(RestaurantDto restaurantDto) {
        if (!this.rowNum.equals(restaurantDto.getRowNum())) return false;
        if (!this.opnSfTeamCode.equals(restaurantDto.getOpnSfTeamCode())) return false;
        if (!this.mgtNo.equals(restaurantDto.getMgtNo())) return false;
        if (!this.opnSvcId.equals(restaurantDto.getOpnSvcId())) return false;
        if (!this.updateGbn.equals(restaurantDto.getUpdateGbn())) return false;
        if (!this.updateDt.equals(restaurantDto.getUpdateDtAsLocalDateTime())) return false;
        if (!this.bplcNm.equals(restaurantDto.getBplcNm())) return false;
        if (!this.sitePostNo.equals(restaurantDto.getSitePostNo())) return false;
        if (!this.siteWhlAddr.equals(restaurantDto.getSiteWhlAddr())) return false;
        if (!this.rdnPostNo.equals(restaurantDto.getRdnPostNo())) return false;
        if (!this.rdnWhlAddr.equals(restaurantDto.getRdnWhlAddr())) return false;
        if (!this.siteArea.equals(restaurantDto.getSiteArea())) return false;
        if (!this.apvPermYmd.equals(restaurantDto.getApvPermYmd())) return false;
        if (!this.apvCancelYmd.equals(restaurantDto.getApvCancelYmd())) return false;
        if (!this.dcbYmd.equals(restaurantDto.getDcbYmd())) return false;
        if (!this.clgStdt.equals(restaurantDto.getClgStdt())) return false;
        if (!this.clgEnddt.equals(restaurantDto.getClgEnddt())) return false;
        if (!this.ropnYmd.equals(restaurantDto.getRopnYmd())) return false;
        if (!this.trdStateGbn.equals(restaurantDto.getTrdStateGbn())) return false;
        if (!this.dtlStateGbn.equals(restaurantDto.getDtlStateGbn())) return false;
        if (!this.posX.equals(restaurantDto.getPosX())) return false;
        if (!this.posY.equals(restaurantDto.getPosY())) return false;
        if (!this.lastModTs.equals(restaurantDto.getLastModTs())) return false;
        if (!this.uptaeNm.equals(restaurantDto.getUptaeNm())) return false;
        if (!this.siteTel.equals(restaurantDto.getSiteTel())) return false;
        if (!this.sntUptaeNm.equals(restaurantDto.getSntUptaeNm())) return false;
        if (!this.manEipCnt.equals(restaurantDto.getManEipCnt())) return false;
        if (!this.wmEipCnt.equals(restaurantDto.getWmEipCnt())) return false;
        if (!this.trdpJubnSeNm.equals(restaurantDto.getTrdpJubnSeNm())) return false;
        if (!this.lvSeNm.equals(restaurantDto.getLvSeNm())) return false;
        if (!this.wtrSplyFacilSeNm.equals(restaurantDto.getWtrSplyFacilSeNm())) return false;
        if (!this.totEpNum.equals(restaurantDto.getTotEpNum())) return false;
        if (!this.hoffEpCnt.equals(restaurantDto.getHoffEpCnt())) return false;
        if (!this.fctyOwkEpCnt.equals(restaurantDto.getFctyOwkEpCnt())) return false;
        if (!this.fctySilJobEpCnt.equals(restaurantDto.getFctySilJobEpCnt())) return false;
        if (!this.fctyPdtJobEpCnt.equals(restaurantDto.getFctyPdtJobEpCnt())) return false;
        if (!this.bdngOwnSeNm.equals(restaurantDto.getBdngOwnSeNm())) return false;
        if (!this.isreAm.equals(restaurantDto.getIsreAm())) return false;
        if (!this.monAm.equals(restaurantDto.getMonAm())) return false;
        if (!this.multUsnUpsoYn.equals(restaurantDto.getMultUsnUpsoYn())) return false;
        if (!this.facilTotScp.equals(restaurantDto.getFacilTotScp())) return false;
        if (!this.jtUpsoAsgnNo.equals(restaurantDto.getJtUpsoAsgnNo())) return false;
        if (!this.jtUpsoMainEdf.equals(restaurantDto.getJtUpsoMainEdf())) return false;
        if (!this.homepage.equals(restaurantDto.getHomepage())) return false;
        return true;
    }

    /**
     * Restaurant 끼리 같은 값을 가지고 있는지 비교
     *
     * @param restaurant Restaurant
     * @return boolean true / false
     */
    public boolean isSameDataByValue(Restaurant restaurant) {
        if (!this.rowNum.equals(restaurant.getRowNum())) return false;
        if (!this.opnSfTeamCode.equals(restaurant.getOpnSfTeamCode())) return false;
        if (!this.mgtNo.equals(restaurant.getMgtNo())) return false;
        if (!this.opnSvcId.equals(restaurant.getOpnSvcId())) return false;
        if (!this.updateGbn.equals(restaurant.getUpdateGbn())) return false;
        if (!this.updateDt.equals(restaurant.getUpdateDt())) return false; // 2024-07-25T11:20:34 // 2024-07-25T11:20:33.921036
        if (!this.bplcNm.equals(restaurant.getBplcNm())) return false;
        if (!this.sitePostNo.equals(restaurant.getSitePostNo())) return false;
        if (!this.siteWhlAddr.equals(restaurant.getSiteWhlAddr())) return false;
        if (!this.rdnPostNo.equals(restaurant.getRdnPostNo())) return false;
        if (!this.rdnWhlAddr.equals(restaurant.getRdnWhlAddr())) return false;
        if (!this.siteArea.equals(restaurant.getSiteArea())) return false;
        if (!this.apvPermYmd.equals(restaurant.getApvPermYmd())) return false;
        if (!this.apvCancelYmd.equals(restaurant.getApvCancelYmd())) return false;
        if (!this.dcbYmd.equals(restaurant.getDcbYmd())) return false;
        if (!this.clgStdt.equals(restaurant.getClgStdt())) return false;
        if (!this.clgEnddt.equals(restaurant.getClgEnddt())) return false;
        if (!this.ropnYmd.equals(restaurant.getRopnYmd())) return false;
        if (!this.trdStateGbn.equals(restaurant.getTrdStateGbn())) return false;
        if (!this.dtlStateGbn.equals(restaurant.getDtlStateGbn())) return false;
        if (!this.posX.equals(restaurant.getPosX())) return false;
        if (!this.posY.equals(restaurant.getPosY())) return false;
        if (!this.lastModTs.equals(restaurant.getLastModTs())) return false;
        if (!this.uptaeNm.equals(restaurant.getUptaeNm())) return false;
        if (!this.siteTel.equals(restaurant.getSiteTel())) return false;
        if (!this.sntUptaeNm.equals(restaurant.getSntUptaeNm())) return false;
        if (!this.manEipCnt.equals(restaurant.getManEipCnt())) return false;
        if (!this.wmEipCnt.equals(restaurant.getWmEipCnt())) return false;
        if (!this.trdpJubnSeNm.equals(restaurant.getTrdpJubnSeNm())) return false;
        if (!this.lvSeNm.equals(restaurant.getLvSeNm())) return false;
        if (!this.wtrSplyFacilSeNm.equals(restaurant.getWtrSplyFacilSeNm())) return false;
        if (!this.totEpNum.equals(restaurant.getTotEpNum())) return false;
        if (!this.hoffEpCnt.equals(restaurant.getHoffEpCnt())) return false;
        if (!this.fctyOwkEpCnt.equals(restaurant.getFctyOwkEpCnt())) return false;
        if (!this.fctySilJobEpCnt.equals(restaurant.getFctySilJobEpCnt())) return false;
        if (!this.fctyPdtJobEpCnt.equals(restaurant.getFctyPdtJobEpCnt())) return false;
        if (!this.bdngOwnSeNm.equals(restaurant.getBdngOwnSeNm())) return false;
        if (!this.isreAm.equals(restaurant.getIsreAm())) return false;
        if (!this.monAm.equals(restaurant.getMonAm())) return false;
        if (!this.multUsnUpsoYn.equals(restaurant.getMultUsnUpsoYn())) return false;
        if (!this.facilTotScp.equals(restaurant.getFacilTotScp())) return false;
        if (!this.jtUpsoAsgnNo.equals(restaurant.getJtUpsoAsgnNo())) return false;
        if (!this.jtUpsoMainEdf.equals(restaurant.getJtUpsoMainEdf())) return false;
        if (!this.homepage.equals(restaurant.getHomepage())) return false;
        return true;
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

