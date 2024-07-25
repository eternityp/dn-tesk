package kr.co.danal.batch.config.batch.listener;

import kr.co.danal.batch.dto.RestaurantDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemProcessListener;

@Log4j2
public class CsvItemProcessListener implements ItemProcessListener<RestaurantDto, RestaurantDto> {

//    @Override
//    public void beforeProcess(RestaurantDto item) {
//        log.info("CsvItemRead 시작");
//    }

//    @Override
//    public void afterProcess(RestaurantDto item, RestaurantDto result) {
//        ItemProcessListener.super.afterProcess(item, result);
//    }

    @Override
    public void onProcessError(RestaurantDto item, Exception e) {
        log.error("CsvItemProcess Exception - opnSfTeamCode: {}, mgtNo: {}, opnSvcId:{}, Message : {}", item.getOpnSfTeamCode(), item.getMgtNo(), item.getOpnSvcId(), e.getMessage());
    }
}
