package tn.isg.mssi.BackingRestAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.isg.mssi.BackingRestAPI.Entities.Customer;
import tn.isg.mssi.BackingRestAPI.Repositories.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository repository;


    public CustomerService(CustomerRepository customerRepository){
        this.repository=customerRepository;
    }
    //private static final Log LOG = LogFactory.getLog(CustomerService.class)
    /*public List<Customer> getAll() {
        return repository.findAll();*/



    /******************************************************************************************/
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> listeCustomers = repository.findAll();
        if (listeCustomers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listeCustomers);
    }


    /******************************************************************************************/
    public ResponseEntity<?> getOneCustomer(Long id){
        Optional<Customer> res = repository.findById(id);
        if (res.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the customer with id = "+id+"is not found");
        }
        return ResponseEntity.ok(res.get());
    }


    /******************************************************************************************/
    //example of Stream use :
    //return a Set containing the triple of even integer into the list 11,4,3,8,6,15
    //Stream.of(11,4,3,8,6,15)
    //.filter(x->x%2==0) //returns a stream containing 4, 8 and 6.
    //.filter(x->x<8) //returns a stream containing 4 and 6
    //.map(x->3*x) //returns a stream containing 12 and 18
    //.collect(Collectors.toSet())
    public ResponseEntity<?> addCustomer(Customer customer){
        List<Customer> listeCustomers = repository.findAll().stream()
                                        .filter(c -> c.getCin().equals(customer.getCin())).collect(Collectors.toList());
        if (!listeCustomers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("this customer cin already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(customer));
    }



    /******************************************************************************************/
    public ResponseEntity<?> removeCustomer(Long id) {
        Optional<Customer> res = repository.findById(id);
        if (res.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the user with the id = "+id+"does not exist");
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("The customer with id ="+ id+" was successfully deleted");
    }



    /******************************************************************************************/
    @Transactional
    public ResponseEntity<Customer> updateCustomer(Long id,
                               String name,
                               String familyName,
                               String cin){
        Customer customer = repository.findById(id).orElseThrow(    ()-> new IllegalStateException ("customer with id = "+id+"does not exist")  );



        if (   name!=null
                && name.length()>0
                && !Objects.equals(customer.getName(),name)
                && familyName!=null
                && familyName.length()>0
                && !Objects.equals(customer.getFamilyName(),familyName)
                && cin!=null
                && cin.length()==8
                && !Objects.equals(customer.getCin(),cin)
                )
        {


            List<Customer> listeCustomers = repository.findAll().stream().filter(c-> c.getCin().equals(cin)).collect(Collectors.toList());
            if (!listeCustomers.isEmpty()) {
                throw new IllegalStateException("cin taken");
            }
            else {
                customer.setName(name);
                customer.setFamilyName(familyName);
                customer.setCin(cin);
                repository.save(customer);
            }

        }

        return ResponseEntity.ok(customer);


    }
}
/*
        if (name!=null && name.length()>0 && !Objects.equals(customer.getName(),name)) {
            customer.setName(name);
        }

        if (familyName!=null && familyName.length()>0 && !Objects.equals(customer.getFamilyName(),familyName)) {
            customer.setFamilyName(familyName);
        }

        if (cin!=null && cin.length()==8 && !Objects.equals(customer.getCin(),cin)) {
            List<Customer> listeCustomers = repository.findAll().stream().filter(c-> c.getCin().equals(cin)).collect(Collectors.toList());
            if (!listeCustomers.isEmpty()) {
               throw new IllegalStateException("cin taken");
            }
            customer.setCin(cin);
        }
*/





