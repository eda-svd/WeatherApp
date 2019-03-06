package geekbrains.androidlevel1;

import java.io.Serializable;

public class Parcel implements Serializable{
    public final String city;
    private final int index;
    private final Boolean humidity;
    private final Boolean windSpeed;
    private final Boolean picture;

    public String getCity() {
        return city;
    }

    public int getIndex() {
        return index;
    }

    public Boolean getHumidity() {
        return humidity;
    }

    public Boolean getWindSpeed() {
        return windSpeed;
    }

    public Boolean getPicture() {
        return picture;
    }


    public Parcel(String city, int index, Boolean humidity, Boolean windSpeed, Boolean picture) {
        this.city = city;
        this.index = index;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.picture = picture;
    }

}
