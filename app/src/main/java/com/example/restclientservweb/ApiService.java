package com.example.restclientservweb;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/dsaApp/users/register2")
    Call<User> registerUser(@Body User user);

    @POST("/dsaApp/users/login2")
    Call<User> loginUser(@Body User user);

    @GET("/dsaApp/objects/Android")
    Call<List<Products>> getProducts();

   @GET("/dsaApp/users/{idUser}/dinero")
    Call<Integer> getDinero(@Path("idUser") String idUser);

    @POST("/dsaApp/users/{idUser}/products/{idProduct}")
    Call<Products> addProductToUser(@Path("idUser") String idUser, @Path("idProduct") int idProduct);

    @GET("/dsaApp/users/{id}/products")
    Call<List<Products>> getProductsOfUser(@Path("id") String idUser);



    @POST("/dsaApp/denuncias/denuncias2")
    Call<Denuncias> sendDenuncia(@Body Denuncias denuncia);

    @POST("/dsaApp/denuncias/denuncias2/{nombre}/{comentario}/{fecha}")
    Call<Denuncias> sendDenuncia_parametros(@Path("nombre") String nombre, @Path("comentario") String comentario, @Path("fecha") String fecha);

    @GET("/dsaApp/faq/PreguntasRespuestas")
    Call<List<FAQs>> getFAQs();

    @POST("/dsaApp/question/question2")
    Call<Question> sendQuestion(@Body Question question);
}
