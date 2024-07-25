package kr.co.danal.batch.config.batch;

import kr.co.danal.batch.dto.RestaurantDto;
import org.springframework.batch.item.ItemProcessor;

public class CsvItemProcessor implements ItemProcessor<RestaurantDto, RestaurantDto> {

    /**
     * Reader에서 읽어들인 RestaurantDto를 하나씩 처리하는 Processor
     * 데이터 유효값 체크를 통해 검증된 것들만 Writer로 전달
     *
     * @param restaurantDto
     * @return RestaurantDto or Null
     */
    @Override
    public RestaurantDto process(RestaurantDto restaurantDto) {
        if(restaurantDto.isValidCode()) {
            restaurantDto.removeDateTimeDashColon();
            return restaurantDto;
        }
        return null;
    }
}
