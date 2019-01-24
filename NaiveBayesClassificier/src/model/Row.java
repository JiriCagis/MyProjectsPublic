package model;

public class Row {

    private Outlook outlook;
    private Temperature temperature;
    private Humidity humidity;
    private Boolean windy;
    private Boolean play;

    public Row(Outlook outlook, Temperature temperature, Humidity humidity, Boolean windy, Boolean play) {
        this.outlook = outlook;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windy = windy;
        this.play = play;
    }

    public Row() {
        outlook = null;
        temperature = null;
        humidity = null;
        windy = null;
        play =null;
    }

    public Outlook getOutlook() {
        return outlook;
    }

    public void setOutlook(Outlook outlook) {
        this.outlook = outlook;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public Boolean getWindy() {
        return windy;
    }

    public void setWindy(Boolean windy) {
        this.windy = windy;
    }

    public Boolean getPlay() {
        return play;
    }

    public void setPlay(Boolean play) {
        this.play = play;
    }


    
    

    
}
