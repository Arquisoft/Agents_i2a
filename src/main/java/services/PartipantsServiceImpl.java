package services;

import dbmanagement.Database;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.JasyptEncryptor;

/**
 * Created by Nicolás on 14/02/2017.
 * Modified by Marcos on 17/02/2018
 */
@Service
public class PartipantsServiceImpl implements ParticipantsService {

    private final Database dat;
    private final JasyptEncryptor encryptor = new JasyptEncryptor();

    @Autowired
    PartipantsServiceImpl(Database dat){
        this.dat = dat;
    }

    @Override
    public User getAgent(String name, String password, String kind) {
    	User user = dat.getAgent(name);
    	System.out.println(user);
    	if (user != null && encryptor.checkPassword(password, user.getPassword())
    		&& user.getKind() == kind)
    	    return user;
    	else
    	    return null;
    }

    @Override
    public void updateInfo(User user, String newPassword) {
    	//It is not necessary, done by the domain class itself.
    	user.setPassword(newPassword);
    	dat.updateInfo(user);
    }
}
