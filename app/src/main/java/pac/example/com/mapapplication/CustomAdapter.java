package pac.example.com.mapapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Yannick on 11/02/2018.
 */

public class CustomAdapter extends BaseAdapter{
    Context c;
    ArrayList<Parking> parkings;

    public CustomAdapter(Context c, ArrayList<Parking> parkings) {
        this.c = c;
        this.parkings = parkings;
    }

    @Override
    public int getCount() {
        return parkings.size();
    }

    @Override
    public Object getItem(int position) {
        return parkings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.listview_layout,parent,false);
        }

        //TextView nameTxt= (TextView) convertView.findViewById(R.id.textView);

        final Parking p= (Parking) this.getItem(position);

        //nameTxt.setText(p.getName());

        //ONITECLICK
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,p.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}