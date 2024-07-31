package com.xlc.mapper;

import com.xlc.pojo.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 注意pom文件加载resources
 * mapper 和 xml映射写法 参考
 * https://blog.csdn.net/Renren151/article/details/117334658?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_utm_term~default-1-117334658-blog-127321090.235^v43^pc_blog_bottom_relevance_base5&spm=1001.2101.3001.4242.2&utm_relevant_index=4
 *
 * */
public interface UserMapper {
    List<Employee> selectAll();

}
