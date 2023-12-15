package services;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Post;

import static io.restassured.RestAssured.given;

public class PostServices {
    static String Base_URL = "https://jsonplaceholder.typicode.com";
    static RequestSpecification req_spec;

    public Response getPost(){
        String endpoint = "/posts";
        return given().baseUri(Base_URL).log().all()
                .get(endpoint);
    }

    public Response getPostById(){
        String endpoint = "/posts/1";
        return given().baseUri(Base_URL).log().all()
                .get(endpoint);
    }

    public  Response postPost(Post post){
        String endpoint = "/posts";
        return given().baseUri(Base_URL).contentType(ContentType.JSON)
                .body(post).request(Method.POST,endpoint);
    }

    public Response putPostById(int postId, Post updatePost){
        String endpoint = "/posts/{postId}";
        return given().baseUri(Base_URL).contentType(ContentType.JSON)
                .body(updatePost).pathParam("postId",postId)
                .when().request(Method.PUT,endpoint);
    }

    public Response patchPostById(int postId, Post updatePost){
        String endpoint = "/posts/{postId}";
        return given().baseUri(Base_URL).contentType(ContentType.JSON)
                .body(updatePost).pathParam("postId",postId)
                .when().request(Method.PUT,endpoint);
    }

    public Response deletePostById(int postId){
        String endpoint = "/posts/{postId}";
        return given().baseUri(Base_URL)
                .pathParam("postId",postId)
                .when().request(Method.DELETE,endpoint);
    }

    public Response getCommentsForPost(int postId){
        String endpoint = "/posts/{postId}/comments";
        return given().baseUri(Base_URL)
                .pathParam("postId" , postId)
                .when().request(Method.GET,endpoint);
    }
}
