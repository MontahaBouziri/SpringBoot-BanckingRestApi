package tn.isg.mssi.BackingRestAPI.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.isg.mssi.BackingRestAPI.Entities.Transaction;
import tn.isg.mssi.BackingRestAPI.Services.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/")
    public ResponseEntity<?> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneTransaction(@PathVariable Long id){
        return transactionService.getOneTransaction(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> postOneTransaction(@RequestParam Transaction transaction){
        return transactionService.addOneTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneTransaction(@PathVariable Long id){
        return transactionService.deleteOneTransaction(id);
    }

}
