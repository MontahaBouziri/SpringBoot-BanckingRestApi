package tn.isg.mssi.BackingRestAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.isg.mssi.BackingRestAPI.Entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
