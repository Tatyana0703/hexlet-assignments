package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> create(User userData) {
        Mono<User> user = userRepository.save(userData);
        return user;
    }

    public Mono<User> findById(long id) {
        Mono<User> user = userRepository.findById(id);
        return user;
    }

    public Mono<User> update(User userData, long id) {
        Mono<User> user = userRepository.findById(id);
        return user.map(v -> {
            v.setFirstName(userData.getFirstName());
            v.setLastName(userData.getLastName());
            v.setEmail(userData.getEmail());
            return v;
        }).flatMap(v -> userRepository.save(v));
    }

    public Mono<Void> delete(long id) {
        return userRepository.deleteById(id);
    }

    // END
}
