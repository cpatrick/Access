package org.qibench.access;

import java.io.IOException;

import org.qibench.access.MidasApi.MidasCommunityListResponse;
import org.qibench.access.MidasApi.MidasCommunityListResponse.CommunityData;
import org.qibench.access.MidasApi.MidasFolderResponse;
import org.qibench.access.MidasApi.MidasItemResponse;
import org.qibench.access.MidasApi.MidasResourceListResponse;
import org.qibench.access.MidasApi.MidasResourceListResponse.FolderData;
import org.qibench.access.MidasApi.MidasResourceListResponse.ItemData;
import org.qibench.access.MidasApi.MidasVersionResponse;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String server = "http://midas3.kitware.com/midas";
        try {
            MidasVersionResponse verRes = MidasApi.version(server);
            System.out.println(verRes.stat);
            System.out.println(verRes.code);
            System.out.println(verRes.message);
            System.out.println(verRes.data.version);
            MidasCommunityListResponse res = MidasApi.communityList(server);
            for( CommunityData com  : res.data) {
                System.out.println(com.id);
                System.out.println(com.name);
            }
            int folderId = 6160;
            MidasResourceListResponse stuffList = MidasApi.folderChildren(server, folderId);
            for( FolderData folder : stuffList.data.folders) {
                System.out.println("*Folder*");
                System.out.println(folder.id);
                System.out.println(folder.name);
                MidasFolderResponse folderRes = MidasApi.folderGet(server, folder.id);
                System.out.println(folderRes.data.description);
            }
            for( ItemData item : stuffList.data.items) {
                System.out.println("*Item*");
                System.out.println(item.id);
                System.out.println(item.name);
                MidasItemResponse itemRes = MidasApi.itemGet(server, item.id);
                System.out.println(itemRes.data.description);
            }
            //System.out.println(res.data);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
