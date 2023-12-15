import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import models.Comment;
import models.Post;
import org.testng.annotations.Test;
import services.PostServices;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

public class PostTest {
    PostServices postServices = new PostServices();
    Post post = new Post();
    static ResponseSpecification res_spec;
    @Test
    public void getPostTest(){

        Response response = postServices.getPost();
        assertEquals(response.getStatusCode(),200 ,"StatusCode is incorrect");
        List<Post> posts = response.jsonPath().getList(".",Post.class);
        assertNotNull(posts, "List of posts should not be null ");
        assertFalse(posts.isEmpty(),"List of posts should not be empty");


    }
    @Test
    public void getPostById(){
       Response response = postServices.getPostById();
       assertEquals(response.getStatusCode(),200 ,"StatusCode is incorrect");
       String title = response.jsonPath().getString("title");
       assertEquals(title,"sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
       String body = response.jsonPath().getString("body");
       assertTrue(body.length() >0 , "Post body should not be empty");

    }

    @Test
    public void postPostTest(){
        post.setUserId(1);
        post.setTitle("New Post Title");
        post.setBody("This is the body of new post");

        Response response = postServices.postPost(post);
        assertEquals(response.getStatusCode(),201,"Status Code should be 201 created");
        Post createdPost = response.as(Post.class);
        assertNotNull(createdPost.getId(),"Post Id should not be null");
        assertEquals(createdPost.getTitle(),post.getTitle(),"Incorrect Post Title");
        assertEquals(createdPost.getBody(),post.getBody(),"Incorrect Post Body");
    }

    @Test
    public void putPostByIdTest(){
        int postId=1;
//        Post updatedPost = new Post();
        post.setTitle("Updated Post Title");
        post.setBody("This is updated body of the post");
        Response response = postServices.putPostById(postId , post);
        assertEquals(response.getStatusCode(),200,"Status Code should be 200 OK");
        Post updatedPostResponse = response.as(Post.class);
        assertNotNull(updatedPostResponse.getId(),"Post Id should not be null");
        assertEquals(updatedPostResponse.getTitle(),post.getTitle());
        assertEquals(updatedPostResponse.getBody(),post.getBody(),"Incorrect updated Post Body");

    }

    @Test
    public void patchPostByIdTest(){
        int postId=1;
//        Post postFields = new Post();
        post.setTitle("Updated Post Title");
        post.setBody("This is updated body of the post");
        Response response = postServices.patchPostById(postId , post);
        assertEquals(response.getStatusCode(),200,"Status Code should be 200 OK");
        Post patchedPost = response.as(Post.class);
        assertNotNull(patchedPost.getId(),"Post Id should not be null");
        assertEquals(patchedPost.getTitle(),post.getTitle());
        assertEquals(patchedPost.getBody(),post.getBody(),"Incorrect updated Post Body");

    }

    @Test
    public void deletePostByIdTest(){
        int postId=1;
        Response response = postServices.deletePostById(postId);
        assertEquals(response.getStatusCode(),200,"Status Code should be 200 OK");
        assertFalse(response.getBody().asString().isEmpty(), "Response Body should be empty for successful deletion");
    }

    @Test
    public void getCommentsForPostTest() {
        int postId = 1;
        Response response = postServices.getCommentsForPost(postId);
        assertEquals(response.getStatusCode(),200,"Status Code should be 200 OK");
        Comment[] comments = response.as(Comment[].class);
        assertNotNull(comments,"List of comments should not be null");
        assertTrue(comments.length>0 , "List of comments should not be empty");
    }
}
