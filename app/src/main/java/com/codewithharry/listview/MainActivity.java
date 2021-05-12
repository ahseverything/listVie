package com.codewithharry.listview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //for preview Testing
   public Bitmap bitmap;
    public int[][] preview_array;
    public int[][] preview_column_array;
    ImageView preview_ImageView;



    ListView listView;
    String mTitle[] = {"Fire","Rainbow","Flow","Sparkle","Love","Pulse","Fade","Travel","Beats","Water" };
    int[] imagea = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5};
    int[] imageb = {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5};
    int[] imagec = {R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5};
    int[] imaged = {R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5};
    int[] imagee = {R.drawable.e1, R.drawable.e2, R.drawable.e3, R.drawable.e4, R.drawable.e5};
    int[] imagef = {R.drawable.f1, R.drawable.f2, R.drawable.f3, R.drawable.f4, R.drawable.f5};
    int[] imageg = {R.drawable.g1, R.drawable.g2, R.drawable.g3, R.drawable.g4, R.drawable.g5};
    int[] imageh = {R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5};
    int[] imagei = {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5};
    int[] imagej = {R.drawable.j1, R.drawable.j2, R.drawable.j3, R.drawable.j4, R.drawable.j5};
                      //Images are present in the drawable folder



//For expandable


    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String, List<Integer>> listItem;
    String stringarray[] = {"Fire","Rainbow","Flow","Sparkle","Love","Pulse","Fade","Travel","Beats","Water" };
    Mainadapter adapter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        expandableListView = findViewById(R.id.Expandable_ListView);
        listGroup = new ArrayList<String>();
        listItem = new HashMap<>();

        adapter = new Mainadapter(this, listGroup, listItem);
        expandableListView.setAdapter(adapter);


        iniListdata();


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.i("GroupPosition", "GroupPosition: " + groupPosition);
                Log.i("ChildPosition", "Childposition" + childPosition);

                imagePreviewfunction(groupPosition,childPosition,0);


                return true;
            }
        });

        //for testing of preview

    }

    public void imagePreviewfunction(int grouppos, int childpos, int nextorprevious)
    {
        int Image;
        switch(grouppos)
        {
            case 0:

                Image = imagea[childpos];
                break;

            case 1:
                Image = imageb[childpos];
                break;
            case 2:
                Image = imagec[childpos];
                break;
            case 3:
                Image = imaged[childpos];
                break;
            case 4:
                Image = imagee[childpos];
                break;
            case 5:
                Image = imagef[childpos];
                break;
            case 6:
                Image = imageg[childpos];
                break;
            case 7:
                Image = imageh[childpos];
                break;
            case 8:
                Image = imagei[childpos];
                break;
            case 9:
                Image = imagej[childpos];
                break;
            default:
                Image = imagea[0];




        }

        preview_array = new int[200][200];
        preview_column_array = new int[200][1];
        bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), Image);
        preview_ImageView = findViewById(R.id.imageViewPreview);


        Log.i("width = ", " width" + bitmap.getWidth());
        Log.i("hight", "height" + bitmap.getHeight());

        try {


            for (int i = 0; i < bitmap.getWidth(); i++) {
                for (int j = 0; j < bitmap.getHeight(); j++) {
                    //This is a great opportunity to filter the ARGB values
                    preview_array[i][j] = bitmap.getPixel(i, j);


                }
            }
        } catch (Exception e) {
            Log.i("error", "error" + e.getMessage());
        }


        try {


            new CountDownTimer(10000, 50) {
                int j = 0;
                Bitmap bt = Bitmap.createBitmap(200, 1, Bitmap.Config.ARGB_8888);

                public void onFinish() {

                }

                public void onTick(long millisUntilFinished) {
                    for (int i = 0; i < bitmap.getWidth(); i++) {

                        //This is a great opportunity to filter the ARGB values
                        bt.setPixel(i, 0, preview_array[i][j]);


                    }

                    if (j < 200) {
                        j++;
                    }
                    preview_ImageView.setImageBitmap(bt);

                }

            }.start();


        } catch (Exception e) {
            Log.i("Error ", "error= " + e.getMessage());
        }
        //Funtion Imagrpreview End
    }


    private void iniListdata() {
        listGroup.add(getString(R.string.Fire));
        listGroup.add(getString(R.string.Rainbow));
        listGroup.add(getString(R.string.Flow));
        listGroup.add(getString(R.string.Sparkle));
        listGroup.add(getString(R.string.Love));
        listGroup.add(getString(R.string.Pulse));
        listGroup.add(getString(R.string.Fade));
        listGroup.add(getString(R.string.Travel));
        listGroup.add(getString(R.string.Beats));
        listGroup.add(getString(R.string.Water));

        int[] array;

        List<Integer> list1 = new ArrayList<>();
        array = imagea;
        for(int item : array)
        {
            list1.add(item);
        }


        List<Integer> list2 = new ArrayList<>();
        array =  imageb;
        for(int item : array)
        {
            list2.add(item);
        }

        List<Integer> list3 = new ArrayList<>();
        array = imagec;
        for(int item : array)
        {
            list3.add(item);
        }

        List<Integer> list4 = new ArrayList<>();
        array = imaged;
        for(int item : array)
        {
            list4.add(item);
        }

        List<Integer> list5 = new ArrayList<>();
        array = imagee;
        for(int item : array)
        {
            list5.add(item);
        }

        List<Integer> list6 = new ArrayList<>();
        array = imagef;
        for(int item : array)
        {
            list6.add(item);
        }

        List<Integer> list7 = new ArrayList<>();
        array = imageg;
        for(int item : array)
        {
            list7.add(item);
        }

        List<Integer> list8 = new ArrayList<>();
        array = imageh;
        for(int item : array)
        {
            list8.add(item);
        }

        List<Integer> list9 = new ArrayList<>();
        array = imagei;
        for(int item : array)
        {
            list9.add(item);
        }

        List<Integer> list10 = new ArrayList<>();
        array = imagej;
        for(int item : array)
        {
            list10.add(item);
        }




        listItem.put(listGroup.get(0),list1);
        listItem.put(listGroup.get(1),list2);
        listItem.put(listGroup.get(2),list3);
        listItem.put(listGroup.get(3),list4);
        listItem.put(listGroup.get(4),list5);
        listItem.put(listGroup.get(5),list6);
        listItem.put(listGroup.get(6),list7);
        listItem.put(listGroup.get(7),list8);
        listItem.put(listGroup.get(8),list9);
        listItem.put(listGroup.get(9),list10);


        adapter.notifyDataSetChanged();





     }
}