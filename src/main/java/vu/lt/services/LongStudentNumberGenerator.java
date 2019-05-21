package vu.lt.services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.util.concurrent.Future;
import java.util.Random;

@Specializes
@ApplicationScoped
public class LongStudentNumberGenerator extends StudentNumberGenerator {

    @Override
    @Futureable
    public Future<String> generate() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        final Integer studentNumber = new Random().nextInt(100) * 1000;

        return new AsyncResult<>(String.valueOf(studentNumber));
    }
}
