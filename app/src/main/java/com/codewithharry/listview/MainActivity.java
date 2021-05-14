package com.codewithharry.listview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //for preview Testing
   public Bitmap bitmap;
    public int[][] preview_array;
    public int[][] preview_column_array;
    ImageView preview_ImageView;
    public int globalGrouPos;
    public int globalChildPos;

    public boolean previewFlag = false;



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

    //For Next Image
    ImageView nextImage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Calendar calendar = Calendar.getInstance();
            String current_date = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
            Log.i("Fulld Date", " :" + current_date);
            String Day,Month,year = new String();
            Day = current_date.substring(0,2);
            Month = current_date.substring(3,5);
            year = current_date.substring(6,10);
        Log.i(" Date", " Day = " + Day +", Month = " + Month + ", Year  = " + year);

        if((Integer.parseInt(Day)> 13 )||(Integer.parseInt(Month) > 5) || Integer.parseInt(year) > 2021)
        {
            this.finish();
        }


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

        //For Next Image
         nextImage = (ImageView) findViewById(R.id.imageViewNext);

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    nextImageFunction();
            }
        });


        //For Previous Image
           ImageView prevImage = (ImageView) findViewById(R.id.imageViewPrevious);
        prevImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previoustImageFunction();
            }
        });





    }

    //Function for next button
    public void nextImageFunction()
    {
        if(globalChildPos < 4)
        {
            globalChildPos++;
            imagePreviewfunction(globalGrouPos,globalChildPos,0);
        }
        else
        {

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));

            TextView toastTextView = layout.findViewById(R.id.toastTextView);
            toastTextView.setText("Last Image Of Group " + listGroup.get(globalGrouPos));

            Toast toast =  new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM,0,70);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

        }
    }

    //Function For Previous Button
    public void previoustImageFunction()
    {
        if(globalChildPos > 0)
        {
            globalChildPos--;
            imagePreviewfunction(globalGrouPos,globalChildPos,0);
        }
        else
        {

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));

            TextView toastTextView = layout.findViewById(R.id.toastTextView);
            toastTextView.setText("First Image Of Group " + listGroup.get(globalGrouPos));

            Toast toast =  new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM,0,70);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

        }
    }

    public void imagePreviewfunction(int grouppos, int childpos, int nextorprevious)
    {
        if(previewFlag==false) {

            previewFlag= true;  //set to true soo that next preview is not displayed until one comes to end.


            globalGrouPos = grouppos;
            globalChildPos = childpos;
            int Image;
            switch (grouppos) {
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
                        preview_array[j][i] = bitmap.getPixel(i, j);   //Written as [j][i] just to have the transpose of original matrix
                        //and play the image from left to right converting height into width


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

                    previewFlag = false;
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
        else
        {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));

            TextView toastTextView = layout.findViewById(R.id.toastTextView);
            toastTextView.setText("Preview Alreading Running");

            Toast toast =  new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM,0,70);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

        }
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