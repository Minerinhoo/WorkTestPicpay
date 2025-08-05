package tech.biuldrun.picpay.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

public class transferNotAuthorizedexeption extends PicPayException{
    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus( HttpStatus.UNPROCESSABLE_ENTITY );

        pb.setTitle("Transfer Not Authorized");
        pb.setDetail( "Authorization Service Not Authorization " );


        return pb;
    }
}
