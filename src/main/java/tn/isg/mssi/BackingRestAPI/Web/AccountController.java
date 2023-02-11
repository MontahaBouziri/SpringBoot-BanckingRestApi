package tn.isg.mssi.BackingRestAPI.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.isg.mssi.BackingRestAPI.Entities.Account;
import tn.isg.mssi.BackingRestAPI.Services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /*
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }*/

    @GetMapping("/")
    public ResponseEntity<?> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneAccount (@PathVariable Long id) {
        return accountService.getOneAccount(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> postOneAccount(@RequestParam Account account){
        return accountService.addOneAccount(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneAccount (@PathVariable Long id) {
        return accountService.deleteOneAccount(id);
    }





}
