package com.human.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.human.dto.CustomerDto;
import com.human.dto.HobbyDto;
import com.human.vo.CustomerInfoVo;
import com.human.vo.CustomerInfoVo2;

@Service
public class CustomInfoServiceImpl implements ICustomInfoService {

	@Inject
	private IHobbyService hobbyService;
	@Inject
	private ICustomerService customerService;

	@Override
	public void insertCustomInfo(CustomerDto dto, CustomerInfoVo vo) throws Exception {
		// TODO Auto-generated method stub
		// 트렌젝션
		customerService.insert(dto);
		vo.setId(customerService.selectMaxId());// 다음시간 101를 읽어오는 sql작성
		System.out.println(dto);
		System.out.println(vo);

		if (vo.getHobbys() != null) {
			for (String hobby : vo.getHobbys()) {
				if (hobby.equals("")) {
					continue;
				}
				HobbyDto hDto = new HobbyDto();
				hDto.setId(vo.getId());
				hDto.setHobby(hobby);
				System.out.println(hDto);
				hobbyService.insert(hDto);
			}
		}

	}

	@Override
	public List<CustomerInfoVo> selectAll() throws Exception {
		List<CustomerInfoVo> customerInfoVos = new ArrayList<CustomerInfoVo>();

		List<CustomerDto> customerDtos = customerService.selectAll();
		System.out.println(customerDtos);

		if (customerDtos != null) {
			for (CustomerDto dto : customerDtos) {

				List<String> hobbys = hobbyService.selectId(dto.getId());
				customerInfoVos.add(new CustomerInfoVo(dto, hobbys));

			}
		}
		return customerInfoVos;
	}

	@Override
	public List<CustomerInfoVo2> selectAllCustomerHobby() throws Exception {
		// TODO Auto-generated method stub
		return customerService.selectAllCustomerHobby();
	}

}



