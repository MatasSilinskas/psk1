package vu.lt.services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.concurrent.Future;

@Decorator
public class LoggingStudentNumberGenerator implements StudentNumberGeneratorInterface {
    @Inject
    @Delegate
    @Any
    private StudentNumberGeneratorInterface studentNumberGenerator;

    @Override
    @Futureable
    public Future<String> generate() {
        System.out.println("Generation has started!");

        return studentNumberGenerator.generate();
    }
}
