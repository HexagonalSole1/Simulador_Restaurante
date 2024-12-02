package org.example.utils;

import java.util.Random;

public class DistribucionPoisson {
    private final Random random;

    public DistribucionPoisson() {
        this.random = new Random();
    }

    public int generate(double lambda) {
        int count = 0;
        double expLambda = Math.exp(-lambda);
        double p = 1.0;

        do {
            count++;
            p *= random.nextDouble();
        } while (p > expLambda);

        return count - 1;
    }
}

