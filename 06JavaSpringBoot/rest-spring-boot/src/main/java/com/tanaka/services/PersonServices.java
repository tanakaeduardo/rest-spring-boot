package com.tanaka.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanaka.data.vo.v1.PersonVO;
import com.tanaka.data.vo.v2.PersonVOV2;
import com.tanaka.exceptions.ResourceNotFoundException;
import com.tanaka.mapper.DozerMapper;
import com.tanaka.mapper.custom.PersonMapper;
import com.tanaka.model.Person;
import com.tanaka.repositories.PersonRepository;

@Service
public class PersonServices {
		
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;
	
	public List<PersonVO> findAll() {
		
		logger.info("Finding all people!");
		
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO findById(Long id) {
		
		logger.info("Finding one person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		
		logger.info("Creating one person!");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		
		return DozerMapper.parseObject(repository.save(entity),PersonVO.class);
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		
		logger.info("Creating one personV2!");
		
		var entity = mapper.convertVOEntity(person);
		
		return mapper.convertEntityToVO(repository.save(entity));
	}
	
	public PersonVO update(PersonVO person) {
		
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException ("No record found for this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return DozerMapper.parseObject(repository.save(entity),PersonVO.class);
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException ("No record found for this ID"));
		
		repository.delete(entity);
	}

}
