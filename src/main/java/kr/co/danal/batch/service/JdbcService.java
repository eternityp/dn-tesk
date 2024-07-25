package kr.co.danal.batch.service;

import kr.co.danal.batch.dto.RestaurantDto;
import kr.co.danal.batch.entity.Restaurant;
import kr.co.danal.batch.mapper.RestaurantRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class JdbcService {

    public static final String BATCH_INSERT_QUERY;
    public static final String BATCH_UPDATE_QUERY;

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static {
        StringBuilder insertBuilder = new StringBuilder();
        insertBuilder.append("insert into tbl_restaurant");
        insertBuilder.append(" (row_num, opn_sf_team_code, mgt_no, opn_svc_id");
        insertBuilder.append(" , update_gbn, update_dt, bplc_nm, site_post_no");
        insertBuilder.append(" , site_whl_addr, rdn_post_no, rdn_whl_addr, site_area");
        insertBuilder.append(" , apv_perm_ymd, apv_cancel_ymd, dcb_ymd, clg_stdt, clg_enddt");
        insertBuilder.append(" , ropn_ymd, trd_state_gbn, dtl_state_gbn, pos_x");
        insertBuilder.append(" , pos_y, last_mod_ts, uptae_nm, site_tel");
        insertBuilder.append(" , snt_uptae_nm, man_eip_cnt, wm_eip_cnt, trdp_jubn_se_nm");
        insertBuilder.append(" , lv_se_nm, wtr_sply_facil_se_nm, tot_ep_num, hoff_ep_cnt");
        insertBuilder.append(" , fcty_owk_ep_cnt, fcty_sil_job_ep_cnt, fcty_pdt_job_ep_cnt, bdng_own_se_nm");
        insertBuilder.append(" , isre_am, mon_am, mult_usn_upso_yn, facil_tot_scp");
        insertBuilder.append(" , jt_upso_asgn_no, jt_upso_main_edf, homepage");
        insertBuilder.append(" )");
        insertBuilder.append(" value");
        insertBuilder.append(" (:rowNum, :opnSfTeamCode, :mgtNo, :opnSvcId");
        insertBuilder.append(" , :updateGbn, :updateDt, :bplcNm, :sitePostNo");
        insertBuilder.append(" , :siteWhlAddr, :rdnPostNo, :rdnWhlAddr, :siteArea");
        insertBuilder.append(" , :apvPermYmd, :apvCancelYmd, :dcbYmd, :clgStdt, :clgEnddt");
        insertBuilder.append(" , :ropnYmd, :trdStateGbn, :dtlStateGbn, :posX");
        insertBuilder.append(" , :posY, :lastModTs, :uptaeNm, :siteTel");
        insertBuilder.append(" , :sntUptaeNm, :manEipCnt, :wmEipCnt, :trdpJubnSeNm");
        insertBuilder.append(" , :lvSeNm, :wtrSplyFacilSeNm, :totEpNum, :hoffEpCnt");
        insertBuilder.append(" , :fctyOwkEpCnt, :fctySilJobEpCnt, :fctyPdtJobEpCnt, :bdngOwnSeNm");
        insertBuilder.append(" , :isreAm, :monAm, :multUsnUpsoYn, :facilTotScp");
        insertBuilder.append(" , :jtUpsoAsgnNo, :jtUpsoMainEdf, :homepage");
        insertBuilder.append(" )");
        BATCH_INSERT_QUERY = insertBuilder.toString();

        StringBuilder updateBuilder = new StringBuilder();
        updateBuilder.append("update tbl_restaurant set");
        updateBuilder.append(" row_num = :rowNum");
        updateBuilder.append(",update_gbn = :updateGbn");
        updateBuilder.append(",update_dt = :updateDt");
        updateBuilder.append(",bplc_nm = :bplcNm");
        updateBuilder.append(",site_post_no = :sitePostNo");
        updateBuilder.append(",site_whl_addr = :siteWhlAddr");
        updateBuilder.append(",rdn_post_no = :rdnPostNo");
        updateBuilder.append(",rdn_whl_addr = :rdnWhlAddr");
        updateBuilder.append(",site_area = :siteArea");
        updateBuilder.append(",apv_perm_ymd = :apvPermYmd");
        updateBuilder.append(",apv_cancel_ymd = :apvCancelYmd");
        updateBuilder.append(",dcb_ymd = :dcbYmd");
        updateBuilder.append(",clg_stdt = :clgStdt");
        updateBuilder.append(",clg_enddt = :clgEnddt");
        updateBuilder.append(",clg_enddt = :clgEnddt");
        updateBuilder.append(",ropn_ymd = :ropnYmd");
        updateBuilder.append(",trd_state_gbn = :trdStateGbn");
        updateBuilder.append(",dtl_state_gbn = :dtlStateGbn");
        updateBuilder.append(",pos_x = :posX");
        updateBuilder.append(",pos_y = :posY");
        updateBuilder.append(",last_mod_ts = :lastModTs");
        updateBuilder.append(",uptae_nm = :uptaeNm");
        updateBuilder.append(",site_tel = :siteTel");
        updateBuilder.append(",snt_uptae_nm = :sntUptaeNm");
        updateBuilder.append(",man_eip_cnt = :man_eip_cnt");
        updateBuilder.append(",wm_eip_cnt = :wmEipCnt");
        updateBuilder.append(",trdp_jubn_se_nm = :trdpJubnSeNm");
        updateBuilder.append(",lv_se_nm = :lvSeNm");
        updateBuilder.append(",wtr_sply_facil_se_nm = :wtrSplyFacilSeNm");
        updateBuilder.append(",tot_ep_num = :tot_ep_num");
        updateBuilder.append(",hoff_ep_cnt = :hoffEpCnt");
        updateBuilder.append(",fcty_owk_ep_cnt = :fctyOwkEpCnt");
        updateBuilder.append(",fcty_sil_job_ep_cnt = :fctySilJobEpCnt");
        updateBuilder.append(",fcty_pdt_job_ep_cnt = :fctyPdtJobEpCnt");
        updateBuilder.append(",bdng_own_se_nm = :bdngOwnSeNm");
        updateBuilder.append(",isre_am = :isreAm");
        updateBuilder.append(",mon_am = :mon_am");
        updateBuilder.append(",mult_usn_upso_yn = :multUsnUpsoYn");
        updateBuilder.append(",facil_tot_scp = :facilTotScp");
        updateBuilder.append(",jt_upso_asgn_no = :jtUpsoAsgnNo");
        updateBuilder.append(",jt_upso_main_edf = :jtUpsoMainEdf");
        updateBuilder.append(",homepage = :homepage");
        updateBuilder.append(")");
        updateBuilder.append(" where opn_sf_team_code = :opnSfTeamCode and mgt_no = :mgtNo and opn_svc_id = :opnSvcId");
        BATCH_UPDATE_QUERY = updateBuilder.toString();
    }

    /**
     * 새로운 데이터 Batch Insert
     *
     * @param newBatchInsertList CSV에서 추출한 신규 등록할 데이터
     */
    public void batchNewInsertRestaurant(List<Restaurant> newBatchInsertList) {
        namedParameterJdbcTemplate.batchUpdate(BATCH_INSERT_QUERY, SqlParameterSourceUtils.createBatch(newBatchInsertList));
    }

    /**
     * 변경점이 있는 데이터 Batch Update
     *
     * @param batchUpdateList CSV에서 추출한 수정 될 데이터
     */
    public void batchUpdateRestaurant(List<Restaurant> batchUpdateList) {
        namedParameterJdbcTemplate.batchUpdate(BATCH_UPDATE_QUERY, SqlParameterSourceUtils.createBatch(batchUpdateList));
    }

    /**
     * 기존에 데이터가 있는지 확인하는 기능
     *
     * @param readRestaurantDtoList API에서 기본키로 설정된 컬럼의 목록
     * @return List<Restaurant> 검색 된 데이터 목록
     */
    public List<Restaurant> getRestaurantList(List<RestaurantDto> readRestaurantDtoList) {
        StringBuilder selectRestaurantSqlBuilder = new StringBuilder("SELECT * FROM tbl_restaurant WHERE (opn_sf_team_code, mgt_no, opn_svc_id) IN (");
        List<String> query = new ArrayList<>();
        for (int i = 0; i < readRestaurantDtoList.size(); i++) {
            selectRestaurantSqlBuilder.append(i > 0 ? ", " : "").append("(?, ?, ?)");
            query.add(readRestaurantDtoList.get(i).getOpnSfTeamCode());
            query.add(readRestaurantDtoList.get(i).getMgtNo());
            query.add(readRestaurantDtoList.get(i).getOpnSvcId());
        }
        selectRestaurantSqlBuilder.append(")");

        String selectRestaurantSql = selectRestaurantSqlBuilder.toString();
        return jdbcTemplate.query(selectRestaurantSql, new RestaurantRowMapper(), query.toArray());
    }


}
