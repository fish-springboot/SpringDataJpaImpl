package com.github.fish56.springdatajpa.jpql;

import com.github.fish56.springdatajpa.entity.Monkey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 使用自定义SQL规则
 */
public interface MonkeyJPQL extends CrudRepository<Monkey, Long> {
    /**
     * 查询出所有的记录 select * from monkey
     * @return
     */
    @Query("select m from Monkey m")
    public List<Monkey> findAllMonkeyByQuery();

    /**
     * 查询名字以特定字符开头的记录
     * ?1 表明这是第一个参数的占位符，% 是SQL语句的
     * @param beginString
     * @return
     */
    @Query("select m from Monkey m where m.name like ?1%")
    public List<Monkey> findAllMonkeyNameBeginWith(String beginString);

    /**
     * 原生SQL语句，查询到结果后会做一次映射
     * @param id
     * @return
     */
    @Query(value = "select * from monkey where id > ?1", nativeQuery = true)
    public List<Monkey> findMonkeyWithNativeSQL(Long id);

    /**
     * 通过id删除一个元素，但是这个写法是有问题的，参考下一个问题
     * @param id
     * @return
     */
    @Query("delete from Monkey m where m.id = ?1")
    public boolean delById(Long id);

    /**
     * 如果我们试图修改数据库
     *   - 必须标注@Modifying 和 @Transactional
     *     否则会报出异常（参考测试用例）
     *
     *   - 同时返回值只能是void类型或者int类型
     *     否则会报出：org.springframework.dao.InvalidDataAccessApiUsageException: Modifying queries can only use void or int/Integer as return type!
     * @param id
     */
    @Transactional
    @Modifying
    @Query("delete from Monkey m where m.id = ?1")
    public void del_ById(Long id);

    /**
     * jpql不支持limit语句，如果想做limit的话，可以传递一个分页参数进去。（或者使用native语句）
     *
     * https://stackoverflow.com/questions/44565820
     */

}
