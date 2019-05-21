package vu.lt.services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Random;
import java.util.concurrent.Future;

@Alternative
@ApplicationScoped
public class FastStudentNumberGenerator implements StudentNumberGeneratorInterface {
    @Override
    @Futureable
    public Future<String> generate() {
        final Integer studentNumber = new Random().nextInt(100);

        return new AsyncResult<>(String.valueOf(studentNumber));
    }
}
