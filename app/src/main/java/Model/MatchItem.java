package Model;

public class MatchItem {
    private String homeTeam;
    private String guestTeam;
    private String id;
    private String currPrice;
    private String percentChangeInPrice;


    public MatchItem(String homeTeam, String guestTeam, String id, String currPrice, String change) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.id = id;
        this.currPrice = currPrice;
        this.percentChangeInPrice = change;
    }

    public String getPercentChangeInPrice() {
        return percentChangeInPrice;
    }

    public void setPercentChangeInPrice(String percentChangeInPrice) {
        this.percentChangeInPrice = percentChangeInPrice;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(String guestTeam) {
        this.guestTeam = guestTeam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(String currPrice) {
        this.currPrice = currPrice;
    }
}
