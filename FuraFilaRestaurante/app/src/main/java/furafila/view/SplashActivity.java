package furafila.view;

/**
 * Created by Silvio on 09/09/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import java.io.IOException;
import java.sql.SQLException;

import furafila.furafilarestaurante.R;


public class SplashActivity extends Activity {

    private boolean _active = true;
    private int _splashTime = 2; // TIME CONTROL IN SECONDS




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


       /* new Thread() {
            @Override
            public void run() {
                int waited = 0;
                try {
                    do {
                        this.sleep(100);
                    } while (waited++ < (_splashTime * 10) && _active);
                } catch (InterruptedException e) {
                } finally {
                    finish();
                    startActivity(new Intent("furafila.furafilarestaurante.app"));
                }
            }
        }.start();*/
    }



    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;

    }
}
