package pac.example.com.mapapplication;

/**
 * Created by Yassine on 11/02/2018.
 */

public class Parking {

    private String name;
    private String imgUrl;
    private String description;
    private String adresse;
    private String price;
    private String v1;
    private String v2;

    public Parking(String name, String imgUrl, String description, String adresse, String price, String v1, String v2) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.adresse = adresse;
        this.price = price;
        this.v1 = v1;
        this.v2 = v2;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Parking() {
        this.name = "";
        this.adresse = "";
        this.description="";
        this.imgUrl="";
        this.price="";
        this.v1="";
        this.v2="";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
