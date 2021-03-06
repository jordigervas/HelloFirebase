package testpg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class FCMNotification {

	// Method to send Notifications from server to client end.
		public final static String AUTH_KEY_FCM = "API_KEY_HERE";
		public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
		
		public final static String USER_DEVICE_TOKEN = "USER_DEVICE_TOKEN";

		public static void pushFCMNotification(String DeviceIdKey) throws Exception {

		    String authKey = AUTH_KEY_FCM; // You FCM AUTH key
		    String FMCurl = API_URL_FCM;

		    URL url = new URL(FMCurl);
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		    conn.setUseCaches(false);
		    conn.setDoInput(true);
		    conn.setDoOutput(true);

		    conn.setRequestMethod("POST");
		    conn.setRequestProperty("Authorization", "key=" + authKey);
		    conn.setRequestProperty("Content-Type", "application/json");
		    
		    JSONObject data = new JSONObject();
		    
		    data.put("to", DeviceIdKey.trim());
		    
		    
		    JSONObject info = new JSONObject();
		    
		    //notification message
		    //info.put("title", "FCM Notification Title"); // Notification title
		    //info.put("body", "Hello First Test notification"); // Notification body
		    //data.put("notification", info);
		    
		    //data message
		    info.put("title", "FCM Data Title"); // Notification title
		    info.put("body", "Hello First Test data"); // Notification body
		    data.put("data", info);

		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(data.toString());
		    wr.flush();
		    wr.close();

		    int responseCode = conn.getResponseCode();
		    System.out.println("Response Code : " + responseCode);

		    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String inputLine;
		    StringBuffer response = new StringBuffer();

		    while ((inputLine = in.readLine()) != null) {
		        response.append(inputLine);
		    }
		    System.out.println("Response : " + response);
		    in.close();

		}

		@SuppressWarnings("static-access")
		public static void main(String[] args) throws Exception {
		    FCMNotification obj = new FCMNotification();
		    obj.pushFCMNotification(
		            USER_DEVICE_TOKEN);
		}

}
