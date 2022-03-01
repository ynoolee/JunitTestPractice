package com.example.test.test.learnjava.optional;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalTest {

  private UserRepository userRepository;

  private User u0;
  private User u1;
  private User u2;
  private User u3;

  @BeforeEach
  public void setUp(){
    userRepository = new UserRepository();
    u0 = new User("first",11);
    u1 = new User("second",21);
    u2 = new User("third",23);
    u3 = new User("fourth",10);
    userRepository.insert(u0);
    userRepository.insert(u1);
    userRepository.insert(u2);
    userRepository.insert(u3);
  }

  @Test
  public void 비어있는_옵셔널_반환시_map_호출(){
    Assertions.assertThrows(NoSuchElementException.class,
            ()-> userRepository.findById(4L)
                    .map(UserDto::new)
                    .get().getName());
  }

  @Test
  public void 비어있지않은_옵셔널로부터_map_호출(){
    // given
    Assertions.assertDoesNotThrow(
            () -> userRepository.findById(0L)
                    .map(user -> new UserDto(user))
    );
    // when
    String findName = userRepository.findById(0L)
            .map(UserDto::new)
            .get().getName();
    // then
    Assertions.assertEquals("first", findName);
  }

  @Test
  public void 나이가_20살이상이면_필터링된다(){
    org.assertj.core.api.Assertions.assertThat(
            userRepository.findById(1L)
                    .filter(user -> user.getAge() >= 20 ))
            .hasValue(u1);
  }


  /**
   * 아래 두 메소드는 같은 역할을 하는 메소드.
   * filter 를 사용, 연쇄 호출을 사용하여 조금 더 가독성 있는 코드로 만들고자 하였다
   * */
  private Optional<User> getUserIfAgeAbove(User user, int age){
    if ( user != null && user.getAge() > age){
      return Optional.of(user);
    }
    return Optional.empty();
  }

  private Optional<User> getUserIfAgeAbove2(User user, int age){
    return Optional.ofNullable(user)
            .filter(u -> u.getAge() > age);
  }


  public class UserDto{

    private String name;
    private int age;

    public UserDto(User user) {
      this.name = user.getName();
      this.age = user.getAge();
    }
    public String getName() {
      return name;
    }
  }

}
