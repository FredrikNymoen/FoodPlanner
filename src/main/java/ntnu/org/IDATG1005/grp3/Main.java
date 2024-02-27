package ntnu.org.IDATG1005.grp3;

import java.security.Provider;
import java.security.Provider.Service;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello world!");
    Provider provider = new Provider("Provider 1", 1.0, "Info 1") {
      @Override
      public Service getService(String type, String algorithm) {
        return super.getService(type, algorithm);
      }
    };
    Service service = new Service(provider, "Type 1", "Algorithm 1", "ClassName 1", null, null);
    System.out.println(service.getProvider().getInfo());



  }
}