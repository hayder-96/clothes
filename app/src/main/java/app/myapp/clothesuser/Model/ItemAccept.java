package app.myapp.clothesuser.Model;

public class ItemAccept {



    private int id;
    private int accept_id;
    private String name;
    private String price;
    private String number;
    private String image;
    private String color;
    private String numsize;
    private int clothes_id;



    public ItemAccept(int id,String name, String price, String number, String image,String color,int clothes_id,String numsize) {
        this.id = id;
        this.color=color;
        this.name = name;
        this.price = price;
        this.number = number;
        this.image = image;
        this.clothes_id=clothes_id;
        this.numsize=numsize;
    }


    public ItemAccept(String name, String price, String number, String image,String color,int clothes_id,String numsize) {
        this.name = name;
        this.price = price;
        this.number = number;
        this.image = image;
        this.color=color;
        this.clothes_id=clothes_id;
        this.numsize=numsize;
    }

    public int getId() {
        return id;
    }

    public int getAccept_id() {
        return accept_id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getNumber() {
        return number;
    }

    public String getImage() {
        return image;
    }

    public String getColor() {
        return color;
    }

    public int getClothes_id() {
        return clothes_id;
    }


    public String getNumsize() {
        return numsize;
    }
}
