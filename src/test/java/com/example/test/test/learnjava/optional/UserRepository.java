package com.example.test.test.learnjava.optional;

import java.util.*;

public class UserRepository {
  private final Map<Long,User> storage;
  private Long seq;

  public UserRepository() {
    storage = new HashMap<>();
    seq = 0L;
  }
  public void insert(User user){
    storage.put(seq++ , user);
  }

  public Optional<User> findById(Long userId){
    return Optional.ofNullable(storage.get(userId));
  }
  
  public Optional<List<User>> findAll(){
    return Optional.ofNullable(new ArrayList<>(storage.values()));
  }

}
