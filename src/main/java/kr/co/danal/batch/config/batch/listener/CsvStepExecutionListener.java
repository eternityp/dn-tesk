package kr.co.danal.batch.config.batch.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.*;

@Log4j2
public class CsvStepExecutionListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("{} - Step 시작", stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("{} - Step 종료, batchStatus : {}", stepExecution.getStepName(), stepExecution.getStatus());
        return ExitStatus.COMPLETED;
    }
}
