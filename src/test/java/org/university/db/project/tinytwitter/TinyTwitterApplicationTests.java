package org.university.db.project.tinytwitter;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.university.db.project.tinytwitter.dao.BlogMapper;
import org.university.db.project.tinytwitter.entity.Blog;

import javax.annotation.Resource;

@SpringBootTest(classes = BlogMapper.class)
@MapperScan("org.university.db.project.tinytwitter.mapper")
//@ComponentScan("org.university.db.project.tinytwitter")
class TinyTwitterApplicationTests {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    void contextLoads() {
        blogMapper.insert(new Blog() {{
            setTitle("title");
        }});
    }

}
