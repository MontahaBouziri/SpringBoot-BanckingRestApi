package tn.isg.mssi.BackingRestAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.isg.mssi.BackingRestAPI.Entities.Bank;
import tn.isg.mssi.BackingRestAPI.Repositories.BankRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public ResponseEntity<List<Bank>> getAllBanks(){
        List<Bank> listBanks = bankRepository.findAll();
        if (listBanks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listBanks);
    }


    public ResponseEntity<?> getOneBank(Integer id) {
        Optional<Bank> res = bankRepository.findById(id);
        if(res.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the Bank with th id = "+id+"does not exist");
        }
        return ResponseEntity.ok(res.get());
    }


    public ResponseEntity<?> postOneBank(Bank bank) {
        List<Bank> bankList = bankRepository.findAll().stream().filter(b->b.getEmail().equals(bank.getEmail()) )
                              .collect(Collectors.toList());
        if(!bankList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("this email already exists");
        }
        return ResponseEntity.ok(bankRepository.save(bank));
    }


    public ResponseEntity<?> deleteOneBank(Integer id){
        Optional<Bank> res = bankRepository.findById(id);
        if (res.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the user with the id = "+id+"does not exist");
        }
        bankRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("The customer with id ="+ id+" was successfully deleted");
    }





}
