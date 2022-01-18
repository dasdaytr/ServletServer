package test.services;

import test.DAO.DataBase;
import test.Model.Person;
import test.Model.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UsersAdminService {

    public List<Person> getPersonRoleUser( ){
        return new DataBase().getRoleUser();
    }
    public void updateUserRole(String name,String password){
        new DataBase().updateUserRole(name,password);
    }
}
