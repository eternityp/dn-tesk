package kr.co.danal.batch.config.batch.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

@Log4j2
public class CsvChunkListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext context) {
        long readCount = context.getStepContext().getStepExecution().getReadCount();
        log.info("Read Chunk Count: {}", readCount);
    }

//    @Override
//    public void afterChunk(ChunkContext context) {
//        ChunkListener.super.afterChunk(context);
//    }
//
//    @Override
//    public void afterChunkError(ChunkContext context) {
//        ChunkListener.super.afterChunkError(context);
//    }
}
