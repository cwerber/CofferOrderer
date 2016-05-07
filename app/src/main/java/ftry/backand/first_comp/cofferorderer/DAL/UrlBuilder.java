package ftry.backand.first_comp.cofferorderer.DAL;

import java.io.Serializable;

/**
 * Created by User on 4/25/2016.
 */
public class UrlBuilder implements Serializable {

    public String BuildInsert(String coffeyType,String sugar,String freeText,int id)
    {
        String url="https://api.backand.com/1/query/data/InsertOrder?parameters=%7B%22CoffeeType%22:%22";
        url+=coffeyType;
        url+="%22,%22Sugar%22:%22";
        url+=sugar;
        url+="%22,%22FreeText%22:%22";
        url+=freeText;
        url+="%22,%22Room_FK%22:%22";
        url+=id;
        url+="%22%7D";
        return url;

    }
    public String BuildGetForRoom(int roomId)
    {
        return "https://api.backand.com/1/query/data/GetAllForRoom?parameters=%7B%22Roomid%22:%22"+roomId+"%22%7D";
    }
    public String BuildValidateRoom(String roonName,String Password)
    {
        return "https://api.backand.com/1/query/data/ValidateRoom?parameters=%7B%22room_name%22:%22"+roonName+"%22,%22password%22:%22"+Password+"%22%7D";
    }


}
