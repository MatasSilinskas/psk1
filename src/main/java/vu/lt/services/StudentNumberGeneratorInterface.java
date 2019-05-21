package vu.lt.services;

import java.util.concurrent.Future;

public interface StudentNumberGeneratorInterface {
    Future<String> generate();
}
