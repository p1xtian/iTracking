package local.itracking.interfaces;

import java.util.List;

import local.itracking.models.Client;
import local.itracking.models.Email;
import local.itracking.models.Order;
import local.itracking.models.User;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceRetrofit {

    //List Users

    @GET("usuarios")
    Call<List<User>> ListUsersGet();

    @POST("usuarios")
    Call<List<User>> ListUsersPost();

    //Save User
    @POST("registrarUsuario")
    Call<User> SaveUser(@Body User user);

    //Login
    @GET("usuarios/{email}/{password}")
    Call<User> Login(@Path("email") String email, @Path("password") String password);

    //Login
    @GET("usuario/buscaremail/{email}")
    Call<User> EmailExits(@Path("email") String email);

    //List Clients
    @GET("clientes")
    Call<List<Client>> ListClientsGet();

    //Save Client
    @POST("registrarCliente")
    Call<Client> SaveClient(@Body Client client);

    //List Orders
    @GET("pedidos")
    Call<List<Order>> ListOrdersGet();

    //List One Order
    @GET("pedido/{id}")
    Call<Order> LoadOrder(@Path("id") String id);

    //Save Client
    @POST("registrarPedido")
    Call<Order> SaveOrder(@Body Order order);

    //Login
    @GET("cliente/buscar/{razon}")
    Call<Client> Login(@Path("razon") String razon);

    //List Email
    @GET("enviar/{json}")
    Call<Void> SendEmail(@Path("json") String json);

    //Update Order
    @PUT("pedido/{id}")
    Call<Order> UpdateOrder(@Path("id") String id, @Body Order order);
}

