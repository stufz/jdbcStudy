package com.fz.dao.test;

import com.fz.dao.dao.ActorDAO;
import com.fz.dao.domain.Actor;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Author:fz
 * Date:2022/11/14 20:32
 */
public class TestDAO {
    //测试ActorDAO对表crud操作
    @Test
    public void testActorDAO() throws SQLException {
        ActorDAO actorDAO = new ActorDAO();
        //1.查询多行结果
        List<Actor> actors = actorDAO.queryMulti("select * from actor where id >=?", Actor.class, 1);
        System.out.println("查询多行结果为");
        for (Actor actor:actors){
            System.out.println(actor);
        }

        //2.查询单行结果
        Actor actor = (Actor) actorDAO.querySingle("select * from actor where id = ?",Actor.class,2);
        System.out.println("查询单行结果为");
        System.out.println(actor);

        //3.查询单行单列
        Object o = actorDAO.queryScalar("select name from actor where id=?",2);
        System.out.println("查询单行单列结果为");
        System.out.println(o);

        //4.dml操作 insert,delete,update
        int row = actorDAO.update("insert into actor values(null,?,?,?,?)","赵丽颖","女","1999-02-03","12345");
        System.out.println(row>0?"添加成功":"添加无效");




    }

}

