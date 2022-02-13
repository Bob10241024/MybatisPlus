package com.xixi;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xixi.mapper.UserMapper;

import com.xixi.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    //继承了BaseMapper，所有的方法都来自于自己父类，我们也可以编写自己的扩展方法！
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        // 参数是一个Wrapper ，条件构造器 ，这里我们先不用 null
        // 查询全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    // 测试插入
    @Test
    public void testInsert(){
        User user = new User();
        user.setAge(23);
        user.setName("Estela");
        user.setEmail("cxqlovezc@163.com");

        int result = userMapper.insert(user);// 帮助我们自动生成id
        System.out.println(result);// 受影响的行数
        System.out.println(user);// 发现，id会自动回填
    }

    // 测试更新
    @Test
    public void testUpdate(){
        User user = new User();

        // 通过条件自动拼接动态sql
        user.setId(1492352477373116420L);
        user.setEmail("zclovecxq@163.com");

        // 注意：updateById 但是参数是一个对象！
        int i =userMapper.updateById(user);
        System.out.println(i);
    }

    // 测试乐观锁成功
    @Test
    public void testOptimisticLocker(){
        // 1 查询用户信息
        User user = userMapper.selectById(1L);
        // 2 修改用户信息
        user.setName("千面");
        user.setEmail("12345678@qq.com");
        // 3 执行更新操作
        userMapper.updateById(user);
    }

    // 测试乐观锁失败
    @Test
    public void testOptimisticLocker2(){
       // 线程 1
        User user = userMapper.selectById(1L);
        user.setName("千面1111");
        user.setEmail("12345678@qq.com");

        // 模拟另外一个线程执行力插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("千面222");
        user2.setEmail("87654321@qq.com");
        userMapper.updateById(user2);

        // 自选锁来多次尝试提交
        userMapper.updateById(user);  // 如果没有乐观锁就会覆盖插队线程的值


    }

    // 测试查询
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    // 测试批量查询
    @Test
    public void testSelectBatchId(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    // 按条件查询之一 使用map操作
    @Test
    public void testSelectByBatchIds(){
        HashMap<String, Object> map = new HashMap<>();
        // 自定义要查询
        map.put("name","Jack");

        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);

    }

    // 测试分页查询
    @Test
    public void testPage(){
        // 参数一：当前页
        // 参数二：页面大小
        // 使用了分页插件之后，所有的分页操作也变得简单了！！！
        Page<User> page = new Page<>(1,3);
        userMapper.selectPage(page,null);

        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("总页数：" + page.getPages());
        System.out.println("记录数：" + page.getTotal());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());

    }

    // 测试删除
    @Test
    public void testDeleteById(){
        userMapper.deleteById(1L);
    }

    // 通过id批量删除
    @Test
    public void testDeleteBatchId(){
        userMapper.deleteBatchIds(Arrays.asList(1492352477373116418L,1492352477373116419L));
        System.out.println("删除成功！！！");
    }

    // 通过map删除
    @Test
    public void testDeleteMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","Estela");
        userMapper.deleteByMap(map);
        System.out.println("删除成功！！！");
    }
}
