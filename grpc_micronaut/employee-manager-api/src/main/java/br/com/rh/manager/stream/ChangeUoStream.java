package br.com.rh.manager.stream;

import br.com.rh.proto.ChangeUoResponse;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangeUoStream implements StreamObserver<ChangeUoResponse> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeUoStream.class);

    @Override
    public void onNext(ChangeUoResponse value) {
        LOGGER.info("Protocol: {}", value.getProtocolNumber());
    }

    @Override
    public void onError(Throwable t) {
        LOGGER.error("Error on response: ", t);
    }

    @Override
    public void onCompleted() {
        LOGGER.info("The UO change is done.");
    }
}
