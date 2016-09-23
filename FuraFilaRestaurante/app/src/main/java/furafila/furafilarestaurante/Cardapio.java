package furafila.furafilarestaurante;

/**
 * Created by Silvio on 09/09/2015.
 */

public class Cardapio {

    int _id;
    String _name, _price, _image, _description;

    public Cardapio(){}

    public Cardapio(int id, String name, String price, String image, String description){
        this._id = id;
    this._name = name;
    this._price = price;
    this._image = image;
    this._description = description;
}

    public Cardapio(String name, String price, String image, String description){
        this._name = name;
        this._price = price;
        this._image = image;
        this._description = description;
    }

    public int getID(){
        return this._id;
    }
    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }
    public void setName(String name){
        this._name = name;
    }

    public String getPrice(){
        return this._price;
    }
    public void setPrice(String price){
        this._price = price;
    }

    public String getImage(){
        return this._image;
    }
    public void setImage(String image){
        this._image = image;
    }

    public String getDescription(){
        return this._description;
    }
    public void setDescription(String description){
        this._description = description;
    }
}
