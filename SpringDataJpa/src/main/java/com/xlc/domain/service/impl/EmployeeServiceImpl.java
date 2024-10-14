package com.xlc.domain.service.impl;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xlc.domain.constant.GenderEnum;
import com.xlc.domain.entity.DepartmentPo;
import com.xlc.domain.entity.EmployeePo;
import com.xlc.domain.entity.QEmployeePo;
import com.xlc.domain.repository.DepartmentDao;
import com.xlc.domain.repository.EmployeeDao;
import com.xlc.domain.service.EmployeeService;
import com.xlc.domain.vo.DepartmentVo;
import com.xlc.domain.vo.EmployeeVo;
import com.xlc.domain.vo.Gender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;
    @Override
    public Long addOrUpdateEmployee(EmployeeVo employeeVo) {
        if (employeeVo.getId() == null) {
            // 增加
            EmployeePo employeePo = new EmployeePo();
            BeanUtils.copyProperties(employeeVo, employeePo);
            employeePo.setGender(JSONUtil.toBean(employeeVo.getGender(), Gender.class).getValue());
            employeePo.setDepartment(employeeVo.getDepartment().getId());
            return jpaQueryFactory.insert(QEmployeePo.employeePo).execute();
        } else {
            EmployeePo employeePo = jpaQueryFactory.selectFrom(QEmployeePo.employeePo)
                    .where(QEmployeePo.employeePo.id.eq(employeeVo.getId())).fetchOne();
            BeanUtils.copyProperties(employeeVo, employeePo);
            employeePo.setGender(JSONUtil.toBean(employeeVo.getGender(), Gender.class).getValue());
            employeePo.setDepartment(employeeVo.getDepartment().getId());
            employeeDao.save(employeePo);
            return employeePo.getId();
        }

    }

    @Override
    public EmployeeVo findEmployeeById(Long id) {
        EmployeePo employeePo = jpaQueryFactory.selectFrom(QEmployeePo.employeePo)
                .where(QEmployeePo.employeePo.id.eq(id)).fetchOne();
        EmployeeVo employeeVo = new EmployeeVo();
        BeanUtils.copyProperties(employeePo, employeeVo);
        ObjectMapper objectMapper = new ObjectMapper();
        String gender;
/*        try {
            gender = objectMapper.writeValueAsString(employeePo.getGender() == GenderEnum.MEN.getValue() ? GenderEnum.MEN : GenderEnum.WOMAN);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }*/
        // gender = JSONUtil.formatJsonStr();
        GenderEnum genderEnum = employeePo.getGender() == GenderEnum.MEN.getValue()
                ? GenderEnum.MEN : GenderEnum.WOMAN;
        Gender gender1 = new Gender(genderEnum.getNameEn(), genderEnum.getNameCn(), genderEnum.getValue());
        employeeVo.setGender(JSONUtil.toJsonStr(gender1));
        DepartmentPo departmentPo = departmentDao.findById(employeePo.getDepartment());
        DepartmentVo departmentVo = new DepartmentVo();
        BeanUtils.copyProperties(departmentPo, departmentVo);
        employeeVo.setDepartment(departmentVo);
        return employeeVo;
    }

    @Override
    public Map<String, Object> findEmployeeList(Integer pageNum, Integer pageSize) {
        BooleanExpression booleaBuilder = QEmployeePo.employeePo.gender.eq(1);
        List<EmployeePo> employeePoList = jpaQueryFactory.selectFrom(QEmployeePo.employeePo)
                .where(booleaBuilder).orderBy(QEmployeePo.employeePo.id.desc()).offset(pageNum - 1).limit(pageSize).fetch();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("employees", employeePoList);
        map.put("total", employeePoList.size());
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return map;
    }
}
