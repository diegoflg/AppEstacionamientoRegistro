package pe.edu.esan.appestacionamientoregistro;


/**
 * Created by Diegoflg on 7/28/2015.
 */
public class ItemData {

    String text;
    Integer imageId;
    public ItemData(String text, Integer imageId){
        this.text=text;
        this.imageId=imageId;
    }

    public String getText(){
        return text;
    }

    public Integer getImageId(){
        return imageId;
    }
}
