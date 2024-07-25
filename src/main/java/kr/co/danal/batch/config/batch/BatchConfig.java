package kr.co.danal.batch.config.batch;


import kr.co.danal.batch.config.batch.listener.*;
import kr.co.danal.batch.dto.RestaurantDto;
import kr.co.danal.batch.service.JdbcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.sql.SQLTransactionRollbackException;
import java.sql.SQLTransientConnectionException;
import java.util.HashMap;
import java.util.Map;


@Log4j2
@Configuration
//@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository;
    private final JdbcService jdbcService;
//    private final CsvJobParameter jobParameter;

    public static String[] CSV_HEADER = {
            "rowNum", "opnSvcNm", "opnSvcId", "opnSfTeamCode", "mgtNo", "apvPermYmd", "apvCancelYmd", "trdStateGbn", "trdStateNm", "dtlStateGbn",
            "dtlStateNm", "dcbYmd", "clgStdt", "clgEnddt", "ropnYmd", "siteTel", "siteArea", "sitePostNo", "siteWhlAddr", "rdnWhlAddr", "rdnPostNo",
            "bplcNm", "lastModTs", "updateGbn", "updateDt", "uptaeNm", "posX", "posY", "sntUptaeNm", "manEipCnt", "wmEipCnt", "trdpJubnSeNm",
            "lvSeNm", "wtrSplyFacilSeNm", "totEpNum", "hoffEpCnt", "fctyOwkEpCnt", "fctySilJobEpCnt", "fctyPdtJobEpCnt", "bdngOwnSeNm", "isreAm",
            "monAm", "multUsnUpsoYn", "facilTotScp", "jtUpsoAsgnNo", "jtUpsoMainEdf", "homepage"};

//    @Bean
//    @JobScope
//    public CsvJobParameter jobParameter() {
//        return new CsvJobParameter();
//    }

    @Bean
    @StepScope
    public FlatFileItemReader<RestaurantDto> reader(@Value("#{jobParameters['csv-file-path']}") String filePath) {
        FlatFileItemReaderBuilder<RestaurantDto> readerBuilder = new FlatFileItemReaderBuilder<RestaurantDto>()
                .name("csvItemReader")
//                .resource(new FileSystemResource("data/full_test.csv"))
                .resource(new FileSystemResource(filePath))
//                .resource(new FileSystemResource(jobParameter.getFilePath()))
                .delimited()
                .strict(false)
                .names(CSV_HEADER)
                .linesToSkip(1)
                .encoding("EUC-KR")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(RestaurantDto.class);
                }});
//        if (true) {
//            readerBuilder.encoding("UTF-8");
//        }
        return readerBuilder.build();
    }

    @Bean
    @StepScope
    public CsvItemProcessor processor() {
        return new CsvItemProcessor();
    }

    @Bean
    @StepScope
    public CsvItemWriter<RestaurantDto> writer() {
        return new CsvItemWriter<>(jdbcService);
    }

    @Bean
    public RetryPolicy csvRetryPolicy() {
        Map<Class<? extends Throwable>, Boolean> retryableExceptions = new HashMap<>();
        retryableExceptions.put(SQLException.class, true);
        return new SimpleRetryPolicy(5, retryableExceptions, true, true);
    }

    @Bean
    public BackOffPolicy csvBackOffPolicy() {
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(5000);
        return backOffPolicy;
    }

    @Bean
    public Job csvJob(JobRepository jobRepository, Step csvStep1) {
        return new JobBuilder("csvJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(csvStep1)
                .end()
                .listener(new CsvJobExecutionListener())
                .build();
    }

    @Bean
    @JobScope
    public Step csvStep1(JobRepository jobRepository, PlatformTransactionManager transactionManager, @Value("#{jobParameters['chunk-size']}") Integer chunkSize) {
        return new StepBuilder("csvStep1", jobRepository)
                .<RestaurantDto, RestaurantDto>chunk(chunkSize, transactionManager)
                .reader(reader(null))
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .retryPolicy(csvRetryPolicy())
                .backOffPolicy(csvBackOffPolicy())
                .listener(new CsvStepExecutionListener())
                .listener(new CsvItemReadListener())
                .listener(new CsvItemProcessListener())
                .listener(new CsvItemWriteListener())
                .listener(new CsvChunkListener())
                .listener(new CsvRetryListener())
                .build();
    }
}
