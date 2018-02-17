package domain_tests;

import domain.User;
import domain.UserInfo;
import domain.UserInfoAdapter;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nicolás on 18/02/2017.
 * Modified by Marcos on 17/02/2018
 */
public class UserAdapterTest {

    private User user1;
    private User user2;

    @Before
    public void setUp(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1996);
        user1 = new User("User1", "Oviedo", "User1@hola.com", "user1Password", "10", "Person", 1);
        user2 = new User("User2", "Gijon", "User2@hola.com", "user2Password", "11", "Person", 1);
    }

    @Test
    public void testAdapter(){
        UserInfoAdapter adapter = new UserInfoAdapter(user1);
        UserInfo info = adapter.userToInfo();
        assertEquals(info.getName(), user1.getName());
        assertEquals(info.getEmail(), user1.getEmail());
        assertEquals(info.getKind(), user1.getKind());
        assertEquals(info.getKindCode(), user1.getKindCode());
        assertEquals(info.getLocation(), user1.getLocation());
        assertEquals(info.getId(), user1.getUserId());
        
        UserInfoAdapter adapter1 = new UserInfoAdapter(user2);
        UserInfo info1 = adapter1.userToInfo();
        assertEquals(info1.getName(), user2.getName());
        assertEquals(info1.getEmail(), user2.getEmail());
        assertEquals(info1.getKind(), user2.getKind());
        assertEquals(info1.getKindCode(), user2.getKindCode());
        assertEquals(info1.getLocation(), user2.getLocation());
        assertEquals(info1.getId(), user2.getUserId());
    }

}
