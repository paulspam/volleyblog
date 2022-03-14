import com.softserveinc.ita.javaclub.volleyblog.model.User;

import java.util.List;

public interface UserService {
    public User findById(Integer id);

    public List<User> findAll();

    public User saveUser(User user);

    public void deleteById(Integer id);

    public List<User> findAllByFirstName(String name);

    public List<User> findAllByNickName(String nickName);
}
