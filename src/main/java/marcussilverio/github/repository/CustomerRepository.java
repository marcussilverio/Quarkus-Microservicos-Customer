package marcussilverio.github.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import marcussilverio.github.entity.CustomerEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<CustomerEntity> {

}
