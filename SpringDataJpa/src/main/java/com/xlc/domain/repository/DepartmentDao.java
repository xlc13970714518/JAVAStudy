package com.xlc.domain.repository;

import com.xlc.domain.entity.DepartmentPo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * jpa关键词查询是通过方法名称关键字的搭配，底层生成sql的方式来实现与数据库的交互，其关键词的搭配又很多方式，
 * 基本能覆盖表查询的所又情况，其余关键字查询可以自行百度；
 *
 *jpa同样支持写sql语句来操作数据，sql 有两种呈现形式：
 * JPQL 形式的 sql 语句，from 后面是以类名呈现的。
 * 原生的 sql 语句，需要使用 nativeQuery = true 指定使用原生 sql
 *
 * */

public interface DepartmentDao extends JpaRepository <DepartmentPo, Object>{

    Long deleteById(Integer id);

    DepartmentPo findById(Long id);
}
