package vu.lt.services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.concurrent.Future;
import java.util.Random;

@ApplicationScoped
public class StudentNumberGenerator implements Serializable, StudentNumberGeneratorInterface {

    @Futureable
    public Future<String> generate() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        final Integer studentNumber = new Random().nextInt(100);

        return new AsyncResult<>(String.valueOf(studentNumber));
    }
}