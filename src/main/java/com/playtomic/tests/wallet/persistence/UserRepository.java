package com.playtomic.tests.wallet.persistence;

import com.playtomic.tests.wallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
