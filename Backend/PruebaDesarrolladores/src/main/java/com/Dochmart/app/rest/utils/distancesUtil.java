package com.Dochmart.app.rest.utils;

public class distancesUtil {
    public static final double R = 6372.8; // In kilometers
    public static double calcularDistancia(double latitud1, double longitud1, double latitud2, double longitud2) {
        latitud1 = Math.toRadians(latitud1);
        latitud2 = Math.toRadians(latitud2);
        double dLat = latitud2 - latitud1;
        double dLon = Math.toRadians(longitud2 - longitud1);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(latitud1) * Math.cos(latitud2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
}
