package br.com.rh.manager.stream;

import br.com.rh.proto.AsoResponse;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.ZoneId;

public class AsoStream implements StreamObserver<AsoResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsoStream.class);

    @Override
    public void onNext(AsoResponse value) {
        var when = Instant.ofEpochSecond(value.getWhen().getSeconds(), value.getWhen().getNanos()).atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime();
        LOGGER.info("Schedule Details: {}-{}-{}", value.getEmployeeId(), value.getLocalization(), when);
    }

    @Override
    public void onError(Throwable t) {
        LOGGER.error("There was a error on schedule process.", t);
    }

    @Override
    public void onCompleted() {
        LOGGER.info("The schedule process was finished.");
    }
}
