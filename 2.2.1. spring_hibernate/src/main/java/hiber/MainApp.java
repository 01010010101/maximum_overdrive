package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      CarService carService = context.getBean(CarService.class);
      carService.add(new Car("Vaz", 2107));
      carService.add(new Car("Dodge Charger", 1970));
      carService.add(new Car("Ferrari", 1488));
      carService.add(new Car("Golfcart", 6969));
      List<Car> cars = carService.listCars();
      for (Car car : cars) {
         System.out.println("Id = "+car.getId());
         System.out.println("Model = "+car.getModel());
         System.out.println("Series = "+car.getSeries());
      }

      UserService userService = context.getBean(UserService.class);
      userService.add(new User("Mikhail", "Boyarskiy", "1000ofimps@mail.ru", cars.get(0)));
      userService.add(new User("Dominik", "Torretto", "fastnfurious@mail.ru", cars.get(1)));
      userService.add(new User("Michael", "Schumacher", "user3@mail.ru", cars.get(2)));
      userService.add(new User("Johny", "Knoxwille", "user4@mail.ru", cars.get(3)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("My car = "+
                 (user.getCar() != null ? user.getCar().getModel() + " " + user.getCar().getSeries()
                         : "I have no car!"));
      }
      System.out.println(userService.getUserByCar("Golfcart", 6969));
      context.close();
   }
}