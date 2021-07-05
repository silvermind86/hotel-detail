package br.com.cvc.hotel.adapters;

import br.com.cvc.hotel.exceptions.AdapterHttpException;
import br.com.cvc.hotel.models.dtos.HotelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BackendHotelAdapter {

    private static final String CVC_BACKEND_HOST = "https://cvcbackendhotel.herokuapp.com/";

    @Autowired
    RestTemplate restTemplate;

    @Async
    @Cacheable("hotels")
    public CompletableFuture<List<HotelDto>> getDetails(Long id) {
        List<HotelDto> hotels = new ArrayList<>();

        try{
            HotelDto[] hotelsArrays = restTemplate.getForObject(CVC_BACKEND_HOST + "/hotels/" + id, HotelDto[].class);
            hotels.addAll(Arrays.asList(hotelsArrays));
            return CompletableFuture.completedFuture(hotels);
        } catch (HttpClientErrorException httpEx) {
            throw new AdapterHttpException("There's a problem to resolve remote server dependency.", httpEx);
        } catch (Exception e) {
            throw e;
        }
    }
}
