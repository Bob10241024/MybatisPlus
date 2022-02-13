package com.xixi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xixi.mapper.UserMapper;
import com.xixi.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static com.baomidou.mybatisplus.core.toolkit.ObjectUtils.isNotNull;

@SpringBootTest
public class WrapperTest {

    //继承了BaseMapper，所有的方法都来自于自己父类，我们也可以编写自己的扩展方法！
    @Autowired
    private UserMapper userMapper;

    // 查询name不为空的用户，并且邮箱不为空，年龄大于等于18
    @Test
    void contextLoads() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age",12);
        userMapper.selectList(wrapper).forEach(System.out::println); // 和我们刚才学习的map对比一下

    }

    // 查询名字
    @Test
    void test2(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","Estela");
        User user = userMapper.selectOne(wrapper); // 查询一个数据，出现多个结果使用List或者Mao
        System.out.println(user);
    }

    // 查询年龄在23 ~ 30岁之间的用户
    @Test
    void test3(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",20,30);// 区间
        Long count = userMapper.selectCount(wrapper);// 查询结果数
        System.out.println(count);
    }

    // 模糊查询
    @Test
    void test4(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .notLike("name","E")
                .likeRight("email","8");

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    // 子查询
    @Test
    void test5(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // id 在子查询中查出来
        wrapper.inSql("id","select id from user where id<3");

        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    //
    @Test
    void test6(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 通过id进行降序排序
        wrapper.orderByDesc("id");

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
