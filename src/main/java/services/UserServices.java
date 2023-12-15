package services;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import models.User;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class UserServices {
    static String Base_URL = "https://jsonplaceholder.typicode.com";
    static String Base_Path ="/users";
    static String endpoint = "/{userId}";

    public Response getAllUsers(){
        return given().baseUri(Base_URL)
                .when().get(Base_Path);
    }
    public Response getUserById(int userId){

        return given().baseUri(Base_URL)
                .pathParam("userId", userId)
                .when().get(Base_Path + endpoint);
    }

    public Response postUser(User newUser){
        return given().baseUri(Base_URL).contentType(ContentType.JSON)
                .body(newUser).when().post(Base_Path);


    }

    public Response putUserById(int userId , User updatedUser){
        return given().baseUri(Base_URL).contentType(ContentType.JSON)
                .body(updatedUser).pathParam("userId", userId)
                .when().request(Method.PUT, Base_Path+endpoint);
    }

    public Response deleteUserById(int userId){
        return given().baseUri(Base_URL).pathParam("userId", userId)
                .when().request(Method.DELETE, Base_Path+endpoint);
    }

    public Response getUserPosts(int userId){

        return given().baseUri(Base_URL)
                .pathParam("userId", userId)
                .when().get(Base_Path + endpoint +"/posts");
    }

    public Response getAlbumsForUser(int userId){
        return given().baseUri(Base_URL)
                .pathParam("userId", userId)
                .when().get(Base_Path + endpoint +"/albums");
    }

    public Response getTodosForUser(int userId){
        return given().baseUri(Base_URL)
                .pathParam("userId", userId)
                .when().get(Base_Path + endpoint +"/todos");
    }

}
