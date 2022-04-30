package com.rsin.js_api_ar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;

public class MainActivity extends AppCompatActivity {
    private ArchitectView architectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.architectView = (ArchitectView)this.findViewById( R.id.architectView );
        final ArchitectStartupConfiguration config = new ArchitectStartupConfiguration();
        config.setLicenseKey("bcngLdgSHhb08xMQ4Mc2P2FCboN3/erNDvGvDiYlp+nzbdD7M2rETBzl6bE0w/su5wRoN436imXecQqhSDrLHltOkyjOEnNMkHBm2b5w739uPkNb6RSExkMN45c5rzTelVIAntREQFS51soOtyu3AfcdsrvPHd+KMriw2ppYWo5TYWx0ZWRfX7T/1FWkBpOjo+tsxo+BhBOU3asMrK575/4oyC7IMqzvPU/6u0Jmj5l4h1n+os7j8mT4/EbgdGnqw153/uik5irwCtvjdysXrFOt9PjMT3zFGeHO0wFJdWRpLijG/oi4x4AhD4kN2aHDHef8gQE36ZfEu+EGMQO4VOxEb0RgdqSlKR4OAbenPI/+CJcNuy9uE4xMJKi/m3U5OEpvBYh/X+4ztMe7iRkptiEFIpsesH3cqcJxUvoTEO84Sxt96mUcbr15ood676tSYDkBNcDLfoKiM+XI3PsY5UPSbICCpVjsdqaZcRECE38NHRAK8OJCbh/ACFu5+C0V4844MdGiC/oPyHOVPQPX+4N5SKS0xS5lYmx731c6wNmJWl8kXwS5iKKb7xkloeLn+2FoTYdGbDIDPh6q2V5h5UR0dYAfxizKcXmSdmgDDjnTJDGYra1GbfDoSAdyxKn+U4YOEfW+rXltbIZCajlvuq2B8Gn0bl5XYF5IbIoa/k8/YsOH9LMUcI6nGsKA/LPMEsoAn+T38K1BIQJHxmLhGSwfRCtZWFrfzDYUN/gjhcSewaRqCcvG6jhWftjlP9Mx5x6h7lkpWUiGFqkr5QNfPVPy7O/iQoSl22BzQIpFLYhYyFUATHklR9N/EowGtAkeLii+u+giWqnRnf0ClNiHlMR3vXe/Ie4t2tl5crzw4tw=");
        this.architectView.onCreate( config );
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        architectView.onPostCreate();
        try {
//            this.architectView.load("file:///android_asset/test_image_on_target/index.html");
            this.architectView.load("file:///android_asset/test_image_on_target/index.html");
//            this.architectView.load("file:///android_asset/07_3dModels_1_3dModelOnTarget/index.html");
//            this.architectView.load("http://yoursid.me/11_Video_1_SimpleVideo/");
        }catch (Exception e)
        {
            Toast.makeText(this, "error "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        architectView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        architectView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        architectView.onPause();
    }
}