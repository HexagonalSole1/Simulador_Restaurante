package org.example.utils;
import java.util.Random;

public class DistribucionPoisson {
    public static int generarIntervaloPoisson(double lambda) {
        Random random = new Random();
        return (int) (-Math.log(1 - random.nextDouble()) / lambda);
    }
}
