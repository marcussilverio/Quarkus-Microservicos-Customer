package marcussilverio.github.service;

import marcussilverio.github.dto.CustomerDto;
import marcussilverio.github.entity.CustomerEntity;
import marcussilverio.github.repository.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class CustomerService {
  @Inject
  CustomerRepository repository;
  @Inject
  Validator validator;
  public CustomerDto findCustomerById(Long id){
    return new CustomerDto(repository.findById(id));
  }
  public List<CustomerDto> findAllCustomers(){
    return repository.findAll().stream().map(CustomerDto::new).collect(Collectors.toList());
  }
  public void createNewCustomer (CustomerDto customerDto) throws Exception {

    Set<ConstraintViolation<CustomerDto>> violations = validator.validate(customerDto);
    if(!violations.isEmpty())
      throw new Exception("Constraint violation error");
    repository.persist(new CustomerEntity(customerDto));
  }

  public void changeCustomer(Long id, CustomerDto dto) throws Exception{
    CustomerEntity customer = repository.findById(id);
    if(customer == null)
      throw new Exception("Not Found");
    Set<ConstraintViolation<CustomerDto>> violations = validator.validate(dto);
    if(!violations.isEmpty())
      throw new Exception("Constraint violation error");

    customer.setAddress(dto.getAddress());
    customer.setAge(dto.getAge());
    customer.setName(dto.getName());
    customer.setPhone(dto.getPhone());
    customer.setEmail(dto.getEmail());
  }
  public void deleteCustomer(Long id){
    repository.deleteById(id);
  }
}
