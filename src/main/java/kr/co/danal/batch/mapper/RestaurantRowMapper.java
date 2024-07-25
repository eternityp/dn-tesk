package kr.co.danal.batch.mapper;

import jakarta.persistence.Column;
import kr.co.danal.batch.entity.Restaurant;
import org.hibernate.annotations.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class RestaurantRowMapper implements RowMapper<Restaurant> {
    @Override
    public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Restaurant restaurant = Restaurant.builder()
                .rowNum(rs.getLong("row_num"))
                .opnSfTeamCode(rs.getString("opn_sf_team_code"))
                .mgtNo(rs.getString("mgt_no"))
                .opnSvcId(rs.getString("opn_svc_id"))
                .updateGbn(rs.getString("update_gbn"))
                .updateDt(rs.getObject("update_dt", LocalDateTime.class))
                .bplcNm(rs.getString("bplc_nm"))
                .sitePostNo(rs.getString("site_post_no"))
                .siteWhlAddr(rs.getString("site_whl_addr"))
                .rdnPostNo(rs.getString("rdn_post_no"))
                .rdnWhlAddr(rs.getString("rdn_whl_addr"))
                .siteArea(rs.getString("site_area"))
                .apvPermYmd(rs.getString("apv_perm_ymd"))
                .apvCancelYmd(rs.getString("apv_cancel_ymd"))
                .dcbYmd(rs.getString("dcb_ymd"))
                .clgStdt(rs.getString("clg_stdt"))
                .clgEnddt(rs.getString("clg_enddt"))
                .ropnYmd(rs.getString("ropn_ymd"))
                .trdStateGbn(rs.getString("trd_state_gbn"))
                .dtlStateGbn(rs.getString("dtl_state_gbn"))
                .posX(rs.getString("pos_x"))
                .posY(rs.getString("pos_y"))
                .lastModTs(rs.getString("last_mod_ts"))
                .uptaeNm(rs.getString("uptae_nm"))
                .siteTel(rs.getString("site_tel"))
                .sntUptaeNm(rs.getString("snt_uptae_nm"))
                .manEipCnt(rs.getInt("man_eip_cnt"))
                .wmEipCnt(rs.getInt("wm_eip_cnt"))
                .trdpJubnSeNm(rs.getString("trdp_jubn_se_nm"))
                .lvSeNm(rs.getString("lv_se_nm"))
                .wtrSplyFacilSeNm(rs.getString("wtr_sply_facil_se_nm"))
                .totEpNum(rs.getInt("tot_ep_num"))
                .hoffEpCnt(rs.getInt("hoff_ep_cnt"))
                .fctyOwkEpCnt(rs.getInt("fcty_owk_ep_cnt"))
                .fctySilJobEpCnt(rs.getInt("fcty_sil_job_ep_cnt"))
                .fctyPdtJobEpCnt(rs.getInt("fcty_pdt_job_ep_cnt"))
                .bdngOwnSeNm(rs.getString("bdng_own_se_nm"))
                .isreAm(rs.getInt("isre_am"))
                .monAm(rs.getInt("mon_am"))
                .multUsnUpsoYn(rs.getString("mult_usn_upso_yn"))
                .facilTotScp(rs.getString("facil_tot_scp"))
                .jtUpsoAsgnNo(rs.getString("jt_upso_asgn_no"))
                .jtUpsoMainEdf(rs.getString("jt_upso_main_edf"))
                .homepage(rs.getString("homepage"))
                .build();
        return restaurant;
    }
}
