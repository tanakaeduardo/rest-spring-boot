package com.tanaka.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanaka.controllers.PersonController;
import com.tanaka.data.vo.v1.PersonVO;
import com.tanaka.exceptions.RequiredObjectIsNullException;
import com.tanaka.exceptions.ResourceNotFoundException;
import com.tanaka.mapper.ModelMapperImp;
import com.tanaka.model.Person;
import com.tanaka.repositories.PersonRepository;

@Service
public class PersonServices {
		
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<PersonVO> findAll() {
		
		logger.info("Finding all people!");
		
		var persons = ModelMapperImp.parseListObjects(repository.findAll(), PersonVO.class);
		
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		return persons;
	}
	
	public PersonVO findById(Long id) {
		
		logger.info("Finding one person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		
		PersonVO vo = ModelMapperImp.parseObject(entity, PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return vo;
	}
	
	public PersonVO create(PersonVO person) {
		
		if (person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one person!");
		
		var entity = ModelMapperImp.parseObject(person, Person.class);
		
		PersonVO vo = ModelMapperImp.parseObject(repository.save(entity),PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		
		if (person == null) throw new RequiredObjectIsNullException();

		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException ("No record found for this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		PersonVO vo = ModelMapperImp.parseObject(repository.save(entity),PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

		return vo;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException ("No record found for this ID"));
		
		repository.delete(entity);
	}

}
