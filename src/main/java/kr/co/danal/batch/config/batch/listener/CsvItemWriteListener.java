package kr.co.danal.batch.config.batch.listener;

import kr.co.danal.batch.dto.RestaurantDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;

@Log4j2
public class CsvItemWriteListener implements ItemWriteListener<RestaurantDto> {

//    @Override
//    public void beforeWrite(Chunk<? extends RestaurantDto> items) {
//        ItemWriteListener.super.beforeWrite(items);
//    }

//    @Override
//    public void afterWrite(Chunk<? extends RestaurantDto> items) {
//        ItemWriteListener.super.afterWrite(items);
//    }

    @Override
    public void onWriteError(Exception exception, Chunk<? extends RestaurantDto> items) {
        log.error("CsvItemWriteListener Exception - {}", exception.getMessage());
    }
}
