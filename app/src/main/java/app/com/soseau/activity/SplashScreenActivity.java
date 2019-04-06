package app.com.soseau.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import app.com.soseau.R;


public class SplashScreenActivity extends FragmentActivity
{
    protected int _splashTime = 3000;
    private Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        run();
    }

    private void run() {

        splashTread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    synchronized (this)
                    {
                        wait(_splashTime);
                    }
                }
                catch (InterruptedException e)
                {

                }
                finally
                {

                    runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            Utils.Util.intentActivity(SplashScreenActivity.this, SplashScreenActivity.this, MainActivity.class);
                            finish();

                        }
                    });
                }
            }

        };
        splashTread.start();
    }

}
