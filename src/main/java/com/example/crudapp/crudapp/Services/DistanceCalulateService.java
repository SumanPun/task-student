package com.example.crudapp.crudapp.Services;

import com.example.crudapp.crudapp.Payloads.ShowDistance;
import org.springframework.stereotype.Component;

@Component
public class DistanceCalulateService {

    public ShowDistance Haversine(double latitude1, double longitude1 , double latitude2, double longitude2) {

        double latitudeDifference = Math.toRadians(latitude2 - latitude1);
        double longitudeDifference = Math.toRadians(longitude2 - longitude1);

        double latitudeRadian1 = Math.toRadians(latitude1);
        double latitudeRadian2 = Math.toRadians(latitude2);

        double a = Math.abs( Math.pow(Math.sin(latitudeDifference / 2), 2) +
                Math.pow(Math.sin(longitudeDifference / 2), 2) *
                        Math.cos(latitudeRadian1) *
                        Math.cos(latitudeRadian2));

        double radius_of_earth_km = 6371;
        double radius_of_earth_miles = 2961;

        double distanceInKm = 2 * radius_of_earth_km * Math.asin(Math.sqrt(a));
        double distanceInMiles = 2 * radius_of_earth_miles * Math.asin(Math.sqrt(a));


        return new ShowDistance(distanceInKm, distanceInMiles);
    }
}
