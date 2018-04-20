package amirjaber.rogmax.mypoems.facades;


import amirjaber.rogmax.mypoems.models.PoemValue;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MainFacade {

    @FormUrlEncoded
    @POST("insert.php")
    Call<PoemValue> insert(@Field("name") String name,
                           @Field("poem") String poem);

    @GET("view.php")
    Call<PoemValue> view();

}
