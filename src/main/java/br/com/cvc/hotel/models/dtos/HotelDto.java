package br.com.cvc.hotel.models.dtos;

import java.util.List;

public class HotelDto {

    private Long id;
    private String name;
    private Long cityCode;
    private String cityName;
    private List<HotelRoomDto> rooms;

    public HotelDto() {
    }

    public HotelDto(Long id, String name, Long cityCode, String cityName, List<HotelRoomDto> rooms) {
        this.id = id;
        this.name = name;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCityCode() {
        return cityCode;
    }

    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<HotelRoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(List<HotelRoomDto> rooms) {
        this.rooms = rooms;
    }

}
