package tn.isg.mssi.BackingRestAPI.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.isg.mssi.BackingRestAPI.Entities.Bank;
import tn.isg.mssi.BackingRestAPI.Services.BankService;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    private BankService bankService;

    @GetMapping("/")
    public ResponseEntity<?> getAllBanks(){
        return bankService.getAllBanks();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOneBank(@PathVariable Integer id) {
        return bankService.getOneBank(id);
    }


    @PostMapping("/")
    public ResponseEntity<?> postOneBank(@RequestParam Bank bank){
        return bankService.postOneBank(bank);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneBank(@PathVariable Integer id){
        return bankService.deleteOneBank(id);
    }
}
