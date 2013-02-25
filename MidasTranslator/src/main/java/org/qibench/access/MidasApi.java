/**
 * 
 */
package org.qibench.access;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;


/**
 * @author cpatrick
 *
 */
public class MidasApi {

    public class MidasResponse {
        public String stat;
        public String code;
        public String message;
    }

    public class MidasVersionResponse extends MidasResponse {
        public class VersionData {
            public String version;
        }
        public VersionData data;
    }
    
    public class MidasCommunityListResponse extends MidasResponse {
        public class CommunityData {
            @SerializedName("community_id")
            public int id;
            public String name;
        }
        public ArrayList<CommunityData> data;
    }

    public class MidasResourceListResponse extends MidasResponse {
        public class FolderData {
            @SerializedName("folder_id")
            public int id;
            public String name;
        }
        public class ItemData {
            @SerializedName("item_id")
            public int id;
            public String name;
        }
        public class ResourceData {
            public ArrayList<FolderData> folders;
            public ArrayList<ItemData> items;
        }
        public ResourceData data;
    }

    public class MidasItemResponse extends MidasResponse {
        public class BitstreamData {
            @SerializedName("bitstream_id")
            public int id;
            public String name;
            public String checksum;
        }
        public class ItemRevisionData {
            @SerializedName("revision_id")
            public int id;
            public String changes;
            public ArrayList<BitstreamData> bitstreams;
        }
        public class ItemData {
            @SerializedName("item_id")
            public int id;
            public String name;
            public String description;
            public ArrayList<ItemRevisionData> revisions;
        }
        ItemData data;
    }

    public class MidasFolderResponse extends MidasResponse {
        public class FolderData {
            @SerializedName("folder_id")
            public int id;
            public String name;
            public String description;
        }
        FolderData data;
    }

    /**
     * The suffix to be appended to all server urls. This way the static
     * methods of this class can use the server base url for all requests.
     */
    public static final String suffix = "/api/json";

    /**
     * Basic request response function. All higher-level functions use this with
     * the exception of TODO
     * @param server the url of the server to access
     * @param httpMethod the HTTP method type to use (GET, POST, PUT, etc)
     * @param midasMethod the API method on the Midas Web API to call
     * @param params the parameter Map to pass to the Web API call
     * @param classOfT The type to return after JSON parsing
     * @return A POJO resulting from JSON deserialization
     * @throws IOException if something goes awry in the connection
     */
    public static MidasResponse makeRequest(String server, String httpMethod, String midasMethod,
            Map<String,String> params, Class<?> classOfT) throws IOException {
        String targetURL = server + suffix;
        URL url;
        HttpURLConnection connection = null;
        String urlParameters = "method=" + URLEncoder.encode(midasMethod, "UTF-8");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlParameters += "&" + entry.getKey() + "=" +
                URLEncoder.encode(entry.getValue(), "UTF-8");
        }
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(httpMethod);
            connection.setRequestProperty("Content-Type", 
                 "application/x-www-form-urlencoded");
                              
            connection.setRequestProperty("Content-Length", "" + 
                     Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");  
                              
            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                        connection.getOutputStream ());
            wr.writeBytes (urlParameters);
            wr.flush ();
            wr.close ();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer(); 
            while((line = rd.readLine()) != null) {
              response.append(line);
              response.append('\r');
            }
            rd.close();
            Gson gson = new Gson();
            //System.out.println(response.toString());
            return (MidasResponse) gson.fromJson(response.toString(), classOfT);

          } finally {

            if(connection != null) {
              connection.disconnect(); 
            }
          }
    }

    /**
     * Get the Midas version from the server
     * @param server web adress of the target Midas instance
     * @return a MidasVersion reponse that contains the version
     * @throws IOException if something goes wrong with the request
     */
    public static MidasVersionResponse version(String server) throws IOException {
        HashMap<String, String> empty = new HashMap<String, String>();
        return (MidasVersionResponse) makeRequest(server, "GET",
                "midas.version", empty, MidasVersionResponse.class);
    }

    /**
     * List all of the communities on the server
     * @param server the url of the target Midas instance
     * @return the list of communities in a light wrapper class
     * @throws IOException if something goes wrong with the request 
     */
    public static MidasCommunityListResponse communityList(String server) throws IOException {
        HashMap<String, String> empty = new HashMap<String, String>();
        MidasCommunityListResponse res = 
                (MidasCommunityListResponse) makeRequest(server, "GET",
                "midas.community.list", empty,
                MidasCommunityListResponse.class);
        return res;
    }

    /**
     * Get the children of the folder specified by id.
     * @param server the url of the target Midas instance
     * @param id the unique id of the target folder
     * @return a list of items and folders that are children of the target
     * folder (in a light wrapper)
     * @throws IOException if something goes wrong with the request
     */
    public static MidasResourceListResponse folderChildren(String server, int id) throws IOException {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", Integer.toString(id));
        MidasResourceListResponse res =
                (MidasResourceListResponse) makeRequest(server, "GET",
                "midas.folder.children", params,
                MidasResourceListResponse.class);
        return res;
    }

    public static MidasItemResponse itemGet(String server, int id) throws IOException {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", Integer.toString(id));
        MidasItemResponse res = (MidasItemResponse) makeRequest(server, "GET",
                "midas.item.get", params, MidasItemResponse.class);
        return res;
    }
    
    public static MidasFolderResponse folderGet(String server, int id) throws IOException {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", Integer.toString(id));
        MidasFolderResponse res = (MidasFolderResponse) makeRequest(server, "GET",
                "midas.folder.get", params, MidasFolderResponse.class);
        return res;
    }

}
