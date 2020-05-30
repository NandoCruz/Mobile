package br.clima;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {
    public static String converterData(String dataUTC) {
        String dataConvertida = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = df.parse(dataUTC);
            df.setTimeZone(TimeZone.getDefault());
            df = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
            dataConvertida = df.format(date);
        } catch (Exception e) {
            dataConvertida = dataUTC + " (UTC)";
        }
        return dataConvertida;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo == null) return false;
        if (!netInfo.isConnected()) return false;
        if (!netInfo.isAvailable()) return false;
        return true;
    } // fim do isOnline


}
