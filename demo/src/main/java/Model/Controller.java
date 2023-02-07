package Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private UserService userservice;

    @RequestMapping("/retrieveAllDat")
    public List<UserData> retrieveAllData() {
        return userservice.RetrieveAllData();
    }
    @RequestMapping("/retrieveData/{name}")
    public UserData retrieveData(@PathVariable("name") String name) {
          return userservice.retrieveData(name);
    }


    @PostMapping("/addData")
   // @RequestMapping(method = RequestMethod.POST, value = "/addData")
    public void addData(@RequestBody UserData userdata ) {

        userservice.addUser1(userdata);
    }



    @RequestMapping("/Deletedata/{name}")
    public void deleteData(@PathVariable("name") String name ) {

          userservice.deleteUser(name);
    }



}
