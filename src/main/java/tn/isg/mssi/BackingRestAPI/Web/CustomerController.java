package tn.isg.mssi.BackingRestAPI.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.isg.mssi.BackingRestAPI.Entities.Customer;
import tn.isg.mssi.BackingRestAPI.Services.CustomerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;


    @GetMapping("/")
    public ResponseEntity<?> getAllCustomers()
    {return service.getAllCustomers();}

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneCustomer(@PathVariable Long id){
        return service.getOneCustomer(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> addOneCustomer (@Valid @RequestBody Customer customer){
        return service.addCustomer(customer);
    }
//RequestParam
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneCustomer(@PathVariable Long id){
        return service.removeCustomer(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCustomer(@PathVariable Long id,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String familyName,
                            @RequestParam(required = false) String cin
                            ) {
        return service.updateCustomer(id,name,familyName,cin);


    }
//@RequestParam(required = false) LocalDate birthDate



}
