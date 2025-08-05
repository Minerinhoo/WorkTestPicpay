package tech.biuldrun.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

public class transferNotAllowedForWalletTypeExeption extends PicPayException {
    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus( HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle( "This wallet type is not allowed to Transfer." );
        
        return pb;
    }
}
