package tech.biuldrun.picpay.service;


import org.springframework.stereotype.Service;
import tech.biuldrun.picpay.client.AuthorizationClient;
import tech.biuldrun.picpay.controller.dto.TransferDto;
import tech.biuldrun.picpay.exception.PicPayException;


@Service
public class AuthorizationService {
    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto transfer){

        var resp = authorizationClient.isAutorized();

        if (resp.getStatusCode() .isError()){
            throw new PicPayException();
        }
        return resp.getBody().authorized();
     }
}
