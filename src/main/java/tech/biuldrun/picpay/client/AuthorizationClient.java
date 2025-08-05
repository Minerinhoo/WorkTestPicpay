package tech.biuldrun.picpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import tech.biuldrun.picpay.client.dto.AuthorizationResponse;

@FeignClient(
        name = "authorization-client",
        url = "${client.authorization-service.url}"
)
public interface AuthorizationClient {
    @GetMapping
    ResponseEntity<AuthorizationResponse> isAutorized();

}

