package pac.example.com.mapapplication;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


/**
 * Created by Yassine on 11/02/2018.
 */

public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved;
    ArrayList<Parking> parkings = new ArrayList<>();

    /*
 PASS DATABASE REFRENCE
  */
    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }
    //WRITE IF NOT NULL
    public Boolean save(Parking parking)
    {
        if(parking == null)
        {
            saved=false;
        }else
        {
            try
            {
                db.child("Parking").push().setValue(parking);
                saved=true;

            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }

        return saved;
    }

    //IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchData(DataSnapshot dataSnapshot)
    {
        parkings.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Parking spacecraft=ds.getValue(Parking.class);
            parkings.add(spacecraft);
        }
    }

    //RETRIEVE
    public ArrayList<Parking> retrieve()
    {
            db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return parkings;
    }


}