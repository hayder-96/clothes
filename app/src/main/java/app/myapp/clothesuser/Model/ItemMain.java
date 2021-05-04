package app.myapp.clothesuser.Model;

public class ItemMain {

    private int id;
    private String name;
    private String image;
    private int drawable;

    public ItemMain(String name,int drawable) {
        this.name = name;
        this.drawable=drawable;
    }


    public ItemMain() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
