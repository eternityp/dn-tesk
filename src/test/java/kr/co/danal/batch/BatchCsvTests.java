package kr.co.danal.batch;

import kr.co.danal.batch.repository.RestaurantRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.batch.core.Job;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
@SpringBootTest
@SpringBatchTest
@ActiveProfiles(value = "test")
class BatchCsvTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private JobRegistry jobRegistry;


    @BeforeEach
    void setBeforeTest() throws NoSuchJobException {
        log.info("테스트 전 설정 시작");
        cleanDatabaseBeforeTest();
        setJob();
    }


    void cleanDatabaseBeforeTest() {
        log.info("테스트 전 데이터 정리");
        String opnSvcId = "07_24_04_P";
        restaurantRepository.deleteAllByOpnSvcId(opnSvcId);
        restaurantRepository.flush();
    }

    void setJob() throws NoSuchJobException {
        log.info("테스트 전 Job을 설정");
        Job csvJob = jobRegistry.getJob("csvJob");
        jobLauncherTestUtils.setJob(csvJob);
    }

    @Test
    @DisplayName("정상 csv파일 테스트")
    void readCsvTest() throws Exception {
        LocalDateTime start = LocalDateTime.now();
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("csv-file-path", "data/full_test.csv")
                .addString("chunk-size", "200")
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());

        int count = restaurantRepository.countAll();
        assertEquals(200, count);

        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("----------------------------------- 걸린시간  :  " + duration.getSeconds() +" 초");
    }

    @Test
    @DisplayName("정상 데이터 200개 중 잘못된 데이터 5개 테스트")
    void invalidDataInCsvTest() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("csv-file-path", "data/full_test_invalid_5.csv")
                .addString("chunk-size", "100")
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());

        int count = restaurantRepository.countAll();
        assertEquals(195, count);
    }

    @Test
    @DisplayName("존재 하지 않는 파일 테스트")
    void notFoundCsvTest() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("csv-file-path", "data/not.csv")
                .addString("chunk-size", "5")
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("FAILED", jobExecution.getExitStatus().getExitCode());
    }



}


