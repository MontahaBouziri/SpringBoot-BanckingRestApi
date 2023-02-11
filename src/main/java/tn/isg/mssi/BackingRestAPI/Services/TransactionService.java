package tn.isg.mssi.BackingRestAPI.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.isg.mssi.BackingRestAPI.Entities.Transaction;
import tn.isg.mssi.BackingRestAPI.Repositories.TransactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;

    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> TransactionList = transactionRepository.findAll();
        if(TransactionList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(TransactionList);
    }

    public ResponseEntity<?> getOneTransaction(Long id){
        Optional<Transaction> res = transactionRepository.findById(id);
        if(res.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the transaction with id = "+id+"is not found");
        }
        return ResponseEntity.ok(res.get());
    }

    public ResponseEntity<?> addOneTransaction(Transaction transaction){
        return ResponseEntity.ok(transactionRepository.save(transaction));
    }

    public ResponseEntity<?> deleteOneTransaction(Long id){
        Optional<Transaction> res = transactionRepository.findById(id);
        if(res.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the transaction with id = "+id+"doesn't exist");
        }
        transactionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("the transaction deleted successfully");
    }
}
