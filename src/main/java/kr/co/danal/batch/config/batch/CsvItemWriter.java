package kr.co.danal.batch.config.batch;

import kr.co.danal.batch.dto.RestaurantDto;
import kr.co.danal.batch.entity.Restaurant;
import kr.co.danal.batch.mapper.RestaurantRowMapper;
import kr.co.danal.batch.service.JdbcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.co.danal.batch.service.JdbcService.BATCH_INSERT_QUERY;
import static kr.co.danal.batch.service.JdbcService.BATCH_UPDATE_QUERY;

@Log4j2
@RequiredArgsConstructor
public class CsvItemWriter<T> implements ItemWriter<T> {

    private final JdbcService jdbcService;


    /**
     * 작업 완료된 restaurantDtoList 현재 가지고 있는 데이터에 따라
     * Batch Insert, Batch Update를 수행하는 ItemWriter
     *
     * @param chunk Reader, Processor  작업 완료된 restaurantDtoList
     */
    @Override
    public void write(Chunk<? extends T> chunk) {

        List<RestaurantDto> restaurantDtoList = (List<RestaurantDto>) chunk.getItems();

        List<Restaurant> exisitingRestaurantList = jdbcService.getRestaurantList(restaurantDtoList);

        // UpdateList
        List<Restaurant> batchUpdateList = new ArrayList<>();
        // New InsertList
        List<Restaurant> newBatchInsertList = new ArrayList<>();

        Map<String, Restaurant> exisitRestaurantMap = new HashMap<>();
        for (Restaurant restaurant : exisitingRestaurantList) {
            String rKey = restaurant.makeKey();
            exisitRestaurantMap.put(rKey, restaurant);
        }

        for (RestaurantDto readRestaurantDto : restaurantDtoList) {
            String rKey = readRestaurantDto.makeKey();
            Restaurant exisitRestaurant = exisitRestaurantMap.get(rKey);
            if (exisitRestaurant != null) {
                if (exisitRestaurant.isNeedUpdateRestaurantData(readRestaurantDto)) {
                    exisitRestaurant.putNewData(readRestaurantDto);
                    batchUpdateList.add(exisitRestaurant);
                }
            } else {
                Restaurant newRestaurant = new Restaurant(readRestaurantDto);
                newBatchInsertList.add(newRestaurant);
            }
        }

        if (!newBatchInsertList.isEmpty()) {
            jdbcService.batchNewInsertRestaurant(newBatchInsertList);
        }
        if (!batchUpdateList.isEmpty()) {
            jdbcService.batchUpdateRestaurant(batchUpdateList);
        }
    }
}

