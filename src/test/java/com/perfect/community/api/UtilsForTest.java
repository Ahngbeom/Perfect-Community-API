package com.perfect.community.api;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.entity.post.PostEntity;
import com.perfect.community.api.mapper.post.PostMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Random;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/securityContext.xml",
        "file:web/WEB-INF/interceptor-servlet.xml"
})
public class UtilsForTest {

    @Autowired
    private PostMapper postMapper;

    public String generateRandomTitle() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String generateRandomContents() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 100;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public void addDummyPosts(long boardNo, int amount) {
        PostDTO postDTO;
        while (amount-- > 0) {
            postDTO = PostDTO.builder()
                    .boardNo(boardNo)
                    .type("normal")
                    .writer("admin")
                    .title(generateRandomTitle())
                    .contents(generateRandomContents())
                    .build();
            postMapper.insertPost(postDTO);
        }
    }

    @Test
    void test() {
        addDummyPosts(2, 10);
        addDummyPosts(3, 10);
    }

}
