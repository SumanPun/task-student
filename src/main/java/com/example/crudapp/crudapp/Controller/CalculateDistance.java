package com.example.crudapp.crudapp.Controller;

import com.example.crudapp.crudapp.Payloads.ShowDistance;
import com.example.crudapp.crudapp.Services.DistanceCalulateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distance")
public class CalculateDistance {

    private final DistanceCalulateService distanceCalulateService;

    public CalculateDistance(DistanceCalulateService distanceCalulateService) {
        this.distanceCalulateService = distanceCalulateService;
    }

    @GetMapping("/")
    public ResponseEntity<ShowDistance> calculateDistance(@RequestParam double latitude1,
                                                          @RequestParam double longitude1,
                                                          @RequestParam double latitude2,
                                                          @RequestParam double longitude2) {
        ShowDistance showDistance = this.distanceCalulateService.Haversine(latitude1,longitude1,latitude2,longitude2);
        return new ResponseEntity<>(showDistance, HttpStatus.OK);
    }
}
