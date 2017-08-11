package com.example.navya.imageslider;

import android.content.Context;
import android.content.Intent;
import android.media.ExifInterface;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class EditDataActivity extends AppCompatActivity {

    Date sd = null , ed = null;
    ViewPager viewPager;
    PagerAdapter adapter;
    private String selectedName;
    private int selectedID;
    private String selectedDate;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        viewPager = (ViewPager) findViewById(R.id.pager);


       Intent receivedIntent=getIntent();
        selectedID=receivedIntent.getIntExtra("id",-1);
        selectedName=receivedIntent.getStringExtra("name");
        selectedDate=(MainActivity.startDate()+"&"+MainActivity.endDate());

        String[] temp=selectedDate.split("&");
        SimpleDateFormat sdf=new SimpleDateFormat();


        sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

        try {
            //converts the string date to date format in order to use the inbuilt function
            sd = sdf.parse(temp[0]);
            // Toast.makeText(EditDataActivity.this," "+sd, Toast.LENGTH_SHORT).show();
            ed = sdf.parse(temp[1]);
            // Toast.makeText(EditDataActivity.this," "+sd, Toast.LENGTH_SHORT).show();

        }
        catch(ParseException e)
        {
            Log.e("Activity"," "+e);
            e.printStackTrace();
        }

        // Locate the ViewPager in activity_edit_data.xml


        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();
//tracks the path where the camera images are stored
        String targetPath = ExternalStorageDirectoryPath + "/DCIM/Camera";

        //lists the files in that path
        File targetDirector = new File(targetPath);
        File[] files = targetDirector.listFiles();
        int l = files.length;
        Toast.makeText(this,l,Toast.LENGTH_SHORT).show();
        int flag[] = new int[l];
        Arrays.sort(files, Collections.reverseOrder());
        int k = 0;
        final String file_path[] = new String[l];

     //   viewPager = (ViewPager) findViewById(R.id.pager);
      //  adapter = new ViewPagerAdapter(this , targetPath);
      //  viewPager.setAdapter(adapter);
        //int j=0;
        for (int i = 0; i < l; i++) {

            ExifInterface intf = null;

            try {
                //ExifInteface is a class for reading and writing exif tags in a jpeg file or image file

                intf = new ExifInterface(files[i].getName());
                if (intf != null) {

                    Date lastModDate = new Date(files[i].lastModified());

                    if (lastModDate.compareTo(sd) >= 0 && lastModDate.compareTo(ed) <= 0) {
                       // flag[i] = 1;
                       // imageAdapter.add(files[i].getAbsolutePath());

                        file_path[k] = files[i].getAbsolutePath();
                        adapter = new ViewPagerAdapter(context , files[i].getAbsolutePath(), l , file_path);
                        //              Toast.makeText(getApplicationContext(), file_path[k], Toast.LENGTH_LONG).show();
                        k++;
                    } else if ((files[i].getName()).charAt(0) != 'V')
                        break;

                }

            } catch (IOException e) {

            }
            if (intf == null) {
                //try {
                Date lastModDate = new Date(files[i].lastModified());
                String s = lastModDate.toString();
                // Date date1=new SimpleDateFormat("dd,MMMM,YYYY,hh,mm,a").parse(sd);
                //  Date date2=new SimpleDateFormat("dd,MMMM,YYYY,hh,mm,a").parse(ed);

                     /* SimpleDateFormat sdf = new SimpleDateFormat("dd,MMMM,YYYY,hh,mm,a");
                      lastModDate = sdf.parse(s);*/

                //  Toast.makeText(getApplicationContext(), " compared" + (lastModDate.compareTo(sd) >= 0 && lastModDate.compareTo(ed) <= 0), Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(), " " + s + " compared" + sd + " " + ed, Toast.LENGTH_LONG).show();
                if (lastModDate.compareTo(sd) >= 0 && lastModDate.compareTo(ed) <= 0) {
                    flag[i] = 1;
                   // imageAdapter.add(files[i].getAbsolutePath());

                    file_path[k] = files[i].getAbsolutePath();
                    adapter = new ViewPagerAdapter(this , files[i].getAbsolutePath() ,l  , file_path);
                    //        Toast.makeText(getApplicationContext(), file_path[k], Toast.LENGTH_LONG).show();
                    k++;

                } else if ((files[i].getName()).charAt(0) != 'V')
                    break;


            }

        }




        // Pass results to ViewPagerAdapter Class

        //adapter = new ViewPagerAdapter(this , );
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);
    }
}
