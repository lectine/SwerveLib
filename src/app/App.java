package app;

import io.swerve.math.Vector3;

public class App {
    public static void main(String[] args) throws Exception {
        Vector3.init();
        System.out.println(180*Math.atan2(1,0)/Math.PI);
    }
}