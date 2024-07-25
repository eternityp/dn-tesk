package kr.co.danal.batch.config.batch.listener;

import kr.co.danal.batch.dto.RestaurantDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemReadListener;

@Log4j2
public class CsvItemReadListener implements ItemReadListener<RestaurantDto> {

//    @Override
//    public void beforeRead() {
//        log.info("CsvItemRead 시작");
//    }

//    @Override
//    public void afterRead(RestaurantDto item) {
//    }

    @Override
    public void onReadError(Exception ex) {
        log.error("CsvItemRead Exception : {}", ex.getMessage());
    }
}
