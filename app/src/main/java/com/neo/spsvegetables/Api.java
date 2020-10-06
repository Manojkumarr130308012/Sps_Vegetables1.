package com.neo.spsvegetables;

public class Api {
    String Local="https://neophrontech.com/spsvegetables.com/";
    String notification,notificationimage,rollofhonors;

    public Api() {

    }

    public String NotificationEndpoints(){
        notification=""+Local+"admin/get_announcements.php";
        return notification;
    }

    public String Rates(){
        notification=""+Local+"admin/get_products.php?keyword=1";
        return notification;
    }
    public String Ratessearch(String newText){
        notification=""+Local+"admin/get_product_search.php?keyword="+newText;
        return notification;
    }
    public String updatedate(){
        notification=""+Local+"admin/get_date.php";
        return notification;
    }
    public String Pushnoti(String token,String deviceud){
        notification=""+Local+"admin/get_id.php?push_id="+token+"&device_id="+deviceud;
        return notification;
    }
}
