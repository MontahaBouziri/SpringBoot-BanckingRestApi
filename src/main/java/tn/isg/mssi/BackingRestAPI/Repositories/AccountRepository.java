package tn.isg.mssi.BackingRestAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.isg.mssi.BackingRestAPI.Entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
}
