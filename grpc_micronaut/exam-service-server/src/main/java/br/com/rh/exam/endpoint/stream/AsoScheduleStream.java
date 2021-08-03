package br.com.rh.exam.endpoint.stream;

import br.com.rh.exam.endpoint.util.LocalizationUtils;
import br.com.rh.proto.AsoRequest;
import br.com.rh.proto.AsoResponse;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AsoScheduleStream implements StreamObserver<AsoRequest> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsoScheduleStream.class);
    private StreamObserver<AsoResponse> asoResponseStreamObserver;

    public AsoScheduleStream(StreamObserver<AsoResponse> asoResponseStreamObserver) {
        this.asoResponseStreamObserver = asoResponseStreamObserver;
    }

    @Override
    public void onNext(AsoRequest asoRequest) {
        LOGGER.info("Scheduling...{}", asoRequest.getEmployeeId());
        Instant when = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(3).toInstant();
        Timestamp whenTimeStamp = Timestamp.newBuilder().setSeconds(when.getEpochSecond()).setNanos(when.getNano()).build();
        //do a lot of things with request
        //and then send response :D
        var asoResponse = AsoResponse.newBuilder().setEmployeeId(asoRequest.getEmployeeId()).setLocalization(LocalizationUtils.getPlace())
                .setWhen(whenTimeStamp).build();
        this.asoResponseStreamObserver.onNext(asoResponse);
    }

    @Override
    public void onError(Throwable t) {
        LOGGER.error("It wasn't possible to schedule the aso exam!", t);
    }

    @Override
    public void onCompleted() {
        asoResponseStreamObserver.onCompleted();
        LOGGER.info("Schedule finished.");
    }
}
