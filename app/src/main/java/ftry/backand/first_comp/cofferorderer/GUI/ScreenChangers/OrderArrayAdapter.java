package ftry.backand.first_comp.cofferorderer.GUI.ScreenChangers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import ftry.backand.first_comp.cofferorderer.Common.IOnClickDeleter;
import ftry.backand.first_comp.cofferorderer.Common.IOnClickEditer;
import ftry.backand.first_comp.cofferorderer.Common.Order;
import ftry.backand.first_comp.cofferorderer.R;

/**
 * Created by User on 4/15/2016.
 */
public class OrderArrayAdapter extends ArrayAdapter<Order> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Order> data;
    private IOnClickDeleter mDeleter;
    private IOnClickEditer mEditer;


    public OrderArrayAdapter(Context context, int resource,  ArrayList<Order> data,IOnClickDeleter deleter,IOnClickEditer editer) {
        super(context, resource, data);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = data;
        mDeleter=deleter;
        mEditer=editer;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CoffeHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new CoffeHolder();
            holder.CoffeType = (TextView)row.findViewById(R.id.textCoffeItem);
            holder.Sugar = (TextView)row.findViewById(R.id.textSugarItem);
            holder.FreeText = (TextView)row.findViewById(R.id.textFreeTextItem);

            holder.DeleteButton=(Button)row.findViewById(R.id.btnDelete);
            holder.EditButton=(Button)row.findViewById(R.id.btnEdit);



            row.setTag(holder);
        }
        else
        {
            holder = (CoffeHolder)row.getTag();
        }
        Order order;
        try {
            order= data.get(position);
        }
        catch (Exception e)
        {
            order=data.get(0);
        }

        holder.CoffeType.setText(order.getCoffeType());
        holder.Sugar.setText(order.getSugar());
        holder.FreeText.setText(order.getFreeText());

        holder.DeleteButton.setHint("" + order.getID());
        holder.EditButton.setHint(""+order.getID());

        holder.DeleteButton.setOnClickListener(mDeleter);
        holder.EditButton.setOnClickListener(mEditer);

        return row;
    }
    static class CoffeHolder
    {
        TextView CoffeType;
        TextView Sugar;
        TextView FreeText;
        Button DeleteButton;
        Button EditButton;

    }
}
