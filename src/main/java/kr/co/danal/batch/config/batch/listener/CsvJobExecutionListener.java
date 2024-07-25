package kr.co.danal.batch.config.batch.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

@Log4j2
public class CsvJobExecutionListener {

    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        log.info("{} - Job 시작", jobExecution.getJobInstance().getJobName());
    }



    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        long duration = jobExecution.getEndTime().getSecond() - jobExecution.getStartTime().getSecond();
        log.info("{} - Job 종료", jobExecution.getJobInstance().getJobName());
        log.info("{} - Job 실행시간 :  {} 초", jobExecution.getJobInstance().getJobName(), duration);
    }
}
