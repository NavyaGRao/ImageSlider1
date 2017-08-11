package com.example.navya.imageslider;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {

    // Declare Variables

    String[] rank;
    String[] country;
    String[] population;
    static Date dates = null , datee=null;
    int[] flag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        Button btnstart = (Button) findViewById(R.id.btnstart);
        Button btnstop = (Button) findViewById(R.id.btnstop);
        Button btnsubmit = (Button) findViewById(R.id.btnsubmit);

        // Generate sample data
      /*  rank = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        country = new String[] { "China", "India", "United States",
                "Indonesia", "Brazil", "Pakistan", "Nigeria", "Bangladesh",
                "Russia", "Japan" };

        population = new String[] { "1,354,040,000", "1,210,193,422",
                "315,761,000", "237,641,326", "193,946,886", "182,912,000",
                "170,901,000", "152,518,015", "143,369,806", "127,360,000" };

        flag = new int[] { R.drawable.china, R.drawable.india,
                R.drawable.unitedstates, R.drawable.indonesia,
                R.drawable.brazil, R.drawable.pakistan, R.drawable.nigeria,
                R.drawable.bangladesh, R.drawable.russia, R.drawable.japan };
*/


    }
    public void startclicked(View view)
    {

      //  flag=true;
        Calendar c=Calendar.getInstance();

        dates =c.getTime();


        // Notification starts after play button is pressed.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        builder.setAutoCancel(true);
        builder.setContentTitle("You Started Your Trip");
        builder.setContentText("Bon Voyage!!!");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(8,notification);

    }
    public void stopclicked (View view)
    {
        Toast.makeText(MainActivity.this,"Trip is over.Submit to view your event timeline", Toast.LENGTH_SHORT).show();
        Calendar c=Calendar.getInstance();
        //         SimpleDateFormat sdf=new SimpleDateFormat("dd,MMMM,YYYY,hh,mm,a");
        //            String endDate =sdf.format(c.getTime());
        datee=c.getTime();
    }
    public void submitclicked (View view)
    {
      /*  // Locate the ViewPager in activity_edit_data.xml
        viewPager = (ViewPager) findViewById(R.id.pager);
        // Pass results to ViewPagerAdapter Class

        adapter = new ViewPagerAdapter();
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);*/
        Intent intent = new Intent(MainActivity.this , EditDataActivity.class);
        startActivity(intent);
    }
    public static Date startDate()
    {
        return dates;
    }
    public static Date endDate()
    {
        return datee;
    }
}