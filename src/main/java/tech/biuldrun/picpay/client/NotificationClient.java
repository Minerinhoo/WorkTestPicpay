package tech.biuldrun.picpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.biuldrun.picpay.entity.Transfer;

@FeignClient(
        name = "notification-client",
        url = "${client.notification-service.url}"
)
public interface NotificationClient {

    @PostMapping
    ResponseEntity<Void> sendNotification(@RequestBody Transfer transfer);

}
