package com.bitc.jpatest.service;

import com.bitc.jpatest.data.dto.EmployeesDto;
import com.bitc.jpatest.data.entity.EmployeesEntity;
import com.bitc.jpatest.data.entity.Gender;
import com.bitc.jpatest.data.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeesServiceImpl implements EmployeesService {

  private final EmployeesRepository employeesRepository;

  @Override
  public void find() throws Exception {
    Optional<EmployeesEntity> emp = employeesRepository.findByEmpNo(10001);
    List<EmployeesEntity> emps = employeesRepository.findAllByGender(Gender.valueOf("F"));

    int femaleCnt = employeesRepository.countByGender(Gender.valueOf("F"));
    int maleCnt = employeesRepository.countByGender(Gender.valueOf("M"));
  }

  @Override
  public EmployeesEntity getEmployeeMemberInfo(int empNO) {
    Optional<EmployeesEntity> empInfo = employeesRepository.findByEmpNo(10001);
    if (empInfo.isPresent()) {
      EmployeesEntity emp = empInfo.get();
      return emp;
    } else {
      return null;
    }
  }

  @Override
  public List<EmployeesEntity> getEmployeesMemberList(String firstName) {
    List<EmployeesEntity> empList = employeesRepository.findAllByFirstName(firstName);
    return empList;
  }

  @Override
  public EmployeesDto getMemberInfoEmpNo(int empNo) {
    Optional<EmployeesEntity> empInfo = employeesRepository.findByEmpNo(empNo);
    if (empInfo.isPresent()) {
      EmployeesDto empMember = new EmployeesDto();
      empMember.setEmpNo(empInfo.get().getEmpNo());
      empMember.setEmpName(empInfo.get().getFirstName() + empInfo.get().getLastName());
      empMember.setGender(empInfo.get().getGender());
      return empMember;
    } else {
      return null;
    }
  }

  @Override
  public List<EmployeesDto> getMemberInfoListEmpName(String empName) {
    List<EmployeesDto> memberList = new ArrayList<>();

    List<EmployeesEntity> empList = employeesRepository.findAllByFirstName(empName);

    for (EmployeesEntity e : empList) {
      EmployeesDto member = new EmployeesDto();
      member.setEmpNo(e.getEmpNo());
      member.setEmpName(e.getFirstName() + e.getLastName());
      member.setGender(e.getGender());
      memberList.add(member);
    }

    return memberList;
  }
}
