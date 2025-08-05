package tech.biuldrun.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

public class WalletNotFoundException extends PicPayException{

        private Long walletId;

    public WalletNotFoundException(Long walletId) {
        this.walletId = walletId;
    }

    @Override
    public ProblemDetail toProblemDetail() {

        var pb = toProblemDetail().forStatus( HttpStatus.UNPROCESSABLE_ENTITY );

        pb.setTitle( "Wallet Not Found");
        pb.setDetail( "There is not Wallet id " + walletId + "." );


        return pb;

    }

}
