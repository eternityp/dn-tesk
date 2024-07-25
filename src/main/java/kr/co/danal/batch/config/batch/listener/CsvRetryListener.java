package kr.co.danal.batch.config.batch.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;


@Log4j2
public class CsvRetryListener implements RetryListener {

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        log.info("CsvRetryListener Start Retry : {}", context.getRetryCount());
        return true;
    }

//    @Override
//    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
//    }

    @Override
    public <T, E extends Throwable> void onSuccess(RetryContext context, RetryCallback<T, E> callback, T result) {
       log.info("CsvRetryListener Retry Success");
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        log.error("CsvRetryListener Exception : {}", throwable.getMessage());
    }
}
