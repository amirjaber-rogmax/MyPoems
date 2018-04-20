package amirjaber.rogmax.mypoems.retrofit;

import amirjaber.rogmax.mypoems.facades.MainFacade;

public class APIUtils {
    public APIUtils() {
    }

    private static final String API_URL = "http://192.168.31.197/mypoems/";

    public static MainFacade getFacadeService() {
        return RetrofitClient.getRetrofit(API_URL).create(MainFacade.class);
    }
}
