package ftry.backand.first_comp.cofferorderer.Common;

import java.io.Serializable;

/**
 * Created by User on 4/15/2016.
 */
public class Order implements Serializable {




    private int ID;
    private String CoffeType;
    private String Sugar;
    private String FreeText;

    public Order(int id,String coffeType, String sugar, String freeText) {
        ID=id;
        CoffeType = coffeType;
        Sugar = sugar;
        FreeText = freeText;
    }

    public String getCoffeType() {
        return CoffeType;
    }

    public void setCoffeType(String coffeType) {
        CoffeType = coffeType;
    }

    public String getSugar() {
        return Sugar;
    }

    public void setSugar(String sugar) {
        Sugar = sugar;
    }

    public String getFreeText() {
        return FreeText;
    }

    public void setFreeText(String freeText) {
        FreeText = freeText;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        return this.ID == order.ID;

    }




}
