import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.response.Response;
import models.Album;
import models.Post;
import models.Todos;
import models.User;
import org.testng.annotations.Test;
import services.UserServices;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class UserTest {
  UserServices userServices = new UserServices();

  @Test
  public void getAllUserTest(){
    Response response = userServices.getAllUsers();
    assertEquals(response.getStatusCode(),200,"Status Code should be 200 OK");
    User[] users = response.as(User[].class);
    assertNotNull(users,"List of users should not be null");
    assertTrue(users.length >0 ,"List of user should not be empty");
  }

  @Test
    public void getUserByIdTest(){
      int userId = 1 ;
      Response userResponse = userServices.getUserById(userId);
      assertEquals(userResponse.getStatusCode(),200,"Status Code should be 200 OK");
      User user = userResponse.as(User.class);
      assertNotNull(user.getId(), "User Id should not be null");
      assertEquals(user.getId(), userId , "Incorrect user id");
  }

  @Test
  public  void postUserTest(){
    User newUser = new User();
    newUser.setName("yashasvi Paunikar");
    newUser.setUsername("yashiP");
    newUser.setEmail("yashi@gmail.com");
    Response response =userServices.postUser(newUser);
    assertEquals(response.getStatusCode(),201,"Status Code should be 201 OK");
    User createdUser = response.as(User.class);
    assertNotNull(createdUser.getId(),"UserId should not be null");
    assertEquals(createdUser.getName(),newUser.getName(),"Incorrect user name");
    assertEquals(createdUser.getUsername(),newUser.getUsername(),"Incorrect username");
    assertEquals(createdUser.getEmail(),newUser.getEmail(),"Incorrect Email");

  }

  @Test
  public void putUserByIdTest(){
    int userId = 2 ;
    User UpdatedUser = new User();
    UpdatedUser.setName("Yashi");
    UpdatedUser.setUsername("Yami");
    Response response =userServices.putUserById(userId,UpdatedUser);
    assertEquals(response.getStatusCode(),200,"Status Code should be 200 OK");
    User updatedUserResponse = response.as(User.class);
    assertNotNull(UpdatedUser.getId(),"UserId should not be null");
    assertEquals(updatedUserResponse.getName(),UpdatedUser.getName(),"Incorrect user name");
    assertEquals(updatedUserResponse.getUsername(),UpdatedUser.getUsername(),"Incorrect user name");
  }

  @Test
  public void deleteUserByIdTest(){
    int userId =1 ;
    Response response =userServices.deleteUserById(userId);
    assertEquals(response.getStatusCode(),200,"Status Code should be 200 OK");
  }



  @Test
    public void  getUserPostsTest(){
      int userId = 1 ;
      Response postResponse = userServices.getUserPosts(userId);
      assertEquals(postResponse.getStatusCode(),200,"Status Code should be 200 OK");
      Post[] userPosts = postResponse.as(Post[].class);
      assertNotNull(userPosts,"List of user posts should not be null");
      assertTrue(userPosts.length>0 , "List of user post should not be empty");
  }

  @Test
     public void getAlbumsForUserTest(){
      int userId = 1 ;
      Response response = userServices.getAlbumsForUser(userId);
      assertEquals(response.getStatusCode(),200,"Status Code should be 200 OK");
      Album[] albums = response.as(Album[].class);
      assertNotNull(albums,"List of albums should not be null");
      assertTrue(albums.length>0 , "List of albums should not be empty");
  }

  @Test
    public void getTodosForUserTest(){
      int userId = 1 ;
      Response response = userServices.getTodosForUser(userId);
      assertEquals(response.getStatusCode(),200,"Status Code should be 200 OK");
      Todos[] todos = response.as(Todos[].class);
      assertNotNull(todos,"List of todos should not be null");
      assertTrue(todos.length>0 , "List of todos should not be empty");

  }
}
