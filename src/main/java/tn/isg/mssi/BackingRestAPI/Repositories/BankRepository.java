package tn.isg.mssi.BackingRestAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.isg.mssi.BackingRestAPI.Entities.Bank;

public interface BankRepository extends JpaRepository<Bank,Integer> {
}
