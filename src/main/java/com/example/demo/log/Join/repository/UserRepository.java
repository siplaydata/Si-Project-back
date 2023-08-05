package com.example.demo.log.Join.repository;

import com.example.demo.log.Join.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 여기에 필요한 추가적인 메서드를 선언할 수 있습니다.
    // Spring Data JPA는 메서드 이름을 분석하여 해당하는 쿼리를 자동으로 생성합니다.
    // 예를 들어, findByUsername(String username) 메서드를 선언하면, username을 기준으로 DB에서 해당 유저를 찾는 쿼리를 자동으로 생성합니다.

    // 회원가입 메서드
    User save(User user);

    // 유저 아이디로 유저를 조회하는 메서드
    User findByUserid(String userid);

    // 필요한 추가적인 메서드들을 여기에 추가할 수 있습니다.
    // 예를 들어, 아이디 중복 여부를 확인하는 메서드를 추가로 작성할 수 있습니다.
    boolean existsByUserid(String userid);
}
