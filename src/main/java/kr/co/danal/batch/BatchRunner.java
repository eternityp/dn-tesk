package kr.co.danal.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@Profile("!test")
@RequiredArgsConstructor
public class BatchRunner implements ApplicationRunner {

    @Value("${csv-file-path}")
    private String csvFilePath;

    @Value("${chunk-size}")
    private String chunkSize;

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Job csvJob = jobRegistry.getJob("csvJob");

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("csv-file-path", csvFilePath)
                .addString("chunk-size", chunkSize)
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(csvJob, jobParameters);
    }
}
