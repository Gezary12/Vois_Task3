import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class JsonPlaceholderTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void printCommentsForRandomPost() {
        // get all posts
        List<Map<String, Object>> posts = given()
                .get("/posts")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");

        // pick random post id
        int randomPostId = (int) posts.get(new Random().nextInt(posts.size())).get("id");
        System.out.println("Random post ID = " + randomPostId);

        // get comments for that post
        List<Map<String, Object>> comments = given()
                .queryParam("postId", randomPostId)
                .get("/comments")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");

        // display comments
        comments.forEach(c -> {
            System.out.println("- id=" + c.get("id") + " | name=" + c.get("name") + " | email=" + c.get("email"));
            System.out.println("  body: " + c.get("body"));
        });

        Assert.assertTrue(comments.size() >= 0);
    }

    @Test
    public void albumTitleLengthDoesNotExceed300() {
        int userId = 3;

        List<Map<String, Object>> albums = given()
                .queryParam("userId", userId)
                .get("/albums")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");

        Assert.assertFalse(albums.isEmpty(), "Expected albums for userId=" + userId);

        albums.forEach(album -> {
            String title = String.valueOf(album.get("title"));
            Assert.assertTrue(title.length() <= 300,
                    "Album title exceeded 300 characters. Length: " + title.length());
        });
    }

    @Test
    public void createPostForUser() {
        Map<String, Object> body = Map.of(
                "userId", 3,
                "title", "Automated test title",
                "body", "This is the body created by Rest Assured test"
        );

        Response postResp = given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        Assert.assertEquals(postResp.jsonPath().getInt("userId"), 3);
        Assert.assertEquals(postResp.jsonPath().getString("title"), body.get("title"));
        Assert.assertEquals(postResp.jsonPath().getString("body"), body.get("body"));
    }

    @Test
    public void printIncompleteTodosTitlesForUser() {
        int userId = 5;

        List<Map<String, Object>> todos = given()
                .queryParam("userId", userId)
                .queryParam("completed", false)
                .get("/todos")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");

        todos.forEach(t -> System.out.println(" - " + t.get("title")));
    }
}
