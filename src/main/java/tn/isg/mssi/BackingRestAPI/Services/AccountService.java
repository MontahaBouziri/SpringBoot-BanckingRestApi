package tn.isg.mssi.BackingRestAPI.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.isg.mssi.BackingRestAPI.Entities.Account;
import tn.isg.mssi.BackingRestAPI.Repositories.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    /****************************************************************************************/
    //il va retourner :
    // soit une liste de Account
    // soit une liste vide
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> listAccounts = accountRepository.findAll();
        if (listAccounts.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listAccounts);
    }

    /****************************************************************************************/

    //ResponseEntity<?> point d'interrogation parcequ'il ne sais pas quel type d'objet il va retourner :
    // soit une chaine ("this user does not exist") dans le body
    // soit un Account à l'intérieure d'un Optional
    public ResponseEntity<?> getOneAccount(Long id){
        Optional<Account> res = accountRepository.findById(id);
        if(res.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("this user does not exist");
        }
        return ResponseEntity.ok(res.get());
    }

    /****************************************************************************************/

    public ResponseEntity<?> addOneAccount(Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(  accountRepository.save(account)  );
    }

    /****************************************************************************************/

    public ResponseEntity<?> deleteOneAccount(Long id) {
        Optional<Account> res = accountRepository.findById(id);
        if(res.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("account with id = "+id+"does not exist");
        }
        accountRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted succesfully");
    }

    /****************************************************************************************/

/*
* public ResponseEntity<?> updateOneAccount(Long id, Account account) {
        Optional<Account> res = accountRepository.findById(id);
        if(res.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("account with id = "+id+"does not exist");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(  accountRepository.save(account)  );

    }
    * */

}
