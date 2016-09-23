package furafila.request;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Listener for change state of the network
 * <p/>
 * <p/>
 * Created by macminilextrend on 3/6/15.
 */
public class NetworkStateReceiver extends BroadcastReceiver {

    protected List<NetworkStateReceiverListener> listeners;
    protected Boolean connected;

    private static boolean tres3;
    private static boolean wifi;

    public NetworkStateReceiver() {
        listeners = new ArrayList<NetworkStateReceiverListener>();
        connected = null;
    }


    public void onReceive(Context context, Intent intent) {

        if (intent == null || intent.getExtras() == null)
            return;

        ConnectivityManager connect = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        tres3 = connect.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
        wifi = connect.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();

        if (tres3 == true || wifi == true) {
            connected = true;
        } else {
            connected = false;
        }
        notifyStateToAll();

    }

    private void notifyStateToAll() {
        for (NetworkStateReceiverListener listener : listeners)
            notifyState(listener);
    }

    private void notifyState(NetworkStateReceiverListener listener) {
        if (connected == null || listener == null)
            return;

        if (connected == true)
            listener.networkAvailable();
        else
            listener.networkUnavailable();
    }

    public void addListener(NetworkStateReceiverListener l) {
        listeners.add(l);
        notifyState(l);
    }

    public void removeListener(NetworkStateReceiverListener l) {
        listeners.remove(l);
    }

    public interface NetworkStateReceiverListener {
        public void networkAvailable();

        public void networkUnavailable();
    }
}

